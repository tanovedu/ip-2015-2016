package org.elsysbg.ip.sockets;

import java.io.PrintStream;

public class ClientSocketExample {
	public static void main(String[] args) {
		final PrintStream out = System.out;
		out.println("GET / HTTP/1.1");
		out.println("Host:www.example.com");
		out.println("Connection:close");
		out.println("");
	}
}
