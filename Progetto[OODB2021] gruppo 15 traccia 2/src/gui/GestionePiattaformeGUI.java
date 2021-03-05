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
import entity.PiattaformaMeeting;
import entity.ProgettiDelDipendente;
import entity.SalaMeeting;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class GestionePiattaformeGUI extends JFrame {

	private JPanel contentPane;

	Controller theController;

	private ImageIcon indietrobtn;

	private JButton tornaMeetingbtn;

	private ImageIcon eliminaPiattaforma;

	private JButton eliminaPiattaformabtn;

	private ImageIcon aggiungiPiattaforma;

	private AbstractButton aggiungiPiattaformabtn;

	private ImageIcon modificaPiattaforma;

	private JButton modificabtn;
	private JTable mostraPiattaformeTable;

	private TableModel righe;

	private TableColumnModel colonne;

	private DefaultTableModel model;

	private ArrayList<PiattaformaMeeting> piattaforme;
	private JScrollPane scrollPane;

	/**
	 * Create the frame.
	 */
	public GestionePiattaformeGUI(Controller c) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				c.ChiudiGestionePiattaformeGUI();
			}
		});
		theController = c;

		try {
			this.piattaforme = c.RecuperaPiattaformeMeeting();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 487, 353);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(GestionePiattaformeGUI.class.getResource("/icon/iconaFrame.png")));
		setTitle("GestionePiattaforme");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(Color.BLACK));
		scrollPane.setBounds(10, 143, 449, 152);
		contentPane.add(scrollPane);

		mostraPiattaformeTable = new JTable(righe, colonne) {
			public boolean isCellEditable(int riga, int colonna) {
				return false;
			}
		};

		scrollPane.setViewportView(mostraPiattaformeTable);
		mostraPiattaformeTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		model = new DefaultTableModel();
		mostraPiattaformeTable.setModel(model);
		mostraPiattaformeTable.getCellSelectionEnabled();

		mostraPiattaformeTable.getTableHeader().setReorderingAllowed(false);
		mostraPiattaformeTable.setBorder(new LineBorder(new Color(0, 0, 0)));

		c.RiempiPiattaformeTable(model, piattaforme);

		indietrobtn = new ImageIcon((GestionePiattaformeGUI.class.getResource("/icon/iconaIndietrobtm.png")));
		tornaMeetingbtn = new JButton("");
		tornaMeetingbtn.setIcon(indietrobtn);
		tornaMeetingbtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		tornaMeetingbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.TornaMeetingGuiDaPiattaformeGUI();
			}
		});
		contentPane.setLayout(null);
		tornaMeetingbtn.setBounds(0, 0, 56, 51);
		tornaMeetingbtn.setToolTipText("Torna Indietro");
		tornaMeetingbtn.setFocusable(false);
		contentPane.add(tornaMeetingbtn);

		eliminaPiattaforma = new ImageIcon(
				(GestionePiattaformeGUI.class.getResource("/icon/iconaCancellaPiattaforma.png")));
		eliminaPiattaformabtn = new JButton("");
		eliminaPiattaformabtn.setIcon(eliminaPiattaforma);
		eliminaPiattaformabtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		eliminaPiattaformabtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				int i = mostraPiattaformeTable.getSelectedRow();
				if (i >= 0) {
					String nomePiattaforma = mostraPiattaformeTable.getValueAt(i, 0).toString();
					int opzione = JOptionPane.showOptionDialog(null,
							"Sei sicuro di voler eliminare la piattafroma: " + nomePiattaforma + "?", "Elimina",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Yes", "No" },
							JOptionPane.YES_OPTION);
					if (opzione == JOptionPane.YES_OPTION) {

						try {
							c.EliminaPiattaforma(nomePiattaforma);
							JOptionPane.showMessageDialog(null,
									"La piattaforma " + nomePiattaforma + " è stata eliminata", "Eliminato",
									JOptionPane.INFORMATION_MESSAGE);
							model.removeRow(i);

						} catch (SQLException e) {

							e.printStackTrace();

						}

					}

				} else {
					JOptionPane.showMessageDialog(null, "Nessuna piattaforma selezionata", "Errore",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		eliminaPiattaformabtn.setBounds(110, 0, 56, 51);
		eliminaPiattaformabtn.setToolTipText("Elimina Piattaforma Selezionata");
		eliminaPiattaformabtn.setFocusable(false);
		contentPane.add(eliminaPiattaformabtn);

		aggiungiPiattaforma = new ImageIcon(
				(GestionePiattaformeGUI.class.getResource("/icon/iconaAggiungiPiattaforma.png")));
		aggiungiPiattaformabtn = new JButton("");
		aggiungiPiattaformabtn.setIcon(aggiungiPiattaforma);
		aggiungiPiattaformabtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		aggiungiPiattaformabtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.ApriAggiungiPiattaformaGui();
			}
		});
		aggiungiPiattaformabtn.setBounds(55, 0, 56, 51);
		aggiungiPiattaformabtn.setToolTipText("Aggiungi Una Nuova Sala");
		aggiungiPiattaformabtn.setFocusable(false);
		contentPane.add(aggiungiPiattaformabtn);

		modificaPiattaforma = new ImageIcon(
				(GestionePiattaformeGUI.class.getResource("/icon/iconaModificaPiattaforma.png")));
		modificabtn = new JButton("");
		modificabtn.setToolTipText("Modifica Piattaforma Selezionata");
		modificabtn.setIcon(modificaPiattaforma);
		modificabtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		modificabtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int i = mostraPiattaformeTable.getSelectedRow();
				if (i >= 0) {
					String nomePiattaforma = mostraPiattaformeTable.getValueAt(i, 0).toString();
					try {
						c.ApriModificaPiattaforme(nomePiattaforma);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(null, "Nessuna Piattaforma selezionata", "Errore",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		modificabtn.setFocusable(false);
		modificabtn.setBounds(165, 0, 51, 51);
		contentPane.add(modificabtn);

	}
}
