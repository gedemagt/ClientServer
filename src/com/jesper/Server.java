package com.jesper;

import java.io.*;
import java.net.*;

public class Server {

	private static PrintWriter out;
	private static BufferedReader in;
	private static ServerSocket serverSocket = null;
	private static Socket clientSocket = null;
	private static String inputLine, outputLine;
	private static Protocol kkp;
	
	public static void start() throws IOException {

        
        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }

        while(true)
        {
	        try {
	            clientSocket = serverSocket.accept();
	        } catch (IOException e) {
	            System.err.println("Accept failed.");
	            System.exit(1);
	        }
	
	        out = new PrintWriter(clientSocket.getOutputStream(), true);
	        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	        kkp = new Protocol();
	
	        outputLine = kkp.processInput(null);
	        out.println(outputLine);
	        
	        while ((inputLine = in.readLine()) != null) {
	        	 System.out.println(inputLine);
	             outputLine = kkp.processInput(inputLine);
	             out.println(outputLine);
	        }
        }

    }
	
	public static void shutDown() throws IOException
	{
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
        System.exit(1);
	}
	
	public static Protocol getProtocol()
	{
		return kkp;
	}
	
}
