package game;

import java.util.HashMap;

import auth.UserManager;

public class BlackJackGame extends AbstractBJGame {
	UserManager manager = UserManager.getManager();

	protected void setBalance(String userId, int chipCount) {
		manager.setBalance(userId, player.getChipCount());
	}

	@Override
	protected void refill(HashMap<String, String> result, String username) {
		player.refill();
		manager.setBalance(username, player.getChipCount());
		System.out.println("refilling player" + username
				+ " his balance is now " + player.getChipCount());
		result.put("refill", Integer.toString(player.getChipCount()));
	}
	@Override
	protected void processGameOver(String userId, HashMap<String, String> result) {
		super.processGameOver(userId, result);
		String gameResult=result.get("gameStatus");
		int winIncr=0,lostIncr=0,balance=player.getChipCount();
		if(gameResult=="PLAYER_WINS"||gameResult=="DEALER_BUSTED"){
			winIncr++;
		}
		else if(gameResult!="TIE"){
			lostIncr++;
		}
		manager.updateUserStats(userId,winIncr,lostIncr,balance);
		Restart();
	}

	@Override
	protected void processGetBalance(String userId,
			HashMap<String, String> result) {
		int playersMoney = manager.getBalance(userId);
		player.setChipCount(playersMoney);
		result.put("balance", Integer.toString(player.getChipCount()));
	}
}
