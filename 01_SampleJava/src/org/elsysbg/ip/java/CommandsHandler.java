package org.elsysbg.ip.java;

import java.util.Scanner;

public class CommandsHandler {
	public static void main(String[] args) {
		// input:
		System.out.print("Enter command: ");
		final Scanner in = new Scanner(System.in);
		final String command = in.next();
		
		// processing:
		final CommandsHandler handler = parse(command);
		final String result = handler.run();
		
		// output:
		System.out.println("Result: " + result);

	}

}
