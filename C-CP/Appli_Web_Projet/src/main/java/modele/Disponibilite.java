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
    private final Semaine semaine;

    public Disponibilite(int id, Consommateur consommateur, Semaine semaine) {
        this.id = id;
        this.consommateur = consommateur;
        this.semaine = semaine;
    }

    public int getId() {
        return id;
    }

    public Consommateur getConsommateur() {
        return consommateur;
    }

    public Semaine getSemaine() {
        return semaine;
    }
}
