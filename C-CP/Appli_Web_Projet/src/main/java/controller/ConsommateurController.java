package controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Consommateur;
import modele.Contrat;
import modele.DAO.ConsommateurDAO;
import modele.DAO.ContratDAO;
import modele.DAO.DAOException;
import modele.DAO.DisponibiliteDAO;
import modele.DAO.MoisDAO;
import modele.DAO.ProducteurDAO;
import modele.DAO.ProduitDAO;
import modele.DAO.SemaineDAO;
import modele.Mois;
import modele.Produit;
import modele.Semaine;

@WebServlet(name = "Consommateur", urlPatterns = {"/consommateur"})
public class ConsommateurController extends UtilisateurController {
    
    private Consommateur self;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        ConsommateurDAO consommateurDAO = new ConsommateurDAO(ds);
        try {
            self = consommateurDAO.getConsommateur((int)session.getAttribute("utilisateur_id"));
        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/WEB-INF/erreur/erreur_connexion.jsp").forward(request, response);
        }
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
                    } else if (action.equals("renouveler_contrat")) {
                        renouvelerContrat(request, response);
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
    public void consulter(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {;
        ContratDAO contratDAO = new ContratDAO(ds);
        ProduitDAO produitDAO = new ProduitDAO(super.ds);
        SemaineDAO semaineDAO = new SemaineDAO(ds);
        ConsommateurDAO consommateurDAO = new ConsommateurDAO(ds);
        MoisDAO moisDAO = new MoisDAO(ds);
        Mois mois = moisDAO.getLastMois();
        Semaine semaine1 = semaineDAO.getSemaine(mois.getSemaine_1_id());
        Semaine semaine2 = semaineDAO.getSemaine(mois.getSemaine_2_id());
        Semaine semaine3 = semaineDAO.getSemaine(mois.getSemaine_3_id());
        Semaine semaine4 = semaineDAO.getSemaine(mois.getSemaine_4_id());
        if(semaine1.getPermanent1Id() == self.getId()) {
            semaine1.setPermanent2(consommateurDAO.getConsommateur(semaine1.getPermanent2Id()));
            semaine1.setMois(mois);
        } else if(semaine1.getPermanent2Id() == self.getId()) {
            semaine1.setPermanent1(consommateurDAO.getConsommateur(semaine1.getPermanent1Id()));
            semaine1.setMois(mois);
        }
        if(semaine2.getPermanent1Id() == self.getId()) {
            semaine2.setPermanent2(consommateurDAO.getConsommateur(semaine2.getPermanent2Id()));
            semaine2.setMois(mois);
        } else if(semaine2.getPermanent2Id() == self.getId()) {
            semaine2.setPermanent1(consommateurDAO.getConsommateur(semaine2.getPermanent1Id()));
            semaine2.setMois(mois);
        }
        if(semaine3.getPermanent1Id() == self.getId()) {
            semaine3.setPermanent2(consommateurDAO.getConsommateur(semaine3.getPermanent2Id()));
            semaine3.setMois(mois);
        } else if(semaine3.getPermanent2Id() == self.getId()) {
            semaine3.setPermanent1(consommateurDAO.getConsommateur(semaine3.getPermanent1Id()));
            semaine3.setMois(mois);
        }
        if(semaine4.getPermanent1Id() == self.getId()) {
            semaine4.setPermanent2(consommateurDAO.getConsommateur(semaine4.getPermanent2Id()));
            semaine4.setMois(mois);
        } else if(semaine4.getPermanent2Id() == self.getId()) {
            semaine4.setPermanent1(consommateurDAO.getConsommateur(semaine4.getPermanent1Id()));
            semaine4.setMois(mois);
        }
        
        List<Contrat> contrats = contratDAO.getContratsByConsommateur(self);
        for(Contrat contrat : contrats) {
            contrat.setProduit(produitDAO.getProduit(contrat.getProduit_id()));
            self.addContrat(contrat.getProduit_id(), contrat);
            if(contrat.getSemaine_Debut_id() != null) {
                contrat.setDebut_semaine(semaineDAO.getSemaine(contrat.getSemaine_Debut_id()));
                Mois moisSemDebut = moisDAO.getMoisBySemaineId(contrat.getSemaine_Debut_id());
                if(contrat.getDebutSemaine() != null)
                    contrat.getDebutSemaine().setMois(moisSemDebut);
            }
        }
        
        List<Produit> produits = produitDAO.getProduits();
        ProducteurDAO producteurDAO = new ProducteurDAO(super.ds);
        for(Produit produit : produits)
            produit.setProducteur(producteurDAO.getProducteurOfProduit(produit));
       
        DisponibiliteDAO disponibiliteDAO = new DisponibiliteDAO(super.ds);
        request.setAttribute("produits", produits);
        request.setAttribute("self", self);
        request.setAttribute("disponibilites", disponibiliteDAO.getDisponibilitesByConsommateur(self));
        request.setAttribute("mois", mois);
        getServletContext().getRequestDispatcher("/WEB-INF/consommateur/consulter.jsp").forward(request, response);
    }

    public void signerContrat(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException  {
            int quantite = Integer.parseInt(request.getParameter("quantite_contrat"));
            int produitId = Integer.parseInt(request.getParameter("produit_id"));
            ContratDAO contratDAO = new ContratDAO(ds);
            contratDAO.addContrat(quantite, produitId, self);
            this.consulter(request, response);
    }

    public void renseignerDisponibilites(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException  {
        ContratDAO contratDAO = new ContratDAO(ds);
        ProduitDAO produitDAO = new ProduitDAO(ds);
        DisponibiliteDAO disponibiliteDAO = new DisponibiliteDAO(ds);
        int contrat_id = Integer.parseInt(request.getParameter("contrat_id"));
        Contrat contrat = contratDAO.getContrat(contrat_id);
        Produit produit = produitDAO.getProduit(contrat.getProduit_id());
        int duree = produit.getDuree();
        for(int i = 1; i <= duree; i++) {
            if(request.getParameter("semaine_"+i) != null && request.getParameter("semaine_"+i).equals("on"))
                disponibiliteDAO.addDisponibilite(self, contrat, i);
        }
        disponibiliteDAO.addDisponibilite(self, contrat, 0);
        this.consulter(request, response);
    }

    private void renouvelerContrat(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {
        ContratDAO contratDAO = new ContratDAO(ds);
        int contrat_id = Integer.parseInt(request.getParameter("contrat_id"));
        Contrat contrat = contratDAO.getContrat(contrat_id);
        contratDAO.addContrat(contrat.getQuantite(), contrat.getProduit_id(), self);
        this.consulter(request, response);
    }
}