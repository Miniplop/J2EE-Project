package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.DAO.DAOException;
import modele.DAO.MoisDAO;
import modele.DAO.ProduitDAO;
import modele.DAO.SemaineDAO;
import modele.Mois;
import modele.Producteur;
import modele.Produit;

@WebServlet(name = "ResponsablePlanning", urlPatterns = {"/responsable"})
public class ResponsablePlanningController extends UtilisateurController {
    
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        super.doGet(request, response);
        
        String action = request.getParameter("action");
        try {
            if (action != null) {
                if (action.equals("demarrer_mois")) {
                    demarrerMois(request, response);
                } else if (action.equals("affecter_permanences")) {
                    affecterPermanences(request, response);
                } else if (action.equals("modifier_permanence")) {
                    modifierPermanence(request, response);
                }  else if (action.equals("visualiser_permanences_passees")) {
                    visualiserPermanencesPassees(request, response);
                }  else if (action.equals("statistique_permanences")) {
                    statistiquePermanences(request, response);
                } else {
                    consulter(request,response);
                }
            }
        } catch (DAOException e) {
            request.setAttribute("erreurMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/erreur/bdErreur.jsp").forward(request, response);
        }
    }

    @Override
    public void consulter(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {
            MoisDAO MoisDAO = new MoisDAO(super.ds);
            request.setAttribute("Mois", MoisDAO.getMois());
            getServletContext().getRequestDispatcher("/WEB-INF/respo_planning/consulter.jsp").forward(request, response);
    }

    public void demarrerMois(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {

        String annee = request.getParameter("annee");
        String month = request.getParameter("mois");
        int quantite = Integer.parseInt(request.getParameter("quantite"));
        int duree = Integer.parseInt(request.getParameter("duree"));
        
        MoisDAO MoisDAO = new MoisDAO (super.ds);
        Mois mois = MoisDAO.addMois(annee,month);
        /*
        response.setContentType("text/plain");  
        response.setCharacterEncoding("UTF-8"); 
        if(produit == null) {
            response.getWriter().write("erreur");
        } else {
            response.getWriter().write(Integer.toString(produit.getId()));
        }*/
        request.removeAttribute("action");
        getServletContext().getRequestDispatcher("/WEB-INF/respo_planning/consulter.jsp").forward(request, response);
    }

    public void affecterPermanences(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {
            throw new UnsupportedOperationException();
    }

    public void modifierPermanence(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {
            throw new UnsupportedOperationException();
    }

    public void visualiserPermanencesPassees(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {
            throw new UnsupportedOperationException();
    }

    public void statistiquePermanences(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {
            throw new UnsupportedOperationException();
    }
}