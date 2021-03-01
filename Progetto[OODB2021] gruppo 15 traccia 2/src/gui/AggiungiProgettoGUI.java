package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.Controller;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JCalendar;
import com.toedter.components.JSpinField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AggiungiProgettoGUI extends JFrame {
	
	private JPanel contentPane;
	Controller theController;
	private JTextField Tipologia_TF;
	private JLabel Ambiti_JL;
	private JTextField Ambiti_TF;
	private JLabel DataScadenza_JL;
	private JTextField NomeCliente_TF;
	private JTextField Nome_TF;
	private JLabel Nome_JL;
	private JLabel Descrizione_JL;
	private JLabel NomeCliente_JL;
	private JDateChooser DataScadenza_JC;
	private JDateChooser ScegliData_JC;
	private JLabel DataInizio_JL;
	private JLabel Tipologia_JL;
	private JTextArea Descrizione_JTP;
	private JTable Ambiti_Table;
	private JScrollPane scrollPane_1;
	private DefaultTableModel model;
	private int controllo = 0;
    private JScrollPane scrollPane;
	 private JLabel TipologiaPartecipante_JL;
	 private JLabel ValutazionePartecipante_JL;
	 private JLabel SalarioPartecipante_JL;
	 
	 
	 Date data = new Date();
	private JButton CercaPartecipante_btm;
	private JButton CancellaFiltri_btm;
	private JTextField TipologiaPartecipante_TF;
	private JTextField SalarioPartecipante_TF;
	private JTextField ValutazionePartecipante_TF;
	private JTable Partecipanti_JT;

	public AggiungiProgettoGUI(Controller c) {
		theController = c;
		setTitle("Aggiungi progetto");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 814, 702);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton Indietro_btm = new JButton("");
		Indietro_btm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Indietro_btm.setToolTipText("Vai alla pagina precedente");
			}
		});
		Indietro_btm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theController.ChiudiAggiungiProgetto();
			}
		});
		ImageIcon iconaIndietro_btm = new ImageIcon("C:\\Users\\Gianpietro\\Desktop\\GP\\Uni\\imm\\iconaIndietrobtm.png");
		Indietro_btm.setIcon(iconaIndietro_btm);
		Indietro_btm.setBorder(null);
		Indietro_btm.setFocusable(false);
		Indietro_btm.setBounds(0, 0, 43, 23);
		contentPane.add(Indietro_btm);
		
		Nome_JL = new JLabel("Nome");
		Nome_JL.setToolTipText("");
		Nome_JL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Nome_JL.setBounds(10, 34, 64, 37);
		contentPane.add(Nome_JL);
		Nome_JL.setToolTipText(null);
		
		Tipologia_JL = new JLabel("Tipologia");
		Tipologia_JL.setToolTipText("");
		Tipologia_JL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Tipologia_JL.setBounds(10, 68, 64, 37);
		contentPane.add(Tipologia_JL);
		Tipologia_JL.setToolTipText(null);
		
		Tipologia_TF = new JTextField();
		Tipologia_TF.setToolTipText("Inserisci la tipologia del progetto.");
		Tipologia_TF.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Tipologia_TF.setBounds(132, 76, 86, 20);
		contentPane.add(Tipologia_TF);
		Tipologia_TF.setColumns(10);
		
		Ambiti_JL = new JLabel("Ambiti");
		Ambiti_JL.setToolTipText("");
		Ambiti_JL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Ambiti_JL.setBounds(10, 210, 46, 14);
		contentPane.add(Ambiti_JL);
		Ambiti_JL.setToolTipText(null);
		
		Ambiti_TF = new JTextField();
//		Ambiti_TF.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				if(e.getKeyCode()==10) {
//					model.addRow(new Object[] {Ambiti_TF.getText()});
//					Ambiti_TF.setText("");
//				}
//			}
//		});
		Ambiti_TF.setToolTipText("Inserisci gli ambiti del progetto.");
		Ambiti_TF.setBounds(132, 209, 86, 20);
		contentPane.add(Ambiti_TF);
		Ambiti_TF.setColumns(10);
		
		JButton AggiungiAmbiti_btn = new JButton("Aggiungi");
		AggiungiAmbiti_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Ambiti_TF.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Per aggiungere un ambito devi riempire il campo", "Errore", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
			model = (DefaultTableModel) Ambiti_Table.getModel();
			model.addRow(new Object[] {Ambiti_TF.getText()});
			Ambiti_TF.setText("");
			}
		});
				
		AggiungiAmbiti_btn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		AggiungiAmbiti_btn.setBounds(249, 207, 86, 23);
		contentPane.add(AggiungiAmbiti_btn);
		AggiungiAmbiti_btn.setFocusable(false);
		
		
		DataInizio_JL = new JLabel("Data Inizio");
		DataInizio_JL.setToolTipText("");
		DataInizio_JL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DataInizio_JL.setBounds(10, 116, 77, 14);
		contentPane.add(DataInizio_JL);
		DataInizio_JL.setToolTipText("Inserisci la data di inizio del progetto");
		
		DataScadenza_JL = new JLabel("Data Scadenza");
		DataScadenza_JL.setToolTipText("Inserisci la data di scadenza del progetto.");
		DataScadenza_JL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DataScadenza_JL.setBounds(10, 144, 103, 14);
		contentPane.add(DataScadenza_JL);
		
		ScegliData_JC = new JDateChooser();
		ScegliData_JC.setBounds(132, 117, 108, 20);
		contentPane.add(ScegliData_JC);
		ScegliData_JC.setDate(data);
		
		DataScadenza_JC = new JDateChooser();
		DataScadenza_JC.setBounds(132, 144, 108, 20);
		contentPane.add(DataScadenza_JC);
		DataScadenza_JC.setDate(data);
		
		NomeCliente_JL = new JLabel("Nome Cliente");
		NomeCliente_JL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		NomeCliente_JL.setToolTipText("");
		NomeCliente_JL.setBounds(10, 179, 86, 14);
		contentPane.add(NomeCliente_JL);
		NomeCliente_JL.setToolTipText(null);
		
		NomeCliente_TF = new JTextField();
		NomeCliente_TF.setToolTipText("Inserisci il nome di chi ha commissionato il progetto.");
		NomeCliente_TF.setBounds(132, 178, 86, 20);
		contentPane.add(NomeCliente_TF);
		NomeCliente_TF.setColumns(10);
		
		Descrizione_JL = new JLabel("Descrizione");
		Descrizione_JL.setToolTipText("");
		Descrizione_JL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Descrizione_JL.setBounds(284, 45, 92, 14);
		contentPane.add(Descrizione_JL);
		Descrizione_JL.setToolTipText(null);
		
		Nome_TF = new JTextField();
		Nome_TF.setToolTipText("Inserisci il nome del progetto.");
		Nome_TF.setBounds(132, 44, 86, 20);
		contentPane.add(Nome_TF);
		Nome_TF.setColumns(10);
		
				
		JButton Salva_btm = new JButton("Salva");
		Salva_btm.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				if (!c.ControllaCampiVuotiAggiungiProgetto(Nome_TF.getText(), Descrizione_JTP.getText(), NomeCliente_TF.getText(),
						Tipologia_TF.getText())) {
					JOptionPane.showMessageDialog(null, "Riempi tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
					return;
							}
				
				if (ScegliData_JC.getDate().after(DataScadenza_JC.getDate())) {
					JOptionPane.showMessageDialog(null, "Correggi le date inserite", "Errore", JOptionPane.ERROR_MESSAGE);
					controllo++;
				}
				
			// manca il controllo su gli ambiti e le date
				
				

				
				if (c.controlloExpRegNome(Nome_TF.getText())) {

					Nome_TF.setBackground(Color.white);

				} else {

					Nome_TF.setBackground(Color.red);
					JOptionPane.showMessageDialog(null,
							"Il nome non può avere numeri e deve avere massimo 20 caratteri", "Errore nome",
							JOptionPane.ERROR_MESSAGE);
					
					controllo++;
				}
				
				if (c.controlloExpRegNome(Tipologia_TF.getText())) {

					Tipologia_TF.setBackground(Color.white);

				} else {

					Tipologia_TF.setBackground(Color.red);
					JOptionPane.showMessageDialog(null,
							"La tipologia non può avere numeri e deve avere massimo 20 caratteri", "Errore tipologia",
							JOptionPane.ERROR_MESSAGE);
					
					controllo++;
				}
				
				if (c.controlloExpRegNome(NomeCliente_TF.getText())) {

					NomeCliente_TF.setBackground(Color.white);

				} else {

					NomeCliente_TF.setBackground(Color.red);
					JOptionPane.showMessageDialog(null,
							"Il nome del cliente non può avere numeri e deve avere massimo 20 caratteri", "Errore nome",
							JOptionPane.ERROR_MESSAGE);
					
					controllo++;
				}
				
				if(Ambiti_Table.getRowCount()== 0) {
					JOptionPane.showMessageDialog(null,
							"Inserisci almeno un ambito", "Errore ambito",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			
				if (controllo == 0) {

					int opzione = JOptionPane.showOptionDialog(null,
							"Vuoi salvare " + Nome_TF.getText() + " come progetto?",
							"Salvataggio", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
							new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);
					
						}
				
				}});
		Salva_btm.setFocusable(false);
		Salva_btm.setBounds(651, 541, 89, 23);
		contentPane.add(Salva_btm);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 251, 120, 123);
		contentPane.add(scrollPane_1);
		
		Ambiti_Table = new JTable();
		scrollPane_1.setViewportView(Ambiti_Table);
		model = new DefaultTableModel();
		Ambiti_Table.setModel(model);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(387, 45, 353, 132);
		contentPane.add(scrollPane);
		
		Descrizione_JTP = new JTextArea();
		scrollPane.setViewportView(Descrizione_JTP);
		
		TipologiaPartecipante_JL = new JLabel("Tipologia");
		TipologiaPartecipante_JL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		TipologiaPartecipante_JL.setBounds(216, 291, 65, 20);
		contentPane.add(TipologiaPartecipante_JL);
		
		ValutazionePartecipante_JL = new JLabel("Valutazione");
		ValutazionePartecipante_JL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ValutazionePartecipante_JL.setBounds(216, 384, 80, 14);
		contentPane.add(ValutazionePartecipante_JL);
		
		SalarioPartecipante_JL = new JLabel("Salario");
		SalarioPartecipante_JL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		SalarioPartecipante_JL.setBounds(216, 340, 65, 14);
		contentPane.add(SalarioPartecipante_JL);
			
		
		Ambiti_Table.getTableHeader().setReorderingAllowed(false);
		
		Object[] column = {"Ambiti"};
		model.setColumnIdentifiers(column);
		
		
		CercaPartecipante_btm = new JButton("");
		CercaPartecipante_btm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!c.ControllaCampiVuotiAggiungiPartecipante(TipologiaPartecipante_TF.getText(),
						ValutazionePartecipante_TF.getText(), SalarioPartecipante_TF.getText())) {
					JOptionPane.showMessageDialog(null, "Riempi tutti i campi di ricerca", "Errore", JOptionPane.ERROR_MESSAGE);
					return;
							}
				
				if (c.controlloExpRegNome(TipologiaPartecipante_TF.getText())) {

					TipologiaPartecipante_TF.setBackground(Color.white);

				} else {

					TipologiaPartecipante_TF.setBackground(Color.red);
					JOptionPane.showMessageDialog(null,
							"La tipologia non può avere numeri e deve avere massimo 20 caratteri", "Errore tipologia",
							JOptionPane.ERROR_MESSAGE);
					
					controllo++;
				}
				
				if (c.ControlloSalario(SalarioPartecipante_TF.getText())) {

					SalarioPartecipante_TF.setBackground(Color.white);
				} else {
					SalarioPartecipante_TF.setBackground(Color.red);
					JOptionPane.showMessageDialog(null,
							"Il salario non può avere lettere e deve avere 2 numeri decimali - Usa il punto come separatore decimale",
							"Errore salario", JOptionPane.ERROR_MESSAGE);
					controllo++;
				}
				
				
			}
		});
		CercaPartecipante_btm.setToolTipText("Cerca partecipante");
		CercaPartecipante_btm.setFocusable(false);
		CercaPartecipante_btm.setBounds(470, 233, 40, 39);
		contentPane.add(CercaPartecipante_btm);
		ImageIcon iconaCercaPartecipante = new ImageIcon("C:\\Users\\Gianpietro\\Desktop\\GP\\Uni\\imm\\iconaCercaProgettobtm.png");
		CercaPartecipante_btm.setIcon(iconaCercaPartecipante);
		
		CancellaFiltri_btm = new JButton("");
		CancellaFiltri_btm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TipologiaPartecipante_TF.setText("");
				ValutazionePartecipante_TF.setText("");
				SalarioPartecipante_TF.setText("");
			}
		});
	
		CancellaFiltri_btm.setToolTipText("Cancella i filtri");
		CancellaFiltri_btm.setFocusable(false);
		CancellaFiltri_btm.setBounds(554, 233, 40, 39);
		contentPane.add(CancellaFiltri_btm);
		ImageIcon iconaCancellaFiltri_btm = new ImageIcon("C:\\Users\\Gianpietro\\Desktop\\GP\\Uni\\imm\\iconaCancellaProgettobtm.png");
		CancellaFiltri_btm.setIcon(iconaCancellaFiltri_btm);
		
		TipologiaPartecipante_TF = new JTextField();
		TipologiaPartecipante_TF.setColumns(10);
		TipologiaPartecipante_TF.setBounds(323, 293, 86, 20);
		contentPane.add(TipologiaPartecipante_TF);
		
		SalarioPartecipante_TF = new JTextField();
		SalarioPartecipante_TF.setColumns(10);
		SalarioPartecipante_TF.setBounds(323, 339, 86, 20);
		contentPane.add(SalarioPartecipante_TF);
		
		ValutazionePartecipante_TF = new JTextField();
		ValutazionePartecipante_TF.setColumns(10);
		ValutazionePartecipante_TF.setBounds(323, 383, 86, 20);
		contentPane.add(ValutazionePartecipante_TF);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(470, 295, 294, 146);
		contentPane.add(scrollPane_2);
		
		Partecipanti_JT = new JTable();
		scrollPane_2.setViewportView(Partecipanti_JT);
		model = new DefaultTableModel();
		Partecipanti_JT.setModel(model);
	
		Object[] column1 = {"Nome","Cognome","Valutazione","Salario"};
		Object[] row = new Object[4];
		model.setColumnIdentifiers(column1);
		Partecipanti_JT.getTableHeader().setReorderingAllowed(false);
		
		

	}
}
