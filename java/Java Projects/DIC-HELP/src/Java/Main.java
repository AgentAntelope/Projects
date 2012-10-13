package Java;

import java.text.DecimalFormat;

/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/


/**
*
* @author Yuki
*/
public class Main {

/**
* @param args the command line arguments
*/
public static void main(String[] args) {
// Declares and builds the variable of the loan amount
int loanAmount = 200000;
double loanBalance = 0;
double interestPaid = 0;
int loanTerm [] = {84, 180, 360};
double interest [] = {.035, .0550, .2575};
double monthlyPayment = 0;
DecimalFormat fmt = new DecimalFormat ("0.##");

//Starts array loop
for (int i = 0; i < interest.length; i++){
//Declares formula
monthlyPayment = (loanAmount*(interest [i]/12))/(1-1/Math.pow((1+interest [i]/12), loanTerm [i]));

//Displays loop with first mortgage and interest paid
if (i==1)
monthlyPayment = (loanAmount*(interest [0]/12))/(1-1/Math.pow ((1+interest [0]/12), loanTerm [0]));
loanBalance = loanAmount - monthlyPayment;
interestPaid = monthlyPayment*interest [0];

while (loanBalance > 0){

//Displays Mortgage Calculator with the first terms
System.out.println ("Mortgage Calculator");
System.out.println ("The loan amount is $200000");
System.out.println ("The term of the loan is " + loanTerm [0]);
System.out.println ("The monthly payment is $" + fmt.format (monthlyPayment));
System.out.println ("The loan balance is: $" + fmt.format (loanBalance));
System.out.println ("The interest paid for the loan is $" + fmt.format (interestPaid));
System.out.println ();

loanBalance = loanAmount - monthlyPayment;
interestPaid = monthlyPayment*interest [0];

//Displays loop with the second terms
if (i==2)
monthlyPayment = (loanAmount*(interest [0]/12))/(1-1/Math.pow ((1+interest [0]/12), loanTerm [0]));
loanBalance = loanAmount - monthlyPayment;
interestPaid = monthlyPayment*interest [0];

//Displays Mortgage Calculator with the second terms
System.out.println ("Mortgage Calculator");
System.out.println ("The loan amount is $200000");
System.out.println ("The term of the loan is " + loanTerm [0]);
System.out.println ("The monthly payment is $" + fmt.format (monthlyPayment));
System.out.println ("The loan balance is: $" + fmt.format (loanBalance));
System.out.println ("The interest paid for the loan is $" + fmt.format (interestPaid));
System.out.println ();

//Displays loop with the third terms
if (i==3)
monthlyPayment = (loanAmount*(interest [0]/12))/(1-1/Math.pow ((1+interest [0]/12), loanTerm [0]));
loanBalance = loanAmount - monthlyPayment;
interestPaid = monthlyPayment*interest [0];

//Displays Mortgage Calculator with the third terms
System.out.println ("Mortgage Calculator");
System.out.println ("The loan amount is $200000");
System.out.println ("The term of the loan is " + loanTerm [0]);
System.out.println ("The monthly payment is $" + fmt.format (monthlyPayment));
System.out.println ("The loan balance is: $" + fmt.format (loanBalance));
System.out.println ("The interest paid for the loan is $" + fmt.format (interestPaid));
System.out.println ();

}
}
}
} 