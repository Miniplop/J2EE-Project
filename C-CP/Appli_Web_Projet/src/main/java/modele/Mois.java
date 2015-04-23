package modele;

import java.util.ArrayList;
import java.util.Collections;

public class Mois implements Comparable {
	private final String annee;
	private final String nom;
	private final ArrayList<Semaine> semaines = new ArrayList<>();

	public Mois(String annee, String nom) {
		this.annee = annee;
                this.nom = nom;
	}
        
        public void addSemaine(Semaine semaine){
            this.semaines.add(semaine);
        }

	public String getAnnee() {
		return this.annee;
	}

	public String getNom() {
		return this.nom;
	}
        
        public ArrayList<Semaine> getSemaines(){
            Collections.sort(semaines);
            return this.semaines;
        }
    
    private enum MoisEnum {
        Janvier, Février, Mars, Avril, Mai, Juin, Juillet, Aout, Septembre, Octobre, Novembre, Décembre;
    }

    @Override
    public int compareTo(Object t) {
        Mois m = (Mois) t;
        if (Integer.parseInt(this.annee) != Integer.parseInt(m.annee)) {
            return Integer.parseInt(this.annee) - Integer.parseInt(m.annee);
        } else {
            MoisEnum thisme = MoisEnum.valueOf(this.getNom());
            MoisEnum tme = MoisEnum.valueOf(m.getNom());
            return thisme.ordinal() - tme.ordinal();
        }
    }
}