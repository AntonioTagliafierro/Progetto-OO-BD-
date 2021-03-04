package daos;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Dipendente;

public interface DipendenteDao {

	 public ArrayList<Dipendente> RecuperaGeneralit‡DipendentiPerFiltri() throws SQLException;

	public ArrayList<Dipendente> RecuperaPartecipantiProgettoTipologia(String tipologia) throws SQLException;

	public ArrayList<Dipendente> RecuperaPartecipantiProgettoTipologiaeValutazione(String tipologia,float valutazione_)throws SQLException;

	public ArrayList<Dipendente> RecuperaPartecipantiProgettoTipologiaeSalario(String tipologia, double salario) throws SQLException;

	public ArrayList<Dipendente> RecuperaPartecipantiProgettoTipologiaSalarioValutazione(String tipologia,double salario, float valutazione) throws SQLException;

	public ArrayList<Dipendente> RecuperaPartecipantiProgettoSalario(double salario) throws SQLException;

	public ArrayList<Dipendente> RecuperaPartecipantiProgettoValutazione(float valutazione) throws SQLException;

	public ArrayList<Dipendente> RecuperaPartecipantiProgettoSalarioeValutazione(double salario, float valutazione)throws SQLException;

}
