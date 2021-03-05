package dbConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static DBConnection instance;
	private Connection connection = null;

	private final String username = "postgres";
	private final String password = "password";
	private final String port = "5432";
	private final String IP = "localhost";
	private final String url = "jdbc:postgresql://" + IP + ":" + port + "/ProjectsManagementDB";

	private DBConnection() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public static DBConnection getInstance() throws SQLException {
		if (instance == null) {
			instance = new DBConnection();
		} else if (instance.getConnection().isClosed()) {
			instance = new DBConnection();
		}

		return instance;
	}
}
