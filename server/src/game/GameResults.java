package game;

import users.*;

/**
 * This class implements methods for definition of status and result of the game
 * 
 * @author max
 *
 */
public class GameResults {
	/**
	 * This method returns the result of the game
	 */
	public static String gameResult(DealerPlayer d, HomoSapiensPlayer hp) {
		if (hp.isBusted()) {
			return "PLAYER_BUSTED";
		} else if (d.isBusted()) {
			return "DEALER_BUSTED";
		} else if (d.sumPoints() > hp.sumPoints())
			return "DEALER_WINS";
		else if ((d.sumPoints() < hp.sumPoints() || hp.sumPoints() == 21))
			return "PLAYER_WINS";
		else
			return "TIE";
	}

	/**
	 * This method shows whether you can continue. If you can'n continue, you
	 * get the result
	 */
	public static String gameStatus(DealerPlayer d, HomoSapiensPlayer hp) {
		if (hp.sumPoints() < 21)
			return "OK";
		else
			return gameResult(d, hp);
	}

}
