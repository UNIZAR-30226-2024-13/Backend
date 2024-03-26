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

    private static final int SOTA = 11;
    private static final int CABALLO = 12;
    private static final int REY = 13;
    private static final int AS = 14;

    private int bote;
    private int ultima_apuesta;
    private List<Carta> cartas_mesa;
    private Baraja baraja;
    private List<Carta> mazo;
    private Map<String, Integer> fichas_usuario; //Diccionario con los usuarios y sus fichas a usar en la partida
    private Map<String, List<Carta>> cartas_usuario; // Diccionario con los usuarios y sus cartas a usar en la partida
    private Map<String, Mano> mano_usuario; //Diccionario con los usuarios y su mano en una partida
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
        mano_usuario = new HashMap<>();
    }

    /**
     * Cargar un juego de poker dado un estado
     * @param estado
     */
    public Poker(Estado estado){

    }


    /**
     * Crea un estado con el estado actual del juego poker
     * @return - estado
     */
    @Override
    public Estado guardar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

    /**
     * Inicializa el juego poker con un estado dado
     * @param estado - el estado a cargar
     */
    @Override
    public void cargar(Estado estado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cargar'");
    }

    /**
     * Genera un estado de poker a partir de un string
     * @param estadoString - String a parsear
     * @return - Estado
     */
    @Override
    public Estado recuperarEstado(String estadoString) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recuperarEstado'");
    }

    /**
     * 
     * @param estado
     * @return
     */
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

    /**
     * Apuesta que un usuario realiza
     * @param usaurio   - Usuario que realiza la apuesta
     * @param apuesta   - Valor de la apuesta
     */
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

    /**
     * Aumenta el numero de fichas de un usuario
     * @param usaurio   - Usuario que va a aumentar sus fichas
     * @param fichas   - Cantidad de fichas
     */
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


    public Mano verificarMano(List<Carta> cartas_mano) {
        // Comprobar escalera real
        Carta carta;
        for (int i = 0; i < cartas_mano.size(); i++) {
            carta = cartas_mano.get(i);
            if (carta.getNumero() == AS or hayAS) {
                
            }
        }
    }


    /**
     * Determina el ganador de una partida de poker
     * @param usuarios - Lista de usuarios de la partida
     * @return - Devuelve el usuario ganador
     */
    public Usuario ganadorPartida(List<Usuario> usuarios){
        String id_usuario;
        Mano mano;
        List<Carta> cartas_mano = new ArrayList<>();
        for (int i = 0; i < usuarios.size(); i++) {
            id_usuario = usuarios.get(i).getId();
            cartas_mano = cartas_usuario.get(id_usuario);
            for (int j = 0; j < cartas_mesa.size(); j++) {
                cartas_mano.add(cartas_mesa.get(j));
            }
            mano = verificarMano(cartas_mano);
        }
    }
}
