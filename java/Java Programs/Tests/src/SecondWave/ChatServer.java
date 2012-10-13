package SecondWave;

import java.net.*;

public class ChatServer {
	
	ServerSocket myServer;
	public ChatServer(){
		
	try{
		myServer = new ServerSocket(4444);
	 }
	catch(Exception e){		}
	
	}
	
}
