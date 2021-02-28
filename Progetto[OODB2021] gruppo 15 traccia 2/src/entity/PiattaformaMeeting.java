package entity;

import java.util.ArrayList;

public class PiattaformaMeeting {
	
	private String nomePiattaforma;
	private int limitePartecipanti;
	private ArrayList<MeetingTelematico> meetingTelematici;
	
	//Costruttore
	
	
	public PiattaformaMeeting(String nomePiattaforma, int limitePartecipanti) {
		super();
		this.nomePiattaforma = nomePiattaforma;
		this.limitePartecipanti = limitePartecipanti;
	}
	

	public PiattaformaMeeting() {
		super();
	}


	public PiattaformaMeeting(String nomePiattaforma, int limitePartecipanti,
			ArrayList<MeetingTelematico> meetingTelematici) {
		super();
		this.nomePiattaforma = nomePiattaforma;
		this.limitePartecipanti = limitePartecipanti;
		this.meetingTelematici = meetingTelematici;
	}


	//Getter e Setter
	
	public String getNomePiattaforma() {
		return nomePiattaforma;
	}

	public void setNomePiattaforma(String nomePiattaforma) {
		this.nomePiattaforma = nomePiattaforma;
	}

	public int getLimitePartecipanti() {
		return limitePartecipanti;
	}

	public void setLimitePartecipanti(int limitePartecipanti) {
		this.limitePartecipanti = limitePartecipanti;
	}

	public ArrayList<MeetingTelematico> getMeetingTelematici() {
		return meetingTelematici;
	}

	public void setMeetingTelematici(ArrayList<MeetingTelematico> meetingTelematici) {
		this.meetingTelematici = meetingTelematici;
	}
	
	

}
