package game;

import java.util.HashMap;

public class Errors {
	private static final HashMap<String, String> noCommandMessage = new HashMap<String, String>();
	static {
		noCommandMessage.put("error", "no command");
		noCommandMessage.put("gameStatus", "OK");
	}
	private static final HashMap<String, String> errorRestart = new HashMap<String, String>();
	static {
		noCommandMessage.put("error", "Game is restarted");
	}

	public static HashMap<String, String> getNocommand() {
		return noCommandMessage;
	}

	public static HashMap<String, String> getRestart() {
		return errorRestart;
	}
}
