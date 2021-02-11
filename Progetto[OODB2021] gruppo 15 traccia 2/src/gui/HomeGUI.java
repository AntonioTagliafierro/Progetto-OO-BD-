package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.Toolkit;

public class HomeGUI extends JFrame {

	Controller theController;

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public HomeGUI(Controller c) {
		String iconaFrame = new String("C:\\Users\\xtony\\git\\Progetto-OO-BD-\\Progetto[OODB2021] gruppo 15 traccia 2\\icon\\iconaFrame.png");
		setIconImage(Toolkit.getDefaultToolkit().getImage(iconaFrame));
		setTitle("Projects Management");
		theController = c;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 717, 456);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ImageIcon iconaGestioneProgetti = new ImageIcon(
				"C:\\Users\\xtony\\git\\Progetto-OO-BD-\\Progetto[OODB2021] gruppo 15 traccia 2\\icon\\iconaGestioneProgettibtn.png");
		JButton GestioneProgettibtn = new JButton("Gestione Progetti");
		GestioneProgettibtn.setIcon(iconaGestioneProgetti);
		GestioneProgettibtn.setBounds(0, 46, 189, 60);
		contentPane.add(GestioneProgettibtn);
		GestioneProgettibtn.setFocusable(false);

		ImageIcon iconaGestioneMeeting = new ImageIcon(
				"C:\\Users\\xtony\\git\\Progetto-OO-BD-\\Progetto[OODB2021] gruppo 15 traccia 2\\icon\\iconaGestioneMeetingbtn.png");
		JButton GestioneMeetingbtn = new JButton("Gestione Meeting");
		GestioneMeetingbtn.setIcon(iconaGestioneMeeting);
		GestioneMeetingbtn.setBounds(0, 178, 189, 60);
		contentPane.add(GestioneMeetingbtn);
		GestioneMeetingbtn.setFocusable(false);

		ImageIcon iconaGestioneDipendenti = new ImageIcon(
				"C:\\Users\\xtony\\git\\Progetto-OO-BD-\\Progetto[OODB2021] gruppo 15 traccia 2\\icon\\iconaGestioneDipendentibtn.png");
		JButton GestioneDipendentibtn = new JButton("Gestione Dipendenti");
		GestioneDipendentibtn.setIcon(iconaGestioneDipendenti);
		GestioneDipendentibtn.setBounds(0, 304, 189, 60);
		contentPane.add(GestioneDipendentibtn);
		GestioneDipendentibtn.setFocusable(false);

		ImageIcon immagineHome = new ImageIcon("C:\\Users\\xtony\\git\\Progetto-OO-BD-\\Progetto[OODB2021] gruppo 15 traccia 2\\immaginiGUI\\immagineHome.png");
		JLabel Homelbl = new JLabel("");
		Homelbl.setIcon(immagineHome);
		Homelbl.setBounds(191, 0, 510, 388);
		contentPane.add(Homelbl);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(191, 0, 510, 417);
		contentPane.add(panel);

		GestioneDipendentibtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
	}
}
