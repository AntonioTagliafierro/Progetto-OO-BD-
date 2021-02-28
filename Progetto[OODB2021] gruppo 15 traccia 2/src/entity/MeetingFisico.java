package entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class MeetingFisico {

	private String codMeetingFisico;
	private Date dataMeetingFisico;
	private LocalTime oraInizioMeetingFisico;
	private LocalTime oraFineMeetingFisico;
	private String descrizioneMeetingFisico;
	private SalaMeeting sala;
	private ArrayList<Dipendente> partecipantiMeetingFisico;

	// Costruttori

	
	public MeetingFisico(String codMeetingFisico) {
		super();
		this.codMeetingFisico = codMeetingFisico;
	}

	public MeetingFisico() {
		super();
	}

	public MeetingFisico(Date dataMeetingFisico, LocalTime oraInizioMeetingFisico,
			LocalTime oraFineMeetingFisico, String descrizioneMeetingFisico, SalaMeeting sala) {
		super();
		this.dataMeetingFisico = dataMeetingFisico;
		this.oraInizioMeetingFisico = oraInizioMeetingFisico;
		this.oraFineMeetingFisico = oraFineMeetingFisico;
		this.descrizioneMeetingFisico = descrizioneMeetingFisico;
		this.sala = sala;
	}





	// Getter e Setter

	public String getCodMeetingFisico() {
		return codMeetingFisico;
	}

	public void setCodMeetingFisico(String codMeetingFisico) {
		this.codMeetingFisico = codMeetingFisico;
	}


	public Date getDataMeetingFisico() {
		return dataMeetingFisico;
	}

	public void setDataMeetingFisico(Date dataMeetingFisico) {
		this.dataMeetingFisico = dataMeetingFisico;
	}

	public LocalTime getOraInizioMeetingFisico() {
		return oraInizioMeetingFisico;
	}

	public void setOraInizioMeetingFisico(LocalTime oraInizioMeetingFisico) {
		this.oraInizioMeetingFisico = oraInizioMeetingFisico;
	}

	public LocalTime getOraFineMeetingFisico() {
		return oraFineMeetingFisico;
	}

	public void setOraFineoMeetingFisico(LocalTime oraFineMeetingFisico) {
		this.oraFineMeetingFisico = oraFineMeetingFisico;
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