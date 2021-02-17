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
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.Font;

public class HomeGUI extends JFrame {

	Controller theController;

	private JPanel contentPane;
	private String iconaFrame;
	private ImageIcon iconaGestioneProgetti;
	private JButton GestioneProgettibtn;
	private JButton GestioneDipendentibtn;
	private JButton GestioneMeetingbtn;
	private ImageIcon iconaGestioneDipendenti;
	private ImageIcon iconaGestioneMeeting;
	private ImageIcon immagineHome;
	
	/**
	 * Create the frame.
	 */
	public HomeGUI(Controller c) {
		setResizable(false);

		theController = c;

		iconaFrame = new String(
				"C:\\Users\\xtony\\eclipse-workspace\\Progetto[OODB2021] gruppo 15 traccia 2\\icon\\iconaFrame.png");
		setIconImage(Toolkit.getDefaultToolkit().getImage(iconaFrame));
		setTitle("Projects Management");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 717, 456);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		iconaGestioneProgetti = new ImageIcon(
				"C:\\Users\\xtony\\eclipse-workspace\\Progetto[OODB2021] gruppo 15 traccia 2\\icon\\iconaGestioneProgettibtn.png");
		GestioneProgettibtn = new JButton("Gestione Progetti");
		GestioneProgettibtn.setFont(new Font("Tahoma", Font.PLAIN, 10));
		GestioneProgettibtn.setIcon(iconaGestioneProgetti);
		GestioneProgettibtn.setBounds(0, 46, 189, 60);
		contentPane.add(GestioneProgettibtn);
		GestioneProgettibtn.setFocusable(false);

		iconaGestioneMeeting = new ImageIcon(
				"C:\\Users\\xtony\\eclipse-workspace\\Progetto[OODB2021] gruppo 15 traccia 2\\icon\\iconaGestioneMeetingbtn.png");
		GestioneMeetingbtn = new JButton("Gestione Meeting");
		GestioneMeetingbtn.setFont(new Font("Tahoma", Font.PLAIN, 10));
		GestioneMeetingbtn.setIcon(iconaGestioneMeeting);
		GestioneMeetingbtn.setBounds(0, 178, 189, 60);
		contentPane.add(GestioneMeetingbtn);
		GestioneMeetingbtn.setFocusable(false);

		iconaGestioneDipendenti = new ImageIcon(
				"C:\\Users\\xtony\\eclipse-workspace\\Progetto[OODB2021] gruppo 15 traccia 2\\icon\\iconaGestioneDipendentibtn.png");
		GestioneDipendentibtn = new JButton("Gestione Dipendenti");
		GestioneDipendentibtn.setFont(new Font("Tahoma", Font.PLAIN, 10));
		GestioneDipendentibtn.setIcon(iconaGestioneDipendenti);
		GestioneDipendentibtn.setBounds(0, 304, 189, 60);
		contentPane.add(GestioneDipendentibtn);
		GestioneDipendentibtn.setFocusable(false);

		immagineHome = new ImageIcon(
				"C:\\Users\\xtony\\eclipse-workspace\\Progetto[OODB2021] gruppo 15 traccia 2\\immaginiGUI\\immagineHome.png");
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
				theController.apriDipendentiGUI();
			}
		});
		
	}

}
