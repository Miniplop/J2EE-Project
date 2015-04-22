package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Consommateur;
import modele.DAO.DAOException;
import modele.DAO.ProduitDAO;

@WebServlet(name = "Consommateur", urlPatterns = {"/consommateur"})
public class ConsommateurController extends UtilisateurController {
    
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Consommateur self = (Consommateur) session.getAttribute("utilisateur");
        if(self == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/erreur/erreur_connexion.jsp").forward(request, response);
        } else {
            super.doGet(request, response);

            String action = request.getParameter("action");
            try {
                if (action != null) {
                    if (action.equals("signer_contrat")) {
                        signerContrat(request, response);
                    } else if (action.equals("renseigner_disponibilites")) {
                        renseignerDisponibilites(request, response);
                    } else if (action.equals("consulter_permanence")) {
                        consulterPermanence(request, response);
                    } else {
                        getServletContext().getRequestDispatcher("/WEB-INF/erreur/controleurErreur.jsp").forward(request, response);
                    }
                }
            } catch (DAOException e) {
                request.setAttribute("erreurMessage", e.getMessage());
                getServletContext().getRequestDispatcher("/WEB-INF/erreur/bdErreur.jsp").forward(request, response);
            }
        }
    }
    
    @Override
    public void consulter(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {
        HttpSession session = request.getSession();
        Consommateur self = (Consommateur) session.getAttribute("utilisateur");
        ProduitDAO produitDAO = new ProduitDAO(super.ds);
        request.setAttribute("produits", produitDAO.getProduits());
        request.setAttribute("self", self);
        getServletContext().getRequestDispatcher("/WEB-INF/consommateur/consulter.jsp").forward(request, response);
    }

    public void signerContrat(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException  {
            throw new UnsupportedOperationException();
    }

    public void renseignerDisponibilites(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException  {
            throw new UnsupportedOperationException();
    }

    public void consulterPermanence(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException  {
            throw new UnsupportedOperationException();
    }
}