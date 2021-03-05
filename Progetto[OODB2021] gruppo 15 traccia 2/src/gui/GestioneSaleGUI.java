package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import controller.Controller;
import entity.ProgettiDelDipendente;
import entity.SalaMeeting;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class GestioneSaleGUI extends JFrame {

	private JPanel contentPane;

	Controller theController;

	private ImageIcon indietrobtn;

	private JButton tornaMeetingbtn;

	private ImageIcon eliminaSala;

	private JButton eliminaSalabtn;

	private ImageIcon aggiungiSala;

	private AbstractButton aggiungiSalabtn;

	private ImageIcon modificaSala;

	private JButton modificabtn;
	private JTable mostraSalaTable;

	private TableModel righe;

	private TableColumnModel colonne;

	private DefaultTableModel model;

	private ArrayList<SalaMeeting> sale;
	private JScrollPane scrollPane;

	/**
	 * Create the frame.
	 */
	public GestioneSaleGUI(Controller c) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				c.ChiudiGestioneSale();
			}
		});
		theController = c;

		try {
			this.sale = c.RecuperaSaleMeeting();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 489, 347);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GestioneSaleGUI.class.getResource("/icon/iconaFrame.png")));
		setTitle("GestioneSale");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(Color.BLACK));
		scrollPane.setBounds(10, 143, 449, 152);
		contentPane.add(scrollPane);

		mostraSalaTable = new JTable(righe, colonne) {
			public boolean isCellEditable(int riga, int colonna) {
				return false;
			}
		};

		scrollPane.setViewportView(mostraSalaTable);
		mostraSalaTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		model = new DefaultTableModel();
		mostraSalaTable.setModel(model);
		mostraSalaTable.getCellSelectionEnabled();

		mostraSalaTable.getTableHeader().setReorderingAllowed(false);
		mostraSalaTable.setBorder(new LineBorder(new Color(0, 0, 0)));

		c.RiempiSaleTable(model, sale);

		indietrobtn = new ImageIcon((GestioneSaleGUI.class.getResource("/icon/iconaIndietrobtm.png")));
		tornaMeetingbtn = new JButton("");
		tornaMeetingbtn.setIcon(indietrobtn);
		tornaMeetingbtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		tornaMeetingbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.TornaMeetingGuiDaSale();
			}
		});
		contentPane.setLayout(null);
		tornaMeetingbtn.setBounds(0, 0, 56, 51);
		tornaMeetingbtn.setToolTipText("Torna Indietro");
		tornaMeetingbtn.setFocusable(false);
		contentPane.add(tornaMeetingbtn);

		eliminaSala = new ImageIcon((GestioneSaleGUI.class.getResource("/icon/iconaCancellaSala.png")));
		eliminaSalabtn = new JButton("");
		eliminaSalabtn.setIcon(eliminaSala);
		eliminaSalabtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		eliminaSalabtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				int i = mostraSalaTable.getSelectedRow();
				if (i >= 0) {
					String nomeSala = mostraSalaTable.getValueAt(i, 0).toString();
					int opzione = JOptionPane.showOptionDialog(null,
							"Sei sicuro di voler eliminare la sala: " + nomeSala + "?", "Elimina",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Yes", "No" },
							JOptionPane.YES_OPTION);
					if (opzione == JOptionPane.YES_OPTION) {

						try {
							c.EliminaSala(nomeSala);
							JOptionPane.showMessageDialog(null, "La sala " + nomeSala + " è stata eliminata",
									"Eliminato", JOptionPane.INFORMATION_MESSAGE);
							model.removeRow(i);

						} catch (SQLException e) {

							e.printStackTrace();

						}

					}

				} else {
					JOptionPane.showMessageDialog(null, "Nessuna sala selezionata", "Errore",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		eliminaSalabtn.setBounds(110, 0, 56, 51);
		eliminaSalabtn.setToolTipText("Elimina Sala Selezionata");
		eliminaSalabtn.setFocusable(false);
		contentPane.add(eliminaSalabtn);

		aggiungiSala = new ImageIcon((GestioneSaleGUI.class.getResource("/icon/iconaAggiungiSala.png")));
		aggiungiSalabtn = new JButton("");
		aggiungiSalabtn.setIcon(aggiungiSala);
		aggiungiSalabtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		aggiungiSalabtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.ApriAggiungiSalaGui();
			}
		});
		aggiungiSalabtn.setBounds(55, 0, 56, 51);
		aggiungiSalabtn.setToolTipText("Aggiungi Una Nuova Sala");
		aggiungiSalabtn.setFocusable(false);
		contentPane.add(aggiungiSalabtn);

		modificaSala = new ImageIcon((GestioneSaleGUI.class.getResource("/icon/iconaModificaSala.png")));
		modificabtn = new JButton("");
		modificabtn.setToolTipText("Modifica Sala Selezionata");
		modificabtn.setIcon(modificaSala);
		modificabtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		modificabtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int i = mostraSalaTable.getSelectedRow();
				if (i >= 0) {
					String nomeSala = mostraSalaTable.getValueAt(i, 0).toString();
					try {
						c.ApriModificaSala(nomeSala);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(null, "Nessuna sala selezionata", "Errore",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		modificabtn.setFocusable(false);
		modificabtn.setBounds(165, 0, 51, 51);
		contentPane.add(modificabtn);

	}
}
