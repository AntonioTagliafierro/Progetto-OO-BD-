package entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class MeetingTelematico {

	private String codMeetingTelematico;
	private Date dataMeetingTelematico;
	private LocalTime oraInizioMeetingTelematico;
	private LocalTime oraFineMeetingTelematico;
	private String descrizioneMeetingTelematico;
	private PiattaformaMeeting piattaforma;
	private ArrayList<Dipendente> partecipantiMeetingTelematico;

	// Costruttori

	public MeetingTelematico(String codMeetingTelematico) {
		super();
		this.codMeetingTelematico = codMeetingTelematico;
	}

	public MeetingTelematico() {
		super();
	}

	public MeetingTelematico(String codMeetingTelematico, Date dataMeetingTelematico, PiattaformaMeeting piattaforma) {
		super();
		this.codMeetingTelematico = codMeetingTelematico;
		this.dataMeetingTelematico = dataMeetingTelematico;
		this.piattaforma = piattaforma;
	}

	// Getter e Setter

	public String getCodMeetingTelematico() {
		return codMeetingTelematico;
	}

	public void setCodMeetingTelematico(String codMeetingTelematico) {
		this.codMeetingTelematico = codMeetingTelematico;
	}

	public Date getDataMeetingTelematico() {
		return dataMeetingTelematico;
	}

	public void setDataMeetingTelematico(Date dataMeetingTelematico) {
		this.dataMeetingTelematico = dataMeetingTelematico;
	}

	public LocalTime getOraInizioMeetingTelematico() {
		return oraInizioMeetingTelematico;
	}

	public void setOraInizioMeetingTelematico(LocalTime oraInizioMeetingTelematico) {
		this.oraInizioMeetingTelematico = oraInizioMeetingTelematico;
	}

	public LocalTime getOraFineMeetingTelematico() {
		return oraFineMeetingTelematico;
	}

	public void setOraFineMeetingTelematico(LocalTime oraFineMeetingTelematico) {
		this.oraFineMeetingTelematico = oraFineMeetingTelematico;
	}

	public String getDescrizioneMeetingTelematico() {
		return descrizioneMeetingTelematico;
	}

	public void setDescrizioneMeetingTelematico(String descrizioneMeetingTelematico) {
		this.descrizioneMeetingTelematico = descrizioneMeetingTelematico;
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

}
