package modele;

public class Contrat {
	private final int id;
	private int quantite;
	private boolean valide;
	private Semaine debut_semaine;
	private Consommateur consommateur;
	private Produit produit;

	public Contrat(int id) {
		throw new UnsupportedOperationException();
	}

	public int getId() {
		return this.id;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public int getQuantite() {
		return this.quantite;
	}

	public void setValide(boolean valide) {
		this.valide = valide;
	}

	public boolean isValide() {
		return this.valide;
	}
}