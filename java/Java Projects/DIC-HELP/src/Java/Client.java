package Java;

import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class Client extends JFrame 
{
   private JTextField enterField; // for entering messages
   private JTextArea displayArea; // for displaying messages
   private DatagramSocket socket; // socket to connect to server

   // set up GUI and DatagramSocket
   public Client()
   {
      super( "Client" );

      enterField = new JTextField( "Type message here" );
      enterField.addActionListener(
         new ActionListener() 
         { 
            public void actionPerformed( ActionEvent event )
            {
               try // create and send packet
               {
                  // get message from textfield 
                  String message = event.getActionCommand();
                 // displayArea.append( "\nSending packet containing: " + message + "\n" );

                  byte data[] = message.getBytes(); // convert to bytes
         
                  // create sendPacket
                  DatagramPacket sendPacket = new DatagramPacket( data, 
                     data.length, InetAddress.getLocalHost(), 5000 );

                  socket.send( sendPacket ); // send packet
                  //displayArea.append( "Packet sent\n" );
                  //displayArea.setCaretPosition(displayArea.getText().length() );
               } // end try
               catch ( IOException ioException ) 
               {
                  displayMessage( ioException.toString() + "\n" );
                  ioException.printStackTrace();
               } // end catch
            } // end actionPerformed
         } // end inner class
      ); // end call to addActionListener

      add( enterField, BorderLayout.NORTH );

      displayArea = new JTextArea();
      add( new JScrollPane( displayArea ), BorderLayout.CENTER );

      setSize( 400, 300 ); // set window size
      setVisible( true ); // show window

      try // create DatagramSocket for sending and receiving packets
      {
         socket = new DatagramSocket();
      } // end try
      catch ( SocketException socketException ) 
      {
         socketException.printStackTrace();
         System.exit( 1 );
      } // end catch
   } // end Client constructor

   // wait for packets to arrive from Server, display packet contents
   public void waitForPackets()
   {
      while ( true ) 
      {
         try // receive packet and display contents
         {
            byte data[] = new byte[ 100 ]; // set up packet
            DatagramPacket receivePacket = new DatagramPacket( 
               data, data.length );

            socket.receive( receivePacket ); // wait for packet

            // display packet contents
            displayMessage( //"\nPacket received:" + 
               //"\nFrom host: " + receivePacket.getAddress() + 
               //"\nHost port: " + receivePacket.getPort() + 
               //"\nLength: " + receivePacket.getLength() + 
               "\nContaining:\n\t" + new String( receivePacket.getData(), 
                  0, receivePacket.getLength() ) );
         } // end try
         catch ( IOException exception ) 
         {
            displayMessage( exception.toString() + "\n" );
            exception.printStackTrace();
         } // end catch
      } // end while
   } // end method waitForPackets

   // manipulates displayArea in the event-dispatch thread
   private void displayMessage( final String messageToDisplay )
   {
      SwingUtilities.invokeLater(
         new Runnable()
         {
            public void run() // updates displayArea
            {
               displayArea.append( messageToDisplay );
            } // end method run
         }  // end inner class
      ); // end call to SwingUtilities.invokeLater
   } // end method displayMessage
}  // end class Client