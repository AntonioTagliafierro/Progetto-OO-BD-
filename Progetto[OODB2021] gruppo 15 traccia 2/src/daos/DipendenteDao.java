package daos;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Dipendente;

public interface DipendenteDao {

	public void InserisciDipendente(Dipendente dipendente) throws SQLException;

	public void InserisciResidenza(Dipendente dipendente) throws SQLException;

	public ArrayList<Dipendente> RecuperaGeneralit‡Dipendenti() throws SQLException;

	public void EliminaDipendente(String codiceFiscale) throws SQLException;

	public void DisattivaDipendente(String codiceFiscale) throws SQLException;

	public ArrayList<Dipendente> RecuperaDipendentiStatus(String status) throws SQLException;

	public ArrayList<Dipendente> RecuperaDipendentiStatuseSalario(String status, Double salario) throws SQLException;

	public ArrayList<Dipendente> RecuperaDipendentiSalario(Double salario) throws SQLException;

	public ArrayList<Dipendente> RecuperaDipendentiStatuseValutazione(String status, Float valutazione)
			throws SQLException;

	public ArrayList<Dipendente> RecuperaDipendentiValutazione(Float valutazione) throws SQLException;

	public ArrayList<Dipendente> RecuperaDipendentiStatusSalarioeValutazione(String status, Double salario,
			Float valutazione) throws SQLException;

	public ArrayList<Dipendente> RecuperaDipendentiSalarioeValutazione(Double salario, Float valutazione)
			throws SQLException;

	public Dipendente RecuperaTuttoDaDipendente(String codiceFiscale) throws SQLException;

	public void AggiornaDipendente(Dipendente dipendente, String vecchioCF) throws SQLException;

	public void AttivaDipendente(String codiceFiscale, Double salario) throws SQLException;

	public ArrayList<Dipendente> RecuperaDipendentiAttivi() throws SQLException;

	public ArrayList<Dipendente> RecuperaGeneralit‡DipendentiPerFiltri() throws SQLException;

	public ArrayList<Dipendente> RecuperaPartecipantiProgettoTipologia(String tipologia) throws SQLException;

	public ArrayList<Dipendente> RecuperaPartecipantiProgettoTipologiaeValutazione(String tipologia, float valutazione_)
			throws SQLException;

	public ArrayList<Dipendente> RecuperaPartecipantiProgettoTipologiaeSalario(String tipologia, double salario)
			throws SQLException;

	public ArrayList<Dipendente> RecuperaPartecipantiProgettoTipologiaSalarioValutazione(String tipologia,
			double salario, float valutazione) throws SQLException;

	public ArrayList<Dipendente> RecuperaPartecipantiProgettoSalario(double salario) throws SQLException;

	public ArrayList<Dipendente> RecuperaPartecipantiProgettoValutazione(float valutazione) throws SQLException;

	public ArrayList<Dipendente> RecuperaPartecipantiProgettoSalarioeValutazione(double salario, float valutazione)
			throws SQLException;

}
