package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.DAO.DAOException;

@WebServlet(name = "Producteur", urlPatterns = {"/producteur"})
public class ProducteurController extends UtilisateurController {
    
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        super.doGet(request, response);
        
        String action = request.getParameter("action");
        try {
            if (action.equals("renseigner_produit")) {
                renseignerProduit(request, response);
            } else if (action.equals("valider_contrat")) {
                validerContrat(request, response);
            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/erreur/controleurErreur.jsp").forward(request, response);
            }
        } catch (DAOException e) {
            request.setAttribute("erreurMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/erreur/bdErreur.jsp").forward(request, response);
        }
    }

    @Override
    public void consulter(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {
            throw new UnsupportedOperationException();
    }

    public void renseignerProduit(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {
            throw new UnsupportedOperationException();
    }

    public void validerContrat(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {
            throw new UnsupportedOperationException();
    }
}