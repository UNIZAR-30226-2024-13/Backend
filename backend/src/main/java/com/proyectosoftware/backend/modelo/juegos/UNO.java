package com.proyectosoftware.backend.modelo.juegos;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.proyectosoftware.backend.modelo.Carta;
import com.proyectosoftware.backend.modelo.Usuario;
import com.proyectosoftware.backend.modelo.barajas.BarajaUNO;
import com.proyectosoftware.backend.modelo.interfaces.Baraja;
import com.proyectosoftware.backend.modelo.interfaces.Estado;
import com.proyectosoftware.backend.modelo.interfaces.JuegoSinApuesta;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;


/**
 * Juego del uno
 */
public class UNO implements JuegoSinApuesta{
    public static final int MAX_USUARIOS = 4;

    private Baraja baraja;
    private List <Carta> mazo;
    private List <Carta> ultimaCarta;
    private Map<Integer, Usuario> usuarios; 
    private Map<Integer, List<Carta>> manoUsuarios;

    private int sentido = 0;
    private int turno = 0;
    private int masdos = 0;
    private int mascuatro = 0;
    private int primerJugador = 0;
    private String id;
    

    /**
     * Constructor por defecto
     */
    public UNO(){
       baraja = BarajaUNO.devolverInstancia();
       mazo = baraja.devolverCartas();
       usuarios = new HashMap<>(MAX_USUARIOS);
       manoUsuarios = new HashMap<>(MAX_USUARIOS);
       ultimaCarta = new ArrayList<>();
    }


    /**
     * Cargar un juego de UNO dado un estado
     * @param estado - Juego almacenado en la Base de Datos
     */
    public UNO(JSONObject estado){
        this.baraja = BarajaUNO.devolverInstancia();
        this.cargar(estado);
    }

    /**
     * Añade un jugador a la partida si esta no esta completa
     * @param usuario - Jugador a añadir en la partida
     */
    public void nuevoUsuario(Usuario usuario){
        int numeroUsuarios = usuarios.size(); 

        if (numeroUsuarios < MAX_USUARIOS){
            usuarios.put(numeroUsuarios, usuario);
        }
    }

    
     /**
     * Reparto de cartas entre los usuarios y se empieza la partida.
     * 
     */
    public void iniciarPartida(){
        int jugador = 0; 
        List<Carta> mano = new ArrayList<>();
        Carta carta;
        
        //  Repartir las cartas
        Collections.shuffle(mazo);
        do {
            do{
                carta = new Carta(mazo.get(0).getNumero(), mazo.get(0).getColor());
                mazo.remove(0);
                mano.add(carta);
            }while(mano.size() < 7);
            
            manoUsuarios.put(jugador++, new ArrayList<>(mano));
            mano.clear();
        } while (jugador < MAX_USUARIOS);
        carta = new Carta(mazo.get(0).getNumero(), mazo.get(0).getColor());
        mazo.remove(0);
        ultimaCarta.add(carta);
    }
    
    
    /**
     * {@inheritDoc}
     * @implSpec
     *  Se guardara:
     *  <ul>
     *  <li> El ID del juego
     *  <li> El turno
     *  <li> El primer jugador
     *  <li> Una lista de los usuarios que contine en cada campo: 
     *       <ul>
     *       <li> El id del usuario
     *       <li> El turno del usuario en el juego
     *       <li> Las cartas (en forma de string) del usuario
     *       </ul>
     *  <li> Cartas que han sido jugadas en la partida
     *  <li> Sentido de la partida
     *  <li> Indicador de robar 2 cartas
     *  <li> Indicador de robar 4 cartas
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
            usuarioJSON.put("cartas", cartasToString(this.manoUsuarios.get(clave)));
            usuariosArray.add(usuarioJSON);
        }
        estado.put("ultima_carta", cartasToString(ultimaCarta));
        estado.put("sentido", sentido);
        estado.put("ID", this.id);
        estado.put("turno", this.turno);
        estado.put("primer_jugador", primerJugador);
        estado.put("usuarios", usuariosArray);

        return estado;
    }

    /**
     * {@inheritDoc}
     * @implSpec
     *  El objeto Json debe contener:
     *  <ul>
     *  <li> El ID del juego
     *  <li> El turno
     *  <li> El primer jugador
     *  <li> Una lista de los usuarios que contine en cada campo: 
     *       <ul>
     *       <li> El id del usuario
     *       <li> El turno del usuario en el juego
     *       <li> Las cartas (en forma de string) del usuario
     *       </ul>
     *  <li> Cartas que han sido jugadas en la partida
     *  <li> Sentido de la partida
     *  <li> Indicador de robar 2 cartas
     *  <li> Indicador de robar 4 cartas
     *  </ul>
     */
    @Override
    public void cargar(JSONObject estado) {
        this.id = (String) estado.get("ID");
        this.turno = (Integer) estado.get("turno");
        this.primerJugador = (Integer) estado.get("primer_jugador");
        String ultCarta = (String) estado.get("ultima_carta");
        this.sentido = (Integer) estado.get("sentido");
        this.ultimaCarta = baraja.parsearCartas(ultCarta);

        JSONArray usuarioArray = (JSONArray)estado.get(usuarios);
        for (Object object : usuarioArray) {
            JSONObject infoUsuario = (JSONObject) object;
           
            //TODO: con un id de un usaurio, acceder al objeto del usuario
            String id = (String) infoUsuario.get("ID");
            int orden = (Integer) infoUsuario.get("turno_en_juego");
            String cartasString = (String) infoUsuario.get("cartas");

            this.usuarios.put(orden, null);
            this.manoUsuarios.put(orden, baraja.parsearCartas(cartasString));
        }

    }

    /*@Override
    public Estado recuperarEstado(String estadoString) {
        throw new UnsupportedOperationException("Unimplemented method 'recuperarEstado'");
    }

    @Override
    public String crearEstado(Estado estado) {
        throw new UnsupportedOperationException("Unimplemented method 'crearEstado'");
    }*/


    @Override
    public void siguenteTurno() {
        if(sentido == 0){
           turno++;
            if(turno == MAX_USUARIOS){
                turno = 0;
            }
        }
        else if(sentido == 1){
            turno--;
            if(turno == -1){
                turno = MAX_USUARIOS - 1;
            }
        }

    }
     

    /** 
     * Jugada normal del UNO,
     * el usuario puede elegir entre jugar una carta o pasar turno voluntariamente
     * siempre y cuando no haya ningun indicador de robar carta activado.
     * En caso de que no existe ninguna carta valida en la mano, el jugador solo tendra la opcion de pasar turno.
     * Pasar turno incluye robar una carta del mazo.
     * @param usuario - Representa el jugador con el turno actual 
     * @param carta - La carta que el jugador quiere jugar
     * @param color - Color al que el jugador quiere cambiar
     */
    public void jugada(Usuario usuario, Carta carta, int color){
        int clave = -1;
        for (int i : usuarios.keySet()) {
            if(usuarios.get(i).getID().equals(usuario.getID())){
                clave = i;
            }
        }
        List<Carta> manoJugador = manoUsuarios.get(clave);
        if (cartaValida(carta)) {
            Carta c;
            int indice = 0;
            for(int i = 0; i < manoJugador.size()- 1; i++){
                if(carta == manoJugador.get(i)){
                    indice = i; //guarda el indice de la carta en la mano
                }
            }
            if (carta == null){
                //roba carta
                robaCarta(manoJugador, 1);
                this.siguenteTurno();
            }
            else {
                int n = carta.getNumero();
                switch (n) {
                    case 11:
                        cambioSentido();
                        //cambia el sentido del juego
                        break;
                    case 12:
                        sumaDos();
                        //+2 para el siguiente
                        break;
                    case 13:
                        saltoTurno();
                        //salta el turno del siguiente
                        break;
                    case 14:
                        c = new Carta(n,color);
                        carta = c;
                        //cambia el color al elegido por el usuario
                        break;
                    case 15:
                        sumaCuatro();
                        c = new Carta(n,color);
                        carta = c;
                        //+4 para el siguiente
                        break;
                    default:
                        //carta normal
                        break;
                }
                ultimaCarta.add(carta);
                manoJugador.remove(indice);
                if(manoJugador.isEmpty()){
                    ganadorPartida(usuarios.get(clave));
                }
                else{
                    this.siguenteTurno();
                    manoUsuarios.put(clave, manoJugador);
                }
            }
        }
    }
    
    /** 
     * Comprueba si la carta elegida por el usuario es valida para jugar
     * @param carta - La carta que el jugador quiere jugar
     * @return boolean - Devuelve true si es correcta para jugar
     */
    public boolean cartaValida(Carta carta){
        boolean valido = false;
        Carta ultCarta = ultimaCarta.get(ultimaCarta.size() - 1);
        // Obtenemos la última carta
        int color = ultCarta.getColor();
        int numero = ultCarta.getNumero();
        if(carta == null) {
            valido = true;
        }
        else if(carta.getNumero() == 14 || carta.getNumero() == 15){
            valido = true;
        }
        else if (carta.getNumero() < 11){
            if (carta.getColor() == color || carta.getNumero() == numero){
                valido = true;
            }
        }
        else {
            if (carta.getColor() == color){
                valido = true;
            }
        }
        return valido;
    }
    
    /**
     * Cambia el sentido de la partida
     */ 
    public void cambioSentido(){
        if (sentido == 0){
            sentido = 1;
        }
        else {
            sentido = 0;
        }
    }
    
     /**
      * El siguiente jugador tiene que robar 2 cartas del mazo.
      */ 
    public void sumaDos(){
        this.siguenteTurno();
        List <Carta> manJugador = manoUsuarios.get(turno);
        robaCarta(manJugador,2);
    }
    
   
    
    /** 
     * Añade n cartas a la mano del usuario y pasa el turno.
     * @param mano - Representa las cartas que tiene el usuario
     * @param n - Representa el numero de cartas que tiene que robar el usuario.
     */
    public void robaCarta(List<Carta> mano, int n){
        Carta carta;
        for(int i = 0; i < n; i++){
            carta = new Carta(mazo.get(0).getNumero(), mazo.get(0).getColor());
            mazo.remove(0);
            if (n == 1 && cartaValida(carta)){
                ultimaCarta.add(carta);
            }
            else {
                mano.add(carta);
            }
        }
        manoUsuarios.put(turno, mano);
    }
    
    /**
     * Pasa el turno
     */ 
    public void saltoTurno(){
        this.siguenteTurno();
    }
    
    /**
     * El siguiente jugador tiene que robar 4 cartas del mazo.
     */ 
    public void sumaCuatro(){
        this.siguenteTurno();
        List <Carta> manJugador = manoUsuarios.get(turno);
        robaCarta(manJugador,4);
    }
    
      
    /** 
     * Devuelve el string del usuario ganador
     * @param u - Representa el usuario que ha ganado
     * @return String - Devuelve la cadena correspondiente al usuario ganador
     */
    public String ganadorPartida(Usuario u){
        return u.generateID();
    }

    /**
     * Devuelve un string representando las cartas de una lista dada
     * @param cartas    - Lista de cartas
     * @return La representacion en string
     */
    private String cartasToString(List<Carta> cartas){
        return String.join(";", cartas.stream().map(Carta::toString).toList());
    }
}