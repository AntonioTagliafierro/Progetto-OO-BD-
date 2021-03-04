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

import daos.DipendenteDao;
import daos.ProgettoDao;
import daos_impl.DipendenteDaoImpl;
import daos_impl.ProgettoDaoImpl;
import entity.Ambito;
import entity.Dipendente;
import entity.PartecipanteProgetto;
import entity.Progetto;

public class Controller {

	HomeGUI home;
    GestioneProgettiGUI gestioneProgettiFrame;
    AggiungiProgettoGUI aggiungiProgettiFrame;
    VisualizzaModificaProgettoGUI visualizzaModificaProgettoFrame;
    
    
    //DAO
    private ProgettoDao ProgettoDao;
    private DipendenteDao dipendenteDao;
    
    //    data odierna
    
    Date data = new Date();
	
		private Progetto progetto;
		Dipendente dipendente = new Dipendente();
		
		
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
		ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();
		

	
	public Controller(Connection connection) {
		
		home = new HomeGUI(this);
		home.setVisible(true);
		ProgettoDao= new ProgettoDaoImpl(connection);
		dipendenteDao=new DipendenteDaoImpl(connection);
		
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
		gestioneProgettiFrame=new GestioneProgettiGUI(this, null);
		aggiungiProgettiFrame.setVisible(false);
		gestioneProgettiFrame.setVisible(true);
		}
	}
	
	public void ApriVisualizzaModificaProgetto(String codiceProgetto) {
		progetto= this.RecuperaTuttoProgetto(codiceProgetto);
		visualizzaModificaProgettoFrame= new VisualizzaModificaProgettoGUI(this, progetto);
		visualizzaModificaProgettoFrame.setVisible(true);
		gestioneProgettiFrame.setVisible(false);
	}
	
	public void ChiudiVisualizzaModificaProgetto() {
		
		visualizzaModificaProgettoFrame.setVisible(false);
		gestioneProgettiFrame = new GestioneProgettiGUI(this, null);
		gestioneProgettiFrame.setVisible(true);
		
	}
	
	public void SalvaProgetto(Progetto progetto, ArrayList<PartecipanteProgetto> partecipantiProgetto) throws SQLException {
		
		ProgettoDao.InserisciProgetto(progetto, partecipantiProgetto);
	}

	public void TornaProgettoGUIDaAggiungiProgetto() {
		
		aggiungiProgettiFrame.setVisible(false);
		gestioneProgettiFrame = new GestioneProgettiGUI(this, null);
		gestioneProgettiFrame.setVisible(true);
	}

	
	
	public Progetto RecuperaTuttoProgetto(String codiceProgetto) {
		try {
			  progetto = ProgettoDao.RecuperaTuttoDaProgetto(codiceProgetto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return progetto;
		
		
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
			if (dataInizio.isEmpty()) {
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

		public ArrayList<Dipendente> RecuperaDipendentiSenzaFiltri() throws SQLException {

			return dipendenti = dipendenteDao.RecuperaGeneralit‡DipendentiPerFiltri();

		}
		

		public void riempiTableAggiungiDipendente(DefaultTableModel model, ArrayList<Dipendente> dipendente) {
			model.setRowCount(0);
			for (Dipendente d : dipendente) {
				Object[] column = {"Codice Fiscale","Nome","Cognome","Valutazione","Salario" };
				Object[] row = new Object[5];
				model.setColumnIdentifiers(column);
				row[0]=d.getCodiceFiscale();
				row[1]=d.getNome();
				row[2]=d.getCognome();
				row[3]=d.getValutazione();
				row[4]=d.getSalarioMedio();
				model.addRow(row);
			}
			
		}

		public ArrayList<Dipendente> RecuperaPartecipantiProgettoFiltrati(String tipologia, String salarioS, String valutazioneS) {

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
							dipendenti = dipendenteDao.RecuperaPartecipantiProgettoSalarioeValutazione(salario,valutazione);
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
							dipendenti = dipendenteDao.RecuperaPartecipantiProgettoTipologiaeValutazione(tipologia, valutazione);
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
							dipendenti = dipendenteDao.RecuperaPartecipantiProgettoTipologiaSalarioValutazione(tipologia, salario,valutazione);
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

		public void RiempiTablePartecipanti(DefaultTableModel model, ArrayList<PartecipanteProgetto>partecipanti) {
			for(PartecipanteProgetto p : partecipanti) {
			
			Object[] row = new Object[6];
			row[0]=p.getDipendente().getCodiceFiscale();
			row[1]=p.getDipendente().getNome();
			row[2]=p.getDipendente().getCognome();
			row[3]=p.getDipendente().getValutazione();
			row[4]=p.getDipendente().getSalarioMedio();
			row[5]= p.getRuolo();
			model.addRow(row);
			}
		}

		public void RiempiTablePartecipanti(DefaultTableModel model5, Dipendente dipendente, String ruolo) {
			Object[] row = new Object[6];
			row[0]=dipendente.getCodiceFiscale();
			row[1]=dipendente.getNome();
			row[2]=dipendente.getCognome();
			row[3]=dipendente.getValutazione();
			row[4]=dipendente.getSalarioMedio();
			row[5]=ruolo;
			model5.addRow(row);
		
			
		}

		public void RiempiAmbiti(DefaultTableModel model, ArrayList<Ambito> ambiti) {
			
			for(Ambito a  : ambiti) {
				
				Object[] row = new Object[1];
				row[0]=a.getNomeAmbito();
				
				model.addRow(row);
				}
		}

		

		public void SalvaModifiche(Progetto progetto, ArrayList<PartecipanteProgetto> dipendenteDaEliminare,
				ArrayList<PartecipanteProgetto> partecipantiDaAggiungere, ArrayList<Ambito> ambitiDaEliminare,
				ArrayList<Ambito> ambitiDaAggiungere) throws SQLException {
			
			ProgettoDao.ModificaProgetto(progetto,dipendenteDaEliminare,partecipantiDaAggiungere,ambitiDaEliminare,ambitiDaAggiungere);
			
			
		}

		public void CompletaProgetto(String codp) throws SQLException {
		
			ProgettoDao.CompletaProgetto(codp);
		}

		

			
					
				
		
		
		
				}
		
					

