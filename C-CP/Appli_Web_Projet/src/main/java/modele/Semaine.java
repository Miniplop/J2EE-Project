package modele;

public class Semaine {
	private final short id;
	private int numero;
        private Consommateur permanent1 ;
        private Consommateur permanent2 ;
        private Mois mois; 
        
	public Semaine(short id, int numero, Consommateur permanent1, Consommateur permanent2, Mois mois) {
            this.numero = numero;
            this.id =id;
            this.permanent1 = permanent1;
            this.permanent2 = permanent2;
            if (mois != null){
                this.mois = mois;
                this.mois.addSemaine(this);
            }
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

}