package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
	private static final String LOGIN = "adminfwcGvfg";
	private static final String PASSWORD = "M6IqEsEpMEBu";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String HOST = "jdbc:mysql://127.6.40.2:3306";

	private static Connection connection;
	
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("JDBC Driver not Found");
		}
		try {
			connection = DriverManager.getConnection(HOST, LOGIN, PASSWORD);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Connection Failed! Check output console");
		}
		return connection;
	}
}
