package com.proyectosoftware.backend.modelo.juegos;

import java.util.List;
import java.util.Map;

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
    private List<Map<Integer, Integer>> usuarios; //lista con los usuarios y sus fichas a usar para la partida

    /**
     * Constructor por defecto
     */
    public Poker() {
        baraja = BarajaFrancesa.devolverInstancia();
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
    public void apostar(Usuario usuario, int apuesta){
        int fichas_disponibles = usuarios.get(usuario);
        if (apuesta > fichas_disponibles) {
            //Mandar error al control y esperar a nueva apuesta
        }
        else {
            fichas_disponibles -= apuesta;
            usuarios.put(usuario,fichas_disponibles);
            //Mandar al control las fichas disponibles
        }
    }

    @Override
    public void sumarFichas(Usuario usuario, int fichas) {
        int fichas_disponibles = usuarios.get(usuario);
        fichas_disponibles += fichas;
        usuarios.put(usuario,fichas_disponibles);
        fichas_totales += fichas_disponibles;
        //Almacenar fichas totales en la BD
    }
    

    public void agnadirCartaCentro(Carta nueva_carta) {
        cartas_mesa.add(nueva_carta);
    }
}
