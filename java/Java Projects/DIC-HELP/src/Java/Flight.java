package Java;

import java.io.*;
import java.util.LinkedList;
import javax.swing.*;

     class Flight {
       public String name;
       public Passenger passenger = null;
       public FlightList curflights = new FlightList();

        public Flight() {}
        
        public boolean equals(Object node) {
              return name.equals(((Flight) node).name);
       }
        
        public void display() {
              System.out.println(name);
              curflights.display();
       }

}

     class Passenger
{
       public String name;
       public ReservationList reservation = new ReservationList();
        public Passenger(){}
        public boolean equals(Object node) {
              return name.equals(((Passenger) node).name);
       }
        public void display(){
              if (!reservation.isEmpty()) {

                     String fmsg = name +  " has a flight reservation" + reservation.toString();
                     JOptionPane.showMessageDialog("\nPassenger List:\n " +
                        fmsg);
              }
              else System.out.print(name + " does not have any flight reservations.");
       }
}

     class Reservation{
       public Flight flight = null;
        public Reservation(){}
    
        public boolean equals(Object node){
              return flight.name.equals(((Reservation) node).flight.name);
        }
       }

     class PassengerList extends LinkedList
{
        public PassengerList() {
              super();
       }
        public void display(){
              for (java.util.Iterator it = iterator(); it.hasNext(); )
                     ((Passenger)it.next()).display();
       }
}

     class ReservationList extends LinkedList
{
        public ReservationList() {
              super();
       }
        public void display(){
              for (int i = 0; i < size(); i++ )
                     System.out.print(get(i));
       }
}


      class Airline {

       private ReservationList[] bookings = new ReservationList[(int)('z'+1)];
       private PassengerList[] people = new PassengerList[(int)('z'+1)];

        public Airline() {
              for (int i = 0; i <= (int) 'z'; i++){
                     catalog[i] = new FlightList();
                     people[i] = new PassengerList();
              }
       }

        private void availFlight() {
              Flight newFlight = new Flight();
              int oldFlight;
              for (int i = 401; i < 405; i++ ){
                     newFlight.name = Integer.toString(i);
                     oldFlight = catalog[(int)
                        newFlight.name.charAt(0)].indexOf(newFlight);
                     if (oldFlight == -1){
                        catalog[(int) newFlight.name.charAt(0)].add(newFlight);
                     }
                     else catalog[(int) newFlight.name.charAt(0)].get(oldFlight);
                     catalog[(int) newFlight.name.charAt(0)].add(newFlight);

              }
       }


        public static void main (String [] args)
       {
              (new Airline()).start();
       }


        private void makeRes() {
              Passenger newpass = new Passenger(), passRef;
              Flight flight = new Flight(), flightRef = new Flight();

              int inputNumber;
              int passIndex, flightIndex = -1;
              String pass = "Enter passenger's name:";
              String strpass = JOptionPane.showInputDialog(null, pass, "", 1);
              newpass.name = strpass;


              Reservation madeReservation = new Reservation();

              passIndex = people[(int)
                             newpass.name.charAt(0)].indexOf(newpass);
              if (passIndex == -1)
                     newpass.reservation.add(flight.name);
              people[(int) newpass.name.charAt(0)].add(newpass);



       }

        private void findRes() {
              String pname;
              pname = JOptionPane.showInputDialog(null, " Enter passenger's name:", "", 1);

              for (int i = (int) 'A'; i <= (int) 'Z'; i++)
                     if (!people[i].isEmpty())
                        people[i].display();

       }

        private void delRes() {
              Passenger passenger = new Passenger();
              Flight flight = new Flight();
              int passIndex = -1, flightIndex = -1;

              String input;

              while (passIndex == -1) {

                     System.out.println();
                     passenger.name = JOptionPane.showInputDialog(null, " Enter passenger's name:", "", 1);
                     passIndex = people[(int) passenger.name.charAt(0)].indexOf(passenger);
                    if (passIndex == -1)
                        passenger.name = JOptionPane.showInputDialog(null, "Name misspelled, Enter passenger's name:", 1);
              }

       }

        private void listPas() {
              for (int i = (int) 'A'; i <= (int) 'Z'; i++)
                     if (!people[i].isEmpty()
                     )
                        people[i].display();


       }

// Program exits when option 5 is selected.
        public void start()
       {
              String msg = "*********Airline Reservation********* \n" +
                      "Select one of the following options \n" +
                      "1. Reserve a ticket \n" +
                      "2. Check Reservation \n" +
                      "3. Cancel Reservation \n" +
                      "4. Display List of passengers \n" +
                      "5. Exit \n";


       // While Loop until option 5 is selected by User
              while(true)
              {
              //Display menu from String
                     String str = JOptionPane.showInputDialog(null, msg, "", 1);
                     int selection = Integer.parseInt(str);

              //case statements for selection
                     switch(selection)
                     {
                        case 1:
                               makeRes();
                               break;
                        case 2:
                               findRes();
                               break;
                        case 3:
                               delRes();
                        case 4:
                               listPas();
                               break;
                        case 5:
                               return;
                        default:
                               JOptionPane.showInputDialog(null, "\n Please select a number from 1 through 5, try again. /n", 1);
                     }
              }
       }
}