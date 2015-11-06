package org.elsysbg.ip.sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class EchoServer {
	private final int port;
	private boolean running;
	private final List<ClientHandler> clients =
		Collections.synchronizedList(
			new LinkedList<ClientHandler>());

	public EchoServer(int port) {
		this.port = port;
	}

	public void startServer() throws IOException {
		setRunning();
		final ServerSocket serverSocket =
			new ServerSocket(port);
		
		// to synchronize access to the variable 'running'
		while(isRunning()) {
			final Socket socket = serverSocket.accept();
			
			final ClientHandler client =
				new ClientHandler(this, socket);
			clients.add(client);
			new Thread(client).start();
		}
		serverSocket.close();
	}

	private synchronized void setRunning() {
		if (running) {
			throw new IllegalStateException("Already running");
		}
		running = true;
	}
	
	public synchronized boolean isRunning() {
		return running;
	}

	public synchronized void stopServer() throws IOException {
		running = false;
		
		for (ClientHandler next : clients) {
			next.stopClient();
		}
	}

	public void onClientStopped(ClientHandler clientHandler) {
		clients.remove(clientHandler);
	}
}
