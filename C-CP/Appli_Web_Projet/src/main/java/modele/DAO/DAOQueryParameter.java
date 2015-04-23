package modele.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author loiseln
 */

public interface DAOQueryParameter {

	public void set(PreparedStatement statement) throws SQLException;
}