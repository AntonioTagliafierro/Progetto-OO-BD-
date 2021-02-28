package entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class Dipendente {

	// Attributi

	private String codiceFiscale;
	private String nome;
	private String cognome;
	private String email;
	private String sesso;
	private Date dataNascita;
	private String status;
	private float valutazione;
	private double salarioMedio;
	private String pathFoto;
	private String nCellulare;
	private Residenza residenza;
	private ArrayList<PartecipanteProgetto> progetti;
	private ArrayList<MeetingFisico> meetingFisici;
	private ArrayList<MeetingTelematico> meetingTelematici;
	private ArrayList<ProgettiDelDipendente> progettiDipendente;

	// Costruttori

	public Dipendente(String codiceFiscale) {
		super();
		this.codiceFiscale = codiceFiscale;
	}
	

	public Dipendente(String nome, String cognome, String sesso, Date dataNascita, Residenza residenza) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.dataNascita = dataNascita;
		this.residenza = residenza;
	}


	public Dipendente(String codiceFiscale, String nome, String cognome, String status, float valutazione,
			double salarioMedio) {
		super();
		this.codiceFiscale = codiceFiscale;
		this.nome = nome;
		this.cognome = cognome;
		this.status = status;
		this.valutazione = valutazione;
		this.salarioMedio = salarioMedio;
	}

	public Dipendente(String codiceFiscale, String nome, String cognome, String email, Date dataNascita,
			String status, String sesso, String nCellulare,float valutazione, double salarioMedio, String pathFoto, Residenza residenza) {
		super();
		this.codiceFiscale = codiceFiscale;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.dataNascita = dataNascita;
		this.status = status;
		this.sesso = sesso;
		this.nCellulare = nCellulare;
		this.valutazione = valutazione;
		this.salarioMedio = salarioMedio;
		this.pathFoto = pathFoto;
		this.residenza = residenza;
	}

	public Dipendente(String codiceFiscale, String nome, String cognome) {
		super();
		this.codiceFiscale = codiceFiscale;
		this.nome = nome;
		this.cognome = cognome;
	}

	 
	public Dipendente(String codiceFiscale, String nome, String cognome, String email, Date dataNascita,
			String status,String sesso, float valutazione, double salarioMedio, String pathFoto, Residenza residenza,
			ArrayList<PartecipanteProgetto> progetti, ArrayList<MeetingFisico> meetingFisici,
			ArrayList<MeetingTelematico> meetingTelematici) {
		super();
		this.codiceFiscale = codiceFiscale;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.dataNascita = dataNascita;
		this.status = status;
		this.sesso = sesso;
		this.valutazione = valutazione;
		this.salarioMedio = salarioMedio;
		this.pathFoto = pathFoto;
		this.residenza = residenza;
		this.progetti =new ArrayList<PartecipanteProgetto>();
		this.meetingFisici = new ArrayList<MeetingFisico>();
		this.meetingTelematici = new ArrayList<MeetingTelematico>();
	}

	public Dipendente() {
		
	} 
	
	// Getter e Setter




	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public float getValutazione() {
		return valutazione;
	}

	public void setValutazione(float valutazione) {
		this.valutazione = valutazione;
	}

	public double getSalarioMedio() {
		return salarioMedio;
	}

	public void setSalarioMedio(double salarioMedio) {
		this.salarioMedio = salarioMedio;
	}

	public String getPathFoto() {
		return pathFoto;
	}

	public void setPathFoto(String pathFoto) {
		this.pathFoto = pathFoto;
	}

	public Residenza getResidenza() {
		return residenza;
	}

	public void setResidenza(Residenza residenza) {
		this.residenza = residenza;
	}

	public ArrayList<PartecipanteProgetto> getProgetti() {
		return progetti;
	}

	public void setProgetti(ArrayList<PartecipanteProgetto> progetti) {
		this.progetti = progetti;
	}

	public ArrayList<MeetingFisico> getMeetingFisici() {
		return meetingFisici;
	}

	public void setMeetingFisici(ArrayList<MeetingFisico> meetingFisici) {
		this.meetingFisici = meetingFisici;
	}

	public ArrayList<MeetingTelematico> getMeetingTelematici() {
		return meetingTelematici;
	}

	public void setMeetingTelematici(ArrayList<MeetingTelematico> meetingTelematici) {
		this.meetingTelematici = meetingTelematici;
	}

	public String getnCellulare() {
		return nCellulare;
	}

	public void setnCellulare(String nCellulare) {
		this.nCellulare = nCellulare;
	}


	public ArrayList<ProgettiDelDipendente> getProgettiDipendente() {
		return progettiDipendente;
	}


	public void setProgettiDipendente(ArrayList<ProgettiDelDipendente> progettiDipendente) {
		this.progettiDipendente = progettiDipendente;
	}

}
