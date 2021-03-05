package daos;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Ambito;
import entity.PartecipanteProgetto;
import entity.Progetto;

public interface ProgettoDao {

	public ArrayList<Progetto> RecuperaGeneralit‡Progetto() throws SQLException;

	public ArrayList<Progetto> RecuperaProgettiStatus(String status) throws SQLException;

	public ArrayList<Progetto> RecuperaProgettiStatuseTipologia(String status, String tipologia) throws SQLException;

	public ArrayList<Progetto> RecuperaProgettiTipologia(String tipologia) throws SQLException;

	public void eliminaProgetto(String codProgetto) throws SQLException;

	public Progetto RecuperaTuttoDaProgetto(String codiceProgetto) throws SQLException;

	public void InserisciProgetto(Progetto progetto, ArrayList<PartecipanteProgetto> partecipantiProgetto)
			throws SQLException;

	public void ModificaProgetto(Progetto progetto, ArrayList<PartecipanteProgetto> dipendenteDaEliminare,
			ArrayList<PartecipanteProgetto> partecipantiDaAggiungere, ArrayList<Ambito> ambitiDaEliminare,
			ArrayList<Ambito> ambitiDaAggiungere) throws SQLException;

	public void CompletaProgetto(String codp) throws SQLException;

}