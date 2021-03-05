package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import controller.Controller;
import entity.Dipendente;
import entity.MeetingFisico;
import entity.MeetingTelematico;
import entity.PiattaformaMeeting;
import entity.SalaMeeting;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JSeparator;

public class MeetingGUI extends JFrame {

	private JPanel contentPane;
	private Controller theController;
	private ArrayList<MeetingTelematico> meetingsTelematici;
	private ArrayList<MeetingFisico> meetingsFisici;
	private ImageIcon indietrobtn;
	private JButton tornaHomebtn;
	private ImageIcon eliminaMeeting;
	private JButton eliminaMeetingbtn;
	private ImageIcon filtraMeeting;
	private JButton filtraMeetingbtn;
	private JComponent filtriPanel;
	private ImageIcon aggiungiMeeting;
	private JButton aggiungiMeetingbtn;
	private ImageIcon cancellaFiltri;
	private JButton cancellabtn;
	private ImageIcon cercaConFiltri;
	private JButton cercabtn;
	private ImageIcon modificaMeeting;
	private JButton visualizzabtn;
	private TableColumnModel colonne = null;

	private TableModel righe = null;
	private JTable meetingsFisicitable;
	private JScrollPane scrollPaneMeetingsFisici;
	private JTable meetingsTelematicitable;
	private JScrollPane scrollPaneMeetingTelematici;
	private JLabel meetingsFisicilbl;
	private JLabel meetingsTelematicilbl;
	private JLabel salalbl;
	private JComboBox salacb;
	private JLabel piattaformalbl;
	private JComboBox piattaformacb;
	private JMenu salePiattaformemenu;
	private JMenuItem gestisciSaleitem;
	private JMenuBar menuBar;
	private JMenuItem gestisciPiattaformeitem;
	private ImageIcon miniaturaSale;
	private ImageIcon miniaturaPiattaforme;
	private ArrayList<PiattaformaMeeting> piattaforme;
	private ArrayList<SalaMeeting> sale;
	private JSeparator separator;
	private DefaultTableModel modelF;
	private DefaultTableModel modelT;

	/**
	 * Create the frame.
	 */
	public MeetingGUI(Controller c) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				c.ChiudiMeetingGui();
			}
		});
		theController = c;
		setResizable(false);

		try {
			this.meetingsTelematici = c.RecuperaMeetingsTelematici();
			this.meetingsFisici = c.RecuperaMeetingsFisici();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		setIconImage(Toolkit.getDefaultToolkit().getImage(MeetingGUI.class.getResource("/icon/iconaFrame.png")));
		setTitle("GestioneMeeting");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1118, 465);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		indietrobtn = new ImageIcon((MeetingGUI.class.getResource("/icon/iconaIndietrobtm.png")));
		tornaHomebtn = new JButton("");
		tornaHomebtn.setIcon(indietrobtn);
		tornaHomebtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		tornaHomebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.ChiudiMeetingGui();
			}
		});
		tornaHomebtn.setBounds(0, 0, 82, 69);
		tornaHomebtn.setToolTipText("Torna Indietro");
		tornaHomebtn.setFocusable(false);
		contentPane.add(tornaHomebtn);

		eliminaMeeting = new ImageIcon((MeetingGUI.class.getResource("/icon/iconaEliminaMeetingbtn.png")));
		eliminaMeetingbtn = new JButton("");
		eliminaMeetingbtn.setIcon(eliminaMeeting);
		eliminaMeetingbtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		eliminaMeetingbtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				int telematico = meetingsTelematicitable.getSelectedRow();
				int fisico = meetingsFisicitable.getSelectedRow();
				if (telematico >= 0) {
					String codMeeting = meetingsTelematicitable.getValueAt(telematico, 0).toString();

					int opzione = JOptionPane.showOptionDialog(null,
							"Sei sicuro di voler eliminare il meeting: " + codMeeting + "?", "Elimina",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Yes", "No" },
							JOptionPane.YES_OPTION);
					if (opzione == JOptionPane.YES_OPTION) {

						try {
							c.EliminaMeetingTelematico(codMeeting);
							JOptionPane.showMessageDialog(null, "Il meeting " + codMeeting + " è stato eliminato",
									"Eliminato", JOptionPane.INFORMATION_MESSAGE);
							modelT.removeRow(telematico);

						} catch (SQLException e) {

							e.printStackTrace();

						}

					}

				} else if (fisico >= 0) {
					String codMeeting = meetingsFisicitable.getValueAt(fisico, 0).toString();
					int opzione = JOptionPane.showOptionDialog(null,
							"Sei sicuro di voler eliminare il meeting: " + codMeeting + "?", "Elimina",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Yes", "No" },
							JOptionPane.YES_OPTION);
					if (opzione == JOptionPane.YES_OPTION) {

						try {
							c.EliminaMeetingFisico(codMeeting);
							JOptionPane.showMessageDialog(null, "Il meeting " + codMeeting + " è stato eliminato",
									"Eliminato", JOptionPane.INFORMATION_MESSAGE);
							modelF.removeRow(fisico);

						} catch (SQLException e) {

							e.printStackTrace();

						}

					}

				}

				else {
					JOptionPane.showMessageDialog(null, "Nessun meeting selezionato", "Errore",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		eliminaMeetingbtn.setBounds(252, 0, 89, 69);
		eliminaMeetingbtn.setToolTipText("Elimina Meeting Selezionato");
		eliminaMeetingbtn.setFocusable(false);
		contentPane.add(eliminaMeetingbtn);

		filtraMeeting = new ImageIcon((HomeGUI.class.getResource("/icon/iconaCercaMeetingbtn.png")));
		filtraMeetingbtn = new JButton("");
		filtraMeetingbtn.setIcon(filtraMeeting);
		filtraMeetingbtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		filtraMeetingbtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				filtriPanel.setVisible(true);
			}
		});
		filtraMeetingbtn.setBounds(166, 0, 89, 69);
		filtraMeetingbtn.setToolTipText("Attiva Filtri");
		filtraMeetingbtn.setFocusable(false);
		contentPane.add(filtraMeetingbtn);

		aggiungiMeeting = new ImageIcon((MeetingGUI.class.getResource("/icon/iconaAggiungiMeetingbtn.png")));
		aggiungiMeetingbtn = new JButton("");
		aggiungiMeetingbtn.setIcon(aggiungiMeeting);
		aggiungiMeetingbtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		aggiungiMeetingbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.ApriAggiungiMeetingGui();
			}
		});
		aggiungiMeetingbtn.setBounds(79, 0, 89, 69);
		aggiungiMeetingbtn.setToolTipText("Aggiungi un nuovo Meeting");
		aggiungiMeetingbtn.setFocusable(false);
		contentPane.add(aggiungiMeetingbtn);

		filtriPanel = new JPanel();
		filtriPanel.setBackground(new Color(245, 245, 245));
		filtriPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		filtriPanel.setBounds(609, 7, 486, 83);
		contentPane.add(filtriPanel);
		filtriPanel.setLayout(null);
		filtriPanel.setVisible(false);

		cancellaFiltri = new ImageIcon((MeetingGUI.class.getResource("/icon/iconaCancellabtm.png")));
		cancellabtn = new JButton("");
		cancellabtn.setToolTipText("Cancella Filtri");
		cancellabtn.setIcon(cancellaFiltri);
		cancellabtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		cancellabtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				filtriPanel.setVisible(false);
				try {
					meetingsTelematici = c.RecuperaMeetingsTelematici();
					meetingsFisici = c.RecuperaMeetingsFisici();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				c.riempiTableMeetingFisici(modelF, meetingsFisici);
				c.riempiTableMeetingTelematici(modelT, meetingsTelematici);

			}
		});
		cancellabtn.setFocusable(false);
		cancellabtn.setAlignmentX(0.5f);
		cancellabtn.setBounds(299, 23, 89, 38);
		filtriPanel.add(cancellabtn);

		cercaConFiltri = new ImageIcon((MeetingGUI.class.getResource("/icon/iconaCercabtm.png")));
		cercabtn = new JButton("");
		cercabtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					meetingsFisici = c.RecuperaMeetingsFisiciFiltrati(salacb.getSelectedItem().toString());
					c.riempiTableMeetingFisici(modelF, meetingsFisici);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					meetingsTelematici = c
							.RecuperaMeetingsTelematiciFiltrati(piattaformacb.getSelectedItem().toString());
					c.riempiTableMeetingTelematici(modelT, meetingsTelematici);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		cercabtn.setToolTipText("Cerca");
		cercabtn.setIcon(cercaConFiltri);
		cercabtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		cercabtn.setFocusable(false);
		cercabtn.setBounds(386, 23, 89, 38);
		filtriPanel.add(cercabtn);

		try {
			sale = c.RecuperaNomeSale();
		} catch (SQLException e2) {

			e2.printStackTrace();
		}
		try {
			piattaforme = c.RecuperaNomePiattaforme();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		salalbl = new JLabel("Sala");
		salalbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		salalbl.setBounds(10, 11, 37, 22);
		filtriPanel.add(salalbl);

		salacb = new JComboBox();
		salacb.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		salacb.setBounds(109, 13, 180, 22);
		filtriPanel.add(salacb);

		salacb.addItem("TUTTE");
		for (SalaMeeting s : sale) {
			salacb.addItem(s.getNomeSala());

		}

		piattaformalbl = new JLabel("Piattaforma");
		piattaformalbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		piattaformalbl.setBounds(10, 42, 89, 22);
		filtriPanel.add(piattaformalbl);

		piattaformacb = new JComboBox();
		piattaformacb.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		piattaformacb.setBounds(109, 44, 180, 22);
		filtriPanel.add(piattaformacb);
		piattaformacb.addItem("TUTTE");
		for (PiattaformaMeeting p : piattaforme) {
			piattaformacb.addItem(p.getNomePiattaforma());

		}

		modificaMeeting = new ImageIcon((MeetingGUI.class.getResource("/icon/iconaModificaMeetingbtn.png")));
		visualizzabtn = new JButton("");
		visualizzabtn.setToolTipText("Visualizza e Modifica Meeting Selezionato");
		visualizzabtn.setIcon(modificaMeeting);
		visualizzabtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		visualizzabtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int telematico = meetingsTelematicitable.getSelectedRow();
				int fisico = meetingsFisicitable.getSelectedRow();
				if (telematico >= 0) {
					String codMeeting = meetingsTelematicitable.getValueAt(telematico, 0).toString();
					int codice = Integer.parseInt(codMeeting);
					c.ApriVisualizzaModificaMeetingTelematicoGUI(codice);

				} else if (fisico >= 0) {
					String codMeeting = meetingsFisicitable.getValueAt(fisico, 0).toString();
					int codice = Integer.parseInt(codMeeting);
					c.ApriVisualizzaModificaMeetingGUIFisico(codice);

				} else {
					JOptionPane.showMessageDialog(null, "Nessun meeting selezionato", "Errore",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		visualizzabtn.setFocusable(false);
		visualizzabtn.setBounds(340, 0, 89, 69);
		contentPane.add(visualizzabtn);

		scrollPaneMeetingsFisici = new JScrollPane();
		scrollPaneMeetingsFisici.setBorder(new LineBorder(Color.BLACK));
		scrollPaneMeetingsFisici.setBounds(10, 170, 540, 248);
		contentPane.add(scrollPaneMeetingsFisici);

		meetingsFisicitable = new JTable(righe, colonne) {
			public boolean isCellEditable(int riga, int colonna) {
				return false;
			}
		};

		scrollPaneMeetingsFisici.setViewportView(meetingsFisicitable);
		meetingsFisicitable.setBorder(new LineBorder(new Color(0, 0, 0)));
		modelF = new DefaultTableModel();
		meetingsFisicitable.setModel(modelF);
		meetingsFisicitable.getCellSelectionEnabled();

		meetingsFisicitable.getTableHeader().setReorderingAllowed(false);
		c.riempiTableMeetingFisici(modelF, meetingsFisici);

		scrollPaneMeetingTelematici = new JScrollPane();
		scrollPaneMeetingTelematici.setBorder(new LineBorder(Color.BLACK));
		scrollPaneMeetingTelematici.setBounds(555, 170, 540, 248);
		contentPane.add(scrollPaneMeetingTelematici);

		meetingsTelematicitable = new JTable(righe, colonne) {
			public boolean isCellEditable(int riga, int colonna) {
				return false;
			}
		};

		scrollPaneMeetingTelematici.setViewportView(meetingsTelematicitable);
		meetingsTelematicitable.setBorder(new LineBorder(new Color(0, 0, 0)));
		modelT = new DefaultTableModel();
		meetingsTelematicitable.setModel(modelT);
		meetingsTelematicitable.getCellSelectionEnabled();

		meetingsTelematicitable.getTableHeader().setReorderingAllowed(false);
		c.riempiTableMeetingTelematici(modelT, meetingsTelematici);

		meetingsFisicilbl = new JLabel("Meetings Fisici");
		meetingsFisicilbl.setForeground(Color.LIGHT_GRAY);
		meetingsFisicilbl.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 24));
		meetingsFisicilbl.setBounds(197, 101, 176, 53);
		contentPane.add(meetingsFisicilbl);

		meetingsTelematicilbl = new JLabel("Meetings Telematici");
		meetingsTelematicilbl.setForeground(Color.LIGHT_GRAY);
		meetingsTelematicilbl.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 24));
		meetingsTelematicilbl.setBounds(719, 101, 246, 53);
		contentPane.add(meetingsTelematicilbl);

		menuBar = new JMenuBar();
		menuBar.setToolTipText("Gestisci sale o piattaforme per i meetings");
		menuBar.setBorder(new LineBorder(new Color(0, 0, 0)));
		menuBar.setBounds(428, 0, 115, 22);
		contentPane.add(menuBar);

		salePiattaformemenu = new JMenu("Sale e Piattaforme");
		salePiattaformemenu.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		menuBar.add(salePiattaformemenu);

		miniaturaSale = new ImageIcon((MeetingGUI.class.getResource("/icon/miniaturaSala.png")));
		gestisciSaleitem = new JMenuItem("Gestisci Sale");
		gestisciSaleitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.ApriGestioneSale();
			}
		});
		gestisciSaleitem.setIcon(miniaturaSale);
		gestisciSaleitem.setBorder(null);
		salePiattaformemenu.add(gestisciSaleitem);

		miniaturaPiattaforme = new ImageIcon((MeetingGUI.class.getResource("/icon/miniaturaPiattaforma.png")));
		gestisciPiattaformeitem = new JMenuItem("Gestisci Piattaforme");
		gestisciPiattaformeitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.ApriGestionePiattaforme();
			}
		});

		separator = new JSeparator();
		salePiattaformemenu.add(separator);
		gestisciPiattaformeitem.setIcon(miniaturaPiattaforme);
		gestisciPiattaformeitem.setBorder(null);
		salePiattaformemenu.add(gestisciPiattaformeitem);

	}
}
