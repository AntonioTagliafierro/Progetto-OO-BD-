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
	public ArrayList<Dipendente> RecuperaGeneralit‡Dipendenti() throws SQLException {

		ResultSet rs = recuperaGeneralit‡PS.executeQuery();
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
	public void eliminaDipendente(String codiceFiscale) throws SQLException {

		eliminaDipendentePS.setString(1, codiceFiscale);
		eliminaDipendentePS.executeUpdate();

	}

	@Override
	public void DisattivaDipendente(String codiceFiscale) throws SQLException {

		disattivaDipendentePS.setString(1, codiceFiscale);
		disattivaDipendentePS.execute();

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
		recuperaStatuseSalarioPS.setBigDecimal(3,maxSalario);
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
		recuperaConSalarioPS.setBigDecimal(1,minSalario);
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
	public ArrayList<Dipendente> RecuperaDipendentiStatuseValutazione(String status, Float valutazione) throws SQLException {
		float minValutazione = valutazione -1;
		float maxValutazione = valutazione +1;
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
		float minValutazione = valutazione -1;
		float maxValutazione = valutazione +1;
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
	public ArrayList<Dipendente> RecuperaDipendentiStatusSalarioeValutazione(String status, Double salario, Float valutazione) throws SQLException {
		BigDecimal minSalario = BigDecimal.valueOf(salario - 100);
		BigDecimal maxSalario = BigDecimal.valueOf(salario + 100);
		float minValutazione = valutazione -1;
		float maxValutazione = valutazione +1;
		recuperaStatusSalarioeValutazionePS.setString(1, status);
		recuperaStatusSalarioeValutazionePS.setBigDecimal(2, minSalario);
		recuperaStatusSalarioeValutazionePS.setBigDecimal(3, maxSalario);
		recuperaStatusSalarioeValutazionePS.setFloat(4,minValutazione);
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
	public ArrayList<Dipendente> RecuperaDipendentiSalarioeValutazione(Double salario, Float valutazione) throws SQLException {
		BigDecimal minSalario = BigDecimal.valueOf(salario - 100);
		BigDecimal maxSalario = BigDecimal.valueOf(salario + 100);
		float minValutazione = valutazione -1;
		float maxValutazione = valutazione +1;

		recuperaConSalarioeValutazionePS.setBigDecimal(1, minSalario);
		recuperaConSalarioeValutazionePS.setBigDecimal(2, maxSalario);
		recuperaConSalarioeValutazionePS.setFloat(3,minValutazione);
		recuperaConSalarioeValutazionePS.setFloat(4,maxValutazione);
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

}
