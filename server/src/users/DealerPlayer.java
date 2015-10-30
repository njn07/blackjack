package users;

import deckpac.Deck;

/**
 * This class implements the dealer play
 * 
 * @author max
 *
 */
public class DealerPlayer extends AbstractPlayer {

	@Override
	public void play(Deck d) {
		while (sumPoints() < 17)
			getCard(d.takeCard());

	}

}
