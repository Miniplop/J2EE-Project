package modele.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public abstract class AbstractDAO<T> {
    
    protected final DataSource dataSource;
    private final String SELECT_QUERY;
    private final String INSERT_QUERY;
    private final String UPDATE_QUERY;
    private final String SELECT_BY_ID_QUERY;

    protected AbstractDAO(DataSource ds, String insert_query, String select_query, String update_query, String select_by_id_query) {
        this.dataSource = ds;
        this.INSERT_QUERY = insert_query;
        this.SELECT_QUERY = select_query;
        this.UPDATE_QUERY = update_query;
        this.SELECT_BY_ID_QUERY = select_by_id_query;
    }

    protected Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    protected void closeConnection(Connection c) throws DAOException {
        if (c != null) {
            try {
                c.close();
            } catch (SQLException sqle) {
                throw new DAOException("Problème fermeture de connexion avec la BD ", sqle);
            }
        }
    }

    protected T add(DAOQueryParameter setter) {
        if(INSERT_QUERY == null)
            throw new UnsupportedOperationException();

        throw new UnsupportedOperationException();
    }

    protected void modify(DAOQueryParameter setter) {
        if(UPDATE_QUERY == null)
            throw new UnsupportedOperationException();

        throw new UnsupportedOperationException();
    }

    protected List<T> gets(DAOModeleBuilder<T> builder) throws DAOException {

        if(SELECT_QUERY == null)
            throw new UnsupportedOperationException();

        List<T> result = new ArrayList<T>();
        ResultSet rs;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            rs = st.executeQuery(SELECT_QUERY);

            while (rs.next()) {
                T object = builder.build(rs);
                result.add(object);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    protected T get(DAOModeleBuilder<T> builder, DAOQueryParameter setter) throws DAOException {

        if(SELECT_BY_ID_QUERY == null)
            throw new UnsupportedOperationException();
        
        Connection conn = null;
        PreparedStatement pSt;
        ResultSet rs;
        T result = null;
        try {
            conn = getConnection();
            pSt = conn.prepareStatement(SELECT_BY_ID_QUERY);
            setter.set(pSt);
            rs = pSt.executeQuery();
            result = builder.build(rs);
            pSt.close();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
}