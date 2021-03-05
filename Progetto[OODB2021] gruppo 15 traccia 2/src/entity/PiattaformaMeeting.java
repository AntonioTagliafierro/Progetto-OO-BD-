package entity;

import java.util.ArrayList;

public class PiattaformaMeeting {

	private String nomePiattaforma;
	private String limitePartecipanti;
	private ArrayList<MeetingTelematico> meetingTelematici;

	// Costruttore

	public PiattaformaMeeting(String nomePiattaforma, String limitePartecipanti) {
		super();
		this.nomePiattaforma = nomePiattaforma;
		this.limitePartecipanti = limitePartecipanti;
	}

	public PiattaformaMeeting() {
		super();
	}

	// Getter e Setter

	public String getNomePiattaforma() {
		return nomePiattaforma;
	}

	public void setNomePiattaforma(String nomePiattaforma) {
		this.nomePiattaforma = nomePiattaforma;
	}

	public String getLimitePartecipanti() {
		return limitePartecipanti;
	}

	public void setLimitePartecipanti(String limitePartecipanti) {
		this.limitePartecipanti = limitePartecipanti;
	}

	public ArrayList<MeetingTelematico> getMeetingTelematici() {
		return meetingTelematici;
	}

	public void setMeetingTelematici(ArrayList<MeetingTelematico> meetingTelematici) {
		this.meetingTelematici = meetingTelematici;
	}

}
