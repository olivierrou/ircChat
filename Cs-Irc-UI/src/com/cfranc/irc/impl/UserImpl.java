package com.cfranc.irc.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.sqlite.JDBC;

import com.cfranc.irc.server.User;

public class UserImpl extends User {

	private int id = 0;
	final static String filePath = "db/ircdb.sqlite";
	
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
	}

	/**
	 * @param login
	 * @param pwd
	 * @param nom
	 * @param prenom
	 */
	public UserImpl(String login, String pwd, String nom, String prenom) {
		super(login, pwd, nom, prenom);
	}

	/**
	 * @param login
	 * @param pwd
	 */
	public UserImpl(String login, String pwd) {
		super(login, pwd);
	}

	public UserImpl(String login) {
		super(login);
	}
	
	private Connection retournerConnection() {
		
		Connection connection = null;
		
    	String s = "jdbc:sqlite:" + filePath;
    	boolean ok = false;
    	
		if (JDBC.isValidURL(s)) {
		    try {
		    	connection = DriverManager.getConnection(s);
		    	System.out.println(connection.toString());
		    	ok = true;
		    } catch (SQLException e) {
		    	e.printStackTrace();
		    }
		} else {
			JOptionPane.showMessageDialog(null, "Impossible de se connecter � la BDD.\nURL invalide.", "Inane error", JOptionPane.ERROR_MESSAGE);
		}
		
		if (ok) {
			return connection;
		} else {
			return null;
		}
			
	}
	public int enregistrer() {

		String sql;

		Connection c = retournerConnection();
		
		if (id == 0) {
			
			Statement statement;
			try {
				statement = c.createStatement();
				// Un peu lourdingue et propice � des bugs mais c'est une
				// alternative au PreparedStatement qui bloquait
				sql = "INSERT INTO User (Nom, Prenom, Login, MotPasse, ImageURI) VALUES ('"
						+ this.getNom()
						+ "','"
						+ this.getPrenom()
						+ "','"
						+ this.getLogin()
						+ "','"
						+ this.getPwd()
						+ "','"
						+ this.getImageURI() + "')";
				System.out.println(sql);
				statement.executeUpdate(sql);
				statement.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			sql = "UPDATE User SET Nom=?, Prenom=?, Login=?, MotPasse=?, ImageURI=? WHERE IdUser="
					+ id;

			try {
				PreparedStatement stt = c.prepareStatement(sql); // ,

				System.out.println(sql); // Statement.RETURN_GENERATED_KEYS);

				stt.setString(1, this.getNom());
				stt.setString(2, this.getPrenom());
				stt.setString(3, this.getLogin());
				stt.setString(4, this.getPwd());
				stt.setString(5, this.getImageURI());

				stt.executeUpdate();
				stt.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 
	 * @return false si le login n'existe pas dans la base true si le login
	 *         existe
	 */
	public boolean estInscrit() {

		boolean retour = false;
		Statement statement;
		Connection c = retournerConnection();
		
		try {
			statement = c.createStatement();
			statement.setQueryTimeout(30);
			String sql = "select count(*) as Nb from User where Login='"
					+ this.getLogin() + "'";
			ResultSet rs = statement.executeQuery(sql);
			retour = (rs != null && rs.getInt("Nb") > 0);
			statement.close();
			c.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retour;
	}

	/**
	 * 
	 * @return boolean true si le mot de passe est ok false si le mot de passe
	 *         est inccorect
	 * 
	 */
	public boolean estMotPasseCorrect() {

		boolean retour = false;
		Statement statement;
		Connection c = retournerConnection();
		
		try {
			statement = c.createStatement();
			statement.setQueryTimeout(30);
			String sql = "select count(*) as Nb from User where Login='"
					+ this.getLogin() + "' and MotPasse='" + this.getPwd()
					+ "'";
			ResultSet rs = statement.executeQuery(sql);
			retour = (rs != null && rs.getInt("Nb") > 0);
			statement.close();
			c.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retour;
	}

	public boolean charger(String login, String pwd) {

		boolean retour = false;
		Statement statement;
		Connection c = retournerConnection();
		try {
			statement = c.createStatement();
			statement.setQueryTimeout(30);
			String sql = "select * from User where Login='" + login
					+ "' and MotPasse='" + pwd + "'";
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);
			
			retour = (rs != null);

			
			
			if (retour) {
				System.out.println(rs.getString("ImageURI"));

				this.setImageURI(rs.getString("ImageURI"));
				this.setLogin(rs.getString("Login"));
				this.setNom(rs.getString("Nom"));
				this.setPrenom(rs.getString("Prenom"));
				this.setPwd(rs.getString("MotPasse"));
			}
			
			statement.close();
			c.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retour;
	}

	
	public boolean charger(String login) {

		boolean retour = false;
		Statement statement;
		Connection c = retournerConnection();
		try {
			statement = c.createStatement();
			statement.setQueryTimeout(30);
			String sql = "select * from User where Login='" + login + "'";
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);

			retour = (rs != null);

			System.out.println(retour);
			if (retour) {
				System.out.println(rs.getString("ImageURI"));

				this.setImageURI(rs.getString("ImageURI"));
				this.setLogin(rs.getString("Login"));
				this.setNom(rs.getString("Nom"));
				this.setPrenom(rs.getString("Prenom"));
				this.setPwd(rs.getString("MotPasse"));
			}
			statement.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retour;
	}
	
//	
//	public static void main(String[] args) {
//
//		System.out.println("je rntre dans le main du singleton");
//		DbSingleton.getInstance().connectSqlLite("db/ircdb.sqlite");
//
//		UserImpl u = new UserImpl("login444", "motpasse", "prenom444", "motpasse");
//		u.enregistrer();
//
//		UserImpl u2 = new UserImpl("login", "nom", "prenom", "motpasse");
//		System.out.println("ici" + u2.estInscrit());
//
//		UserImpl u3 = new UserImpl("login", "nom", "prenom", "modtpasse");
//		System.out.println("mot ed passe" + u2.estMotPasseCorrect());
//
//		UserImpl u4 = new UserImpl();
//		u4.charger("login444", "motpasse");
//
//		System.out.println("u4 " + u4.getPrenom());
//	}
}
