package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Contrat;
import modele.DAO.ConsommateurDAO;
import modele.DAO.ContratDAO;
import modele.DAO.DAOException;
import modele.DAO.MoisDAO;
import modele.DAO.ProducteurDAO;
import modele.DAO.ProduitDAO;
import modele.DAO.SemaineDAO;
import modele.Mois;
import modele.Producteur;
import modele.Produit;

@WebServlet(name = "Producteur", urlPatterns = {"/producteur"})
public class ProducteurController extends UtilisateurController {
    
    private Producteur self;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        ProducteurDAO producteurDAO = new ProducteurDAO(ds);
        try {
            this.self = producteurDAO.getProducteur((int)session.getAttribute("utilisateur_id"));
        } catch (DAOException e) {
                request.setAttribute("erreurMessage", e.getMessage());
                getServletContext().getRequestDispatcher("/WEB-INF/erreur/bdErreur.jsp").forward(request, response);
        }
        if(this.self == null) {
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
        MoisDAO moisDAO = new MoisDAO(ds);
        SemaineDAO semaineDAO = new SemaineDAO(ds);
        request.setAttribute("self", this.self);
        request.setAttribute("contrats", getContratForProducteur(this.self));
        Mois mois = moisDAO.getLastMois();
        mois.addSemaine(semaineDAO.getSemaine(mois.getSemaine_1_id()));
        mois.addSemaine(semaineDAO.getSemaine(mois.getSemaine_2_id()));
        mois.addSemaine(semaineDAO.getSemaine(mois.getSemaine_3_id()));
        mois.addSemaine(semaineDAO.getSemaine(mois.getSemaine_4_id()));
        request.setAttribute("mois", mois);
        getServletContext().getRequestDispatcher("/WEB-INF/producteur/consulter.jsp").forward(request, response);
    }

    public void renseignerProduit(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {
        String nom = request.getParameter("nom");
        String unite = request.getParameter("unite");
        int quantite = Integer.parseInt(request.getParameter("quantite"));
        int duree = Integer.parseInt(request.getParameter("duree"));
        
        ProduitDAO produitDAO = new ProduitDAO(super.ds);
        Produit produit = produitDAO.addProduit(nom, unite, quantite, duree, this.self);
        response.setContentType("text/plain");  
        response.setCharacterEncoding("UTF-8"); 
        if(produit == null) {
            response.getWriter().write("erreur");
        } else {
            response.getWriter().write(Integer.toString(produit.getId()));
        }
    }

    public void validerContrat(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {
        int contrat_id = Integer.parseInt(request.getParameter("contrat_id"));
        int semaine_id = Integer.parseInt(request.getParameter("semaine_id"));
        int valide = request.getParameter("accept") != null ? 1 : 0;
        System.err.println(valide);
        ContratDAO contratDAO = new ContratDAO(ds);
        SemaineDAO semaineDAO = new SemaineDAO(ds);
        contratDAO.modifyContrat(contratDAO.getContrat(contrat_id), valide, semaineDAO.getSemaine(semaine_id));
        this.consulter(request, response);
    }

    private Map<Integer, List<Contrat>> getContratForProducteur(Producteur self) throws DAOException {
        ContratDAO contratDAO = new ContratDAO(ds);
        ProduitDAO produitDAO = new ProduitDAO(ds);
        ConsommateurDAO consommateurDAO = new ConsommateurDAO(ds);
        List<Produit> produits = produitDAO.getProduitsByProducteur(self);
        Map<Integer, List<Contrat>> contrats = new HashMap<>();
        for(Produit prod : produits) {
            if(contrats.get(prod.getId()) == null)
                contrats.put(prod.getId(), new ArrayList<Contrat>());
            List<Contrat> contratsproduit = contratDAO.getContratByProduit(prod);
            for(Contrat contrat : contratsproduit)
                contrat.setConsommateur(consommateurDAO.getConsommateur(contrat.getConsommateur_id()));
            contrats.get(prod.getId()).addAll(contratsproduit);
        }
        System.err.println(contrats.size());
        return contrats;
    }
}