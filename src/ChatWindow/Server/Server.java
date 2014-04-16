package ChatWindow.Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class Server implements Runnable {

	private List<ServerClient> clients = new ArrayList<ServerClient>();

	private DatagramSocket socket;
	private int port;
	private boolean running = false;
	private Thread runServer, manage, send, receive;

	public Server(int port) {
		this.port = port;
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
			return;
		}
		runServer = new Thread(this, "Server");
		runServer.start();
	}

	public void run() {
		running = true;
		System.out.println("Server started on port: " + port);
		manageClients();
		receive();
	}

	private void manageClients() {
		// Manage the clients and check for client activity
		manage = new Thread("Manage") {
			public void run() {
				while (running) {
					// Managing
				}
			}
		};
		manage.start();

	}

	private void receive() {
		receive = new Thread("Receive") {
			public void run() {
				while (running) {
					// Receiving
					byte[] data = new byte[1024];
					DatagramPacket packet = new DatagramPacket(data,
							data.length);
					try {
						socket.receive(packet);
					} catch (IOException e) {
						e.printStackTrace();
					}

					process(packet);

					clients.add(new ServerClient("Jon", packet.getAddress(),
							packet.getPort(), 50));
					System.out.println("Connection from: "
							+ clients.get(0).address.toString() + ": "
							+ clients.get(0).port);
				}
			}
		};
		receive.start();
	}
	
	private void send(final byte[] data, final InetAddress ipAddress, final int port){
		send = new Thread("Send"){
			public void run(){
				DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
				try {
					socket.send(packet);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}; 
		send.start();
	}
	
	private void sendToAll(String message){
		for (int i = 0; i < clients.size(); i++){
			ServerClient client = clients.get(i);
			send(message.getBytes(),client.address,client.port);
		}
	}

	private void process(DatagramPacket packet) {
		String stringReceived = new String(packet.getData());
		//System.out.println(stringReceived);
		if (stringReceived.startsWith("/c/")) {
			
			int id = UniqueIdentifier.getIdentifier();
			
			clients.add(new ServerClient(stringReceived.substring(3,stringReceived.length()), 
					packet.getAddress(), packet.getPort(), id));
			System.out.println(stringReceived.substring(3,stringReceived.length()));
		} else if (stringReceived.startsWith("/m/")){
			//String message = stringReceived.substring(3,stringReceived.length());
			System.out.println(stringReceived);
			sendToAll(stringReceived);
		} else {
			System.out.println("message not formated");
		}

	}

}
