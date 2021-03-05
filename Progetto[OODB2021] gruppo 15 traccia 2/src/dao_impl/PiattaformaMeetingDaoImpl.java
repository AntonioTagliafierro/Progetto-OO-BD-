package dao_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import daos.PiattaformaMeetingDao;
import entity.PiattaformaMeeting;
import entity.SalaMeeting;

public class PiattaformaMeetingDaoImpl implements PiattaformaMeetingDao {
	private Connection connessione;

	PreparedStatement recuperaPiattaformePS;
	PreparedStatement eliminaPiattaformaPS;
	PreparedStatement salvaPiattaformaPS;
	PreparedStatement recuperaPiattaformaPS;
	PreparedStatement modificaPiattaformaPS;
	PreparedStatement recuperaNomePiattaformePS;

	private ArrayList<PiattaformaMeeting> piattaforme = new ArrayList<PiattaformaMeeting>();
	PiattaformaMeeting piattaforma = new PiattaformaMeeting();

	public PiattaformaMeetingDaoImpl(Connection connection) {
		this.connessione = connection;

		try {
			recuperaPiattaformePS = connessione.prepareStatement("SELECT  * FROM PIATTAFORMAMEETING");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			eliminaPiattaformaPS = connessione
					.prepareStatement("DELETE FROM PIATTAFORMAMEETING WHERE NOMEPIATTAFORMA = ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			salvaPiattaformaPS = connessione.prepareStatement("INSERT INTO PIATTAFORMAMEETING VALUES (?,?)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			recuperaPiattaformaPS = connessione
					.prepareStatement("SELECT * FROM PIATTAFORMAMEETING WHERE NOMEPIATTAFORMA LIKE ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			modificaPiattaformaPS = connessione.prepareStatement(
					"UPDATE PIATTAFORMAMEETING SET NOMEPIATTAFORMA = ? ,NMAXDISPOSITIVI = ? WHERE NOMEPIATTAFORMA = ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			recuperaNomePiattaformePS = connessione.prepareStatement("SELECT NOMEPIATTAFORMA FROM PIATTAFORMAMEETING");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<PiattaformaMeeting> RecuperaPiattaforme() throws SQLException {
		ArrayList<PiattaformaMeeting> piattaforme = new ArrayList<PiattaformaMeeting>();
		ResultSet rs = recuperaPiattaformePS.executeQuery();

		while (rs.next()) {
			PiattaformaMeeting piattaforma = new PiattaformaMeeting();
			piattaforma.setNomePiattaforma(rs.getString("NOMEPIATTAFORMA").toUpperCase());
			piattaforma.setLimitePartecipanti(String.valueOf(rs.getInt("NMAXDISPOSITIVI")));

			piattaforme.add(piattaforma);

		}
		rs.close();
		return piattaforme;
	}

	@Override
	public void EliminaPiattaforma(String nomePiattaforma) throws SQLException {

		eliminaPiattaformaPS.setString(1, nomePiattaforma);
		eliminaPiattaformaPS.executeUpdate();
	}

	@Override
	public void SalvaPiattaforma(PiattaformaMeeting piattaforma) throws SQLException {
		int nDispositivi = Integer.valueOf(piattaforma.getLimitePartecipanti());
		salvaPiattaformaPS.setString(1, piattaforma.getNomePiattaforma().toUpperCase());
		salvaPiattaformaPS.setInt(2, nDispositivi);
		salvaPiattaformaPS.executeUpdate();

	}

	@Override
	public PiattaformaMeeting RecuperaPiattaforma(String nomePiattaforma) throws SQLException {

		recuperaPiattaformaPS.setString(1, nomePiattaforma);
		PiattaformaMeeting piattaforma = new PiattaformaMeeting();
		ResultSet rs = recuperaPiattaformaPS.executeQuery();
		if (rs.next()) {
			piattaforma.setNomePiattaforma(rs.getString("NOMEPIATTAFORMA"));
			piattaforma.setLimitePartecipanti(String.valueOf(rs.getInt("NMAXDISPOSITIVI")));
		}
		rs.close();
		return piattaforma;
	}

	@Override
	public void ModificaPiattaforma(PiattaformaMeeting piattaformaModificata, String nomePiattaformaDaModificare)
			throws SQLException {
		int nDispositivi = Integer.valueOf(piattaformaModificata.getLimitePartecipanti());
		modificaPiattaformaPS.setString(1, piattaformaModificata.getNomePiattaforma().toUpperCase());
		modificaPiattaformaPS.setInt(2, nDispositivi);
		modificaPiattaformaPS.setString(3, nomePiattaformaDaModificare);
		modificaPiattaformaPS.executeUpdate();

	}

	@Override
	public ArrayList<PiattaformaMeeting> RecuperaNomePiattaforme() throws SQLException {
		ArrayList<PiattaformaMeeting> piattaforme = new ArrayList<PiattaformaMeeting>();
		ResultSet rs = recuperaNomePiattaformePS.executeQuery();

		while (rs.next()) {
			PiattaformaMeeting piattaforma = new PiattaformaMeeting();
			piattaforma.setNomePiattaforma(rs.getString("NOMEPIATTAFORMA"));

			piattaforme.add(piattaforma);

		}
		rs.close();
		return piattaforme;
	}

}
