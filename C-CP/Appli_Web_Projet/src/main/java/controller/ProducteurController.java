package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.DAO.ContratDAO;
import modele.DAO.DAOException;
import modele.DAO.ProduitDAO;
import modele.Producteur;
import modele.Produit;

@WebServlet(name = "Producteur", urlPatterns = {"/producteur"})
public class ProducteurController extends UtilisateurController {
    
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Producteur self = (Producteur) session.getAttribute("utilisateur");
        if(self == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/erreur/erreur_connexion.jsp").forward(request, response);
        } else {
            super.doGet(request, response);
            String action = request.getParameter("action");
            try {
                if (action != null) {
                    switch (action) {
                        case "renseigner_produit":
                            renseignerProduit(request, response);
                            break;
                        case "valider_contrat":
                            validerContrat(request, response);
                            break;
                        default:
                            getServletContext().getRequestDispatcher("/WEB-INF/erreur/controleurErreur.jsp").forward(request, response);
                            break;
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
        Producteur self = (Producteur) session.getAttribute("utilisateur");
        ContratDAO contratDAO = new ContratDAO(ds);
        request.setAttribute("self", self);
        request.setAttribute("contrats", contratDAO.getContratEnAttente());
        getServletContext().getRequestDispatcher("/WEB-INF/producteur/consulter.jsp").forward(request, response);
    }

    public void renseignerProduit(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {
        HttpSession session = request.getSession();
        Producteur self = (Producteur) session.getAttribute("utilisateur");
        String nom = request.getParameter("nom");
        String unite = request.getParameter("unite");
        int quantite = Integer.parseInt(request.getParameter("quantite"));
        int duree = Integer.parseInt(request.getParameter("duree"));
        
        ProduitDAO produitDAO = new ProduitDAO(super.ds);
        Produit produit = produitDAO.addProduit(nom, unite, quantite, duree, self);
        response.setContentType("text/plain");  
        response.setCharacterEncoding("UTF-8"); 
        if(produit == null) {
            response.getWriter().write("erreur");
        } else {
            response.getWriter().write(Integer.toString(produit.getId()));
        }
    }

    public void validerContrat(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {
        HttpSession session = request.getSession();
        Producteur self = (Producteur) session.getAttribute("utilisateur");
        int contrat_id = Integer.parseInt(request.getParameter("contrat_id"));
        ContratDAO contratDAO = new ContratDAO(ds);
        contratDAO.modifyContrat(contratDAO.getContrat(contrat_id), 0, null);
    }
}