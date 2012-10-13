public class Player{

	private String firstName, lastName;
	private int money, roundsPlayed, roundsWon;


	public Player(String f, int m){
		firstName = f;
		money = m;
	}

	public Player(int m){
		money = m;
	}

	public int getMoney(){
		return money;
	}

	public void addMoney(int amountAdded){
		money += amountAdded;
	}
	public void subtractMoney(int amountSub){
		money -= amountSub;
	}

	public String getFirstName(){
		return firstName;
	}

}