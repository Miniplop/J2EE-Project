package modele;

import java.util.ArrayList;

public class Mois {
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
            return this.semaines;
        }
}