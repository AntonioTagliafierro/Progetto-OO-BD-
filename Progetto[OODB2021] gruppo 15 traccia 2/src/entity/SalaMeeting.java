package entity;

import java.util.ArrayList;

public class SalaMeeting {

	private String nomeSala;
	private String nPosti;
	private ArrayList<MeetingFisico> meetingFisici;

	// Costruttore

	public SalaMeeting(String nomeSala, String nPosti) {
		super();
		this.nomeSala = nomeSala;
		this.nPosti = nPosti;
	}

	public SalaMeeting() {
		super();
	}

	public SalaMeeting(String nomeSala, String nPosti, ArrayList<MeetingFisico> meetingFisici) {
		super();
		this.nomeSala = nomeSala;
		this.nPosti = nPosti;
		this.meetingFisici = meetingFisici;
	}

	// Getter e Setter

	public String getNomeSala() {
		return nomeSala;
	}

	public void setNomeSala(String nomeSala) {
		this.nomeSala = nomeSala;
	}

	public String getnPosti() {
		return nPosti;
	}

	public void setnPosti(String nPosti) {
		this.nPosti = nPosti;
	}

	public ArrayList<MeetingFisico> getMeetingFisici() {
		return meetingFisici;
	}

	public void setMeetingFisici(ArrayList<MeetingFisico> meetingFisici) {
		this.meetingFisici = meetingFisici;
	}

}
