package com.proyectosoftware.backend.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**  * Clase que abstrae la conexion con la base de datos.  */

public class ConnectionUtil {
	// JDBC nombre del driver y URL de BD 
	private static final String JDBC_DRIVER = "org.postgresql.Driver";  
	private static String DB_URL;
	
	// Credenciales de la Base de Datos
	private static String USER;
	private static String PASSWD;
    
    static {
        // Cargar las propiedades del archivo properties
        try {
            Properties props = new Properties();
            FileInputStream fis = new FileInputStream("application.properties");
            props.load(fis);
            fis.close();
            
            DB_URL = props.getProperty("spring.datasource.url");
            USER = props.getProperty("spring.datasource.username");
            PASSWD = props.getProperty("spring.datasource.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
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

