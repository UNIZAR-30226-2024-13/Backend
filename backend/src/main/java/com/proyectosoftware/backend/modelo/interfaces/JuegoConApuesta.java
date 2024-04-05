package com.proyectosoftware.backend.modelo.interfaces;

import com.proyectosoftware.backend.modelo.Usuario;

public interface JuegoConApuesta extends Juego{
    /**
     * Apuesta que un usuario realiza
     * @param usaurio   - Usuario que realiza la apuesta
     * @param apuesta   - Valor de la apuesta
     */
    public void apostar(String usaurio, int apuesta);
    
    /**
     * Aumenta el numero de fichas de un usuario
     * @param usaurio   - Usuario que va a aumentar sus fichas
     */
    public void sumarFichas(String usuario);
    

}
