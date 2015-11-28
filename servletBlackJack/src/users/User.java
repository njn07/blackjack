package users;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3210440699370088846L;
	private String login;
	private String email;
	private String password;
	private int balance;
	private int games_played;
	private int games_won;
	private int games_lost;

	public User() {
	}

	public User(String login, String email, String password, int balance,
			int games_played, int games_won, int games_lost) {
		super();
		this.login = login;
		this.email = email;
		this.password = password;
		this.balance = balance;
		this.games_played = games_played;
		this.games_won = games_won;
		this.games_lost = games_lost;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getGames_played() {
		return this.games_lost + this.games_won;
	}

	public void setGames_played(int games_played) {
		this.games_played = games_played;
	}

	public int getGames_won() {
		return games_won;
	}

	public void setGames_won(int games_won) {
		this.games_won = games_won;
	}

	public int getGames_lost() {
		return games_lost;
	}

	public void setGames_lost(int games_lost) {
		this.games_lost = games_lost;
	}

	@Override
	public String toString() {
		return "User [login=" + login + ", email=" + email + ", password="
				+ password + ", balance=" + balance + ", games_played="
				+ games_played + ", games_won=" + games_won + ", games_lost="
				+ games_lost + "]";
	}

}
