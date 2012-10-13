package NOfQueens;
/*
 * Sean Myers
 * stm52@pitt.edu
 * 2/6/09
 * 
 */
public class NQueen {
	
public int RecursiveCalls;//How many recursive calls in the program.
public int Solutions;//How many Solutions
public Boolean Pruning;//Pruning on / off
public Boolean Printing;//Printing Solutions on / off
private int Size; //Used for checking Rows
private int[] BoardArray;


public void Solve(int size)
{
	BoardArray = new int[size];
	Size = size;
	RecursiveCalls= 0;
	Solutions = 0;
	SolveRec(0,0);
}

public void SolveRec(int i, int j)
{
	RecursiveCalls++;
if(i == Size)
	{
if(Pruning)
{
	if(Check(i-1))
		{
		if(Printing){
			printBoard();
		}
		
		Solutions++;
		}
}
else{
	
	if(CheckNoPrune(i-1))
	{
		if(Printing)
		printBoard();
		Solutions++;
	
	}
}
	
	}
// Base Case ----------------------------------------------------

else
	{
	for(;j < Size; j++){
			BoardArray[i]= j;

	if(Pruning){
		if(Check(i))
		{
			SolveRec(i+1, 0);	
		}
		else if(!Check(i)&& j < Size)
		{
			
		}

			}
	//Pruning-------------------------------------------
	
	
	else
			{

	SolveRec(i+1, 0);
			}
		}
		
	}
	
}
public Boolean Check(int CurrentColumn)
{


	for(int i = CurrentColumn; i > 0; i--)
	{
		
		int CheckDiag = CurrentColumn - (i-1);
		if(BoardArray[CurrentColumn] == BoardArray[i-1])
		{
			return false;
		}
		if(BoardArray[CurrentColumn] == (BoardArray[i-1]-CheckDiag) && (BoardArray[i-1]-CheckDiag >= 0))
		{
			return false;
		}
		if(BoardArray[CurrentColumn] == (BoardArray[i-1]+CheckDiag) && (BoardArray[i-1]+CheckDiag) < Size)
		{

			return false;
		}
		if(BoardArray[CurrentColumn] >= Size)
			return false;
	
	}
	return true;


}
public Boolean CheckNoPrune(int CurrentColumn)
{
	for(;CurrentColumn > 0; CurrentColumn--)
	{
	for(int i = CurrentColumn; i > 0; i--)
	{
		int CheckDiag = CurrentColumn - (i-1);
		if(BoardArray[CurrentColumn] == BoardArray[i-1])
		{
			return false;
		}
		if(BoardArray[CurrentColumn] == (BoardArray[i-1]-CheckDiag) && (BoardArray[i-1]-CheckDiag >= 0))
		{
			return false;
		}
		if(BoardArray[CurrentColumn] == (BoardArray[i-1]+CheckDiag) && (BoardArray[i-1]+CheckDiag) < Size)
		{

			return false;
		}
		if(BoardArray[CurrentColumn] >= Size)
			return false;
	
	}
	}
	return true;


}
private void printBoard()
{
	for(int l = 0; l < Size; l++)
	{
		for(int h = 0; h < Size; h++)
		{
			if(BoardArray[h] == l)
		System.out.print(" [X] ");
			else
		System.out.print(" [ ] ");
			
			if(h == Size-1)
				System.out.println();
		}
	}
	System.out.println("------------------------------");
}

}
