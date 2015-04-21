package modele;

import java.util.ArrayList;
import java.util.List;

public class Consommateur extends Utilisateur {
    private Permanence permanence;
    private final List<Contrat> contrats;

        
    public Consommateur(short id, String nom, String prenom, String email, String adresse, Permanence permanence) {
        super(id, nom, prenom, email, adresse);
        this.contrats = new ArrayList<Contrat>();
        this.permanence = permanence;
    }

    void addContrat(Contrat contrat) {
        this.contrats.add(contrat);
    }

    public Permanence getPermanence() {
        return permanence;
    }

    public void setPermanence(Permanence permanence) {
        this.permanence = permanence;
    }

    public List<Contrat> getContrats() {
        return contrats;
    }
}
