package com.cfranc.irc.server;

/**
 * <code>User</code> relative aux données associées à un utilisateur.
 * 
 * @beaninfo
 * description: Données d'un utilisateur
 *
 * @author Bertrand Wittmer
 */
public class User {

	private String nom;
	private String prenom;
	private String login;
	private String pwd;
	private String imageURI;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String toString() {
		return nom + " " + prenom;
	}

	public String getImageURI() {
		return imageURI;
	}
	public void setImageURI(String imageURI) {
		this.imageURI = imageURI;
	}
	
	public User(String login, String pwd, String nom, String prenom, String imageURI) {
		super();
		this.login = login;
		this.pwd = pwd;
		this.nom = nom;
		this.prenom = prenom;
		this.imageURI = imageURI;
	}	
	
	
	public User(String login, String pwd, String nom, String prenom) {
		super();
		this.login = login;
		this.pwd = pwd;
		this.nom = nom;
		this.prenom = prenom;
	}	
	
	public User() {
		super();
		this.login = "";
		this.pwd = "";
		this.nom = "";
		this.prenom = "";
		this.imageURI = "";
	}
}
