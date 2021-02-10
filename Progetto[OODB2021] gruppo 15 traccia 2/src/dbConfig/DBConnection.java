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

	// metodo per caricare le propriet� del Database da file
	public void loadProperties() throws Exception {
		Properties propriet� = new Properties();
		try {
			FileInputStream config = new FileInputStream("db.properties");
			propriet�.load(config);
		} catch (Exception e) {
			System.out.println("Errore dati configurazione");
		}
		USERNAME = propriet�.getProperty("User");
		PASSWORD = propriet�.getProperty("Password");
		IP = propriet�.getProperty("IP");
		PORT = propriet�.getProperty("Port");
		DB = propriet�.getProperty("DBname");
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
