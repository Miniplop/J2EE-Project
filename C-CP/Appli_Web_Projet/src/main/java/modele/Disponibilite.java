/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author loiseln
 */
public class Disponibilite {
    
    private final int id;
    private Consommateur consommateur;
    private Contrat contrat;
    private int contrat_id;
    private int consommateur_id;

    public Disponibilite(int id, Contrat contrat, int consommateur_id) {
        this.id = id;
        this.contrat = contrat;
        this.consommateur_id = consommateur_id;
    }

    public Disponibilite(int id, Consommateur consommateur, int contrat_id) {
        this.id = id;
        this.consommateur = consommateur;
        this.contrat_id = contrat_id;
    }

    public Disponibilite(int id) {
        this.id = id;
    }

    public int getContrat_id() {
        return contrat_id;
    }

    public int getConsommateur_id() {
        return consommateur_id;
    }


    public int getId() {
        return id;
    }

    public Consommateur getConsommateur() {
        return consommateur;
    }
    
    public void setConsommateur(Consommateur consommateur) {
        this.consommateur = consommateur;
    }

    public Contrat getContrat() {
        return contrat;
    }
    
    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }
}
