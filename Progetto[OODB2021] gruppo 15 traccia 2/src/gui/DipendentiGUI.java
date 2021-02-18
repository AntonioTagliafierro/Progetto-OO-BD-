package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import entity.Dipendente;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.util.ArrayList;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class DipendentiGUI extends JFrame {

	Controller theController;

	private JPanel contentPane;
	private JButton tornaHomebtn;
	private JButton cercaDipendentebtn;
	private JButton eliminaDipendentebtn;
	private JButton aggiungiDipendentebtn;
	private JTable mostraDipendentiTable;
	private JPanel filtriPanel;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private ArrayList<Dipendente> dipendenti;
	/**
	 * Create the frame.
	 * @param dipendenti 
	 * @throws SQLException 
	 */
	public DipendentiGUI(Controller c){
		theController = c;
		
	
		try {
			this.dipendenti = c.RecuperaDipendenti();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}

		setIconImage(Toolkit.getDefaultToolkit().getImage(HomeGUI.class.getResource("/icon/iconaFrame.png")));
		setTitle("GestioneDipendenti");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 799, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tornaHomebtn = new JButton("Indietro");
		tornaHomebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.ChiudiDipendentiGui();
			}
		});
		tornaHomebtn.setBounds(0, 0, 82, 69);
		tornaHomebtn.setToolTipText("Torna Indietro");
		tornaHomebtn.setFocusable(false);
		contentPane.add(tornaHomebtn);
		
		
		eliminaDipendentebtn = new JButton("elimina");
		eliminaDipendentebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		eliminaDipendentebtn.setBounds(252, 0, 89, 69);
		eliminaDipendentebtn.setToolTipText("Elimina Dipendente");
		eliminaDipendentebtn.setFocusable(false);
		contentPane.add(eliminaDipendentebtn);
		
		cercaDipendentebtn = new JButton("cerca");
		cercaDipendentebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				filtriPanel.setVisible(true);
			}
		});
		cercaDipendentebtn.setBounds(166, 0, 89, 69);
		cercaDipendentebtn.setToolTipText("Attiva Filtri");
		cercaDipendentebtn.setFocusable(false);
		contentPane.add(cercaDipendentebtn);
		
		aggiungiDipendentebtn = new JButton("aggiungi");
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
		scrollPane.setBounds(0, 188, 783, 220);
		contentPane.add(scrollPane);
		
		mostraDipendentiTable = new JTable();
		scrollPane.setViewportView(mostraDipendentiTable);
		model = new DefaultTableModel();
		mostraDipendentiTable.setModel(model);
		
		Object[] column = {"Codice Fiscale","Cognome","Nome","Status","Valutazione","Salario"};
		Object[] row = new Object[6];
		model.setColumnIdentifiers(column);
		
		//popolamento tabella
		for(Dipendente d : dipendenti) {
			row[0]=d.getCodiceFiscale();
			row[1]=d.getCognome();
			row[2]=d.getNome();
			row[3]=d.getStatus();
			row[4]=d.getValutazione();
			row[5]=d.getSalarioMedio();
			model.addRow(row);
		}
		
		filtriPanel = new JPanel();
		filtriPanel.setBounds(24, 80, 701, 60);
		contentPane.add(filtriPanel);
	}
}