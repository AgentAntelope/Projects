package SecondWave;
import java.io.IOException;
import java.net.*;

public class ChatClient {
 public Socket myClient;
 public ChatClient(){
	 try {
		myClient = new Socket("My Client", 4444);
	} catch (Exception e) {
		e.printStackTrace();
	} 
	 
 }
}
