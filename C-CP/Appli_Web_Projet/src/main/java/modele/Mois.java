package modele;

import java.util.ArrayList;
import java.util.Collections;

public class Mois implements Comparable {
	private final String annee;
	private final String nom;
	private final ArrayList<Semaine> semaines = new ArrayList<>();
        private int semaine_1_id;

    public int getSemaine_1_id() {
        return semaine_1_id;
    }

    public int getSemaine_2_id() {
        return semaine_2_id;
    }

    public int getSemaine_3_id() {
        return semaine_3_id;
    }

    public int getSemaine_4_id() {
        return semaine_4_id;
    }
        private int semaine_2_id;
        private int semaine_3_id;
        private int semaine_4_id;

	public Mois(String annee, String nom) {
		this.annee = annee;
                this.nom = nom;
	}

    public Mois(String annee, String nom, int sem1_id, int sem2_id, int sem3_id, int sem4_id) {
        this.annee = annee;
        this.nom = nom;
        this.semaine_1_id = sem1_id;
        this.semaine_2_id = sem2_id;
        this.semaine_3_id = sem3_id;
        this.semaine_4_id = sem4_id;
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