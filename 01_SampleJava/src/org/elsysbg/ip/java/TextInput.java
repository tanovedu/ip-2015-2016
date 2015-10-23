package org.elsysbg.ip.java;

import java.util.Scanner;

public class TextInput {

	public static void main(String[] args) {
		System.out.print("Enter your name: ");
		// standard input is held by System.in
		final Scanner in = new Scanner(System.in);
		// get text from standard input
		// scanner is used
		// for more info: http://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html
		final String name = in.next();
		System.out.printf("Hello, %s", name);
		// always close resources!
		in.close();
	}
}
