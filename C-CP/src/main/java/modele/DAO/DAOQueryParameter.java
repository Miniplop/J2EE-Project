package modele.DAO;

import java.sql.PreparedStatement;

public interface DAOQueryParameter {

	public void set(PreparedStatement statement);
}