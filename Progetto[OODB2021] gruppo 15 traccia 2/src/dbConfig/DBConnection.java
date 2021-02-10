package dbConfig;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

	private static DBConnection instance;
	private Connection connection = null;
	private String USERNAME;
	private String PASSWORD;
	private String IP;
	private String PORT;
	private String DB;
	private String URL = "jbdc:postegresql://" + IP + ":" + PORT + "/" + DB;

	// metodo per caricare le proprietà del Database da file
	public void loadProperties() throws Exception {
		Properties proprietà = new Properties();
		try {
			FileInputStream config = new FileInputStream("db.properties");
			proprietà.load(config);
		} catch (Exception e) {
			System.out.println("Errore dati configurazione");
		}
		USERNAME = proprietà.getProperty("User");
		PASSWORD = proprietà.getProperty("Password");
		IP = proprietà.getProperty("IP");
		PORT = proprietà.getProperty("Port");
		DB = proprietà.getProperty("DBname");
	}

	private DBConnection() throws SQLException {

		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

		} catch (ClassNotFoundException ex) {
			System.out.println("Database Connection Creation Failed : " + ex.getMessage());
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
