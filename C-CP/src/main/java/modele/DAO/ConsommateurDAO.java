package modele.DAO;

import java.util.List;
import javax.sql.DataSource;
import modele.Consommateur;

public class ConsommateurDAO extends UtilisateurDAO {
    
    private static final String SELECT_CONSOMMATEURS = "";
    private static final String SELECT_CONSOMMATEUR = "";

    public ConsommateurDAO(DataSource ds) {
        super(ds, null, SELECT_CONSOMMATEURS, null, SELECT_CONSOMMATEUR);
    }

    public Consommateur addConsommateur(String nom, String prenom, String email, String adresse) {
            throw new UnsupportedOperationException();
    }

    public void modifyConsommateur(Consommateur consommateur, String nom, String prenom, String email, String adresse) {
            throw new UnsupportedOperationException();
    }

    public List<Consommateur> getConsommateurs() {
            throw new UnsupportedOperationException();
    }
}