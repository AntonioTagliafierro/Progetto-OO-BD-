package daos_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import daos.ProgettoDao;
import entity.Progetto;

public class ProgettoDaoImpl implements ProgettoDao {
	
	private Connection connessione;

	//Prepared Statement
	private PreparedStatement recuperaGeneralit‡PS;
	private PreparedStatement recuperaStatusPS;
	private PreparedStatement recuperaStatuseTipologiaPS;
	private PreparedStatement recuperaTipologiaPS;
	private PreparedStatement eliminaProgettoPS;
	
	public ProgettoDaoImpl(Connection connection) {

		this.connessione = connection;
	
	
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
	public ArrayList<Progetto> RecuperaProgettiTipologia(String tipologia)	throws SQLException {
		
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
		eliminaProgettoPS.setString(1, codProgetto);
		eliminaProgettoPS.executeUpdate();
		
		
	}
	

	}
		
		


	
	

