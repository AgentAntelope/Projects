// CS 401 Fall 2009
// Modifications to DoMathInt to handle some exceptions.
// I have also added my own exception class to handle integer
// overflow -- note that integer overflow by default in Java does
// NOT cause an exception.

import javax.swing.*;
import java.awt.event.*;

class DoMathIntCheck implements ActionListener {

  DoMathIntCheck(JTextField first, JTextField second,
         JTextField result)
  {
    inputOne = first;
    inputTwo = second;
    output = result;
  }
  public void actionPerformed(ActionEvent e) {
	  int first, second;

	  try
	  {
		  first = Integer.parseInt(inputOne.getText().trim());
		  second = Integer.parseInt(inputTwo.getText().trim());
		  // Test for overflow by seeing if the second integer is
		  // greater than the maximum allowed value minus the first
		  // integer.  We could test for "underflow" in a similar
		  // way for subtraction.
		  if (e.getActionCommand().equals("Add"))
		  {
			  if (second > Integer.MAX_VALUE - first)
				  throw new OverFlowException("Add");
			  output.setText(String.valueOf(first + second));
		  }
		  else if (e.getActionCommand().equals("Subtract"))
			  output.setText(String.valueOf(first - second));
		  // Test for overflow using multiplication
		  else if (e.getActionCommand().equals("Multiply"))
		  {
			  if (second > Integer.MAX_VALUE / first)
				  throw new OverFlowException("Multiply");
			  output.setText(String.valueOf(first * second));
		  }
		  else
			  output.setText(String.valueOf(first / second));
	  }
	  // This exception can occur when the strings are being
	  // parsed.  Pass the error into the output textfield.
	  catch (NumberFormatException e2)
	  {
		  output.setText(e2.toString() + ":operand is not a legal integer");
	  }
	  // This exception is explicitly thrown it overflow is
	  // detected -- pass it into the output textfield
	  catch (OverFlowException e3)
	  {
	  	  output.setText(e3.toString());
	  }
	  // This exception is thrown when division by zero occurs.
	  // Again, pass it to the textfield.  Note that OverFlowException
	  // is defined to be a subclass of ArithmeticException, so the
	  // handler below would also catch OverFlowException if a more
	  // specific handler were not indicated above.  Test this by
	  // commenting out the previous handler above -- you should see
	  // no real difference in the execution, since both handlers are
	  // simply outputing the toString() result of the exception.
	  catch (ArithmeticException e4)
	  {
		  output.setText(e4.toString());
	  }
  }
  private JTextField inputOne, inputTwo, output;
}

// Extending ArithmeticException.  The only real functionality of
// this class is its name, which identifies it.  However, this is
// still useful since now it is very clear what the problem is
// based on the type of the exception alone.
class OverFlowException extends ArithmeticException
{
	public OverFlowException(String s)
	{
		super(s);
	}
}
