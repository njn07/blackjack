package persistence;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void doTest(String[] args) {
		
		try {
			Connector.getConnection();
			System.out.println("Hoorray");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage()+" - message");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		User u1 = new User("user", "user@mail.com", "user", 1000, 10, 4, 6);
//		User u2 = new User("user2", "user2@mail.com", "user2", 2000, 20, 8, 12);
//		User u3 = new User("user3", "user3@mail.com", "user3", 3000, 30, 12, 18);
//		User u4 = new User("user4", "user4@mail.com", "user4", 4000, 40, 16, 24);
//		User u5 = new User("user5", "user5@mail.com", "user5", 5000, 50, 20, 30);
//		
//		List<User> users = new ArrayList<>();
//		users.add(u1);
//		users.add(u2);
//		users.add(u3);
//		users.add(u4);
//		users.add(u5);
//		
//		//=============================================================
//		//ADDING USERS
//		
////		for (User user : users) {
////			try {
////				UserUtil.addUser(user);
////			} catch (ClassNotFoundException | NoSuchAlgorithmException | SQLException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
////		}
//		//=============================================================
//		
//		//GET ALL USERS
//		
//		List<User> users_got = new ArrayList<>();
//		try {
//			users_got = UserUtil.getAllUsers();
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		for (User user : users_got) {
//			System.out.println(user);			
//		}
//		//=============================================================
//		
//		//GET 1 USER
//		
//		User user_got = null;
//		try {
//			user_got = UserUtil.getUser("user", "user");
//		} catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("======================================");
//		System.out.println(user_got);
//		
//		//=============================================================	
//		//UPDATE BALANCE
//		
//		System.out.println("======================================");
//		try {
//			UserUtil.updateUserBalance("user", 10000);
//		} catch (NoSuchAlgorithmException | ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		// =============================================================
//		// UPDATE STATS
//
//		System.out.println("======================================");
//		try {
//			UserUtil.updateUserStats("user3", 300000, 1000, 50000);
//		} catch (NoSuchAlgorithmException | ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		// =============================================================
//		// UPDATE INFO
//
//		System.out.println("======================================");
//		try {
//			UserUtil.updateUserInfo("user2", "user2_new@mail.com", "user2pass");
//		} catch (NoSuchAlgorithmException | ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		// =============================================================
//		// DELETE
//
//		System.out.println("======================================");
//		try {
//			UserUtil.deleteUser("user5");
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	

}
