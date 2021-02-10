package entity;

import java.util.ArrayList;

public class SalaMeeting {

	private String nomeSala;
	private int nPosti;
	private ArrayList<MeetingFisico> meetingFisici;

	// Costruttore

	public SalaMeeting(String nomeSala, int nPosti) {
		super();
		this.nomeSala = nomeSala;
		this.nPosti = nPosti;
	}

	public SalaMeeting(String nomeSala, int nPosti, ArrayList<MeetingFisico> meetingFisici) {
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

	public int getnPosti() {
		return nPosti;
	}

	public void setnPosti(int nPosti) {
		this.nPosti = nPosti;
	}

	public ArrayList<MeetingFisico> getMeetingFisici() {
		return meetingFisici;
	}

	public void setMeetingFisici(ArrayList<MeetingFisico> meetingFisici) {
		this.meetingFisici = meetingFisici;
	}

}
