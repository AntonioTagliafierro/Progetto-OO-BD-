package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

import controller.Controller;
import entity.Ambito;
import entity.Dipendente;
import entity.PartecipanteProgetto;
import entity.Progetto;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VisualizzaModificaProgettoGUI extends JFrame {

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
	private JTable ElencoDipendenti_JT;
	private Progetto progetto;
	private JButton Modifica_btm;
	private JButton Salva_btm;
	private ImageIcon iconaIndietro_btm;
	private ImageIcon iconaCercaPartecipante_btm;
	private ImageIcon iconaCancellaFiltri_btm;
	private ImageIcon iconaModifica_btm;
	private ImageIcon iconaSalva_btm;
	private JTable PartecipantiProgetto_JT;
	private JScrollPane scrollPane_3;
	private TableModel righe;
	private TableColumnModel colonne;
	private DefaultTableModel model1;
	private DefaultTableModel modelDipendenti;
	private DefaultTableModel modelPartecipanti;
	private ArrayList<Dipendente> dipendenteDaAggiungere;
	private ArrayList<Dipendente> dipendentiPartecipanti;
	private ArrayList<PartecipanteProgetto> partecipantiDaAggiungere;
	private ArrayList<PartecipanteProgetto> dipendenteDaEliminare;
	private ArrayList<Ambito> ambitiProgetto;
	private ArrayList<Ambito> ambitiDaAggiungere;
	private ArrayList<Ambito> ambitiDaEliminare;
	private JButton AggiungiAmbiti_btn;
	private ImageIcon iconaAggiungiAmbito_btm;
	private JButton RimuoviAmbito_btm;
	private ImageIcon iconaCancellaAmbiti_btm;
	private JButton AggiungiPartecipante_btn;
	private JButton RimuoviPartecipante_btn;
	private ImageIcon iconaAggiungiPartecipante_btm;
	private ArrayList<Dipendente> dipendenti;
	private JButton Completa_btn;
	private ImageIcon iconaCompleta_btm;
	private ImageIcon iconaCancellaDipendente_btm;

	public VisualizzaModificaProgettoGUI(Controller c, Progetto progettoDaVisualizzare) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				if (Salva_btm.isVisible()) {
					int a = JOptionPane.showConfirmDialog(null, "Attenzione, se confermi perderai i dati inseriti",
							"Attenzione", JOptionPane.WARNING_MESSAGE);
					if (a == 0) {
						c.TornaHomeGUIDaModificaProgetto();
					}
				} else {
					c.TornaHomeGUIDaModificaProgetto();
				}
			}
		});

		try {
			this.dipendenteDaAggiungere = c.RecuperaDipendentiSenzaFiltri();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VisualizzaModificaProgettoGUI.class.getResource("/icon/iconaFrame.png")));
		this.progetto = progettoDaVisualizzare;
		String codp = progettoDaVisualizzare.getCodProgetto();

		setTitle("Dettagli progetto");
		ambitiDaAggiungere = new ArrayList<Ambito>();
		ambitiDaEliminare = new ArrayList<Ambito>();
		partecipantiDaAggiungere = new ArrayList<PartecipanteProgetto>();
		dipendenteDaEliminare = new ArrayList<PartecipanteProgetto>();
		theController = c;

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1005, 749);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton Indietro_btm = new JButton("");
		Indietro_btm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Salva_btm.isVisible()) {
					int a = JOptionPane.showConfirmDialog(null, "Attenzione, se confermi perderai i dati inseriti",
							"Attenzione", JOptionPane.WARNING_MESSAGE);
					if (a == 0) {
						c.ChiudiVisualizzaModificaProgetto();
					}
				} else {
					c.ChiudiVisualizzaModificaProgetto();
				}
			}
		});
		iconaIndietro_btm = new ImageIcon(
				(VisualizzaModificaProgettoGUI.class.getResource("/icon/iconaIndietrobtm.png.png")));
		Indietro_btm.setIcon(iconaIndietro_btm);
		Indietro_btm.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		Indietro_btm.setFocusable(false);
		Indietro_btm.setBounds(10, 643, 124, 39);
		contentPane.add(Indietro_btm);

		Nome_JL = new JLabel("Nome");
		Nome_JL.setForeground(Color.LIGHT_GRAY);
		Nome_JL.setToolTipText("");
		Nome_JL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Nome_JL.setBounds(10, 11, 64, 37);
		contentPane.add(Nome_JL);
		Nome_JL.setToolTipText(null);

		Tipologia_JL = new JLabel("Tipologia");
		Tipologia_JL.setForeground(Color.LIGHT_GRAY);
		Tipologia_JL.setToolTipText("");
		Tipologia_JL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Tipologia_JL.setBounds(10, 45, 64, 37);
		contentPane.add(Tipologia_JL);
		Tipologia_JL.setToolTipText(null);

		Tipologia_TF = new JTextField();
		Tipologia_TF.setBorder(new LineBorder(new Color(0, 0, 0)));
		Tipologia_TF.setEditable(false);
		Tipologia_TF.setText(progetto.getTipologiaProgetto());
		Tipologia_TF.setToolTipText("Inserisci la tipologia del progetto.");
		Tipologia_TF.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Tipologia_TF.setBounds(132, 53, 86, 20);
		contentPane.add(Tipologia_TF);
		Tipologia_TF.setColumns(10);

		Ambiti_JL = new JLabel("Ambiti");
		Ambiti_JL.setForeground(Color.LIGHT_GRAY);
		Ambiti_JL.setToolTipText("");
		Ambiti_JL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Ambiti_JL.setBounds(344, 22, 46, 14);
		contentPane.add(Ambiti_JL);
		Ambiti_JL.setToolTipText(null);

		Ambiti_TF = new JTextField();
		Ambiti_TF.setBorder(new LineBorder(new Color(0, 0, 0)));
		Ambiti_TF.setEditable(false);
		Ambiti_TF.setToolTipText("Inserisci gli ambiti del progetto.");
		Ambiti_TF.setBounds(426, 24, 86, 23);
		contentPane.add(Ambiti_TF);
		Ambiti_TF.setColumns(10);

		AggiungiAmbiti_btn = new JButton("");
		AggiungiAmbiti_btn.setVisible(false);
		AggiungiAmbiti_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (Ambiti_TF.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Per aggiungere un ambito devi riempire il campo", "Errore",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				model = (DefaultTableModel) Ambiti_Table.getModel();
				int nPartecipanti = model.getRowCount();

				for (int n = 0; n < nPartecipanti; n++) {

					if (Ambiti_Table.getValueAt(n, 0).equals(Ambiti_TF.getText().toUpperCase())) {
						JOptionPane.showMessageDialog(null, "Ambito già inserito", "Errore", JOptionPane.ERROR_MESSAGE);
						return;
					}

				}
				Ambito ambito = new Ambito();
				ambito.setNomeAmbito(Ambiti_TF.getText().toUpperCase());
				model.addRow(new Object[] { ambito.getNomeAmbito() });
				String ambitoAggiunto = Ambiti_TF.getText();
				Ambiti_TF.setText("");
				ambitiDaAggiungere.add(ambito);

				if (ambitiDaEliminare != null) {
					int dim = ambitiDaEliminare.size();
					if (dim != 0) {
						for (Iterator<Ambito> a = ambitiDaEliminare.iterator(); a.hasNext();) {
							Ambito amb = a.next();
							if (amb.getNomeAmbito().equals(ambito.getNomeAmbito())) {
								a.remove();
							}
						}

					}
				}

			}
		});
		AggiungiAmbiti_btn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		iconaAggiungiAmbito_btm = new ImageIcon(
				(VisualizzaModificaProgettoGUI.class.getResource("/icon/iconaAggiungiProgettobtm.png.png")));
		AggiungiAmbiti_btn.setIcon(iconaAggiungiAmbito_btm);
		AggiungiAmbiti_btn.setToolTipText("Aggiungi ambito");
		AggiungiAmbiti_btn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		AggiungiAmbiti_btn.setBounds(515, 24, 25, 23);
		contentPane.add(AggiungiAmbiti_btn);
		AggiungiAmbiti_btn.setFocusable(false);

		DataInizio_JL = new JLabel("Data Inizio");
		DataInizio_JL.setForeground(Color.LIGHT_GRAY);
		DataInizio_JL.setToolTipText("");
		DataInizio_JL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DataInizio_JL.setBounds(10, 93, 77, 14);
		contentPane.add(DataInizio_JL);
		DataInizio_JL.setToolTipText("Inserisci la data di inizio del progetto");

		DataScadenza_JL = new JLabel("Data Scadenza");
		DataScadenza_JL.setForeground(Color.LIGHT_GRAY);
		DataScadenza_JL.setToolTipText("Inserisci la data di scadenza del progetto.");
		DataScadenza_JL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DataScadenza_JL.setBounds(10, 121, 103, 14);
		contentPane.add(DataScadenza_JL);

		ScegliData_JC = new JDateChooser();
		ScegliData_JC.setBorder(new LineBorder(new Color(0, 0, 0)));
		ScegliData_JC.getCalendarButton().setEnabled(false);
		ScegliData_JC.setBounds(132, 94, 108, 20);
		contentPane.add(ScegliData_JC);
		ScegliData_JC.setDate(progetto.getDataInizioProgetto());
		ScegliData_JC.setEnabled(false);

		DataScadenza_JC = new JDateChooser();
		DataScadenza_JC.setBorder(new LineBorder(new Color(0, 0, 0)));
		DataScadenza_JC.getCalendarButton().setEnabled(false);
		DataScadenza_JC.setDate(progetto.getDataScadenzaProgetto());
		DataScadenza_JC.setBounds(132, 121, 108, 20);
		contentPane.add(DataScadenza_JC);
		DataScadenza_JC.setEnabled(false);

		NomeCliente_JL = new JLabel("Nome Cliente");
		NomeCliente_JL.setForeground(Color.LIGHT_GRAY);
		NomeCliente_JL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		NomeCliente_JL.setToolTipText("");
		NomeCliente_JL.setBounds(10, 156, 86, 14);
		contentPane.add(NomeCliente_JL);
		NomeCliente_JL.setToolTipText(null);

		NomeCliente_TF = new JTextField();
		NomeCliente_TF.setBorder(new LineBorder(new Color(0, 0, 0)));
		NomeCliente_TF.setEditable(false);
		NomeCliente_TF.setText(progetto.getNomeCliente());
		NomeCliente_TF.setToolTipText("Inserisci il nome di chi ha commissionato il progetto.");
		NomeCliente_TF.setBounds(132, 155, 86, 20);
		contentPane.add(NomeCliente_TF);
		NomeCliente_TF.setColumns(10);

		Descrizione_JL = new JLabel("Descrizione");
		Descrizione_JL.setForeground(Color.LIGHT_GRAY);
		Descrizione_JL.setToolTipText("");
		Descrizione_JL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Descrizione_JL.setBounds(658, 11, 92, 14);
		contentPane.add(Descrizione_JL);
		Descrizione_JL.setToolTipText(null);

		Nome_TF = new JTextField();
		Nome_TF.setBorder(new LineBorder(new Color(0, 0, 0)));
		Nome_TF.setEditable(false);
		Nome_TF.setText(progetto.getNomeProgetto());
		Nome_TF.setToolTipText("Inserisci il nome del progetto.");
		Nome_TF.setBounds(132, 21, 86, 20);
		contentPane.add(Nome_TF);
		Nome_TF.setColumns(10);

		Salva_btm = new JButton("Salva");
		Salva_btm.addActionListener(new ActionListener() {
			private JComponent scrollPane_2;

			public void actionPerformed(ActionEvent e) {

				if (!c.ControllaCampiVuotiAggiungiProgetto(Nome_TF.getText(), Descrizione_JTP.getText(),
						NomeCliente_TF.getText(), Tipologia_TF.getText())) {
					JOptionPane.showMessageDialog(null, "Riempi tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (PartecipantiProgetto_JT.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "Inserisci almeno un partecipante", "Errore",
							JOptionPane.ERROR_MESSAGE);
					return;

				}

				if (ScegliData_JC.getDate().after(DataScadenza_JC.getDate())) {
					JOptionPane.showMessageDialog(null, "Correggi le date inserite", "Errore",
							JOptionPane.ERROR_MESSAGE);
					controllo++;

				}

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

				if (Ambiti_Table.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "Inserisci almeno un ambito", "Errore ambito",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (controllo == 0) {

					int opzione = JOptionPane.showOptionDialog(null,
							"Vuoi modificare il progetto : " + Nome_TF.getText() + " ?", "Salvataggio",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Yes", "No" },
							JOptionPane.YES_OPTION);

					if (opzione == JOptionPane.YES_OPTION) {

						Progetto progetto = new Progetto();
						progetto.setCodProgetto(codp);

						progetto.setNomeProgetto(Nome_TF.getText());
						progetto.setTipologiaProgetto(Tipologia_TF.getText());
						progetto.setDescrizioneProgetto(Descrizione_JTP.getText());
						progetto.setDataInizioProgetto(ScegliData_JC.getDate());
						progetto.setDataScadenzaProgetto(DataScadenza_JC.getDate());
						progetto.setNomeCliente(NomeCliente_TF.getText());

						int nPartecipanti = modelPartecipanti.getRowCount();
						int count = 0;
						for (int n1 = 0; n1 < nPartecipanti; n1++) {

							if (PartecipantiProgetto_JT.getValueAt(n1, 5).equals("PROJECT MANAGER")) {
								count++;

							}

						}

						if (count == 0) {

							JOptionPane.showMessageDialog(null, "Non hai inserito nessun project manager", "Errore",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						try {

							c.SalvaModifiche(progetto, dipendenteDaEliminare, partecipantiDaAggiungere,
									ambitiDaEliminare, ambitiDaAggiungere);
							int risposta = JOptionPane.showOptionDialog(null, "Progetto salvato! Vuoi fare altro?",
									"Salvato", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
									new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);
							if (risposta == JOptionPane.YES_OPTION) {

								Tipologia_TF.setVisible(false);
								Nome_TF.setEditable(false);
								NomeCliente_TF.setEditable(false);
								Ambiti_TF.setEditable(false);
								ScegliData_JC.setEnabled(false);
								DataScadenza_JC.setEnabled(false);
								Descrizione_JTP.setEditable(false);
								ValutazionePartecipante_TF.setVisible(false);
								SalarioPartecipante_TF.setVisible(false);
								TipologiaPartecipante_TF.setVisible(false);
								AggiungiAmbiti_btn.setVisible(false);
								Salva_btm.setVisible(false);
								scrollPane_3.setVisible(false);
								RimuoviAmbito_btm.setVisible(false);
								CercaPartecipante_btm.setVisible(false);
								CancellaFiltri_btm.setVisible(false);
								scrollPane_2.setVisible(false);
								TipologiaPartecipante_JL.setVisible(false);
								ValutazionePartecipante_JL.setVisible(false);
								SalarioPartecipante_JL.setVisible(false);
								AggiungiPartecipante_btn.setVisible(false);
								RimuoviPartecipante_btn.setVisible(false);
								ElencoDipendenti_JT.setVisible(false);

							} else if (risposta == JOptionPane.NO_OPTION) {
								c.ChiudiVisualizzaModificaProgetto();
							}
						} catch (SQLException e1) {

							JOptionPane.showMessageDialog(null, e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
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
		Salva_btm.setHorizontalTextPosition(SwingConstants.LEFT);
		Salva_btm.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		Salva_btm.setVisible(false);
		Salva_btm.setFocusable(false);
		iconaSalva_btm = new ImageIcon((VisualizzaModificaProgettoGUI.class.getResource("/icon/iconaSalvataggio.png")));
		Salva_btm.setIcon(iconaSalva_btm);
		Salva_btm.setBounds(823, 643, 124, 39);
		contentPane.add(Salva_btm);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new LineBorder(Color.BLACK));
		scrollPane_1.setBounds(426, 45, 141, 123);
		contentPane.add(scrollPane_1);

		Ambiti_Table = new JTable(righe, colonne) {
			public boolean isCellEditable(int riga, int colonna) {
				return false;
			}
		};
		Ambiti_Table.setBorder(new LineBorder(Color.BLACK));
		scrollPane_1.setViewportView(Ambiti_Table);
		model = new DefaultTableModel();
		Ambiti_Table.setModel(model);
		Ambiti_Table.getTableHeader().setReorderingAllowed(false);

		Object[] column = { "Ambiti" };
		model.setColumnIdentifiers(column);
		c.RiempiAmbiti(model, progetto.getAmbiti());
		// riempimento tabella

		scrollPane = new JScrollPane();
		scrollPane.setBounds(658, 38, 289, 132);
		contentPane.add(scrollPane);

		Descrizione_JTP = new JTextArea();
		Descrizione_JTP.setBorder(new LineBorder(new Color(0, 0, 0)));
		Descrizione_JTP.setEditable(false);
		Descrizione_JTP.setText(progetto.getDescrizioneProgetto());
		scrollPane.setViewportView(Descrizione_JTP);

		TipologiaPartecipante_JL = new JLabel("Tipologia");
		TipologiaPartecipante_JL.setForeground(Color.LIGHT_GRAY);
		TipologiaPartecipante_JL.setVisible(false);
		TipologiaPartecipante_JL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		TipologiaPartecipante_JL.setBounds(599, 282, 65, 20);
		contentPane.add(TipologiaPartecipante_JL);

		ValutazionePartecipante_JL = new JLabel("Valutazione");
		ValutazionePartecipante_JL.setForeground(Color.LIGHT_GRAY);
		ValutazionePartecipante_JL.setVisible(false);
		ValutazionePartecipante_JL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ValutazionePartecipante_JL.setBounds(599, 375, 80, 14);
		contentPane.add(ValutazionePartecipante_JL);

		SalarioPartecipante_JL = new JLabel("Salario");
		SalarioPartecipante_JL.setForeground(Color.LIGHT_GRAY);
		SalarioPartecipante_JL.setVisible(false);
		SalarioPartecipante_JL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		SalarioPartecipante_JL.setBounds(599, 331, 65, 14);
		contentPane.add(SalarioPartecipante_JL);

		CercaPartecipante_btm = new JButton("Cerca");
		CercaPartecipante_btm.addActionListener(new ActionListener() {

			private ArrayList<Dipendente> dipendenti;

			public void actionPerformed(ActionEvent e) {

				if (!c.ControlloStringaVuota(TipologiaPartecipante_TF.getText())) {

					if (c.controlloExpRegNome(TipologiaPartecipante_TF.getText())) {

						TipologiaPartecipante_TF.setBackground(Color.white);

					} else {

						TipologiaPartecipante_TF.setBackground(Color.red);
						JOptionPane.showMessageDialog(null,
								"La tipologia non può avere numeri e deve avere massimo 20 caratteri",
								"Errore tipologia", JOptionPane.ERROR_MESSAGE);

						controllo++;
					}
				}
				if (!c.ControlloStringaVuota(SalarioPartecipante_TF.getText())) {
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
				if (!c.ControlloStringaVuota(ValutazionePartecipante_TF.getText())) {
					if (c.ControlloValutazione(ValutazionePartecipante_TF.getText())) {
						ValutazionePartecipante_TF.setBackground(Color.white);
					} else {
						ValutazionePartecipante_TF.setBackground(Color.red);
						JOptionPane.showMessageDialog(null, "La valutazione non può avere lettere ",
								"Errore valutazione", JOptionPane.ERROR_MESSAGE);
						controllo++;

					}
				}

				if (controllo == 0) {

					dipendenti = c.RecuperaPartecipantiProgettoFiltrati(TipologiaPartecipante_TF.getText(),
							SalarioPartecipante_TF.getText(), ValutazionePartecipante_TF.getText());
					c.riempiTableAggiungiDipendente(modelDipendenti, dipendenti);

				}

			}
		});
		CercaPartecipante_btm.setVisible(false);
		CercaPartecipante_btm.setHorizontalTextPosition(SwingConstants.LEFT);
		CercaPartecipante_btm.setFont(new Font("Tahoma", Font.PLAIN, 10));
		CercaPartecipante_btm.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		CercaPartecipante_btm.setToolTipText("Cerca partecipante");
		CercaPartecipante_btm.setFocusable(false);
		CercaPartecipante_btm.setBounds(198, 241, 124, 39);
		contentPane.add(CercaPartecipante_btm);
		iconaCercaPartecipante_btm = new ImageIcon(
				(VisualizzaModificaProgettoGUI.class.getResource("/icon/iconaCercaProgettobtm.png.png")));
		CercaPartecipante_btm.setIcon(iconaCercaPartecipante_btm);

		CancellaFiltri_btm = new JButton("Cancella i filtri");
		CancellaFiltri_btm.addActionListener(new ActionListener() {
			private ArrayList<Dipendente> dipendenti;

			public void actionPerformed(ActionEvent e) {
				TipologiaPartecipante_TF.setText("");
				ValutazionePartecipante_TF.setText("");
				SalarioPartecipante_TF.setText("");

				try {
					dipendenti = c.RecuperaDipendentiSenzaFiltri();
					c.riempiTableAggiungiDipendente(modelDipendenti, dipendenti);
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		});
		CancellaFiltri_btm.setVisible(false);
		CancellaFiltri_btm.setHorizontalTextPosition(SwingConstants.LEFT);
		CancellaFiltri_btm.setFont(new Font("Tahoma", Font.PLAIN, 10));
		CancellaFiltri_btm.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		CancellaFiltri_btm.setToolTipText("Cancella i filtri");
		CancellaFiltri_btm.setFocusable(false);
		CancellaFiltri_btm.setBounds(320, 241, 124, 39);
		contentPane.add(CancellaFiltri_btm);
		iconaCancellaFiltri_btm = new ImageIcon(
				(VisualizzaModificaProgettoGUI.class.getResource("/icon/iconaCancellaProgettobtm.png.png")));
		CancellaFiltri_btm.setIcon(iconaCancellaFiltri_btm);

		TipologiaPartecipante_TF = new JTextField();
		TipologiaPartecipante_TF.setBorder(new LineBorder(new Color(0, 0, 0)));
		TipologiaPartecipante_TF.setVisible(false);
		TipologiaPartecipante_TF.setColumns(10);
		TipologiaPartecipante_TF.setBounds(706, 284, 86, 20);
		contentPane.add(TipologiaPartecipante_TF);

		SalarioPartecipante_TF = new JTextField();
		SalarioPartecipante_TF.setBorder(new LineBorder(new Color(0, 0, 0)));
		SalarioPartecipante_TF.setVisible(false);
		SalarioPartecipante_TF.setColumns(10);
		SalarioPartecipante_TF.setBounds(706, 330, 86, 20);
		contentPane.add(SalarioPartecipante_TF);

		ValutazionePartecipante_TF = new JTextField();
		ValutazionePartecipante_TF.setBorder(new LineBorder(new Color(0, 0, 0)));
		ValutazionePartecipante_TF.setVisible(false);
		ValutazionePartecipante_TF.setColumns(10);
		ValutazionePartecipante_TF.setBounds(706, 374, 86, 20);
		contentPane.add(ValutazionePartecipante_TF);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBorder(new LineBorder(Color.BLACK));
		scrollPane_2.setBounds(10, 282, 557, 146);
		contentPane.add(scrollPane_2);
		scrollPane_2.setVisible(false);

		ElencoDipendenti_JT = new JTable(righe, colonne) {
			public boolean isCellEditable(int riga, int colonna) {
				return false;
			}
		};
		ElencoDipendenti_JT.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane_2.setViewportView(ElencoDipendenti_JT);
		modelDipendenti = new DefaultTableModel();
		Object[] column2 = { "Codice Fiscale", "Nome", "Cognome", "Valutazione", "Salario" };
		Object[] row2 = new Object[5];
		modelDipendenti.setColumnIdentifiers(column2);

		ElencoDipendenti_JT.setModel(modelDipendenti);
		ElencoDipendenti_JT.getTableHeader().setReorderingAllowed(false);
		c.riempiTableAggiungiDipendente(modelDipendenti, dipendenteDaAggiungere);
		ElencoDipendenti_JT.setVisible(false);

		Modifica_btm = new JButton("Modifica");
		Modifica_btm.setHorizontalTextPosition(SwingConstants.LEFT);
		Modifica_btm.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		Modifica_btm.setFocusable(false);
		iconaModifica_btm = new ImageIcon((VisualizzaModificaProgettoGUI.class.getResource("/icon/iconaModifica.png")));
		Modifica_btm.setIcon(iconaModifica_btm);
		Modifica_btm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Tipologia_TF.setEditable(true);
				Nome_TF.setEditable(true);
				NomeCliente_TF.setEditable(true);
				Ambiti_TF.setEditable(true);
				ScegliData_JC.setEnabled(true);
				DataScadenza_JC.setEnabled(true);
				Descrizione_JTP.setEditable(true);
				ValutazionePartecipante_TF.setVisible(true);
				SalarioPartecipante_TF.setVisible(true);
				TipologiaPartecipante_TF.setVisible(true);
				AggiungiAmbiti_btn.setVisible(true);
				Salva_btm.setVisible(true);
				scrollPane_3.setVisible(true);
				RimuoviAmbito_btm.setVisible(true);
				CercaPartecipante_btm.setVisible(true);
				CancellaFiltri_btm.setVisible(true);
				scrollPane_2.setVisible(true);
				TipologiaPartecipante_JL.setVisible(true);
				ValutazionePartecipante_JL.setVisible(true);
				SalarioPartecipante_JL.setVisible(true);
				AggiungiPartecipante_btn.setVisible(true);
				RimuoviPartecipante_btn.setVisible(true);
				ElencoDipendenti_JT.setVisible(true);
				Completa_btn.setVisible(true);

			}
		});
		Modifica_btm.setBounds(700, 643, 124, 39);
		contentPane.add(Modifica_btm);

		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBorder(new LineBorder(Color.BLACK));
		scrollPane_3.setBounds(10, 486, 557, 146);
		contentPane.add(scrollPane_3);

		PartecipantiProgetto_JT = new JTable(righe, colonne) {
			public boolean isCellEditable(int riga, int colonna) {
				return false;
			}
		};
		PartecipantiProgetto_JT.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane_3.setViewportView(PartecipantiProgetto_JT);
		scrollPane_3.setVisible(true);
		modelPartecipanti = new DefaultTableModel();
		Object[] column1 = { "Codice Fiscale", "Nome", "Cognome", "Valutazione", "Salario", "Ruolo" };
		Object[] row = new Object[6];
		modelPartecipanti.setColumnIdentifiers(column1);
		PartecipantiProgetto_JT.setModel(modelPartecipanti);

		RimuoviAmbito_btm = new JButton("");
		RimuoviAmbito_btm.setVisible(false);
		iconaCancellaAmbiti_btm = new ImageIcon(
				(VisualizzaModificaProgettoGUI.class.getResource("/icon/iconaCancellaProgettobtm.png.png")));
		RimuoviAmbito_btm.setIcon(iconaCancellaAmbiti_btm);
		RimuoviAmbito_btm.setFocusable(false);
		RimuoviAmbito_btm.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		RimuoviAmbito_btm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int i = Ambiti_Table.getSelectedRow();
				if (i >= 0) {
					Ambito ambito = new Ambito();
					String ambitoEliminato = Ambiti_Table.getValueAt(i, 0).toString();
					ambito.setNomeAmbito(ambitoEliminato);

					if (ambitiDaAggiungere != null) {
						int dim = ambitiDaAggiungere.size();

						if (dim != 0) {
							for (Iterator<Ambito> a = ambitiDaAggiungere.iterator(); a.hasNext();) {
								Ambito amb = a.next();
								if (amb.getNomeAmbito().equals(ambito.getNomeAmbito())) {
									a.remove();
								}

							}
						}
					}
					model.removeRow(i);

				} else {
					JOptionPane.showMessageDialog(null, "Nessun ambito selezionato", "Errore",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		RimuoviAmbito_btm.setBounds(542, 25, 25, 23);
		contentPane.add(RimuoviAmbito_btm);

		AggiungiPartecipante_btn = new JButton("Aggiungi");
		AggiungiPartecipante_btn.addActionListener(new ActionListener() {
			private Dipendente dipendente;

			public void actionPerformed(ActionEvent e) {

				int i = ElencoDipendenti_JT.getSelectedRow();

				if (i >= 0) {

					dipendente = new Dipendente();

					String ruoloP = JOptionPane.showInputDialog(null, "Inserisci il ruolo con il quale partecipa",
							"Ruolo", JOptionPane.INFORMATION_MESSAGE);
					if (c.ControlloStringaVuota(ruoloP)) {
						JOptionPane.showMessageDialog(null, "Nessun ruolo inserito", "Errore",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					String ruolo = ruoloP.toUpperCase();

					dipendente.setCodiceFiscale(ElencoDipendenti_JT.getValueAt(i, 0).toString());
					dipendente.setNome(ElencoDipendenti_JT.getValueAt(i, 1).toString());
					dipendente.setCognome(ElencoDipendenti_JT.getValueAt(i, 2).toString());
					dipendente.setValutazione(Float.parseFloat(ElencoDipendenti_JT.getValueAt(i, 3).toString()));
					dipendente.setSalarioMedio(Double.valueOf(ElencoDipendenti_JT.getValueAt(i, 4).toString()));

					int nPartecipanti = modelPartecipanti.getRowCount();

					for (int n = 0; n < nPartecipanti; n++) {

						if (ruolo.equals("PROJECT MANAGER")) {

							if (PartecipantiProgetto_JT.getValueAt(n, 5).equals("PROJECT MANAGER")) {
								JOptionPane.showMessageDialog(null, "Project manager già inserito", "Errore",
										JOptionPane.ERROR_MESSAGE);
								return;
							}
						}

						if (PartecipantiProgetto_JT.getValueAt(n, 0).equals(dipendente.getCodiceFiscale())) {
							JOptionPane.showMessageDialog(null, "Dipendente già inserito", "Errore",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

					}

					PartecipanteProgetto partecipante = new PartecipanteProgetto();

					Progetto progettoNuovoPartecipante = new Progetto();
					progettoNuovoPartecipante.setCodProgetto(progetto.getCodProgetto());
					partecipante.setProgetto(progettoNuovoPartecipante);
					partecipante.setDipendente(dipendente);
					partecipante.setRuolo(ruolo);

					partecipantiDaAggiungere.add(partecipante);

					if (dipendenteDaEliminare != null) {
						int dim = dipendenteDaEliminare.size();
						if (dim != 0) {
							for (Iterator<PartecipanteProgetto> p = dipendenteDaEliminare.iterator(); p.hasNext();) {
								PartecipanteProgetto par = p.next();
								if (par.getDipendente().getCodiceFiscale().equals(dipendente.getCodiceFiscale())) {
									p.remove();
								}
							}

						}
					}

					c.RiempiTablePartecipanti(modelPartecipanti, dipendente, ruolo);

				} else {
					JOptionPane.showMessageDialog(null, "Nessun dipendente selezionato", "Errore",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		AggiungiPartecipante_btn.setVisible(false);
		AggiungiPartecipante_btn.setHorizontalTextPosition(SwingConstants.LEFT);
		iconaAggiungiPartecipante_btm = new ImageIcon(
				(VisualizzaModificaProgettoGUI.class.getResource("/icon/iconaAggiungiPartecipante.png")));
		AggiungiPartecipante_btn.setIcon(iconaAggiungiPartecipante_btm);
		AggiungiPartecipante_btn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		AggiungiPartecipante_btn.setBounds(443, 241, 124, 39);
		contentPane.add(AggiungiPartecipante_btn);

		RimuoviPartecipante_btn = new JButton("Rimuovi");
		iconaCancellaDipendente_btm = new ImageIcon(
				(VisualizzaModificaProgettoGUI.class.getResource("/icon/iconaCancellaProgettobtm.png.png")));
		RimuoviPartecipante_btn.setIcon(iconaCancellaDipendente_btm);
		RimuoviPartecipante_btn.setHorizontalTextPosition(SwingConstants.LEFT);
		RimuoviPartecipante_btn.setFocusable(false);
		RimuoviPartecipante_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int i = PartecipantiProgetto_JT.getSelectedRow();
				if (i >= 0) {
					String nome = PartecipantiProgetto_JT.getValueAt(i, 1).toString();
					String cognome = PartecipantiProgetto_JT.getValueAt(i, 2).toString();
					int opzione = JOptionPane.showOptionDialog(null,
							"Vuoi rimuovere  " + cognome + " " + nome + " dal progetto?", "Rimuovi",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Yes", "No" },
							JOptionPane.YES_OPTION);
					if (opzione == JOptionPane.YES_OPTION) {

						Dipendente dipendente = new Dipendente();
						dipendente.setCodiceFiscale(PartecipantiProgetto_JT.getValueAt(i, 0).toString());

						PartecipanteProgetto partecipante = new PartecipanteProgetto();

						Progetto progettoNuovoPartecipante = new Progetto();
						progettoNuovoPartecipante.setCodProgetto(progetto.getCodProgetto());
						partecipante.setProgetto(progettoNuovoPartecipante);
						partecipante.setDipendente(dipendente);

						dipendenteDaEliminare.add(partecipante);

						if (partecipantiDaAggiungere != null) {
							int dim = partecipantiDaAggiungere.size();
							if (dim != 0) {
								for (Iterator<PartecipanteProgetto> p = partecipantiDaAggiungere.iterator(); p
										.hasNext();) {
									PartecipanteProgetto par = p.next();
									if (par.getDipendente().getCodiceFiscale().equals(dipendente.getCodiceFiscale())) {
										p.remove();
									}
								}
							}
						}

						modelPartecipanti.removeRow(i);

					} else {
						return;
					}

				} else {
					JOptionPane.showMessageDialog(null, "Nessun dipendente selezionato", "Errore",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		RimuoviPartecipante_btn.setVisible(false);
		RimuoviPartecipante_btn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		RimuoviPartecipante_btn.setBounds(320, 446, 124, 39);
		contentPane.add(RimuoviPartecipante_btn);

		Completa_btn = new JButton("Completa");
		Completa_btn.setHorizontalTextPosition(SwingConstants.LEFT);
		Completa_btn.setToolTipText("Imposta il progetto come completato");
		Completa_btn.setVisible(false);
		iconaCompleta_btm = new ImageIcon((VisualizzaModificaProgettoGUI.class.getResource("/icon/checked.png")));
		Completa_btn.setIcon(iconaCompleta_btm);
		Completa_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					c.CompletaProgetto(codp);
					int opzione = JOptionPane.showOptionDialog(null,
							"Vuoi completare il progetto : " + Nome_TF.getText() + " ?", "Completa",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Yes", "No" },
							JOptionPane.YES_OPTION);

					if (opzione == JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(null, "Progetto completato con successo", "Completato",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		Completa_btn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		Completa_btn.setFocusable(false);
		Completa_btn.setBounds(443, 446, 124, 39);
		contentPane.add(Completa_btn);
		PartecipantiProgetto_JT.getTableHeader().setReorderingAllowed(false);
		c.RiempiTablePartecipanti(modelPartecipanti, progetto.getPartecipantiProgetto());
	}
}
