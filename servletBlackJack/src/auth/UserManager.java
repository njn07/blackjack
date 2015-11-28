package auth;

import java.util.Collection;
import java.util.HashMap;

import users.User;

public class UserManager {

	private static UserManager manager = new UserManager();

	public static UserManager getManager() {
		return manager;
	}

	private UserManager() {
	}

	HashMap<String, User> users = new HashMap<>();
	{
		User admin = new User("admin", "admin@asd.com", "admin", 10000, 0, 0, 0);
		User mickey = new User("user", "user@asd.com", "user", 0, 0, 0, 0);
		addUser(admin);
		addUser(mickey);
	}

	public synchronized void addUser(User userToAdd) {
		users.put(userToAdd.getLogin(), userToAdd);
	}

	public synchronized int getBalance(String userLogin) {
		User user=users.get(userLogin);
		if(user!=null){
			return user.getBalance();
		}
		return -1;
	}
	
	public synchronized User getUser(String name, String password) {
		User user = users.get(name);
		if (user != null) {
			if (user.getPassword().equals(password))
				return user;
		}
		return null;
	}

	public synchronized Collection<User> getAllUsers() {
		return users.values();
	}

	public synchronized User deleteUser(String userNameToDelete) {
		return users.remove(userNameToDelete);
	}

	public void setBalance(String username, int newChipCount) {
		System.out.println("setting balance for"+newChipCount+username);
		users.get(username).setBalance(newChipCount);
		
	}
	
	public void updateUserStats(String username, int winIncr, int lostIncr, int balance) {
		User user=users.get(username);
		user.setGames_won(user.getGames_won()+winIncr);
		user.setGames_lost(user.getGames_lost()+lostIncr);
		user.setGames_played(user.getGames_played()+1);
		if(winIncr==0){
			user.setGames_played(user.getGames_played()+1);
		}
		user.setBalance(balance);
		System.out.println(username+" update balance to "+balance);
	}

}
