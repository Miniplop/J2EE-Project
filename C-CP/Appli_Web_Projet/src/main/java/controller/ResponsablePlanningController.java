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
import modele.Consommateur;
import modele.DAO.ConsommateurDAO;
import modele.DAO.DAOException;
import modele.DAO.MoisDAO;
import modele.DAO.SemaineDAO;
import modele.DAO.ContratDAO;
import modele.Semaine;


@WebServlet(name = "ResponsablePlanning", urlPatterns = {"/responsable"})
public class ResponsablePlanningController extends UtilisateurController {
    
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        try {
            boolean test = (boolean)session.getAttribute("responsable") ;
        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/WEB-INF/erreur/erreur_connexion.jsp").forward(request, response);
        }
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
            ConsommateurDAO consommateurDAO = new ConsommateurDAO(super.ds);
            Map <Integer,Consommateur> map = new HashMap<Integer,Consommateur>();
            List <Consommateur> list = consommateurDAO.getConsommateurs();
            for (Consommateur c : list){
                map.put((int)c.getId(), c);
            }
            request.setAttribute("consommateur_map", map);
            request.setAttribute("Mois", MoisDAO.getMois());
            getServletContext().getRequestDispatcher("/WEB-INF/respo_planning/consulter.jsp").forward(request, response);
    }

    public void demarrerMois(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {

        String annee = request.getParameter("annee");
        String month = request.getParameter("nom_mois");
                System.err.println(annee);
                System.err.println(month);
        MoisDAO moisDAO = new MoisDAO (super.ds);
        SemaineDAO semaineDAO = new SemaineDAO (super.ds);
        Semaine semaine1 = semaineDAO.addSemaine(1);
                System.err.println(semaine1);
        Semaine semaine2 = semaineDAO.addSemaine(2);
                System.err.println(semaine2);
        Semaine semaine3 = semaineDAO.addSemaine(3);
                System.err.println(semaine3);
        Semaine semaine4 = semaineDAO.addSemaine(4);
                System.err.println(semaine4);
        moisDAO.addMois(annee,month, semaine1, semaine2, semaine3, semaine4);
                System.err.println("end");
        this.consulter(request,response);
    }

    public void affecterPermanences(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {
            ConsommateurDAO consommateurDAO = new ConsommateurDAO(super.ds);
            SemaineDAO semaineDAO = new SemaineDAO(super.ds);
            Semaine semaine = semaineDAO.getSemaine(Integer.parseInt(request.getParameter("semaine_id")));
            request.setAttribute("consommateur", consommateurDAO.getConsommateurs());
            request.setAttribute("num_perm", request.getParameter("num_perm"));
            request.setAttribute("permanentId", request.getParameter("permanentId"));
            request.setAttribute("semaine", semaine);
            request.setAttribute("consommateurs_dispo", consommateurDAO.getConsommateursDisponible(semaine));
            getServletContext().getRequestDispatcher("/WEB-INF/respo_planning/choisir_user.jsp").forward(request, response);
    }

    public void statistiquePermanences(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {
        SemaineDAO semaineDAO = new SemaineDAO(super.ds);
        ConsommateurDAO consommateurDAO = new ConsommateurDAO(super.ds);
        Map<Consommateur,ArrayList<Integer>> map = new HashMap<Consommateur,ArrayList<Integer>>();
        List<Consommateur> list = consommateurDAO.getConsommateurs();
        ContratDAO contratDAO = new  ContratDAO(super.ds);
        
        for(Consommateur c : list){
            Integer nmbr = semaineDAO.getNombreSemaineByConsommateur(c.getId());
            Integer nmbr_contrat = contratDAO.getContratsByConsommateur(c).size();
            ArrayList<Integer> list_stat = new ArrayList<>();
            list_stat.add(nmbr);
            list_stat.add(nmbr_contrat);
            map.put(c, list_stat);
        }
        request.setAttribute("stat_count", map);
        request.setAttribute("consommateurs", list);
        getServletContext().getRequestDispatcher("/WEB-INF/respo_planning/statistique.jsp").forward(request, response);
    }

    private void updatePermanence(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {
           SemaineDAO semaineDAO = new SemaineDAO(super.ds);
           ConsommateurDAO consommateurDAO = new ConsommateurDAO(super.ds);
           if (Integer.parseInt(request.getParameter("num_perm")) == 1 ){
             semaineDAO.modifySemainePermanent1(semaineDAO.getSemaine(Integer.parseInt(request.getParameter("semaine_id"))), consommateurDAO.getConsommateur(Integer.parseInt(request.getParameter("permanent_choisi"))));  
           }else if (Integer.parseInt(request.getParameter("num_perm")) == 2){
             semaineDAO.modifySemainePermanent2(semaineDAO.getSemaine(Integer.parseInt(request.getParameter("semaine_id"))), consommateurDAO.getConsommateur(Integer.parseInt(request.getParameter("permanent_choisi"))));  
           }
        this.consulter(request, response);
    }
}