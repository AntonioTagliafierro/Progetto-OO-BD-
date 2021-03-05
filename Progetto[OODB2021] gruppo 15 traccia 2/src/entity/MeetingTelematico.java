package entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class MeetingTelematico extends Meeting {

	private String codMeetingTelematico;

	private PiattaformaMeeting piattaforma;
	private ArrayList<Dipendente> partecipantiMeetingTelematico;
	private ArrayList<PartecipanteMeeting> partecipanti;
	// Costruttori

	public MeetingTelematico(String codMeetingTelematico) {
		super();
		this.codMeetingTelematico = codMeetingTelematico;
	}

	public MeetingTelematico() {
		super();
	}

	// Getter e Setter

	public String getCodMeetingTelematico() {
		return codMeetingTelematico;
	}

	public void setCodMeetingTelematico(String codMeetingTelematico) {
		this.codMeetingTelematico = codMeetingTelematico;
	}

	public PiattaformaMeeting getPiattaforma() {
		return piattaforma;
	}

	public void setPiattaforma(PiattaformaMeeting piattaforma) {
		this.piattaforma = piattaforma;
	}

	public ArrayList<Dipendente> getPartecipantiMeetingTelematico() {
		return partecipantiMeetingTelematico;
	}

	public void setPartecipantiMeetingTelematico(ArrayList<Dipendente> partecipantiMeetingTelematico) {
		this.partecipantiMeetingTelematico = partecipantiMeetingTelematico;
	}

	public ArrayList<PartecipanteMeeting> getPartecipanti() {
		return partecipanti;
	}

	public void setPartecipanti(ArrayList<PartecipanteMeeting> partecipanti) {
		this.partecipanti = partecipanti;
	}

}
