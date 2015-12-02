package deprecated;

import game.BlackJackGameException;
import game.Errors;
import game.GameResults;
import game.GameState;

import java.util.Date;
import java.util.HashMap;

import persistence.UserManager;
import users.DealerPlayer;
import users.HumanPlayer;
import deckpac.Deck;

public class BlackJackGame {

	Deck deck = new Deck();
	UserManager manager = UserManager.getManager();
	DealerPlayer dealer = new DealerPlayer();
	HumanPlayer player = new HumanPlayer();
	GameState state = GameState.Finished;
	Date lastLaunched;
	{
	}

	public Date getLastLaunched() {
		return lastLaunched;
	}

	public void updateLaunchedTime() {
		this.lastLaunched = new Date();
	}

	private void Restart() {
		dealer.update();
		player.update();
	}

	public HashMap<String, String> respond(String userId, String command) {
		System.out.println(this.getClass().getName() + " is responsing");
		System.out.println("new request with command ==>" + command + " from "
				+ userId);
		System.out.println("game state ==> " + state);
		updateLaunchedTime();
		try {
			HashMap<String, String> result = new HashMap<String, String>();
			result.put("userId", userId);
			result.put("action", command);
			if (command.startsWith("PLACEBET")) {
				if (state != GameState.Betting) {
					throw new Exception("Wrong game state " + state);
				}
				System.out.println("betting ");
				state = GameState.OnGoing;
				return processBet(result, command);
			}
			if (command.equals("GETBALANCE")) {
				processGetBalance(userId, result);
				return result;
			}
			if (command.equals("REFILL")) {
				if (state == GameState.Finished) {
					refill(result, userId);
					return result;
				} else {
					throw new BlackJackGameException(
							"can't refill while game ongoing!");
				}
			}
			switch (command) {
			case "GAMESTART":
				System.out.println("game start+restart for " + userId);
				Restart();
				result.put("balance", Integer.toString(player.getChipCount()));
				state = GameState.Betting;
				return result;

			case "RESTART":// DEBUG COMMAND
				Restart();
				player = new HumanPlayer();
				result.put("DEBUG",
						" Game has been restarted! Balance is refilled");
				state = GameState.Finished;
				return result;
			case "HIT":
				System.out.println("hitting, gamestate is " + state);
				if (state != GameState.OnGoing) {
					throw new BlackJackGameException("wrong state");
				}
				player.play(deck);
				result.put("playerCard", player.getLastCardVisualisation());
				result.put("playerSum", Integer.toString(player.sumPoints()));
				result.put("gameStatus", GameResults.gameStatus(dealer, player));
				break;
			case "STAND":
				if (this.state != GameState.OnGoing) {
					throw new BlackJackGameException("wrong state");
				}
				state = GameState.Stand;
				System.out.println("player is standing");
				dealer.play(deck);
				result.put("dealerCards", dealer.displayCards());
				result.put("dealerSum", Integer.toString(dealer.sumPoints()));
				System.out.println("dealers score is " + dealer.sumPoints());
				result.put("gameStatus", GameResults.gameResult(dealer, player));
				break;
			default:
				return Errors.getNocommand();
			}
			if (gameOver(result, userId)) {
				System.out.println("game is over");
				state = GameState.Finished;
				result.put("balance", Integer.toString(player.getChipCount()));
				setBalance(userId, player.getChipCount());
				Restart();
			}
			return result;
		} catch (Exception ex) {
			System.out.println("Exception occured!" + ex.getMessage());
			for (StackTraceElement call : ex.getStackTrace()) {
				System.out.println(call);
			}
			Restart();
			return Errors.getRestart();
		}
	}

	protected void setBalance(String userId, int chipCount) {
		//manager.setBalance(userId, player.getChipCount());

	}

	protected void processGetBalance(String userId,
			HashMap<String, String> result) {
		//int playersMoney = manager.getBalance(userId);
		//player.setChipCount(playersMoney);
		result.put("balance", Integer.toString(player.getChipCount()));
	}

	protected void refill(HashMap<String, String> result, String username) {
		player.refill();
		//manager.setBalance(username, player.getChipCount());
		System.out.println("refilling player"+username+" his balance is now "+player.getChipCount());
		result.put("refill", Integer.toString(player.getChipCount()));
	}

	private HashMap<String, String> processBet(HashMap<String, String> result,
			String betString) {
		int bet = 0;
		try {
			bet = Integer.parseInt(betString.substring(8));
			player.setPot(bet);
			int chipcount = player.getChipCount();
			player.setChipCount(chipcount - bet);
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

	protected boolean gameOver(HashMap<String, String> result, String userId) {
		String resString = result.get("gameStatus");
		System.out.println(" game result " + resString);
		if (resString.equals("PLAYER_BUSTED")
				|| resString.equals("DEALER_WINS")) {
			
			return true;
		}
		if (resString.equals("DEALER_BUSTED")
				|| resString.equals("PLAYER_WINS")) {
			setWinChips(player, userId);
			result.put("winSum", Integer.toString(player.getPot() * 2));
			return true;
		}
		if (resString.equals("TIE")) {
			setTieChips(player, userId);
			// System.out.println("tie, playe");
			return true;
		}
		return false;
	}

	protected void setTieChips(HumanPlayer p, String username) {
		int newChipCount = p.getChipCount() + p.getPot();
		p.setChipCount(newChipCount);

	}

	protected void setWinChips(HumanPlayer p, String username) {
		int newChipCount = p.getChipCount() + p.getPot() * 2;
		p.setChipCount(newChipCount);
	}

}
