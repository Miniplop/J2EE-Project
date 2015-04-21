package modele;

public abstract class Utilisateur {
	private final short id;
	private String nom;
	private String prenom;
	private String email;
	private String adresse;

	public Utilisateur(short id, String nom, String prenom, String email, String adresse) {
            this.id = id;
            this.nom = nom;
            this.prenom = prenom;
            this.email = email;
            this.adresse = adresse;
	}

	public short getId() {
		return this.id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return this.nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getAdresse() {
		return this.adresse;
	}
}