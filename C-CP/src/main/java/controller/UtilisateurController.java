package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Consommateur;
import modele.DAO.*;
import modele.Producteur;
import modele.Utilisateur;

@WebServlet(name = "Utilisateur", urlPatterns = {"/utilisateur"})
public class UtilisateurController extends Controller {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");

        try {
            if (action == null) {
                consulter(request, response);
            } else if (action.equals("login")) {
                connexion(request, response);
            } else if (action.equals("logout")) {
                deconnexion(request, response);
            }
        } catch (DAOException e) {
            request.setAttribute("erreurMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/erreur/bdErreur.jsp").forward(request, response);
        }
    }

    public void connexion(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {
        HttpSession session = request.getSession();
        UtilisateurDAO utilisateurDAO = new ConsommateurDAO(super.ds);
        Utilisateur utilisateur = utilisateurDAO.getUtilisateur(request.getParameter("email"), request.getParameter("nom"), request.getParameter("type"));
        if(utilisateur == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/erreur/erreur_connexion.jsp").forward(request, response);
        } else {
            session.setAttribute("user_id", utilisateur.getId());
            session.setAttribute("is_consommateur", utilisateur instanceof Consommateur);
        }
        if(utilisateur instanceof Consommateur)
            getServletContext().getRequestDispatcher("/WEB-INF/consommateur/consulter.jsp").forward(request, response);
        else if(utilisateur instanceof Producteur)
            getServletContext().getRequestDispatcher("/WEB-INF/erreur_connexion.jsp").forward(request, response);
    }

    public void consulter(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("user_id") != null) {
            if(session.getAttribute("is_consommateur") == true) {
                
            } else {
                
            }
        } else {
            ProduitDAO produitDAO = new ProduitDAO(super.ds);
            request.setAttribute("produits", produitDAO.getProduits());
            getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/consulter.jsp").forward(request, response);
        }
    }

    public void deconnexion(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException  {
            throw new UnsupportedOperationException();
    }
}