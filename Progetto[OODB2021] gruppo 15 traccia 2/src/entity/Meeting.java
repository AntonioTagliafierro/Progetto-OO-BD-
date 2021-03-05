package entity;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

public class Meeting {

	private Time oraInizioMeeting;
	private Time oraFineMeeting;
	private String descrizioneMeeting;
	private Date dataMeeting;
	private String numeroPartecipanti;

	public Meeting() {
		super();
	}

	public Date getDataMeeting() {
		return dataMeeting;
	}

	public void setDataMeeting(Date dataMeeting) {
		this.dataMeeting = dataMeeting;
	}

	public Time getOraInizioMeeting() {
		return oraInizioMeeting;
	}

	public void setOraInizioMeeting(Time oraInizioMeeting) {
		this.oraInizioMeeting = oraInizioMeeting;
	}

	public Time getOraFineMeeting() {
		return oraFineMeeting;
	}

	public void setOraFineMeeting(Time oraFineMeeting) {
		this.oraFineMeeting = oraFineMeeting;
	}

	public String getDescrizioneMeeting() {
		return descrizioneMeeting;
	}

	public void setDescrizioneMeeting(String descrizioneMeeting) {
		this.descrizioneMeeting = descrizioneMeeting;
	}

	public String getNumeroPartecipanti() {
		return numeroPartecipanti;
	}

	public void setNumeroPartecipanti(String numeroPartecipanti) {
		this.numeroPartecipanti = numeroPartecipanti;
	}

}
