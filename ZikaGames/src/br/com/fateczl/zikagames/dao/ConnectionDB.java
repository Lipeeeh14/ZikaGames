package br.com.fateczl.zikagames.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class ConnectionDB {
	
	protected final static String JDBC_CLASS = "org.mariadb.jdbc.Driver";
	protected final static String JDBC_URL = "jdbc:mariadb://localhost:3306/zikagames";
	protected final static String JDBC_USER = "root";
	
	// TODO: Alterar a senha de acordo com o banco, na fatec: "alunofatec"
	protected final static String JDBC_PASS = "123";
	
	protected Connection con;
	
	public ConnectionDB() {
		try {
			Class.forName(JDBC_CLASS);
			con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
