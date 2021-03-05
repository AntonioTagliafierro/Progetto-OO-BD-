package dao_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import daos.MeetingDao;
import entity.Ambito;
import entity.Dipendente;
import entity.MeetingFisico;
import entity.MeetingTelematico;
import entity.PartecipanteMeeting;
import entity.PartecipanteProgetto;
import entity.PiattaformaMeeting;
import entity.Progetto;
import entity.SalaMeeting;

public class MeetingDaoImpl implements MeetingDao {

	private Connection connessione;

	// Prepared Statement
	private PreparedStatement recuperaMeetingsTelematiciPS;
	private PreparedStatement recuperaMeetingsFisiciPS;
	private PreparedStatement eliminaMeetingTelematicoPS;
	private PreparedStatement eliminaMeetingFisicoPS;
	private PreparedStatement recuperaNumeroPartecipantiMeetingFisicoPS;
	private PreparedStatement recuperaNumeroPartecipantiMeetingTelematicoPS;
	private PreparedStatement recuperaMeetingsTelematiciFiltratiPS;
	private PreparedStatement recuperaMeetingsFisiciFiltratiPS;
	private PreparedStatement salvaMeetingFisicoPS;
	private PreparedStatement salvaPartecipantiMeetingFisicoPS;
	private PreparedStatement recuperaCodiceMeetingFisicoPS;
	private PreparedStatement salvaMeetingTelematicoPS;
	private PreparedStatement salvaPartecipantiMeetingTelematicoPS;
	private PreparedStatement recuperaCodiceMeetingTelematicoPS;
	private PreparedStatement recuperaMeetingFisicoPS;
	private PreparedStatement recuperaMeetingTelematicoPS;
	private PreparedStatement recuperaPartecipantiMeetingFisicoPS;
	private PreparedStatement recuperaPartecipantiMeetingTelematicoPS;
	private PreparedStatement aggiornaMeetingFisicoPS;
	private PreparedStatement rimuoviPartecipantiMeetingFisicoPS;
	private PreparedStatement aggiornaMeetingTelematicoPS;
	private PreparedStatement rimuoviPartecipantiMeetingTelematicoPS;

	private ArrayList<MeetingTelematico> meetingsTelematici = new ArrayList<MeetingTelematico>();
	private ArrayList<MeetingFisico> meetingsFisici = new ArrayList<MeetingFisico>();

	public MeetingDaoImpl(Connection connection) {
		this.connessione = connection;

		try {
			rimuoviPartecipantiMeetingFisicoPS = connessione
					.prepareStatement("DELETE FROM PARTECIPANTEMEETINGFISICO WHERE CODF = ? AND CODMF = ? ");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		try {
			rimuoviPartecipantiMeetingTelematicoPS = connessione
					.prepareStatement("DELETE FROM PARTECIPANTEMEETINGTELEMATICO WHERE CODF = ? AND CODMT = ? ");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			aggiornaMeetingFisicoPS = connessione.prepareStatement(
					"UPDATE MEETINGFISICO SET DATAMF = ?,  ORAI = ? , ORAF = ? , NOMESALA = ?  WHERE CODMF = ?");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		try {
			aggiornaMeetingTelematicoPS = connessione.prepareStatement(
					"UPDATE MEETINGTELEMATICO SET DATAMT = ?,  ORAI = ? , ORAF = ? , NOMEPIATTAFORMA = ? WHERE CODMT = ? ");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			recuperaMeetingFisicoPS = connessione.prepareStatement("SELECT * FROM MEETINGFISICO WHERE CODMF = ?");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			recuperaMeetingTelematicoPS = connessione
					.prepareStatement("SELECT * FROM MEETINGTELEMATICO WHERE CODMT = ?");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			recuperaPartecipantiMeetingFisicoPS = connessione.prepareStatement(
					"SELECT CODF, NOME, COGNOME, VALUTAZIONE, SALARIO FROM DIPENDENTE NATURAL JOIN PARTECIPANTEMEETINGFISICO WHERE CODMF = ? ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			recuperaPartecipantiMeetingTelematicoPS = connessione.prepareStatement(
					"SELECT CODF, NOME, COGNOME, VALUTAZIONE, SALARIO FROM DIPENDENTE NATURAL JOIN PARTECIPANTEMEETINGTELEMATICO WHERE CODMT = ? ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			salvaMeetingFisicoPS = connessione.prepareStatement("INSERT INTO MEETINGFISICO VALUES (DEFAULT, ?,?,?,?) ");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			recuperaCodiceMeetingFisicoPS = connessione.prepareStatement("SELECT CURRVAL('meetingfisico_codmf_seq') ");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			salvaPartecipantiMeetingFisicoPS = connessione
					.prepareStatement("INSERT INTO PARTECIPANTEMEETINGFISICO VALUES (?,?) ");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		try {
			salvaMeetingTelematicoPS = connessione
					.prepareStatement("INSERT INTO MEETINGTELEMATICO VALUES (DEFAULT, ?,?,?,?) ");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			recuperaCodiceMeetingTelematicoPS = connessione
					.prepareStatement("SELECT CURRVAL('meetingtelematico_codmt_seq') ");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			salvaPartecipantiMeetingTelematicoPS = connessione
					.prepareStatement("INSERT INTO PARTECIPANTEMEETINGTELEMATICO VALUES (?,?) ");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			recuperaMeetingsTelematiciPS = connessione.prepareStatement("SELECT  * FROM MEETINGTELEMATICO");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			recuperaMeetingsFisiciPS = connessione.prepareStatement("SELECT  * FROM MEETINGFISICO ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			eliminaMeetingFisicoPS = connessione.prepareStatement("DELETE FROM MEETINGFISICO WHERE CODMF = ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			eliminaMeetingTelematicoPS = connessione.prepareStatement("DELETE FROM MEETINGTELEMATICO WHERE CODMT = ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			recuperaNumeroPartecipantiMeetingTelematicoPS = connessione.prepareStatement(
					"SELECT COUNT(CodF) AS NPARTECIPANTI  FROM PARTECIPANTEMEETINGTELEMATICO WHERE CodMT = ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			recuperaNumeroPartecipantiMeetingFisicoPS = connessione.prepareStatement(
					"SELECT COUNT(CodF)  AS NPARTECIPANTI FROM PARTECIPANTEMEETINGFISICO WHERE CodMF = ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			recuperaMeetingsTelematiciFiltratiPS = connessione
					.prepareStatement("SELECT  * FROM MEETINGTELEMATICO WHERE NOMEPIATTAFORMA = ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			recuperaMeetingsFisiciFiltratiPS = connessione
					.prepareStatement("SELECT  * FROM MEETINGFISICO WHERE NOMESALA = ? ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<MeetingTelematico> RecuperaMeetingsTelematici() throws SQLException {
		ArrayList<MeetingTelematico> meetingsTelematici = new ArrayList<MeetingTelematico>();
		ResultSet rs = recuperaMeetingsTelematiciPS.executeQuery();

		while (rs.next()) {
			MeetingTelematico meetingTelematico = new MeetingTelematico();
			PiattaformaMeeting piattaforma = new PiattaformaMeeting();

			meetingTelematico.setCodMeetingTelematico(String.valueOf(rs.getInt("CODMT")));
			int codice = Integer.valueOf(meetingTelematico.getCodMeetingTelematico());
			recuperaNumeroPartecipantiMeetingTelematicoPS.setInt(1, codice);
			ResultSet rsNum = recuperaNumeroPartecipantiMeetingTelematicoPS.executeQuery();
			rsNum.next();
			meetingTelematico.setNumeroPartecipanti(String.valueOf(rsNum.getInt("NPARTECIPANTI")));
			rsNum.close();
			meetingTelematico.setDataMeeting(rs.getDate("DATAMT"));
			meetingTelematico.setOraInizioMeeting(rs.getTime("ORAI"));
			meetingTelematico.setOraFineMeeting(rs.getTime("ORAF"));
			piattaforma.setNomePiattaforma(rs.getString("NOMEPIATTAFORMA"));
			meetingTelematico.setPiattaforma(piattaforma);
			meetingsTelematici.add(meetingTelematico);
		}
		rs.close();
		return meetingsTelematici;
	}

	@Override
	public ArrayList<MeetingFisico> RecuperaMeetingsFisici() throws SQLException {

		ArrayList<MeetingFisico> meetingsFisici = new ArrayList<MeetingFisico>();
		ResultSet rs = recuperaMeetingsFisiciPS.executeQuery();

		while (rs.next()) {
			MeetingFisico meetingFisico = new MeetingFisico();
			SalaMeeting sala = new SalaMeeting();
			meetingFisico.setCodMeetingFisico(String.valueOf(rs.getInt("CODMF")));
			int codice = Integer.valueOf(meetingFisico.getCodMeetingFisico());
			recuperaNumeroPartecipantiMeetingFisicoPS.setInt(1, codice);
			ResultSet rsNum = recuperaNumeroPartecipantiMeetingFisicoPS.executeQuery();
			rsNum.next();
			meetingFisico.setNumeroPartecipanti(String.valueOf(rsNum.getInt("NPARTECIPANTI")));
			rsNum.close();
			meetingFisico.setDataMeeting(rs.getDate("DATAMF"));
			meetingFisico.setOraInizioMeeting(rs.getTime("ORAI"));
			meetingFisico.setOraFineMeeting(rs.getTime("ORAF"));
			sala.setNomeSala(rs.getString("NOMESALA"));
			meetingFisico.setSala(sala);
			meetingsFisici.add(meetingFisico);
		}
		rs.close();
		return meetingsFisici;

	}

	@Override
	public void EliminaMeetingTelematico(int codiceMeeting) throws SQLException {

		eliminaMeetingTelematicoPS.setInt(1, codiceMeeting);
		eliminaMeetingTelematicoPS.executeUpdate();

	}

	@Override
	public void EliminaMeetingFisico(int codiceMeeting) throws SQLException {
		eliminaMeetingFisicoPS.setInt(1, codiceMeeting);
		eliminaMeetingFisicoPS.executeUpdate();

	}

	@Override
	public ArrayList<MeetingTelematico> RecuperaMeetingsTelematiciFiltrati(String nomepiattaforma) throws SQLException {
		ArrayList<MeetingTelematico> meetingsTelematici = new ArrayList<MeetingTelematico>();
		recuperaMeetingsTelematiciFiltratiPS.setString(1, nomepiattaforma);
		ResultSet rs = recuperaMeetingsTelematiciFiltratiPS.executeQuery();

		while (rs.next()) {
			MeetingTelematico meetingTelematico = new MeetingTelematico();
			PiattaformaMeeting piattaforma = new PiattaformaMeeting();

			meetingTelematico.setCodMeetingTelematico(String.valueOf(rs.getInt("CODMT")));
			int codice = Integer.valueOf(meetingTelematico.getCodMeetingTelematico());
			recuperaNumeroPartecipantiMeetingTelematicoPS.setInt(1, codice);
			ResultSet rsNum = recuperaNumeroPartecipantiMeetingTelematicoPS.executeQuery();
			rsNum.next();
			meetingTelematico.setNumeroPartecipanti(String.valueOf(rsNum.getInt("NPARTECIPANTI")));
			rsNum.close();
			meetingTelematico.setDataMeeting(rs.getDate("DATAMT"));
			meetingTelematico.setOraInizioMeeting(rs.getTime("ORAI"));
			meetingTelematico.setOraFineMeeting(rs.getTime("ORAF"));
			piattaforma.setNomePiattaforma(rs.getString("NOMEPIATTAFORMA"));
			meetingTelematico.setPiattaforma(piattaforma);
			meetingsTelematici.add(meetingTelematico);
		}
		rs.close();
		return meetingsTelematici;

	}

	@Override
	public ArrayList<MeetingFisico> RecuperaMeetingsFisiciFiltrati(String nomeSala) throws SQLException {
		ArrayList<MeetingFisico> meetingsFisici = new ArrayList<MeetingFisico>();
		recuperaMeetingsFisiciFiltratiPS.setString(1, nomeSala);
		ResultSet rs = recuperaMeetingsFisiciFiltratiPS.executeQuery();

		while (rs.next()) {
			MeetingFisico meetingFisico = new MeetingFisico();
			SalaMeeting sala = new SalaMeeting();
			meetingFisico.setCodMeetingFisico(String.valueOf(rs.getInt("CODMF")));
			int codice = Integer.valueOf(meetingFisico.getCodMeetingFisico());
			recuperaNumeroPartecipantiMeetingFisicoPS.setInt(1, codice);
			ResultSet rsNum = recuperaNumeroPartecipantiMeetingFisicoPS.executeQuery();
			rsNum.next();
			meetingFisico.setNumeroPartecipanti(String.valueOf(rsNum.getInt("NPARTECIPANTI")));
			rsNum.close();
			meetingFisico.setDataMeeting(rs.getDate("DATAMF"));
			meetingFisico.setOraInizioMeeting(rs.getTime("ORAI"));
			meetingFisico.setOraFineMeeting(rs.getTime("ORAF"));
			sala.setNomeSala(rs.getString("NOMESALA"));
			meetingFisico.setSala(sala);
			meetingsFisici.add(meetingFisico);
		}
		rs.close();
		return meetingsFisici;

	}

	@Override
	public void SalvaMeetingFisico(MeetingFisico meeting, ArrayList<PartecipanteMeeting> partecipanti)
			throws SQLException {

		long time = meeting.getDataMeeting().getTime();

		salvaMeetingFisicoPS.setDate(1, new java.sql.Date(time));
		salvaMeetingFisicoPS.setTime(2, meeting.getOraInizioMeeting());
		salvaMeetingFisicoPS.setTime(3, meeting.getOraFineMeeting());
		salvaMeetingFisicoPS.setString(4, meeting.getSala().getNomeSala());
		salvaMeetingFisicoPS.executeUpdate();

		int codiceM = 0;
		ResultSet rs = recuperaCodiceMeetingFisicoPS.executeQuery();
		if (rs.next()) {
			codiceM = (rs.getInt(1));
		}
		rs.close();

		for (PartecipanteMeeting p : partecipanti) {

			salvaPartecipantiMeetingFisicoPS.setString(1, p.getDipendente().getCodiceFiscale());
			salvaPartecipantiMeetingFisicoPS.setInt(2, codiceM);
			salvaPartecipantiMeetingFisicoPS.executeUpdate();

		}

	}

	@Override
	public void SalvaMeetingTelematico(MeetingTelematico meeting, ArrayList<PartecipanteMeeting> partecipanti)
			throws SQLException {

		long time = meeting.getDataMeeting().getTime();

		salvaMeetingTelematicoPS.setDate(1, new java.sql.Date(time));
		salvaMeetingTelematicoPS.setTime(2, meeting.getOraInizioMeeting());
		salvaMeetingTelematicoPS.setTime(3, meeting.getOraFineMeeting());
		salvaMeetingTelematicoPS.setString(4, meeting.getPiattaforma().getNomePiattaforma());
		salvaMeetingTelematicoPS.executeUpdate();

		int codiceM = 0;
		ResultSet rs = recuperaCodiceMeetingTelematicoPS.executeQuery();
		if (rs.next()) {
			codiceM = (rs.getInt(1));
		}
		rs.close();

		for (PartecipanteMeeting p : partecipanti) {

			salvaPartecipantiMeetingTelematicoPS.setString(1, p.getDipendente().getCodiceFiscale());
			salvaPartecipantiMeetingTelematicoPS.setInt(2, codiceM);
			salvaPartecipantiMeetingTelematicoPS.executeUpdate();

		}

	}

	@Override
	public MeetingFisico RecuperaMeetingFisico(int codice) throws SQLException {

		recuperaMeetingFisicoPS.setInt(1, codice);
		MeetingFisico meeting = new MeetingFisico();
		ResultSet rs = recuperaMeetingFisicoPS.executeQuery();

		if (rs.next()) {
			SalaMeeting sala = new SalaMeeting();
			meeting.setCodMeetingFisico(String.valueOf(rs.getInt("CODMF")));
			meeting.setDataMeeting(rs.getDate("DATAMF"));
			meeting.setOraInizioMeeting(rs.getTime("ORAI"));
			meeting.setOraFineMeeting(rs.getTime("ORAF"));
			sala.setNomeSala(rs.getString("NOMESALA"));
			meeting.setSala(sala);
		}
		rs.close();

		ArrayList<PartecipanteMeeting> partecipanti = new ArrayList<PartecipanteMeeting>();
		recuperaPartecipantiMeetingFisicoPS.setInt(1, codice);
		ResultSet rs_partecipanti = recuperaPartecipantiMeetingFisicoPS.executeQuery();
		while (rs_partecipanti.next()) {
			PartecipanteMeeting partecipante = new PartecipanteMeeting();
			Dipendente dipendente = new Dipendente();
			dipendente.setCodiceFiscale(rs_partecipanti.getString("CODF"));
			dipendente.setNome(rs_partecipanti.getString("NOME"));
			dipendente.setCognome(rs_partecipanti.getString("COGNOME"));
			dipendente.setValutazione(rs_partecipanti.getFloat("VALUTAZIONE"));
			dipendente.setSalarioMedio(rs_partecipanti.getBigDecimal("SALARIO").doubleValue());
			partecipante.setDipendente(dipendente);
			partecipanti.add(partecipante);

		}
		rs_partecipanti.close();
		meeting.setPartecipanti(partecipanti);

		return meeting;
	}

	@Override
	public MeetingTelematico RecuperaMeetingTelematico(int codice) throws SQLException {

		recuperaMeetingTelematicoPS.setInt(1, codice);
		MeetingTelematico meeting = new MeetingTelematico();
		ResultSet rs = recuperaMeetingTelematicoPS.executeQuery();

		if (rs.next()) {
			PiattaformaMeeting piattaforma = new PiattaformaMeeting();
			meeting.setCodMeetingTelematico(String.valueOf(rs.getInt("CODMT")));
			meeting.setDataMeeting(rs.getDate("DATAMT"));
			meeting.setOraInizioMeeting(rs.getTime("ORAI"));
			meeting.setOraFineMeeting(rs.getTime("ORAF"));
			piattaforma.setNomePiattaforma(rs.getString("NOMEPIATTAFORMA"));
			meeting.setPiattaforma(piattaforma);

		}
		rs.close();

		ArrayList<PartecipanteMeeting> partecipanti = new ArrayList<PartecipanteMeeting>();
		recuperaPartecipantiMeetingTelematicoPS.setInt(1, codice);
		ResultSet rs_partecipanti = recuperaPartecipantiMeetingTelematicoPS.executeQuery();
		while (rs_partecipanti.next()) {
			PartecipanteMeeting partecipante = new PartecipanteMeeting();
			Dipendente dipendente = new Dipendente();
			dipendente.setCodiceFiscale(rs_partecipanti.getString("CODF"));
			dipendente.setNome(rs_partecipanti.getString("NOME"));
			dipendente.setCognome(rs_partecipanti.getString("COGNOME"));
			dipendente.setValutazione(rs_partecipanti.getFloat("VALUTAZIONE"));
			dipendente.setSalarioMedio(rs_partecipanti.getBigDecimal("SALARIO").doubleValue());
			partecipante.setDipendente(dipendente);
			partecipanti.add(partecipante);

		}
		rs_partecipanti.close();
		meeting.setPartecipanti(partecipanti);

		return meeting;
	}

	@Override
	public void ModificaMeetingFisico(MeetingFisico meeting, ArrayList<PartecipanteMeeting> partecipantiDaAggiungere,
			ArrayList<PartecipanteMeeting> partecipantiDaEliminare) throws SQLException {

		int codice = Integer.parseInt(meeting.getCodMeetingFisico());

		long time = meeting.getDataMeeting().getTime();

		aggiornaMeetingFisicoPS.setDate(1, new java.sql.Date(time));
		aggiornaMeetingFisicoPS.setTime(2, meeting.getOraInizioMeeting());
		aggiornaMeetingFisicoPS.setTime(3, meeting.getOraFineMeeting());
		aggiornaMeetingFisicoPS.setString(4, meeting.getSala().getNomeSala());
		aggiornaMeetingFisicoPS.setInt(5, codice);

		aggiornaMeetingFisicoPS.executeUpdate();

		for (PartecipanteMeeting p : partecipantiDaAggiungere) {

			salvaPartecipantiMeetingFisicoPS.setString(1, p.getDipendente().getCodiceFiscale());
			salvaPartecipantiMeetingFisicoPS.setInt(2, codice);
			salvaPartecipantiMeetingFisicoPS.executeUpdate();

		}
		for (PartecipanteMeeting p : partecipantiDaEliminare) {

			rimuoviPartecipantiMeetingFisicoPS.setString(1, p.getDipendente().getCodiceFiscale());
			rimuoviPartecipantiMeetingFisicoPS.setInt(2, codice);
			rimuoviPartecipantiMeetingFisicoPS.executeUpdate();

		}

	}

	@Override
	public void ModificaMeetingTelematico(MeetingTelematico meeting,
			ArrayList<PartecipanteMeeting> partecipantiDaAggiungere,
			ArrayList<PartecipanteMeeting> partecipantiDaEliminare) throws SQLException {
		int codice = Integer.parseInt(meeting.getCodMeetingTelematico());

		long time = meeting.getDataMeeting().getTime();

		aggiornaMeetingTelematicoPS.setDate(1, new java.sql.Date(time));
		aggiornaMeetingTelematicoPS.setTime(2, meeting.getOraInizioMeeting());
		aggiornaMeetingTelematicoPS.setTime(3, meeting.getOraFineMeeting());
		aggiornaMeetingTelematicoPS.setString(4, meeting.getPiattaforma().getNomePiattaforma());
		aggiornaMeetingTelematicoPS.setInt(5, codice);

		aggiornaMeetingTelematicoPS.executeUpdate();

		for (PartecipanteMeeting p : partecipantiDaEliminare) {

			rimuoviPartecipantiMeetingTelematicoPS.setString(1, p.getDipendente().getCodiceFiscale());
			rimuoviPartecipantiMeetingTelematicoPS.setInt(2, codice);
			rimuoviPartecipantiMeetingTelematicoPS.executeUpdate();

		}

		for (PartecipanteMeeting p : partecipantiDaAggiungere) {

			salvaPartecipantiMeetingTelematicoPS.setString(1, p.getDipendente().getCodiceFiscale());
			salvaPartecipantiMeetingTelematicoPS.setInt(2, codice);
			salvaPartecipantiMeetingTelematicoPS.executeUpdate();

		}

	}
}
