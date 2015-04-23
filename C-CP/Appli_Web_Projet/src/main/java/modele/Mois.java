package modele;

import java.util.ArrayList;

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
            return this.semaines;
        }
    
    private enum MoisEnum {
        JANVIER, FEVRIER, MARS, AVRIL, MAI, JUIN, JUILLET, AOUT, SEPTEMBRE, OCTOBRE, NOVEMBRE, DECEMBRE;
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