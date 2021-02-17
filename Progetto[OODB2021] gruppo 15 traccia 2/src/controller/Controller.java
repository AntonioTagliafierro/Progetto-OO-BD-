package controller;

import gui.*;


import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

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
//espressioni regolari
	private String expRegCAP = "[0-9]{5}";

	private String expRegNCivico = "[0-9A-Za-z/� ]{1,6}";

	private String expRegVia = "[A-Za-z0-9 '�����/�]{1,15}";

	private String expRegCitt� = "[A-Za-z]{1,15}";

	private String expRegCellulare = "[0-9]{1,20}";

	private String expRegEmail = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";

	private String expRegCF = "[a-zA-Z]{6}\\d\\d[a-zA-Z]\\d\\d[a-zA-Z]\\d\\d\\d[a-zA-Z]";

	private String expRegCognome = "[A-Za-z]{1,20}";

	private String expRegNome = "[A-Za-z]{1,20}";

	private String expRegSalario = "[0-9]+.+[0-9]{2}";


	public Controller(Connection connection) {
		
		home = new HomeGUI(this);
		home.setVisible(true);
		dipendenteDao = new DipendenteDaoImpl(connection);
	}

	public void apriDipendentiGUI() {
		dipendentiFrame = new DipendentiGUI(this);
		home.setVisible(false);
		dipendentiFrame.setVisible(true);

	}

	public void ChiudiDipendentiGui() {
		dipendentiFrame.setVisible(false);
		home.setVisible(true);

	}

	public void ApriAggiungiDipendenteGui() {
		aggiungiDipendenteFrame = new AggiungiDipendenteGUI(this);
		aggiungiDipendenteFrame.setVisible(true);
		dipendentiFrame.dispose();

	}

	public void TornaDipendentiGUIDaAggiungiDipendente() {
		aggiungiDipendenteFrame.dispose();
		dipendentiFrame.setVisible(true);

	}



	// metodo per salvare la foto profilo di un dipendente
	public String SalvaFotoDipendente() {
		String pathImage = null;
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, GIF, and PNG Images", "jpg", "gif", "png");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {

			try {
				File file = chooser.getSelectedFile();
				System.out.println("You chose to open this file: " + file.getName());
				BufferedImage image;
				image = ImageIO.read(file);
				String nomeFoto = (String) JOptionPane.showInputDialog("Scegli il nome con cui salvare la foto");
				
				String estensione=JOptionPane.showInputDialog(null,"Qual � l'estensione della foto?","Estensione",JOptionPane.QUESTION_MESSAGE);
				pathImage = "C:\\Users\\xtony\\eclipse-workspace\\Progetto[OODB2021] gruppo 15 traccia 2\\fotoDipendenti\\"
						+ nomeFoto + "."+estensione;
				ImageIO.write(image, "jpg", new File(pathImage));
			} catch (IOException e1) {

				JOptionPane.showMessageDialog(null, "Errore caricamento foto", "Errore", JOptionPane.ERROR_MESSAGE);
			}

		}
		return pathImage;
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

	public boolean controlloExpRegCitt�(String valore) {
		if (Pattern.matches(expRegCitt�, valore)) {
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

	// metodo per controllare se una stringa � vuota
	public boolean ControlloStringaVuota(String valore) {
		if (valore.isBlank()) {
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
			String citt�, String cap, String via, String nCivico, String salario) {
		if (this.ControlloStringaVuota(nome) || this.ControlloStringaVuota(cognome) || this.ControlloStringaVuota(codF)
				|| this.ControlloStringaVuota(email) || this.ControlloStringaVuota(cellulare)
				|| this.ControlloStringaVuota(citt�) || this.ControlloStringaVuota(cap)
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
	
	//metodo che chiama la funzione della classe DipendenteDaoImpl  per salvare il dipendente nel DB 
	public void SalvaDipendente(Dipendente dipendente) throws SQLException {
		

			dipendenteDao.InserisciDipendente(dipendente);
			dipendenteDao.InserisciResidenza(dipendente);



	}

}
