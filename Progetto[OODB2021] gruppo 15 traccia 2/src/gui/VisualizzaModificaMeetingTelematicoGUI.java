package gui;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.Controller;
import entity.Dipendente;
import entity.MeetingTelematico;
import entity.PartecipanteMeeting;
import entity.PartecipanteProgetto;
import entity.PiattaformaMeeting;
import javax.swing.JLabel;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class VisualizzaModificaMeetingTelematicoGUI extends JFrame {

	private JPanel contentPane;

	private Controller theController;

	private ImageIcon iconaIndietro;

	private JButton tornaIndietrobtn;

	private ImageIcon iconaSalvataggio;

	private JButton salvaTelematicobtn;
	private JLabel datalbl;
	private JLabel oraIniziolbl;
	private JLabel oraFinelbl;
	private JSpinner oraIniziosp;
	private JSpinner oraFinesp;
	private Date dataCorrente = new Date();
	private DateFormat formato = new SimpleDateFormat("HH:mm");
	private JDateChooser dataMeetingChooser;
	private SpinnerDateModel modelloSpinnerInizio = new SpinnerDateModel(dataCorrente, null, null,
			Calendar.HOUR_OF_DAY);
	private JSpinner.DateEditor editorInizio;
	private SpinnerDateModel modelloSpinnerFine = new SpinnerDateModel(dataCorrente, null, null, Calendar.HOUR_OF_DAY);
	private JSpinner.DateEditor editorFine;
	private JLabel tipolbl;
	private ArrayList<PiattaformaMeeting> piattaforme;
	private JComboBox<String> piattaformecb;
	private JTable mostraDipendentitable;
	private JScrollPane scrollPane1;

	private DefaultTableModel model;

	private ArrayList<Dipendente> dipendenti;

	private TableModel righe;

	private TableColumnModel colonne;
	private JTable mostraPartecipantitable;
	private JScrollPane scrollPane2;
	private JButton aggiungibtn;

	private ImageIcon aggiungi;

	private DefaultTableModel modelAggiunti;
	private JButton rimuovibtn;

	private ImageIcon rimuovi;

	private java.sql.Time oraIniziale;
	private java.sql.Time oraFinale;
	private Date tempo;
	private DateFormat formatoTempo = new SimpleDateFormat("HH:mm:ss");

	private MeetingTelematico meeting;
	private JButton modificabtn;

	private ImageIcon modifica;
	private JButton annullabtn;

	private ImageIcon annulla;
	private ArrayList<PartecipanteMeeting> partecipantiDaAggiungere;
	private ArrayList<PartecipanteMeeting> partecipantiDaEliminare;

	/**
	 * Create the frame.
	 */
	public VisualizzaModificaMeetingTelematicoGUI(Controller c, int codice) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (salvaTelematicobtn.isVisible()) {
					int a = JOptionPane.showConfirmDialog(null, "Attenzione, se confermi perderai i dati inseriti",
							"Attenzione", JOptionPane.WARNING_MESSAGE);
					if (a == 0) {
						c.TornaHomeGUIDaModificaMeetingTelematico();
					}
				} else {
					c.TornaHomeGUIDaModificaMeetingTelematico();
				}
			}

		});
		theController = c;
		partecipantiDaAggiungere = new ArrayList<PartecipanteMeeting>();
		partecipantiDaEliminare = new ArrayList<PartecipanteMeeting>();
		int codiceMeeting = codice;
		try {
			meeting = c.RecuperaMeetingTelematico(codice);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {
			dipendenti = c.RecuperaDipendentiAttivi();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			piattaforme = c.RecuperaNomePiattaforme();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VisualizzaModificaMeetingTelematicoGUI.class.getResource("/icon/iconaFrame.png")));
		setTitle("AggiungiMeeting");
		setResizable(false);

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 574, 519);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		iconaIndietro = new ImageIcon(
				(VisualizzaModificaMeetingTelematicoGUI.class.getResource("/icon/iconaIndietrobtm.png")));
		tornaIndietrobtn = new JButton("Indietro");
		tornaIndietrobtn.setBackground(UIManager.getColor("Button.background"));
		tornaIndietrobtn.setIcon(iconaIndietro);
		tornaIndietrobtn.setBorder(new LineBorder(Color.BLACK, 1, true));
		tornaIndietrobtn.setFocusable(false);
		tornaIndietrobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (salvaTelematicobtn.isVisible()) {
					int a = JOptionPane.showConfirmDialog(null, "Attenzione, se confermi perderai i dati inseriti",
							"Attenzione", JOptionPane.WARNING_MESSAGE);
					if (a == 0) {
						c.TornaGestioneMeetingGUIDaModificaMeetingTelematico();
					}
				} else {
					c.TornaGestioneMeetingGUIDaModificaMeetingTelematico();
				}
			}

		});
		tornaIndietrobtn.setToolTipText("Torna Indietro");
		tornaIndietrobtn.setBounds(10, 431, 89, 44);
		contentPane.add(tornaIndietrobtn);

		iconaSalvataggio = new ImageIcon(
				(VisualizzaModificaMeetingTelematicoGUI.class.getResource("/icon/iconaSalvataggio.png")));
		salvaTelematicobtn = new JButton("Salva");
		salvaTelematicobtn.setHorizontalTextPosition(SwingConstants.LEFT);
		salvaTelematicobtn.setBackground(UIManager.getColor("Button.background"));
		salvaTelematicobtn.setForeground(Color.BLACK);
		salvaTelematicobtn.setIcon(iconaSalvataggio);
		salvaTelematicobtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		salvaTelematicobtn.setFocusable(false);
		salvaTelematicobtn.setVisible(false);
		salvaTelematicobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int opzione = JOptionPane.showOptionDialog(null,
						"Vuoi salvare il meeting che si terrà sulla piattaforma: "
								+ piattaformecb.getSelectedItem().toString() + " ?",
						"Salvataggio", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);
				if (opzione == JOptionPane.YES_OPTION) {

					try {
						MeetingTelematico meeting = new MeetingTelematico();
						PiattaformaMeeting piattaforma = new PiattaformaMeeting();

						int b = mostraPartecipantitable.getRowCount();
						int count = 0;
						for (int i = 0; i < b; i++) {

							count++;

						}
						if (count < 2) {
							JOptionPane.showMessageDialog(null, "Devi selezionare almeno due partecipanti", "Errore",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						oraIniziale = (java.sql.Time
								.valueOf(formatoTempo.format((java.util.Date) oraIniziosp.getValue())));
						oraFinale = (java.sql.Time.valueOf(formatoTempo.format((java.util.Date) oraFinesp.getValue())));
						if (oraFinale.before(oraIniziale)) {
							JOptionPane.showMessageDialog(null,
									"L'orario finale non può essere prima di quello iniziale", "Errore",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						meeting.setCodMeetingTelematico(String.valueOf(codiceMeeting));
						meeting.setDataMeeting(dataMeetingChooser.getDate());
						meeting.setOraInizioMeeting(oraIniziale);
						meeting.setOraFineMeeting(oraFinale);
						piattaforma.setNomePiattaforma(piattaformecb.getSelectedItem().toString());
						meeting.setPiattaforma(piattaforma);

						c.ModificaMeetingTelematico(meeting, partecipantiDaAggiungere, partecipantiDaEliminare);
						int risposta = JOptionPane.showOptionDialog(null, "Meeting salvato! Vuoi fare altro?",
								"Salvato", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
								new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);
						if (risposta == JOptionPane.YES_OPTION) {
							setBounds(100, 100, 574, 519);
							aggiungibtn.setVisible(false);
							rimuovibtn.setVisible(false);
							scrollPane1.setVisible(false);
							mostraDipendentitable.setVisible(false);
							oraIniziosp.setEnabled(false);
							oraFinesp.setEnabled(false);
							dataMeetingChooser.setEnabled(false);
							piattaformecb.setEnabled(false);
							annullabtn.setVisible(false);
							salvaTelematicobtn.setVisible(false);

						} else if (risposta == JOptionPane.NO_OPTION) {
							c.TornaGestioneMeetingGUIDaModificaMeetingTelematico();
						}
					} catch (SQLException e) {

						JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					return;
				}

			}
		});

		salvaTelematicobtn.setToolTipText("Salva Meeting Telematico");
		salvaTelematicobtn.setBounds(1060, 431, 89, 44);
		contentPane.add(salvaTelematicobtn);

		datalbl = new JLabel("Data");
		datalbl.setForeground(Color.LIGHT_GRAY);
		datalbl.setBackground(Color.LIGHT_GRAY);
		datalbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		datalbl.setBounds(10, 11, 89, 17);
		contentPane.add(datalbl);

		oraIniziolbl = new JLabel("Ora Inizio");
		oraIniziolbl.setForeground(Color.LIGHT_GRAY);
		oraIniziolbl.setBackground(Color.LIGHT_GRAY);
		oraIniziolbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		oraIniziolbl.setBounds(10, 39, 89, 17);
		contentPane.add(oraIniziolbl);

		oraFinelbl = new JLabel("Ora Fine");
		oraFinelbl.setForeground(Color.LIGHT_GRAY);
		oraFinelbl.setBackground(Color.LIGHT_GRAY);
		oraFinelbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		oraFinelbl.setBounds(10, 67, 89, 17);
		contentPane.add(oraFinelbl);

		dataMeetingChooser = new JDateChooser();
		dataMeetingChooser.getCalendarButton().setToolTipText("Scegli La Data Del Meeting");
		dataMeetingChooser.setBounds(109, 11, 123, 20);
		dataMeetingChooser.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(dataMeetingChooser);
		dataMeetingChooser.setDate(meeting.getDataMeeting());
		dataMeetingChooser.setEnabled(false);

		oraIniziosp = new JSpinner(modelloSpinnerInizio);
		oraIniziosp.setBorder(new LineBorder(new Color(0, 0, 0)));
		oraIniziosp.setToolTipText("Scegli A Che Ora Inizia Il Meeting");
		oraIniziosp.setBounds(109, 39, 75, 20);
		contentPane.add(oraIniziosp);
		editorInizio = new JSpinner.DateEditor(oraIniziosp, "HH:mm");
		((JSpinner.DefaultEditor) oraIniziosp.getEditor()).getTextField().setEditable(false);
		oraIniziosp.setEditor(editorInizio);
		oraIniziosp.setValue(meeting.getOraInizioMeeting());
		oraIniziosp.setEnabled(false);

		oraFinesp = new JSpinner(modelloSpinnerFine);
		oraFinesp.setToolTipText("Scegli A Che Ora Finisce Il Meeting");
		oraFinesp.setBorder(new LineBorder(new Color(0, 0, 0)));
		oraFinesp.setBounds(109, 67, 75, 20);
		contentPane.add(oraFinesp);
		editorFine = new JSpinner.DateEditor(oraFinesp, "HH:mm");
		((JSpinner.DefaultEditor) oraFinesp.getEditor()).getTextField().setEditable(false);
		oraFinesp.setEditor(editorFine);
		oraFinesp.setValue(meeting.getOraFineMeeting());
		oraFinesp.setEnabled(false);

		tipolbl = new JLabel("Piattaforma");
		tipolbl.setForeground(Color.LIGHT_GRAY);
		tipolbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tipolbl.setBounds(10, 98, 81, 20);
		contentPane.add(tipolbl);

		piattaformecb = new JComboBox<String>();
		piattaformecb.setBorder(new LineBorder(new Color(0, 0, 0)));
		piattaformecb.setBounds(109, 96, 203, 22);
		contentPane.add(piattaformecb);
		piattaformecb.setToolTipText("Seleziona La Piattaforma");
		piattaformecb.setSelectedItem(meeting.getPiattaforma().getNomePiattaforma());

		for (PiattaformaMeeting p : piattaforme) {
			piattaformecb.addItem(p.getNomePiattaforma());
		}
		piattaformecb.setEnabled(false);

		scrollPane1 = new JScrollPane();
		scrollPane1.setBorder(new LineBorder(Color.BLACK));
		scrollPane1.setBounds(616, 143, 536, 182);
		contentPane.add(scrollPane1);

		mostraDipendentitable = new JTable(righe, colonne) {
			public boolean isCellEditable(int riga, int colonna) {
				return false;
			}
		};
		scrollPane1.setViewportView(mostraDipendentitable);

		mostraDipendentitable.setBorder(new LineBorder(Color.BLACK));
		model = new DefaultTableModel();
		mostraDipendentitable.setModel(model);
		mostraDipendentitable.getCellSelectionEnabled();

		mostraDipendentitable.getTableHeader().setReorderingAllowed(false);
		scrollPane1.setVisible(false);
		mostraDipendentitable.setVisible(false);

		c.riempiTableAggiungiDipendente(model, dipendenti);

		scrollPane2 = new JScrollPane();
		scrollPane2.setBorder(new LineBorder(Color.BLACK));
		scrollPane2.setBounds(10, 143, 536, 182);
		contentPane.add(scrollPane2);

		mostraPartecipantitable = new JTable(righe, colonne) {
			public boolean isCellEditable(int riga, int colonna) {
				return false;
			}
		};
		scrollPane2.setViewportView(mostraPartecipantitable);
		mostraPartecipantitable.setBorder(new LineBorder(Color.BLACK));
		modelAggiunti = new DefaultTableModel();
		mostraPartecipantitable.setModel(modelAggiunti);
		mostraPartecipantitable.getCellSelectionEnabled();
		Object[] column = { "Codice Fiscale", "Nome", "Cognome", "Valutazione", "Salario" };
		modelAggiunti.setColumnIdentifiers(column);

		c.riempiTablePartecipantiMeeting(modelAggiunti, meeting.getPartecipanti());

		aggiungi = new ImageIcon(
				(VisualizzaModificaMeetingTelematicoGUI.class.getResource("/icon/iconaAggiungibtm.png")));
		aggiungibtn = new JButton("");
		aggiungibtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int i = mostraDipendentitable.getSelectedRow();
				if (i >= 0) {
					String cognome = mostraDipendentitable.getValueAt(i, 1).toString();
					String nome = mostraDipendentitable.getValueAt(i, 2).toString();
					int opzione = JOptionPane.showOptionDialog(null,
							"Vuoi aggiungere  " + cognome + " " + nome + " al meeting?", "Aggiungi",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Yes", "No" },
							JOptionPane.YES_OPTION);
					if (opzione == JOptionPane.YES_OPTION) {
						PartecipanteMeeting partecipante = new PartecipanteMeeting();
						Dipendente dipendente = new Dipendente();
						dipendente.setCodiceFiscale(mostraDipendentitable.getValueAt(i, 0).toString());
						dipendente.setNome(mostraDipendentitable.getValueAt(i, 1).toString());
						dipendente.setCognome(mostraDipendentitable.getValueAt(i, 2).toString());

						dipendente.setValutazione(Float.parseFloat(mostraDipendentitable.getValueAt(i, 3).toString()));
						dipendente
								.setSalarioMedio(Double.parseDouble(mostraDipendentitable.getValueAt(i, 4).toString()));
						partecipante.setDipendente(dipendente);

						int nPartecipanti = modelAggiunti.getRowCount();

						for (int n = 0; n < nPartecipanti; n++) {

							if (mostraPartecipantitable.getValueAt(n, 0).equals(dipendente.getCodiceFiscale())) {
								JOptionPane.showMessageDialog(null, "Dipendente già inserito", "Errore",
										JOptionPane.ERROR_MESSAGE);
								return;
							}

						}

						partecipantiDaAggiungere.add(partecipante);

						if (partecipantiDaEliminare != null) {
							int dim = partecipantiDaEliminare.size();
							if (dim != 0) {
								for (Iterator<PartecipanteMeeting> p = partecipantiDaEliminare.iterator(); p
										.hasNext();) {
									PartecipanteMeeting par = p.next();
									if (par.getDipendente().getCodiceFiscale().equals(dipendente.getCodiceFiscale())) {
										p.remove();
									}
								}

							}
						}
						c.riempiTablePartecipanti(modelAggiunti, dipendente);

					} else {
						return;
					}

				} else {
					JOptionPane.showMessageDialog(null, "Nessun dipendente selezionato", "Errore",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		aggiungibtn.setFocusable(false);
		aggiungibtn.setIcon(aggiungi);
		aggiungibtn.setToolTipText("Aggiungi Partecipante");
		aggiungibtn.setBorder(new LineBorder(new Color(0, 0, 0)));
		aggiungibtn.setBounds(581, 143, 35, 182);
		contentPane.add(aggiungibtn);
		aggiungibtn.setVisible(false);

		rimuovi = new ImageIcon(
				(VisualizzaModificaMeetingTelematicoGUI.class.getResource("/icon/iconaCancellabtm.png")));
		rimuovibtn = new JButton("");
		rimuovibtn.setIcon(rimuovi);
		rimuovibtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = mostraPartecipantitable.getSelectedRow();
				if (i >= 0) {
					String nome = mostraPartecipantitable.getValueAt(i, 1).toString();
					String cognome = mostraPartecipantitable.getValueAt(i, 2).toString();

					int opzione = JOptionPane.showOptionDialog(null,
							"Vuoi rimuovere  " + cognome + " " + nome + " dal meeting?", "Rimuovi",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Yes", "No" },
							JOptionPane.YES_OPTION);
					if (opzione == JOptionPane.YES_OPTION) {
						PartecipanteMeeting partecipante = new PartecipanteMeeting();
						Dipendente dipendente = new Dipendente();
						dipendente.setCodiceFiscale(mostraDipendentitable.getValueAt(i, 0).toString());
						dipendente.setNome(mostraDipendentitable.getValueAt(i, 1).toString());
						dipendente.setCognome(mostraDipendentitable.getValueAt(i, 2).toString());

						dipendente.setValutazione(Float.parseFloat(mostraDipendentitable.getValueAt(i, 3).toString()));
						dipendente
								.setSalarioMedio(Double.parseDouble(mostraDipendentitable.getValueAt(i, 4).toString()));
						partecipante.setDipendente(dipendente);

						partecipantiDaEliminare.add(partecipante);

						if (partecipantiDaAggiungere != null) {
							int dim = partecipantiDaAggiungere.size();
							if (dim != 0) {
								for (Iterator<PartecipanteMeeting> p = partecipantiDaAggiungere.iterator(); p
										.hasNext();) {
									PartecipanteMeeting par = p.next();
									if (par.getDipendente().getCodiceFiscale().equals(dipendente.getCodiceFiscale())) {
										p.remove();
									}
								}

							}
						}

						modelAggiunti.removeRow(i);

					} else {
						return;
					}

				} else {
					JOptionPane.showMessageDialog(null, "Nessun dipendente selezionato", "Errore",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		rimuovibtn.setBorder(new LineBorder(new Color(0, 0, 0)));
		rimuovibtn.setFocusable(false);
		rimuovibtn.setBounds(546, 143, 35, 182);
		contentPane.add(rimuovibtn);

		modifica = new ImageIcon((VisualizzaModificaMeetingTelematicoGUI.class.getResource("/icon/iconaModifica.png")));
		modificabtn = new JButton("Modifica");
		modificabtn.setFocusable(false);
		modificabtn.setHorizontalTextPosition(SwingConstants.LEFT);
		modificabtn.setIcon(modifica);
		modificabtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setBounds(100, 100, 1180, 526);
				aggiungibtn.setVisible(true);
				rimuovibtn.setVisible(true);
				scrollPane1.setVisible(true);
				mostraDipendentitable.setVisible(true);
				oraIniziosp.setEnabled(true);
				oraFinesp.setEnabled(true);
				dataMeetingChooser.setEnabled(true);
				piattaformecb.setEnabled(true);
				annullabtn.setVisible(true);
				salvaTelematicobtn.setVisible(true);
			}
		});
		modificabtn.setToolTipText("Modifica Meeting");
		modificabtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		modificabtn.setBounds(457, 431, 89, 44);
		contentPane.add(modificabtn);

		annulla = new ImageIcon((VisualizzaModificaMeetingTelematicoGUI.class.getResource("/icon/iconaAnnulla.png")));
		annullabtn = new JButton("Annulla");
		annullabtn.setIcon(annulla);
		annullabtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int a = JOptionPane.showConfirmDialog(null, "Attenzione, se confermi perderai i dati inseriti",
						"Attenzione", JOptionPane.WARNING_MESSAGE);
				if (a == 0) {
					setBounds(100, 100, 574, 519);
					aggiungibtn.setVisible(false);
					rimuovibtn.setVisible(false);
					scrollPane1.setVisible(false);
					mostraDipendentitable.setVisible(false);
					oraIniziosp.setEnabled(false);
					oraFinesp.setEnabled(false);
					dataMeetingChooser.setEnabled(false);
					piattaformecb.setEnabled(false);
					piattaformecb.setSelectedItem(meeting.getPiattaforma().getNomePiattaforma());
					dataMeetingChooser.setDate(meeting.getDataMeeting());
					oraIniziosp.setValue(meeting.getOraInizioMeeting());
					oraFinesp.setValue(meeting.getOraFineMeeting());
					c.riempiTablePartecipantiMeeting(modelAggiunti, meeting.getPartecipanti());
					annullabtn.setVisible(false);
					salvaTelematicobtn.setVisible(false);

				}

				return;

			}
		});
		annullabtn.setVisible(false);
		annullabtn.setFocusable(false);
		annullabtn.setBorder(new LineBorder(new Color(0, 0, 0)));
		annullabtn.setBounds(628, 431, 89, 44);
		contentPane.add(annullabtn);
		rimuovibtn.setVisible(false);

	}
}