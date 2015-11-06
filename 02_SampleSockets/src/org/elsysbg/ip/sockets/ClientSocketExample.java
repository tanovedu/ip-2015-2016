package org.elsysbg.ip.sockets;

public class ClientSocketExample {
	public static void main(String[] args) {
		System.out.println("GET / HTTP/1.1");
		System.out.println("Host:www.example.com");
		System.out.println("Connection:close");
		System.out.println("");
	}
}
