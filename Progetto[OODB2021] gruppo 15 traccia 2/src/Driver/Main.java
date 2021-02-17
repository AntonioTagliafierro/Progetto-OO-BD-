package Driver;

import java.sql.Connection;
import java.sql.SQLException;

import controller.Controller;
import dbConfig.DBConnection;

public class Main {

	public static void main(String[] args) {
		DBConnection dbconn = null;
		Connection connection = null;
		try {
			dbconn = DBConnection.getInstance();
			connection = dbconn.getConnection();

			Controller c = new Controller(connection);

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

}
