package warp.handgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * program to execute
 *
 */
public class App {
	public static final String PROMPT_USER_TURN = "Your turn:";
	
	public static void main(String[] args) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s;
		System.out.println("Game start\n Character represebt Rock(r), Paper(p), Scissors(c), Lizard(l), Spock(s)");
		
		System.out.println(PROMPT_USER_TURN);
		System.out.println(PROMPT_USER_TURN);
		try {
			while ((s = in.readLine()) != null && s.length() != 0)
				System.out.println(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
