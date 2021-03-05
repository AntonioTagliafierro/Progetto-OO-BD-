package daos;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.SalaMeeting;

public interface SalaMeetingDao {

	public ArrayList<SalaMeeting> RecuperaSale() throws SQLException;

	public void EliminaSala(String nomeSala) throws SQLException;

	public void SalvaSala(SalaMeeting sala) throws SQLException;

	public SalaMeeting RecuperaSala(String nomeSala) throws SQLException;

	public void ModificaSala(SalaMeeting salaModificata, String nomeSalaDaModificare) throws SQLException;

	public ArrayList<SalaMeeting> RecuperaNomeSale() throws SQLException;

}
