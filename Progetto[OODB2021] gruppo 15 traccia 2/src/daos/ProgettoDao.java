package daos;

import java.sql.SQLException;
import java.util.ArrayList;
import entity.Progetto;

public interface ProgettoDao {
	
	
	public ArrayList<Progetto> RecuperaGeneralit‡Progetto() throws SQLException;

	public ArrayList<Progetto> RecuperaProgettiStatus(String status) throws SQLException;

	public ArrayList<Progetto> RecuperaProgettiStatuseTipologia(String status, String tipologia) throws SQLException;

	public ArrayList<Progetto> RecuperaProgettiTipologia(String tipologia) throws SQLException;

	public void eliminaProgetto(String codProgetto) throws SQLException;

}
