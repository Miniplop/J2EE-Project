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

    protected AbstractDAO(DataSource ds, String insert_query, String select_query, String update_query) {
        this.dataSource = ds;
        this.INSERT_QUERY = insert_query;
        this.SELECT_QUERY = select_query;
        this.UPDATE_QUERY = update_query;
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
    
    private void updateQuery(DAOQueryParameter setter, String sql_query) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();
            
            stmt = conn.prepareStatement(sql_query);
            setter.set(stmt);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating object failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    }
    
    private Object selectQuery(DAOQueryParameter setter, DAOModeleBuilder<T> builder, String sql_query, boolean multiple) throws DAOException {
        Object result = null;
        ResultSet rs;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st = conn.prepareStatement(sql_query);
            if(setter != null)
                setter.set(st);
            rs = st.executeQuery();
            if(multiple) {
                result = new ArrayList<>();
                while (rs.next()) {
                    T object = builder.build(rs);
                    ((ArrayList<T>)result).add(object);
                }
            }
            else {
                if(rs.next()) {
                    result = builder.build(rs);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
    
    protected int getLastId(String table_name) throws DAOException {
        Statement statement = null;
        ResultSet generatedKeys = null;
        Connection conn = null;
        int id = 0;
        try {
            conn = getConnection();
            statement = conn.createStatement();
            generatedKeys = statement.executeQuery("SELECT max(id) FROM "+table_name);
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating object failed, no generated key obtained.");
            }
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        } finally {
            closeConnection(conn);
        }
        return id;
    }

    protected int getCount(DAOQueryParameter setter,String sql_query) throws DAOException {
        ResultSet generatedKeys = null;
        Connection conn = null;
        int id;
        try {
            conn = getConnection();
            PreparedStatement st = conn.prepareStatement(sql_query);
            if(setter != null)
                setter.set(st);
            generatedKeys = st.executeQuery();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating object failed, no generated key obtained.");
            }
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        } finally {
            closeConnection(conn);
        }
        return id;
    }

    protected void add(DAOQueryParameter setter) throws DAOException {
        if(INSERT_QUERY == null)
            throw new UnsupportedOperationException();
        updateQuery(setter, INSERT_QUERY);
    }
    
    protected void modify(DAOQueryParameter setter, String QUERY) throws DAOException {
        updateQuery(setter, QUERY);
    }

    protected List<T> gets(DAOModeleBuilder<T> builder) throws DAOException {

        if(SELECT_QUERY == null)
            throw new UnsupportedOperationException();

        return (List<T>) selectQuery(null, builder, SELECT_QUERY, true);
    }

    protected T getSingle(DAOModeleBuilder<T> builder, DAOQueryParameter setter, String QUERY) throws DAOException {
        return (T) selectQuery(setter, builder, QUERY, false);
    }

    protected List<T> getMultiple(DAOModeleBuilder<T> builder, DAOQueryParameter setter, String QUERY) throws DAOException {
        return (List<T>) selectQuery(setter, builder, QUERY, true);
    }
}