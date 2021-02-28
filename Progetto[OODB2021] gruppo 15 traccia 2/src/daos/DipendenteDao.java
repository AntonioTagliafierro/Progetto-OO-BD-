package daos;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Dipendente;

public interface DipendenteDao {

		public void InserisciDipendente(Dipendente dipendente) throws SQLException;
		public void InserisciResidenza(Dipendente dipendente) throws SQLException;
		public ArrayList<Dipendente> RecuperaGeneralit‡Dipendenti() throws SQLException;
		public void eliminaDipendente(String codiceFiscale) throws SQLException;
		public void DisattivaDipendente(String codiceFiscale) throws SQLException;
		public ArrayList<Dipendente> RecuperaDipendentiStatus(String status) throws SQLException;
		public ArrayList<Dipendente> RecuperaDipendentiStatuseSalario(String status, Double salario) throws SQLException;
		public ArrayList<Dipendente> RecuperaDipendentiSalario(Double salario) throws SQLException;
		public ArrayList<Dipendente> RecuperaDipendentiStatuseValutazione(String status, Float valutazione) throws SQLException;

		public ArrayList<Dipendente> RecuperaDipendentiValutazione(Float valutazione) throws SQLException;
		public ArrayList<Dipendente> RecuperaDipendentiStatusSalarioeValutazione(String status, Double salario, Float valutazione) throws SQLException;

		public ArrayList<Dipendente> RecuperaDipendentiSalarioeValutazione(Double salario, Float valutazione) throws SQLException;
		public Dipendente RecuperaTuttoDaDipendente(String codiceFiscale) throws SQLException;
		public void aggiornaDipendente(Dipendente dipendente,String vecchioCF) throws SQLException;

}
