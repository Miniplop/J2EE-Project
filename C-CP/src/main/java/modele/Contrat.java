package modele;

public class Contrat {
    
    private final int id;
    private final int quantite;
    private boolean valide = false;
    private Semaine debut_semaine;
    private final Consommateur consommateur;
    private final Produit produit;

    public Contrat(int id, int quantite, boolean valide, Consommateur consommateur, Produit produit, Semaine semaine) {
        this.id = id;
        this.quantite = quantite;
        this.valide = valide;
        this.consommateur = consommateur;
        this.produit = produit;
        this.debut_semaine = semaine;
        consommateur.addContrat(this);
    }

    public int getId() {
            return this.id;
    }

    public int getQuantite() {
            return this.quantite;
    }

    public void setValide(boolean valide) {
            this.valide = valide;
    }

    public boolean isValide() {
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