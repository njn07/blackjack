package users;

/**
 * This class implements functional of Registered user
 * 
 * @author max
 *
 */
public class RegisteredUser extends AbstractUser {
	int gamesPlayed;// total amount of played games
	int chipsAmount = 50000;// chips left on this account
	
	/**
	 * Refills player's chips to a certain number
	 */
	public void refill(){
		chipsAmount=50000;
	}

}
