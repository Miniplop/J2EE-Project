package modele;

import java.util.ArrayList;
import java.util.List;

public class Producteur extends Utilisateur {
    
    private final List<Produit> produits;
        
    public Producteur(short id, String nom, String prenom, String email, String adresse) {
        super(id, nom, prenom, email, adresse);
        produits = new ArrayList<Produit>();
    }

    public List<Produit> getProduits() {
        return produits;
    }
    
    public void addProduits(Produit produit) {
        this.produits.add(produit);
    }
}