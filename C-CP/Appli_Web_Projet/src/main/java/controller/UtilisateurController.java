package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Consommateur;
import modele.DAO.*;
import modele.Producteur;
import modele.Produit;
import modele.Utilisateur;

@WebServlet(name = "Utilisateur", urlPatterns = {"/utilisateur"})
public class UtilisateurController extends Controller {

    private static final String EMAIl_RESPO = "loisel@hotmail.fr";
    
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
        response.setContentType("text/plain");  
        response.setCharacterEncoding("UTF-8"); 
        if(utilisateur == null) {
            if (request.getParameter("email").equalsIgnoreCase(UtilisateurController.EMAIl_RESPO)) {
                response.getWriter().write("responsable");
            } else {
                response.getWriter().write("erreur");
            }
        } else {
            session.setAttribute("utilisateur_id", (int)utilisateur.getId());                
            if(utilisateur instanceof Consommateur)
                response.getWriter().write("consommateur");
            else if(utilisateur instanceof Producteur)
                response.getWriter().write("producteur");
        }
        
    }

    public void consulter(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {
        HttpSession session = request.getSession();
        Utilisateur user = null;
        UtilisateurController userController = null;
        if((user = (Utilisateur) session.getAttribute("utilisateur")) != null) {
            if(user instanceof Producteur) {
                userController = new ProducteurController();
            }else {
                userController = new ConsommateurController();
            }
            userController.ds = ds;
            userController.init(this.getServletConfig());
            userController.consulter(request, response);
        } else {
            ProduitDAO produitDAO = new ProduitDAO(super.ds);
            List<Produit> produits = produitDAO.getProduits();
            ProducteurDAO producteurDAO = new ProducteurDAO(super.ds);
            for(Produit produit : produits)
                produit.setProducteur(producteurDAO.getProducteurOfProduit(produit));
            request.setAttribute("produits", produits);
            getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/consulter.jsp").forward(request, response);
        }
    }
    
    // Impossible techniquement  car il n'y a pas de bouton de déconexion dans la partie utilisateur ...
    public void deconnexion(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException  {
        HttpSession session = request.getSession();
        session.removeAttribute("utilisateur_id");
        ProduitDAO produitDAO = new ProduitDAO(super.ds);
        List<Produit> produits = produitDAO.getProduits();
        ProducteurDAO producteurDAO = new ProducteurDAO(super.ds);
        for(Produit produit : produits)
            produit.setProducteur(producteurDAO.getProducteurOfProduit(produit));
        request.setAttribute("produits", produits);
        getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/consulter.jsp").forward(request, response);
    }
}