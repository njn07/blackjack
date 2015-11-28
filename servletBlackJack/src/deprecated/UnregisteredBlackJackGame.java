package deprecated;

import game.Errors;

import java.util.HashMap;

import users.HumanPlayer;

public class UnregisteredBlackJackGame extends BlackJackGame {

	@Override
	protected void refill(HashMap<String, String> result, String username) {
		result.put("error", Errors.REFILLNOTALLOWED);
	}

	@Override
	protected void setTieChips(HumanPlayer p, String username) {
		p.setChipCount(p.getChipCount() + p.getPot());
	}

	@Override
	protected void setBalance(String userId, int chipCount) {
		// manager.setBalance(userId, player.getChipCount());

	}

	@Override
	protected void processGetBalance(String userId,
			HashMap<String, String> result) {
		result.put("balance", Integer.toString(player.getChipCount()));
	}

	@Override
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

	@Override
	protected void setWinChips(HumanPlayer p, String username) {
		p.setChipCount(p.getChipCount() + p.getPot() * 2);

	}
}
