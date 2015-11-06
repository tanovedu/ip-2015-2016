package org.elsysbg.ip.sockets;

import java.io.IOException;

public class EchoServerStarter {
	private static final int SERVER_PORT = 31111;

	public static void main(String[] args) throws IOException {
		final EchoServer server = new EchoServer(SERVER_PORT);
		server.startServer();
	}
}
