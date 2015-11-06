package org.elsysbg.ip.sockets;

import java.io.PrintStream;
import java.util.Scanner;

public class ClientSocketExample {
	public static void main(String[] args) {
		final PrintStream out = System.out;
		out.println("GET / HTTP/1.1");
		out.println("Host:www.example.com");
		out.println("Connection:close");
		out.println("");
		
		final Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			final String line = scanner.nextLine();
			System.out.println(line);
		}
		scanner.close();
	}
}
