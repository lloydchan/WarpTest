@echo off
setlocal EnableDelayedExpansion

set _cp=cfg;resources;

for /f %%a IN ('dir target\dependency /b') do (
	set _cp=!_cp!target/dependency/%%a;
)

set _cp=!_cp!target/handgame-1.0.0.jar

echo.CLASSPATH=%_cp%

java -Xms4G -Xmx4G -XX:+UseConcMarkSweepGC -Dcom.sun.management.jmxremote.port=7001 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -cp %_cp%  -Dlog4j.configuration=log4j.properties warp.common.util.SpringLoader -f beans/gui.xml -r LifeCycleContainer -r gui   