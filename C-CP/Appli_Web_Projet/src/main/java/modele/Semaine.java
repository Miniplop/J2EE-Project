package modele;

public class Semaine {
	private final short id;
	private int numero;
        private Consommateur permanent1 ;
        private Consommateur permanent2 ;
        private Mois mois; 

    public void setMois(Mois mois) {
        this.mois.addSemaine(this);
        this.mois = mois;
    }
    public Semaine(short id, int numero) {
            this.numero = numero;
            this.id =id;
    }

	public short getId() {
		return this.id;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getNumero() {
		return this.numero;
	}

	public Mois getMois() {
		return this.mois;
	}

	public Consommateur getPermanent1() {
		return this.permanent1;
	}

	public Consommateur getPermanent2() {
		return this.permanent2;
	}

    public void setPermanent1(Consommateur permanent1) {
        this.permanent1 = permanent1;
    }

    public void setPermanent2(Consommateur permanent2) {
        this.permanent2 = permanent2;
    }

}