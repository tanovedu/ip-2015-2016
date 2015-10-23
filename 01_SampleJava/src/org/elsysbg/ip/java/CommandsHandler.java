package org.elsysbg.ip.java;

import java.util.Scanner;

public class CommandsHandler {
	public static void main(String[] args) {
		// input:
		System.out.print("Enter command: ");
		final Scanner in = new Scanner(System.in);
		final String command = in.next();
		
		// processing:
		final int result = execute(command);
		
		// output:
		System.out.println("Result: " + result);

		in.close();
	}

	/**
	 * @param command something like: sum:1:2 or you can use something standard like json/xml
	 * @return result
	 * @throws IllegalArgumentException if command is unknown
	 */
	private static int execute(String command) throws IllegalArgumentException {
		final String[] split = command.split(":");
		if ("sum".equals(split[0])) {
			return Integer.valueOf(split[1])
						+ Integer.valueOf(split[2]);
		}
		throw new IllegalArgumentException(
				"Unknown command: " + command);
	}
}
