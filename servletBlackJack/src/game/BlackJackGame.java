package game;

import java.util.HashMap;

import persistence.PersistenceException;
import persistence.UserManager;
import users.HumanPlayer;
import users.User;

public class BlackJackGame extends AbstractBJGame {
	public BlackJackGame(String registeredPlayerLogin) {
		try {
			User user =manager.getUser(registeredPlayerLogin);
			System.out.println(user+" ==============setting user");
			player.setUser(user);
		} catch (Exception e) {
			System.out.println("error while setting user, user is now null");
		}
	}

	UserManager manager = UserManager.getManager();

	protected void setBalance(String userId, int chipCount) {
		try {
			manager.setBalance(userId, player.getChipCount());
		} catch (PersistenceException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	protected void refill(HashMap<String, String> result, String username) {
		player.refill();
		try {
			manager.setBalance(username, player.getChipCount());
		} catch (PersistenceException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("refilling player" + username
				+ " his balance is now " + player.getChipCount());
		result.put("refill", Integer.toString(player.getChipCount()));
	}

	@Override
	protected void processGameOver(String userId, HashMap<String, String> result) {
		result.put("balance", Integer.toString(player.getChipCount()));
		String gameResult=result.get("gameStatus");
		System.out.println(player.getUser()+" is user stats");
		int win=player.getUser().getGames_won();
		int lost=player.getUser().getGames_lost();
		int draws=player.getUser().getGames_draws();
		int balance=player.getChipCount();
		if(gameResult=="PLAYER_WINS"||gameResult=="DEALER_BUSTED"){
			win++;
		}
		else if(gameResult=="DEALER_WINS"||gameResult=="PLAYER_BUSTED"){
			lost++;
		}
		else if(gameResult=="TIE"){
			draws++;
		}
		try {
			manager.updateUserStats(userId,win,lost,draws,balance);
		} catch (PersistenceException e) {
			System.out.println(e.getMessage());
		}
		Restart();
	}

	@Override
	protected void hardRestart(HashMap<String, String> result) {
		Restart();
		result.put("DEBUG",
				" Game has been restarted! Balance is default");
		state = GameState.Finished;
		System.out.println("restarted!");	
	}
	@Override
	protected void processGetBalance(String userId,
			HashMap<String, String> result) {
		int playersMoney;
		try {
			playersMoney = manager.getBalance(userId);
		} catch (PersistenceException e) {
			System.out.println("blackjack game setbalance exception "
					+ e.getMessage());
			playersMoney = 0;
		}
		player.setChipCount(playersMoney);
		result.put("balance", Integer.toString(player.getChipCount()));
	}
}
