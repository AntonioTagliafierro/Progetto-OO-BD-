package daos;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.MeetingFisico;
import entity.MeetingTelematico;
import entity.PartecipanteMeeting;

public interface MeetingDao {

	public ArrayList<MeetingTelematico> RecuperaMeetingsTelematici() throws SQLException;

	public ArrayList<MeetingFisico> RecuperaMeetingsFisici() throws SQLException;

	public void EliminaMeetingTelematico(int codiceMeeting) throws SQLException;

	public void EliminaMeetingFisico(int codiceMeeting) throws SQLException;

	public ArrayList<MeetingTelematico> RecuperaMeetingsTelematiciFiltrati(String piattaforma) throws SQLException;

	public ArrayList<MeetingFisico> RecuperaMeetingsFisiciFiltrati(String sala) throws SQLException;

	public void SalvaMeetingFisico(MeetingFisico meeting, ArrayList<PartecipanteMeeting> partecipanti)
			throws SQLException;

	public void SalvaMeetingTelematico(MeetingTelematico meeting, ArrayList<PartecipanteMeeting> partecipanti)
			throws SQLException;

	public MeetingFisico RecuperaMeetingFisico(int codice) throws SQLException;

	public MeetingTelematico RecuperaMeetingTelematico(int codice) throws SQLException;

	public void ModificaMeetingFisico(MeetingFisico meeting, ArrayList<PartecipanteMeeting> partecipantiDaAggiungere,
			ArrayList<PartecipanteMeeting> partecipantiDaEliminare) throws SQLException;

	public void ModificaMeetingTelematico(MeetingTelematico meeting,
			ArrayList<PartecipanteMeeting> partecipantiDaAggiungere,
			ArrayList<PartecipanteMeeting> partecipantiDaEliminare) throws SQLException;

}
