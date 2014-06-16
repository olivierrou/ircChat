package com.cfranc.irc.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.sqlite.JDBC;


public class DbSingleton {

    private static DbSingleton _instance = null;
    private Connection connection = null;

    public static DbSingleton getInstance() {
		if (_instance == null)
		    _instance = new DbSingleton();
		return _instance;
    }

    public Connection getConnection() {
    	return connection;
    }

    /**
     * <code>connectSqlLite</code> connection à une base SQLite.
     *
     * @param filePath filePath de la database de la forme : <em>subname</em>
     * @return true si OK
     * @exception SQLException if a database access error occurs
     */
    public boolean connectSqlLite(String filePath) {

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
			JOptionPane.showMessageDialog(null, "Impossible de se connecter à la BDD.\nURL invalide.", "Inane error", JOptionPane.ERROR_MESSAGE);
		}
		return ok;
    }
}
