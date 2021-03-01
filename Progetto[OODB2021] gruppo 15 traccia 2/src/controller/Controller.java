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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import daos.ProgettoDao;
import daos_impl.ProgettoDaoImpl;
import entity.Dipendente;
import entity.Progetto;

public class Controller {

	HomeGUI home;
    GestioneProgettiGUI gestioneProgettiFrame;
    AggiungiProgettoGUI aggiungiProgettiFrame;
   
    
    //DAO
    private ProgettoDao ProgettoDao;
    
    
    //    data odierna
    
    Date data = new Date();
	
		
    //		espressioni regolari
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
			
		private String expRegValutazione = "[0-9]*";
		
		private String expRegNomeCognome = "[A-Za-z ]{1,40}";

		
		//ArrayList
		ArrayList <Progetto> progetti = new ArrayList<Progetto>();

	
	public Controller(Connection connection) {
		
		home = new HomeGUI(this);
		home.setVisible(true);
		ProgettoDao= new ProgettoDaoImpl(connection);
		
	}

	public void ApriGestioneProggetti() {
	
		gestioneProgettiFrame= new GestioneProgettiGUI(this, null);
		home.setVisible(false);
		gestioneProgettiFrame.setVisible(true);
				
	}

	
	public void ChiudiGestioneProgetti() {
		
		home.setVisible(true);
		gestioneProgettiFrame.setVisible(false);
	}

	public void ApriAggiungiProgetto() {
		aggiungiProgettiFrame= new AggiungiProgettoGUI(this);
		gestioneProgettiFrame.setVisible(false);
		aggiungiProgettiFrame.setVisible(true);
		
	}

	

	public void ChiudiAggiungiProgetto() {
		int s= JOptionPane.showConfirmDialog(null, "Attenzione, se confermi perderai i dati inseriti", "Attenzione", JOptionPane.WARNING_MESSAGE);
		if(s==0) {
		aggiungiProgettiFrame.setVisible(false);
		gestioneProgettiFrame.setVisible(true);
		}
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
		
		public boolean ControlloSalario(String stipendio) {

			if (Pattern.matches(expRegSalario, stipendio)) {
				return true;
			} else {
				return false;
			}
		}
				
		public boolean ControlloValutazione(String valutazione) {
			
			if (Pattern.matches(expRegValutazione, valutazione)) {
				return true;
			} else {
				return false;
			}
		}
		
		public boolean controlloExpRegNomeCognome(String valore) {
			if (Pattern.matches(expRegNomeCognome, valore)) {
				return true;
			} else {
				return false;
			}
		}

		// metodo per controllare se una stringa Ë vuota
		public boolean ControlloStringaVuota(String dataInizio) {
			if (dataInizio.isBlank()) {
				return true;
			} else {
				return false;
			}
		}
		
		// metodo per controllare se una data Ë passata
		public boolean ControlloDataPassata(Date dataDaControllare) {
			if (dataDaControllare.after(data)) {
				return true;
			} else {
				return false;
			}
		}
	
		public ArrayList<Progetto> RecuperaProgetto() throws SQLException {
			
			return progetti = ProgettoDao.RecuperaGeneralit‡Progetto();
	
	}
		
		public boolean ControllaCampiVuotiAggiungiProgetto(String nome, String tipologia, String nomeCliente, String descrizione) {
			if (this.ControlloStringaVuota(nome) || this.ControlloStringaVuota(tipologia) || this.ControlloStringaVuota(nomeCliente)
					|| this.ControlloStringaVuota(descrizione))  {

				return false;
			} else {
				return true;
			}

		}
		
		public boolean ControllaCampiVuotiAggiungiPartecipante( String tipologia, String valutazione, String salario) {
			if (this.ControlloStringaVuota(tipologia) || this.ControlloStringaVuota(valutazione)|| this.ControlloStringaVuota(salario))  {

				return false;
			} else {
				return true;
			}

		}

		public ArrayList<Progetto> RecuperaProgettiFiltrati(String status, String tipologia){
			
			if ((status.equals("FINITO") || status.equals("IN CORSO"))) {

				if (this.ControlloStringaVuota(tipologia)) {

						try {
							progetti = ProgettoDao.RecuperaProgettiStatus(status);
							return progetti;
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return progetti;
				}else{
						try {
							progetti = ProgettoDao.RecuperaProgettiStatuseTipologia(status, tipologia);
							return progetti;
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return progetti;
					}
				} 
				
			  else {
					
						try {
							
							progetti = ProgettoDao.RecuperaProgettiTipologia(tipologia);
							return progetti;
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} 
			
			return progetti;
			
			
		}

		public void riempiTableProgetti(DefaultTableModel model, ArrayList<Progetto> progetti) {
			model.setRowCount(0);
			for (Progetto p : progetti) {
				Object[] column = {"Nome","Codice Progetto","Data inizio","Tipologia","Status" };
				Object[] row = new Object[5];
				model.setColumnIdentifiers(column);
				row[0]=p.getNomeProgetto();
				row[1]=p.getCodProgetto();
				row[2]=p.getDataInizioProgetto();
				row[3]=p.getTipologiaProgetto();
				row[4]=p.getStatusProgetto();
				model.addRow(row);
			}
		}

		public void EliminaProgetto(String codProgetto) throws SQLException {
			
			ProgettoDao.eliminaProgetto(codProgetto);
			
		}

		public void ApriVisualizzaModificaProgettoGUI(String codiceProgetto) {
			
		}

		
		
		
}
	

