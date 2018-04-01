package sample.com.projetAnnuel.model;

import java.util.ArrayList;

import sample.com.projetAnnuel.annotation.PropertyLabel;

public class Utilisateur extends Personne {

	private ArrayList<Evenement> sesEvenements;

	@PropertyLabel(value = "Login")
	private String login;

	@PropertyLabel(value = "Mot de passe")
	private String password;
	private boolean isConnect;

	public Utilisateur(String nom, String prenom, String age, String email, String adressePostal, int codePostal, String ville, ArrayList<Evenement> sesEvenements, String login, String password, boolean isConnect) {
		super(nom, prenom, age, email, adressePostal, codePostal, ville);
		this.sesEvenements = sesEvenements;
		this.login = login;
		this.password = password;
		this.isConnect = isConnect;
	}


	public boolean getIsConnect() {
		return this.isConnect;
	}

	/**
	 * 
	 * @param isConnect
	 */
	public void setIsConnect(boolean isConnect) {
		this.isConnect = isConnect;
	}

	public String getPassword() {
		return this.password;
	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin() {
		return this.login;
	}

}