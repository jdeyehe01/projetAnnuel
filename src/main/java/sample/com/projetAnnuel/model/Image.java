package sample.com.projetAnnuel.model;

import sample.com.projetAnnuel.annotation.PropertyLabel;

import java.util.Date;

public class Image {

	private Evenement sonEvenement;

	@PropertyLabel(value = "Nom")
	private String nom;

	@PropertyLabel(value = "Choisir votre image")
	private String url;

	private Date date;

	public Date getDate() {
		return this.date;
	}

	/**
	 * 
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

    public Image(Evenement sonEvenement, String nom, String url, Date date) {
        this.sonEvenement = sonEvenement;
        this.nom = nom;
        this.url = url;
        this.date = date;
    }

    public String getUrl() {
		return this.url;
	}

	/**
	 * 
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
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
	 * @param nom
	 * @param url
	 * @param date
	 */


}