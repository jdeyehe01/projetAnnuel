package sample.com.projetAnnuel.model;

import sample.com.projetAnnuel.annotation.PropertyLabel;

public class Budget {

	private Evenement sonEvenement;
	@PropertyLabel(value = "Nom")
	private String nom;

	@PropertyLabel(value = "Montant")
	private Float montant;

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

	public Float getMontant() {
		return this.montant;
	}

	/**
	 * 
	 * @param montant
	 */
	public void setMontant(Float montant) {
		this.montant = montant;
	}

	/**
	 * 
	 * @param nom
	 * @param montant
	 */
    public Budget(Evenement sonEvenement, String nom, Float montant) {
        this.sonEvenement = sonEvenement;
        this.nom = nom;
        this.montant = montant;
    }
}