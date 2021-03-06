package persistence;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;

import com.mysql.jdbc.exceptions.jdbc4.MySQLDataException;

import javax.naming.OperationNotSupportedException;

import users.User;

public class UserManager {

	private static UserManager manager = new UserManager();

	public static UserManager getManager() {
		return manager;
	}

	protected UserManager() {
	}

	HashMap<String, User> users = new HashMap<>();
	{
		User admin = new User("admin", "admin@asd.com", "admin", 10000, 0, 0,
				0, 0);
		User mickey = new User("user", "user@asd.com", "user", 0, 0, 0, 0, 0);
		try {
			addUser(admin);
			addUser(mickey);
		} catch (Exception e) {
			System.out.println("user add error");
		}
	}

	public synchronized void addUser(User userToAdd)
			throws PersistenceException {
		users.put(userToAdd.getLogin(), userToAdd);
	}

	public synchronized int getBalance(String userLogin)
			throws PersistenceException {
		User user = users.get(userLogin);
		if (user != null) {
			return user.getBalance();
		}
		return -1;
	}

	public synchronized Collection<User> getAllUsers()
			throws PersistenceException {
		return users.values();
	}

	public synchronized User deleteUser(String userNameToDelete)
			throws OperationNotSupportedException {
		return users.remove(userNameToDelete);
	}

	public void setBalance(String username, int newChipCount)
			throws PersistenceException {
		System.out.println("setting balance for" + newChipCount + username);
		users.get(username).setBalance(newChipCount);

	}

	public void updateUserStats(String username, int winIncr, int lostIncr,
			int draws, int balance) throws PersistenceException {
		User user = users.get(username);
		user.setGames_won(user.getGames_won() + winIncr);
		user.setGames_lost(user.getGames_lost() + lostIncr);
		user.setGames_draws(draws);
		if (winIncr == 0) {
			user.setGames_played(user.getGames_played() + 1);
		}
		user.setBalance(balance);
		System.out.println(username + " update balance to " + balance);
	}

	public User getVerifyUser(String name, String password)
			throws PersistenceException {
		User user = users.get(name);
		if (user != null) {
			if (user.getPassword().equals(password))
				return user;
		}
		return null;

	}

	public User getUser(String login) throws PersistenceException {
		return users.get(login);
	}
}
