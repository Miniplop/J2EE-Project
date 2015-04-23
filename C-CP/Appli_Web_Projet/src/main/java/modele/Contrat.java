package modele;

public class Contrat {
    
    private final int id;
    private final int quantite;
    private int valide = 2;
    private Semaine debut_semaine = null;
    private Consommateur consommateur;
    private Integer semaine_debut_id;
    private Produit produit;
    private int consommateur_id;
    private int produit_id;

    public void setConsommateur(Consommateur consommateur) {
        this.consommateur = consommateur;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getConsommateur_id() {
        return consommateur_id;
    }

    public Integer getSemaine_Debut_id() {
        return semaine_debut_id;
    }
    public int getProduit_id() {
        return produit_id;
    }

    public Contrat(int id, int quantite, int valide, Produit produit, int consommateur_id, Integer semaine_debut_id) {
        this.id = id;
        this.quantite = quantite;
        this.valide = valide;
        this.produit = produit;
        this.consommateur_id = consommateur_id;
        this.semaine_debut_id = semaine_debut_id;
    }

    public Contrat(int id, int quantite, int valide, int consommateur_id, int produit_id, Integer semaine_debut_id) {
        this.id = id;
        this.quantite = quantite;
        this.valide = valide;
        this.produit_id = produit_id;
        this.semaine_debut_id = semaine_debut_id;
    }

    public Contrat(int id, int quantite, int valide,Consommateur consommateur, int produit_id, Integer semaine_debut_id) {
        this.id = id;
        this.quantite = quantite;
        this.valide = valide;
        this.consommateur = consommateur;
        this.produit_id = produit_id;
        this.semaine_debut_id = semaine_debut_id;
    }

    public Contrat(int id, int quantite, Consommateur consommateur, int produit_id, Integer semaine_debut_id) {
        this.id = id;
        this.quantite = quantite;
        this.consommateur = consommateur;
        this.produit_id = produit_id;
    }

    public Contrat(int id, int quantite, Consommateur consommateur, Produit produit, Integer semaine_debut_id) {
        this.id = id;
        this.quantite = quantite;
        this.consommateur = consommateur;
        this.produit = produit;
        this.semaine_debut_id = semaine_debut_id;
    }

    public int getId() {
            return this.id;
    }

    public int getQuantite() {
            return this.quantite;
    }

    public void setValide(int valide) {
            this.valide = valide;
    }

    public int getValide() {
            return this.valide;
    }

    public Semaine getDebutSemaine() {
        return debut_semaine;
    }

    public void setDebut_semaine(Semaine debut_semaine) {
        this.debut_semaine = debut_semaine;
    }

    public Consommateur getConsommateur() {
        return consommateur;
    }

    public Produit getProduit() {
        return produit;
    }
}