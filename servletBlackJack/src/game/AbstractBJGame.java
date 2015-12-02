package game;

import java.util.Date;
import java.util.HashMap;

import users.DealerPlayer;
import users.HumanPlayer;
import deckpac.Deck;

public abstract class AbstractBJGame {

	Deck deck = new Deck();
	DealerPlayer dealer = new DealerPlayer();
	HumanPlayer player = new HumanPlayer();
	GameState state = GameState.Finished;
	Date lastLaunched;

	public Date getLastLaunched() {
		return lastLaunched;
	}

	public void updateLaunchedTime() {
		this.lastLaunched = new Date();
	}

	protected void Restart() {
		dealer.update();
		player.update();
		deck = new Deck();
	}

	public HashMap<String, String> respond(String userId, String command) {
		System.out.print(this.getClass().getSimpleName() + ": ");
		System.out.println("command " + command + " from " + userId
				+ " game state: " + state);
		updateLaunchedTime();
		try {
			HashMap<String, String> result = new HashMap<String, String>();
			result.put("userId", userId);
			result.put("action", command);
			if (command.startsWith("PLACEBET")) {
				if (state != GameState.Betting) {
					throw new Exception("Wrong game state " + state);
				}
				state = GameState.OnGoing;
				return processBet(result, command);
			}
			if (command.equals("GETBALANCE")) {
				System.out.println("getting balance");
				if (state != GameState.Finished) {
					return Errors.badParams();
				}
				System.out.println(state != GameState.Finished);
				processGetBalance(userId, result);
				return result;
			}
			if (command.equals("REFILL")) {
				if (state == GameState.Finished) {
					System.out.println(userId + " is refilled with 1000!");
					refill(result, userId);
					return result;
				} else {
					throw new BlackJackGameException(
							"can't refill while game ongoing!");
				}
			}
			if (command.equals("RESTART")) {
				hardRestart(result);
				return result;
			}
			switch (command) {
			case "GAMESTART":
				System.out.println("game start+restart for " + userId);
				processRestart(result);
				state = GameState.Betting;
				return result;
			case "HIT":
				System.out.println("hitting, gamestate is " + state);
				if (state != GameState.OnGoing) {
					throw new BlackJackGameException("wrong state");
				}
				processHit(result);
				break;
			case "STAND":
				if (this.state != GameState.OnGoing) {
					throw new BlackJackGameException("wrong state");
				}
				processStand(result);
				break;
			default:
				return Errors.getNocommand();
			}
			if (gameOver(result, userId)) {
				System.out.println("game is over");
				state = GameState.Finished;
				processGameOver(userId, result);
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

	protected void hardRestart(HashMap<String, String> result) {
		Restart();
		player = new HumanPlayer();
		result.put("DEBUG", " Game has been restarted! Balance is default");
		state = GameState.Finished;
		System.out.println("restarted!");
	}

	protected void processGameOver(String userId, HashMap<String, String> result) {
		result.put("balance", Integer.toString(player.getChipCount()));

		Restart();
	}

	protected void processStand(HashMap<String, String> result) {
		state = GameState.Stand;
		System.out.println("player is standing");
		dealer.play(deck);
		result.put("dealerCards", dealer.displayCards());
		result.put("dealerSum", Integer.toString(dealer.sumPoints()));
		System.out.println("dealers score is " + dealer.sumPoints());
		result.put("gameStatus", GameResults.gameResult(dealer, player));
	}

	protected void processHit(HashMap<String, String> result) {
		player.play(deck);
		result.put("playerCard", player.getLastCardVisualisation());
		result.put("playerSum", Integer.toString(player.sumPoints()));
		result.put("gameStatus", GameResults.gameStatus(dealer, player));

	}

	protected void processRestart(HashMap<String, String> result) {
		Restart();
		result.put("balance", Integer.toString(player.getChipCount()));

	}

	protected void processGetBalance(String userId,
			HashMap<String, String> result) {
		result.put("balance", Integer.toString(player.getChipCount()));
	}

	protected void refill(HashMap<String, String> result, String username)
			throws BlackJackGameException {
		throw new BlackJackGameException("cant refill unregistered user");
	}

	protected HashMap<String, String> processBet(
			HashMap<String, String> result, String betString) {
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
		System.out.println("[GAME OVER: player " + userId + " result "
				+ resString + "]");
		if (resString.equals("PLAYER_BUSTED")
				|| resString.equals("DEALER_WINS")) {
			return true;
		}
		if (resString.equals("DEALER_BUSTED")
				|| resString.equals("PLAYER_WINS")) {
			setWinChips(player, userId);
			if (player.sumPoints() == 21) {
				result.put("winSum", Integer.toString(player.getPot() / 2 * 3));
			} else {
				result.put("winSum", Integer.toString(player.getPot() * 2));
			}
			return true;
		}
		if (resString.equals("TIE")) {
			setTieChips(player, userId);
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
