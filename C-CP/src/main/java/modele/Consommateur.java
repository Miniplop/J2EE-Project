package modele;

import java.util.ArrayList;
import java.util.List;

public class Consommateur extends Utilisateur {
    private final Permanence permanence;
    private final List<Contrat> contrats;

        
    public Consommateur(short id, String nom, String prenom, String email, String adresse, List<Contrat> contrats, Permanence permanence) {
        super(id, nom, prenom, email, adresse);
        if(contrats == null)
            this.contrats = new ArrayList<Contrat>();
        else
            this.contrats = contrats;
        this.permanence = permanence;
    }
}