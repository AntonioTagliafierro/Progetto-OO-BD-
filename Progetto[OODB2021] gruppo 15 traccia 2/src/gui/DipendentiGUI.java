package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import controller.Controller;
import entity.Dipendente;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.util.ArrayList;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.border.LineBorder;

public class DipendentiGUI extends JFrame {

	Controller theController;

	private JPanel contentPane;
	private JButton tornaHomebtn;
	private JButton filtraDipendentebtn;
	private JButton eliminaDipendentebtn;
	private JButton aggiungiDipendentebtn;
	private JTable mostraDipendentiTable;
	private JPanel filtriPanel;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private ArrayList<Dipendente> dipendenti;
	private JComboBox statuscb;
	private JLabel statuslbl;
	private JLabel valutazionelbl;
	private JLabel salariolbl;
	private JButton cancellabtn;
	private JTextField salariotf;
	private JTextField valutazionetf;
	private JButton visualizzabtn;
	private ImageIcon indietrobtn;
	private ImageIcon aggiungiDipendente;
	private ImageIcon eliminaDipendente;
	private ImageIcon filtraDipendente;
	private ImageIcon cancellaFiltri;
	private ImageIcon cercaConFiltri;
	private ImageIcon modifica;

	private TableColumnModel colonne = null;

	private TableModel righe = null;
	private JButton cercabtn;

	/**
	 * Create the frame.
	 * 
	 * @param dipendenti
	 * @throws SQLException
	 */

	public DipendentiGUI(Controller c) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				c.ChiudiDipendentiGui();
			}
		});
		theController = c;
		setResizable(false);

		try {
			this.dipendenti = c.RecuperaDipendenti();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		setIconImage(Toolkit.getDefaultToolkit().getImage(DipendentiGUI.class.getResource("/icon/iconaFrame.png")));
		setTitle("GestioneDipendenti");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 820, 458);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		indietrobtn = new ImageIcon((HomeGUI.class.getResource("/icon/iconaIndietrobtm.png")));
		tornaHomebtn = new JButton("");
		tornaHomebtn.setIcon(indietrobtn);
		tornaHomebtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		tornaHomebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.ChiudiDipendentiGui();
			}
		});
		tornaHomebtn.setBounds(0, 0, 82, 69);
		tornaHomebtn.setToolTipText("Torna Indietro");
		tornaHomebtn.setFocusable(false);
		contentPane.add(tornaHomebtn);

		eliminaDipendente = new ImageIcon((DipendentiGUI.class.getResource("/icon/iconaEliminaDipendentibtn.png")));
		eliminaDipendentebtn = new JButton("");
		eliminaDipendentebtn.setIcon(eliminaDipendente);
		eliminaDipendentebtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		eliminaDipendentebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = mostraDipendentiTable.getSelectedRow();
				if (i >= 0) {
					String cognome = mostraDipendentiTable.getValueAt(i, 1).toString();
					String nome = mostraDipendentiTable.getValueAt(i, 2).toString();
					int opzione = JOptionPane.showOptionDialog(null,
							"Sei sicuro di voler eliminare " + cognome + " " + nome + "?", "Elimina",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Yes", "No" },
							JOptionPane.YES_OPTION);
					if (opzione == JOptionPane.YES_OPTION) {
						String codiceFiscale = mostraDipendentiTable.getValueAt(i, 0).toString();
						try {
							c.EliminaDipendente(codiceFiscale);
							JOptionPane.showMessageDialog(null,
									"Il dipendente " + cognome + " " + nome + " è stato eliminato", "Eliminato",
									JOptionPane.INFORMATION_MESSAGE);
							model.removeRow(i);

						} catch (SQLException e) {

							e.printStackTrace();
							int selezione = JOptionPane.showOptionDialog(null,
									"Il dipendente " + cognome + " " + nome
											+ " non può essere eliminato,vuoi disattivarlo?",
									"Disattivare", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
									new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);
							if (selezione == JOptionPane.YES_OPTION) {
								try {
									c.DisattivaDipendente(codiceFiscale);
								} catch (SQLException e1) {

									e1.printStackTrace();
								}

								JOptionPane.showMessageDialog(null,
										"Il dipendente " + cognome + " " + nome + " è stato disattivato", "Disattivato",
										JOptionPane.INFORMATION_MESSAGE);
								try {
									dipendenti = c.RecuperaDipendenti();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								c.riempiTableDipendenti(model, dipendenti);

							}

						}

					} else {
						return;
					}

				} else {
					JOptionPane.showMessageDialog(null, "Nessun dipendente selezionato", "Errore",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		eliminaDipendentebtn.setBounds(252, 0, 89, 69);
		eliminaDipendentebtn.setToolTipText("Elimina Dipendente Selezionato");
		eliminaDipendentebtn.setFocusable(false);
		contentPane.add(eliminaDipendentebtn);

		filtraDipendente = new ImageIcon((DipendentiGUI.class.getResource("/icon/iconaFiltraDipendentibtn.png")));
		filtraDipendentebtn = new JButton("");
		filtraDipendentebtn.setIcon(filtraDipendente);
		filtraDipendentebtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		filtraDipendentebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				filtriPanel.setVisible(true);
			}
		});
		filtraDipendentebtn.setBounds(166, 0, 89, 69);
		filtraDipendentebtn.setToolTipText("Attiva Filtri");
		filtraDipendentebtn.setFocusable(false);
		contentPane.add(filtraDipendentebtn);

		aggiungiDipendente = new ImageIcon((DipendentiGUI.class.getResource("/icon/iconaAggiungiDipendentibtn.png")));
		aggiungiDipendentebtn = new JButton("");
		aggiungiDipendentebtn.setIcon(aggiungiDipendente);
		aggiungiDipendentebtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		aggiungiDipendentebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.ApriAggiungiDipendenteGui();
			}
		});
		aggiungiDipendentebtn.setBounds(79, 0, 89, 69);
		aggiungiDipendentebtn.setToolTipText("Aggiungi un nuovo Dipendente");
		aggiungiDipendentebtn.setFocusable(false);
		contentPane.add(aggiungiDipendentebtn);

		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(245, 245, 245));
		scrollPane.setBorder(new LineBorder(Color.BLACK, 1, true));
		scrollPane.setBounds(0, 187, 805, 231);
		contentPane.add(scrollPane);

		mostraDipendentiTable = new JTable(righe, colonne) {
			public boolean isCellEditable(int riga, int colonna) {
				return false;
			}
		};
		mostraDipendentiTable.setBackground(new Color(245, 245, 245));
		mostraDipendentiTable.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		scrollPane.setViewportView(mostraDipendentiTable);
		model = new DefaultTableModel();
		mostraDipendentiTable.setModel(model);
		mostraDipendentiTable.getCellSelectionEnabled();

		mostraDipendentiTable.getTableHeader().setReorderingAllowed(false);
		c.riempiTableDipendenti(model, dipendenti);

		filtriPanel = new JPanel();
		filtriPanel.setBackground(new Color(245, 245, 245));
		filtriPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		filtriPanel.setBounds(10, 99, 783, 53);
		contentPane.add(filtriPanel);
		filtriPanel.setLayout(null);
		filtriPanel.setVisible(false);

		valutazionelbl = new JLabel("Valutazione");
		valutazionelbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		valutazionelbl.setBounds(389, 16, 72, 25);
		filtriPanel.add(valutazionelbl);

		salariolbl = new JLabel("Salario");
		salariolbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		salariolbl.setBounds(204, 21, 51, 14);
		filtriPanel.add(salariolbl);

		statuslbl = new JLabel("Status");
		statuslbl.setFont(new Font("Dialog", Font.PLAIN, 14));
		statuslbl.setBounds(10, 21, 46, 14);
		filtriPanel.add(statuslbl);

		statuscb = new JComboBox();
		statuscb.setToolTipText("Seleziona Lo Status");
		statuscb.setBorder(new LineBorder(new Color(0, 0, 0)));
		statuscb.setFocusable(false);
		statuscb.setBounds(68, 19, 106, 22);
		filtriPanel.add(statuscb);
		statuscb.addItem("ATTIVO");
		statuscb.addItem("NON ATTIVO");
		statuscb.addItem("ENTRAMBI");

		cancellaFiltri = new ImageIcon((DipendentiGUI.class.getResource("/icon/iconaCancellabtm.png")));
		cancellabtn = new JButton("");
		cancellabtn.setToolTipText("Cancella Filtri");
		cancellabtn.setIcon(cancellaFiltri);
		cancellabtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		cancellabtn.addActionListener(new ActionListener() {
			private ArrayList<Dipendente> dipendenti;

			public void actionPerformed(ActionEvent arg0) {
				salariotf.setText("");
				valutazionetf.setText("");
				filtriPanel.setVisible(false);
				try {
					this.dipendenti = c.RecuperaDipendenti();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				c.riempiTableDipendenti(model, dipendenti);

			}
		});
		cancellabtn.setFocusable(false);
		cancellabtn.setAlignmentX(0.5f);
		cancellabtn.setBounds(596, 11, 89, 38);
		filtriPanel.add(cancellabtn);

		cercaConFiltri = new ImageIcon((DipendentiGUI.class.getResource("/icon/iconaCercabtm.png")));
		cercabtn = new JButton("");
		cercabtn.setToolTipText("Cerca");
		cercabtn.setIcon(cercaConFiltri);
		cercabtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		cercabtn.addActionListener(new ActionListener() {
			private ArrayList<Dipendente> dipendenti;

			public void actionPerformed(ActionEvent arg0) {
				if (!c.ControlloStringaVuota(salariotf.getText())) {
					if (!c.ControlloSalario(salariotf.getText())) {
						JOptionPane.showMessageDialog(null,
								"Salario non corretto,usa il punto come separatore decimale", "Errore",
								JOptionPane.ERROR_MESSAGE);
						salariotf.setBackground(Color.red);
						return;
					} else {
						salariotf.setBackground(Color.white);
					}
				}
				if (!c.ControlloStringaVuota(valutazionetf.getText())) {
					if (!c.ControlloValutazione(valutazionetf.getText())) {
						JOptionPane.showMessageDialog(null, "Valutazione non corretta,inserisci un numero", "Errore",
								JOptionPane.ERROR_MESSAGE);
						valutazionetf.setBackground(Color.red);
						return;

					} else {
						valutazionetf.setBackground(Color.white);
					}
				}

				this.dipendenti = c.RecuperaDipendentiFiltrati(statuscb.getSelectedItem().toString(),
						salariotf.getText(), valutazionetf.getText());
				c.riempiTableDipendenti(model, dipendenti);
			}
		});
		cercabtn.setFocusable(false);
		cercabtn.setBounds(684, 11, 89, 38);
		filtriPanel.add(cercabtn);

		salariotf = new JTextField();
		salariotf.setToolTipText(
				"Inserisci Salario,Verr\u00E0 ricercato con valore -100 +100 rispetto al valore inserito");
		salariotf.setBackground(Color.WHITE);
		salariotf.setBorder(new LineBorder(Color.BLACK));
		salariotf.setBounds(265, 20, 86, 20);
		filtriPanel.add(salariotf);
		salariotf.setColumns(10);

		valutazionetf = new JTextField();
		valutazionetf.setToolTipText(
				"Inserisci Valutazione,Verr\u00E0 ricercato con valori -1 e +1 rispetto al valore inserito");
		valutazionetf.setBorder(new LineBorder(Color.BLACK));
		valutazionetf.setBounds(471, 20, 86, 20);
		filtriPanel.add(valutazionetf);
		valutazionetf.setColumns(10);

		modifica = new ImageIcon((DipendentiGUI.class.getResource("/icon/iconaModificaDipendentibtn.png")));
		visualizzabtn = new JButton("");
		visualizzabtn.setToolTipText("Visualizza e Modifica Dipendente Selezionato");
		visualizzabtn.setIcon(modifica);
		visualizzabtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		visualizzabtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int i = mostraDipendentiTable.getSelectedRow();
				if (i >= 0) {
					String codiceFiscale = mostraDipendentiTable.getValueAt(i, 0).toString();
					c.ApriVisualizzaModificaDipendenteGUI(codiceFiscale);

				} else {
					JOptionPane.showMessageDialog(null, "Nessun dipendente selezionato", "Errore",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		visualizzabtn.setFocusable(false);
		visualizzabtn.setBounds(340, 0, 89, 69);
		contentPane.add(visualizzabtn);
	}

}