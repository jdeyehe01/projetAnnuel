package sample.com.projetAnnuel.model;
import java.util.ArrayList;
import java.util.Date;

import sample.com.projetAnnuel.annotation.PropertyLabel;

public class Evenement {

	ArrayList<Budget> sesBudgets;
	ArrayList<Utilisateur> sesParticipants;
	ArrayList<Image> sesPhotos;

	@PropertyLabel(value = "Nom")
	private String nom;

	@PropertyLabel(value = "Date de debut")
	private Date dateDebut;

	@PropertyLabel(value = "Adresse")
	private String adresse;

	@PropertyLabel(value = "Code Postal")
	private int codePostal;

	@PropertyLabel(value = "Ville")
	private String ville;

	@PropertyLabel(value = "Heure de debut")
	private String heure;

	@PropertyLabel(value = "Description")
	private String description;

	public String getDescription() {
		return this.description;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public String getHeure() {
		return this.heure;
	}

	/**
	 * 
	 * @param heure
	 */
	public void setHeure(String heure) {
		this.heure = heure;
	}

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
	 * @param codePostal
	 */
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public String getAdresse() {
		return this.adresse;
	}

	/**
	 * 
	 * @param adresse
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Date getDateDebut() {
		return this.dateDebut;
	}

	/**
	 * 
	 * @param dateDebut
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
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

    public Evenement(String nom, Date dateDebut, String adresse, int codePostal, String ville, String heure, String description) {
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.heure = heure;
        this.description = description;
    }

    /**
	 * 
	 * @param nom
	 * @param dateDebut
	 * @param adresse
	 * @param codePostal
	 * @param ville
	 * @param heure
	 * @param description
	 */

    public Evenement(ArrayList<Budget> sesBudgets, ArrayList<Utilisateur> sesParticipants, ArrayList<Image> sesPhotos, String nom, Date dateDebut, String adresse, int codePostal, String ville, String heure, String description) {

        this.sesBudgets = sesBudgets;
        this.sesParticipants = sesParticipants;
        this.sesPhotos = sesPhotos;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.heure = heure;
        this.description = description;
    }
}