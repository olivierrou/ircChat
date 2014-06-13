package com.cfranc.irc.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

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

    public void connectSqlLite(String filePath) {

    	String s = "jdbc:sqlite:" + filePath;

		if (JDBC.isValidURL(s)) {
		    try {
		    	connection = DriverManager.getConnection(s);
		    	System.out.println(connection.toString());
		    } catch (SQLException e) {
		    	e.printStackTrace();
		    }
		}
    }
}
