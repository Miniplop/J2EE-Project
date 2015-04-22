package modele;

public class Semaine {
	private final short id;
	private int numero;
        private int consommateur_1_id ;
        private int consommateur_2_id ;
        private Mois mois; 
        
	public Semaine(short id, int numero, int c1, int c2, Mois mois) {
            this.mois = mois;
            this.numero = numero;
            this.id =id;
            this.consommateur_1_id = c1;
            this.consommateur_2_id = c2;
            mois.addSemaines(this);
            
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
}