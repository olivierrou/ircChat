package com.cfranc.irc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cfranc.irc.server.User;

public class UserImpl extends User {

	private int id =  0;
	
	public UserImpl() {
		super();
	}

	/**
	 * @param login
	 * @param pwd
	 * @param nom
	 * @param prenom
	 * @param imageURI
	 */
	public UserImpl(String login, String pwd, String nom, String prenom,
			String imageURI) {
		super(login, pwd, nom, prenom, imageURI);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param login
	 * @param pwd
	 * @param nom
	 * @param prenom
	 */
	public UserImpl(String login, String pwd, String nom, String prenom) {
		super(login, pwd, nom, prenom);
		// TODO Auto-generated constructor stub
	}

	public int enregistrer() {
		
		String sql;
		if (id == 0) {
		    sql = "INSERT INTO User (Nom, Prenom, Login, MotPasse, ImageURI)  VALUES (?,?,?,?,?)";
		} else {
		    sql = "UPDATE User SET Nom=?, Prenom=?, Login=?, MotPasse=?, ImageURI=? WHERE IdUser="  + id;
		}

		try {
		    PreparedStatement stt = DbSingleton.getInstance().getConnection().prepareStatement(sql); //,  Statement.RETURN_GENERATED_KEYS);
		    
		    
		    
		    
		    stt.setString(1, this.getNom());
		    stt.setString(2, this.getPrenom());
		    stt.setString(3, this.getLogin());
		    stt.setString(4, this.getPwd());
		    stt.setString(5, this.getImageURI());
		    
		    stt.executeUpdate();

		    ResultSet rs = stt.getGeneratedKeys();
		    if (rs.next()){
		        int i =rs.getInt(1);
		    }
		    
		    
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		
		return 0;
	}
	
	public boolean estInscrit() {
		
		boolean retour = false;
		
		
		return retour;
	}
	
	public boolean charger(String login, String pwd) {
		
		return true;
	}
	
	public static void main(String[] args) {
		
		
		UserImpl u = new UserImpl("login", "nom", "prenom", "motpasse");
		
		u.enregistrer();
	}
}
