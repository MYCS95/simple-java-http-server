package simpleHTTPServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * A Simple HTTP Server
 */

/**
 * @author mycs
 *
 */
public class SimpleHTTPServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		
		// Create a web server
		final int PORT = 8080;
		final ServerSocket server = new ServerSocket(PORT);
		
		System.out.println("Listenning for connection on port 8080 ....");
		
		while(true) {
			
			// Accept connection
			final Socket clientSocket = server.accept();
			
			// Read request from browser
			InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
			BufferedReader reader = new BufferedReader(isr);
			
			String line = reader.readLine();
			
			while(!line.isEmpty()) {
				System.out.println(line);
				line = reader.readLine();
			}
			
			// Send today Date to client
			try(Socket socket = server.accept()) {
				Date today = new Date();
				String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + today;
				socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
			}
		}
	}

}
