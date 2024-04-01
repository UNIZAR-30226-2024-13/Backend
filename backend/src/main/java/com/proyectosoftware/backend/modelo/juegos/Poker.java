package com.proyectosoftware.backend.modelo.juegos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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

    private static final String CARTA_ALTA = "carta_alta";
    private static final String PAR = "pareja";
    private static final String DOBLE_PAR = "doble_pareja";
    private static final String TRIO = "trio";
    private static final String ESC = "escalera";
    private static final String COLOR = "color";
    private static final String FULL = "full";
    private static final String POKER = "poker";
    private static final String ESC_COLOR = "escalera_color";
    private static final String ESC_REAL = "escalera_real";

    private static final int PRIO_CARTA_ALTA = 0;
    private static final int PRIO_PAR = 1;
    private static final int PRIO_DOBLE_PAR = 2;
    private static final int PRIO_TRIO = 3;
    private static final int PRIO_ESC = 4;
    private static final int PRIO_COLOR = 5;
    private static final int PRIO_FULL = 6;
    private static final int PRIO_POKER = 7;
    private static final int PRIO_ESC_COLOR = 8;
    private static final int PRIO_ESC_REAL = 9;

    private static final int MAX_JUGADORES = 4;

    private int bote;
    private int turno;
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
        turno = 0;
        ultima_apuesta = 0;
        cartas_mesa = new ArrayList<>();
        fichas_usuario = new HashMap<>(MAX_JUGADORES);
        cartas_usuario = new HashMap<>(MAX_JUGADORES);
        mano_usuario = new HashMap<>(MAX_JUGADORES);
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
        turno++;
        if (turno == MAX_JUGADORES) {
            turno = 0;
        }
    }

    /**
     * Reparte las cartas a los jugadores y las centrales
     * @param usuarios
     */
    public void repartirCartas(List<Usuario> usuarios) {
        Collections.shuffle(mazo);
        List<Carta> cartas;
        for (int i = 0; i < usuarios.size(); i++) {
            cartas = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                cartas.add(mazo.get(0));
                System.out.println(mazo.get(0));
                mazo.remove(0);
            }
            cartas_usuario.put(usuarios.get(i).getId(), new ArrayList<>(cartas));
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
        // Ordenar la lista de cartas de menor a mayor número
        Collections.sort(cartas_mano, new Comparator<Carta>() {
            @Override
            public int compare(Carta carta1, Carta carta2) {
                return Integer.compare(carta1.getNumero(), carta2.getNumero());
            }
        });
        Carta carta;
        Carta carta_siguiente = cartas_mano.get(0);
        int num_iguales = 1;
        int num_esc = 1;
        int esc_color = 1;
        int esc_real = 1;
        boolean hay_pareja = false;
        boolean hay_trio = false;
        Map<String, Integer> num_color = new HashMap<>(4);
        for (int i = 0; i < 4; i++) {
            num_color.put(baraja.colorReal(i), 0);
        }
        Mano mano = new Mano();
        Mano mejor_mano = new Mano();
        for (int i = 0; i < cartas_mano.size(); i++) {
            carta = cartas_mano.get(i);
            int valor_color = num_color.get(baraja.colorReal(carta.getColor()));
            num_color.put(baraja.colorReal(carta.getColor()), valor_color + 1);
            if (i < cartas_mano.size() - 1) {
                carta_siguiente = cartas_mano.get(i+1);
            }
            if (carta.getNumero() == carta_siguiente.getNumero() && i != 6) {
                num_iguales++;
                // Caso poker
                if (num_iguales == 4) {
                    mano.setMano(POKER);
                    mano.setPrioridad(PRIO_POKER);
                    mano.setValor(carta.getNumero());
                }
                // Caso trio
                else if (num_iguales == 3) {
                    hay_trio = true;
                    // Caso full
                    if (hay_pareja) {
                        mano.setMano(FULL);
                        mano.setPrioridad(PRIO_FULL);
                        mano.setValor(carta.getNumero());
                    }
                    else {
                        mano.setMano(TRIO);
                        mano.setPrioridad(PRIO_TRIO);
                        mano.setValor(carta.getNumero());
                    }
                }
                // Caso doble pareja
                else if (num_iguales == 2 && mejor_mano.getPrioridad() == PRIO_PAR) {
                    hay_pareja = true;
                    mano.setMano(DOBLE_PAR);
                    mano.setPrioridad(PRIO_DOBLE_PAR);
                    mano.setValor(carta.getNumero());
                }
                // Caso pareja
                else {
                    // Caso full
                    if (hay_trio) {
                        mano.setMano(FULL);
                        mano.setPrioridad(PRIO_FULL);
                        mano.setValor(carta.getNumero());
                    }
                    else {
                        mano.setMano(PAR);
                        mano.setPrioridad(PRIO_PAR);
                        mano.setValor(carta.getNumero());
                    }
                }
            }
            else if (carta.getNumero() == (carta_siguiente.getNumero() - 1)){
                if (carta.getNumero() == BarajaFrancesa.AS || carta.getNumero() == BarajaFrancesa.REY ||
                    carta.getNumero() == BarajaFrancesa.CABALLO ||carta.getNumero() == BarajaFrancesa.SOTA || carta.getNumero() == 10) {
                    esc_real++;
                }
                if (i != 6) {
                    num_iguales = 1;
                    num_esc++;
                    if (baraja.colorReal(carta.getColor()) == baraja.colorReal(carta_siguiente.getColor())) {
                        esc_color++;
                    }
                    else {
                        esc_color = 1;
                    }
                }
                // Caso escalera real
                if (num_esc >= 5 && esc_color >= 5 && esc_real == 5) {
                    mano.setMano(ESC_REAL);    
                    mano.setPrioridad(PRIO_ESC_REAL);
                    mano.setValor(carta.getNumero());
                }
                else if (i != 6) {
                    // Caso escalera color
                    if (num_esc >= 5 && esc_color >= 5) {
                        mano.setMano(ESC_COLOR);    
                        mano.setPrioridad(PRIO_ESC_COLOR);
                        mano.setValor(carta.getNumero());
                    }
                    // Caso escalera
                    else if (num_esc >= 5) {
                        mano.setMano(ESC);
                        mano.setPrioridad(PRIO_ESC);
                        mano.setValor(carta.getNumero());
                    }
                    else {
                        mano.setMano(CARTA_ALTA);
                        mano.setPrioridad(PRIO_CARTA_ALTA);
                        mano.setValor(carta.getNumero());
                    }
                }
            }
            else {
                num_esc = 1;
                num_iguales = 1;
                esc_color = 1;
                boolean es_color = false;
                // Caso color
                for (int j = 0; j < 4; j++) {
                    if (num_color.get(baraja.colorReal(j)) == 5) {
                        mano.setMano(COLOR);
                        mano.setPrioridad(PRIO_COLOR);
                        mano.setValor(carta.getNumero());
                        es_color = true;
                        break;
                    }
                }
                // Caso carta alta
                if (!es_color) {
                    mano.setMano(CARTA_ALTA);
                    mano.setPrioridad(PRIO_CARTA_ALTA);
                    mano.setValor(carta.getNumero());
                }
            }
            if (mano.getPrioridad() > mejor_mano.getPrioridad() ||
                mano.getPrioridad() == mejor_mano.getPrioridad() && mano.getValor() > mejor_mano.getValor()) {
                mejor_mano.setMano(mano.getMano());
                mejor_mano.setPrioridad(mano.getPrioridad());
                mejor_mano.setValor(mano.getValor());
            }
        }
        return mejor_mano;
    }


    /**
     * Determina el ganador de una partida de poker
     * @param usuarios - Lista de usuarios de la partida
     * @return - Devuelve el usuario ganador
     */
    public Usuario ganadorPartida(List<Usuario> usuarios){
        String id_usuario;
        Mano mano = new Mano();
        Mano mejor_mano = new Mano();
        int ganador = 0;
        List<Carta> cartas_mano = new ArrayList<>();
        for (int i = 0; i < usuarios.size(); i++) {
            id_usuario = usuarios.get(i).getId();
            List<Carta> carta_usuario = cartas_usuario.get(id_usuario);
            for (int j = 0; j < 2; j++) {
                cartas_mano.add(carta_usuario.get(j));
            }
            for (int j = 0; j < cartas_mesa.size(); j++) {
                cartas_mano.add(cartas_mesa.get(j));
            }
            
            mano = verificarMano(cartas_mano);
            mano_usuario.put(id_usuario, mano);
            cartas_mano.clear();
        }
        mejor_mano = mano_usuario.get(usuarios.get(0).getId());
        for (int i = 1; i < usuarios.size(); i++) {
            mano = mano_usuario.get(usuarios.get(i).getId());
            if (mano.getPrioridad() > mejor_mano.getPrioridad() || (mano.getPrioridad() == mejor_mano.getPrioridad() &&
                mano.getValor() > mejor_mano.getValor())) {
                mejor_mano.setMano(mano.getMano());
                mejor_mano.setPrioridad(mano.getPrioridad());
                mejor_mano.setValor(mano.getValor());
                ganador = i;
            }
        }
        return usuarios.get(ganador);
    }
}
