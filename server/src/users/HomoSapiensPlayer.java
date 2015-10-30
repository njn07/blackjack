package users;

import deckpac.Deck;

/**
 * This class implements human BlackJack game
 * 
 * @author max
 *
 */
public class HomoSapiensPlayer extends AbstractPlayer {

	@Override
	public void play(Deck d) {
		getCard(d.takeCard());
	}

}
