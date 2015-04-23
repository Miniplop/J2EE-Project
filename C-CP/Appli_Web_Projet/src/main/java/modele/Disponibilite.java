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
    private final Consommateur consommateur;
    private final int numero;
    private final Contrat contrat;

    public Disponibilite(int id, Consommateur consommateur, Contrat contrat, int numero) {
        this.id = id;
        this.consommateur = consommateur;
        this.contrat = contrat;
        this.numero = numero;
    }

    public int getId() {
        return id;
    }

    public Consommateur getConsommateur() {
        return consommateur;
    }

    public Contrat getContrat() {
        return contrat;
    }
}
