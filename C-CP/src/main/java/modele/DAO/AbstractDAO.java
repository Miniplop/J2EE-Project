package modele.DAO;

import java.sql.Connection;
import java.util.List;
import javax.sql.DataSource;

public abstract class AbstractDAO {
	private DataSource dataSource;
	private final String SELECT_QUERY;
	private final String INSERT_QUERY;
	private final String UPDATE_QUERY;

	protected AbstractDAO(Object ds, String insert_query, String select_query, String update_query) {
		throw new UnsupportedOperationException();
	}

	protected Connection getConnection() {
		throw new UnsupportedOperationException();
	}

	protected void closeConnection() {
		throw new UnsupportedOperationException();
	}

	protected abstract Object add(DAOQueryParameter setter);

	protected abstract void modify(DAOQueryParameter setter);

	protected abstract List get(DAOQueryParameter setter);
}