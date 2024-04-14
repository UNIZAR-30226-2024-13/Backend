package com.proyectosoftware.backend.modelo.juegos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;

import com.proyectosoftware.backend.modelo.Carta;
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
    private int ronda;
    private List<Carta> cartas_mesa;
    private Baraja baraja;
    private List<Carta> mazo;
    private List<Usuario> usuarios;
    private Set<String> usuarios_con_apuesta;
    private Map<String, Integer> fichas_usuario; //Diccionario con los usuarios y sus fichas a usar en la partida
    private Map<String, List<Carta>> cartas_usuario; // Diccionario con los usuarios y sus cartas a usar en la partida
    private Map<String, Mano> mano_usuario; //Diccionario con los usuarios y su mano en una partida
    /**
     * Constructor por defecto poker
     */
    public Poker() {
        baraja = BarajaFrancesa.devolverInstancia();
        mazo = baraja.devolverCartas();
        bote = 0;
        turno = 0;
        ronda = 0;
        ultima_apuesta = 0;
        cartas_mesa = new ArrayList<>();
        usuarios = new ArrayList<>(MAX_JUGADORES);
        usuarios_con_apuesta = new HashSet<>(MAX_JUGADORES);
        fichas_usuario = new HashMap<>(MAX_JUGADORES);
        cartas_usuario = new HashMap<>(MAX_JUGADORES);
        mano_usuario = new HashMap<>(MAX_JUGADORES);
    }

    /**
     * Cargar un juego de poker dado un estado
     * @param estado
     */
    public Poker(JSONObject estado){

    }


    /**
     * {@inheritDoc}
     * @implSpec
     * Crea un estado con el estado actual del juego poker
     */
    @Override
    public JSONObject guardar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

    /**
     * {@inheritDoc}
     * @implSpec
     * Inicializa el juego poker con un estado dado
     */
    @Override
    public void cargar(JSONObject estado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cargar'");
    }

    /**
     * {@inheritDoc}
     * @implSpec
     * Genera un estado de poker a partir de un string
     */
    @Override
    public JSONObject crearEstado(String estadoString) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recuperarEstado'");
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void siguenteTurno() {
        turno++;
        if (turno == MAX_JUGADORES) {
            turno = 0;
        }
    }

    /**
     * Añade un nuevo usuario a la partida mientras no supere el máximo de jugadores
     * @param usuario   - Usuario que se añade a la partida
     */
    public void nuevoUsuario(Usuario usuario){
        int numeroUsuarios = usuarios.size(); 
        if (numeroUsuarios < MAX_JUGADORES){
            usuarios.add(usuario);
            if(usuarios.size() == numeroUsuarios){
                repartirCartas();
            }
        }
        else{
            /**
             * TODO: lanzar error juego lleno
             */
        }
    }

    /**
     * Reparte las cartas a los jugadores y las centrales
     */
    public void repartirCartas() {
        Collections.shuffle(mazo);
        List<Carta> cartas;
        for (int i = 0; i < usuarios.size(); i++) {
            cartas = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                cartas.add(mazo.get(0));
                mazo.remove(0);
            }
            cartas_usuario.put(usuarios.get(i).getID(), new ArrayList<>(cartas));
        }
        for (int i = 0; i < 3; i++) {
            cartas_mesa.add(mazo.get(0));
            mazo.remove(0);
        }
    }



    /**
     * Apuesta que un usuario realiza
     * @param usaurio   - Id del usuario que realiza la apuesta
     * @param apuesta   - Valor de la apuesta
     */
    @Override
    public void apostar(Usuario usuario, int apuesta){
        int fichas_disponibles = fichas_usuario.get(usuario.getID());
        if (apuesta > fichas_disponibles) {
            //Mandar error al control y esperar a nueva apuesta
        }
        else {
            fichas_disponibles -= apuesta;
            bote += apuesta;
            fichas_usuario.put(usuario.getID(),fichas_disponibles);
            //Mandar al control las fichas disponibles
        }
    }

    /**
     * Aumenta el numero de fichas de un usuario
     * @param usaurio   - Id del suario que va a aumentar sus fichas
     */
    public void sumarFichas(String usuario) {
        int fichas_disponibles = fichas_usuario.get(usuario);
        fichas_disponibles += bote;
        fichas_usuario.put(usuario, fichas_disponibles);
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
     * Calcula y devuelve la mano que tiene un usuario según sus cartas y las de la mesa
     * @param cartas_mano   - Lista de las cartas del usuario y la mesa
     * @return              - Devuelve la mano que tiene el usuario
     */
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
            // Si la carta actual y la siguiente coinciden en número se pueden dar estos casos
            if (carta.getNumero() == carta_siguiente.getNumero() && i != 6) {
                num_iguales++;
                // Caso poker
                if (num_iguales == 4) {
                    mano.setNombre(POKER);
                    mano.setPrioridad(PRIO_POKER);
                    mano.setValor(carta.getNumero());
                }
                // Caso trio
                else if (num_iguales == 3) {
                    hay_trio = true;
                    // Caso full
                    if (hay_pareja) {
                        mano.setNombre(FULL);
                        mano.setPrioridad(PRIO_FULL);
                        mano.setValor(carta.getNumero());
                    }
                    else {
                        mano.setNombre(TRIO);
                        mano.setPrioridad(PRIO_TRIO);
                        mano.setValor(carta.getNumero());
                    }
                }
                // Caso doble pareja
                else if (num_iguales == 2 && mejor_mano.getPrioridad() == PRIO_PAR) {
                    hay_pareja = true;
                    mano.setNombre(DOBLE_PAR);
                    mano.setPrioridad(PRIO_DOBLE_PAR);
                    mano.setValor(carta.getNumero());
                }
                // Caso pareja
                else {
                    // Caso full
                    if (hay_trio) {
                        mano.setNombre(FULL);
                        mano.setPrioridad(PRIO_FULL);
                        mano.setValor(carta.getNumero());
                    }
                    else {
                        mano.setNombre(PAR);
                        mano.setPrioridad(PRIO_PAR);
                        mano.setValor(carta.getNumero());
                    }
                }
            }
            // Si el número de la carta actual es una unidad menor que el número de la
            // siguiente carta se pueden dar estos casos
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
                    mano.setNombre(ESC_REAL);    
                    mano.setPrioridad(PRIO_ESC_REAL);
                    mano.setValor(carta.getNumero());
                }
                else if (i != 6) {
                    // Caso escalera color
                    if (num_esc >= 5 && esc_color >= 5) {
                        mano.setNombre(ESC_COLOR);    
                        mano.setPrioridad(PRIO_ESC_COLOR);
                        mano.setValor(carta.getNumero());
                    }
                    // Caso escalera
                    else if (num_esc >= 5) {
                        mano.setNombre(ESC);
                        mano.setPrioridad(PRIO_ESC);
                        mano.setValor(carta.getNumero());
                    }
                    // Caso carta alta
                    else {
                        mano.setNombre(CARTA_ALTA);
                        mano.setPrioridad(PRIO_CARTA_ALTA);
                        mano.setValor(carta.getNumero());
                    }
                }
            }
            // Si no se da ningún caso de los anteriores solo puede ser o color o carta alta
            else {
                num_esc = 1;
                num_iguales = 1;
                esc_color = 1;
                boolean es_color = false;
                // Caso color
                for (int j = 0; j < 4; j++) {
                    if (num_color.get(baraja.colorReal(j)) == 5) {
                        mano.setNombre(COLOR);
                        mano.setPrioridad(PRIO_COLOR);
                        mano.setValor(carta.getNumero());
                        es_color = true;
                        break;
                    }
                }
                // Caso carta alta
                if (!es_color) {
                    mano.setNombre(CARTA_ALTA);
                    mano.setPrioridad(PRIO_CARTA_ALTA);
                    mano.setValor(carta.getNumero());
                }
            }
            // Si la mano actual es mejor que la mejor que había hasta ahora, se actualiza
            if (mano.getPrioridad() > mejor_mano.getPrioridad() ||
                mano.getPrioridad() == mejor_mano.getPrioridad() && mano.getValor() > mejor_mano.getValor()) {
                mejor_mano.setNombre(mano.getNombre());
                mejor_mano.setPrioridad(mano.getPrioridad());
                mejor_mano.setValor(mano.getValor());
            }
        }
        return mejor_mano;
    }


    /**
     * Determina el ganador de una partida de poker
     * @return - Devuelve el id del usuario ganador
     */
    public String ganadorPartida(){
        String id_usuario;
        Mano mano = new Mano();
        Mano mejor_mano = new Mano();
        int ganador = 0;
        List<Carta> cartas_mano = new ArrayList<>();
        List<String> usuariosApuesta = new ArrayList<>(usuarios_con_apuesta);
        for (int i = 0; i < usuariosApuesta.size(); i++) {
            id_usuario = usuariosApuesta.get(i);
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
        mejor_mano = mano_usuario.get(usuariosApuesta.get(0));
        for (int i = 1; i < usuariosApuesta.size(); i++) {
            mano = mano_usuario.get(usuariosApuesta.get(i));
            if (mano.getPrioridad() > mejor_mano.getPrioridad() || (mano.getPrioridad() == mejor_mano.getPrioridad() &&
                mano.getValor() > mejor_mano.getValor())) {
                mejor_mano.setNombre(mano.getNombre());
                mejor_mano.setPrioridad(mano.getPrioridad());
                mejor_mano.setValor(mano.getValor());
                ganador = i;
            }
        }
        return usuariosApuesta.get(ganador);
    }


    /**
     * Comprueba si los usuarios han apostado lo mismo en una ronda
     * @param apuestas  - Lista de las apuestas e la ronda
     * @return          - Devuelve true si los usuarios han apostado lo mismo en una ronda, en caso contrario, false
     */
    public boolean mismaApuesta(List<Integer> apuestas) {
        int apuesta = apuestas.get(0);
        boolean iguales = true;
        for (int i = 1; i < apuestas.size(); i++) {
            if (apuesta != apuestas.get(i)) {
                iguales = false;
            }
        }
        return iguales;
    }


    /**
     * Jugada de poker donde se realizan las apuestas de cada jugador y se añade otra
     * carta a la mesa o se verifica el ganador de la partida y se le añaden las fichas
     * @param Usuario   - Usuario que ha realizado la apuesta
     * @param apuesta   - Cantidad de fichas que ha apostado el usuario (vale 0 si se ha retirado)
     */
    public void jugada (Usuario usuario, int apuesta) {
        List<Integer> apuestas = new ArrayList<>(MAX_JUGADORES);
        apostar(usuario, apuesta);
        apuestas.add(apuesta);
        if (apuesta != 0) {
            usuarios_con_apuesta.add(usuario.getID());
        }
        else {
            usuarios_con_apuesta.remove(usuario.getID());
        }
        if (turno == 3 && mismaApuesta(apuestas)) {
            // Turno final, comprobar ganador
            if (ronda == 2) {
                String ganador = ganadorPartida();
                sumarFichas(ganador);
            }
            // Turnos intermedios
            else {
                agnadirCartaCentro();
                apuestas.clear();
            }
            ronda++;
        }
        else if (turno == 3 && !mismaApuesta(apuestas)) {
            apuestas.clear();
        }
        siguenteTurno();
    }
}
