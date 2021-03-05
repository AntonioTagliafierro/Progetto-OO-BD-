package dao_impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.jdi.DoubleValue;

import controller.Controller;
import daos.DipendenteDao;
import dbConfig.DBConnection;
import entity.Dipendente;
import entity.MeetingFisico;
import entity.MeetingTelematico;
import entity.PiattaformaMeeting;
import entity.ProgettiDelDipendente;
import entity.Residenza;
import entity.SalaMeeting;

public class DipendenteDaoImpl implements DipendenteDao {

	private Connection connessione;

//Prepared Statement
	private PreparedStatement inserisciDipendentePS;
	private PreparedStatement inserisciResidenzaPS;
	private PreparedStatement recuperaGeneralit‡PS;
	private PreparedStatement eliminaDipendentePS;
	private PreparedStatement disattivaDipendentePS;
	private PreparedStatement recuperaStatusPS;
	private PreparedStatement recuperaStatuseSalarioPS;
	private PreparedStatement recuperaConSalarioPS;
	private PreparedStatement recuperaStatuseValutazionePS;
	private PreparedStatement recuperaConValutazionePS;
	private PreparedStatement recuperaStatusSalarioeValutazionePS;
	private PreparedStatement recuperaConSalarioeValutazionePS;
	private PreparedStatement recuperaTuttoDipendentePS;
	private PreparedStatement recuperaProgettiDipendentePS;
	private PreparedStatement recuperaMeetingTDipendentePS;
	private PreparedStatement recuperaMeetingFDipendentePS;
	private PreparedStatement recuperaResidenzaDipendentePS;
	private PreparedStatement aggiornaDipendentePS;
	private PreparedStatement aggiornaResidenzaPS;
	private PreparedStatement attivaDipendentePS;
	private PreparedStatement recuperaDipendentiAttiviPS;
	private PreparedStatement recuperaGeneralit‡PerFiltriPS;
	private PreparedStatement recuperaPartecipanteProgettoTipologiaPS;
	private PreparedStatement recuperaPartecipanteProgettoTipologiaeValutazionePS;
	private PreparedStatement recuperaPartecipanteProgettoTipologiaeSalarioPS;
	private PreparedStatement recuperaPartecipanteProgettoTipologiaSalarioeValutazionePS;
	private PreparedStatement recuperaPartecipanteProgettoSalarioPS;
	private PreparedStatement recuperaPartecipanteProgettoValutazionePS;
	private PreparedStatement recuperaPartecipanteProgettoSalarioValutazionePS;
	private ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();

	public DipendenteDaoImpl(Connection connection) {

		this.connessione = connection;

		try {
			inserisciDipendentePS = connessione.prepareStatement(
					"INSERT INTO DIPENDENTE (CODF,NOME,COGNOME,EMAIL,DATAN,SESSO,NUMCELLULARE,PATHFOTO,SALARIO) VALUES(?,?,?,?,?,?,?,?,?)");
			inserisciResidenzaPS = connessione.prepareStatement("INSERT INTO RESIDENZA VALUES (?,?,?,?,?,?)");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			recuperaGeneralit‡PS = connessione
					.prepareStatement("SELECT  CODF,NOME,COGNOME,STATUS,VALUTAZIONE,SALARIO FROM DIPENDENTE");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			recuperaDipendentiAttiviPS = connessione.prepareStatement(
					"SELECT  CODF,NOME,COGNOME,STATUS,VALUTAZIONE,SALARIO FROM DIPENDENTE WHERE STATUS LIKE 'ATTIVO'");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			eliminaDipendentePS = connessione.prepareStatement("DELETE FROM DIPENDENTE WHERE CODF = ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			disattivaDipendentePS = connessione.prepareStatement("CALL  DISATTIVA_DIPENDENTE(?)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			attivaDipendentePS = connessione.prepareStatement("CALL ATTIVA_DIPENDENTE(?,?)");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			recuperaStatusPS = connessione.prepareStatement(
					"SELECT CODF,NOME,COGNOME,STATUS,VALUTAZIONE,SALARIO FROM DIPENDENTE WHERE STATUS LIKE ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			recuperaStatuseSalarioPS = connessione.prepareStatement(
					"SELECT CODF,NOME,COGNOME,STATUS,VALUTAZIONE,SALARIO FROM DIPENDENTE WHERE STATUS LIKE ? AND SALARIO BETWEEN ? AND ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			recuperaConSalarioPS = connessione.prepareStatement(
					"SELECT CODF,NOME,COGNOME,STATUS,VALUTAZIONE,SALARIO FROM DIPENDENTE WHERE  SALARIO BETWEEN ? AND ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			recuperaStatuseValutazionePS = connessione.prepareStatement(
					"SELECT CODF,NOME,COGNOME,STATUS,VALUTAZIONE,SALARIO FROM DIPENDENTE WHERE STATUS LIKE ? AND VALUTAZIONE BETWEEN ? AND ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			recuperaConValutazionePS = connessione.prepareStatement(
					"SELECT CODF,NOME,COGNOME,STATUS,VALUTAZIONE,SALARIO FROM DIPENDENTE WHERE  VALUTAZIONE BETWEEN ? AND ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			recuperaStatusSalarioeValutazionePS = connessione.prepareStatement(
					"SELECT CODF,NOME,COGNOME,STATUS,VALUTAZIONE,SALARIO FROM DIPENDENTE WHERE STATUS LIKE ? AND SALARIO BETWEEN ? AND ?  AND VALUTAZIONE BETWEEN ? AND ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			recuperaConSalarioeValutazionePS = connessione.prepareStatement(
					"SELECT CODF,NOME,COGNOME,STATUS,VALUTAZIONE,SALARIO FROM DIPENDENTE WHERE  SALARIO BETWEEN ? AND ?  AND  VALUTAZIONE BETWEEN ? AND ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			recuperaTuttoDipendentePS = connessione.prepareStatement("SELECT * FROM DIPENDENTE WHERE CODF = ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			recuperaResidenzaDipendentePS = connessione.prepareStatement("SELECT * FROM RESIDENZA WHERE CODF = ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			recuperaProgettiDipendentePS = connessione.prepareStatement(
					"SELECT NOMEPROGETTO,TIPOLOGIA,RUOLO FROM dipendenti_dei_progetti WHERE CODF = ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			recuperaMeetingTDipendentePS = connessione.prepareStatement(
					"SELECT CODMT,DATAMT,NOMEPIATTAFORMA FROM dipendenti_dei_meetingt WHERE CODF = ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			recuperaMeetingFDipendentePS = connessione
					.prepareStatement("SELECT CODMF,DATAMF,NOMESALA FROM dipendenti_dei_meetingf WHERE CODF = ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			aggiornaDipendentePS = connection.prepareStatement(
					"UPDATE DIPENDENTE SET  CODF = ?,NOME = ?,COGNOME =?,EMAIL = ?, DATAN = ?,SESSO=?,NUMCELLULARE=?,PATHFOTO=?,SALARIO=? WHERE CODF = ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			aggiornaResidenzaPS = connection.prepareStatement(
					"UPDATE RESIDENZA SET CODF=?,PROVINCIA=?,CAP=?,VIA=?,NCIVICO=?,citt‡=? WHERE CODF=?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			recuperaGeneralit‡PerFiltriPS = connessione.prepareStatement(
					"SELECT CODF,NOME, COGNOME, VALUTAZIONE, SALARIO FROM DIPENDENTE WHERE STATUS LIKE 'ATTIVO' ");

		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			recuperaPartecipanteProgettoTipologiaPS = connessione.prepareStatement(
					"SELECT CODF,NOME,COGNOME,SALARIO,VALUTAZIONE FROM progetti_per_tipologia NATURAL JOIN DIPENDENTE WHERE TIPOLOGIA LIKE ? AND STATUS LIKE 'ATTIVO' ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			recuperaPartecipanteProgettoTipologiaeValutazionePS = connessione.prepareStatement(
					"SELECT CODF,NOME,COGNOME,SALARIO,VALUTAZIONE FROM progetti_per_tipologia NATURAL JOIN DIPENDENTE WHERE TIPOLOGIA LIKE ? AND VALUTAZIONE BETWEEN ? AND ? AND STATUS LIKE 'ATTIVO' ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			recuperaPartecipanteProgettoTipologiaeSalarioPS = connessione.prepareStatement(
					"SELECT CODF,NOME,COGNOME,SALARIO,VALUTAZIONE FROM progetti_per_tipologia NATURAL JOIN DIPENDENTE WHERE TIPOLOGIA LIKE ? AND SALARIO BETWEEN ? AND ? AND STATUS LIKE 'ATTIVO' ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			recuperaPartecipanteProgettoTipologiaSalarioeValutazionePS = connessione.prepareStatement(
					"SELECT CODF,NOME,COGNOME,SALARIO,VALUTAZIONE FROM progetti_per_tipologia NATURAL JOIN DIPENDENTE WHERE TIPOLOGIA LIKE ? AND SALARIO BETWEEEN ? AND ? AND VALUTAZIONE BETWEEN ? AND ? AND STATUS LIKE 'ATTIVO' ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			recuperaPartecipanteProgettoSalarioPS = connessione.prepareStatement(
					"SELECT CODF,NOME,COGNOME,SALARIO,VALUTAZIONE FROM DIPENDENTE  WHERE SALARIO BETWEEN ? AND ? AND STATUS LIKE 'ATTIVO' ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			recuperaPartecipanteProgettoValutazionePS = connessione.prepareStatement(
					"SELECT CODF,NOME,COGNOME,SALARIO,VALUTAZIONE FROM DIPENDENTE WHERE VALUTAZIONE BETWEEN ? AND ? AND STATUS LIKE 'ATTIVO' ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			recuperaPartecipanteProgettoSalarioValutazionePS = connessione.prepareStatement(
					"SELECT CODF,NOME,COGNOME,SALARIO,VALUTAZIONE FROM DIPENDENTE WHERE SALARIO BETWEEN ? AND ? AND VALUTAZIONE BETWEEN ? AND ? AND STATUS LIKE 'ATTIVO' ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void InserisciDipendente(Dipendente dipendente) throws SQLException {

		long time = dipendente.getDataNascita().getTime();

		inserisciDipendentePS.setString(1, dipendente.getCodiceFiscale().toUpperCase());
		inserisciDipendentePS.setString(2, dipendente.getNome().toUpperCase());
		inserisciDipendentePS.setString(3, dipendente.getCognome().toUpperCase());
		inserisciDipendentePS.setString(4, dipendente.getEmail().toLowerCase());
		inserisciDipendentePS.setDate(5, new java.sql.Date(time));

		inserisciDipendentePS.setString(6, dipendente.getSesso());

		inserisciDipendentePS.setString(7, dipendente.getnCellulare());
		inserisciDipendentePS.setString(8, dipendente.getPathFoto());
		inserisciDipendentePS.setBigDecimal(9, BigDecimal.valueOf(dipendente.getSalarioMedio()));

		inserisciDipendentePS.executeUpdate();

	}

	@Override
	public void InserisciResidenza(Dipendente dipendente) throws SQLException {

		inserisciResidenzaPS.setString(1, dipendente.getCodiceFiscale().toUpperCase());
		inserisciResidenzaPS.setString(2, dipendente.getResidenza().getProvincia());
		inserisciResidenzaPS.setString(3, dipendente.getResidenza().getCap());
		inserisciResidenzaPS.setString(4, dipendente.getResidenza().getVia().toUpperCase());
		inserisciResidenzaPS.setString(5, dipendente.getResidenza().getnCivico());
		inserisciResidenzaPS.setString(6, dipendente.getResidenza().getCitt‡().toUpperCase());
		inserisciResidenzaPS.executeUpdate();

	}

	@Override
	public void AggiornaDipendente(Dipendente dipendente, String vecchioCF) throws SQLException {
		aggiornaResidenzaPS.setString(1, dipendente.getCodiceFiscale().toUpperCase());
		aggiornaResidenzaPS.setString(2, dipendente.getResidenza().getProvincia());
		aggiornaResidenzaPS.setString(3, dipendente.getResidenza().getCap());
		aggiornaResidenzaPS.setString(4, dipendente.getResidenza().getVia().toUpperCase());
		aggiornaResidenzaPS.setString(5, dipendente.getResidenza().getnCivico());
		aggiornaResidenzaPS.setString(6, dipendente.getResidenza().getCitt‡().toUpperCase());
		aggiornaResidenzaPS.setString(7, vecchioCF);
		aggiornaResidenzaPS.executeUpdate();
		long time = dipendente.getDataNascita().getTime();
		aggiornaDipendentePS.setString(1, dipendente.getCodiceFiscale().toUpperCase());
		aggiornaDipendentePS.setString(2, dipendente.getNome().toUpperCase());
		aggiornaDipendentePS.setString(3, dipendente.getCognome().toUpperCase());
		aggiornaDipendentePS.setString(4, dipendente.getEmail().toLowerCase());
		aggiornaDipendentePS.setDate(5, new java.sql.Date(time));

		aggiornaDipendentePS.setString(6, dipendente.getSesso());

		aggiornaDipendentePS.setString(7, dipendente.getnCellulare());
		aggiornaDipendentePS.setString(8, dipendente.getPathFoto());
		aggiornaDipendentePS.setBigDecimal(9, BigDecimal.valueOf(dipendente.getSalarioMedio()));
		aggiornaDipendentePS.setString(10, vecchioCF);

		aggiornaDipendentePS.executeUpdate();

	}

	@Override
	public ArrayList<Dipendente> RecuperaGeneralit‡Dipendenti() throws SQLException {

		ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();
		ResultSet rs = recuperaGeneralit‡PS.executeQuery();

		while (rs.next()) {
			Dipendente dipendente = new Dipendente();
			dipendente.setCodiceFiscale(rs.getString("CODF"));
			dipendente.setNome(rs.getString("NOME"));
			dipendente.setCognome(rs.getString("COGNOME"));
			dipendente.setStatus(rs.getString("STATUS"));
			dipendente.setValutazione(rs.getFloat("VALUTAZIONE"));
			dipendente.setSalarioMedio(rs.getBigDecimal("SALARIO").doubleValue());

			dipendenti.add(dipendente);
		}
		rs.close();

		return dipendenti;
	}

	@Override
	public void EliminaDipendente(String codiceFiscale) throws SQLException {

		eliminaDipendentePS.setString(1, codiceFiscale);
		eliminaDipendentePS.executeUpdate();

	}

	@Override
	public void DisattivaDipendente(String codiceFiscale) throws SQLException {

		disattivaDipendentePS.setString(1, codiceFiscale);
		disattivaDipendentePS.execute();

	}

	@Override
	public void AttivaDipendente(String codiceFiscale, Double salario) throws SQLException {
		attivaDipendentePS.setString(1, codiceFiscale);
		attivaDipendentePS.setBigDecimal(2, BigDecimal.valueOf(salario));
		attivaDipendentePS.execute();
	}

	@Override
	public ArrayList<Dipendente> RecuperaDipendentiStatus(String status) throws SQLException {
		recuperaStatusPS.setString(1, status);
		ResultSet rs = recuperaStatusPS.executeQuery();
		ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();

		while (rs.next()) {
			Dipendente dipendente = new Dipendente();
			dipendente.setCodiceFiscale(rs.getString("CODF"));
			dipendente.setNome(rs.getString("NOME"));
			dipendente.setCognome(rs.getString("COGNOME"));
			dipendente.setStatus(rs.getString("STATUS"));
			dipendente.setValutazione(rs.getFloat("VALUTAZIONE"));
			dipendente.setSalarioMedio(rs.getBigDecimal("SALARIO").doubleValue());

			dipendenti.add(dipendente);
		}
		rs.close();

		return dipendenti;

	}

	@Override
	public ArrayList<Dipendente> RecuperaDipendentiStatuseSalario(String status, Double salario) throws SQLException {
		BigDecimal minSalario = BigDecimal.valueOf(salario - 100);
		BigDecimal maxSalario = BigDecimal.valueOf(salario + 100);
		recuperaStatuseSalarioPS.setString(1, status);
		recuperaStatuseSalarioPS.setBigDecimal(2, minSalario);
		recuperaStatuseSalarioPS.setBigDecimal(3, maxSalario);
		ResultSet rs = recuperaStatuseSalarioPS.executeQuery();
		ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();

		while (rs.next()) {
			Dipendente dipendente = new Dipendente();
			dipendente.setCodiceFiscale(rs.getString("CODF"));
			dipendente.setNome(rs.getString("NOME"));
			dipendente.setCognome(rs.getString("COGNOME"));
			dipendente.setStatus(rs.getString("STATUS"));
			dipendente.setValutazione(rs.getFloat("VALUTAZIONE"));
			dipendente.setSalarioMedio(rs.getBigDecimal("SALARIO").doubleValue());

			dipendenti.add(dipendente);
		}
		rs.close();

		return dipendenti;

	}

	@Override
	public ArrayList<Dipendente> RecuperaDipendentiSalario(Double salario) throws SQLException {
		BigDecimal minSalario = BigDecimal.valueOf(salario - 100);
		BigDecimal maxSalario = BigDecimal.valueOf(salario + 100);
		recuperaConSalarioPS.setBigDecimal(1, minSalario);
		recuperaConSalarioPS.setBigDecimal(2, maxSalario);
		ResultSet rs = recuperaConSalarioPS.executeQuery();
		ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();

		while (rs.next()) {
			Dipendente dipendente = new Dipendente();
			dipendente.setCodiceFiscale(rs.getString("CODF"));
			dipendente.setNome(rs.getString("NOME"));
			dipendente.setCognome(rs.getString("COGNOME"));
			dipendente.setStatus(rs.getString("STATUS"));
			dipendente.setValutazione(rs.getFloat("VALUTAZIONE"));
			dipendente.setSalarioMedio(rs.getBigDecimal("SALARIO").doubleValue());

			dipendenti.add(dipendente);
		}
		rs.close();

		return dipendenti;

	}

	@Override
	public ArrayList<Dipendente> RecuperaDipendentiStatuseValutazione(String status, Float valutazione)
			throws SQLException {
		float minValutazione = valutazione - 1;
		float maxValutazione = valutazione + 1;
		recuperaStatuseValutazionePS.setString(1, status);
		recuperaStatuseValutazionePS.setFloat(2, minValutazione);
		recuperaStatuseValutazionePS.setFloat(3, maxValutazione);
		ResultSet rs = recuperaStatuseValutazionePS.executeQuery();
		ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();

		while (rs.next()) {
			Dipendente dipendente = new Dipendente();
			dipendente.setCodiceFiscale(rs.getString("CODF"));
			dipendente.setNome(rs.getString("NOME"));
			dipendente.setCognome(rs.getString("COGNOME"));
			dipendente.setStatus(rs.getString("STATUS"));
			dipendente.setValutazione(rs.getFloat("VALUTAZIONE"));
			dipendente.setSalarioMedio(rs.getBigDecimal("SALARIO").doubleValue());

			dipendenti.add(dipendente);
		}
		rs.close();

		return dipendenti;
	}

	@Override
	public ArrayList<Dipendente> RecuperaDipendentiValutazione(Float valutazione) throws SQLException {
		float minValutazione = valutazione - 1;
		float maxValutazione = valutazione + 1;
		recuperaConValutazionePS.setFloat(1, minValutazione);
		recuperaConValutazionePS.setFloat(2, maxValutazione);
		ResultSet rs = recuperaConValutazionePS.executeQuery();
		ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();

		while (rs.next()) {
			Dipendente dipendente = new Dipendente();
			dipendente.setCodiceFiscale(rs.getString("CODF"));
			dipendente.setNome(rs.getString("NOME"));
			dipendente.setCognome(rs.getString("COGNOME"));
			dipendente.setStatus(rs.getString("STATUS"));
			dipendente.setValutazione(rs.getFloat("VALUTAZIONE"));
			dipendente.setSalarioMedio(rs.getBigDecimal("SALARIO").doubleValue());

			dipendenti.add(dipendente);
		}
		rs.close();

		return dipendenti;
	}

	@Override
	public ArrayList<Dipendente> RecuperaDipendentiStatusSalarioeValutazione(String status, Double salario,
			Float valutazione) throws SQLException {
		BigDecimal minSalario = BigDecimal.valueOf(salario - 100);
		BigDecimal maxSalario = BigDecimal.valueOf(salario + 100);
		float minValutazione = valutazione - 1;
		float maxValutazione = valutazione + 1;
		recuperaStatusSalarioeValutazionePS.setString(1, status);
		recuperaStatusSalarioeValutazionePS.setBigDecimal(2, minSalario);
		recuperaStatusSalarioeValutazionePS.setBigDecimal(3, maxSalario);
		recuperaStatusSalarioeValutazionePS.setFloat(4, minValutazione);
		recuperaStatusSalarioeValutazionePS.setFloat(5, maxValutazione);
		ResultSet rs = recuperaStatusSalarioeValutazionePS.executeQuery();
		ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();

		while (rs.next()) {
			Dipendente dipendente = new Dipendente();
			dipendente.setCodiceFiscale(rs.getString("CODF"));
			dipendente.setNome(rs.getString("NOME"));
			dipendente.setCognome(rs.getString("COGNOME"));
			dipendente.setStatus(rs.getString("STATUS"));
			dipendente.setValutazione(rs.getFloat("VALUTAZIONE"));
			dipendente.setSalarioMedio(rs.getBigDecimal("SALARIO").doubleValue());

			dipendenti.add(dipendente);
		}
		rs.close();

		return dipendenti;

	}

	@Override
	public ArrayList<Dipendente> RecuperaDipendentiSalarioeValutazione(Double salario, Float valutazione)
			throws SQLException {
		BigDecimal minSalario = BigDecimal.valueOf(salario - 100);
		BigDecimal maxSalario = BigDecimal.valueOf(salario + 100);
		float minValutazione = valutazione - 1;
		float maxValutazione = valutazione + 1;

		recuperaConSalarioeValutazionePS.setBigDecimal(1, minSalario);
		recuperaConSalarioeValutazionePS.setBigDecimal(2, maxSalario);
		recuperaConSalarioeValutazionePS.setFloat(3, minValutazione);
		recuperaConSalarioeValutazionePS.setFloat(4, maxValutazione);
		ResultSet rs = recuperaConSalarioeValutazionePS.executeQuery();
		ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();

		while (rs.next()) {
			Dipendente dipendente = new Dipendente();
			dipendente.setCodiceFiscale(rs.getString("CODF"));
			dipendente.setNome(rs.getString("NOME"));
			dipendente.setCognome(rs.getString("COGNOME"));
			dipendente.setStatus(rs.getString("STATUS"));
			dipendente.setValutazione(rs.getFloat("VALUTAZIONE"));
			dipendente.setSalarioMedio(rs.getBigDecimal("SALARIO").doubleValue());

			dipendenti.add(dipendente);
		}
		rs.close();

		return dipendenti;

	}

	@Override
	public Dipendente RecuperaTuttoDaDipendente(String codiceFiscale) throws SQLException {
		recuperaTuttoDipendentePS.setString(1, codiceFiscale);
		Dipendente dipendente = new Dipendente();
		ResultSet rs = recuperaTuttoDipendentePS.executeQuery();

		if (rs.next()) {

			dipendente.setCodiceFiscale(rs.getString("CODF"));
			dipendente.setNome(rs.getString("NOME"));
			dipendente.setCognome(rs.getString("COGNOME"));
			dipendente.setEmail(rs.getString("EMAIL"));
			dipendente.setDataNascita(rs.getDate("DATAN"));
			dipendente.setSesso(rs.getString("SESSO"));
			dipendente.setnCellulare(rs.getString("NUMCELLULARE"));
			dipendente.setPathFoto(rs.getString("PATHFOTO"));
			dipendente.setSalarioMedio(rs.getBigDecimal("SALARIO").doubleValue());
			dipendente.setValutazione(rs.getFloat("VALUTAZIONE"));
			dipendente.setStatus(rs.getString("STATUS"));
		}
		rs.close();

		recuperaResidenzaDipendentePS.setString(1, codiceFiscale);
		ResultSet rsResidenza = recuperaResidenzaDipendentePS.executeQuery();
		if (rsResidenza.next()) {
			Residenza residenza = new Residenza();
			residenza.setProvincia(rsResidenza.getString("PROVINCIA"));
			residenza.setCap(rsResidenza.getString("CAP"));
			residenza.setVia(rsResidenza.getString("VIA"));
			residenza.setnCivico(rsResidenza.getString("NCivico"));
			residenza.setCitt‡(rsResidenza.getString("citt‡"));
			dipendente.setResidenza(residenza);
		}
		rsResidenza.close();

		recuperaProgettiDipendentePS.setString(1, codiceFiscale);
		ResultSet rsProgetti = recuperaProgettiDipendentePS.executeQuery();
		ArrayList<ProgettiDelDipendente> progettiDipendente = new ArrayList<ProgettiDelDipendente>();

		while (rsProgetti.next()) {
			ProgettiDelDipendente progetto = new ProgettiDelDipendente();
			progetto.setNomeProgetto(rsProgetti.getString("NOMEPROGETTO"));
			progetto.setTipologia(rsProgetti.getString("TIPOLOGIA"));
			progetto.setRuolo(rsProgetti.getString("RUOLO"));

			progettiDipendente.add(progetto);

		}
		rsProgetti.close();

		dipendente.setProgettiDipendente(progettiDipendente);

		recuperaMeetingTDipendentePS.setString(1, codiceFiscale);
		ResultSet rsMeetingT = recuperaMeetingTDipendentePS.executeQuery();
		ArrayList<MeetingTelematico> meetingsTelematici = new ArrayList<MeetingTelematico>();

		while (rsMeetingT.next()) {
			MeetingTelematico meetingTelematico = new MeetingTelematico();
			PiattaformaMeeting piattaforma = new PiattaformaMeeting();

			meetingTelematico.setCodMeetingTelematico(String.valueOf(rsMeetingT.getInt("CODMT")));
			meetingTelematico.setDataMeeting(rsMeetingT.getDate("DATAMT"));
			piattaforma.setNomePiattaforma(rsMeetingT.getString("NOMEPIATTAFORMA"));
			meetingTelematico.setPiattaforma(piattaforma);
			meetingsTelematici.add(meetingTelematico);
		}
		rsMeetingT.close();

		dipendente.setMeetingTelematici(meetingsTelematici);

		recuperaMeetingFDipendentePS.setString(1, codiceFiscale);
		ResultSet rsMeetingF = recuperaMeetingFDipendentePS.executeQuery();
		ArrayList<MeetingFisico> meetingsFisici = new ArrayList<MeetingFisico>();

		while (rsMeetingF.next()) {
			MeetingFisico meetingFisico = new MeetingFisico();
			SalaMeeting sala = new SalaMeeting();

			meetingFisico.setCodMeetingFisico(String.valueOf(rsMeetingF.getInt("CODMF")));
			meetingFisico.setDataMeeting(rsMeetingF.getDate("DATAMF"));
			sala.setNomeSala(rsMeetingF.getString("NOMESALA"));
			meetingFisico.setSala(sala);
			meetingsFisici.add(meetingFisico);
		}
		rsMeetingF.close();

		dipendente.setMeetingFisici(meetingsFisici);

		return dipendente;

	}

	@Override
	public ArrayList<Dipendente> RecuperaDipendentiAttivi() throws SQLException {
		ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();
		ResultSet rs = recuperaDipendentiAttiviPS.executeQuery();

		while (rs.next()) {
			Dipendente dipendente = new Dipendente();
			dipendente.setCodiceFiscale(rs.getString("CODF"));
			dipendente.setNome(rs.getString("NOME"));
			dipendente.setCognome(rs.getString("COGNOME"));
			dipendente.setValutazione(rs.getFloat("VALUTAZIONE"));
			dipendente.setSalarioMedio(rs.getBigDecimal("SALARIO").doubleValue());

			dipendenti.add(dipendente);
		}
		rs.close();

		return dipendenti;
	}

	public ArrayList<Dipendente> RecuperaGeneralit‡DipendentiPerFiltri() throws SQLException {

		ResultSet rs = recuperaGeneralit‡PerFiltriPS.executeQuery();
		ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();

		while (rs.next()) {

			Dipendente dipendente = new Dipendente();
			dipendente.setCodiceFiscale(rs.getString("CODF"));
			dipendente.setNome(rs.getString("NOME"));
			dipendente.setCognome(rs.getString("COGNOME"));
			dipendente.setValutazione(rs.getFloat("VALUTAZIONE"));
			dipendente.setSalarioMedio(rs.getBigDecimal("SALARIO").doubleValue());

			dipendenti.add(dipendente);
		}
		rs.close();

		return dipendenti;

	}

	@Override
	public ArrayList<Dipendente> RecuperaPartecipantiProgettoTipologia(String tipologia) throws SQLException {

		recuperaPartecipanteProgettoTipologiaPS.setString(1, tipologia.toUpperCase());

		ResultSet rs = recuperaPartecipanteProgettoTipologiaPS.executeQuery();
		ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();

		while (rs.next()) {

			Dipendente dipendente = new Dipendente();
			dipendente.setCodiceFiscale(rs.getString("CODF"));
			dipendente.setNome(rs.getString("NOME"));
			dipendente.setCognome(rs.getString("COGNOME"));
			dipendente.setValutazione(rs.getFloat("VALUTAZIONE"));
			dipendente.setSalarioMedio(rs.getBigDecimal("SALARIO").doubleValue());

			dipendenti.add(dipendente);
		}
		rs.close();

		return dipendenti;

	}

	@Override
	public ArrayList<Dipendente> RecuperaPartecipantiProgettoTipologiaeValutazione(String tipologia, float valutazione)
			throws SQLException {

		float min_valutazione = valutazione - 1;
		float max_valutazione = valutazione + 1;
		recuperaPartecipanteProgettoTipologiaeValutazionePS.setString(1, tipologia.toUpperCase());
		recuperaPartecipanteProgettoTipologiaeValutazionePS.setFloat(2, min_valutazione);
		recuperaPartecipanteProgettoTipologiaeValutazionePS.setFloat(3, max_valutazione);
		ResultSet rs = recuperaPartecipanteProgettoTipologiaeValutazionePS.executeQuery();

		ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();

		while (rs.next()) {

			Dipendente dipendente = new Dipendente();
			dipendente.setCodiceFiscale(rs.getString("CODF"));
			dipendente.setNome(rs.getString("NOME"));
			dipendente.setCognome(rs.getString("COGNOME"));
			dipendente.setValutazione(rs.getFloat("VALUTAZIONE"));
			dipendente.setSalarioMedio(rs.getBigDecimal("SALARIO").doubleValue());

			dipendenti.add(dipendente);
		}
		rs.close();

		return dipendenti;

	}

	@Override
	public ArrayList<Dipendente> RecuperaPartecipantiProgettoTipologiaeSalario(String tipologia, double salario)
			throws SQLException {

		BigDecimal minSalario = BigDecimal.valueOf(salario - 100);
		BigDecimal maxSalario = BigDecimal.valueOf(salario + 100);
		recuperaPartecipanteProgettoTipologiaeSalarioPS.setString(1, tipologia.toUpperCase());
		recuperaPartecipanteProgettoTipologiaeSalarioPS.setBigDecimal(2, minSalario);
		recuperaPartecipanteProgettoTipologiaeSalarioPS.setBigDecimal(2, maxSalario);

		ResultSet rs = recuperaPartecipanteProgettoTipologiaeSalarioPS.executeQuery();

		ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();
		while (rs.next()) {

			Dipendente dipendente = new Dipendente();
			dipendente.setCodiceFiscale(rs.getString("CODF"));
			dipendente.setNome(rs.getString("NOME"));
			dipendente.setCognome(rs.getString("COGNOME"));
			dipendente.setValutazione(rs.getFloat("VALUTAZIONE"));
			dipendente.setSalarioMedio(rs.getBigDecimal("SALARIO").doubleValue());

			dipendenti.add(dipendente);
		}
		rs.close();

		return dipendenti;
	}

	@Override
	public ArrayList<Dipendente> RecuperaPartecipantiProgettoTipologiaSalarioValutazione(String tipologia,
			double salario, float valutazione) throws SQLException {

		float min_valutazione = valutazione - 1;
		float max_valutazione = valutazione + 1;
		BigDecimal minSalario = BigDecimal.valueOf(salario - 100);
		BigDecimal maxSalario = BigDecimal.valueOf(salario + 100);
		recuperaPartecipanteProgettoTipologiaSalarioeValutazionePS.setString(1, tipologia.toUpperCase());
		recuperaPartecipanteProgettoTipologiaSalarioeValutazionePS.setBigDecimal(2, minSalario);
		recuperaPartecipanteProgettoTipologiaSalarioeValutazionePS.setBigDecimal(3, maxSalario);
		recuperaPartecipanteProgettoTipologiaeValutazionePS.setFloat(4, min_valutazione);
		recuperaPartecipanteProgettoTipologiaeValutazionePS.setFloat(5, max_valutazione);

		ResultSet rs = recuperaPartecipanteProgettoTipologiaSalarioeValutazionePS.executeQuery();
		ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();
		while (rs.next()) {

			Dipendente dipendente = new Dipendente();
			dipendente.setCodiceFiscale(rs.getString("CODF"));
			dipendente.setNome(rs.getString("NOME"));
			dipendente.setCognome(rs.getString("COGNOME"));
			dipendente.setValutazione(rs.getFloat("VALUTAZIONE"));
			dipendente.setSalarioMedio(rs.getBigDecimal("SALARIO").doubleValue());

			dipendenti.add(dipendente);
		}
		rs.close();

		return dipendenti;
	}

	@Override
	public ArrayList<Dipendente> RecuperaPartecipantiProgettoSalario(double salario) throws SQLException {

		BigDecimal minSalario = BigDecimal.valueOf(salario - 100);
		BigDecimal maxSalario = BigDecimal.valueOf(salario + 100);
		recuperaPartecipanteProgettoSalarioPS.setBigDecimal(1, minSalario);
		recuperaPartecipanteProgettoSalarioPS.setBigDecimal(2, maxSalario);

		ResultSet rs = recuperaPartecipanteProgettoSalarioPS.executeQuery();
		ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();

		while (rs.next()) {

			Dipendente dipendente = new Dipendente();
			dipendente.setCodiceFiscale(rs.getString("CODF"));
			dipendente.setNome(rs.getString("NOME"));
			dipendente.setCognome(rs.getString("COGNOME"));
			dipendente.setValutazione(rs.getFloat("VALUTAZIONE"));
			dipendente.setSalarioMedio(rs.getBigDecimal("SALARIO").doubleValue());

			dipendenti.add(dipendente);
		}
		rs.close();

		return dipendenti;
	}

	@Override
	public ArrayList<Dipendente> RecuperaPartecipantiProgettoValutazione(float valutazione) throws SQLException {

		float min_valutazione = valutazione - 1;
		float max_valutazione = valutazione + 1;
		recuperaPartecipanteProgettoValutazionePS.setFloat(1, min_valutazione);
		recuperaPartecipanteProgettoValutazionePS.setFloat(2, max_valutazione);

		ResultSet rs = recuperaPartecipanteProgettoValutazionePS.executeQuery();
		ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();
		while (rs.next()) {

			Dipendente dipendente = new Dipendente();
			dipendente.setCodiceFiscale(rs.getString("CODF"));
			dipendente.setNome(rs.getString("NOME"));
			dipendente.setCognome(rs.getString("COGNOME"));
			dipendente.setValutazione(rs.getFloat("VALUTAZIONE"));
			dipendente.setSalarioMedio(rs.getBigDecimal("SALARIO").doubleValue());

			dipendenti.add(dipendente);
		}
		rs.close();

		return dipendenti;

	}

	@Override
	public ArrayList<Dipendente> RecuperaPartecipantiProgettoSalarioeValutazione(double salario, float valutazione)
			throws SQLException {

		float min_valutazione = valutazione - 1;
		float max_valutazione = valutazione + 1;
		BigDecimal minSalario = BigDecimal.valueOf(salario - 100);
		BigDecimal maxSalario = BigDecimal.valueOf(salario + 100);

		recuperaPartecipanteProgettoSalarioValutazionePS.setBigDecimal(1, minSalario);
		recuperaPartecipanteProgettoSalarioValutazionePS.setBigDecimal(2, maxSalario);
		recuperaPartecipanteProgettoSalarioValutazionePS.setFloat(3, min_valutazione);
		recuperaPartecipanteProgettoSalarioValutazionePS.setFloat(4, max_valutazione);

		ResultSet rs = recuperaPartecipanteProgettoSalarioValutazionePS.executeQuery();
		ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();
		while (rs.next()) {

			Dipendente dipendente = new Dipendente();
			dipendente.setCodiceFiscale(rs.getString("CODF"));
			dipendente.setNome(rs.getString("NOME"));
			dipendente.setCognome(rs.getString("COGNOME"));
			dipendente.setValutazione(rs.getFloat("VALUTAZIONE"));
			dipendente.setSalarioMedio(rs.getBigDecimal("SALARIO").doubleValue());

			dipendenti.add(dipendente);
		}
		rs.close();

		return dipendenti;
	}

}
