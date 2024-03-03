package com.proyectosoftware.backend.util;

import java.sql.*;

/**  * Clase que abstrae la conexion con la base de datos.  */

public class ConnectionUtil {
	// JDBC nombre del driver y URL de BD 
	private static final String JDBC_DRIVER = "org.postgresql.Driver";  
	private static final String DB_URL = ;
	
	// Credenciales de la Base de Datos
	private static final String USER = ;
	private static final String PASSWD = ;
	
	// Devuelve una nueva conexion.
	public final static Connection getConnection() throws SQLException {
		Connection conn = null;

		try{
			//STEP 1: Register JDBC driver
			Class.forName(JDBC_DRIVER);
			//STEP 2: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASSWD);
			return conn; 
		} catch (Exception e) {
			e.printStackTrace();
			return null; 
		} 
	}
	
	// Libera la conexion, devolviendola al pool 
	public final static void releaseConnection(Connection conn) throws SQLException {
		conn.close(); 
	}
}

