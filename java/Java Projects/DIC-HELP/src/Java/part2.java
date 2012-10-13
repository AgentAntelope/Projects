package Java;

import java.util.Scanner; // import delcaration for program to use class Scanner

public class part2
{
// main method begins execution of Java application
public static void main( String args[] )
{
Scanner input = new Scanner( System.in ); // create Scanner to obtain input from the command window

System.out.println(); // white space for readability
System.out.printf("Payroll Program Part 2\n"); // program name displayed, cursor placed on next line
System.out.printf("This program will determine the weekly pay for an employee.\n"); // program description
System.out.printf("Please input the information as it is requested.\n"); // program instructions
System.out.println(); // white space for readability

double hoursWorked; // variable for the employee's hours worked
double hourlyRate; // variable for the employee's hourly rate
double weeklyPay; // variable for the employee's calculated weekly pay

System.out.print("Enter the employee's name (enter stop to quit): "); // prompt
String employeeName = input.nextLine(); // read employee's name

while ( !employeeName.equalsIgnoreCase ( "stop" ) ) // condition statement to request data until stop is entered
{
System.out.print("Enter the hours worked by empoyee this week: "); // prompt
hoursWorked = input.nextDouble(); // read hours worked

while ( hoursWorked <= 0 ) // condition statement to check for negative number
{
System.out.println("Error: You have entered a negative number!"); // error message
System.out.print("Enter the hours worked by employee this week: "); // prompt
hoursWorked = input.nextDouble(); // read hours worked
} // end while

System.out.print("Enter the hourly rate of pay for the employee: "); // prompt
hourlyRate = input.nextDouble(); // read hourly rate

while ( hourlyRate <= 0 ) // condition statement to check for negative number
{
System.out.println("Error: You have entered a negative number!"); // error message
System.out.print("Enter the hourly rate of pay for the employee: "); // prompt
hourlyRate = input.nextDouble(); // read hourly rate
} // end while

weeklyPay = hoursWorked * hourlyRate; // calculate the employee's weekly pay

System.out.println(); // white space for readability

System.out.println("Results...");

System.out.printf("Employee: %s\n", employeeName); // display employee name

System.out.printf("Number of hours worked this week: %.2f\n", hoursWorked); // display hours worked, rounded

System.out.printf("Employee's hourly rate of pay: $%.2f\n", hourlyRate); // display hourly rate, rounded

System.out.printf("The employee's weekly pay is $%.2f", weeklyPay); // display weekly pay, rounded

System.out.println(); // white space for readability

System.out.println(); // white space for readability

System.out.print("Enter another employee's name (enter stop to quit): "); // prompt
employeeName = input.nextLine(); // read employee's name
System.out.println();

} // end while

if ( employeeName.equalsIgnoreCase ( "stop" ) )
{
System.out.println("Thank you for using the Payroll Program!");
} // end if

} // end method main

} // end class PayrollProgramPart2 
