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
import modele.Consommateur;
import modele.DAO.ConsommateurDAO;
import modele.DAO.DAOException;
import modele.DAO.MoisDAO;
import modele.DAO.SemaineDAO;
import modele.DAO.ContratDAO;
import modele.Mois;
import modele.Semaine;


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
                } else if (action.equals("update_permanence")) {
                    updatePermanence(request, response);
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
        String month = request.getParameter("nom_mois");
        MoisDAO moisDAO = new MoisDAO (super.ds);
        SemaineDAO semaineDAO = new SemaineDAO (super.ds);
        moisDAO.addMois(annee,month, semaineDAO.addSemaine(1), semaineDAO.addSemaine(2), semaineDAO.addSemaine(3), semaineDAO.addSemaine(4));
        this.consulter(request,response);
    }

    public void affecterPermanences(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {
            ConsommateurDAO consommateurDAO = new ConsommateurDAO(super.ds);
            SemaineDAO semaineDAO = new SemaineDAO(super.ds);
            request.setAttribute("consommateur", consommateurDAO.getConsommateurs());
            request.setAttribute("num_perm", request.getParameter("num_perm"));
            request.setAttribute("semaine", semaineDAO.getSemaine(Integer.parseInt(request.getParameter("semaine_id"))));
            getServletContext().getRequestDispatcher("/WEB-INF/respo_planning/choisir_user.jsp").forward(request, response);
    }

    public void statistiquePermanences(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {
        SemaineDAO semaineDAO = new SemaineDAO(super.ds);
        ConsommateurDAO consommateurDAO = new ConsommateurDAO(super.ds);
        Map<Consommateur,ArrayList<Integer>> map = new HashMap<Consommateur,ArrayList<Integer>>();
        List<Consommateur> list = consommateurDAO.getConsommateurs();
        ContratDAO  contratDAO = new  ContratDAO(super.ds);
        
        for(Consommateur c : list){
            Integer nmbr = semaineDAO.getNombre(c.getId());
            Integer nmbr_contrat = contratDAO.getCountContrat(c.getId());
            ArrayList<Integer> list_stat = new ArrayList<Integer>();
            list_stat.add(nmbr);
            list_stat.add(nmbr_contrat);
            map.put(c, list_stat);
        }
        request.setAttribute("stat_count", map);
        request.setAttribute("Consommateur", list);
        getServletContext().getRequestDispatcher("/WEB-INF/respo_planning/statistique.jsp").forward(request, response);
    }

    private void updatePermanence(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {
        if (request.getParameter("permanent_choisi") != null){
           SemaineDAO semaineDAO = new SemaineDAO(super.ds);
           ConsommateurDAO consommateurDAO = new ConsommateurDAO(super.ds);
           if (Integer.parseInt(request.getParameter("num_perm")) == 1 ){
             semaineDAO.modifySemainePermanent1(semaineDAO.getSemaine(Integer.parseInt(request.getParameter("semaine_id"))), consommateurDAO.getConsommateur(Integer.parseInt(request.getParameter("permanent_choisi"))));  
           }else if (Integer.parseInt(request.getParameter("num_perm")) == 2){
             semaineDAO.modifySemainePermanent2(semaineDAO.getSemaine(Integer.parseInt(request.getParameter("semaine_id"))), consommateurDAO.getConsommateur(Integer.parseInt(request.getParameter("permanent_choisi"))));  
           }
        }
        this.consulter(request, response);
    }
}