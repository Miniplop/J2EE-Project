/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import modele.Consommateur;
import modele.Contrat;
import modele.Disponibilite;

/**
 *
 * @author loiseln
 */
public class DisponibiliteDAO extends AbstractDAO<Disponibilite> {
    
    private static final String INSERT_DISPONIBILITE = "INSERT INTO Disponibilite (consommateur_id, contrat_id, numero_semaine) VALUES (?, ?, ?)";
    private static final String SELECT_DISPONIBILITES = "SELECT * FROM Disponibilite";
    private static final String SELECT_DISPONIBILITE = "SELECT * FROM Disponibilite WHERE id = ?";
    private static final String SELECT_DISPONIBILITES_BY_CONSOMMATEUR = "SELECT * FROM Disponibilite WHERE consommateur_id = ?";
    private static final String SELECT_DISPONIBILITES_BY_CONTRAT = "SELECT * FROM Disponibilite WHERE contrat_id = ?";
    private static final String SELECT_DISPONIBILITES_BY_SEMAINE = "SELECT * FROM Disponibilite WHERE numero_semaine = ?";
    public DisponibiliteDAO(DataSource ds) {
        super(ds, INSERT_DISPONIBILITE, SELECT_DISPONIBILITES, null);
    }
    
    public Disponibilite addDisponibilite(final Consommateur consommateur, final Contrat contrat, final int numero_semaine) throws DAOException {
        DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws SQLException {
                statement.setInt(1, consommateur.getId());
                statement.setInt(2, contrat.getId());
                statement.setInt(3, numero_semaine);
            }
        };
        super.add(setter);
        return null;
    }
    public List<Disponibilite> getDisponibilitesByContrat(final Contrat contrat) throws DAOException {
        DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws SQLException {
                statement.setInt(1, contrat.getId());
            }
        };
        DAOModeleBuilder<Disponibilite> builder;
        builder = new DAOModeleBuilder<Disponibilite>() {
            @Override
            public Disponibilite build(ResultSet rs) throws SQLException, DAOException {
                return new Disponibilite(rs.getInt("id"), rs.getInt("numero_semaine"), contrat, rs.getInt("consommateur_id"));
            }
        };
        return this.getMultiple(builder, setter, SELECT_DISPONIBILITES_BY_CONTRAT);
    }
    
    public Map<Integer, List<Disponibilite>> getDisponibilitesByConsommateur(final Consommateur consommateur) throws DAOException {
        final Map<Integer, List<Disponibilite>> disponibilites = new HashMap<>();
        DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws SQLException {
                statement.setInt(1, consommateur.getId());
            }
        };
        DAOModeleBuilder<Disponibilite> builder = new DAOModeleBuilder<Disponibilite>() {
            @Override
            public Disponibilite build(ResultSet rs) throws SQLException, DAOException {
                Disponibilite disponibilite = new Disponibilite(rs.getInt("id"), rs.getInt("numero_semaine"), consommateur, rs.getInt("contrat_id"));
                if(disponibilites.get(disponibilite.getContrat_id()) == null)
                    disponibilites.put(disponibilite.getContrat_id(), new ArrayList<Disponibilite>());
                disponibilites.get(disponibilite.getContrat_id()).add(disponibilite);
                return disponibilite;
            }
        };
       this.getMultiple(builder, setter, SELECT_DISPONIBILITES_BY_SEMAINE);
        return disponibilites;
    }
    
    public List<Consommateur> getDisponibilitesBySemaine(final int id_semaine) throws DAOException {
        final List<Consommateur> results = new ArrayList<Consommateur>();
        DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws SQLException {
                statement.setInt(1, id_semaine);
            }
        };
        DAOModeleBuilder<Disponibilite> builder = new DAOModeleBuilder<Disponibilite>() {
            @Override
            public Disponibilite build(ResultSet rs) throws SQLException, DAOException {
                return new Disponibilite(rs.getInt("id"), rs.getInt("consommateur_id"), rs.getInt("contrat_id"),id_semaine);
            }
        };
        this.getMultiple(builder, setter, SELECT_DISPONIBILITES_BY_CONSOMMATEUR);
        return results;
    }
    
    public Disponibilite getDisponibilite(final int id) throws DAOException {
        DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws SQLException {
                statement.setInt(1, id);
            }
        };
        DAOModeleBuilder<Disponibilite> builder = new DAOModeleBuilder<Disponibilite>() {
            @Override
            public Disponibilite build(ResultSet rs) throws SQLException, DAOException {

                return new Disponibilite(rs.getInt("id"), rs.getInt("numero_semaine"), rs.getInt("contrat_id"), rs.getInt("consommateur_id"));
            }
        };
        return this.getSingle(builder, setter, SELECT_DISPONIBILITE);
    }
    
}
