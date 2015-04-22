package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.DAO.DAOException;

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
                    getServletContext().getRequestDispatcher("/WEB-INF/erreur/controleurErreur.jsp").forward(request, response);
                }
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

    public void demarrerMois(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {
            throw new UnsupportedOperationException();
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