package daos;

import java.sql.SQLException;
import java.util.ArrayList;
import entity.Progetto;

public interface ProgettoDao {
	
	
	public ArrayList<Progetto> RecuperaGeneralitąProgetto() throws SQLException;

}
