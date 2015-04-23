/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import modele.Consommateur;
import modele.Disponibilite;

/**
 *
 * @author loiseln
 */
public class DisponibiliteDAO extends AbstractDAO<Disponibilite> {
    
    private static final String INSERT_DISPONIBILITE = "INSERT INTO Disponibilite (consommateur_id, semaine_id) VALUES (?, ?)";
    private static final String SELECT_DISPONIBILITES = "SELECT * FROM Disponibilite";
    private static final String SELECT_DISPONIBILITE = "SELECT * FROM Disponibilite WHERE id = ?";
    private static final String SELECT_DISPONIBILITES_BY_CONSOMMATEUR = "SELECT * FROM Disponibilite WHERE consommateur_id = ?";

    public DisponibiliteDAO(DataSource ds) {
        super(ds, INSERT_DISPONIBILITE, SELECT_DISPONIBILITES, null);
    }
    
    public Disponibilite addDisponibilite(final Consommateur consommateur, final int semaine_id) throws DAOException {
        DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws SQLException {
                statement.setInt(1, consommateur.getId());
                statement.setInt(2, semaine_id);
            }
        };
        super.add(setter);
        return this.getDisponibilite(super.getLastId("Disponibilite"));
    }
    
    public List<Disponibilite> getDisponibilitesByConsommateur(final Consommateur consommateur) throws DAOException {
        DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws SQLException {
                statement.setInt(1, consommateur.getId());
            }
        };
        final SemaineDAO semaineDAO = new SemaineDAO(dataSource);
        DAOModeleBuilder<Disponibilite> builder = new DAOModeleBuilder<Disponibilite>() {
            @Override
            public Disponibilite build(ResultSet rs) throws SQLException, DAOException {
                return new Disponibilite(rs.getInt("id"), consommateur, semaineDAO.getSemaine(rs.getInt("semaine_id")));
            }
        };
        return this.getMultiple(builder, setter, SELECT_DISPONIBILITES_BY_CONSOMMATEUR);
    }
    
    public Disponibilite getDisponibilite(final int id) throws DAOException {
        DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws SQLException {
                statement.setInt(1, id);
            }
        };
        final SemaineDAO semaineDAO = new SemaineDAO(dataSource);
        final ConsommateurDAO consommateurDAO = new ConsommateurDAO(dataSource);
        DAOModeleBuilder<Disponibilite> builder = new DAOModeleBuilder<Disponibilite>() {
            @Override
            public Disponibilite build(ResultSet rs) throws SQLException, DAOException {
                return new Disponibilite(rs.getInt("id"), consommateurDAO.getConsommateur(rs.getInt("consommateur_id")), semaineDAO.getSemaine(rs.getInt("semaine_id")));
            }
        };
        return this.getSingle(builder, setter, SELECT_DISPONIBILITE);
        
    }
    
}
