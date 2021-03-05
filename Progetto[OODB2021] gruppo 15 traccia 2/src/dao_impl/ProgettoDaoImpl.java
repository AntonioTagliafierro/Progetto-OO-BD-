package dao_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import daos.ProgettoDao;
import entity.Ambito;
import entity.Dipendente;
import entity.PartecipanteProgetto;
import entity.Progetto;

public class ProgettoDaoImpl implements ProgettoDao {

	private Connection connessione;

	// Prepared Statement
	private PreparedStatement recuperaGeneralit‡PS;
	private PreparedStatement recuperaStatusPS;
	private PreparedStatement recuperaStatuseTipologiaPS;
	private PreparedStatement recuperaTipologiaPS;
	private PreparedStatement eliminaProgettoPS;
	private PreparedStatement recuperaTuttoProgettoPS;
	private PreparedStatement recuperaAmbitiProgettoPS;
	private PreparedStatement inserisciProgettoPS;
	private PreparedStatement inserisciPartecipantiProgettoPS;
	private PreparedStatement inserisciAmbitoProgettoPS;
	private PreparedStatement recuperaCodiceProgettoPS;
	private PreparedStatement recuperaPartecipantiProgettoPS;
	private PreparedStatement aggiornaProgettoPS;
	private PreparedStatement rimuoviPartecipantiProgettoPS;
	private PreparedStatement rimuoviAmbitiProgettoPS;
	private PreparedStatement fineProgettoPS;

	public ProgettoDaoImpl(Connection connection) {

		this.connessione = connection;

		try {
			fineProgettoPS = connessione.prepareStatement("CALL FINE_PROGETTO(?) ");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			rimuoviAmbitiProgettoPS = connessione
					.prepareStatement("DELETE FROM AMBITO WHERE NOMEAMBITO LIKE ? AND CODP = ? ");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			rimuoviPartecipantiProgettoPS = connessione
					.prepareStatement("DELETE FROM PARTECIPANTEPROGETTO WHERE CODF = ? AND CODP = ? ");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			aggiornaProgettoPS = connessione.prepareStatement(
					"UPDATE PROGETTO SET NOMEPROGETTO = ?,  TIPOLOGIA = ? , DATAI = ? , DATAF = ?, NOMECLIENTE = ? ,DESCRIZIONE = ?  WHERE CODP = ?");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			inserisciProgettoPS = connessione.prepareStatement("INSERT INTO PROGETTO VALUES (DEFAULT, ?,?,?,?,?,?) ");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			recuperaCodiceProgettoPS = connessione.prepareStatement("SELECT CURRVAL('progetto_codp_seq') ");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			inserisciPartecipantiProgettoPS = connessione
					.prepareStatement("INSERT INTO PARTECIPANTEPROGETTO VALUES (?,?,?) ");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			inserisciAmbitoProgettoPS = connessione.prepareStatement("INSERT INTO AMBITO VALUES (?,?) ");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			recuperaGeneralit‡PS = connessione
					.prepareStatement("SELECT  NOMEPROGETTO, CODP, DATAI, TIPOLOGIA,STATUS FROM PROGETTO");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			recuperaStatusPS = connessione.prepareStatement(
					"SELECT NOMEPROGETTO, CODP, DATAI, TIPOLOGIA,STATUS FROM PROGETTO WHERE STATUS LIKE ?");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			recuperaStatuseTipologiaPS = connessione.prepareStatement(
					"SELECT NOMEPROGETTO, CODP, DATAI, TIPOLOGIA,STATUS FROM PROGETTO WHERE STATUS LIKE ? AND TIPOLOGIA LIKE ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			recuperaTipologiaPS = connessione.prepareStatement(
					"SELECT NOMEPROGETTO, CODP, DATAI, TIPOLOGIA,STATUS FROM PROGETTO WHERE TIPOLOGIA LIKE ? ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			eliminaProgettoPS = connessione.prepareStatement("DELETE FROM PROGETTO WHERE CODP = ? ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			recuperaTuttoProgettoPS = connessione.prepareStatement("SELECT * FROM PROGETTO WHERE CODP = ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			recuperaAmbitiProgettoPS = connessione.prepareStatement("SELECT NOME FROM AMBITO WHERE CODP = ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			recuperaPartecipantiProgettoPS = connessione.prepareStatement(
					"SELECT CODF, NOME, COGNOME, VALUTAZIONE, SALARIO, RUOLO FROM DIPENDENTE NATURAL JOIN PARTECIPANTEPROGETTO WHERE CODP = ? ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<Progetto> RecuperaGeneralit‡Progetto() throws SQLException {

		ResultSet rs = recuperaGeneralit‡PS.executeQuery();
		ArrayList<Progetto> progetti = new ArrayList<Progetto>();

		while (rs.next()) {
			Progetto progetto = new Progetto();
			progetto.setCodProgetto(rs.getString("CODP"));
			progetto.setNomeProgetto(rs.getString("NOMEPROGETTO"));
			progetto.setDataInizioProgetto(rs.getDate("DATAI"));
			progetto.setStatusProgetto(rs.getString("STATUS"));
			progetto.setTipologiaProgetto(rs.getString("TIPOLOGIA"));

			progetti.add(progetto);
		}
		rs.close();

		return progetti;
	}

	@Override
	public ArrayList<Progetto> RecuperaProgettiStatus(String status) throws SQLException {

		recuperaStatusPS.setString(1, status);
		ResultSet rs = recuperaStatusPS.executeQuery();
		ArrayList<Progetto> progetti = new ArrayList<Progetto>();

		while (rs.next()) {
			Progetto progetto = new Progetto();
			progetto.setCodProgetto(rs.getString("CODP"));
			progetto.setNomeProgetto(rs.getString("NOMEPROGETTO"));
			progetto.setDataInizioProgetto(rs.getDate("DATAI"));
			progetto.setStatusProgetto(rs.getString("STATUS"));
			progetto.setTipologiaProgetto(rs.getString("TIPOLOGIA"));

			progetti.add(progetto);
		}
		rs.close();

		return progetti;
	}

	@Override
	public ArrayList<Progetto> RecuperaProgettiStatuseTipologia(String status, String tipologia) throws SQLException {

		recuperaStatuseTipologiaPS.setString(1, status);
		recuperaStatuseTipologiaPS.setString(2, tipologia.toUpperCase());

		ResultSet rs = recuperaStatuseTipologiaPS.executeQuery();
		ArrayList<Progetto> progetti = new ArrayList<Progetto>();

		while (rs.next()) {
			Progetto progetto = new Progetto();
			progetto.setCodProgetto(rs.getString("CODP"));
			progetto.setNomeProgetto(rs.getString("NOMEPROGETTO"));
			progetto.setDataInizioProgetto(rs.getDate("DATAI"));
			progetto.setStatusProgetto(rs.getString("STATUS"));
			progetto.setTipologiaProgetto(rs.getString("TIPOLOGIA"));

			progetti.add(progetto);
		}
		rs.close();

		return progetti;

	}

	@Override
	public ArrayList<Progetto> RecuperaProgettiTipologia(String tipologia) throws SQLException {

		recuperaTipologiaPS.setString(1, tipologia.toUpperCase());
		ResultSet rs = recuperaTipologiaPS.executeQuery();
		ArrayList<Progetto> progetti = new ArrayList<Progetto>();

		while (rs.next()) {
			Progetto progetto = new Progetto();
			progetto.setCodProgetto(rs.getString("CODP"));
			progetto.setNomeProgetto(rs.getString("NOMEPROGETTO"));
			progetto.setDataInizioProgetto(rs.getDate("DATAI"));
			progetto.setStatusProgetto(rs.getString("STATUS"));
			progetto.setTipologiaProgetto(rs.getString("TIPOLOGIA"));

			progetti.add(progetto);
		}
		rs.close();

		return progetti;
	}

	@Override
	public void eliminaProgetto(String codProgetto) throws SQLException {
		int codiceProgetto = Integer.parseInt(codProgetto);
		eliminaProgettoPS.setInt(1, codiceProgetto);
		eliminaProgettoPS.executeUpdate();

	}

	@Override
	public Progetto RecuperaTuttoDaProgetto(String codiceProgetto) throws SQLException {

		int codProgetto = Integer.parseInt(codiceProgetto);
		recuperaTuttoProgettoPS.setInt(1, codProgetto);
		Progetto progetto = new Progetto();
		ResultSet rs = recuperaTuttoProgettoPS.executeQuery();

		if (rs.next()) {

			progetto.setCodProgetto(String.valueOf(rs.getInt("CODP")));
			progetto.setNomeProgetto(rs.getString("NOMEPROGETTO"));
			progetto.setTipologiaProgetto(rs.getString("TIPOLOGIA"));
			progetto.setDataScadenzaProgetto(rs.getDate("DATAF"));
			progetto.setDataInizioProgetto(rs.getDate("DATAI"));
			progetto.setNomeCliente(rs.getString("NOMECLIENTE"));
			progetto.setDescrizioneProgetto(rs.getString("DESCRIZIONE"));
			progetto.setStatusProgetto(rs.getString("STATUS"));
		}
		rs.close();

		ArrayList<Ambito> ambiti = new ArrayList<Ambito>();
		recuperaAmbitiProgettoPS.setInt(1, codProgetto);
		ResultSet rs_ambiti = recuperaAmbitiProgettoPS.executeQuery();

		while (rs_ambiti.next()) {
			Ambito ambito = new Ambito();
			ambito.setNomeAmbito(rs_ambiti.getString("NOME"));

			ambiti.add(ambito);

		}
		rs_ambiti.close();
		progetto.setAmbiti(ambiti);

		ArrayList<PartecipanteProgetto> partecipanti = new ArrayList<PartecipanteProgetto>();
		recuperaPartecipantiProgettoPS.setInt(1, codProgetto);
		ResultSet rs_partecipanti = recuperaPartecipantiProgettoPS.executeQuery();
		while (rs_partecipanti.next()) {
			PartecipanteProgetto partecipante = new PartecipanteProgetto();
			Dipendente dipendente = new Dipendente();
			dipendente.setCodiceFiscale(rs_partecipanti.getString("CODF"));
			dipendente.setNome(rs_partecipanti.getString("NOME"));
			dipendente.setCognome(rs_partecipanti.getString("COGNOME"));
			dipendente.setValutazione(rs_partecipanti.getFloat("VALUTAZIONE"));
			dipendente.setSalarioMedio(rs_partecipanti.getBigDecimal("SALARIO").doubleValue());
			partecipante.setDipendente(dipendente);
			partecipante.setRuolo(rs_partecipanti.getString("RUOLO"));
			partecipanti.add(partecipante);

		}
		rs_partecipanti.close();
		progetto.setPartecipantiProgetto(partecipanti);

		return progetto;

	}

	@Override
	public void InserisciProgetto(Progetto progetto, ArrayList<PartecipanteProgetto> partecipantiProgetto)
			throws SQLException {

		long dataInizio = progetto.getDataInizioProgetto().getTime();
		long dataScadenza = progetto.getDataScadenzaProgetto().getTime();

		inserisciProgettoPS.setString(1, progetto.getNomeProgetto().toUpperCase());
		inserisciProgettoPS.setString(2, progetto.getTipologiaProgetto().toUpperCase());
		inserisciProgettoPS.setDate(3, new java.sql.Date(dataInizio));
		inserisciProgettoPS.setDate(4, new java.sql.Date(dataScadenza));
		inserisciProgettoPS.setString(5, progetto.getNomeCliente().toUpperCase());
		inserisciProgettoPS.setString(6, progetto.getDescrizioneProgetto().toUpperCase());
		inserisciProgettoPS.executeUpdate();

		int codiceP = 0;
		ResultSet rs = recuperaCodiceProgettoPS.executeQuery();
		if (rs.next()) {
			codiceP = (rs.getInt(1));
		}
		rs.close();

		for (PartecipanteProgetto p : partecipantiProgetto) {

			inserisciPartecipantiProgettoPS.setString(1, p.getDipendente().getCodiceFiscale());
			inserisciPartecipantiProgettoPS.setInt(2, codiceP);
			inserisciPartecipantiProgettoPS.setString(3, p.getRuolo().toUpperCase());
			inserisciPartecipantiProgettoPS.executeUpdate();

		}

		for (Ambito a : progetto.getAmbiti()) {

			inserisciAmbitoProgettoPS.setString(1, a.getNomeAmbito().toUpperCase());
			inserisciAmbitoProgettoPS.setInt(2, codiceP);
			inserisciAmbitoProgettoPS.executeUpdate();

		}

	}

	@Override
	public void ModificaProgetto(Progetto progetto, ArrayList<PartecipanteProgetto> dipendenteDaEliminare,
			ArrayList<PartecipanteProgetto> partecipantiDaAggiungere, ArrayList<Ambito> ambitiDaEliminare,
			ArrayList<Ambito> ambitiDaAggiungere) throws SQLException {

		int codp = Integer.parseInt(progetto.getCodProgetto());

		long dataInizio = progetto.getDataInizioProgetto().getTime();
		long dataScadenza = progetto.getDataScadenzaProgetto().getTime();

		for (PartecipanteProgetto p : dipendenteDaEliminare) {

			rimuoviPartecipantiProgettoPS.setString(1, p.getDipendente().getCodiceFiscale());
			rimuoviPartecipantiProgettoPS.setInt(2, codp);
			rimuoviPartecipantiProgettoPS.executeUpdate();

		}

		aggiornaProgettoPS.setString(1, progetto.getNomeProgetto().toUpperCase());
		aggiornaProgettoPS.setString(2, progetto.getTipologiaProgetto().toUpperCase());
		aggiornaProgettoPS.setDate(3, new java.sql.Date(dataInizio));
		aggiornaProgettoPS.setDate(4, new java.sql.Date(dataScadenza));
		aggiornaProgettoPS.setString(5, progetto.getNomeCliente().toUpperCase());
		aggiornaProgettoPS.setString(6, progetto.getDescrizioneProgetto().toUpperCase());
		aggiornaProgettoPS.setInt(7, codp);
		aggiornaProgettoPS.executeUpdate();

		for (PartecipanteProgetto p : partecipantiDaAggiungere) {

			inserisciPartecipantiProgettoPS.setString(1, p.getDipendente().getCodiceFiscale());
			inserisciPartecipantiProgettoPS.setInt(2, codp);
			inserisciPartecipantiProgettoPS.setString(3, p.getRuolo().toUpperCase());
			inserisciPartecipantiProgettoPS.executeUpdate();

		}

		for (Ambito a : ambitiDaAggiungere) {

			inserisciAmbitoProgettoPS.setString(1, a.getNomeAmbito().toUpperCase());
			inserisciAmbitoProgettoPS.setInt(2, codp);
			inserisciAmbitoProgettoPS.executeUpdate();

		}

		for (Ambito a : ambitiDaEliminare) {

			rimuoviAmbitiProgettoPS.setString(1, a.getNomeAmbito().toUpperCase());
			rimuoviAmbitiProgettoPS.setInt(2, codp);
			rimuoviAmbitiProgettoPS.executeUpdate();

		}

	}

	@Override
	public void CompletaProgetto(String codp) throws SQLException {

		int codice = Integer.parseInt(codp);

		fineProgettoPS.setInt(1, codice);
		fineProgettoPS.execute();

	}

}