package modele.DAO;

import java.sql.PreparedStatement;

/**
 *
 * @author loiseln
 */

public interface DAOQueryParameter {

	public void set(PreparedStatement statement) throws DAOException;
}