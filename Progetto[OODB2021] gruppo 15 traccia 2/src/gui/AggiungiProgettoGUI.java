package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import controller.Controller;
import entity.Ambito;
import entity.Dipendente;
import entity.PartecipanteProgetto;
import entity.Progetto;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

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
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

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
	private DefaultTableModel model2;

	private int controllo = 0;
	private JLabel TipologiaPartecipante_JL;
	private JLabel ValutazionePartecipante_JL;
	private JLabel SalarioPartecipante_JL;
	private ArrayList<Dipendente> dipendenti;

	Date data = new Date();
	private JButton CercaPartecipante_btm;
	private JButton CancellaFiltri_btm;
	private JTextField TipologiaPartecipante_TF;
	private JTextField SalarioPartecipante_TF;
	private JTextField ValutazionePartecipante_TF;
	private JTable ElencoDipendenti_JT;
	private JScrollPane scrollPane;
	private TableModel righe;
	private TableColumnModel colonne;
	private ImageIcon iconaIndietro_btm;
	private ImageIcon iconaCercaPartecipante_btm;
	private ImageIcon iconaCancellaFiltri_btm;
	private ImageIcon iconaSalva_btm;
	private JButton Salva_btm;
	private JTable ListaPartecipantiProgetto_JT;
	private ImageIcon iconaAggiungiAmbito_btm;
	private JButton AggiungiAmbiti_btn;
	private JButton AggiungiPartecipante_btm;
	private DefaultTableModel model5;
	private ImageIcon iconaAggiungiPartecipante_btm;
	private Dipendente dipendente;
	private ArrayList<PartecipanteProgetto> partecipantiProgetto;
	private PartecipanteProgetto partecipanteProgetto;
	private JButton SvuotaPartecipanti_btm;
	private JButton RimuoviAmbito_btm;
	private ImageIcon iconaCancellaAmbiti_btm;
	private JButton RimuoviPartecipante_btn;
	private ImageIcon iconaSvuotaAmbiti_btm;
	private ImageIcon iconaRimuoviPartecipante_btm;

	public AggiungiProgettoGUI(Controller c) {

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				int a = JOptionPane.showConfirmDialog(null, "Attenzione, se confermi perderai i dati inseriti",
						"Attenzione", JOptionPane.WARNING_MESSAGE);
				if (a == 0) {
					c.ChiudiAggiungiProgettoSenzaDialog();
				}
			}
		});

		try {
			this.dipendenti = c.RecuperaDipendentiSenzaFiltri();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		setIconImage(
				Toolkit.getDefaultToolkit().getImage(AggiungiProgettoGUI.class.getResource("/icon/iconaFrame.png")));
		theController = c;
		setTitle("Aggiungi progetto");

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 982, 705);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton Indietro_btm = new JButton("");
		iconaIndietro_btm = new ImageIcon((AggiungiProgettoGUI.class.getResource("/icon/iconaIndietrobtm.png.png")));
		Indietro_btm.setIcon(iconaIndietro_btm);
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
		ImageIcon iconaIndietro_btm = new ImageIcon(
				"C:\\Users\\Gianpietro\\Desktop\\GP\\Uni\\imm\\iconaIndietrobtm.png");
		Indietro_btm.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		Indietro_btm.setFocusable(false);
		Indietro_btm.setBounds(10, 617, 103, 37);
		contentPane.add(Indietro_btm);

		Nome_JL = new JLabel("Nome");
		Nome_JL.setForeground(Color.LIGHT_GRAY);
		Nome_JL.setBackground(Color.BLACK);
		Nome_JL.setToolTipText("");
		Nome_JL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Nome_JL.setBounds(10, 6, 64, 37);
		contentPane.add(Nome_JL);
		Nome_JL.setToolTipText(null);

		Tipologia_JL = new JLabel("Tipologia");
		Tipologia_JL.setForeground(Color.LIGHT_GRAY);
		Tipologia_JL.setToolTipText("");
		Tipologia_JL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Tipologia_JL.setBounds(10, 40, 64, 37);
		contentPane.add(Tipologia_JL);
		Tipologia_JL.setToolTipText(null);

		Tipologia_TF = new JTextField();
		Tipologia_TF.setBorder(new LineBorder(Color.BLACK));
		Tipologia_TF.setToolTipText("Inserisci la tipologia del progetto.");
		Tipologia_TF.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Tipologia_TF.setBounds(132, 48, 86, 20);
		contentPane.add(Tipologia_TF);
		Tipologia_TF.setColumns(10);

		Ambiti_JL = new JLabel("Ambiti");
		Ambiti_JL.setForeground(Color.LIGHT_GRAY);
		Ambiti_JL.setToolTipText("");
		Ambiti_JL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Ambiti_JL.setBounds(344, 22, 57, 14);
		contentPane.add(Ambiti_JL);
		Ambiti_JL.setToolTipText(null);

		Ambiti_TF = new JTextField();
		Ambiti_TF.setBorder(new LineBorder(Color.BLACK));

		Ambiti_TF.setToolTipText("Inserisci gli ambiti del progetto.");
		Ambiti_TF.setBounds(430, 20, 86, 23);
		contentPane.add(Ambiti_TF);
		Ambiti_TF.setColumns(10);

		AggiungiAmbiti_btn = new JButton("");
		AggiungiAmbiti_btn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		iconaAggiungiAmbito_btm = new ImageIcon(
				(VisualizzaModificaProgettoGUI.class.getResource("/icon/iconaAggiungiProgettobtm.png.png")));
		AggiungiAmbiti_btn.setIcon(iconaAggiungiAmbito_btm);
		AggiungiAmbiti_btn.setToolTipText("Aggiungi ambito");
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
				model.addRow(new Object[] { Ambiti_TF.getText().toUpperCase() });
				Ambiti_TF.setText("");
			}
		});

		AggiungiAmbiti_btn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		AggiungiAmbiti_btn.setBounds(517, 20, 25, 23);
		contentPane.add(AggiungiAmbiti_btn);
		AggiungiAmbiti_btn.setFocusable(false);

		DataInizio_JL = new JLabel("Data Inizio");
		DataInizio_JL.setForeground(Color.LIGHT_GRAY);
		DataInizio_JL.setToolTipText("");
		DataInizio_JL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DataInizio_JL.setBounds(10, 88, 77, 14);
		contentPane.add(DataInizio_JL);
		DataInizio_JL.setToolTipText("Inserisci la data di inizio del progetto");

		DataScadenza_JL = new JLabel("Data Scadenza");
		DataScadenza_JL.setForeground(Color.LIGHT_GRAY);
		DataScadenza_JL.setToolTipText("Inserisci la data di scadenza del progetto.");
		DataScadenza_JL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DataScadenza_JL.setBounds(10, 116, 103, 14);
		contentPane.add(DataScadenza_JL);

		ScegliData_JC = new JDateChooser();
		ScegliData_JC.setBorder(new LineBorder(new Color(0, 0, 0)));
		ScegliData_JC.setBounds(132, 89, 108, 20);
		contentPane.add(ScegliData_JC);
		ScegliData_JC.setDate(data);

		DataScadenza_JC = new JDateChooser();
		DataScadenza_JC.setBorder(new LineBorder(new Color(0, 0, 0)));
		DataScadenza_JC.setBounds(132, 116, 108, 20);
		contentPane.add(DataScadenza_JC);
		DataScadenza_JC.setDate(data);

		NomeCliente_JL = new JLabel("Nome Cliente");
		NomeCliente_JL.setForeground(Color.LIGHT_GRAY);
		NomeCliente_JL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		NomeCliente_JL.setToolTipText("");
		NomeCliente_JL.setBounds(10, 151, 86, 14);
		contentPane.add(NomeCliente_JL);
		NomeCliente_JL.setToolTipText(null);

		NomeCliente_TF = new JTextField();
		NomeCliente_TF.setBorder(new LineBorder(Color.BLACK));
		NomeCliente_TF.setToolTipText("Inserisci il nome di chi ha commissionato il progetto.");
		NomeCliente_TF.setBounds(132, 150, 86, 20);
		contentPane.add(NomeCliente_TF);
		NomeCliente_TF.setColumns(10);

		Descrizione_JL = new JLabel("Descrizione");
		Descrizione_JL.setForeground(Color.LIGHT_GRAY);
		Descrizione_JL.setToolTipText("");
		Descrizione_JL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Descrizione_JL.setBounds(652, 17, 92, 14);
		contentPane.add(Descrizione_JL);
		Descrizione_JL.setToolTipText(null);

		Nome_TF = new JTextField();
		Nome_TF.setBorder(new LineBorder(Color.BLACK));
		Nome_TF.setToolTipText("Inserisci il nome del progetto.");
		Nome_TF.setBounds(132, 16, 86, 20);
		contentPane.add(Nome_TF);
		Nome_TF.setColumns(10);

		Salva_btm = new JButton("Salva");
		Salva_btm.setToolTipText("Salva il progetto");
		Salva_btm.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		Salva_btm.setHorizontalTextPosition(SwingConstants.LEFT);
		Salva_btm.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!c.ControllaCampiVuotiAggiungiProgetto(Nome_TF.getText(), Descrizione_JTP.getText(),
						NomeCliente_TF.getText(), Tipologia_TF.getText())) {
					JOptionPane.showMessageDialog(null, "Riempi tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (ListaPartecipantiProgetto_JT.getRowCount() == 0) {
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
							"Vuoi salvare " + Nome_TF.getText() + " come progetto?", "Salvataggio",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Yes", "No" },
							JOptionPane.YES_OPTION);

					if (opzione == JOptionPane.YES_OPTION) {

						Progetto progetto = new Progetto();

						progetto.setNomeProgetto(Nome_TF.getText());
						progetto.setTipologiaProgetto(Tipologia_TF.getText());
						progetto.setDescrizioneProgetto(Descrizione_JTP.getText());
						progetto.setDataInizioProgetto(ScegliData_JC.getDate());
						progetto.setDataScadenzaProgetto(DataScadenza_JC.getDate());
						progetto.setNomeCliente(NomeCliente_TF.getText());

						ArrayList<Ambito> ambiti = new ArrayList<Ambito>();
						int n = Ambiti_Table.getRowCount();
						for (int i = 0; i < n; i++) {
							Ambito ambito = new Ambito();
							ambito.setNomeAmbito(Ambiti_Table.getValueAt(i, 0).toString());
							ambiti.add(ambito);

						}
						progetto.setAmbiti(ambiti);
						partecipantiProgetto = new ArrayList<PartecipanteProgetto>();
						int b = ListaPartecipantiProgetto_JT.getRowCount();
						for (int i = 0; i < b; i++) {

							partecipanteProgetto = new PartecipanteProgetto();
							Dipendente dipendente = new Dipendente();
							String cf = ListaPartecipantiProgetto_JT.getValueAt(i, 0).toString();
							String ruolo = ListaPartecipantiProgetto_JT.getValueAt(i, 5).toString();
							dipendente.setCodiceFiscale(cf);
							partecipanteProgetto.setRuolo(ruolo);
							partecipanteProgetto.setDipendente(dipendente);

							partecipantiProgetto.add(partecipanteProgetto);

						}
						int nPartecipanti = model5.getRowCount();
						int count = 0;
						for (int n1 = 0; n1 < nPartecipanti; n1++) {

							if (ListaPartecipantiProgetto_JT.getValueAt(n1, 5).equals("PROJECT MANAGER")) {
								count++;

							}

						}

						if (count == 0) {

							JOptionPane.showMessageDialog(null, "Non hai inserito nessun project manager", "Errore",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

						try {
							c.SalvaProgetto(progetto, partecipantiProgetto);
							int risposta = JOptionPane.showOptionDialog(null,
									"Progetto salvato! Vuoi aggiungere un altro progetto?", "Salvato",
									JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
									new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);
							if (risposta == JOptionPane.YES_OPTION) {

								Nome_TF.setText("");
								Tipologia_TF.setText("");
								Descrizione_JTP.setText("");
								NomeCliente_TF.setText("");
								ScegliData_JC.setDate(data);
								DataScadenza_JC.setDate(data);
								Ambiti_TF.setText("");
								model.setRowCount(0);
								TipologiaPartecipante_TF.setText("");
								SalarioPartecipante_TF.setText("");
								ValutazionePartecipante_TF.setText("");
								model5.setRowCount(0);

							} else if (risposta == JOptionPane.NO_OPTION) {
								c.TornaProgettoGUIDaAggiungiProgetto();
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
		Salva_btm.setFocusable(false);
		iconaSalva_btm = new ImageIcon((VisualizzaModificaProgettoGUI.class.getResource("/icon/iconaSalvataggio.png")));
		Salva_btm.setIcon(iconaSalva_btm);
		Salva_btm.setBounds(817, 617, 124, 39);
		contentPane.add(Salva_btm);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new LineBorder(Color.BLACK));
		scrollPane_1.setBounds(430, 43, 137, 127);
		contentPane.add(scrollPane_1);

		Ambiti_Table = new JTable(righe, colonne) {
			public boolean isCellEditable(int riga, int colonna) {
				return false;
			}
		};
		Ambiti_Table.setBorder(new LineBorder(Color.BLACK));
		scrollPane_1.setViewportView(Ambiti_Table);
		model2 = new DefaultTableModel();
		Ambiti_Table.setModel(model2);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(652, 40, 289, 127);
		contentPane.add(scrollPane);

		Descrizione_JTP = new JTextArea();
		Descrizione_JTP.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(Descrizione_JTP);

		TipologiaPartecipante_JL = new JLabel("Tipologia");
		TipologiaPartecipante_JL.setVisible(false);
		TipologiaPartecipante_JL.setForeground(Color.LIGHT_GRAY);
		TipologiaPartecipante_JL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		TipologiaPartecipante_JL.setBounds(577, 241, 65, 20);
		contentPane.add(TipologiaPartecipante_JL);

		ValutazionePartecipante_JL = new JLabel("Valutazione");
		ValutazionePartecipante_JL.setVisible(false);
		ValutazionePartecipante_JL.setForeground(Color.LIGHT_GRAY);
		ValutazionePartecipante_JL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ValutazionePartecipante_JL.setBounds(577, 334, 80, 14);
		contentPane.add(ValutazionePartecipante_JL);

		SalarioPartecipante_JL = new JLabel("Salario");
		SalarioPartecipante_JL.setVisible(false);
		SalarioPartecipante_JL.setForeground(Color.LIGHT_GRAY);
		SalarioPartecipante_JL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		SalarioPartecipante_JL.setBounds(577, 290, 65, 14);
		contentPane.add(SalarioPartecipante_JL);

		Ambiti_Table.getTableHeader().setReorderingAllowed(false);

		Object[] column = { "Ambiti" };
		model2.setColumnIdentifiers(column);

		CercaPartecipante_btm = new JButton("Cerca");
		CercaPartecipante_btm.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		CercaPartecipante_btm.setHorizontalTextPosition(SwingConstants.LEFT);
		iconaCercaPartecipante_btm = new ImageIcon(
				(VisualizzaModificaProgettoGUI.class.getResource("/icon/iconaCercaProgettobtm.png.png")));
		CercaPartecipante_btm.setIcon(iconaCercaPartecipante_btm);
		CercaPartecipante_btm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TipologiaPartecipante_JL.setVisible(true);
				TipologiaPartecipante_TF.setVisible(true);
				SalarioPartecipante_JL.setVisible(true);
				SalarioPartecipante_TF.setVisible(true);
				ValutazionePartecipante_JL.setVisible(true);
				ValutazionePartecipante_TF.setVisible(true);

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
					c.riempiTableAggiungiDipendente(model, dipendenti);

				}

			}
		});
		CercaPartecipante_btm.setToolTipText("");
		CercaPartecipante_btm.setFocusable(false);
		CercaPartecipante_btm.setBounds(196, 197, 124, 39);
		contentPane.add(CercaPartecipante_btm);
		// ImageIcon iconaCercaPartecipante = new
		// ImageIcon("C:\\Users\\Gianpietro\\Desktop\\GP\\Uni\\imm\\iconaCercaProgettobtm.png");

		CancellaFiltri_btm = new JButton("Elimina filtri");
		CancellaFiltri_btm.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		CancellaFiltri_btm.setHorizontalTextPosition(SwingConstants.LEFT);
		CancellaFiltri_btm.setFont(new Font("Tahoma", Font.PLAIN, 10));
		iconaCancellaFiltri_btm = new ImageIcon(
				(VisualizzaModificaProgettoGUI.class.getResource("/icon/iconaCancellaProgettobtm.png.png")));
		CancellaFiltri_btm.setIcon(iconaCancellaFiltri_btm);
		CancellaFiltri_btm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TipologiaPartecipante_JL.setVisible(false);
				TipologiaPartecipante_TF.setVisible(false);
				SalarioPartecipante_JL.setVisible(false);
				SalarioPartecipante_TF.setVisible(false);
				ValutazionePartecipante_JL.setVisible(false);
				ValutazionePartecipante_TF.setVisible(false);

				TipologiaPartecipante_TF.setText("");
				ValutazionePartecipante_TF.setText("");
				SalarioPartecipante_TF.setText("");

				try {
					dipendenti = c.RecuperaDipendentiSenzaFiltri();
					c.riempiTableAggiungiDipendente(model, dipendenti);
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		});

		CancellaFiltri_btm.setToolTipText("");
		CancellaFiltri_btm.setFocusable(false);
		CancellaFiltri_btm.setBounds(319, 197, 124, 39);
		contentPane.add(CancellaFiltri_btm);

		TipologiaPartecipante_TF = new JTextField();
		TipologiaPartecipante_TF.setVisible(false);
		TipologiaPartecipante_TF.setBorder(new LineBorder(Color.BLACK));
		TipologiaPartecipante_TF.setColumns(10);
		TipologiaPartecipante_TF.setBounds(657, 243, 86, 20);
		contentPane.add(TipologiaPartecipante_TF);

		SalarioPartecipante_TF = new JTextField();
		SalarioPartecipante_TF.setVisible(false);
		SalarioPartecipante_TF.setBorder(new LineBorder(Color.BLACK));
		SalarioPartecipante_TF.setColumns(10);
		SalarioPartecipante_TF.setBounds(657, 289, 86, 20);
		contentPane.add(SalarioPartecipante_TF);

		ValutazionePartecipante_TF = new JTextField();
		ValutazionePartecipante_TF.setVisible(false);
		ValutazionePartecipante_TF.setBorder(new LineBorder(Color.BLACK));
		ValutazionePartecipante_TF.setColumns(10);
		ValutazionePartecipante_TF.setBounds(657, 333, 86, 20);
		contentPane.add(ValutazionePartecipante_TF);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBorder(new LineBorder(Color.BLACK));
		scrollPane_2.setBounds(10, 241, 557, 146);
		contentPane.add(scrollPane_2);

		ElencoDipendenti_JT = new JTable(righe, colonne) {
			public boolean isCellEditable(int riga, int colonna) {
				return false;
			}
		};
		ElencoDipendenti_JT.setBorder(new LineBorder(new Color(0, 0, 0)));

		scrollPane_2.setViewportView(ElencoDipendenti_JT);
		model = new DefaultTableModel();
		ElencoDipendenti_JT.setModel(model);

		AggiungiPartecipante_btm = new JButton("Aggiungi");
		AggiungiPartecipante_btm.setToolTipText("Aggiungi partecipante selezionato");
		AggiungiPartecipante_btm.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		AggiungiPartecipante_btm.setHorizontalTextPosition(SwingConstants.LEFT);
		iconaAggiungiPartecipante_btm = new ImageIcon(
				(VisualizzaModificaProgettoGUI.class.getResource("/icon/iconaAggiungiPartecipante.png")));
		AggiungiPartecipante_btm.setIcon(iconaAggiungiPartecipante_btm);
		AggiungiPartecipante_btm.setFocusable(false);
		AggiungiPartecipante_btm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int i = ElencoDipendenti_JT.getSelectedRow();

				if (i >= 0) {

					dipendente = new Dipendente();

					String ruolo = JOptionPane.showInputDialog(null, "Inserisci il ruolo con il quale partecipa",
							"Ruolo", JOptionPane.INFORMATION_MESSAGE).toUpperCase();
					if (c.ControlloStringaVuota(ruolo)) {
						JOptionPane.showMessageDialog(null, "Nessun ruolo inserito", "Errore",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					dipendente.setCodiceFiscale(ElencoDipendenti_JT.getValueAt(i, 0).toString());
					dipendente.setNome(ElencoDipendenti_JT.getValueAt(i, 1).toString());
					dipendente.setCognome(ElencoDipendenti_JT.getValueAt(i, 2).toString());
					dipendente.setValutazione(Float.parseFloat(ElencoDipendenti_JT.getValueAt(i, 3).toString()));
					dipendente.setSalarioMedio(Double.valueOf(ElencoDipendenti_JT.getValueAt(i, 4).toString()));

					int nPartecipanti = model5.getRowCount();

					for (int n = 0; n < nPartecipanti; n++) {

						if (ruolo.equals("PROJECT MANAGER")) {

							if (ListaPartecipantiProgetto_JT.getValueAt(n, 5).equals("PROJECT MANAGER")) {
								JOptionPane.showMessageDialog(null, "Project manager già inserito", "Errore",
										JOptionPane.ERROR_MESSAGE);
								return;
							}
						}

						if (ListaPartecipantiProgetto_JT.getValueAt(n, 0).equals(dipendente.getCodiceFiscale())) {
							JOptionPane.showMessageDialog(null, "Dipendente già inserito", "Errore",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

					}

					c.RiempiTablePartecipanti(model5, dipendente, ruolo);

				} else {
					JOptionPane.showMessageDialog(null, "Nessun dipendente selezionato", "Errore",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		AggiungiPartecipante_btm.setBounds(443, 197, 124, 39);
		contentPane.add(AggiungiPartecipante_btm);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBorder(new LineBorder(Color.BLACK));
		scrollPane_3.setBounds(10, 439, 557, 146);
		contentPane.add(scrollPane_3);

		model5 = new DefaultTableModel();
		ListaPartecipantiProgetto_JT = new JTable(righe, colonne) {
			public boolean isCellEditable(int riga, int colonna) {
				return false;
			}
		};
		ListaPartecipantiProgetto_JT.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane_3.setViewportView(ListaPartecipantiProgetto_JT);
		Object[] column1 = { "Codice Fiscale", "Nome", "Cognome", "Valutazione", "Salario", "Ruolo" };
		Object[] row = new Object[6];
		model5.setColumnIdentifiers(column1);
		ListaPartecipantiProgetto_JT.setModel(model5);

		SvuotaPartecipanti_btm = new JButton("Svuota tabella");
		SvuotaPartecipanti_btm.setHorizontalTextPosition(SwingConstants.LEFT);
		SvuotaPartecipanti_btm.setFont(new Font("Tahoma", Font.PLAIN, 10));
		iconaSvuotaAmbiti_btm = new ImageIcon((VisualizzaModificaProgettoGUI.class.getResource("/icon/trash.png")));
		SvuotaPartecipanti_btm.setIcon(iconaSvuotaAmbiti_btm);
		SvuotaPartecipanti_btm.setFocusable(false);
		SvuotaPartecipanti_btm.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		SvuotaPartecipanti_btm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model5.setRowCount(0);
			}
		});
		SvuotaPartecipanti_btm.setBounds(353, 398, 108, 37);
		contentPane.add(SvuotaPartecipanti_btm);
		ListaPartecipantiProgetto_JT.getTableHeader().setReorderingAllowed(false);

		ElencoDipendenti_JT.getTableHeader().setReorderingAllowed(false);
		c.riempiTableAggiungiDipendente(model, dipendenti);

		RimuoviAmbito_btm = new JButton("");
		RimuoviAmbito_btm.setFocusable(false);
		iconaCancellaAmbiti_btm = new ImageIcon(
				(VisualizzaModificaProgettoGUI.class.getResource("/icon/iconaCancellaProgettobtm.png.png")));
		RimuoviAmbito_btm.setIcon(iconaCancellaAmbiti_btm);
		RimuoviAmbito_btm.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		RimuoviAmbito_btm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = Ambiti_Table.getSelectedRow();
				if (i >= 0) {
					model.removeRow(i);
				} else {
					JOptionPane.showMessageDialog(null, "Nessun ambito selezionato", "Errore",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		RimuoviAmbito_btm.setBounds(542, 20, 25, 23);
		contentPane.add(RimuoviAmbito_btm);

		RimuoviPartecipante_btn = new JButton("Rimuovi");
		RimuoviPartecipante_btn.setHorizontalTextPosition(SwingConstants.LEFT);
		iconaRimuoviPartecipante_btm = new ImageIcon(
				(VisualizzaModificaProgettoGUI.class.getResource("/icon/iconaCancellaProgettobtm.png.png")));
		RimuoviPartecipante_btn.setIcon(iconaRimuoviPartecipante_btm);
		RimuoviPartecipante_btn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		RimuoviPartecipante_btn.setToolTipText("Rimuovi partecipante dalla tabella");
		RimuoviPartecipante_btn.setFocusable(false);
		RimuoviPartecipante_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int i = ListaPartecipantiProgetto_JT.getSelectedRow();
				if (i >= 0) {
					String nome = ListaPartecipantiProgetto_JT.getValueAt(i, 1).toString();
					String cognome = ListaPartecipantiProgetto_JT.getValueAt(i, 2).toString();
					int opzione = JOptionPane.showOptionDialog(null,
							"Vuoi rimuovere  " + cognome + " " + nome + " dal progetto?", "Rimuovi",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Yes", "No" },
							JOptionPane.YES_OPTION);
					if (opzione == JOptionPane.YES_OPTION) {

						model5.removeRow(i);

					} else {
						return;
					}

				} else {
					JOptionPane.showMessageDialog(null, "Nessun dipendente selezionato", "Errore",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		RimuoviPartecipante_btn.setBounds(459, 398, 108, 37);
		contentPane.add(RimuoviPartecipante_btn);

	}
}
