package entity;

import java.util.ArrayList;
import java.util.Date;

public class Dipendente {
	
	//Attributi
	
	private String codiceFiscale;
	private String nome;
	private String cognome;
	private String email;
	private Date dataNascita;
	private String status;
	private int età;
	private int valutazione;
	private float salarioMedio;
	private String pathFoto;
	private Residenza residenza;
	private ArrayList<PartecipanteProgetto> progetti;
	private ArrayList<MeetingFisico> meetingFisici;
	private ArrayList<MeetingTelematico> meetingTelematici;
	
	//Costruttori
	
	
	public Dipendente(String codiceFiscale) {
		super();
		this.codiceFiscale = codiceFiscale;
	}
	
	
	public Dipendente(String codiceFiscale, String nome, String cognome, String status, int valutazione,
			float salarioMedio) {
		super();
		this.codiceFiscale = codiceFiscale;
		this.nome = nome;
		this.cognome = cognome;
		this.status = status;
		this.valutazione = valutazione;
		this.salarioMedio = salarioMedio;
	}

	
	public Dipendente(String codiceFiscale, String nome, String cognome, String email, Date dataNascita, String status,
			int età, int valutazione, float salarioMedio, String pathFoto, Residenza residenza) {
		super();
		this.codiceFiscale = codiceFiscale;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.dataNascita = dataNascita;
		this.status = status;
		this.età = età;
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


	//Getter e Setter
	
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
	public int getEtà() {
		return età;
	}
	public void setEtà(int età) {
		this.età = età;
	}
	public int getValutazione() {
		return valutazione;
	}
	public void setValutazione(int valutazione) {
		this.valutazione = valutazione;
	}
	public float getSalarioMedio() {
		return salarioMedio;
	}
	public void setSalarioMedio(float salarioMedio) {
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
	

}
