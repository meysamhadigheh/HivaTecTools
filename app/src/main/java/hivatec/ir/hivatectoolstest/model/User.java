package hivatec.ir.hivatectoolstest.model;

public class User {

	public String name;
	public int coins;

	public User(String name, int coins) {
		this.name = name;
		this.coins = coins;
	}

	public void addOneCoin(){

		coins += 1;
	}
}
