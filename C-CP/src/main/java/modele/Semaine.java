package java.modele;

public class Semaine {
	private final short id;
	private int numero;
	private Mois mois;
	private Permanence permanence;

	public Semaine(short id) {
		throw new UnsupportedOperationException();
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