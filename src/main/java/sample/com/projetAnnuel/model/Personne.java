package sample.com.projetAnnuel.model;

import sample.com.projetAnnuel.annotation.PropertyLabel;

public class Personne {

	@PropertyLabel(value = "Nom")
	private String nom;

	@PropertyLabel(value = "Prenom")
	private String prenom;

	@PropertyLabel(value = "Age")
	private String age;

	@PropertyLabel(value = "Email")
	private String email;

	@PropertyLabel(value = "Adresse complete")
	private String adressePostal;

	@PropertyLabel(value = "Code postal de la ville")
	private int codePostal;

//	@PropertyLabel(value = "Nom de la ville ")
	private String ville;

	public String getVille() {
		return this.ville;
	}

	/**
	 * 
	 * @param ville
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

	public int getCodePostal() {
		return this.codePostal;
	}

	/**
	 * 
	 * @param codePostale
	 */
	public void setCodePostale(int codePostale) {
		throw new UnsupportedOperationException();
	}

	public String getEmail() {
		return this.email;
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdressePostale() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param adressePostale
	 */
	public void setAdressePostale(String adressePostale) {
		throw new UnsupportedOperationException();
	}

	public String getAge() {
		return this.age;
	}

	/**
	 * 
	 * @param age
	 */
	public void setAge(String age) {
		this.age = age;
	}

	public String getPrenom() {
		return this.prenom;
	}

	/**
	 * 
	 * @param prenom
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return this.nom;
	}

	/**
	 * 
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 *
	 */
	public Personne(String nom, String prenom, String age, String email, String adressePostal, int codePostal, String ville) {
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.email = email;
		this.adressePostal = adressePostal;
		this.codePostal = codePostal;
		this.ville = ville;
	}
}