package entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class MeetingTelematico {

	private String codMeetingTelematico;
	private String nomeMeetingTelematico;
	private LocalDate dataMeetingTelematico;
	private LocalTime oraInizioMeetingTelematico;
	private int durataMeetingTelematico;
	private String descrizioneMeetingTelematico;
	private PiattaformaMeeting piattaforma;
	private ArrayList<Dipendente> partecipantiMeetingTelematico;

	// Costruttori

	public MeetingTelematico(String codMeetingTelematico) {
		super();
		this.codMeetingTelematico = codMeetingTelematico;
	}

	public MeetingTelematico(String codMeetingTelematico, String nomeMeetingTelematico, LocalDate dataMeetingTelematico,
			PiattaformaMeeting piattaforma) {
		super();
		this.codMeetingTelematico = codMeetingTelematico;
		this.nomeMeetingTelematico = nomeMeetingTelematico;
		this.dataMeetingTelematico = dataMeetingTelematico;
		this.piattaforma = piattaforma;
	}

	public MeetingTelematico(String nomeMeetingTelematico, LocalDate dataMeetingTelematico,
			LocalTime oraInizioMeetingTelematico, int durataMeetingTelematico, String descrizioneMeetingTelematico,
			PiattaformaMeeting piattaforma) {
		super();
		this.nomeMeetingTelematico = nomeMeetingTelematico;
		this.dataMeetingTelematico = dataMeetingTelematico;
		this.oraInizioMeetingTelematico = oraInizioMeetingTelematico;
		this.durataMeetingTelematico = durataMeetingTelematico;
		this.descrizioneMeetingTelematico = descrizioneMeetingTelematico;
		this.piattaforma = piattaforma;
	}

	public MeetingTelematico(String codMeetingTelematico, String nomeMeetingTelematico, LocalDate dataMeetingTelematico,
			LocalTime oraInizioMeetingTelematico, int durataMeetingTelematico, String descrizioneMeetingTelematico,
			PiattaformaMeeting piattaforma, ArrayList<Dipendente> partecipantiMeetingTelematico) {
		super();
		this.codMeetingTelematico = codMeetingTelematico;
		this.nomeMeetingTelematico = nomeMeetingTelematico;
		this.dataMeetingTelematico = dataMeetingTelematico;
		this.oraInizioMeetingTelematico = oraInizioMeetingTelematico;
		this.durataMeetingTelematico = durataMeetingTelematico;
		this.descrizioneMeetingTelematico = descrizioneMeetingTelematico;
		this.piattaforma = piattaforma;
		this.partecipantiMeetingTelematico = new ArrayList<Dipendente>();
	}

	// Getter e Setter

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

	public LocalDate getDataMeetingTelematico() {
		return dataMeetingTelematico;
	}

	public void setDataMeetingTelematico(LocalDate dataMeetingTelematico) {
		this.dataMeetingTelematico = dataMeetingTelematico;
	}

	public LocalTime getOraInizioMeetingTelematico() {
		return oraInizioMeetingTelematico;
	}

	public void setOraInizioMeetingTelematico(LocalTime oraInizioMeetingTelematico) {
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
		this.partecipantiMeetingTelematico = new ArrayList<Dipendente>();
	}

}
