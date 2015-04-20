package modele;

import java.util.ArrayList;

public class Producteur extends Utilisateur {
	private ArrayList<Produit> produits = new ArrayList<Produit>();

    public Producteur(short id) {
        super(id);
    }
}