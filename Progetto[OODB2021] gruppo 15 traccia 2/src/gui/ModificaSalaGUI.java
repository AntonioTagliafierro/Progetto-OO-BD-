package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.Controller;
import entity.SalaMeeting;

public class ModificaSalaGUI extends JFrame {

	private JPanel contentPane;
	private Controller theController;
	private JLabel nomeSalalbl;
	private JLabel nPostilbl;
	private JButton tornaIndietrobtn;
	private ImageIcon iconaIndietro;
	private JTextField nomeSalatf;
	private JTextField nPostitf;
	private ImageIcon iconaSalvataggio;
	private JButton salvabtn;
	private SalaMeeting sala;
	private JButton modificabtn;
	private String nomeSalaDaModificare;
	private ImageIcon iconaModifica;

	/**
	 * Create the frame.
	 */
	public ModificaSalaGUI(Controller c, SalaMeeting salaDaModificare) {
		this.sala = salaDaModificare;
		nomeSalaDaModificare = salaDaModificare.getNomeSala();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (salvabtn.isVisible()) {
					int a = JOptionPane.showConfirmDialog(null, "Attenzione, se confermi perderai i dati inseriti",
							"Attenzione", JOptionPane.WARNING_MESSAGE);
					if (a == 0) {
						c.TornaHomeGUIDaModificaSala();
					}
				} else {
					c.TornaHomeGUIDaModificaSala();
				}
			}

		});
		theController = c;

		setIconImage(Toolkit.getDefaultToolkit().getImage(ModificaSalaGUI.class.getResource("/icon/iconaFrame.png")));
		setTitle("AggiungiSala");
		setResizable(false);

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 337, 193);
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

		iconaIndietro = new ImageIcon((ModificaSalaGUI.class.getResource("/icon/iconaIndietrobtm.png")));
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
						c.TornaGestioneSaleGUIDaModificaSala();
					}
				} else {
					c.TornaGestioneSaleGUIDaModificaSala();
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
		nomeSalatf.setText(sala.getNomeSala());
		nomeSalatf.setEditable(false);

		nPostitf = new JTextField();
		nPostitf.setToolTipText("Inserisci Numero Posti Della Sala");
		nPostitf.setBorder(new LineBorder(Color.BLACK));
		nPostitf.setBounds(174, 46, 134, 20);
		contentPane.add(nPostitf);
		nPostitf.setColumns(10);
		nPostitf.setText(sala.getnPosti());
		nPostitf.setEditable(false);

		iconaSalvataggio = new ImageIcon((ModificaSalaGUI.class.getResource("/icon/iconaSalvataggio.png")));
		salvabtn = new JButton("Salva");
		salvabtn.setHorizontalTextPosition(SwingConstants.LEFT);
		salvabtn.setBackground(UIManager.getColor("Button.background"));
		salvabtn.setForeground(Color.BLACK);
		salvabtn.setIcon(iconaSalvataggio);
		salvabtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		salvabtn.setFocusable(false);
		salvabtn.setVisible(false);
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
							SalaMeeting salaModificata = new SalaMeeting();
							salaModificata.setNomeSala(nomeSalatf.getText());
							salaModificata.setnPosti(nPostitf.getText());
							c.ModificaSala(salaModificata, nomeSalaDaModificare);
							int risposta = JOptionPane.showOptionDialog(null, "Sala salvata! Vuoi fare altro?",
									"Salvato", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
									new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);
							if (risposta == JOptionPane.YES_OPTION) {

								nomeSalatf.setText(salaModificata.getNomeSala());
								nPostitf.setText(salaModificata.getnPosti());
								nomeSalatf.setEditable(false);
								nPostitf.setEditable(false);
								salvabtn.setVisible(false);
								modificabtn.setVisible(true);

							} else if (risposta == JOptionPane.NO_OPTION) {
								c.TornaGestioneSaleGUIDaModificaSala();
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

		iconaModifica = new ImageIcon((ModificaSalaGUI.class.getResource("/icon/iconaModifica.png")));
		modificabtn = new JButton("Modifica");
		modificabtn.setIcon(iconaModifica);
		modificabtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nomeSalatf.setEditable(true);
				nPostitf.setEditable(true);
				salvabtn.setVisible(true);
				modificabtn.setVisible(false);
			}
		});
		modificabtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		modificabtn.setToolTipText("Modifica Sala");
		modificabtn.setBounds(115, 99, 89, 44);
		contentPane.add(modificabtn);

	}
}