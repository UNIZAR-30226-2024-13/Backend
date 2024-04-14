package com.proyectosoftware.backend.modelo.interfaces;

import com.proyectosoftware.backend.modelo.Usuario;

public interface JuegoConApuesta extends Juego{
    /**
     * Apuesta que un usuario realiza
     * @param usaurio   - Usuario que realiza la apuesta
     * @param apuesta   - Valor de la apuesta
     */
    public void apostar(Usuario usaurio, int apuesta);
    
    /**
     * Anaide un usaurio a la mesa con una cantidad de fichas disponibles
     * @param usuario   - El usuario
     * @param apuesta   - La cantidad de fichas
     * 
     * @see Juego#nuevoUsuario(Usuario)
     * @see JuegoConApuesta#apostar(Integer)
     */
    public default void nuevoUsuario(Usuario usuario, int apuesta){
        nuevoUsuario(usuario);
        apostar(usuario, apuesta);
    }
}
