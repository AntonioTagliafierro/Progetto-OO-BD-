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
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;

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

	public GestioneProgettiGUI(Controller c, TableModel righe) {
		setBackground(SystemColor.desktop);

		theController = c;
		
		try {
			this.progetti = c.RecuperaProgetto();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		setTitle("Gestione Progetti");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 731, 519);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
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
		Indietro_btm.setBorder(null);
		Indietro_btm.setBackground(UIManager.getColor("Button.background"));
		Indietro_btm.setFocusable(false);
		ImageIcon iconaIndietro_btm = new ImageIcon(
				"C:\\Users\\Gianpietro\\Desktop\\GP\\Uni\\imm\\iconaIndietrobtm.png");
		Indietro_btm.setIcon(iconaIndietro_btm);
		
		Indietro_btm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theController.ChiudiGestioneProgetti();
			}
		});

		Indietro_btm.setBounds(0, 2, 43, 25);
		contentPane.add(Indietro_btm);

		Aggiungi_btm = new JButton("");
		Aggiungi_btm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Aggiungi_btm.setToolTipText("Crea un nuovo progetto");
			}
		});
		Aggiungi_btm.setBorder(null);
		Aggiungi_btm.setBackground(UIManager.getColor("Button.background"));
		Aggiungi_btm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theController.ApriAggiungiProgetto();
			}
		});
		Aggiungi_btm.setBounds(42, 2, 43, 25);
		contentPane.add(Aggiungi_btm);
		Aggiungi_btm.setFocusable(false);
		ImageIcon iconaAggiungi_btm = new ImageIcon("C:\\Users\\Gianpietro\\Desktop\\GP\\Uni\\imm\\iconaAggiungiProgettobtm.png");
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
		Cerca_btm.setBorder(null);
		
		Cerca_btm.setBackground(UIManager.getColor("Button.background"));
		ImageIcon iconaCerca_btm = new ImageIcon("C:\\Users\\Gianpietro\\Desktop\\GP\\Uni\\imm\\iconaCercaProgettobtm.png");
		Cerca_btm.setIcon(iconaCerca_btm);
		
			
	
		Cerca_btm.setBounds(83, 2, 43, 25);
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
						} catch (SQLException b) {

							b.printStackTrace();
							}
						JOptionPane.showMessageDialog(null,
								"Il progetto " + nome + " è stato eliminato", "Eliminato",
								JOptionPane.INFORMATION_MESSAGE);
						model.removeRow(i);

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
				
		Elimina_btm.setBorder(null);
		Elimina_btm.setBackground(UIManager.getColor("Button.background"));
		ImageIcon iconaElimina_btm = new ImageIcon("C:\\Users\\Gianpietro\\Desktop\\GP\\Uni\\imm\\iconaCancellaProgettobtm.png");
		Elimina_btm.setIcon(iconaElimina_btm);
		Elimina_btm.setBounds(124, 2, 43, 25);
		contentPane.add(Elimina_btm);
		Elimina_btm.setFocusable(false);
		
		scrollPane = new JScrollPane();
		scrollPane.setFocusable(false);
		scrollPane.setBounds(42, 196, 611, 184);
		contentPane.add(scrollPane);
		
		
		MostraProgetti_JT = new JTable(righe,colonne) {
			public boolean isCellEditable(int riga, int colonna) {
				return false;
			}
		};
		scrollPane.setViewportView(MostraProgetti_JT);
		model = new DefaultTableModel();
		MostraProgetti_JT.setModel(model);
		
		Filtri_JP = new JPanel();
		Filtri_JP.setVisible(false);
		Filtri_JP.setBounds(42, 69, 556, 99);
		contentPane.add(Filtri_JP);
		Filtri_JP.setLayout(null);
		
		JLabel Status_JL = new JLabel("Status");
		Status_JL.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Status_JL.setBounds(10, 11, 58, 41);
		Filtri_JP.add(Status_JL);
		
		JComboBox statuscb = new JComboBox();
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
		
		JButton Cancellafiltri_btm = new JButton("Cerca");
		Cancellafiltri_btm.setFocusable(false);
		Cancellafiltri_btm.addActionListener(new ActionListener() {
			private ArrayList<Progetto> progetti;
			public void actionPerformed(ActionEvent e) {
				

					if (!c.ControlloStringaVuota(Tipologia_TF.getText())) {
						if (!c.controlloExpRegNome(Tipologia_TF.getText())) {
							JOptionPane.showMessageDialog(null,
									"Tipologia non corretta", "Errore",
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
		Cancellafiltri_btm.setBounds(457, 65, 89, 23);
		Filtri_JP.add(Cancellafiltri_btm);
		
		JButton CancellaFiltri_btm = new JButton("Cancella");
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
		CancellaFiltri_btm.setBounds(367, 65, 89, 23);
		Filtri_JP.add(CancellaFiltri_btm);
		
		JButton Dettagli_btn = new JButton("Visualizza Dettagli");
		Dettagli_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = MostraProgetti_JT.getSelectedRow();
				if (i >= 0) {
					String codiceProgetto = MostraProgetti_JT.getValueAt(i, 1).toString();
					c.ApriVisualizzaModificaProgettoGUI(codiceProgetto);

				} else {
					JOptionPane.showMessageDialog(null, "Nessun progetto selezionato", "Errore",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		Dettagli_btn.setBounds(173, 4, 89, 23);
		contentPane.add(Dettagli_btn);
	
		Object[] column = {"Nome","Codice Progetto","Data inizio","Tipologia","Status"};
		Object[] row = new Object[5];
		model.setColumnIdentifiers(column);
		MostraProgetti_JT.getTableHeader().setReorderingAllowed(false);
		c.riempiTableProgetti(model, progetti);
		
	
		
	}
}
