package com.proyectosoftware.backend.modelo.juegos;

import com.proyectosoftware.backend.modelo.Usuario;
import com.proyectosoftware.backend.modelo.barajas.BarajaEspaniola;
import com.proyectosoftware.backend.modelo.interfaces.Baraja;
import com.proyectosoftware.backend.modelo.interfaces.Estado;
import com.proyectosoftware.backend.modelo.interfaces.JuegoSinApuesta;

/**
 * Juego del chinquillo
 */
public class Cinquillo implements JuegoSinApuesta{
    private Baraja baraja;

    /**
     * Constructor por defecto
     */
    public Cinquillo() {
        baraja = BarajaEspaniola.devolverInstancia();
    }

    /**
     * Cargar un juego de cinquillo dado un estado
     * @param estado
     */
    public Cinquillo(Estado estado){

    }

    @Override
    public Estado guardar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

    @Override
    public void cargar(Estado estado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cargar'");
    }

    @Override
    public Estado recuperarEstado(String estadoString) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recuperarEstado'");
    }

    @Override
    public String crearEstado(Estado estado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearEstado'");
    }


    @Override
    public void siguenteTurno() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'siguenteTurno'");
    }

    /**
     * Juego completo del Cinquillo para 4 jugadores
     */
    public void jugar(){
        baraja.barajar();

        /**
         * TODO:  repartir  a los 4 usuarios
         */

         boolean finJuego = false;
         do{
            
         }while(!finJuego);
    }
}
