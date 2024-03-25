package com.proyectosoftware.backend.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.PoolConnectionManager;

public class BlackJackDAO {

	// Creacion de una partida nueva
	public int insert(BlackJack blackjack) throws SQLException {
		Connection conn = PoolConnectionManager.getConnection();
		PreparedStatement statemenmt;
		String id_max = "SELECT max(id) FROM Blackjack";
		statemenmt = conn.prepareStatement(id_max);
		
		ResultSet rSet = statemenmt.executeQuery();
		rSet.next();
		int id_nueva_partida = rSet.getInt(1) + 1; // Se le suma uno al ID de partida más alto

		Blackjack blackjack = new Blackjack();
		
		String sql = "INSERT INTO Blackjack(id, email, rol, nickname, password, pais) VALUES (?, ?, ?, ?, ?, ?)"; // Campos de Blackjack
		statemenmt = conn.prepareStatement(sql);

        // Campos de Blackjack
		statemenmt.setInt(1, blackjack.getId());
		statemenmt.setString(2, blackjack.getEmail());
		statemenmt.setString(3, blackjack.getRol());
		statemenmt.setString(4, blackjack.getNickname());
		statemenmt.setString(5, blackjack.getPassword());
		statemenmt.setString(6, blackjack.getPais());
		statemenmt.execute();  
		
		//asignarPuntuacion(idUser);
		
		conn.close();
		statemenmt.close();
		rSet.close();
        return 0;
    }
	/*
	// Asigna una puntuacion inicial de 0 y la ultima posicion en el ranking
	private void asignarPuntuacion(int idUser) throws SQLException {
		PuntuacionDAO puntuacionDAO = new PuntuacionDAO();
		
		Connection conn = PoolConnectionManager.getConnection();
		PreparedStatement statemenmt;
		String maxPosicion = "SELECT max(posicion) FROM public.\"Puntuacion\"";
		statemenmt = conn.prepareStatement(maxPosicion);
		
		ResultSet rSet = statemenmt.executeQuery();
		rSet.next(); 
		int maxPosicionBD = rSet.getInt(1);
		
		Puntuacion puntuacion = new Puntuacion(0, ++maxPosicionBD);
		puntuacion.setId(idUser);
		puntuacionDAO.insert(puntuacion);
		
		conn.close();
		statemenmt.close();
		rSet.close();
	}
	*/
	// Borrado de una partida de la base de datos cuando acabe
	public int delete(int id) throws SQLException {
		Connection conn = PoolConnectionManager.getConnection();
		String sql = "DELETE FROM Blackjack WHERE id = " + id;
		
		PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.execute();   
        
        conn.close();
        stmt.close();
        return 0;
	}
	
	// Actualizacion de cualquiera de los campos de una partida a excepcion del identificador
	public int update(int id, String email, String rol, String nickname, String pais, String password) throws SQLException { // Campos de Blackjack
		Connection conn = PoolConnectionManager.getConnection();
		String sql = "UPDATE public.\"UsuarioConCuenta\" SET email=?, rol=?, nickname=?, password=?, pais=? WHERE email = ?";
		PreparedStatement stm = conn.prepareStatement(sql);
        
		stm.setString(1, email);
        stm.setString(2, rol);
        stm.setString(3, nickname);
        stm.setString(4, password);
        stm.setString(5, pais);      
        stm.setString(6, email);
        stm.execute();
        
        conn.close();
        stm.close();
            
        return 0;
	}
	/*
	// Comprueba que el usuario dado esta registrado en la tabla UsuarioConCuenta
	public boolean validateUser(String email, String passwd) throws Exception { 
		boolean result = false;
		
		// Abrimos la conexión e inicializamos los parámetros 
		Connection conn = PoolConnectionManager.getConnection();
		String sql = "SELECT password FROM public.\"UsuarioConCuenta\" WHERE email = ?";
		
		PreparedStatement stm = conn.prepareStatement(sql);	
		stm.setInt(1, id);
		
		ResultSet rSet = stm.executeQuery();
		rSet.next();
		String password = rSet.getString(1); 
		if(password.contentEquals(passwd)) {
			result = true;
		}
		
		conn.close();
		stm.close();
		rSet.close();
		
		return result;
	}
	*/
	// Recupera una partida identificada por un id al reanudarla
	public Blackjack getPartida(int id) throws Exception {
		// Abrimos la conexión e inicializamos los parámetros 
		Connection conn = PoolConnectionManager.getConnection();
		String sql = "SELECT * FROM Blackjack WHERE id = " + id;
		
		PreparedStatement stm = conn.prepareStatement(sql);	
		stm.setInt(1, id);
		
		ResultSet rSet = stm.executeQuery();
		while (rSet.next()) {    // Campos de Blackjack
			//id = rSet.getInt(1);
			rol = rSet.getString(3);
			nickname = rSet.getString(4);
			passwd = rSet.getString(5);
			pais = rSet.getString(6);
		}
		conn.close();
		stm.close();
		rSet.close();
		
		Blackjack partida = new Blackjack(); // Campos de Blackjack
		partida.setId(id);
		
		return partida;
	}
}
