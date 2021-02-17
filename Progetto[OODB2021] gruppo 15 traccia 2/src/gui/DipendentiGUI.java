package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JProgressBar;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JMenu;
import java.awt.Button;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DipendentiGUI extends JFrame {

	Controller theController;

	private JPanel contentPane;
	private JButton tornaHomebtn;
	private JButton cercaDipendentebtn;
	private JButton eliminaDipendentebtn;
	private JButton aggiungiDipendentebtn;
	private String iconaFrame;
	private JPanel filtriPanel;
	private JButton btnNewButton;

	/**
	 * Create the frame.
	 */
	public DipendentiGUI(Controller c) {
		theController = c;
		
		iconaFrame =  new String(
				"C:\\Users\\xtony\\eclipse-workspace\\Progetto[OODB2021] gruppo 15 traccia 2\\icon\\iconaFrame.png");
		setIconImage(Toolkit.getDefaultToolkit().getImage(iconaFrame));
		setTitle("GestioneDipendenti");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 799, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tornaHomebtn = new JButton("Indietro");
		tornaHomebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.ChiudiDipendentiGui();
			}
		});
		tornaHomebtn.setBounds(0, 0, 82, 69);
		tornaHomebtn.setToolTipText("Torna Indietro");
		tornaHomebtn.setFocusable(false);
		contentPane.add(tornaHomebtn);
		
		
		eliminaDipendentebtn = new JButton("elimina");
		eliminaDipendentebtn.setBounds(252, 0, 89, 69);
		eliminaDipendentebtn.setToolTipText("Elimina Dipendente");
		eliminaDipendentebtn.setFocusable(false);
		contentPane.add(eliminaDipendentebtn);
		
		cercaDipendentebtn = new JButton("cerca");
		cercaDipendentebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				filtriPanel.setVisible(true);
			}
		});
		cercaDipendentebtn.setBounds(166, 0, 89, 69);
		cercaDipendentebtn.setToolTipText("Attiva Filtri");
		cercaDipendentebtn.setFocusable(false);
		contentPane.add(cercaDipendentebtn);
		
		aggiungiDipendentebtn = new JButton("aggiungi");
		aggiungiDipendentebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.ApriAggiungiDipendenteGui();
			}
		});
		aggiungiDipendentebtn.setBounds(79, 0, 89, 69);
		aggiungiDipendentebtn.setToolTipText("Aggiungi un nuovo Dipendente");
		aggiungiDipendentebtn.setFocusable(false);
		contentPane.add(aggiungiDipendentebtn);
		
		filtriPanel = new JPanel();
		filtriPanel.setVisible(false);
		filtriPanel.setBounds(25, 97, 370, 199);
		contentPane.add(filtriPanel);
		filtriPanel.setLayout(null);
		
		btnNewButton = new JButton("New button");
		btnNewButton.setFocusable(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				filtriPanel.setVisible(false);
			}
		});
		btnNewButton.setBounds(244, 29, 89, 23);
		filtriPanel.add(btnNewButton);
	}
}