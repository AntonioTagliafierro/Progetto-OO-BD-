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
import daos.DipendenteDao;
import entity.Dipendente;

public class Controller {
//Classi GUI

	// DAO
	private DipendenteDao dipendenteDao;
	// data odierna
	Date data = new Date();

	private HomeGUI home;
	private DipendentiGUI dipendentiFrame;
	private AggiungiDipendenteGUI aggiungiDipendenteFrame;
	private VisualizzaModificaDipendenteGUI visualizzaModificaDipendenteFrame;
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
	
	Dipendente dipendente = new Dipendente();
	// ArrayList
	ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();

	public Controller(Connection connection) {

		home = new HomeGUI(this);
		home.setVisible(true);
		dipendenteDao = new DipendenteDaoImpl(connection);
	}

	public void apriDipendentiGUI() {

		home.setVisible(false);
		dipendentiFrame = new DipendentiGUI(this);
		dipendentiFrame.setVisible(true);

	}

	public ArrayList<Dipendente> RecuperaDipendenti() throws SQLException {

		return dipendenti = dipendenteDao.RecuperaGeneralit‡Dipendenti();

	}

	public void ChiudiDipendentiGui() {
		dipendentiFrame.setVisible(false);
		home.setVisible(true);

	}

	public void ApriAggiungiDipendenteGui() {
		aggiungiDipendenteFrame = new AggiungiDipendenteGUI(this);
		aggiungiDipendenteFrame.setVisible(true);
		dipendentiFrame.setVisible(false);
		;

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

	private Dipendente RecuperaTuttoDipendente(String codiceFiscale) {
		try {
			return dipendente = dipendenteDao.RecuperaTuttoDaDipendente(codiceFiscale);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dipendente;
		
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
	public void AggiornaDipendente(Dipendente dipendente,String vecchioCF) throws SQLException {
		
		dipendenteDao.AggiornaDipendente(dipendente,vecchioCF);
	}

	public void EliminaDipendente(String codiceFiscale) throws SQLException {
		dipendenteDao.EliminaDipendente(codiceFiscale);
	}

	public void DisattivaDipendente(String codiceFiscale) throws SQLException {

		dipendenteDao.DisattivaDipendente(codiceFiscale);

	}
	public void AttivaDipendente(String codiceFiscale,Double salario) throws SQLException {
		dipendenteDao.AttivaDipendente(codiceFiscale,salario);
		
	}

	public ArrayList<Dipendente> RecuperaDipendentiFiltrati(String status, String salarioS, String valutazioneS) {

		if ((status.equals("ATTIVO") || status.equals("NON ATTIVO"))) {

			if (this.ControlloStringaVuota(salarioS)) {

				if (this.ControlloStringaVuota(valutazioneS)) {

					try {
						dipendenti = dipendenteDao.RecuperaDipendentiStatus(status);
						return dipendenti;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return dipendenti;
				} else {
					try {
						float valutazione = Float.parseFloat(valutazioneS);
						dipendenti = dipendenteDao.RecuperaDipendentiStatuseValutazione(status, valutazione);
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
						dipendenti = dipendenteDao.RecuperaDipendentiStatuseSalario(status, salario);
						return dipendenti;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
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
						// TODO Auto-generated catch block
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					try {
						float valutazione = Float.parseFloat(valutazioneS);
						dipendenti = dipendenteDao.RecuperaDipendentiValutazione(valutazione);
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
						dipendenti = dipendenteDao.RecuperaDipendentiSalario(salario);
						return dipendenti;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					try {
						double salario = Double.valueOf(salarioS);
						float valutazione = Float.parseFloat(valutazioneS);
						dipendenti = dipendenteDao.RecuperaDipendentiSalarioeValutazione(salario, valutazione);
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










}
