package entity;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class MeetingTelematico  {
	
	private String codMeetingTelematico;
	private String nomeMeetingTelematico;
	private Date dataMeetingTelematico;
	private Time oraInizioMeetingTelematico;
	private int durataMeetingTelematico;
	private String descrizioneMeetingTelematico;
	private PiattaformaMeeting piattaforma;
	private ArrayList <Dipendente> partecipantiMeetingTelematico;
	
	//Costruttori
	
	public MeetingTelematico(String codMeetingTelematico) {
		super();
		this.codMeetingTelematico = codMeetingTelematico;
	}

	public MeetingTelematico(String codMeetingTelematico, String nomeMeetingTelematico, Date dataMeetingTelematico,
			PiattaformaMeeting piattaforma) {
		super();
		this.codMeetingTelematico = codMeetingTelematico;
		this.nomeMeetingTelematico = nomeMeetingTelematico;
		this.dataMeetingTelematico = dataMeetingTelematico;
		this.piattaforma = piattaforma;
	}

	public MeetingTelematico(String nomeMeetingTelematico, Date dataMeetingTelematico, Time oraInizioMeetingTelematico,
			int durataMeetingTelematico, String descrizioneMeetingTelematico, PiattaformaMeeting piattaforma) {
		super();
		this.nomeMeetingTelematico = nomeMeetingTelematico;
		this.dataMeetingTelematico = dataMeetingTelematico;
		this.oraInizioMeetingTelematico = oraInizioMeetingTelematico;
		this.durataMeetingTelematico = durataMeetingTelematico;
		this.descrizioneMeetingTelematico = descrizioneMeetingTelematico;
		this.piattaforma = piattaforma;
	}

	
	public MeetingTelematico(String codMeetingTelematico, String nomeMeetingTelematico, Date dataMeetingTelematico,
			Time oraInizioMeetingTelematico, int durataMeetingTelematico, String descrizioneMeetingTelematico,
			PiattaformaMeeting piattaforma, ArrayList<Dipendente> partecipantiMeetingTelematico) {
		super();
		this.codMeetingTelematico = codMeetingTelematico;
		this.nomeMeetingTelematico = nomeMeetingTelematico;
		this.dataMeetingTelematico = dataMeetingTelematico;
		this.oraInizioMeetingTelematico = oraInizioMeetingTelematico;
		this.durataMeetingTelematico = durataMeetingTelematico;
		this.descrizioneMeetingTelematico = descrizioneMeetingTelematico;
		this.piattaforma = piattaforma;
		this.partecipantiMeetingTelematico = partecipantiMeetingTelematico;
	}

	//Getter e Setter
	
	public String getCodMeetingTelematico() {
		return codMeetingTelematico;
	}

	public void setCodMeetingTelematico(String codMeetingTelematico) {
		this.codMeetingTelematico = codMeetingTelematico;
	}

	public String getNomeMeetingTelematico() {
		return nomeMeetingTelematico;
	}

	public void setNomeMeetingTelematico(String nomeMeetingTelematico) {
		this.nomeMeetingTelematico = nomeMeetingTelematico;
	}

	public Date getDataMeetingTelematico() {
		return dataMeetingTelematico;
	}

	public void setDataMeetingTelematico(Date dataMeetingTelematico) {
		this.dataMeetingTelematico = dataMeetingTelematico;
	}

	public Time getOraInizioMeetingTelematico() {
		return oraInizioMeetingTelematico;
	}

	public void setOraInizioMeetingTelematico(Time oraInizioMeetingTelematico) {
		this.oraInizioMeetingTelematico = oraInizioMeetingTelematico;
	}

	public int getDurataMeetingTelematico() {
		return durataMeetingTelematico;
	}

	public void setDurataMeetingTelematico(int durataMeetingTelematico) {
		this.durataMeetingTelematico = durataMeetingTelematico;
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

