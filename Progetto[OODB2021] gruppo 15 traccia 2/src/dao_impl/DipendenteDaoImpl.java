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

}
