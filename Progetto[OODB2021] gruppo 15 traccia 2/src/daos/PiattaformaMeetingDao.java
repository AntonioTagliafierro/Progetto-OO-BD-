package daos;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.PiattaformaMeeting;

public interface PiattaformaMeetingDao {

	public ArrayList<PiattaformaMeeting> RecuperaPiattaforme() throws SQLException;

	public void EliminaPiattaforma(String nomePiattaforma) throws SQLException;

	public void SalvaPiattaforma(PiattaformaMeeting piattaforma) throws SQLException;

	public PiattaformaMeeting RecuperaPiattaforma(String nomePiattaforma) throws SQLException;

	public void ModificaPiattaforma(PiattaformaMeeting piattaformaModificata, String nomePiattaformaDaModificare)
			throws SQLException;

	public ArrayList<PiattaformaMeeting> RecuperaNomePiattaforme() throws SQLException;

}
