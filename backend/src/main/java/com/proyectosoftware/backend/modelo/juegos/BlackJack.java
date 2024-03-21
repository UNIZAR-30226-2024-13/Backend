package com.proyectosoftware.backend.modelo.juegos;

import java.util.List;

import com.proyectosoftware.backend.modelo.Carta;
import com.proyectosoftware.backend.modelo.Partida;
import com.proyectosoftware.backend.modelo.Usuario;
import com.proyectosoftware.backend.modelo.barajas.BarajaFrancesa;
import com.proyectosoftware.backend.modelo.interfaces.Baraja;
import com.proyectosoftware.backend.modelo.interfaces.Estado;
import com.proyectosoftware.backend.modelo.interfaces.JuegoConApuesta;

/**
 * Juego del blackjack
 */
public class BlackJack implements JuegoConApuesta, Estado{
    private List<Carta> cartas_banca;
    private List<Carta> mazo;
    private Baraja baraja;

    /**
     * Constructor por defecto
     */
    public BlackJack() {
        baraja = BarajaFrancesa.devolverInstancia();
    }

    /**
     * Cargar un juego de blackjack dado un estado
     * @param estado
     */
    public BlackJack(Estado estado){

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


    @Override
    public void apostar(Usuario usaurio, double apuesta){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'apostar'");
    }

    /**
     * Genera un estado a partir de un string
     * @param estadoString - String a parsear
     * @return - Estado
     */
    public Estado recuperarEstado(String estadoString){

    }

    public void pedirCarta(Usuario usuario) {
        
    }
    
    public void plantarse(Usuario usuario) {
        
    }

    /**
     * 
     * @param estado
     * @return
     */
    public String crearEstado(Estado estado){
        
    }
}
