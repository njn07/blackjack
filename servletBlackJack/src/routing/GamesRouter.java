package routing;

import game.BlackJackGame;

import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class GamesRouter {
	private static final long CLEANER_COOLDOWN = 300_000;
	Timer cleaner = new Timer();
	private ConcurrentHashMap<String, BlackJackGame> games = new ConcurrentHashMap<String, BlackJackGame>();

	public GamesRouter() {
		cleaner.schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				
				System.out.println("Router: launching cleaner");
				for (String key : games.keySet()) {
					Date launched = games.get(key).getLastLaunched();
					Date now = new Date();
					long diff = now.getTime() - launched.getTime();// as given
					long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
					long seconds = TimeUnit.MILLISECONDS.toSeconds(diff);
					if (seconds > 15) {
						games.remove(key);
						System.out.println("removed game for user " + key);
					}
				}
			}
		}, CLEANER_COOLDOWN,CLEANER_COOLDOWN);
	}

	public HashMap<String, String> handleUserRequest(String userId,
			String command) {
		HashMap<String, String> result = getUserGame(userId).respond(userId,
				command);
		return result;
	}

	private BlackJackGame getUserGame(String userId) {
		BlackJackGame game = games.get(userId);
		if (game != null) {
			return game;
		}
		games.put(userId, new BlackJackGame());
		return getUserGame(userId);
	}
}
