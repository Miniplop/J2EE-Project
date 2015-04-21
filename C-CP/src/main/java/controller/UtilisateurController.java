package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.DAO.*;

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
            getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
        }
    }

    public void connexion(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {
            throw new UnsupportedOperationException();
    }

    public void consulter(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {
        ProduitDAO produitDAO = new ProduitDAO(super.ds);
        request.setAttribute("produits", produitDAO.getProduits());
        getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/consulter.jsp").forward(request, response);
    }

    public void deconnexion(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException  {
            throw new UnsupportedOperationException();
    }
}