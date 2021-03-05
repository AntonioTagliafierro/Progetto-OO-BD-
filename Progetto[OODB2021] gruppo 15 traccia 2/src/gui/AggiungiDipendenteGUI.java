package gui;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import entity.Dipendente;
import entity.Residenza;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.UIManager;

public class AggiungiDipendenteGUI extends JFrame {

	private JPanel contentPane;

	Controller theController;

	private JButton fotobtn;
	private JLabel nomelbl;
	private JLabel cognomelbl;
	private JLabel codFlbl;
	private JLabel sessolbl;
	private JLabel dataNlbl;
	private JLabel emaillbl;
	private JLabel nCellularelbl;
	private JLabel cittàlbl;
	private JLabel caplbl;
	private JLabel provincialbl;
	private JLabel vialbl;
	private JLabel nCivicolbl;
	private JLabel salariolbl;
	private JTextField cognometf;
	private JTextField nometf;
	private JTextField codFtf;
	private JRadioButton Frbtn;
	private JRadioButton Mrbtn;
	private JRadioButton nonSpecificarerbtn;
	private JTextField emailtf;
	private JTextField cellularetf;
	private JTextField cittàtf;
	private JTextField captf;
	private JTextField viatf;
	private JTextField nCivicotf;
	private JTextField salariotf;
	private ButtonGroup sesso;
	private InputStream ips;
	private InputStreamReader ipsr;
	private BufferedReader br;
	private InputStream ips2;
	private InputStreamReader ipsr2;
	private BufferedReader br2;
	private String pathImage = "/fotoDipendenti/fotoprofilodefault2.jpg";
	private Date data = new Date();
	private int controllo = 0;
	private JButton eliminaFotobtn;
	private String[] foto = new String[3];
	private ImageIcon iconaIndietro;
	private ImageIcon iconaSalvataggio;
	private String fotoVecchia = "/fotoDipendenti/fotoprofilodefault2.jpg";
	private JComboBox<String> provinciacb;
	private JComboBox<String> prefissocb;
	private JDateChooser dataDiNascita;
	private JButton tornaIndietrobtn;
	private JButton salvabtn;

	/**
	 * Create the frame.
	 */
	public AggiungiDipendenteGUI(Controller c) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				int a = JOptionPane.showConfirmDialog(null, "Attenzione, se confermi perderai i dati inseriti",
						"Attenzione", JOptionPane.WARNING_MESSAGE);
				if (a == 0) {
					c.TornaHomeDaAggiungiDipendente();
				}

			}

		});
		foto[1] = "/fotoDipendenti/fotoprofilodefault2.jpg";
		theController = c;
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(AggiungiDipendenteGUI.class.getResource("/icon/iconaFrame.png")));
		setTitle("AggiungiDipendente");
		setResizable(false);

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 810, 491);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		nomelbl = new JLabel("Nome");
		nomelbl.setForeground(Color.LIGHT_GRAY);
		nomelbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nomelbl.setBounds(10, 11, 47, 25);
		contentPane.add(nomelbl);

		cognomelbl = new JLabel("Cognome");
		cognomelbl.setForeground(Color.LIGHT_GRAY);
		cognomelbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cognomelbl.setBounds(10, 33, 68, 25);
		contentPane.add(cognomelbl);

		codFlbl = new JLabel("Codice Fiscale");
		codFlbl.setForeground(Color.LIGHT_GRAY);
		codFlbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		codFlbl.setBounds(10, 64, 99, 14);
		contentPane.add(codFlbl);

		sessolbl = new JLabel("Sesso");
		sessolbl.setForeground(Color.LIGHT_GRAY);
		sessolbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sessolbl.setBounds(11, 89, 46, 14);
		contentPane.add(sessolbl);

		dataNlbl = new JLabel("Data Di Nascita");
		dataNlbl.setForeground(Color.LIGHT_GRAY);
		dataNlbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dataNlbl.setBounds(11, 114, 98, 14);
		contentPane.add(dataNlbl);

		emaillbl = new JLabel("e-mail");
		emaillbl.setForeground(Color.LIGHT_GRAY);
		emaillbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		emaillbl.setBounds(11, 139, 46, 14);
		contentPane.add(emaillbl);

		nCellularelbl = new JLabel("Numero Cellulare");
		nCellularelbl.setForeground(Color.LIGHT_GRAY);
		nCellularelbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nCellularelbl.setBounds(11, 164, 111, 14);
		contentPane.add(nCellularelbl);

		cittàlbl = new JLabel("Citt\u00E0");
		cittàlbl.setForeground(Color.LIGHT_GRAY);
		cittàlbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cittàlbl.setHorizontalAlignment(SwingConstants.LEFT);
		cittàlbl.setBounds(10, 189, 37, 14);
		contentPane.add(cittàlbl);

		caplbl = new JLabel("CAP");
		caplbl.setForeground(Color.LIGHT_GRAY);
		caplbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		caplbl.setBounds(10, 214, 30, 14);
		contentPane.add(caplbl);

		provincialbl = new JLabel("Provincia");
		provincialbl.setForeground(Color.LIGHT_GRAY);
		provincialbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		provincialbl.setHorizontalAlignment(SwingConstants.LEFT);
		provincialbl.setBounds(10, 239, 68, 14);
		contentPane.add(provincialbl);

		vialbl = new JLabel("Via");
		vialbl.setForeground(Color.LIGHT_GRAY);
		vialbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		vialbl.setBounds(10, 264, 30, 14);
		contentPane.add(vialbl);

		nCivicolbl = new JLabel("N\u00B0Civico");
		nCivicolbl.setForeground(Color.LIGHT_GRAY);
		nCivicolbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nCivicolbl.setBounds(10, 289, 68, 14);
		contentPane.add(nCivicolbl);

		salariolbl = new JLabel("Salario");
		salariolbl.setForeground(Color.LIGHT_GRAY);
		salariolbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		salariolbl.setBounds(10, 335, 46, 14);
		contentPane.add(salariolbl);

		fotobtn = new JButton("");
		fotobtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		fotobtn.setOpaque(false);
		fotobtn.setFocusable(false);
		fotobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fotoVecchia = pathImage;
				foto = c.SalvaFotoDipendente();
				fotobtn.setIcon(c.resize(new ImageIcon(foto[2]), 369, 385));
				pathImage = foto[1];

			}
		});
		fotobtn.setToolTipText("Inserisci Foto Dipendente");
		fotobtn.setBounds(421, 11, 369, 385);
		fotobtn.setIcon(c.resize(new ImageIcon(AggiungiDipendenteGUI.class.getResource(pathImage)), 369, 385));
		contentPane.add(fotobtn);

		iconaIndietro = new ImageIcon((AggiungiDipendenteGUI.class.getResource("/icon/iconaIndietrobtm.png")));
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
						c.TornaDipendentiGUIDaAggiungiDipendente();
					}
				} else {
					c.TornaDipendentiGUIDaAggiungiDipendente();
				}
			}

		});
		tornaIndietrobtn.setToolTipText("Torna Indietro");
		tornaIndietrobtn.setBounds(10, 407, 89, 44);
		contentPane.add(tornaIndietrobtn);

		nometf = new JTextField();
		nometf.setToolTipText("Inserisci Il Nome");
		nometf.setBorder(new LineBorder(Color.BLACK));
		nometf.setBounds(127, 15, 154, 20);
		contentPane.add(nometf);
		nometf.setColumns(10);

		cognometf = new JTextField();
		cognometf.setToolTipText("Inserisci Il Cognome");
		cognometf.setBorder(new LineBorder(Color.BLACK));
		cognometf.setBounds(127, 37, 154, 20);
		contentPane.add(cognometf);
		cognometf.setColumns(10);

		codFtf = new JTextField();
		codFtf.setToolTipText("Inserisci Il Codice Fiscale");
		codFtf.setBorder(new LineBorder(Color.BLACK));
		codFtf.setBounds(127, 63, 126, 20);
		contentPane.add(codFtf);
		codFtf.setColumns(10);

		Mrbtn = new JRadioButton("M");
		Mrbtn.setForeground(Color.LIGHT_GRAY);
		Mrbtn.setBackground(new Color(0, 0, 128));
		Mrbtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Mrbtn.setBounds(127, 85, 37, 23);
		contentPane.add(Mrbtn);

		Frbtn = new JRadioButton("F");
		Frbtn.setForeground(Color.LIGHT_GRAY);
		Frbtn.setBackground(new Color(0, 0, 128));
		Frbtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Frbtn.setBounds(166, 85, 35, 23);
		contentPane.add(Frbtn);

		nonSpecificarerbtn = new JRadioButton("Non Specificare");
		nonSpecificarerbtn.setForeground(Color.LIGHT_GRAY);
		nonSpecificarerbtn.setBackground(new Color(0, 0, 128));
		nonSpecificarerbtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nonSpecificarerbtn.setBounds(203, 85, 134, 23);
		contentPane.add(nonSpecificarerbtn);

		sesso = new ButtonGroup();
		sesso.add(Frbtn);
		sesso.add(Mrbtn);
		sesso.add(nonSpecificarerbtn);
		nonSpecificarerbtn.setSelected(true);

		emailtf = new JTextField();
		emailtf.setToolTipText("Inserisci L'Email");
		emailtf.setBorder(new LineBorder(Color.BLACK));
		emailtf.setBounds(127, 138, 284, 20);
		contentPane.add(emailtf);
		emailtf.setColumns(10);

		prefissocb = new JComboBox<String>();
		prefissocb.setToolTipText("Seleziona Il Prefisso Nazionale");
		prefissocb.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		prefissocb.setBounds(127, 162, 159, 22);
		contentPane.add(prefissocb);

		cellularetf = new JTextField();
		cellularetf.setToolTipText("Inserisci Il Numero Di Cellulare");
		cellularetf.setBorder(new LineBorder(Color.BLACK));
		cellularetf.setBounds(290, 163, 121, 20);
		contentPane.add(cellularetf);
		cellularetf.setColumns(10);

		cittàtf = new JTextField();
		cittàtf.setToolTipText("Inserisci La Citt\u00E0");
		cittàtf.setBorder(new LineBorder(Color.BLACK));
		cittàtf.setBounds(127, 188, 190, 20);
		contentPane.add(cittàtf);
		cittàtf.setColumns(10);

		captf = new JTextField();
		captf.setToolTipText("Inserisci CAP");
		captf.setBorder(new LineBorder(Color.BLACK));
		captf.setBounds(127, 213, 99, 20);
		contentPane.add(captf);
		captf.setColumns(10);

		provinciacb = new JComboBox<String>();
		provinciacb.setToolTipText("Seleziona Provincia");
		provinciacb.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		provinciacb.setBounds(127, 237, 57, 22);
		contentPane.add(provinciacb);

		viatf = new JTextField();
		viatf.setToolTipText("Inserisci Via");
		viatf.setBorder(new LineBorder(Color.BLACK));
		viatf.setBounds(127, 263, 190, 20);
		contentPane.add(viatf);
		viatf.setColumns(10);

		nCivicotf = new JTextField();
		nCivicotf.setToolTipText("Inserisci Numero Civico");
		nCivicotf.setBorder(new LineBorder(Color.BLACK));
		nCivicotf.setBounds(127, 288, 86, 20);
		contentPane.add(nCivicotf);
		nCivicotf.setColumns(10);

		salariotf = new JTextField();
		salariotf.setToolTipText("Inserisci Salario");
		salariotf.setBorder(new LineBorder(Color.BLACK));
		salariotf.setBounds(127, 334, 86, 20);
		contentPane.add(salariotf);
		salariotf.setColumns(10);

		dataDiNascita = new JDateChooser();
		dataDiNascita.setBounds(127, 114, 99, 20);
		contentPane.add(dataDiNascita);

		dataDiNascita.setDate(data);

		iconaSalvataggio = new ImageIcon((AggiungiDipendenteGUI.class.getResource("/icon/iconaSalvataggio.png")));
		salvabtn = new JButton("Salva");
		salvabtn.setHorizontalTextPosition(SwingConstants.LEFT);
		salvabtn.setBackground(UIManager.getColor("Button.background"));
		salvabtn.setForeground(Color.BLACK);
		salvabtn.setIcon(iconaSalvataggio);
		salvabtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		salvabtn.setFocusable(false);
		salvabtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (!c.ControllaCampiVuoti(nometf.getText(), cognometf.getText(), codFtf.getText(), emailtf.getText(),
						cellularetf.getText(), cittàtf.getText(), captf.getText(), viatf.getText(), nCivicotf.getText(),
						salariotf.getText())) {
					JOptionPane.showMessageDialog(null, "Riempi tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (c.ControlloDataPassata(dataDiNascita.getDate())) {
					JOptionPane.showMessageDialog(null, "Viene dal futuro?", "Errore", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (c.controlloExpRegCognome(cognometf.getText())) {

					cognometf.setBackground(Color.white);

				} else {

					cognometf.setBackground(Color.red);
					JOptionPane.showMessageDialog(null,
							"Il cognome non può avere numeri e deve avere massimo 20 caratteri", "Errore cognome",
							JOptionPane.ERROR_MESSAGE);
					controllo++;
				}
				if (c.controlloExpRegNome(nometf.getText())) {

					nometf.setBackground(Color.white);

				} else {

					nometf.setBackground(Color.red);
					JOptionPane.showMessageDialog(null,
							"Il nome non può avere numeri e deve avere massimo 20 caratteri", "Errore nome",
							JOptionPane.ERROR_MESSAGE);
					controllo++;

				}

				if (c.controlloExpRegCF(codFtf.getText())) {

					codFtf.setBackground(Color.white);

				} else {

					codFtf.setBackground(Color.red);
					JOptionPane.showMessageDialog(null, "Codice Fiscale non scritto nella forma corretta",
							"Errore Codice Fiscale", JOptionPane.ERROR_MESSAGE);
					controllo++;

				}
				if (c.controlloExpRegEmail(emailtf.getText())) {

					emailtf.setBackground(Color.white);
				} else {

					emailtf.setBackground(Color.red);
					JOptionPane.showMessageDialog(null, "e-mail non scritta nella forma corretta", "Errore e-mail",
							JOptionPane.ERROR_MESSAGE);
					controllo++;
				}
				if (c.controlloExpRegCellulare(cellularetf.getText())) {
					cellularetf.setBackground(Color.white);
				} else {

					cellularetf.setBackground(Color.red);
					JOptionPane.showMessageDialog(null,
							"Il numero di cellulare non può avere lettere o caratteri speciali e deve avere massimo 20 caratteri",
							"Errore cellulare", JOptionPane.ERROR_MESSAGE);
					controllo++;
				}
				if (c.controlloExpRegCittà(cittàtf.getText())) {
					cittàtf.setBackground(Color.white);
				} else {
					cittàtf.setBackground(Color.red);
					JOptionPane.showMessageDialog(null,
							"Il nome dellà città può contenere solo lettere e deve avere massimo 15 caratteri",
							"Errore città", JOptionPane.ERROR_MESSAGE);
					controllo++;
				}
				if (c.controlloExpRegCAP(captf.getText())) {
					captf.setBackground(Color.white);

				} else {
					captf.setBackground(Color.red);
					JOptionPane.showMessageDialog(null, "Il CAP deve essere composto da 5 numeri", "Errore CAP",
							JOptionPane.ERROR_MESSAGE);
					controllo++;

				}
				if (c.controlloExpRegNCivico(nCivicotf.getText())) {
					nCivicotf.setBackground(Color.white);

				} else {
					nCivicotf.setBackground(Color.red);
					JOptionPane.showMessageDialog(null, "Il numero civico deve avere massimo 6 caratteri",
							"Errore numero civico", JOptionPane.ERROR_MESSAGE);
					controllo++;
				}
				if (c.controlloExpRegVia(viatf.getText())) {
					viatf.setBackground(Color.white);

				} else {
					viatf.setBackground(Color.red);
					JOptionPane.showMessageDialog(null, "Il nome dellà via deve avere massimo 15 caratteri",
							"Errore via", JOptionPane.ERROR_MESSAGE);
					controllo++;
				}
				if (c.ControlloSalario(salariotf.getText())) {

					salariotf.setBackground(Color.white);
				} else {
					salariotf.setBackground(Color.red);
					JOptionPane.showMessageDialog(null,
							"Il salario non può avere lettere e deve avere 2 numeri decimali - Usa il punto come separatore decimale",
							"Errore salario", JOptionPane.ERROR_MESSAGE);
					controllo++;
				}

				if (controllo == 0) {

					int opzione = JOptionPane.showOptionDialog(null,
							"Vuoi salvare " + nometf.getText() + " " + cognometf.getText() + " come dipendente?",
							"Salvataggio", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
							new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);
					if (opzione == JOptionPane.YES_OPTION) {
						if (!foto[1].equals(fotoVecchia)) {
							c.SalvaNuovaFoto(foto);
						}
						String prefissoDaDividere = prefissocb.getSelectedItem().toString();
						String[] prefisso = prefissoDaDividere.split(" ");
						String nazione = prefisso[0];
						String prefissoDaSalvare = prefisso[1];
						Residenza residenza = new Residenza();
						residenza.setCittà(cittàtf.getText());
						residenza.setProvincia(provinciacb.getSelectedItem().toString());
						residenza.setCap(captf.getText());
						residenza.setVia(viatf.getText());
						residenza.setnCivico(nCivicotf.getText());

						Dipendente dipendente = new Dipendente();
						dipendente.setCodiceFiscale(codFtf.getText());
						dipendente.setCognome(cognometf.getText());
						dipendente.setNome(nometf.getText());
						dipendente.setEmail(emailtf.getText());
						dipendente.setSalarioMedio(Double.parseDouble(salariotf.getText()));
						dipendente.setDataNascita(dataDiNascita.getDate());
						if (Mrbtn.isSelected()) {
							dipendente.setSesso("M");
						} else if (Frbtn.isSelected()) {
							dipendente.setSesso("F");
						} else {
							dipendente.setSesso("Non Specificato");
						}
						dipendente.setPathFoto(pathImage);
						dipendente.setResidenza(residenza);
						dipendente.setnCellulare(prefissoDaSalvare + cellularetf.getText());

						try {
							c.SalvaDipendente(dipendente);
							int risposta = JOptionPane.showOptionDialog(null,
									"Dipendente salvato! Vuoi aggiungere un altro dipendente?", "Salvato",
									JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
									new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);
							if (risposta == JOptionPane.YES_OPTION) {

								nometf.setText("");
								cognometf.setText("");
								codFtf.setText("");
								emailtf.setText("");
								cittàtf.setText("");
								viatf.setText("");
								captf.setText("");
								salariotf.setText("");
								nCivicotf.setText("");
								cellularetf.setText("");
								dataDiNascita.setDate(data);
								fotobtn.setIcon(
										c.resize(
												new ImageIcon(AggiungiDipendenteGUI.class
														.getResource("/fotoDipendenti/fotoprofilodefault2.jpg")),
												369, 385));
								pathImage = "/fotoDipendenti/fotoprofilodefault2.jpg";

							} else if (risposta == JOptionPane.NO_OPTION) {
								c.TornaDipendentiGUIDaAggiungiDipendente();
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
		salvabtn.setToolTipText("Salva Dipendente");
		salvabtn.setBounds(701, 407, 89, 44);
		contentPane.add(salvabtn);

		eliminaFotobtn = new JButton("Elimina Foto");
		eliminaFotobtn.setToolTipText("Rimuovi Foto");
		eliminaFotobtn.setFocusable(false);
		eliminaFotobtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		eliminaFotobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pathImage = "/fotoDipendenti/fotoprofilodefault2.jpg";
				fotobtn.setIcon(c.resize(new ImageIcon(AggiungiDipendenteGUI.class.getResource(pathImage)), 369, 385));
				;
			}
		});
		eliminaFotobtn.setBounds(292, 373, 111, 23);
		contentPane.add(eliminaFotobtn);

		// caricamento combobox province

		try {
			ips = new FileInputStream("Province.txt");
			ipsr = new InputStreamReader(ips);
			br = new BufferedReader(ipsr);
			String provincia;
			while ((provincia = br.readLine()) != null) {
				provinciacb.addItem(provincia);
			}
			br.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Errore caricamento file province", "Errore",
					JOptionPane.ERROR_MESSAGE);
		}

		// caricamento prefissi
		try {
			ips2 = new FileInputStream("Prefissi.txt");
			ipsr2 = new InputStreamReader(ips2);
			br2 = new BufferedReader(ipsr2);
			String provincia;
			while ((provincia = br2.readLine()) != null) {
				prefissocb.addItem(provincia);
			}
			br2.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Errore caricamento file province", "Errore",
					JOptionPane.ERROR_MESSAGE);
		}

	}
}
