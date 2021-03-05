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
import entity.PiattaformaMeeting;
import entity.SalaMeeting;

public class ModificaPiattaformaGUI extends JFrame {

	private JPanel contentPane;
	private Controller theController;
	private JLabel nomePiattaformalbl;
	private JLabel nDispositivilbl;
	private JButton tornaIndietrobtn;
	private ImageIcon iconaIndietro;
	private JTextField nomePiattaformatf;
	private JTextField nDispositivitf;
	private ImageIcon iconaSalvataggio;
	private JButton salvabtn;
	private PiattaformaMeeting piattaforma;
	private JButton modificabtn;
	private String nomePiattaformaDaModificare;
	private ImageIcon iconaModifica;

	/**
	 * Create the frame.
	 */
	public ModificaPiattaformaGUI(Controller c, PiattaformaMeeting piattaformaDaModificare) {
		this.piattaforma = piattaformaDaModificare;
		nomePiattaformaDaModificare = piattaformaDaModificare.getNomePiattaforma();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (salvabtn.isVisible()) {
					int a = JOptionPane.showConfirmDialog(null, "Attenzione, se confermi perderai i dati inseriti",
							"Attenzione", JOptionPane.WARNING_MESSAGE);
					if (a == 0) {
						c.TornaHomeGUIDaModificaSala();
						;
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
		setBounds(100, 100, 380, 193);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		nomePiattaformalbl = new JLabel("Nome Piattaforma");
		nomePiattaformalbl.setForeground(Color.LIGHT_GRAY);
		nomePiattaformalbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nomePiattaformalbl.setBounds(10, 11, 123, 25);
		contentPane.add(nomePiattaformalbl);

		nDispositivilbl = new JLabel("Numero Dispositivi Piattaforma");
		nDispositivilbl.setForeground(Color.LIGHT_GRAY);
		nDispositivilbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nDispositivilbl.setBounds(10, 47, 194, 14);
		contentPane.add(nDispositivilbl);

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

		nomePiattaformatf = new JTextField();
		nomePiattaformatf.setToolTipText("Inserisci Nome Della Piattaforma");
		nomePiattaformatf.setBorder(new LineBorder(Color.BLACK));
		nomePiattaformatf.setBounds(215, 15, 133, 20);
		contentPane.add(nomePiattaformatf);
		nomePiattaformatf.setColumns(10);
		nomePiattaformatf.setText(piattaforma.getNomePiattaforma());
		nomePiattaformatf.setEditable(false);

		nDispositivitf = new JTextField();
		nDispositivitf.setToolTipText("Inserisci Numero Max Dispositivi Collegabili Alla Piattaforma");
		nDispositivitf.setBorder(new LineBorder(Color.BLACK));
		nDispositivitf.setBounds(214, 46, 134, 20);
		contentPane.add(nDispositivitf);
		nDispositivitf.setColumns(10);
		nDispositivitf.setText(piattaforma.getLimitePartecipanti());
		nDispositivitf.setEditable(false);

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
					JOptionPane.showMessageDialog(null, "Deve essere un numero intero", "Errore Numero Dispositivi",
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
							PiattaformaMeeting piattaformaModificata = new PiattaformaMeeting();
							piattaformaModificata.setNomePiattaforma(nomePiattaformatf.getText());
							piattaformaModificata.setLimitePartecipanti(nDispositivitf.getText());
							c.ModificaPiattaforma(piattaformaModificata, nomePiattaformaDaModificare);
							int risposta = JOptionPane.showOptionDialog(null, "Piattaforma salvata! Vuoi fare altro?",
									"Salvato", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
									new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);
							if (risposta == JOptionPane.YES_OPTION) {

								nomePiattaformatf.setText(piattaformaModificata.getNomePiattaforma());
								nDispositivitf.setText(piattaformaModificata.getLimitePartecipanti());
								nomePiattaformatf.setEditable(false);
								nDispositivitf.setEditable(false);
								salvabtn.setVisible(false);
								modificabtn.setVisible(true);

							} else if (risposta == JOptionPane.NO_OPTION) {
								c.TornaGestioneSaleGUIDaModificaPiattaforma();
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
		salvabtn.setBounds(259, 99, 89, 44);
		contentPane.add(salvabtn);

		iconaModifica = new ImageIcon((ModificaSalaGUI.class.getResource("/icon/iconaModifica.png")));
		modificabtn = new JButton("Modifica");
		modificabtn.setIcon(iconaModifica);
		modificabtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nomePiattaformatf.setEditable(true);
				nDispositivitf.setEditable(true);
				salvabtn.setVisible(true);
				modificabtn.setVisible(false);
			}
		});
		modificabtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		modificabtn.setToolTipText("Modifica Sala");
		modificabtn.setBounds(133, 99, 89, 44);
		contentPane.add(modificabtn);

	}
}
