package modele;

public class Contrat {
    
    private final int id;
    private final int quantite;
    private int valide = 2;
    private Semaine debut_semaine = null;
    private final Consommateur consommateur;
    private final Produit produit;

    public Contrat(int id, int quantite, int valide, Consommateur consommateur, Produit produit, Semaine semaine) {
        this.id = id;
        this.quantite = quantite;
        this.valide = valide;
        this.consommateur = consommateur;
        this.produit = produit;
        this.debut_semaine = semaine;
        consommateur.addContrat(produit.getId(), this);
    }

    public Contrat(int id, int quantite, Produit produit, Consommateur consommateur) {
        this.id = id;
        this.quantite = quantite;
        this.consommateur = consommateur;
        this.produit = produit;
        consommateur.addContrat(produit.getId(), this);
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