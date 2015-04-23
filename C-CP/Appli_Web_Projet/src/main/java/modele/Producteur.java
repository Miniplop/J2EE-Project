package modele;

import java.util.HashSet;
import java.util.Set;

public class Producteur extends Utilisateur {
    
    private final Set<Produit> produits;
        
    public Producteur(short id, String nom, String prenom, String email, String adresse) {
        super(id, nom, prenom, email, adresse);
        produits = new HashSet<>();
    }

    public Producteur(short id) {
        super(id);
        produits = new HashSet<>();
    }

    public Set<Produit> getProduits() {
        return produits;
    }
    
    public void addProduits(Produit produit) {
        this.produits.add(produit);
    }
}