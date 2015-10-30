package routing;

import game.BlackJackGame;

import java.util.ArrayList;
import java.util.HashMap;

public class GamesRouter {
	// TODO Concurrency
	private ArrayList<BlackJackGame> games = new ArrayList<BlackJackGame>();
	{
		games.add(new BlackJackGame());
	}

	public HashMap<String, String> handleUserRequest(String userId,
			String command) {
		int gameIndex = getUserGame(userId);
		HashMap<String, String> result = games.get(gameIndex).respond(userId,
				command);
		return result;
	}

	private int getUserGame(String userId) {
		// TODO
		return 0;
	}
}
