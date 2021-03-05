package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import controller.Controller;
import entity.Dipendente;
import entity.Progetto;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

public class GestioneProgettiGUI extends JFrame {

	private JPanel contentPane;
	Controller theController;
	private JButton Indietro_btm;
	private JButton Aggiungi_btm;
	private JButton Cerca_btm;
	private JButton Elimina_btm;
	private JTable MostraProgetti_JT;
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	private ArrayList<Progetto> progetti;
	private TableColumnModel colonne;
	private JPanel Filtri_JP;
	private JLabel Tipologia_JL;
	private JTextField Tipologia_TF;
	private JButton Dettagli_btn;
	private ImageIcon iconaIndietro_btm;
	private ImageIcon iconaAggiungi_btm;
	private ImageIcon iconaCerca_btm;
	private ImageIcon iconaElimina_btm;
	private ImageIcon iconaDettagli_btm;
	private ImageIcon iconaCancellaFiltri_btm;
	private JButton CancellaFiltri_btm;
	private ImageIcon iconaCercaFiltri_btm;
	private JButton Cercafiltri_btm;
	private JComboBox statuscb;
	private JLabel Status_JL;
	private ImageIcon cerca;

	public GestioneProgettiGUI(Controller c, TableModel righe) {

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				c.ChiudiGestioneProgetti();

			}

		});
		setBackground(SystemColor.desktop);

		theController = c;
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(GestioneProgettiGUI.class.getResource("/icon/iconaFrame.png")));
		try {
			this.progetti = c.RecuperaProgetto();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		setTitle("Gestione Progetti");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 671, 484);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 128));
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Indietro_btm = new JButton("");
		Indietro_btm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Indietro_btm.setToolTipText("Torna alla pagina precedente");
			}
		});
		Indietro_btm.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		Indietro_btm.setBackground(UIManager.getColor("Button.background"));
		Indietro_btm.setFocusable(false);
		iconaIndietro_btm = new ImageIcon((GestioneProgettiGUI.class.getResource("/icon/iconaIndietrobtm.png")));
		Indietro_btm.setIcon(iconaIndietro_btm);

		Indietro_btm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theController.ChiudiGestioneProgetti();
			}
		});

		Indietro_btm.setBounds(0, 1, 78, 54);
		contentPane.add(Indietro_btm);

		Aggiungi_btm = new JButton("");
		Aggiungi_btm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Aggiungi_btm.setToolTipText("Crea un nuovo progetto");
			}
		});
		Aggiungi_btm.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		Aggiungi_btm.setBackground(UIManager.getColor("Button.background"));
		Aggiungi_btm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theController.ApriAggiungiProgetto();
			}
		});
		Aggiungi_btm.setBounds(77, 1, 78, 54);
		contentPane.add(Aggiungi_btm);
		Aggiungi_btm.setFocusable(false);
		iconaAggiungi_btm = new ImageIcon(
				(GestioneProgettiGUI.class.getResource("/icon/iconaAggiungiProgettibtn.png")));
		Aggiungi_btm.setIcon(iconaAggiungi_btm);

		Cerca_btm = new JButton("");
		Cerca_btm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Filtri_JP.setVisible(true);

			}
		});

		Cerca_btm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Cerca_btm.setToolTipText("Filtra i progetti ");
			}
		});
		Cerca_btm.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		Cerca_btm.setBackground(UIManager.getColor("Button.background"));
		iconaCerca_btm = new ImageIcon((GestioneProgettiGUI.class.getResource("/icon/iconaCercaProgettibtn.png")));
		Cerca_btm.setIcon(iconaCerca_btm);

		Cerca_btm.setBounds(154, 1, 78, 54);
		contentPane.add(Cerca_btm);
		Cerca_btm.setFocusable(false);

		Elimina_btm = new JButton("");
		Elimina_btm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int i = MostraProgetti_JT.getSelectedRow();
				if (i >= 0) {

					String nome = MostraProgetti_JT.getValueAt(i, 0).toString();
					int opzione = JOptionPane.showOptionDialog(null,
							"Sei sicuro di voler eliminare il progetto " + nome + "?", "Elimina",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Yes", "No" },
							JOptionPane.YES_OPTION);
					if (opzione == JOptionPane.YES_OPTION) {
						String codProgetto = MostraProgetti_JT.getValueAt(i, 1).toString();
						try {
							c.EliminaProgetto(codProgetto);
							JOptionPane.showMessageDialog(null, "Il progetto " + nome + " è stato eliminato",
									"Eliminato", JOptionPane.INFORMATION_MESSAGE);
							model.removeRow(i);
						} catch (SQLException b) {

							b.printStackTrace();
						}

					} else {
						return;
					}

				} else {
					JOptionPane.showMessageDialog(null, "Nessun progetto selezionato", "Errore",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		Elimina_btm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Elimina_btm.setToolTipText("Elimina un progetto");
			}
		});

		Elimina_btm.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		Elimina_btm.setBackground(UIManager.getColor("Button.background"));
		iconaElimina_btm = new ImageIcon((GestioneProgettiGUI.class.getResource("/icon/iconaCancellaProgettibtn.png")));
		Elimina_btm.setIcon(iconaElimina_btm);
		Elimina_btm.setBounds(230, 1, 78, 54);
		contentPane.add(Elimina_btm);
		Elimina_btm.setFocusable(false);

		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setFocusable(false);
		scrollPane.setBounds(10, 222, 635, 184);
		contentPane.add(scrollPane);

		MostraProgetti_JT = new JTable(righe, colonne) {
			public boolean isCellEditable(int riga, int colonna) {
				return false;
			}
		};
		MostraProgetti_JT.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(MostraProgetti_JT);
		model = new DefaultTableModel();
		MostraProgetti_JT.setModel(model);

		Filtri_JP = new JPanel();
		Filtri_JP.setVisible(false);
		Filtri_JP.setBounds(46, 97, 556, 99);
		contentPane.add(Filtri_JP);
		Filtri_JP.setLayout(null);

		Status_JL = new JLabel("Status");
		Status_JL.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Status_JL.setBounds(10, 11, 58, 41);
		Filtri_JP.add(Status_JL);

		statuscb = new JComboBox();
		statuscb.setBounds(59, 22, 104, 22);
		Filtri_JP.add(statuscb);
		statuscb.setFocusable(false);
		statuscb.addItem("FINITO");
		statuscb.addItem("IN CORSO");
		statuscb.addItem("ENTRAMBI");

		Tipologia_JL = new JLabel("Tipologia");
		Tipologia_JL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Tipologia_JL.setBounds(193, 18, 82, 26);
		Filtri_JP.add(Tipologia_JL);

		Tipologia_TF = new JTextField();
		Tipologia_TF.setBounds(270, 23, 86, 20);
		Filtri_JP.add(Tipologia_TF);
		Tipologia_TF.setColumns(10);

		Cercafiltri_btm = new JButton("Cerca");
		Cercafiltri_btm.setHorizontalTextPosition(SwingConstants.LEFT);
		Cercafiltri_btm.setIcon(iconaCercaFiltri_btm);
		Cercafiltri_btm.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		Cercafiltri_btm.setFocusable(false);
		cerca = new ImageIcon((GestioneProgettiGUI.class.getResource("/icon/iconaCercabtm.png")));
		Cercafiltri_btm.setIcon(cerca);
		Cercafiltri_btm.addActionListener(new ActionListener() {
			private ArrayList<Progetto> progetti;

			public void actionPerformed(ActionEvent e) {

				if (!c.ControlloStringaVuota(Tipologia_TF.getText())) {
					if (!c.controlloExpRegNome(Tipologia_TF.getText())) {
						JOptionPane.showMessageDialog(null, "Tipologia non corretta", "Errore",
								JOptionPane.ERROR_MESSAGE);
						Tipologia_TF.setBackground(Color.red);
						return;
					} else {
						Tipologia_TF.setBackground(Color.white);
					}
				}

				this.progetti = c.RecuperaProgettiFiltrati(statuscb.getSelectedItem().toString(),
						Tipologia_TF.getText());
				c.riempiTableProgetti(model, progetti);
			}
		});
		Cercafiltri_btm.setBounds(457, 59, 89, 29);
		Filtri_JP.add(Cercafiltri_btm);

		CancellaFiltri_btm = new JButton("Cancella");
		CancellaFiltri_btm.setHorizontalTextPosition(SwingConstants.LEFT);
		iconaCancellaFiltri_btm = new ImageIcon((GestioneProgettiGUI.class.getResource("/icon/iconaCancellabtm.png")));
		CancellaFiltri_btm.setIcon(iconaCancellaFiltri_btm);
		CancellaFiltri_btm.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		CancellaFiltri_btm.addActionListener(new ActionListener() {
			private ArrayList<Progetto> progetti;

			public void actionPerformed(ActionEvent e) {

				Tipologia_TF.setText("");
				Filtri_JP.setVisible(false);
				try {
					this.progetti = c.RecuperaProgetto();
				} catch (SQLException b) {
					b.printStackTrace();
				}
				c.riempiTableProgetti(model, progetti);

			}
		});
		CancellaFiltri_btm.setFocusable(false);
		CancellaFiltri_btm.setBounds(367, 59, 89, 29);
		Filtri_JP.add(CancellaFiltri_btm);

		Dettagli_btn = new JButton("");
		Dettagli_btn.setToolTipText("Modifica un progetto");
		Dettagli_btn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		Dettagli_btn.setFocusable(false);
		iconaDettagli_btm = new ImageIcon(
				(GestioneProgettiGUI.class.getResource("/icon/iconaModificaProgettibtn.png")));
		Dettagli_btn.setIcon(iconaDettagli_btm);
		Dettagli_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = MostraProgetti_JT.getSelectedRow();
				if (i >= 0) {
					String codiceProgetto = MostraProgetti_JT.getValueAt(i, 1).toString();
					c.ApriVisualizzaModificaProgetto(codiceProgetto);

				} else {
					JOptionPane.showMessageDialog(null, "Nessun progetto selezionato", "Errore",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		Dettagli_btn.setBounds(307, 1, 78, 54);
		contentPane.add(Dettagli_btn);

		Object[] column = { "Nome", "Codice Progetto", "Data inizio", "Tipologia", "Status" };
		Object[] row = new Object[5];
		model.setColumnIdentifiers(column);
		MostraProgetti_JT.getTableHeader().setReorderingAllowed(false);
		c.riempiTableProgetti(model, progetti);

	}
}