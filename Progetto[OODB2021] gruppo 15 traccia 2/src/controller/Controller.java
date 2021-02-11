package controller;

import gui.HomeGUI;

public class Controller {
	
	HomeGUI home;

	public static void main(String[] args) {
		
		Controller c = new Controller();
		

	}
	
	public Controller() {
		
		home = new HomeGUI(this);
		home.setVisible(true);
		
	}

}
