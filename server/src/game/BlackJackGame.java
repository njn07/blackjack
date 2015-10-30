package game;

import java.util.HashMap;

import users.DealerPlayer;
import users.HomoSapiensPlayer;
import deckpac.Deck;

public class BlackJackGame {
	Deck deck = new Deck();
	DealerPlayer dealer = new DealerPlayer();
	HomoSapiensPlayer player = new HomoSapiensPlayer();
	HashMap<String, String> noCommandMessage = new HashMap<String, String>();
	{
		noCommandMessage.put("error", "no command");
	}

	private void Restart() {
		dealer = new DealerPlayer();
		player = new HomoSapiensPlayer();
	}

	public HashMap<String, String> respond(String userId, String command) {
		HashMap<String, String> result = new HashMap<String, String>();
		if (command == null) {
			return noCommandMessage;
		}
		switch (command) {
		case "GAMESTART":
			result.put("", "");
			break;
		case "RESTART":// DEBUG COMMAND
			Restart();
			result.put("DEBUG", " Game has been restarted! ");
			return result;
		case "PLACEBET":
			dealer.play(deck);// here we already know dealer's cards, but
								// players don't
			result.put("dealerCard", dealer.getPlayerCards().get(1)
					.getVisualization());// return
			// visualization of the second dealer card. For example: dealer has
			// (3h; 4h; 8s) it will return 4h
			result.put(
					"dealerSum",
					Integer.toString(dealer.getPlayerCards().get(1)
							.getcardPoints()));// second
												// card
												// points
			player.play(deck);
			player.play(deck);// player takes two cards
			result.put("playerCards", player.displayCards());// visualization
			result.put("playerSum", Integer.toString(player.sumPoints()));// total
																			// points
			break;
		case "HIT":
			player.play(deck);
			result.put(
					"playerCard",
					player.getPlayerCards()
							.get(player.getPlayerCards().size() - 1)
							.getVisualization());// get last player's card
			result.put("playerSum", Integer.toString(player.sumPoints()));
			result.put("gameStatus", GameResults.gameStatus(dealer, player));// current
			// status
			break;
		case "STAND":
			dealer.play(deck);
			result.put("dealerCards", dealer.displayCards());// all dealer cards
			result.put("dealerSum", Integer.toString(dealer.sumPoints()));// total
																			// dealer
																			// points
			result.put("gameStatus", GameResults.gameResult(dealer, player));// result
																				// of
																				// the
																				// game
			break;
		default:
			return noCommandMessage;
		}
		if (gameOver(result)) {
			Restart();
		}
		return result;

	}

	private boolean gameOver(HashMap<String,String> result) {
		String resString=result.get("gameStatus");
			if(resString.equals("PLAYER_BUSTED"))
				return true;
			if(resString.equals("DEALER_BUSTED"))
				return true;
			if(resString.equals("PLAYER_WINS"))
				return true;
			if(resString.equals("DEALER_WINS"))
				return true;
			if(resString.equals("TIE"))
				return true;
		return false;
	}

}
