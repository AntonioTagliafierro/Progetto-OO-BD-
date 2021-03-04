package daos_impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import daos.DipendenteDao;
import entity.Dipendente;

public class DipendenteDaoImpl implements DipendenteDao {
	
	private Connection connessione;
	private PreparedStatement recuperaGeneralit‡PerFiltriPS;
	private PreparedStatement recuperaPartecipanteProgettoTipologiaPS;
	private PreparedStatement recuperaPartecipanteProgettoTipologiaeValutazionePS;
	private PreparedStatement recuperaPartecipanteProgettoTipologiaeSalarioPS;
	private PreparedStatement recuperaPartecipanteProgettoTipologiaSalarioeValutazionePS;
	private PreparedStatement recuperaPartecipanteProgettoSalarioPS;
	private PreparedStatement recuperaPartecipanteProgettoValutazionePS;
	private PreparedStatement recuperaPartecipanteProgettoSalarioValutazionePS;
	 	
	public DipendenteDaoImpl(Connection connection) {

		this.connessione = connection;
		
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
	public ArrayList<Dipendente> RecuperaPartecipantiProgettoTipologiaeValutazione(String tipologia, float valutazione)throws SQLException {
		
		float min_valutazione = valutazione -1;
		float max_valutazione = valutazione +1;
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
	public ArrayList<Dipendente> RecuperaPartecipantiProgettoTipologiaeSalario(String tipologia, double salario) throws SQLException{
		
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
	public ArrayList<Dipendente> RecuperaPartecipantiProgettoTipologiaSalarioValutazione(String tipologia,double salario, float valutazione) throws SQLException {
		
		float min_valutazione = valutazione -1;
		float max_valutazione = valutazione +1;
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
		
		float min_valutazione = valutazione -1;
		float max_valutazione = valutazione +1;
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
	public ArrayList<Dipendente> RecuperaPartecipantiProgettoSalarioeValutazione(double salario, float valutazione)throws SQLException {
	
		float min_valutazione = valutazione -1;
		float max_valutazione = valutazione +1;
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
