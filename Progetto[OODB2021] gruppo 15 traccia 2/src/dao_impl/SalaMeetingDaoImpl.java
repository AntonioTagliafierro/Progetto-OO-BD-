package dao_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.net.httpserver.Authenticator.Result;

import daos.SalaMeetingDao;
import entity.SalaMeeting;

public class SalaMeetingDaoImpl implements SalaMeetingDao {

	private Connection connessione;

	PreparedStatement recuperaSalePS;
	PreparedStatement eliminaSalaPS;
	PreparedStatement salvaSalaPS;
	PreparedStatement recuperaSalaPS;
	PreparedStatement modificaSalaPS;
	PreparedStatement recuperaNomeSalePS;

	private ArrayList<SalaMeeting> sale = new ArrayList<SalaMeeting>();
	SalaMeeting sala = new SalaMeeting();

	public SalaMeetingDaoImpl(Connection connection) {
		this.connessione = connection;

		try {
			recuperaSalePS = connessione.prepareStatement("SELECT  * FROM SALAMEETING");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			eliminaSalaPS = connessione.prepareStatement("DELETE FROM SALAMEETING WHERE NOMESALA = ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			salvaSalaPS = connessione.prepareStatement("INSERT INTO SALAMEETING VALUES (?,?)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			recuperaSalaPS = connessione.prepareStatement("SELECT * FROM SALAMEETING WHERE NOMESALA LIKE ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			modificaSalaPS = connessione
					.prepareStatement("UPDATE SALAMEETING SET NOMESALA = ? ,NPOSTI = ? WHERE NOMESALA = ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			recuperaNomeSalePS = connessione.prepareStatement("SELECT NOMESALA FROM SALAMEETING");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<SalaMeeting> RecuperaSale() throws SQLException {
		ArrayList<SalaMeeting> sale = new ArrayList<SalaMeeting>();
		ResultSet rs = recuperaSalePS.executeQuery();

		while (rs.next()) {
			SalaMeeting sala = new SalaMeeting();
			sala.setNomeSala(rs.getString("NOMESALA"));
			sala.setnPosti(String.valueOf(rs.getInt("NPOSTI")));

			sale.add(sala);

		}
		rs.close();
		return sale;
	}

	@Override
	public void EliminaSala(String nomeSala) throws SQLException {

		eliminaSalaPS.setString(1, nomeSala);
		eliminaSalaPS.executeUpdate();
	}

	@Override
	public void SalvaSala(SalaMeeting sala) throws SQLException {
		int nPosti = Integer.valueOf(sala.getnPosti());
		salvaSalaPS.setString(1, sala.getNomeSala().toUpperCase());
		salvaSalaPS.setInt(2, nPosti);
		salvaSalaPS.executeUpdate();

	}

	@Override
	public SalaMeeting RecuperaSala(String nomeSala) throws SQLException {

		recuperaSalaPS.setString(1, nomeSala);
		SalaMeeting sala = new SalaMeeting();
		ResultSet rs = recuperaSalaPS.executeQuery();
		if (rs.next()) {
			sala.setNomeSala(rs.getString("NOMESALA"));
			sala.setnPosti(String.valueOf(rs.getInt("NPOSTI")));
		}
		rs.close();
		return sala;
	}

	@Override
	public void ModificaSala(SalaMeeting salaModificata, String nomeSalaDaModificare) throws SQLException {
		int nPosti = Integer.valueOf(salaModificata.getnPosti());
		modificaSalaPS.setString(1, salaModificata.getNomeSala().toUpperCase());
		modificaSalaPS.setInt(2, nPosti);
		modificaSalaPS.setString(3, nomeSalaDaModificare);
		modificaSalaPS.executeUpdate();

	}

	@Override
	public ArrayList<SalaMeeting> RecuperaNomeSale() throws SQLException {
		ArrayList<SalaMeeting> sale = new ArrayList<SalaMeeting>();
		ResultSet rs = recuperaNomeSalePS.executeQuery();

		while (rs.next()) {
			SalaMeeting sala = new SalaMeeting();
			sala.setNomeSala(rs.getString("NOMESALA"));

			sale.add(sala);

		}
		rs.close();
		return sale;
	}

}
