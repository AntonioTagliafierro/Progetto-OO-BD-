package dao_impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import controller.Controller;
import daos.DipendenteDao;
import dbConfig.DBConnection;
import entity.Dipendente;

public class DipendenteDaoImpl implements DipendenteDao {

	private Connection connessione;

//Prepared Statement
	private PreparedStatement inserisciDipendentePS;
	private PreparedStatement inserisciResidenzaPS;

	public DipendenteDaoImpl(Connection connection) {

		this.connessione = connection;

		try {
			inserisciDipendentePS = connessione.prepareStatement(
					"INSERT INTO DIPENDENTE (CODF,NOME,COGNOME,EMAIL,DATAN,SESSO,NUMCELLULARE,PATHFOTO,SALARIO) VALUES(?,?,?,?,?,?,?,?,?)");
			inserisciResidenzaPS = connessione.prepareStatement("INSERT INTO RESIDENZA VALUES (?,?,?,?,?,?)");
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
		inserisciDipendentePS.setBigDecimal( 9,BigDecimal.valueOf( dipendente.getSalarioMedio()));

		inserisciDipendentePS.executeUpdate();

//		inserisciDipendentePS.close();
		

	}

	@Override
	public void InserisciResidenza(Dipendente dipendente) throws SQLException {

		inserisciResidenzaPS.setString(1, dipendente.getCodiceFiscale().toUpperCase());
		inserisciResidenzaPS.setString(2, dipendente.getResidenza().getProvincia());
		inserisciResidenzaPS.setString(3, dipendente.getResidenza().getCap());
		inserisciResidenzaPS.setString(4, dipendente.getResidenza().getVia().toUpperCase());
		inserisciResidenzaPS.setString(5, dipendente.getResidenza().getnCivico());
		inserisciResidenzaPS.setString(6, dipendente.getResidenza().getCittà().toUpperCase());
		inserisciResidenzaPS.executeUpdate();

//		inserisciResidenzaPS.close();
		
	}

}
