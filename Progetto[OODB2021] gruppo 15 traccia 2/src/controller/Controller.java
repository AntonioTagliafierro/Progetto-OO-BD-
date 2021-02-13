package controller;

import gui.HomeGUI;
import dbConfig.DBConnection;
public class Controller {
	
	HomeGUI home;

	public static void main(String[] args) {
		
		Controller c = new Controller();
		Connection databaseConnection = DBConnection.getInstance().getConnection();

	}
	
	public Controller() {
		
		home = new HomeGUI(this);
		home.setVisible(true);
		
	}

}
