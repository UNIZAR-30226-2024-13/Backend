package com.proyectosoftware.backend.modelo.juegos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.proyectosoftware.backend.modelo.Carta;
import com.proyectosoftware.backend.modelo.Usuario;
import com.proyectosoftware.backend.modelo.barajas.BarajaEspaniola;
import com.proyectosoftware.backend.modelo.interfaces.Baraja;
import com.proyectosoftware.backend.modelo.interfaces.JuegoSinApuesta;

/**
 * Juego del mentiroso
 * @see JuegoSinApuesta
 */
public class Mentiroso implements JuegoSinApuesta{
    public static enum Accion{
        LEVANTAR,
        MENTIR
    }
    private static final int MAX_JUGADORES = 4;

    private Baraja baraja;
    private List<Carta> cartas;

    private Map<Integer, Usuario> usuarios;
    private Map<Integer, List<Carta>> cartasUsuarios;
    
    private int turno;
    private int numeroActual;
    private int cartasUltimaJugada;
    private List<Carta> cartasMesa;
    private String id;

    /**
     * Prepara un nuevo juego
     */
    public Mentiroso(){
        this.baraja = BarajaEspaniola.devolverInstancia();
        this.usuarios = new HashMap<>(MAX_JUGADORES);
        this.cartasUsuarios = new HashMap<>(MAX_JUGADORES);
        this.cartas = baraja.devolverCartas();
        this.id = this.generateID();
    }

    /**
     * 
     * @param estado
     */
    public Mentiroso(JSONObject estado) {
        this.baraja = BarajaEspaniola.devolverInstancia();
        this.cargar(estado);
    }

    /**
     * Jugada inicial sin cartas en la mesa
     * @param usuario   - El usuario que realiza la jugada. Debe represetnar el usuario del turno actual
     * @param cartas    - Cartas que se ponen en la mesa
     * @param numero    - Numero que se pone en la mesa
     */
    public void jugada(Usuario usuario, List<Carta> cartas, int numero){
        int clave = ordenUsuario(usuario);
        List<Carta> cartasUsuario = cartasUsuarios.get(clave);

        cartasUsuario.removeAll(cartas);
        cartasMesa.addAll(cartas);

        numeroActual = numero;
        cartasUltimaJugada = cartas.size();
        siguenteTurno();
    }

    /**
     * Jugada normal del juego.
     * <p>- Si el usuario decide {@code LEVANTAR} se levantaran las ultimas cartas puestas en la mesa
     * si estas tiene todas el mismo numero que el inicial dicho, usuario recogera todas las cartas de la mesa
     * en caso de que al menos una no tenga el mismo numero, el usuario del turno anterior tendra que llevarse las cartas.
     * <p>- Si el usaurio decide {@code MENTIR} se apilaran las cartas dadas al mazo de la mesa.
     * @param usuario   - Usuario que realiza la jugada. Debe ser el mismo que el turno actual
     * @param cartas    - Cartas que se ponen en la mesa. Puede ser nulo.
     * @param accion    - Accion a realizar.
     */
    public void jugada(Usuario usuario, List<Carta> cartas, Accion accion){
        if(numeroActual == -1){
            /**
             * TODO: Mala jugada, lanzar error
             */
        }else{
            int clave = ordenUsuario(usuario);
            
            List<Carta> cartasUsuario = cartasUsuarios.get(clave);

            if(accion == Accion.MENTIR){
                cartasUsuario.removeAll(cartas);
                cartasMesa.addAll(cartas);
                cartasUltimaJugada = cartas.size();
                siguenteTurno();

            }else if(accion == Accion.LEVANTAR){
                int usuarioAnterior = -1;
                int usuarioPerdedor = -1;
                
                usuarioAnterior = turno - 1;
                if(usuarioAnterior < 0){
                    usuarioAnterior = MAX_JUGADORES - 1;
                }

                if (miente()) {
                    usuarioPerdedor = usuarioAnterior;
                }else{
                    usuarioPerdedor = turno;
                    siguenteTurno();
                    if(cartasUsuarios.get(usuarioAnterior).size() == 0){ // El usuario anterior se ha quedado sin cartas
                        ganador(usuarioAnterior);
                        return;
                    }
                }

                aniadirCartas(cartasMesa, usuarioPerdedor);
                cartasMesa.clear();
                numeroActual = -1;
                cartasUltimaJugada = 0;
                if(cartasUsuarios.get(usuarioPerdedor).size() == 0){ // Gana por descartes
                    ganador(usuarioPerdedor);
                    return;
                }
            }
        }
    }
    
    /**
     * Aniade cartas a la manao de un usuario, eliminando los cuartetos.
     * <p> p.e si la mano del usuario despues de aniadir las cartas contine el {@code 3} de {@code OROS},
     * {@code COPAS}, {@code ESPADAS} y {@code BASTOS}, eliminara las 4 cartas de la baraja
     * @param cartas        - Cartas a aniadir
     * @param ordenUsuario  - Orden del usuario a recibir las cartas
     */
    private void aniadirCartas(List<Carta> cartas, int ordenUsuario){
        List<Carta> cartasUsuario = cartasUsuarios.get(ordenUsuario);
        cartasUsuario.addAll(cartas);
        
        List<Integer> numerosAQuitar = cartas.stream()
            .filter(carta -> numeroCartasMismoNumero(cartasUsuario, carta.getNumero()) == 4)
            .map(Carta::getNumero)
            .distinct()
            .collect(Collectors.toList());
        
        cartasUsuario.removeIf(carta -> numerosAQuitar.contains(carta.getNumero()));        
    }

    /**
     * Cuenta el numero de cartas del con el mismo numero en una lista
     * @param cartas    - Lista de cartas donde contar
     * @param numero    - El numero a buscar
     * @return El numero de cartas
     */
    private int numeroCartasMismoNumero(List<Carta> cartas, int numero){
        return (int) cartas.stream()
                           .filter(carta -> carta.getNumero() == numero)
                           .count();
    }

    /**
     * Busca el orden del usuairo en la partida
     * @param usuario   - Usuario a buscar
     * @return El orden, o {@code -1} si no se encuentra
     */
    private int ordenUsuario(Usuario usuario){
        for (int i : usuarios.keySet()) {
            if(usuarios.get(i).getID().equals(usuario.getID())){
                return i;
            }
        }
        return -1;
    }

    /**
     * Comprueba si el ultimo turno ha mentido
     * @return {@code True} si se ha mentido
     */
    private boolean miente(){
        for(Carta carta: cartasMesa.subList(cartasMesa.size() - cartasUltimaJugada - 1, cartasMesa.size() - 1)){
            if(carta.getNumero() != numeroActual){
                return true;
            }
        }
        return false;
    }

    /**
     * Inicializa la partida
     */
    public void iniciarPartida(){
        int para = 0;
        // Mezclar
        Collections.shuffle(cartas);

        // Repartir
        for (Carta carta : cartas) {
            cartasUsuarios.get(para).add(carta);
            para++;
            if (para == MAX_JUGADORES) {
                para = 0;
            }
        }

        // Descartar cuartetos
        for (Integer clave : usuarios.keySet()) {
            List<Carta> cartasUsuario = cartasUsuarios.get(clave);
            List<Integer> numerosAQuitar = cartasUsuario.stream()
                .filter(carta -> numeroCartasMismoNumero(cartasUsuario, carta.getNumero()) == 4)
                .map(Carta::getNumero)
                .distinct()
                .collect(Collectors.toList());
        
            cartasUsuario.removeIf(carta -> numerosAQuitar.contains(carta.getNumero()));  
        }

        // Turno
        turno = 0;
        numeroActual = -1;
        cartasUltimaJugada = 0;
        cartasMesa = new ArrayList<>();
    }

    /**
     * Aniade un usuario al juego
     * @param usuario usuario a aniadir
     */
    public void nuevoUsuario(Usuario usuario){
        int numeroUsuarios = usuarios.size(); 
        if (numeroUsuarios < MAX_JUGADORES){
            usuarios.put(numeroUsuarios, usuario);
            cartasUsuarios.put(numeroUsuarios, new ArrayList<Carta>());
            if(usuarios.size() == numeroUsuarios){
                iniciarPartida();
            }
        } else{
            /**
             * TODO: lanzar error juego lleno
             */
        }
    }

    /**
     * 
     * @param ordenUsuario
     */
    private void ganador(int ordenUsuario){

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
     *       <li> El turno del usuario en el juego
     *       <li> Las cartas (en forma de string) del usuario
     *       </ul>
     *  <li> Las cartas (en forma de string) del usuario
     *  <li> El numero de cartas que de pusieron el ultimo turno
     *  <li> El numero actual de las cartas
     *  </ul>
     */
    @SuppressWarnings("unchecked")
    @Override
    public JSONObject guardar() {
        JSONObject estado = new JSONObject();
        JSONArray usuariosArray = new JSONArray();
        
        for (Integer clave : this.usuarios.keySet()) {
            JSONObject usuarioJSON = new JSONObject();
            usuarioJSON.put("ID", this.usuarios.get(clave).getID());
            usuarioJSON.put("turno_en_juego", clave);
            usuarioJSON.put("cartas", cartasToString(this.cartasUsuarios.get(clave)));
            usuariosArray.add(usuarioJSON);
        }
        estado.put("ID", this.id);
        estado.put("turno", this.turno);
        estado.put("usuarios", usuariosArray);
        estado.put("cartas_mesa", cartasToString(this.cartasMesa));
        estado.put("ultimas_cartas", this.cartasUltimaJugada);
        estado.put("numero_actual", this.numeroActual);

        return estado;
    }

    @Override
    public void cargar(JSONObject estado) {
        this.id = (String) estado.get("ID");
        this.turno = (Integer) estado.get("turno");
        this.cartasMesa = baraja.parsearCartas((String) estado.get("cartas_mesa"));
        this.cartasUltimaJugada = (Integer) estado.get("ultimas_cartas");
        this.numeroActual = (Integer) estado.get("numero_actual");
        JSONArray usuarioArray = (JSONArray)estado.get(usuarios);
        for (Object object : usuarioArray) {
            JSONObject infoUsuario = (JSONObject) object;
           
            //TODO: con un id de un usaurio, acceder al objeto del usuario
            String id = (String) infoUsuario.get("ID");
            int orden = (Integer) infoUsuario.get("turno_en_juego");
            String cartasString = (String) infoUsuario.get("cartas");

            this.usuarios.put(orden, null);
            this.cartasUsuarios.put(orden, baraja.parsearCartas(cartasString));
        }
    }

    // @Override
    // public JSONObject crearEstado(String estadoString) {
    //     // TO-DO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'recuperarEstado'");
    // }

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
}
