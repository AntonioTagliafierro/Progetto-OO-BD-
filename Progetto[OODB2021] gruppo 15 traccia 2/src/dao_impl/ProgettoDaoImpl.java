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
	private PreparedStatement recuperaGeneralitąPS;
	
	
	public ProgettoDaoImpl(Connection connection) {

		this.connessione = connection;
	
	
	try {
		recuperaGeneralitąPS = connessione
				.prepareStatement("SELECT  NOMEPROGETTO, CODP, DATAI, TIPOLOGIA,STATUS FROM PROGETTO");
	} catch (SQLException e) {

		e.printStackTrace();
	}

	}
	
	public ArrayList<Progetto> RecuperaGeneralitąProgetto() throws SQLException {
		
		ResultSet rs = recuperaGeneralitąPS.executeQuery();
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

	
	
}
