package entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class MeetingFisico extends Meeting {

	private String codMeetingFisico;

	private SalaMeeting sala;
	private ArrayList<Dipendente> partecipantiMeetingFisico;
	private ArrayList<PartecipanteMeeting> partecipanti;

	// Costruttori

	public MeetingFisico(String codMeetingFisico) {
		super();
		this.codMeetingFisico = codMeetingFisico;
	}

	public ArrayList<PartecipanteMeeting> getPartecipanti() {
		return partecipanti;
	}

	public void setPartecipanti(ArrayList<PartecipanteMeeting> partecipanti) {
		this.partecipanti = partecipanti;
	}

	public MeetingFisico() {
		super();
	}

	// Getter e Setter

	public String getCodMeetingFisico() {
		return codMeetingFisico;
	}

	public void setCodMeetingFisico(String codMeetingFisico) {
		this.codMeetingFisico = codMeetingFisico;
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