package com.proyectosoftware.backend.modelo.juegos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
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
    private Baraja baraja;
    private List<Carta> mazo;
    private Map<String, Integer> fichas_usuario; //Diccionario con los usuarios y sus fichas a usar en la partida
    private Map<String, List<Carta>> cartas_usuario; // Diccionario con los usuarios y sus cartas a usar en la partida

    /**
     * Constructor por defecto
     */
    public Poker() {
        baraja = BarajaFrancesa.devolverInstancia();
        mazo = baraja.devolverCartas();
        bote = 0;
        ultima_apuesta = 0;
        cartas_mesa = new ArrayList<>();
        fichas_usuario = new HashMap<>();
        cartas_usuario = new HashMap<>();
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

    /**
     * Reparte las cartas a los jugadores y las centrales
     * @param usuarios
     */
    public void repartirCartas(List<Usuario> usuarios) {
        Collections.shuffle(mazo);
        List<Carta> cartas = new ArrayList<>();
        for (int i = 0; i < usuarios.size(); i++) {
            for (int j = 0; j < 2; j++) {
                cartas.add(mazo.get(0));
                mazo.remove(0);
            }
            cartas_usuario.put(usuarios.get(i).getId(), cartas);
        }
        for (int i = 0; i < 3; i++) {
            cartas_mesa.add(mazo.get(0));
            mazo.remove(0);
        }
    }


    @Override
    public void apostar(Usuario usuario, int apuesta){
        int fichas_disponibles = fichas_usuario.get(usuario.getId());
        if (apuesta > fichas_disponibles) {
            //Mandar error al control y esperar a nueva apuesta
        }
        else if (apuesta < ultima_apuesta) {
            //Mandar error al control y esperar a nueva apuesta
        }
        else {
            fichas_disponibles -= apuesta;
            fichas_usuario.put(usuario.getId(),fichas_disponibles);
            //Mandar al control las fichas disponibles
        }
    }


    @Override
    public void sumarFichas(Usuario usuario, int fichas) {
        int fichas_disponibles = fichas_usuario.get(usuario.getId());
        fichas_disponibles += fichas;
        fichas_usuario.put(usuario.getId(),fichas_disponibles);
        bote += fichas_disponibles;
        //Almacenar fichas totales en la BD
    }
    

    /**
     * Añade una carta a las centrales
     */
    public void agnadirCartaCentro() {
        cartas_mesa.add(mazo.get(0));
        mazo.remove(0);
    }


    /**
     * 
     */
    public Usuario ganadorPartida(){
        
    }
}
