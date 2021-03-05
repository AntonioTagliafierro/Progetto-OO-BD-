package controller;

import gui.*;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import dao_impl.DipendenteDaoImpl;
import dao_impl.MeetingDaoImpl;
import dao_impl.PiattaformaMeetingDaoImpl;
import dao_impl.ProgettoDaoImpl;
import dao_impl.SalaMeetingDaoImpl;
import daos.DipendenteDao;
import daos.MeetingDao;
import daos.PiattaformaMeetingDao;
import daos.ProgettoDao;
import daos.SalaMeetingDao;
import entity.Ambito;
import entity.Dipendente;
import entity.MeetingFisico;
import entity.MeetingTelematico;
import entity.PartecipanteMeeting;
import entity.PartecipanteProgetto;
import entity.PiattaformaMeeting;
import entity.Progetto;
import entity.SalaMeeting;

public class Controller {
//Classi GUI

	// DAO
	private DipendenteDao dipendenteDao;
	private MeetingDao meetingDao;
	private SalaMeetingDao salaMeetingDao;
	private PiattaformaMeetingDao piattaformaMeetingDao;
	private ProgettoDao progettoDao;

	// data odierna
	Date data = new Date();

	private HomeGUI home;
	private DipendentiGUI dipendentiFrame;
	private AggiungiDipendenteGUI aggiungiDipendenteFrame;
	private VisualizzaModificaDipendenteGUI visualizzaModificaDipendenteFrame;
	private MeetingGUI meetingFrame;
	private VisualizzaModificaMeetingFisicoGUI visualizzaModificaMeetingFisicoFrame;
	private VisualizzaModificaMeetingTelematicoGUI visualizzaModificaMeetingTelematicoFrame;
	private GestioneSaleGUI gestioneSaleFrame;
	private GestionePiattaformeGUI gestionePiattaformeFrame;
	private AggiungiSalaGUI aggiungiSalaFrame;
	private ModificaSalaGUI modificaSalaFrame;
	private SalaMeeting sala;
	private AggiungiPiattaformaGUI aggiungiPiattaformaFrame;
	private ModificaPiattaformaGUI modificaPiattaformaFrame;
	private AggiungiMeetingGUI aggiungiMeetingFrame;
	private GestioneProgettiGUI gestioneProgettiFrame;
	private AggiungiProgettoGUI aggiungiProgettiFrame;
	private VisualizzaModificaProgettoGUI visualizzaModificaProgettoFrame;
//espressioni regolari
	private String expRegCAP = "[0-9]{5}";

	private String expRegNCivico = "[0-9A-Za-z/∞ ]{1,6}";

	private String expRegVia = "[A-Za-z0-9 'ËÈ‡Ú˘/Ï]{1,15}";

	private String expRegCitt‡ = "[A-Za-z]{1,15}";

	private String expRegCellulare = "[0-9]{1,20}";

	private String expRegEmail = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";

	private String expRegCF = "[a-zA-Z]{6}\\d\\d[a-zA-Z]\\d\\d[a-zA-Z]\\d\\d\\d[a-zA-Z]";

	private String expRegCognome = "[A-Za-z]{1,20}";

	private String expRegNome = "[A-Za-z]{1,20}";

	private String expRegSalario = "[0-9]+.+[0-9]{2}";

	private String expRegValutazioneFiltro = "[0-9]*";

	private String expRegnPosti = "[0-9]*";

	private Dipendente dipendente = new Dipendente();
	private PiattaformaMeeting piattaforma;
	private Progetto progetto;
	private MeetingFisico meetingF;
	private MeetingTelematico meetingT;
	// ArrayList
	private ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();
	private ArrayList<MeetingTelematico> meetingsTelematici = new ArrayList<MeetingTelematico>();
	private ArrayList<MeetingFisico> meetingsFisici = new ArrayList<MeetingFisico>();
	private ArrayList<SalaMeeting> sale = new ArrayList<SalaMeeting>();
	private ArrayList<PiattaformaMeeting> piattaforme = new ArrayList<PiattaformaMeeting>();
	private ArrayList<Progetto> progetti = new ArrayList<Progetto>();

	public Controller(Connection connection) {

		home = new HomeGUI(this);
		home.setVisible(true);
		dipendenteDao = new DipendenteDaoImpl(connection);
		meetingDao = new MeetingDaoImpl(connection);
		salaMeetingDao = new SalaMeetingDaoImpl(connection);
		piattaformaMeetingDao = new PiattaformaMeetingDaoImpl(connection);
		progettoDao = new ProgettoDaoImpl(connection);
	}

	public void apriDipendentiGUI() {

		home.setVisible(false);
		dipendentiFrame = new DipendentiGUI(this);
		dipendentiFrame.setVisible(true);

	}

	public void ChiudiMeetingGui() {
		meetingFrame.setVisible(false);
		home.setVisible(true);

	}

	public void apriMeetingGUI() {
		home.setVisible(false);
		meetingFrame = new MeetingGUI(this);
		meetingFrame.setVisible(true);

	}

	public void ChiudiDipendentiGui() {
		dipendentiFrame.setVisible(false);
		home.setVisible(true);

	}

	public void ApriAggiungiDipendenteGui() {
		aggiungiDipendenteFrame = new AggiungiDipendenteGUI(this);
		aggiungiDipendenteFrame.setVisible(true);
		dipendentiFrame.setVisible(false);

	}

	public void TornaDipendentiGUIDaAggiungiDipendente() {
		aggiungiDipendenteFrame.setVisible(false);
		dipendentiFrame = new DipendentiGUI(this);
		dipendentiFrame.setVisible(true);

	}

	public void ApriVisualizzaModificaDipendenteGUI(String codiceFiscale) {
		dipendente = this.RecuperaTuttoDipendente(codiceFiscale);
		visualizzaModificaDipendenteFrame = new VisualizzaModificaDipendenteGUI(this, dipendente);
		visualizzaModificaDipendenteFrame.setVisible(true);
		dipendentiFrame.setVisible(false);

	}

	public void ApriVisualizzaModificaMeetingTelematicoGUI(int telematico) {
		meetingFrame.setVisible(false);
		visualizzaModificaMeetingTelematicoFrame = new VisualizzaModificaMeetingTelematicoGUI(this, telematico);
		visualizzaModificaMeetingTelematicoFrame.setVisible(true);

	}

	public void ApriVisualizzaModificaMeetingGUIFisico(int fisico) {
		meetingFrame.setVisible(false);
		visualizzaModificaMeetingFisicoFrame = new VisualizzaModificaMeetingFisicoGUI(this, fisico);
		visualizzaModificaMeetingFisicoFrame.setVisible(true);

	}

	public void TornaDipendentiGUIDaVisualizzaModificaDipendente() {
		visualizzaModificaDipendenteFrame.setVisible(false);
		dipendentiFrame = new DipendentiGUI(this);
		dipendentiFrame.setVisible(true);

	}

	public void TornaHomeDaAggiungiDipendente() {
		aggiungiDipendenteFrame.setVisible(false);
		home.setVisible(true);

	}

	public void TornaHomeDaVisualizzaModificaDipendente() {
		visualizzaModificaDipendenteFrame.setVisible(false);
		home.setVisible(true);

	}

	public void ApriGestioneSale() {
		meetingFrame.setVisible(false);
		gestioneSaleFrame = new GestioneSaleGUI(this);
		gestioneSaleFrame.setVisible(true);

	}

	public void ApriGestionePiattaforme() {
		meetingFrame.setVisible(false);
		gestionePiattaformeFrame = new GestionePiattaformeGUI(this);
		gestionePiattaformeFrame.setVisible(true);

	}

	public void ChiudiGestioneSale() {
		gestioneSaleFrame.setVisible(false);
		home.setVisible(true);

	}

	public void TornaMeetingGuiDaSale() {
		gestioneSaleFrame.setVisible(false);
		meetingFrame = new MeetingGUI(this);
		meetingFrame.setVisible(true);

	}

	public void ApriAggiungiSalaGui() {
		gestioneSaleFrame.setVisible(false);
		aggiungiSalaFrame = new AggiungiSalaGUI(this);
		aggiungiSalaFrame.setVisible(true);

	}

	public void TornaGestioneSaleGUIDaAggiungiSala() {
		aggiungiSalaFrame.setVisible(false);
		gestioneSaleFrame = new GestioneSaleGUI(this);
		gestioneSaleFrame.setVisible(true);

	}

	public void TornaHomeGUIDaModificaSala() {
		modificaSalaFrame.setVisible(false);
		home.setVisible(true);

	}

	public void TornaGestioneSaleGUIDaModificaSala() {
		modificaSalaFrame.setVisible(false);
		gestioneSaleFrame = new GestioneSaleGUI(this);
		gestioneSaleFrame.setVisible(true);

	}

	public void ApriModificaSala(String nomeSala) throws SQLException {
		sala = salaMeetingDao.RecuperaSala(nomeSala);
		modificaSalaFrame = new ModificaSalaGUI(this, sala);
		modificaSalaFrame.setVisible(true);
		gestioneSaleFrame.setVisible(false);

	}

	public void ApriAggiungiPiattaformaGui() {
		gestionePiattaformeFrame.setVisible(false);
		aggiungiPiattaformaFrame = new AggiungiPiattaformaGUI(this);
		aggiungiPiattaformaFrame.setVisible(true);

	}

	public void ApriModificaPiattaforme(String nomePiattaforma) throws SQLException {
		piattaforma = piattaformaMeetingDao.RecuperaPiattaforma(nomePiattaforma);
		modificaPiattaformaFrame = new ModificaPiattaformaGUI(this, piattaforma);
		modificaPiattaformaFrame.setVisible(true);
		gestionePiattaformeFrame.setVisible(false);

	}

	public void ChiudiGestionePiattaformeGUI() {
		gestionePiattaformeFrame.setVisible(false);
		home.setVisible(true);

	}

	public void TornaMeetingGuiDaPiattaformeGUI() {
		gestionePiattaformeFrame.setVisible(false);
		meetingFrame = new MeetingGUI(this);
		meetingFrame.setVisible(true);

	}

	public void TornaGestionePiattaformeGUIDaAggiungiPiattaforma() {

		aggiungiPiattaformaFrame.setVisible(false);
		gestionePiattaformeFrame = new GestionePiattaformeGUI(this);
		gestionePiattaformeFrame.setVisible(true);
	}

	public void TornaHomeGUIDaAggiungiSala() {
		aggiungiSalaFrame.setVisible(false);
		home.setVisible(true);

	}

	public void TornaHomeGUIDaAggiungiPiattaforma() {
		aggiungiPiattaformaFrame.setVisible(false);
		home.setVisible(true);

	}

	public void TornaGestioneSaleGUIDaModificaPiattaforma() {
		modificaPiattaformaFrame.setVisible(false);
		gestionePiattaformeFrame = new GestionePiattaformeGUI(this);
		gestionePiattaformeFrame.setVisible(true);

	}

	public void ApriAggiungiMeetingGui() {
		meetingFrame.setVisible(false);
		aggiungiMeetingFrame = new AggiungiMeetingGUI(this);
		aggiungiMeetingFrame.setVisible(true);

	}

	public void TornaHomeGUIDaAggiungiMeeting() {
		aggiungiMeetingFrame.setVisible(false);
		home.setVisible(true);
	}

	public void TornaGestioneMeetingGUIDaAggiungiMeeting() {
		aggiungiMeetingFrame.setVisible(false);
		meetingFrame = new MeetingGUI(this);
		meetingFrame.setVisible(true);

	}

	public void TornaGestioneMeetingGUIDaModificaMeetingFisico() {
		visualizzaModificaMeetingFisicoFrame.setVisible(false);
		meetingFrame = new MeetingGUI(this);
		meetingFrame.setVisible(true);

	}

	public void TornaGestioneMeetingGUIDaModificaMeetingTelematico() {
		visualizzaModificaMeetingTelematicoFrame.setVisible(false);
		meetingFrame = new MeetingGUI(this);
		meetingFrame.setVisible(true);

	}

	public void TornaHomeGUIDaModificaMeetingFisico() {
		visualizzaModificaMeetingFisicoFrame.setVisible(false);
		home.setVisible(true);

	}

	public void TornaHomeGUIDaModificaMeetingTelematico() {
		visualizzaModificaMeetingTelematicoFrame.setVisible(false);
		home.setVisible(true);

	}

	public void ApriGestioneProggetti() {

		gestioneProgettiFrame = new GestioneProgettiGUI(this, null);
		home.setVisible(false);
		gestioneProgettiFrame.setVisible(true);

	}

	public void ChiudiGestioneProgetti() {

		home.setVisible(true);
		gestioneProgettiFrame.setVisible(false);
	}

	public void ApriAggiungiProgetto() {
		aggiungiProgettiFrame = new AggiungiProgettoGUI(this);
		gestioneProgettiFrame.setVisible(false);
		aggiungiProgettiFrame.setVisible(true);

	}

	public void ChiudiAggiungiProgetto() {
		int s = JOptionPane.showConfirmDialog(null, "Attenzione, se confermi perderai i dati inseriti", "Attenzione",
				JOptionPane.WARNING_MESSAGE);
		if (s == 0) {
			gestioneProgettiFrame = new GestioneProgettiGUI(this, null);
			aggiungiProgettiFrame.setVisible(false);
			gestioneProgettiFrame.setVisible(true);
		}
	}

	public void ApriVisualizzaModificaProgetto(String codiceProgetto) {
		progetto = this.RecuperaTuttoProgetto(codiceProgetto);
		visualizzaModificaProgettoFrame = new VisualizzaModificaProgettoGUI(this, progetto);
		visualizzaModificaProgettoFrame.setVisible(true);
		gestioneProgettiFrame.setVisible(false);
	}

	public void ChiudiVisualizzaModificaProgetto() {

		visualizzaModificaProgettoFrame.setVisible(false);
		gestioneProgettiFrame = new GestioneProgettiGUI(this, null);
		gestioneProgettiFrame.setVisible(true);

	}

	public void TornaProgettoGUIDaAggiungiProgetto() {

		aggiungiProgettiFrame.setVisible(false);
		gestioneProgettiFrame = new GestioneProgettiGUI(this, null);
		gestioneProgettiFrame.setVisible(true);
	}

	public void TornaHomeDaAggiungiProgetto() {
		aggiungiProgettiFrame.setVisible(false);
		home.setVisible(true);

	}

	public void TornaHomeGUIDaModificaProgetto() {
		visualizzaModificaProgettoFrame.setVisible(false);
		home.setVisible(true);

	}

	public void riempiTableAggiungiDipendente(DefaultTableModel model, ArrayList<Dipendente> dipendente) {
		model.setRowCount(0);
		for (Dipendente d : dipendente) {
			Object[] column = { "Codice Fiscale", "Nome", "Cognome", "Valutazione", "Salario" };
			Object[] row = new Object[5];
			model.setColumnIdentifiers(column);

			row[0] = d.getCodiceFiscale();
			row[1] = d.getCognome();
			row[2] = d.getNome();
			row[3] = d.getValutazione();
			row[4] = d.getSalarioMedio();
			model.addRow(row);
		}
	}

	public void riempiTablePartecipanti(DefaultTableModel model, Dipendente dipendente) {

		Object[] row = new Object[6];

		row[0] = dipendente.getCodiceFiscale();
		row[1] = dipendente.getCognome();
		row[2] = dipendente.getNome();
		row[3] = dipendente.getValutazione();
		row[4] = dipendente.getSalarioMedio();
		model.addRow(row);

	}

	public void riempiTableDipendenti(DefaultTableModel model, ArrayList<Dipendente> dipendenti) {
		model.setRowCount(0);

		for (Dipendente d : dipendenti) {
			Object[] column = { "Codice Fiscale", "Cognome", "Nome", "Status", "Valutazione", "Salario" };
			Object[] row = new Object[6];
			model.setColumnIdentifiers(column);
			row[0] = d.getCodiceFiscale();
			row[1] = d.getCognome();
			row[2] = d.getNome();
			row[3] = d.getStatus();
			row[4] = d.getValutazione();
			row[5] = d.getSalarioMedio();
			model.addRow(row);
		}

	}

	public void riempiTablePartecipantiMeeting(DefaultTableModel modelAggiunti,
			ArrayList<PartecipanteMeeting> partecipanti) {
		modelAggiunti.setRowCount(0);
		for (PartecipanteMeeting p : partecipanti) {
			Object[] column = { "Codice Fiscale", "Nome", "Cognome", "Valutazione", "Salario" };
			Object[] row = new Object[5];
			modelAggiunti.setColumnIdentifiers(column);

			row[0] = p.getDipendente().getCodiceFiscale();
			row[1] = p.getDipendente().getCognome();
			row[2] = p.getDipendente().getNome();
			row[3] = p.getDipendente().getValutazione();
			row[4] = p.getDipendente().getSalarioMedio();
			modelAggiunti.addRow(row);
		}

	}

	public void riempiTableMeetingFisici(DefaultTableModel model, ArrayList<MeetingFisico> meetingsFisici) {
		model.setRowCount(0);
		if (meetingsFisici.size() != 0) {
			for (MeetingFisico mf : meetingsFisici) {
				Object[] column = { "Codice Meeting", "Data Meeting", "Ora Inizio", "Ora Fine", "Sala",
						"N∞Partecipanti" };
				Object[] row = new Object[6];
				model.setColumnIdentifiers(column);
				row[0] = mf.getCodMeetingFisico();
				row[1] = mf.getDataMeeting();
				row[2] = mf.getOraInizioMeeting();
				row[3] = mf.getOraFineMeeting();
				row[4] = mf.getSala().getNomeSala();
				row[5] = mf.getNumeroPartecipanti();
				model.addRow(row);
			}
		} else {
			Object[] column = { "Codice Meeting", "Data Meeting", "Ora Inizio", "Ora Fine", "Sala", "N∞Partecipanti" };
			Object[] row = new Object[6];
			model.setColumnIdentifiers(column);

		}

	}

	public void riempiTableMeetingTelematici(DefaultTableModel model, ArrayList<MeetingTelematico> meetingsTelematici) {
		model.setRowCount(0);
		if (meetingsTelematici.size() != 0) {
			for (MeetingTelematico mt : meetingsTelematici) {
				Object[] column = { "Codice Meeting", "Data Meeting", "Ora Inizio", "Ora Fine", "Piattaforma",
						"N∞Partecipanti" };
				Object[] row = new Object[6];
				model.setColumnIdentifiers(column);
				row[0] = mt.getCodMeetingTelematico();
				row[1] = mt.getDataMeeting();
				row[2] = mt.getOraInizioMeeting();
				row[3] = mt.getOraFineMeeting();
				row[4] = mt.getPiattaforma().getNomePiattaforma();
				row[5] = mt.getNumeroPartecipanti();
				model.addRow(row);
			}
		} else {
			Object[] column = { "Codice Meeting", "Data Meeting", "Ora Inizio", "Ora Fine", "Piattaforma",
					"N∞Partecipanti" };
			Object[] row = new Object[6];
			model.setColumnIdentifiers(column);

		}

	}

	public void riempiTableProgetti(DefaultTableModel model, ArrayList<Progetto> progetti) {
		model.setRowCount(0);
		for (Progetto p : progetti) {
			Object[] column = { "Nome", "Codice Progetto", "Data inizio", "Tipologia", "Status" };
			Object[] row = new Object[5];
			model.setColumnIdentifiers(column);
			row[0] = p.getNomeProgetto();
			row[1] = p.getCodProgetto();
			row[2] = p.getDataInizioProgetto();
			row[3] = p.getTipologiaProgetto();
			row[4] = p.getStatusProgetto();
			model.addRow(row);
		}
	}

	public void RiempiTablePartecipanti(DefaultTableModel model, ArrayList<PartecipanteProgetto> partecipanti) {
		for (PartecipanteProgetto p : partecipanti) {

			Object[] row = new Object[6];
			row[0] = p.getDipendente().getCodiceFiscale();
			row[1] = p.getDipendente().getNome();
			row[2] = p.getDipendente().getCognome();
			row[3] = p.getDipendente().getValutazione();
			row[4] = p.getDipendente().getSalarioMedio();
			row[5] = p.getRuolo();
			model.addRow(row);
		}
	}

	public void RiempiTablePartecipanti(DefaultTableModel model5, Dipendente dipendente, String ruolo) {
		Object[] row = new Object[6];
		row[0] = dipendente.getCodiceFiscale();
		row[1] = dipendente.getNome();
		row[2] = dipendente.getCognome();
		row[3] = dipendente.getValutazione();
		row[4] = dipendente.getSalarioMedio();
		row[5] = ruolo;
		model5.addRow(row);

	}

	public void RiempiAmbiti(DefaultTableModel model, ArrayList<Ambito> ambiti) {

		for (Ambito a : ambiti) {

			Object[] row = new Object[1];
			row[0] = a.getNomeAmbito();

			model.addRow(row);
		}
	}

	public void RiempiSaleTable(DefaultTableModel model, ArrayList<SalaMeeting> sale) {
		model.setRowCount(0);

		if (sale.size() != 0) {
			for (SalaMeeting s : sale) {
				Object[] column = { "Nome Sala", "N∞Posti" };
				Object[] row = new Object[2];
				model.setColumnIdentifiers(column);
				row[0] = s.getNomeSala();
				row[1] = s.getnPosti();

				model.addRow(row);
			}
		} else {
			Object[] column = { "Nome Sala", "N∞Posti" };
			Object[] row = new Object[2];
			model.setColumnIdentifiers(column);

		}

	}

	public void RiempiPiattaformeTable(DefaultTableModel model, ArrayList<PiattaformaMeeting> piattaforme) {
		model.setRowCount(0);

		if (piattaforme.size() != 0) {
			for (PiattaformaMeeting p : piattaforme) {
				Object[] column = { "Nome Piattaforma", "N∞Dispositivi Collegabili" };
				Object[] row = new Object[2];
				model.setColumnIdentifiers(column);
				row[0] = p.getNomePiattaforma();
				row[1] = p.getLimitePartecipanti();

				model.addRow(row);
			}
		} else {
			Object[] column = { "Nome Piattaforma", "N∞Dispositivi Collegabili" };
			Object[] row = new Object[2];
			model.setColumnIdentifiers(column);

		}

	}

	// metodo per salvare la foto profilo di un dipendente
	public String[] SalvaFotoDipendente() {
		String[] foto = new String[3];
		String pathImage = null;
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, GIF, and PNG Images", "jpg", "gif", "png");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {

			File file = chooser.getSelectedFile();
			System.out.println("You chose to open this file: " + file.getName());

			String nomeFoto = (String) JOptionPane.showInputDialog("Scegli il nome con cui salvare la foto");

			foto[0] = "C:\\Users\\xtony\\eclipse-workspace\\Progetto[OODB2021] gruppo 15 traccia 2\\src\\fotoDipendenti\\"
					+ nomeFoto + ".jpg";

			foto[1] = "/fotoDipendenti/" + nomeFoto + ".jpg";
			foto[2] = file.getAbsolutePath();

		}
		return foto;
	}

	// metodo per copiare la foto solo se Ë nuova
	public void SalvaNuovaFoto(String[] foto) {

		try {
			File file = new File(foto[2]);
			BufferedImage image;
			image = ImageIO.read(file);
			ImageIO.write(image, "jpg", new File(foto[0]));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Errore salvataggio foto", "Errore", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	// metodo per ridimensionare L'immagine
	public ImageIcon resize(ImageIcon im, int x, int y) {
		BufferedImage bi = new BufferedImage(x, y, BufferedImage.TRANSLUCENT);
		Graphics2D gd = (Graphics2D) bi.createGraphics();
		gd.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
		gd.drawImage(im.getImage(), 0, 0, x, y, null);
		gd.dispose();
		return new ImageIcon(bi);
	}

	// metodo per controllare le espressioni regolari
	public boolean controlloExpRegNome(String valore) {
		if (Pattern.matches(expRegNome, valore)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean controlloExpRegCognome(String valore) {
		if (Pattern.matches(expRegCognome, valore)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean controlloExpRegCF(String valore) {
		if (Pattern.matches(expRegCF, valore)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean controlloExpRegEmail(String valore) {
		if (Pattern.matches(expRegEmail, valore)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean controlloExpRegCellulare(String valore) {
		if (Pattern.matches(expRegCellulare, valore)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean controlloExpRegCitt‡(String valore) {
		if (Pattern.matches(expRegCitt‡, valore)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean controlloExpRegVia(String valore) {
		if (Pattern.matches(expRegVia, valore)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean controlloExpRegNCivico(String valore) {
		if (Pattern.matches(expRegNCivico, valore)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean controlloExpRegCAP(String valore) {
		if (Pattern.matches(expRegCAP, valore)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean controlloExpRegnPosti(String nPosti) {
		if (Pattern.matches(expRegnPosti, nPosti)) {
			return true;
		} else {
			return false;
		}
	}

	// metodo per controllare se una stringa Ë vuota
	public boolean ControlloStringaVuota(String valore) {
		if (valore.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean ControlloDataPassata(Date dataDaControllare) {
		if (dataDaControllare.after(data)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean ControllaCampiVuoti(String nome, String cognome, String codF, String email, String cellulare,
			String citt‡, String cap, String via, String nCivico, String salario) {
		if (this.ControlloStringaVuota(nome) || this.ControlloStringaVuota(cognome) || this.ControlloStringaVuota(codF)
				|| this.ControlloStringaVuota(email) || this.ControlloStringaVuota(cellulare)
				|| this.ControlloStringaVuota(citt‡) || this.ControlloStringaVuota(cap)
				|| this.ControlloStringaVuota(via) || this.ControlloStringaVuota(nCivico)
				|| this.ControlloStringaVuota(salario)) {

			return false;
		} else {
			return true;
		}

	}

	public boolean ControllaCampiVuotiAggiungiProgetto(String nome, String tipologia, String nomeCliente,
			String descrizione) {
		if (this.ControlloStringaVuota(nome) || this.ControlloStringaVuota(tipologia)
				|| this.ControlloStringaVuota(nomeCliente) || this.ControlloStringaVuota(descrizione)) {

			return false;
		} else {
			return true;
		}

	}

	public Boolean ControlloSalario(String stipendio) {
		if (Pattern.matches(expRegSalario, stipendio)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean ControlloValutazione(String valutazione) {
		if (Pattern.matches(expRegValutazioneFiltro, valutazione)) {
			return true;
		} else {

			return false;
		}
	}

	// metodo che chiama la funzione della classe DipendenteDaoImpl per salvare il
	// dipendente nel DB
	public void SalvaDipendente(Dipendente dipendente) throws SQLException {

		dipendenteDao.InserisciDipendente(dipendente);
		dipendenteDao.InserisciResidenza(dipendente);

	}

	public void AggiornaDipendente(Dipendente dipendente, String vecchioCF) throws SQLException {

		dipendenteDao.AggiornaDipendente(dipendente, vecchioCF);
	}

	public void EliminaDipendente(String codiceFiscale) throws SQLException {
		dipendenteDao.EliminaDipendente(codiceFiscale);
	}

	public void DisattivaDipendente(String codiceFiscale) throws SQLException {

		dipendenteDao.DisattivaDipendente(codiceFiscale);

	}

	public void AttivaDipendente(String codiceFiscale, Double salario) throws SQLException {
		dipendenteDao.AttivaDipendente(codiceFiscale, salario);

	}

	public void EliminaMeetingTelematico(String codMeeting) throws SQLException {
		int codiceMeeting = Integer.valueOf(codMeeting);
		meetingDao.EliminaMeetingTelematico(codiceMeeting);

	}

	public void EliminaMeetingFisico(String codMeeting) throws SQLException {
		int codiceMeeting = Integer.valueOf(codMeeting);
		meetingDao.EliminaMeetingFisico(codiceMeeting);

	}

	public void EliminaSala(String nomeSala) throws SQLException {

		salaMeetingDao.EliminaSala(nomeSala);

	}

	public void SalvaSala(SalaMeeting sala) throws SQLException {
		salaMeetingDao.SalvaSala(sala);

	}

	public ArrayList<SalaMeeting> RecuperaSaleMeeting() throws SQLException {

		return sale = salaMeetingDao.RecuperaSale();
	}

	public void ModificaSala(SalaMeeting salaModificata, String nomeSalaDaModificare) throws SQLException {
		salaMeetingDao.ModificaSala(salaModificata, nomeSalaDaModificare);

	}

	public void SalvaPiattaforma(PiattaformaMeeting nuovaPiattaforma) throws SQLException {
		piattaformaMeetingDao.SalvaPiattaforma(nuovaPiattaforma);

	}

	public ArrayList<PiattaformaMeeting> RecuperaPiattaformeMeeting() throws SQLException {

		return piattaforme = piattaformaMeetingDao.RecuperaPiattaforme();
	}

	public void EliminaPiattaforma(String nomePiattaforma) throws SQLException {
		piattaformaMeetingDao.EliminaPiattaforma(nomePiattaforma);

	}

	public void ModificaPiattaforma(PiattaformaMeeting piattaformaModificata, String nomePiattaformaDaModificare)
			throws SQLException {
		piattaformaMeetingDao.ModificaPiattaforma(piattaformaModificata, nomePiattaformaDaModificare);

	}

	public ArrayList<Dipendente> RecuperaDipendenti() throws SQLException {

		return dipendenti = dipendenteDao.RecuperaGeneralit‡Dipendenti();

	}

	public ArrayList<MeetingTelematico> RecuperaMeetingsTelematici() throws SQLException {

		return meetingsTelematici = meetingDao.RecuperaMeetingsTelematici();
	}

	public ArrayList<MeetingFisico> RecuperaMeetingsFisici() throws SQLException {

		return meetingsFisici = meetingDao.RecuperaMeetingsFisici();
	}

	private Dipendente RecuperaTuttoDipendente(String codiceFiscale) {
		try {
			return dipendente = dipendenteDao.RecuperaTuttoDaDipendente(codiceFiscale);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dipendente;

	}

	public ArrayList<SalaMeeting> RecuperaNomeSale() throws SQLException {

		return sale = salaMeetingDao.RecuperaNomeSale();
	}

	public ArrayList<PiattaformaMeeting> RecuperaNomePiattaforme() throws SQLException {

		return piattaforme = piattaformaMeetingDao.RecuperaNomePiattaforme();
	}

	public ArrayList<Dipendente> RecuperaDipendentiFiltrati(String status, String salarioS, String valutazioneS) {

		if ((status.equals("ATTIVO") || status.equals("NON ATTIVO"))) {

			if (this.ControlloStringaVuota(salarioS)) {

				if (this.ControlloStringaVuota(valutazioneS)) {

					try {
						dipendenti = dipendenteDao.RecuperaDipendentiStatus(status);
						return dipendenti;
					} catch (SQLException e) {

						e.printStackTrace();
					}
					return dipendenti;
				} else {
					try {
						float valutazione = Float.parseFloat(valutazioneS);
						dipendenti = dipendenteDao.RecuperaDipendentiStatuseValutazione(status, valutazione);
						return dipendenti;
					} catch (SQLException e) {

						e.printStackTrace();
					}
					return dipendenti;
				}
			} else {
				if (this.ControlloStringaVuota(valutazioneS)) {
					try {
						double salario = Double.valueOf(salarioS);
						dipendenti = dipendenteDao.RecuperaDipendentiStatuseSalario(status, salario);
						return dipendenti;
					} catch (SQLException e) {

						e.printStackTrace();
					}
				} else {
					try {
						double salario = Double.valueOf(salarioS);
						float valutazione = Float.parseFloat(valutazioneS);
						dipendenti = dipendenteDao.RecuperaDipendentiStatusSalarioeValutazione(status, salario,
								valutazione);
						return dipendenti;
					} catch (SQLException e) {

						e.printStackTrace();
					}
				}
			}
		} else {
			if (this.ControlloStringaVuota(salarioS)) {
				if (this.ControlloStringaVuota(valutazioneS)) {
					try {
						dipendenti = dipendenteDao.RecuperaGeneralit‡Dipendenti();
						return dipendenti;
					} catch (SQLException e) {

						e.printStackTrace();
					}
				} else {
					try {
						float valutazione = Float.parseFloat(valutazioneS);
						dipendenti = dipendenteDao.RecuperaDipendentiValutazione(valutazione);
						return dipendenti;
					} catch (SQLException e) {

						e.printStackTrace();
					}
				}
			} else {
				if (this.ControlloStringaVuota(valutazioneS)) {
					try {
						double salario = Double.valueOf(salarioS);
						dipendenti = dipendenteDao.RecuperaDipendentiSalario(salario);
						return dipendenti;
					} catch (SQLException e) {

						e.printStackTrace();
					}
				} else {
					try {
						double salario = Double.valueOf(salarioS);
						float valutazione = Float.parseFloat(valutazioneS);
						dipendenti = dipendenteDao.RecuperaDipendentiSalarioeValutazione(salario, valutazione);
						return dipendenti;
					} catch (SQLException e) {

						e.printStackTrace();
					}
				}
			}
		}
		return dipendenti;
	}

	public ArrayList<MeetingTelematico> RecuperaMeetingsTelematiciFiltrati(String piattaforma) throws SQLException {

		if (piattaforma.equals("TUTTE")) {

			meetingsTelematici = meetingDao.RecuperaMeetingsTelematici();
		} else {
			meetingsTelematici = meetingDao.RecuperaMeetingsTelematiciFiltrati(piattaforma);

		}
		return meetingsTelematici;
	}

	public ArrayList<MeetingFisico> RecuperaMeetingsFisiciFiltrati(String sala) throws SQLException {

		if (sala.equals("TUTTE")) {

			meetingsFisici = meetingDao.RecuperaMeetingsFisici();
		} else {
			meetingsFisici = meetingDao.RecuperaMeetingsFisiciFiltrati(sala);

		}

		return meetingsFisici;

	}

	public ArrayList<Dipendente> RecuperaDipendentiAttivi() throws SQLException {

		return dipendenti = dipendenteDao.RecuperaDipendentiAttivi();
	}

	public void SalvaMeetingFisico(MeetingFisico meeting, ArrayList<PartecipanteMeeting> partecipanti)
			throws SQLException {
		meetingDao.SalvaMeetingFisico(meeting, partecipanti);

	}

	public void SalvaMeetingTelematico(MeetingTelematico meeting, ArrayList<PartecipanteMeeting> partecipanti)
			throws SQLException {
		meetingDao.SalvaMeetingTelematico(meeting, partecipanti);

	}

	public void SalvaProgetto(Progetto progetto, ArrayList<PartecipanteProgetto> partecipantiProgetto)
			throws SQLException {

		progettoDao.InserisciProgetto(progetto, partecipantiProgetto);
	}

	public Progetto RecuperaTuttoProgetto(String codiceProgetto) {
		try {
			progetto = progettoDao.RecuperaTuttoDaProgetto(codiceProgetto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return progetto;

	}

	public ArrayList<Progetto> RecuperaProgetto() throws SQLException {

		return progetti = progettoDao.RecuperaGeneralit‡Progetto();

	}

	public ArrayList<Progetto> RecuperaProgettiFiltrati(String status, String tipologia) {

		if ((status.equals("FINITO") || status.equals("IN CORSO"))) {

			if (this.ControlloStringaVuota(tipologia)) {

				try {
					progetti = progettoDao.RecuperaProgettiStatus(status);
					return progetti;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return progetti;
			} else {
				try {
					progetti = progettoDao.RecuperaProgettiStatuseTipologia(status, tipologia);
					return progetti;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return progetti;
			}
		}

		else {

			if (this.ControlloStringaVuota(tipologia)) {
				try {

					return progetti = progettoDao.RecuperaGeneralit‡Progetto();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {

					return progetti = progettoDao.RecuperaProgettiTipologia(tipologia);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return progetti;

	}

	public void EliminaProgetto(String codProgetto) throws SQLException {

		progettoDao.eliminaProgetto(codProgetto);

	}

	public ArrayList<Dipendente> RecuperaDipendentiSenzaFiltri() throws SQLException {

		return dipendenti = dipendenteDao.RecuperaGeneralit‡DipendentiPerFiltri();

	}

	public ArrayList<Dipendente> RecuperaPartecipantiProgettoFiltrati(String tipologia, String salarioS,
			String valutazioneS) {

		if (this.ControlloStringaVuota(tipologia)) {

			if (this.ControlloStringaVuota(salarioS)) {

				if (this.ControlloStringaVuota(valutazioneS)) {

					try {
						dipendenti = dipendenteDao.RecuperaGeneralit‡DipendentiPerFiltri();
						return dipendenti;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return dipendenti;
				} else {
					try {
						float valutazione = Float.parseFloat(valutazioneS);
						dipendenti = dipendenteDao.RecuperaPartecipantiProgettoValutazione(valutazione);
						return dipendenti;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return dipendenti;
				}
			} else {
				if (this.ControlloStringaVuota(valutazioneS)) {
					try {
						double salario = Double.valueOf(salarioS);
						dipendenti = dipendenteDao.RecuperaPartecipantiProgettoSalario(salario);
						return dipendenti;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					try {
						double salario = Double.valueOf(salarioS);
						float valutazione = Float.parseFloat(valutazioneS);
						dipendenti = dipendenteDao.RecuperaPartecipantiProgettoSalarioeValutazione(salario,
								valutazione);
						return dipendenti;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} else {
			if (this.ControlloStringaVuota(salarioS)) {
				if (this.ControlloStringaVuota(valutazioneS)) {
					try {
						dipendenti = dipendenteDao.RecuperaPartecipantiProgettoTipologia(tipologia);
						return dipendenti;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					try {
						float valutazione = Float.parseFloat(valutazioneS);
						dipendenti = dipendenteDao.RecuperaPartecipantiProgettoTipologiaeValutazione(tipologia,
								valutazione);
						return dipendenti;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				if (this.ControlloStringaVuota(valutazioneS)) {
					try {
						double salario = Double.valueOf(salarioS);
						dipendenti = dipendenteDao.RecuperaPartecipantiProgettoTipologiaeSalario(tipologia, salario);
						return dipendenti;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					try {
						double salario = Double.valueOf(salarioS);
						float valutazione = Float.parseFloat(valutazioneS);
						dipendenti = dipendenteDao.RecuperaPartecipantiProgettoTipologiaSalarioValutazione(tipologia,
								salario, valutazione);
						return dipendenti;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return dipendenti;
	}

	public void SalvaModifiche(Progetto progetto, ArrayList<PartecipanteProgetto> dipendenteDaEliminare,
			ArrayList<PartecipanteProgetto> partecipantiDaAggiungere, ArrayList<Ambito> ambitiDaEliminare,
			ArrayList<Ambito> ambitiDaAggiungere) throws SQLException {

		progettoDao.ModificaProgetto(progetto, dipendenteDaEliminare, partecipantiDaAggiungere, ambitiDaEliminare,
				ambitiDaAggiungere);

	}

	public void CompletaProgetto(String codp) throws SQLException {

		progettoDao.CompletaProgetto(codp);
	}

	public MeetingFisico RecuperaMeetingFisico(int codice) throws SQLException {

		return meetingF = meetingDao.RecuperaMeetingFisico(codice);
	}

	public MeetingTelematico RecuperaMeetingTelematico(int codice) throws SQLException {

		return meetingT = meetingDao.RecuperaMeetingTelematico(codice);
	}

	public void ModificaMeetingFisico(MeetingFisico meeting, ArrayList<PartecipanteMeeting> partecipantiDaAggiungere,
			ArrayList<PartecipanteMeeting> partecipantiDaEliminare) throws SQLException {

		meetingDao.ModificaMeetingFisico(meeting, partecipantiDaAggiungere, partecipantiDaEliminare);

	}

	public void ModificaMeetingTelematico(MeetingTelematico meeting,
			ArrayList<PartecipanteMeeting> partecipantiDaAggiungere,
			ArrayList<PartecipanteMeeting> partecipantiDaEliminare) throws SQLException {

		meetingDao.ModificaMeetingTelematico(meeting, partecipantiDaAggiungere, partecipantiDaEliminare);

	}

}
