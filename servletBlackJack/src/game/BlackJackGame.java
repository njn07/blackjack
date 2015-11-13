package game;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import users.DealerPlayer;
import users.HumanPlayer;
import deckpac.Deck;

public class BlackJackGame {

	Deck deck = new Deck();
	DealerPlayer dealer = new DealerPlayer();
	HumanPlayer player = new HumanPlayer();
	GameState state=GameState.Finished;
	Date lastLaunched;
	public Date getLastLaunched() {
		return lastLaunched;
	}

	public void updateLaunchedTime() {
		this.lastLaunched = new Date();
	}

	private void Restart() {
		dealer.update();
		player.update();
		if(player.getChipCount()<1)player.refill();
	}

	public HashMap<String, String> respond(String userId, String command) {
		System.out.println("new request with command ==>"+command);
		updateLaunchedTime();
		try {
			HashMap<String, String> result = new HashMap<String, String>();

			if (command == null) {
				return Errors.getNocommand();
			}
			try {
				if (command.substring(0, 8).equals("PLACEBET")) {
					if(state!=GameState.Started){
						throw new Exception("Wrong game state "+state);
					}
					System.out.println("betting ");
					state=GameState.Betting;
					return processBet(result, command);
				}
			} catch (StringIndexOutOfBoundsException ex) {
			}
			if(command.equals("GETBALANCE")){
				result.put("balance", Integer.toString(player.getChipCount()));
				return result;
			}
			//
			switch (command) {
			case "GAMESTART":
				System.out.println("game start+restart for "+userId);
				Restart();
				result.put("balance", Integer.toString(player.getChipCount()));
				state=GameState.Started;
				return result;

			case "RESTART":// DEBUG COMMAND
				Restart();
				player = new HumanPlayer();
				result.put("DEBUG", " Game has been restarted! Balance is refilled");
				state=GameState.Finished;
				return result;
			case "HIT":
				if(state==GameState.Finished){
					throw new Exception("wrong state");
				}
				System.out.println("player is hitting "+userId);
				state=GameState.OnGoing;
				player.play(deck);
				result.put(
						"playerCard",
						player.getPlayerCards()
								.get(player.getPlayerCards().size() - 1)
								.getVisualization());// get
														// last
														// player's
														// card
				result.put("playerSum", Integer.toString(player.sumPoints()));
				result.put("gameStatus", GameResults.gameStatus(dealer, player));// current
				// status
				break;
			case "STAND":
				if(state==GameState.Finished){
					throw new Exception("wrong state");
				}
				state=GameState.Stand;
				System.out.println("player is standing");
				dealer.play(deck);
				result.put("dealerCards", dealer.displayCards());// all dealer
																	// cards
				result.put("dealerSum", Integer.toString(dealer.sumPoints()));// total
				System.out.println("dealers score is "+dealer.sumPoints());																// dealer
																				// points
				result.put("gameStatus", GameResults.gameResult(dealer, player));// result
																					// of
																					// the
																					// game
				break;
			default:
				return Errors.getNocommand();
			}
			if (gameOver(result)) {
				System.out.println("game is over");
				state=GameState.Finished;
				result.put("balance", Integer.toString(player.getChipCount()));
				Restart();
			}
			return result;
		} catch (Exception ex) {
			Restart();
			return Errors.getRestart();
		}
	}

	private HashMap<String, String> processBet(HashMap<String, String> result,
			String betString) {
		int bet = 0;
		try {
			bet = Integer.parseInt(betString.substring(8));
			player.setPot(bet);
			int chipcount=player.getChipCount();
			player.setChipCount(chipcount-bet);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(bet + ' ' + betString);
			result.put("error", "bad bet");
			result.put("gameStatus", "OK");
			return result;
		}
		dealer.play(deck);
		result.put("dealerCards", dealer.getPlayerCards().get(1)
				.getVisualization());
		result.put("dealerSum", Integer.toString(dealer.getPlayerCards().get(1)
				.getcardPoints()));
		player.play(deck);
		player.play(deck);
		result.put("playerCards", player.displayCards());
		result.put("playerSum", Integer.toString(player.sumPoints()));
		result.put("balance", Integer.toString(player.getChipCount()));
		return result;
	}

	private boolean gameOver(HashMap<String, String> result) {
		String resString = result.get("gameStatus");
		System.out.println(" game result "+resString);
		if (resString.equals("PLAYER_BUSTED")
				|| resString.equals("DEALER_WINS")) {
			return true;
		}
		if (resString.equals("DEALER_BUSTED")
				|| resString.equals("PLAYER_WINS")) {
			player.setChipCount(player.getChipCount() + player.getPot() * 2);
			result.put("winSum", Integer.toString(player.getPot() * 2));
			return true;
		}
		if (resString.equals("TIE")) {
			player.setChipCount(player.getChipCount() + player.getPot());
			//System.out.println("tie, playe");
			return true;
		}
		return false;
	}

}
