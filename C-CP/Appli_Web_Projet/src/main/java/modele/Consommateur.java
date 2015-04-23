package modele;

import java.util.*;

public class Consommateur extends Utilisateur {
    private Permanence permanence;
    private final Map<Integer, List<Contrat>> contrats;

        
    public Consommateur(short id, String nom, String prenom, String email, String adresse, Permanence permanence) {
        super(id, nom, prenom, email, adresse);
        this.contrats = new HashMap<>();
        this.permanence = permanence;
    }

    public Consommateur(short id) {
        super(id);
        this.contrats = new HashMap<>();
    }

    public void addContrat(Integer produitId, Contrat contrat) {
        if(this.contrats.get(produitId) == null)
            this.contrats.put(produitId, new ArrayList<Contrat>());
        this.contrats.get(produitId).add(contrat);
    }

    public Permanence getPermanence() {
        return permanence;
    }

    public void setPermanence(Permanence permanence) {
        this.permanence = permanence;
    }

    public Map<Integer, List<Contrat>> getContrats() {
        return contrats;
    }
}
