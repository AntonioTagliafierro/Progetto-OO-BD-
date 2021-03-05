package entity;

import java.util.ArrayList;

public class PartecipanteMeeting {
	private MeetingFisico meetingFisico;
	private MeetingTelematico meetingTelematico;
	private Dipendente dipendente;

	public PartecipanteMeeting() {
		super();
	}

	public MeetingFisico getMeetingFisico() {
		return meetingFisico;
	}

	public void setMeetingFisico(MeetingFisico meetingFisico) {
		this.meetingFisico = meetingFisico;
	}

	public MeetingTelematico getMeetingTelematico() {
		return meetingTelematico;
	}

	public void setMeetingTelematico(MeetingTelematico meetingTelematico) {
		this.meetingTelematico = meetingTelematico;
	}

	public Dipendente getDipendente() {
		return dipendente;
	}

	public void setDipendente(Dipendente dipendente) {
		this.dipendente = dipendente;
	}

}
