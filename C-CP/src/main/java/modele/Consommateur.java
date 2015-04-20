package modele;

import java.util.ArrayList;

public class Consommateur extends Utilisateur {
	private Permanence permanence;
	private ArrayList<Contrat> contrats = new ArrayList<Contrat>();

    public Consommateur(short id) {
        super(id);
    }
}