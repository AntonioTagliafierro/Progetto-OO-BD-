package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import controller.Controller;
import entity.Dipendente;
import entity.Residenza;
import entity.SalaMeeting;

public class AggiungiSalaGUI extends JFrame {

	private JPanel contentPane;

	private Controller theController;

	private SalaMeeting sala;

	private JLabel nomeSalalbl;

	private JComponent nPostilbl;

	private ImageIcon iconaIndietro;

	private JButton tornaIndietrobtn;

	private JTextField nPostitf;

	private ImageIcon iconaSalvataggio;

	private JButton salvabtn;

	private JTextField nomeSalatf;

	/**
	 * Create the frame.
	 */
	public AggiungiSalaGUI(Controller c) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				int a = JOptionPane.showConfirmDialog(null, "Attenzione, se confermi perderai i dati inseriti",
						"Attenzione", JOptionPane.WARNING_MESSAGE);
				if (a == 0) {
					c.TornaHomeGUIDaAggiungiSala();
				}

			}

		});
		theController = c;
		setIconImage(Toolkit.getDefaultToolkit().getImage(AggiungiSalaGUI.class.getResource("/icon/iconaFrame.png")));
		setTitle("AggiungiSala");
		setResizable(false);

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 336, 193);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		nomeSalalbl = new JLabel("Nome Sala");
		nomeSalalbl.setForeground(Color.LIGHT_GRAY);
		nomeSalalbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nomeSalalbl.setBounds(10, 11, 89, 25);
		contentPane.add(nomeSalalbl);

		nPostilbl = new JLabel("Numero Posti Della Sala");
		nPostilbl.setForeground(Color.LIGHT_GRAY);
		nPostilbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nPostilbl.setBounds(10, 47, 154, 14);
		contentPane.add(nPostilbl);

		iconaIndietro = new ImageIcon((AggiungiSalaGUI.class.getResource("/icon/iconaIndietrobtm.png")));
		tornaIndietrobtn = new JButton("Indietro");
		tornaIndietrobtn.setBackground(UIManager.getColor("Button.background"));
		tornaIndietrobtn.setIcon(iconaIndietro);
		tornaIndietrobtn.setBorder(new LineBorder(Color.BLACK, 1, true));
		tornaIndietrobtn.setFocusable(false);
		tornaIndietrobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (salvabtn.isVisible()) {
					int a = JOptionPane.showConfirmDialog(null, "Attenzione, se confermi perderai i dati inseriti",
							"Attenzione", JOptionPane.WARNING_MESSAGE);
					if (a == 0) {
						c.TornaGestioneSaleGUIDaAggiungiSala();
					}
				} else {
					c.TornaGestioneSaleGUIDaAggiungiSala();
				}
			}

		});
		tornaIndietrobtn.setToolTipText("Torna Indietro");
		tornaIndietrobtn.setBounds(10, 99, 89, 44);
		contentPane.add(tornaIndietrobtn);

		nomeSalatf = new JTextField();
		nomeSalatf.setToolTipText("Inserisci Nome Della Sala");
		nomeSalatf.setBorder(new LineBorder(Color.BLACK));
		nomeSalatf.setBounds(175, 15, 133, 20);
		contentPane.add(nomeSalatf);
		nomeSalatf.setColumns(10);

		nPostitf = new JTextField();
		nPostitf.setToolTipText("Inserisci Numero Posti Della Sala");
		nPostitf.setBorder(new LineBorder(Color.BLACK));
		nPostitf.setBounds(174, 46, 134, 20);
		contentPane.add(nPostitf);
		nPostitf.setColumns(10);

		iconaSalvataggio = new ImageIcon((AggiungiSalaGUI.class.getResource("/icon/iconaSalvataggio.png")));
		salvabtn = new JButton("Salva");
		salvabtn.setHorizontalTextPosition(SwingConstants.LEFT);
		salvabtn.setBackground(UIManager.getColor("Button.background"));
		salvabtn.setForeground(Color.BLACK);
		salvabtn.setIcon(iconaSalvataggio);
		salvabtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		salvabtn.setFocusable(false);
		salvabtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (c.ControlloStringaVuota(nomeSalatf.getText()) || c.ControlloStringaVuota(nPostitf.getText())) {
					JOptionPane.showMessageDialog(null, "Riempi tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
					return;
				}

				int controllo = 0;
				if (nomeSalatf.getText().length() < 20) {

					nomeSalatf.setBackground(Color.white);

				} else {

					nomeSalatf.setBackground(Color.red);
					JOptionPane.showMessageDialog(null, "Il nome può avere massimo 20 caratteri", "Errore nome",
							JOptionPane.ERROR_MESSAGE);
					controllo++;

				}

				if (c.controlloExpRegnPosti(nPostitf.getText())) {

					nPostitf.setBackground(Color.white);

				} else {

					nPostitf.setBackground(Color.red);
					JOptionPane.showMessageDialog(null, "Deve essere un numero intero", "Errore Numero Posti",
							JOptionPane.ERROR_MESSAGE);
					controllo++;

				}

				if (controllo == 0) {

					int opzione = JOptionPane.showOptionDialog(null,
							"Vuoi salvare la sala: " + nomeSalatf.getText() + " ?", "Salvataggio",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Yes", "No" },
							JOptionPane.YES_OPTION);
					if (opzione == JOptionPane.YES_OPTION) {

						try {
							SalaMeeting nuovaSala = new SalaMeeting();
							nuovaSala.setNomeSala(nomeSalatf.getText());
							nuovaSala.setnPosti(nPostitf.getText());
							c.SalvaSala(nuovaSala);
							int risposta = JOptionPane.showOptionDialog(null,
									"Sala salvata! Vuoi aggiungere un'altra sala?", "Salvato",
									JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
									new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);
							if (risposta == JOptionPane.YES_OPTION) {

								nomeSalatf.setText("");
								nPostitf.setText("");

							} else if (risposta == JOptionPane.NO_OPTION) {
								c.TornaGestioneSaleGUIDaAggiungiSala();
							}
						} catch (SQLException e) {

							JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						return;
					}
				} else {
					controllo = 0;
					return;
				}

			}
		});
		salvabtn.setToolTipText("Salva Sala");
		salvabtn.setBounds(219, 99, 89, 44);
		contentPane.add(salvabtn);

	}

}
