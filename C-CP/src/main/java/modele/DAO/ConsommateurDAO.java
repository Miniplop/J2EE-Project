package modele.DAO;

import java.util.List;
import modele.Consommateur;

public class ConsommateurDAO extends UtilisateurDAO {
    //TODO
    private static final String INSERT_CONSOMMATEUR = "";
    private static final String SELECT_CONSOMMATEURS = "";
    private static final String UPDATE_CONSOMMATEUR = "";

    public ConsommateurDAO(Object ds, String insert_query, String select_query, String update_query) {
        super(ds, insert_query, select_query, update_query);
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