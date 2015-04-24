package modele.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.sql.DataSource;
import modele.Consommateur;
import modele.Disponibilite;
import modele.Mois;
import modele.Semaine;

public class ConsommateurDAO extends UtilisateurDAO {
    
    private static final String SELECT_CONSOMMATEURS = "SELECT * FROM Consommateur";
    private static final String SELECT_CONSOMMATEUR_BY_ID = "SELECT * FROM Consommateur WHERE id = ?";

    public ConsommateurDAO(DataSource ds) {
        super(ds, null, SELECT_CONSOMMATEURS, null);
    }

    public List<Consommateur> getConsommateurs() throws DAOException {
        DAOModeleBuilder<Consommateur> builder = new DAOModeleBuilder<Consommateur>() {
            @Override
            public Consommateur build(ResultSet rs) throws DAOException,SQLException {
                return new Consommateur(rs.getShort("id"));
            }
        };
        
        List<Consommateur> consommateurs = super.gets(builder);
        for(Consommateur consommateur : consommateurs)
            this.getUtilisateur(consommateur);
        return consommateurs;
    }

    public Consommateur getConsommateur(final int id) throws DAOException {
        DAOModeleBuilder<Consommateur> builder = new DAOModeleBuilder<Consommateur>() {
            @Override
            public Consommateur build(ResultSet rs) throws DAOException {
                try {
                    Consommateur conso = new Consommateur((short) rs.getInt("id"));
                    return conso;
                } catch (SQLException ex) {
                    throw new DAOException(ex.getMessage(), ex);
                }
            }
        };
        DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws SQLException {
                statement.setInt(1, id);
            }
        };
        
        Consommateur consommateur = (Consommateur) super.getSingle(builder, setter, this.SELECT_CONSOMMATEUR_BY_ID);
        return (Consommateur)this.getUtilisateur(consommateur);
    }

    public List<Consommateur> getConsommateursDisponible(Semaine semaine) throws DAOException {
       LinkedList<Mois> moisLinked = new LinkedList<>();
        MoisDAO moisDAO = new MoisDAO(dataSource);
        int moi_index = 0;
        List<Mois> mois = moisDAO.getMois();
        for(Mois moi : mois)
            moisLinked.addLast(moi);
        
        List<Consommateur> consommateurs = new ArrayList<>();
        DisponibiliteDAO disponibiliteDAO = new DisponibiliteDAO(dataSource);
        ContratDAO contratDAO = new ContratDAO(dataSource);
        SemaineDAO semaineDAO = new SemaineDAO(dataSource);
        Mois moisDeLaSemaine = moisDAO.getMoisBySemaineId(semaine.getId());
        List<Disponibilite> disponibilites = disponibiliteDAO.getDisponibilites();
        for(Disponibilite dispo : disponibilites) {
            if(dispo.getNumero() != 0) {
                dispo.setContrat(contratDAO.getContrat(dispo.getContrat_id()));
                dispo.setConsommateur(this.getConsommateur(dispo.getConsommateur_id()));
                dispo.getContrat().setDebut_semaine(semaineDAO.getSemaine(dispo.getContrat().getSemaine_Debut_id()));
                int numero_moi = 0;
                int numero_semaine = 0;
                int i = 0;
                for(Mois moi : moisLinked) {

                    if(moi.getSemaine_1_id() == dispo.getContrat().getSemaine_Debut_id() 
                            || moi.getSemaine_2_id() == dispo.getContrat().getSemaine_Debut_id()
                            || moi.getSemaine_3_id() == dispo.getContrat().getSemaine_Debut_id()
                            || moi.getSemaine_4_id() == dispo.getContrat().getSemaine_Debut_id()) {

                        numero_semaine = semaineDAO.getSemaine(dispo.getContrat().getSemaine_Debut_id()).getNumero();
                        numero_moi = i;
                    }


                    if(moi.getSemaine_1_id() == semaine.getId() 
                            || moi.getSemaine_2_id() == semaine.getId()
                            || moi.getSemaine_3_id() == semaine.getId()
                            || moi.getSemaine_4_id() == semaine.getId())
                        moi_index = i;
                    i++;
                }
                int addMois = 0;
                int semaineDispoContrat = numero_semaine + dispo.getNumero();
                while(semaineDispoContrat > 4) {
                    semaineDispoContrat = semaineDispoContrat % 4;
                    addMois++;
                }
                numero_moi+=addMois;
                numero_semaine = semaineDispoContrat;
                if(numero_moi == moi_index) {
                    Mois moiDispo = mois.get(numero_moi);
                    if(numero_semaine == 1 && moiDispo.getSemaine_1_id() == semaine.getId())
                        consommateurs.add(dispo.getConsommateur());
                    else if(numero_semaine == 2 && moiDispo.getSemaine_2_id() == semaine.getId())
                        consommateurs.add(dispo.getConsommateur());
                    else if(numero_semaine == 3 && moiDispo.getSemaine_3_id() == semaine.getId())
                        consommateurs.add(dispo.getConsommateur());
                    else if(numero_semaine == 4 && moiDispo.getSemaine_4_id() == semaine.getId())
                        consommateurs.add(dispo.getConsommateur());
                }
            }
        }
        for(Consommateur conso : consommateurs)
            this.getUtilisateur(conso);
        return consommateurs;
    }
}