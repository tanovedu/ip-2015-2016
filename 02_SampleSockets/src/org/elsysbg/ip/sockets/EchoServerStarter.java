package org.elsysbg.ip.sockets;

public class EchoServerStarter {
	private static final int SERVER_PORT = 31111;

	public static void main(String[] args) {
		final EchoServer server = new EchoServer(SERVER_PORT);
		server.startServer();
	}
}
