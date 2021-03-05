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
import javax.swing.JComponent;
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
import entity.PiattaformaMeeting;

public class AggiungiPiattaformaGUI extends JFrame {

	private JPanel contentPane;

	private Controller theController;

	private PiattaformaMeeting piattaforma;

	private JLabel nomePiattaformalbl;

	private JComponent nDispositivilbl;

	private ImageIcon iconaIndietro;

	private JButton tornaIndietrobtn;

	private JTextField nDispositivitf;

	private ImageIcon iconaSalvataggio;

	private JButton salvabtn;

	private JTextField nomePiattaformatf;

	/**
	 * Create the frame.
	 */
	public AggiungiPiattaformaGUI(Controller c) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				int a = JOptionPane.showConfirmDialog(null, "Attenzione, se confermi perderai i dati inseriti",
						"Attenzione", JOptionPane.WARNING_MESSAGE);
				if (a == 0) {
					c.TornaHomeGUIDaAggiungiPiattaforma();
				}

			}
		});
		theController = c;
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(AggiungiPiattaformaGUI.class.getResource("/icon/iconaFrame.png")));
		setTitle("AggiungiPiattaforma");
		setResizable(false);

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 381, 193);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		nomePiattaformalbl = new JLabel("Nome Piattaforma");
		nomePiattaformalbl.setForeground(Color.LIGHT_GRAY);
		nomePiattaformalbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nomePiattaformalbl.setBounds(10, 11, 133, 25);
		contentPane.add(nomePiattaformalbl);

		nDispositivilbl = new JLabel("Numero Dispositvi Piattaforma");
		nDispositivilbl.setForeground(Color.LIGHT_GRAY);
		nDispositivilbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nDispositivilbl.setBounds(10, 47, 194, 14);
		contentPane.add(nDispositivilbl);

		iconaIndietro = new ImageIcon((AggiungiPiattaformaGUI.class.getResource("/icon/iconaIndietrobtm.png")));
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
						c.TornaGestionePiattaformeGUIDaAggiungiPiattaforma();
					}
				} else {
					c.TornaGestionePiattaformeGUIDaAggiungiPiattaforma();
				}

			}
		});
		tornaIndietrobtn.setToolTipText("Torna Indietro");
		tornaIndietrobtn.setBounds(10, 99, 89, 44);
		contentPane.add(tornaIndietrobtn);

		nomePiattaformatf = new JTextField();
		nomePiattaformatf.setToolTipText("Inserisci Nome Della Piattaforma");
		nomePiattaformatf.setBorder(new LineBorder(Color.BLACK));
		nomePiattaformatf.setBounds(219, 15, 133, 20);
		contentPane.add(nomePiattaformatf);
		nomePiattaformatf.setColumns(10);

		nDispositivitf = new JTextField();
		nDispositivitf.setToolTipText("Inserisci Numero Massimo Dispositivi Collegabili Della Piattaforma");
		nDispositivitf.setBorder(new LineBorder(Color.BLACK));
		nDispositivitf.setBounds(218, 46, 134, 20);
		contentPane.add(nDispositivitf);
		nDispositivitf.setColumns(10);

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

				if (c.ControlloStringaVuota(nomePiattaformatf.getText())
						|| c.ControlloStringaVuota(nDispositivitf.getText())) {
					JOptionPane.showMessageDialog(null, "Riempi tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
					return;
				}

				int controllo = 0;
				if (nomePiattaformatf.getText().length() < 20) {

					nomePiattaformatf.setBackground(Color.white);

				} else {

					nomePiattaformatf.setBackground(Color.red);
					JOptionPane.showMessageDialog(null, "Il nome può avere massimo 20 caratteri", "Errore nome",
							JOptionPane.ERROR_MESSAGE);
					controllo++;

				}

				if (c.controlloExpRegnPosti(nDispositivitf.getText())) {

					nDispositivitf.setBackground(Color.white);

				} else {

					nDispositivitf.setBackground(Color.red);
					JOptionPane.showMessageDialog(null, "Deve essere un numero intero", "Errore Numero Posti",
							JOptionPane.ERROR_MESSAGE);
					controllo++;

				}

				if (controllo == 0) {

					int opzione = JOptionPane.showOptionDialog(null,
							"Vuoi salvare la piattaforma: " + nomePiattaformatf.getText() + " ?", "Salvataggio",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Yes", "No" },
							JOptionPane.YES_OPTION);
					if (opzione == JOptionPane.YES_OPTION) {

						try {
							PiattaformaMeeting nuovaPiattaforma = new PiattaformaMeeting();
							nuovaPiattaforma.setNomePiattaforma(nomePiattaformatf.getText());
							nuovaPiattaforma.setLimitePartecipanti(nDispositivitf.getText());
							c.SalvaPiattaforma(nuovaPiattaforma);
							int risposta = JOptionPane.showOptionDialog(null,
									"Piattaforma salvata! Vuoi aggiungere un'altra sala?", "Salvato",
									JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
									new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);
							if (risposta == JOptionPane.YES_OPTION) {

								nomePiattaformatf.setText("");
								nDispositivitf.setText("");

							} else if (risposta == JOptionPane.NO_OPTION) {
								c.TornaGestionePiattaformeGUIDaAggiungiPiattaforma();
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
		salvabtn.setToolTipText("Salva Piattaforma");
		salvabtn.setBounds(263, 99, 89, 44);
		contentPane.add(salvabtn);

	}

}
