package com.proyectosoftware.backend.modelo.interfaces;

import com.proyectosoftware.backend.modelo.Usuario;

public interface JuegoConApuesta extends Juego{
    /**
     * Apuesta que un usuario realiza
     * @param usaurio   - Usuario que realiza la apuesta
     * @param apuesta   - Valor de la apuesta
     */
    public void apostar(Usuario usaurio, int apuesta);    
}
