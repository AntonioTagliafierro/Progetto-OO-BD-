package gui;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
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
import java.util.Date;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

public class AggiungiDipendenteGUI extends JFrame {

	private JPanel contentPane;

	Controller theController;

	private String iconaFrame;
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
	private String pathImage = "C:\\Users\\xtony\\eclipse-workspace\\Progetto[OODB2021] gruppo 15 traccia 2\\fotoDipendenti\\fotoprofilodefault2.jpg";
	private Date data = new Date();
	private int controllo = 0;

	/**
	 * Create the frame.
	 */
	public AggiungiDipendenteGUI(Controller c) {

		theController = c;
		iconaFrame = new String(
				"C:\\Users\\xtony\\eclipse-workspace\\Progetto[OODB2021] gruppo 15 traccia 2\\icon\\iconaFrame.png");
		setIconImage(Toolkit.getDefaultToolkit().getImage(iconaFrame));
		setTitle("AggiungiDipendente");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 816, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		nomelbl = new JLabel("Nome");
		nomelbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nomelbl.setBounds(10, 11, 47, 25);
		contentPane.add(nomelbl);

		cognomelbl = new JLabel("Cognome");
		cognomelbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cognomelbl.setBounds(10, 33, 68, 25);
		contentPane.add(cognomelbl);

		codFlbl = new JLabel("Codice Fiscale");
		codFlbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		codFlbl.setBounds(10, 64, 99, 14);
		contentPane.add(codFlbl);

		sessolbl = new JLabel("Sesso");
		sessolbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sessolbl.setBounds(11, 89, 46, 14);
		contentPane.add(sessolbl);

		dataNlbl = new JLabel("Data DI Nascita");
		dataNlbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dataNlbl.setBounds(11, 114, 98, 14);
		contentPane.add(dataNlbl);

		emaillbl = new JLabel("e-mail");
		emaillbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		emaillbl.setBounds(11, 139, 46, 14);
		contentPane.add(emaillbl);

		nCellularelbl = new JLabel("Numero Cellulare");
		nCellularelbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nCellularelbl.setBounds(11, 164, 111, 14);
		contentPane.add(nCellularelbl);

		cittàlbl = new JLabel("Citt\u00E0");
		cittàlbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cittàlbl.setHorizontalAlignment(SwingConstants.LEFT);
		cittàlbl.setBounds(10, 189, 37, 14);
		contentPane.add(cittàlbl);

		caplbl = new JLabel("CAP");
		caplbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		caplbl.setBounds(10, 214, 30, 14);
		contentPane.add(caplbl);

		provincialbl = new JLabel("Provincia");
		provincialbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		provincialbl.setHorizontalAlignment(SwingConstants.LEFT);
		provincialbl.setBounds(10, 239, 68, 14);
		contentPane.add(provincialbl);

		vialbl = new JLabel("Via");
		vialbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		vialbl.setBounds(10, 264, 30, 14);
		contentPane.add(vialbl);

		nCivicolbl = new JLabel("N\u00B0Civico");
		nCivicolbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nCivicolbl.setBounds(10, 289, 68, 14);
		contentPane.add(nCivicolbl);

		salariolbl = new JLabel("Salario");
		salariolbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		salariolbl.setBounds(10, 335, 46, 14);
		contentPane.add(salariolbl);

		fotobtn = new JButton("");
		fotobtn.setOpaque(false);
		fotobtn.setFocusable(false);
		fotobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pathImage = c.SalvaFotoDipendente();
				fotobtn.setIcon(c.resize(new ImageIcon(pathImage), 369, 389));

			}
		});
		fotobtn.setToolTipText("Inserisci Foto Dipendente");
		fotobtn.setBounds(421, 11, 369, 385);
		fotobtn.setIcon(c.resize(new ImageIcon(
				"C:\\Users\\xtony\\eclipse-workspace\\Progetto[OODB2021] gruppo 15 traccia 2\\fotoDipendenti\\fotoprofilodefault2.jpg"),
				369, 389));
		contentPane.add(fotobtn);

		JButton tornaIndietrobtn = new JButton("Indietro");
		tornaIndietrobtn.setFocusable(false);
		tornaIndietrobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.TornaDipendentiGUIDaAggiungiDipendente();
			}
		});
		tornaIndietrobtn.setToolTipText("Torna Indietro");
		tornaIndietrobtn.setBounds(10, 411, 89, 44);
		contentPane.add(tornaIndietrobtn);

		nometf = new JTextField();
		nometf.setBounds(127, 15, 154, 20);
		contentPane.add(nometf);
		nometf.setColumns(10);

		cognometf = new JTextField();
		cognometf.setBounds(127, 37, 154, 20);
		contentPane.add(cognometf);
		cognometf.setColumns(10);

		codFtf = new JTextField();
		codFtf.setBounds(127, 63, 126, 20);
		contentPane.add(codFtf);
		codFtf.setColumns(10);

		Mrbtn = new JRadioButton("M");
		Mrbtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Mrbtn.setBounds(127, 85, 37, 23);
		contentPane.add(Mrbtn);

		Frbtn = new JRadioButton("F");
		Frbtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Frbtn.setBounds(166, 85, 35, 23);
		contentPane.add(Frbtn);

		nonSpecificarerbtn = new JRadioButton("Non Specificare");
		nonSpecificarerbtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nonSpecificarerbtn.setBounds(203, 85, 134, 23);
		contentPane.add(nonSpecificarerbtn);

		sesso = new ButtonGroup();
		sesso.add(Frbtn);
		sesso.add(Mrbtn);
		sesso.add(nonSpecificarerbtn);
		nonSpecificarerbtn.setSelected(true);

		emailtf = new JTextField();
		emailtf.setBounds(127, 138, 284, 20);
		contentPane.add(emailtf);
		emailtf.setColumns(10);

		JComboBox<String> prefissocb = new JComboBox<String>();
		prefissocb.setBounds(127, 162, 165, 22);
		contentPane.add(prefissocb);

		cellularetf = new JTextField();
		cellularetf.setBounds(290, 163, 121, 20);
		contentPane.add(cellularetf);
		cellularetf.setColumns(10);

		cittàtf = new JTextField();
		cittàtf.setBounds(127, 188, 190, 20);
		contentPane.add(cittàtf);
		cittàtf.setColumns(10);

		captf = new JTextField();
		captf.setBounds(127, 213, 99, 20);
		contentPane.add(captf);
		captf.setColumns(10);

		JComboBox<String> provinciacb = new JComboBox<String>();
		provinciacb.setBounds(127, 237, 57, 22);
		contentPane.add(provinciacb);

		viatf = new JTextField();
		viatf.setBounds(127, 263, 190, 20);
		contentPane.add(viatf);
		viatf.setColumns(10);

		nCivicotf = new JTextField();
		nCivicotf.setBounds(127, 288, 86, 20);
		contentPane.add(nCivicotf);
		nCivicotf.setColumns(10);

		salariotf = new JTextField();
		salariotf.setBounds(127, 334, 86, 20);
		contentPane.add(salariotf);
		salariotf.setColumns(10);

		JDateChooser dataDiNascita = new JDateChooser();
		dataDiNascita.setBounds(127, 114, 99, 20);
		contentPane.add(dataDiNascita);

		dataDiNascita.setDate(data);

		JButton salvabtn = new JButton("Salva");
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
				if(c.ControlloSalario(salariotf.getText())) {
					
					salariotf.setBackground(Color.white);
				}else {
					salariotf.setBackground(Color.red);
					JOptionPane.showMessageDialog(null, "Il salario non può avere lettere e deve avere 2 numeri decimali - Usa il punto come separatore decimale",
							"Errore salario", JOptionPane.ERROR_MESSAGE);
					controllo++;
				}
				
				if(controllo == 0) {
					controllo = 0;
					c.SalvaDipendente();
				} else {
					
					controllo = 0;
					return;
				}

			}
		});
		salvabtn.setToolTipText("Salva Dipendente");
		salvabtn.setBounds(701, 411, 89, 44);
		contentPane.add(salvabtn);

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
