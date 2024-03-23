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
     * Aumenta el numero de fichas de un usuario
     * @param usaurio   - Usuario que va a aumentar sus fichas
     * @param apuesta   - Cantidad de fichas
     */
    public void sumarFichas(Usuario usuario, int fichas);
    
    /**
     * Resta el numero de fichas de un usuario
     * @param usaurio   - Usuario que va a perder fichas
     * @param apuesta   - Cantidad de fichas
     */
    public void restarFichas(Usuario usuario, int fichas);
}
