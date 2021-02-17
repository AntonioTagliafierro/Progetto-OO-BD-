package daos;

import java.sql.SQLException;

import entity.Dipendente;

public interface DipendenteDao {

		public void InserisciDipendente(Dipendente dipendente) throws SQLException;
		public void InserisciResidenza(Dipendente dipendente) throws SQLException;
}
