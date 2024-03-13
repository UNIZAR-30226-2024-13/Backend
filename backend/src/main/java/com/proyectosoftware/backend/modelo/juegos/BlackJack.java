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
public class BlackJack implements JuegoConApuesta{
    private List<Carta> cartas_banca;
    private List<Carta> mazo;
    private Baraja baraja;

    /**
     * Constructor por defecto
     */
    public BlackJack(int id, int turno, String mazo, String cartas_banca) {
        super(id, turno);
        baraja = BarajaFrancesa.devolverInstancia();
        this.mazo = baraja.parsearCartas(mazo);
        this.cartas_banca = baraja.parsearCartas(cartas_banca);
        
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
}
