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
		setBounds(100, 100, 658, 451);
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
			c.ApriCercaProgetto();
			}
		});
		Cerca_btm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Cerca_btm.setToolTipText("Cerca un progetto esistente");
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
		Elimina_btm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Elimina_btm.setToolTipText("Elimina un progetto");
			}
		});
		Elimina_btm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theController.ApriEliminaProgetto();
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
		scrollPane.setBounds(10, 169, 588, 184);
		contentPane.add(scrollPane);
		
		
		MostraProgetti_JT = new JTable(righe,colonne) {
			public boolean isCellEditable(int riga, int colonna) {
				return false;
			}
		};
		scrollPane.setViewportView(MostraProgetti_JT);
		model = new DefaultTableModel();
		MostraProgetti_JT.setModel(model);
	
		Object[] column = {"Nome","Codice Progetto","Data inizio","Tipologia","Status"};
		Object[] row = new Object[5];
		model.setColumnIdentifiers(column);
		MostraProgetti_JT.getTableHeader().setReorderingAllowed(false);
		
		
		//popolamento tabella
				for(Progetto p : progetti) {
					row[0]=p.getNomeProgetto();
					row[1]=p.getCodProgetto();
					row[2]=p.getDataInizioProgetto();
					row[3]=p.getTipologiaProgetto();
					row[4]=p.getStatusProgetto();
					model.addRow(row);
				}
		
		
	}
}
