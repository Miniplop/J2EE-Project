package modele;

public class Produit {

    private final int id;
    private String nom;
    private String unite;
    private int quantite;
    private int duree;
    private Producteur producteur;

    public void setProducteur(Producteur producteur) {
        producteur.addProduits(this);
        this.producteur = producteur;
    }

    public Produit(int id, String nom, String unite, int quantite, int duree) {
        this.id = id;
        this.nom = nom;
        this.unite = unite;
        this.quantite = quantite;
        this.duree = duree;
    }

    public int getId() {
            return this.id;
    }

    public void setNom(String nom) {
            this.nom = nom;
    }

    public String getNom() {
            return this.nom;
    }

    public void setUnite(String unite) {
            this.unite = unite;
    }

    public String getUnite() {
            return this.unite;
    }

    public void setQuantite(int quantite) {
            this.quantite = quantite;
    }

    public int getQuantite() {
            return this.quantite;
    }

    public void setDuree(int duree) {
            this.duree = duree;
    }

    public int getDuree() {
            return this.duree;
    }

    public Producteur getProducteur() {
        return producteur;
    }
}