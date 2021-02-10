package entity;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class MeetingFisico  {
	
	private String codMeetingFisico;
	private String nomeMeetingFisico;
	private Date dataMeetingFisico;
	private Time oraInizioMeetingFisico;
	private int durataMeetingFisico;
	private String descrizioneMeetingFisico;
	private SalaMeeting sala;
	private ArrayList <Dipendente> partecipantiMeetingFisico;
	
	//Costruttori
	
	public MeetingFisico(String codMeetingFisico) {
		super();
		this.codMeetingFisico = codMeetingFisico;
	}

	public MeetingFisico(String nomeMeetingFisico, Date dataMeetingFisico, Time oraInizioMeetingFisico,
			int durataMeetingFisico, String descrizioneMeetingFisico, SalaMeeting sala) {
		super();
		this.nomeMeetingFisico = nomeMeetingFisico;
		this.dataMeetingFisico = dataMeetingFisico;
		this.oraInizioMeetingFisico = oraInizioMeetingFisico;
		this.durataMeetingFisico = durataMeetingFisico;
		this.descrizioneMeetingFisico = descrizioneMeetingFisico;
		this.sala = sala;
	}

	public MeetingFisico(String codMeetingFisico, String nomeMeetingFisico, Date dataMeetingFisico, SalaMeeting sala) {
		super();
		this.codMeetingFisico = codMeetingFisico;
		this.nomeMeetingFisico = nomeMeetingFisico;
		this.dataMeetingFisico = dataMeetingFisico;
		this.sala = sala;
	}
	

	public MeetingFisico(String codMeetingFisico, String nomeMeetingFisico, Date dataMeetingFisico,
			Time oraInizioMeetingFisico, int durataMeetingFisico, String descrizioneMeetingFisico, SalaMeeting sala,
			ArrayList<Dipendente> partecipantiMeetingFisico) {
		super();
		this.codMeetingFisico = codMeetingFisico;
		this.nomeMeetingFisico = nomeMeetingFisico;
		this.dataMeetingFisico = dataMeetingFisico;
		this.oraInizioMeetingFisico = oraInizioMeetingFisico;
		this.durataMeetingFisico = durataMeetingFisico;
		this.descrizioneMeetingFisico = descrizioneMeetingFisico;
		this.sala = sala;
		this.partecipantiMeetingFisico = partecipantiMeetingFisico;
	}

	//Getter e Setter
	
	public String getCodMeetingFisico() {
		return codMeetingFisico;
	}

	public void setCodMeetingFisico(String codMeetingFisico) {
		this.codMeetingFisico = codMeetingFisico;
	}

	public String getNomeMeetingFisico() {
		return nomeMeetingFisico;
	}

	public void setNomeMeetingFisico(String nomeMeetingFisico) {
		this.nomeMeetingFisico = nomeMeetingFisico;
	}

	public Date getDataMeetingFisico() {
		return dataMeetingFisico;
	}

	public void setDataMeetingFisico(Date dataMeetingFisico) {
		this.dataMeetingFisico = dataMeetingFisico;
	}

	public Time getOraInizioMeetingFisico() {
		return oraInizioMeetingFisico;
	}

	public void setOraInizioMeetingFisico(Time oraInizioMeetingFisico) {
		this.oraInizioMeetingFisico = oraInizioMeetingFisico;
	}

	public int getDurataMeetingFisico() {
		return durataMeetingFisico;
	}

	public void setDurataMeetingFisico(int durataMeetingFisico) {
		this.durataMeetingFisico = durataMeetingFisico;
	}

	public String getDescrizioneMeetingFisico() {
		return descrizioneMeetingFisico;
	}

	public void setDescrizioneMeetingFisico(String descrizioneMeetingFisico) {
		this.descrizioneMeetingFisico = descrizioneMeetingFisico;
	}

	public SalaMeeting getSala() {
		return sala;
	}

	public void setSala(SalaMeeting sala) {
		this.sala = sala;
	}

	public ArrayList<Dipendente> getPartecipantiMeetingFisico() {
		return partecipantiMeetingFisico;
	}

	public void setPartecipantiMeetingFisico(ArrayList<Dipendente> partecipantiMeetingFisico) {
		this.partecipantiMeetingFisico = partecipantiMeetingFisico;
	}

	
	
}