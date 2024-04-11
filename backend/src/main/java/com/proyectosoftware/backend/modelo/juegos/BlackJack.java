package com.proyectosoftware.backend.modelo.juegos;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import com.proyectosoftware.backend.modelo.Carta;
import com.proyectosoftware.backend.modelo.Usuario;
import com.proyectosoftware.backend.modelo.barajas.BarajaFrancesa;
import com.proyectosoftware.backend.modelo.interfaces.Baraja;
import com.proyectosoftware.backend.modelo.interfaces.JuegoConApuesta;

/*
 * Juego del blackjack
*/
public class BlackJack implements JuegoConApuesta {

    private enum Accion {
        PEDIR_CARTA,
        PLANTARSE
    }

    private static final int MAX_JUGADORES = 4;

    private List<Carta> mazo;
    private Baraja baraja;
    private int turno;

    private HashMap<String, Integer> fichas_usuario;        // Diccionario con los usuarios y sus fichas a usar en la partida
    private HashMap<String, List<Carta>> cartas_usuario;    // Diccionario con los usuarios y sus cartas a usar en la partida
    private HashMap<String, Boolean> plantado;              // Diccionario con los usuarios y si se han plantado o no (inicializar a false)
    private List<Carta> cartas_croupier;                    // Lista de cartas del croupier (Carta de índice 0 está boca arriba y la otra boca abajo)
    private HashMap<String, Integer> apuesta_usuario;       // Diccionario con los usuarios y su apuesta en la partida
    private HashMap<String, Boolean> apuesta_plus;
    private HashMap<Integer, String> usuarios;
    private String id;


    /**
     * Constructor por defecto
    */
    public BlackJack() {
        baraja = BarajaFrancesa.devolverInstancia();
        mazo = baraja.devolverCartas();
        fichas_usuario = new HashMap<>();
        cartas_usuario = new HashMap<>();
        plantado = new HashMap<>();
        cartas_croupier = new ArrayList<>();
        apuesta_usuario = new HashMap<>();
        apuesta_plus = new HashMap<>();
        usuarios = new HashMap<>();
        id = this.generateID();
    }


    /**
     * Cargar un juego de blackjack dado un estado
     * @param estado
    */
    public BlackJack(JSONObject estado) {

    }


    /**
     * Función que inicializa una partida, se baraja y se reparten
     * las cartas a los jugadores y a la banca
    */
    public void iniciarPartida() {
        turno = 0;
        Collections.shuffle(mazo);
        repartirCartas();
    }


    /**
     * Jugada normal de Blackjack
     * @param usuario   - Usuario al que le toca jugar
     * @param accion    - Accion que realiza el usuario
    */
    public void jugada(Usuario usuario, Accion accion) {
        if (accion == Accion.PEDIR_CARTA) {
            if (!jugadorPlantado(usuario)) {
                pedirCarta(usuario);
                if (valorMano(cartas_usuario.get(usuario.getId())) >= 21) { // Si el usuario llega o se pasa de 21, se retira de la partida
                    plantarse(usuario);
                    siguenteTurno();
                }
            }
            else {
                // Lanzar error, jugador plantado y está pidiendo carta
            }
        }
        else { // accion == PLANTARSE
            if (!jugadorPlantado(usuario)) {
                plantarse(usuario);
                siguenteTurno();
            }
            else {
                // Lanzar error, jugador plantado y se está plantando otra vez
            }
        }
        if (turno == MAX_JUGADORES - 1) {
            jugadaFinal();
        }
    }


    /**
     * Jugada inicial de Blackjack
     * @param usuario           - Usuario al que le toca jugar
     * @param apuestaInicial    - Apuesta inicial que realiza el usuario
    */
    public void jugadaInicial(Usuario usuario, int apuestaInicial) {
        apostar(usuario, apuestaInicial);   // Cada usuario realiza su apuesta inicial
    }


    /**
     * Jugada final de Blackjack, el croupier destapa su otra carta
     * y se comprueba que jugadores han ganado, perdido o empatado contra la banca
    */
    public void jugadaFinal() {
        List<String> usuarios_ganadores;
        turnoCroupier();
        usuarios_ganadores = comprobarGanadores();
    }


    public void nuevoUsuario(Usuario usuario) {
        int numeroUsuarios = usuarios.size(); 
        if (numeroUsuarios < MAX_JUGADORES) {
            usuarios.put(numeroUsuarios, usuario.getId());              // Turno del jugador en la mesa
            cartas_usuario.put(usuario.getId(), new ArrayList<Carta>());
            apuesta_plus.put(usuario.getId(), false);
            plantado.put(usuario.getId(), false);
            if (usuarios.size() == MAX_JUGADORES) {
                iniciarPartida();
            }
        }
        else {
            // Lanzar error, tope de jugadores alcanzado
        }
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
     * Apuesta inicial que un jugador realiza
     * @param usuario   - Usuario que realiza la apuesta
     * @param apuesta   - Valor de la apuesta
     */
    @Override
    public void apostar(Usuario usuario, int apuesta) {
        if (usuarios.size() < MAX_JUGADORES) {
            fichas_usuario.put(usuario.getId(), apuesta);
        }
        else {
            int fichas_disponibles = fichas_usuario.get(usuario.getId());
            if (apuesta > fichas_disponibles) {
                //Mandar error al control y esperar a nueva apuesta
            }
            else {
                fichas_disponibles -= apuesta;
                fichas_usuario.put(usuario.getId(), fichas_disponibles);
                apuesta_usuario.put(usuario.getId(), apuesta);
                //Mandar al control las fichas disponibles
            }
        }
    }


    /**
     * Suma una carta aleatoria a las cartas de un usuario
     * @param usuario - Usuario que va a sumar una carta
    */
    public void pedirCarta(Usuario usuario) {
        Carta carta_sacada = mazo.get(0);
        mazo.remove(0);                                         // Se saca una carta y se saca del mazo
        cartas_usuario.get(usuario.getId()).add(carta_sacada);  // Se añade la carta a las cartas del usuario
    }
    

    /**
     * Planta al usuario con las cartas que tiene
     * @param usuario - Usuario que se planta
    */
    public void plantarse(Usuario usuario) {
        plantado.put(usuario.getId(), true);   // Pone a true su booleano de plantado
    }
    

    /**
     * Devuelve el valor de una carta dentro del Blackjack
     * @param carta - Carta a evaluar
     * @return valor - Valor de la carta
    */
    public int valorCarta(Carta carta) {
        int numero = carta.getNumero();
        return (numero == 1) ? 11 : (numero == 11 || numero == 12) ? 10 : numero;      // Si la carta es un as (valor 1), devuelve 11, sino su valor normal
    }


    /**
     * Devuelve el valor de una mano dentro del Blackjack
     * @param cartas - Cartas a evaluar
     * @return valor - Valor de la mano
    */
    public int valorMano(List<Carta> cartas) {
        int suma = 0;
        for (Carta carta : cartas) {
            suma += valorCarta(carta);
        }
        return suma;
    }


    /**
     * Devuelve true si "usuario" se ha plantado
     * @param usuario - Usuario a evaluar
     * @return boolean - True sí y sólo sí "usuario" se ha plantado
    */
    public boolean jugadorPlantado(Usuario usuario) {
        return plantado.get(usuario.getId());
    }


    /**
     * El croupier juega su turno después de que
     * los jugadores hayan jugado sus turnos.
     * La funcion acaba con la situación final de las cartas del croupier
    */
    public void turnoCroupier() {
        // Se revela la segunda carta del croupier (vista)
        int suma_croupier = valorMano(cartas_croupier);
        while (suma_croupier <= 16) {                   // El croupier pide cartas hasta pasarse de 16
            Carta carta_sacada = mazo.get(0);
            mazo.remove(0);                             // Se saca una carta y se quita del mazo
            cartas_croupier.add(carta_sacada);
            suma_croupier = valorMano(cartas_croupier);
        }
    }
    

    /**
     * Se reparten las dos cartas iniciales a cada jugador
    */
    public void repartirCartas() {
        List<Carta> cartas;
        for (int i = 0; i < usuarios.size(); i++) {     // Itera sobre cada usuario
            cartas = new ArrayList<>();                 // Array temporal en el que se meten dos cartas
            for (int j = 0; j < 2; j++) {               // A cada usuario se le reparten DOS cartas
                cartas.add(mazo.get(0));                // Se saca una carta y se añade al array temporal
                mazo.remove(0);
            }
            String usuario = usuarios.get(i);
            List<Carta> cartas_temp = new ArrayList<>();
            cartas_temp.addAll(cartas);
            cartas_usuario.put(usuario, cartas_temp);
            if (valorMano(cartas_usuario.get(usuario)) == 21) {
                apuesta_plus.put(usuario, true);
            }
        }
        for (int i = 0; i < 2; i++) {                   // Reparto de cartas al croupier
            cartas_croupier.add(mazo.get(0));
            mazo.remove(0);
        }
    }


    /**
     * Se comprueba qué jugadores han ganado o han perdido contra la banca
     * y se retocan sus fichas
     * @return Lista de usuarios que han ganado a la banca
    */
    public List<String> comprobarGanadores() {
        int cuenta_croupier = valorMano(cartas_croupier);
        List<String> usuarios_ganadores = new ArrayList<>();

        for (String usuario : cartas_usuario.keySet()) {
            List<Carta> cartas = cartas_usuario.get(usuario);

            int cuenta = valorMano(cartas);
            int apuesta_realizada_usuario = apuesta_usuario.get(usuario);
            int fichas_del_usuario = fichas_usuario.get(usuario);

            if (cuenta > 21) {      // Si un jugador se pasa de 21, pierde independientemente de las cartas del croupier
                // usuario pierde, ¿mostrar algo en vista?
            }
            else if (cuenta_croupier > 21) {    // usuario gana
                if (!apuesta_plus.get(usuario)) {
                    fichas_usuario.put(usuario, fichas_del_usuario + 2*(apuesta_realizada_usuario));
                }
                else {
                    fichas_usuario.put(usuario, fichas_del_usuario + 2*(apuesta_realizada_usuario) + (apuesta_realizada_usuario/2));
                }
                usuarios_ganadores.add(usuario);
            }
            else if (cuenta <= 21 && cuenta > cuenta_croupier) {    // usuario gana
                if (!apuesta_plus.get(usuario)) {
                    fichas_usuario.put(usuario, fichas_del_usuario + 2*(apuesta_realizada_usuario));
                }
                else {
                    fichas_usuario.put(usuario, fichas_del_usuario + 2*(apuesta_realizada_usuario) + (apuesta_realizada_usuario/2));
                }
                usuarios_ganadores.add(usuario);
            }
            else if (cuenta <= 21 && cuenta < cuenta_croupier) {
                // usuario pierde, ¿mostrar algo en vista?
            }
            else {  // Empate, el jugador recupera su apuesta sin ganar ni perder nada
                fichas_usuario.put(usuario, fichas_del_usuario + apuesta_realizada_usuario);
            }
        }
        return usuarios_ganadores;
    }


    private String cartasToString(List<Carta> cartas){
        return String.join(";", cartas.stream().map(Carta::toString).toList());
    }
    

    /**
     * {@inheritDoc}
     * @implSpec
     *  Se guardara:
     *  <ul>
     *  <li> El ID del juego
     *  <li> El turno
     *  <li> Una lista de los usuario que contine en cada campo: 
     *       <ul>
     *       <li> El id del usuario
     *       <li> La apuesta inicial del usuario
     *       <li> Si el usuario ha obtenido 21 con las primeras cartas
     *       <li> Si el usuario se ha plantado
     *       <li> Las fichas del usuario
     *       <li> Las cartas (en forma de string) del usuario
     *       </ul>
     *  <li> Las cartas (en forma de string) del croupier
     *  <li> Las cartas (en forma de string) del mazo
     *  </ul>
     */
    @SuppressWarnings("unchecked")
    @Override
    public JSONObject guardar() {
        JSONObject estado = new JSONObject();
        JSONArray usuariosArray = new JSONArray();
        
        for (Integer clave : this.usuarios.keySet()) {
            String id_usuario = this.usuarios.get(clave);
            JSONObject usuarioJSON = new JSONObject();
            usuarioJSON.put("ID", id_usuario);
            usuarioJSON.put("Apuesta", apuesta_usuario.get(id_usuario));
            usuarioJSON.put("Premio_extra", apuesta_plus.get(id_usuario));
            usuarioJSON.put("Plantado", plantado.get(id_usuario));
            usuarioJSON.put("Fichas", fichas_usuario.get(id_usuario));
            usuarioJSON.put("Turno_mesa",clave);
            usuarioJSON.put("Cartas", cartasToString(this.cartas_usuario.get(id_usuario)));
            usuariosArray.add(usuarioJSON);
        }

        estado.put("ID", this.id);
        estado.put("Turno", this.turno);
        estado.put("Usuarios", usuariosArray);
        estado.put("Cartas_croupier", cartasToString(this.cartas_croupier));
        estado.put("Cartas_mazo", cartasToString(this.mazo));
        return estado;
    }
    

    @Override
    public void cargar(JSONObject estado) {
        this.id = (String) estado.get("ID");
        this.turno = (Integer) estado.get("Turno");
        this.mazo = baraja.parsearCartas((String) estado.get("Cartas_mazo"));
        this.cartas_croupier = baraja.parsearCartas((String) estado.get("Cartas_croupier"));
        JSONArray usuarioArray = (JSONArray)estado.get(usuarios);

        for (Object object : usuarioArray) {
            JSONObject infoUsuario = (JSONObject) object;
           
            String id = (String) infoUsuario.get("ID");
            String cartasString = (String) infoUsuario.get("Cartas");
            Integer apuesta = (Integer) infoUsuario.get("Apuesta");
            Integer fichas = (Integer) infoUsuario.get("Fichas");
            Boolean plantado = (Boolean) infoUsuario.get("Plantado");
            Boolean premio_extra = (Boolean) infoUsuario.get("Premio_extra");
            Integer turno_jugador = (Integer) infoUsuario.get("Turno_mesa");

            this.usuarios.put(turno_jugador, id);
            this.cartas_usuario.put(id, baraja.parsearCartas(cartasString));
            this.apuesta_usuario.put(id, apuesta);
            this.apuesta_plus.put(id, premio_extra);
            this.plantado.put(id, plantado);
            this.fichas_usuario.put(id, fichas);
        }
    }
}
