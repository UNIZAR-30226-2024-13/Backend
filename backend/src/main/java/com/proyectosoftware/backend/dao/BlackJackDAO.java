package com.proyectosoftware.backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.PoolConnectionManager;
import com.proyectosoftware.backend.modelo.juegos.BlackJack;
public class BlackJackDAO {

	/**
	 * Creación de una partida de blackjack
	 * @param blackjack
	 */
	public int insert(Estado estado) throws SQLException {
		Connection conn = PoolConnectionManager.getConnection();
		PreparedStatement statemenmt;
		String id_max = "SELECT max(id) FROM BlackJack";
		statemenmt = conn.prepareStatement(id_max);
		
		ResultSet rSet = statemenmt.executeQuery();
		rSet.next();
		int id_nueva_partida = generateId(); // Se le suma uno al ID de partida más alto

		
		String sql = "INSERT INTO BlackJack(id, mazo, cartas_banca) VALUES (?, ?, ?)"; // Campos de Blackjack
		statemenmt = conn.prepareStatement(sql);

        // Campos de Blackjack
		statemenmt.setInt(1, blackjack.getId());
		statemenmt.setString(2, blackjack.getMazo());
		statemenmt.setString(3, blackjack.getCartas_banca());
		statemenmt.execute();  

		String sql2 = "INSERT INTO Partida(id, turno) VALUES (?, ?)"; // Campos de Blackjack
		statemenmt = conn.prepareStatement(sql2);

        // Campos de Partida
		statemenmt.setInt(1, partida.getId());
		statemenmt.setString(2, partida.getTurno());
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
	/**
	 * Borrado de una partida de blackjack
	 * @param id
	 */
	public int delete(int id) throws SQLException {
		Connection conn = PoolConnectionManager.getConnection();
		String sql = "DELETE FROM BlackJack WHERE id = " + id;
		
		PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.execute();   
        
        conn.close();
        stmt.close();
        return 0;
	}
	
	/**
	 * Actualizacion de cualquiera de los campos de una partida a excepcion del identificador
	 */
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
	/**
	 * Recupera una partida identificada por un id al reanudarla
	 * @param id
	 */
	public Blackjack getPartida(int id) throws Exception {
		// Abrimos la conexión e inicializamos los parámetros 
		Connection conn = PoolConnectionManager.getConnection();
		String sql = "SELECT * FROM BlackJack WHERE id = " + id;
		
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
