package modele.DAO;

import java.util.List;
import modele.Utilisateur;

public class UtilisateurDAO extends AbstractDAO {
	private static final String INSERT_UTILISATEUR="";
	private static final String SELECT_UTILISATEURS="";
	private static final String UPDATE_UTILISATEUR="";

    public UtilisateurDAO(Object ds, String insert_query, String select_query, String update_query) {
        super(ds, insert_query, select_query, update_query);
    }

    protected Utilisateur addUtilisateur(String nom, String prenom, String email, String adresse) {
            throw new UnsupportedOperationException();
    }

    protected void modifyUtilisateur(Utilisateur utilisateur, String nom, String prenom, String email, String adresse) {
            throw new UnsupportedOperationException();
    }

    protected List<Utilisateur> getUtilisateurs() {
            throw new UnsupportedOperationException();
    }

    protected Object add(DAOQueryParameter setter) {
            throw new UnsupportedOperationException();
    }

    protected void modify(DAOQueryParameter setter) {
            throw new UnsupportedOperationException();
    }

    protected List get(DAOQueryParameter setter) {
            throw new UnsupportedOperationException();
    }
}