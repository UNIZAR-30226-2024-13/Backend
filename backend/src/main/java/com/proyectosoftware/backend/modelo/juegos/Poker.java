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
 * Juego del poker
 */
public class Poker implements JuegoConApuesta{
    private int bote;
    private int ultima_apuesta;
    private List<Carta> cartas_mesa;
    private List<Carta> mazo;
    private Baraja baraja;

    /**
     * Constructor por defecto
     */
    public Poker(int id, int turno, int bote, int ultima_apuesta, String mazo, String cartas_mesa) {
        super(id, turno);
        this.bote = bote;
        this.ultima_apuesta = ultima_apuesta;
        baraja = BarajaFrancesa.devolverInstancia();
        this.mazo = baraja.parsearCartas(mazo);
        this.cartas_mesa = baraja.parsearCartas(cartas_mesa);
        
    }

    /**
     * Cargar un juego de poker dado un estado
     * @param estado
     */
    public Poker(Estado estado){

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
}
