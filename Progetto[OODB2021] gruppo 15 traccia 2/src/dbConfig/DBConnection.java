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

	// metodo per caricare le proprietÓ del Database da file
	public void loadProperties() throws Exception {
		Properties proprietÓ = new Properties();
		try {
			FileInputStream config = new FileInputStream("db.properties");
			proprietÓ.load(config);
		} catch (Exception e) {
			System.out.println("Errore dati configurazione");
		}
		USERNAME = proprietÓ.getProperty("User");
		PASSWORD = proprietÓ.getProperty("Password");
		IP = proprietÓ.getProperty("IP");
		PORT = proprietÓ.getProperty("Port");
		DB = proprietÓ.getProperty("DBname");
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
