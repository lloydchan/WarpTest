package warp.common.util;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import warp.common.ILifeCycleContainer;

public class SpringLoader implements IStoppable {
	private final static Logger logger = Logger.getLogger(SpringLoader.class);
	private Object theLock = new Object();
	private ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();

	@Override
	public void stop() {
		synchronized (theLock) {
			theLock.notifyAll();
		}
	}

	private static void usage(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("SpringLoader", options);
	}

	@SuppressWarnings("static-access")
	public void load(String[] args) {
		Options options = new Options();
		options.addOption("f", true, "[f] filename");
		// options.addOption("r", true, "[r] runner");
		Option myOption = OptionBuilder.withDescription("[r] runner").hasArgs().create("r");
		options.addOption(myOption);
		myOption = OptionBuilder.withDescription("[t] stopTime").hasArgs().create("t");
		options.addOption(myOption);
		CommandLineParser parser = new BasicParser();
		CommandLine cmd;
		try {
			cmd = parser.parse(options, args);
		} catch (ParseException pe) {
			usage(options);
			return;
		}

		String filename = null;
		if (cmd.hasOption("f")) {
			filename = cmd.getOptionValue("f");
			logger.info("Reading file " + filename);
			// System.out.println("Reading file " + filename);
		}
		List<String> runners = new ArrayList<String>();
		if (cmd.hasOption("r")) {
			String[] myRunners = cmd.getOptionValues("r");
			runners = Arrays.asList(myRunners);
			logger.info("Triggering runners " + runners);
			// System.out.println("Triggering runners " + runners);
		}

		if (filename != null) {
			List<ILifeCycleContainer> lifecycles = new ArrayList<ILifeCycleContainer>();
			@SuppressWarnings("resource")
			ApplicationContext context = new ClassPathXmlApplicationContext(filename);
			if (runners.size() == 0) {
				for (String runner : context.getBeanDefinitionNames()) {
					Object obj = (Object) context.getBean(runner);
					if (obj instanceof ILifeCycleContainer) {
						lifecycles.add((ILifeCycleContainer) obj);
					}
					if (obj instanceof IStoppableController) {
						((IStoppableController) obj).register(this);
					}
				}
			} else {
				for (String runner : runners) {
					Object obj = (Object) context.getBean(runner);
					if (obj instanceof ILifeCycleContainer) {
						lifecycles.add((ILifeCycleContainer) obj);
					}
					if (obj instanceof IStoppableController) {
						((IStoppableController) obj).register(this);
					}
				}

			}

			for (ILifeCycleContainer runner : lifecycles) {
				runner.init();
			}

			for (ILifeCycleContainer runner : lifecycles) {
				runner.start();
			}

			LocalTime curTime = LocalTime.now();
			LocalTime endTime = LocalTime.MAX;
			logger.info("Current time is " + curTime + " and end time is " + endTime);
			long delay = curTime.until(endTime, ChronoUnit.MILLIS);
			logger.info("Scheduled to stop in " + delay + " ms");
			exec.schedule(new Runnable() {
				@Override
				public void run() {
					stop();

				}
			}, delay, TimeUnit.MILLISECONDS);

			synchronized (theLock) {
				try {
					theLock.wait();
				} catch (InterruptedException e) {
					logger.warn("Fail to pause", e);
				}
			}

			for (ILifeCycleContainer runner : lifecycles) {
				runner.stop();
			}

			for (ILifeCycleContainer runner : lifecycles) {
				runner.finit();
			}

			System.exit(0);
		}

	}

	public static void main(String[] args) {
		SpringLoader mySpringLoader = new SpringLoader();
		// for (String arg : args) {
		// System.out.println(arg);
		// }
		mySpringLoader.load(args);

	}

}
