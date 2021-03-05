package gui;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import controller.Controller;
import entity.Dipendente;
import entity.MeetingFisico;
import entity.MeetingTelematico;
import entity.ProgettiDelDipendente;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class VisualizzaModificaDipendenteGUI extends JFrame {

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
	private String pathImage;
	private Date data = new Date();
	private int controllo = 0;
	private JButton fotoPrecedentebtn;
	private String[] foto = new String[3];
	private String vecchioCF;

	private String fotoVecchia;
	private JComboBox<String> provinciacb;
	private JComboBox<String> prefissocb;
	private JDateChooser dataDiNascita;
	private Dipendente dipendente;
	private JButton modificabtn;
	private JLabel valutazionelbl;
	private JButton rimuoviFotobtn;
	private JButton tornaIndietrobtn;
	private JTable progettiTable;
	private JTable meetingTable;
	private DefaultTableModel model;

	private TableColumnModel colonne;

	private TableModel righe;
	private JScrollPane progettiscrollPane;
	private JScrollPane meetingscrollPane;
	private JLabel mostraStatuslbl;
	private JLabel statuslbl;
	private JButton disattivabtn;
	private JButton attivabtn;
	private ImageIcon iconaIndietro;
	private ImageIcon iconaSalvataggio;
	private ImageIcon iconaModificabtn;
	private JButton salvabtn;
	private JLabel valoreValutazionelbl;

	/**
	 * Create the frame.
	 */
	public VisualizzaModificaDipendenteGUI(Controller c, Dipendente dipendenteDaVisualizzare) {
		setForeground(Color.LIGHT_GRAY);
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (salvabtn.isVisible()) {
					int a = JOptionPane.showConfirmDialog(null, "Attenzione, se confermi perderai i dati inseriti",
							"Attenzione", JOptionPane.WARNING_MESSAGE);
					if (a == 0) {
						c.TornaHomeDaVisualizzaModificaDipendente();
					}
				} else {
					c.TornaHomeDaVisualizzaModificaDipendente();
				}

			}
		});
		this.dipendente = dipendenteDaVisualizzare;
		foto[1] = dipendente.getPathFoto();
		pathImage = dipendente.getPathFoto();
		fotoVecchia = dipendente.getPathFoto();
		theController = c;
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VisualizzaModificaDipendenteGUI.class.getResource("/icon/iconaFrame.png")));
		setTitle("VisualizzaModificaDipendente");

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1051, 670);
		contentPane = new JPanel();
		contentPane.setForeground(Color.LIGHT_GRAY);
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
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
		salariolbl.setBounds(10, 314, 46, 14);
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
		fotobtn.setToolTipText("Cambia Foto Dipendente");
		fotobtn.setBounds(656, 11, 369, 385);
		fotobtn.setIcon(
				c.resize(new ImageIcon(VisualizzaModificaDipendenteGUI.class.getResource(pathImage)), 369, 385));
		contentPane.add(fotobtn);

		iconaIndietro = new ImageIcon(
				(VisualizzaModificaDipendenteGUI.class.getResource("/icon/iconaIndietrobtm.png")));
		tornaIndietrobtn = new JButton("Indietro");
		tornaIndietrobtn.setIcon(iconaIndietro);
		tornaIndietrobtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		tornaIndietrobtn.setFocusable(false);
		tornaIndietrobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (salvabtn.isVisible()) {
					int a = JOptionPane.showConfirmDialog(null, "Attenzione, se confermi perderai i dati inseriti",
							"Attenzione", JOptionPane.WARNING_MESSAGE);
					if (a == 0) {
						c.TornaDipendentiGUIDaVisualizzaModificaDipendente();
					}
				} else {
					c.TornaDipendentiGUIDaVisualizzaModificaDipendente();
				}
			}

		});
		tornaIndietrobtn.setToolTipText("Torna Indietro");
		tornaIndietrobtn.setBounds(10, 581, 89, 44);
		contentPane.add(tornaIndietrobtn);

		nometf = new JTextField();
		nometf.setToolTipText("Cambia Nome Dipendente");
		nometf.setBorder(new LineBorder(Color.BLACK));
		nometf.setBounds(127, 15, 154, 20);
		contentPane.add(nometf);
		nometf.setColumns(10);
		nometf.setText(dipendente.getNome());
		nometf.setEditable(false);

		cognometf = new JTextField();
		cognometf.setToolTipText("Cambia Cognome Dipendente");
		cognometf.setBorder(new LineBorder(Color.BLACK));
		cognometf.setBounds(127, 37, 154, 20);
		contentPane.add(cognometf);
		cognometf.setColumns(10);
		cognometf.setText(dipendente.getCognome());
		cognometf.setEditable(false);

		codFtf = new JTextField();
		codFtf.setToolTipText("Cambia Codice Fiscale Dipendente");
		codFtf.setBorder(new LineBorder(Color.BLACK));
		codFtf.setBounds(127, 63, 154, 20);
		contentPane.add(codFtf);
		codFtf.setColumns(10);
		codFtf.setText(dipendente.getCodiceFiscale());
		codFtf.setEditable(false);
		vecchioCF = codFtf.getText();

		Mrbtn = new JRadioButton("M");
		Mrbtn.setBackground(new Color(0, 0, 128));
		Mrbtn.setForeground(Color.LIGHT_GRAY);
		Mrbtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Mrbtn.setBounds(127, 85, 37, 23);
		contentPane.add(Mrbtn);

		Frbtn = new JRadioButton("F");
		Frbtn.setBackground(new Color(0, 0, 128));
		Frbtn.setForeground(Color.LIGHT_GRAY);
		Frbtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Frbtn.setBounds(166, 85, 35, 23);
		contentPane.add(Frbtn);

		nonSpecificarerbtn = new JRadioButton("Non Specificare");
		nonSpecificarerbtn.setBackground(new Color(0, 0, 128));
		nonSpecificarerbtn.setForeground(Color.LIGHT_GRAY);
		nonSpecificarerbtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nonSpecificarerbtn.setBounds(203, 85, 134, 23);
		contentPane.add(nonSpecificarerbtn);

		sesso = new ButtonGroup();
		sesso.add(Frbtn);
		sesso.add(Mrbtn);
		sesso.add(nonSpecificarerbtn);
		if (dipendente.getSesso().equals("M")) {
			Mrbtn.setSelected(true);
		} else if (dipendente.getSesso().equals("F")) {
			Frbtn.setSelected(true);
		} else {
			nonSpecificarerbtn.setSelected(true);
		}
		Mrbtn.setEnabled(false);
		Frbtn.setEnabled(false);
		nonSpecificarerbtn.setEnabled(false);

		emailtf = new JTextField();
		emailtf.setToolTipText("Cambia Email Dipendente");
		emailtf.setBorder(new LineBorder(Color.BLACK));
		emailtf.setBounds(127, 138, 284, 20);
		contentPane.add(emailtf);
		emailtf.setColumns(10);
		emailtf.setText(dipendente.getEmail());
		emailtf.setEditable(false);

		cellularetf = new JTextField();
		cellularetf.setToolTipText("Cambia Il Numero Di Cellulare");
		cellularetf.setBorder(new LineBorder(Color.BLACK));
		cellularetf.setBounds(127, 163, 121, 20);
		contentPane.add(cellularetf);
		cellularetf.setColumns(10);
		cellularetf.setText(dipendente.getnCellulare());
		cellularetf.setEditable(false);

		cittàtf = new JTextField();
		cittàtf.setToolTipText("Cambia Citt\u00E0 Di Residenza");
		cittàtf.setBorder(new LineBorder(Color.BLACK));
		cittàtf.setBounds(127, 188, 190, 20);
		contentPane.add(cittàtf);
		cittàtf.setColumns(10);
		cittàtf.setText(dipendente.getResidenza().getCittà());
		cittàtf.setEditable(false);

		captf = new JTextField();
		captf.setToolTipText("Cambia CAP");
		captf.setBorder(new LineBorder(Color.BLACK));
		captf.setBounds(127, 213, 99, 20);
		contentPane.add(captf);
		captf.setColumns(10);
		captf.setText(dipendente.getResidenza().getCap());
		captf.setEditable(false);

		provinciacb = new JComboBox<String>();
		provinciacb.setToolTipText("Seleziona Nuova Provincia");
		provinciacb.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		provinciacb.setBounds(127, 237, 57, 22);
		contentPane.add(provinciacb);
		provinciacb.setSelectedItem(dipendente.getResidenza().getProvincia());
		provinciacb.setEnabled(false);

		viatf = new JTextField();
		viatf.setToolTipText("Cambia Via Di Residenza");
		viatf.setBorder(new LineBorder(Color.BLACK));
		viatf.setBounds(127, 263, 190, 20);
		contentPane.add(viatf);
		viatf.setColumns(10);
		viatf.setText(dipendente.getResidenza().getVia());
		viatf.setEditable(false);

		nCivicotf = new JTextField();
		nCivicotf.setToolTipText("Cambia Numero Civico");
		nCivicotf.setBorder(new LineBorder(Color.BLACK));
		nCivicotf.setBounds(127, 288, 86, 20);
		contentPane.add(nCivicotf);
		nCivicotf.setColumns(10);
		nCivicotf.setText(dipendente.getResidenza().getnCivico());
		nCivicotf.setEditable(false);

		salariotf = new JTextField();
		salariotf.setToolTipText("Cambia Salario");
		salariotf.setBorder(new LineBorder(Color.BLACK));
		salariotf.setBounds(127, 313, 86, 20);
		contentPane.add(salariotf);
		salariotf.setColumns(10);
		salariotf.setText(String.valueOf(dipendente.getSalarioMedio()));
		salariotf.setEditable(false);

		dataDiNascita = new JDateChooser();
		dataDiNascita.setBorder(new LineBorder(new Color(0, 0, 0)));
		dataDiNascita.setBounds(127, 114, 99, 20);
		contentPane.add(dataDiNascita);

		dataDiNascita.setDate(dipendente.getDataNascita());
		dataDiNascita.setEnabled(false);

		iconaSalvataggio = new ImageIcon(
				(VisualizzaModificaDipendenteGUI.class.getResource("/icon/iconaSalvataggio.png")));
		salvabtn = new JButton("Salva");
		salvabtn.setHorizontalTextPosition(SwingConstants.LEFT);
		salvabtn.setIcon(iconaSalvataggio);
		salvabtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		salvabtn.setFocusable(false);
		salvabtn.setVisible(false);
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
							"Vuoi aggiornare " + nometf.getText() + " " + cognometf.getText() + "?", "Salvataggio",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Yes", "No" },
							JOptionPane.YES_OPTION);
					if (opzione == JOptionPane.YES_OPTION) {
						if (!foto[1].equals(fotoVecchia)) {
							c.SalvaNuovaFoto(foto);
						}
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
						dipendente.setnCellulare(cellularetf.getText());

						try {
							c.AggiornaDipendente(dipendente, vecchioCF);
							int risposta = JOptionPane.showOptionDialog(null, "Dipendente Aggiornato! Vuoi fare altro?",
									"Salvato", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
									new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);
							if (risposta == JOptionPane.YES_OPTION) {

								nometf.setText(dipendente.getNome());
								nometf.setEditable(false);
								cognometf.setText(dipendente.getCognome());
								cognometf.setEditable(false);
								codFtf.setText(dipendente.getCodiceFiscale());
								codFtf.setEditable(false);
								emailtf.setText(dipendente.getEmail());
								emailtf.setEditable(false);
								cittàtf.setText(dipendente.getResidenza().getCittà());
								cittàtf.setEditable(false);
								viatf.setText(dipendente.getResidenza().getVia());
								viatf.setEditable(false);
								captf.setText(dipendente.getResidenza().getCap());
								captf.setEditable(false);
								salariotf.setText(String.valueOf(dipendente.getSalarioMedio()));
								salariotf.setEditable(false);
								nCivicotf.setText(dipendente.getResidenza().getnCivico());
								nCivicotf.setEditable(false);
								cellularetf.setText(dipendente.getnCellulare());
								cellularetf.setEditable(false);
								dataDiNascita.setDate(dipendente.getDataNascita());
								dataDiNascita.setEnabled(false);
								fotobtn.setIcon(c.resize(new ImageIcon(
										VisualizzaModificaDipendenteGUI.class.getResource(dipendente.getPathFoto())),
										369, 385));
								pathImage = dipendente.getPathFoto();
								provinciacb.setSelectedItem(dipendente.getResidenza().getProvincia());
								provinciacb.setEnabled(false);
								if (dipendente.getSesso().equals("M")) {
									Mrbtn.setSelected(true);
								} else if (dipendente.getSesso().equals("F")) {
									Frbtn.setSelected(true);
								} else {
									nonSpecificarerbtn.setSelected(true);
								}
								Mrbtn.setEnabled(false);
								Frbtn.setEnabled(false);
								nonSpecificarerbtn.setEnabled(false);
								salvabtn.setVisible(false);
								rimuoviFotobtn.setVisible(false);

							} else if (risposta == JOptionPane.NO_OPTION) {
								c.TornaDipendentiGUIDaVisualizzaModificaDipendente();
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
		salvabtn.setToolTipText("Salva Modifiche Dipendente");
		salvabtn.setBounds(936, 581, 89, 44);
		contentPane.add(salvabtn);

		fotoPrecedentebtn = new JButton("Foto Precedente");
		fotoPrecedentebtn.setToolTipText("Rimuovi Nuova Foto Selezionata");
		fotoPrecedentebtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		fotoPrecedentebtn.setFocusable(false);
		fotoPrecedentebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pathImage = dipendente.getPathFoto();
				fotobtn.setIcon(c.resize(new ImageIcon(VisualizzaModificaDipendenteGUI.class.getResource(pathImage)),
						369, 385));

			}
		});
		fotoPrecedentebtn.setBounds(508, 287, 126, 23);
		fotoPrecedentebtn.setVisible(false);
		contentPane.add(fotoPrecedentebtn);

		iconaModificabtn = new ImageIcon(
				(VisualizzaModificaDipendenteGUI.class.getResource("/icon/iconaModifica.png")));
		modificabtn = new JButton("Modifica");
		modificabtn.setHorizontalTextPosition(SwingConstants.LEFT);
		modificabtn.setIcon(iconaModificabtn);
		modificabtn.setToolTipText("Attiva Modifica");
		modificabtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		modificabtn.setFocusable(false);
		modificabtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salvabtn.setVisible(true);
				fotoPrecedentebtn.setVisible(true);
				Mrbtn.setEnabled(true);
				Frbtn.setEnabled(true);
				nonSpecificarerbtn.setEnabled(true);
				nometf.setEditable(true);
				cognometf.setEditable(true);
				codFtf.setEditable(true);
				emailtf.setEditable(true);
				dataDiNascita.setEnabled(true);
				cellularetf.setEditable(true);
				cittàtf.setEditable(true);
				captf.setEditable(true);
				viatf.setEditable(true);
				nCivicotf.setEditable(true);
				salariotf.setEditable(true);
				provinciacb.setEditable(true);
				rimuoviFotobtn.setVisible(true);

			}
		});
		modificabtn.setBounds(837, 581, 89, 44);
		contentPane.add(modificabtn);

		valutazionelbl = new JLabel("Valutazione");
		valutazionelbl.setForeground(Color.LIGHT_GRAY);
		valutazionelbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		valutazionelbl.setBounds(10, 339, 89, 27);
		contentPane.add(valutazionelbl);

		valoreValutazionelbl = new JLabel(String.valueOf(dipendente.getValutazione()));
		valoreValutazionelbl.setForeground(Color.LIGHT_GRAY);
		valoreValutazionelbl.setToolTipText("Valutazione Dipendente");
		valoreValutazionelbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		valoreValutazionelbl.setBounds(127, 341, 86, 25);
		contentPane.add(valoreValutazionelbl);

		rimuoviFotobtn = new JButton("Rimuovi Foto");
		rimuoviFotobtn.setToolTipText("Rimuovi La Foto Reimpostando Quella Di Default");
		rimuoviFotobtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		rimuoviFotobtn.setFocusable(false);
		rimuoviFotobtn.setVisible(false);
		rimuoviFotobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pathImage = "/fotoDipendenti/fotoprofilodefault2.jpg";
				fotobtn.setIcon(c.resize(new ImageIcon(HomeGUI.class.getResource(pathImage)), 369, 385));
			}
		});
		rimuoviFotobtn.setBounds(508, 137, 126, 23);
		contentPane.add(rimuoviFotobtn);

		progettiscrollPane = new JScrollPane();
		progettiscrollPane.setBorder(new LineBorder(Color.BLACK));
		progettiscrollPane.setToolTipText("Progetti Associati Al Dipendente");
		progettiscrollPane.setBounds(10, 456, 492, 120);
		contentPane.add(progettiscrollPane);

		progettiTable = new JTable(righe, colonne) {
			public boolean isCellEditable(int riga, int colonna) {
				return false;
			}
		};

		progettiTable = new JTable();
		progettiscrollPane.setViewportView(progettiTable);
		model = new DefaultTableModel();
		progettiTable.setModel(model);
		progettiTable.getTableHeader().setReorderingAllowed(false);
		model.setRowCount(0);

		if (dipendente.getProgettiDipendente().size() != 0) {
			for (ProgettiDelDipendente p : dipendente.getProgettiDipendente()) {
				Object[] column = { "Nome Progetto", "Tipologia", "Ruolo" };
				Object[] row = new Object[3];
				model.setColumnIdentifiers(column);
				row[0] = p.getNomeProgetto();
				row[1] = p.getTipologia();
				row[2] = p.getRuolo();

				model.addRow(row);
			}
		} else {
			Object[] column = { "Nome Progetto", "Tipologia", "Ruolo" };
			Object[] row = new Object[3];
			model.setColumnIdentifiers(column);

		}

		meetingscrollPane = new JScrollPane();
		meetingscrollPane.setBorder(new LineBorder(Color.BLACK));
		meetingscrollPane.setToolTipText("Meeting Associati Al Dipendente");
		meetingscrollPane.setBounds(523, 456, 502, 120);
		contentPane.add(meetingscrollPane);

		meetingTable = new JTable(righe, colonne) {
			public boolean isCellEditable(int riga, int colonna) {
				return false;
			}
		};
		meetingscrollPane.setViewportView(meetingTable);
		model = new DefaultTableModel();
		meetingTable.setModel(model);

		statuslbl = new JLabel("Status");
		statuslbl.setForeground(Color.LIGHT_GRAY);
		statuslbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		statuslbl.setBounds(10, 382, 68, 14);
		contentPane.add(statuslbl);

		mostraStatuslbl = new JLabel("");
		mostraStatuslbl.setForeground(Color.LIGHT_GRAY);
		mostraStatuslbl.setText(dipendente.getStatus());
		mostraStatuslbl.setHorizontalAlignment(SwingConstants.CENTER);
		mostraStatuslbl.setBounds(127, 377, 126, 19);
		contentPane.add(mostraStatuslbl);

		disattivabtn = new JButton("Disattiva");
		disattivabtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		disattivabtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int opzione = JOptionPane.showOptionDialog(null,
							"Vuoi disattivate " + nometf.getText() + " " + cognometf.getText() + " ?", "Disattivazione",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Yes", "No" },
							JOptionPane.YES_OPTION);
					if (opzione == JOptionPane.YES_OPTION) {
						c.DisattivaDipendente(dipendente.getCodiceFiscale());
						mostraStatuslbl.setText("NON ATTIVO");
						attivabtn.setVisible(true);
						disattivabtn.setVisible(false);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		disattivabtn.setToolTipText("Disattiva Dipendente");
		disattivabtn.setFocusable(false);
		disattivabtn.setBounds(10, 407, 89, 38);
		contentPane.add(disattivabtn);

		attivabtn = new JButton("Attiva");
		attivabtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		attivabtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int opzione = JOptionPane.showOptionDialog(null,
							"Vuoi attivare " + nometf.getText() + " " + cognometf.getText() + " ?", "Attivazione",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Yes", "No" },
							JOptionPane.YES_OPTION);
					if (opzione == JOptionPane.YES_OPTION) {
						boolean controlloSalario = false;
						while (!controlloSalario) {
							double salario;
							salario = Double.parseDouble(JOptionPane.showInputDialog(null, "Inserisci salario",
									"Salario", JOptionPane.NO_OPTION));
							if (c.ControlloSalario(Double.toString(salario))) {

								c.AttivaDipendente(dipendente.getCodiceFiscale(), salario);
								mostraStatuslbl.setText("ATTIVO");
								salariotf.setText(Double.toString(salario));
								attivabtn.setVisible(false);
								disattivabtn.setVisible(true);
								controlloSalario = true;
							} else {
								JOptionPane.showMessageDialog(null,
										"Il salario non può avere lettere e deve avere 2 numeri decimali - Usa il punto come separatore decimale",
										"Errore salario", JOptionPane.ERROR_MESSAGE);

							}
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		attivabtn.setToolTipText("Attiva Dipendente");
		attivabtn.setFocusable(false);
		attivabtn.setBounds(10, 407, 89, 38);
		if (dipendente.getStatus().equals("ATTIVO")) {
			attivabtn.setVisible(false);

		} else {
			disattivabtn.setVisible(false);

		}

		contentPane.add(attivabtn);
		meetingTable.getTableHeader().setReorderingAllowed(false);
		if (dipendente.getMeetingFisici().size() != 0) {
			for (MeetingFisico mf : dipendente.getMeetingFisici()) {
				Object[] column = { "Codice", "Tipo", "Data", "Luogo" };
				Object[] row = new Object[4];
				model.setColumnIdentifiers(column);
				row[0] = mf.getCodMeetingFisico();
				row[1] = "Fisico";
				row[2] = mf.getDataMeeting();
				row[3] = mf.getSala().getNomeSala();

				model.addRow(row);
			}
		} else {
			Object[] column = { "Codice", "Tipo", "Data", "Luogo" };
			Object[] row = new Object[4];
			model.setColumnIdentifiers(column);

		}
		for (MeetingTelematico mt : dipendente.getMeetingTelematici()) {
			Object[] row = new Object[4];
			row[0] = mt.getCodMeetingTelematico();
			row[1] = "Telematico";
			row[2] = mt.getDataMeeting();
			row[3] = mt.getPiattaforma().getNomePiattaforma();

			model.addRow(row);
		}
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

	}
}
