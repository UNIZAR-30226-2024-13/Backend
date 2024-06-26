package com.proyectosoftware.backend.modelo.juegos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.proyectosoftware.backend.modelo.Carta;
import com.proyectosoftware.backend.modelo.Usuario;
import com.proyectosoftware.backend.modelo.barajas.BarajaEspaniola;
import com.proyectosoftware.backend.modelo.interfaces.Baraja;
import com.proyectosoftware.backend.modelo.interfaces.JuegoSinApuesta;

/**
 * Juego del chinquillo
 */
public class Cinquillo implements JuegoSinApuesta{
    public static final int MAX_USUARIOS = 4;

    private Baraja baraja;
    private List<Carta> mazo;
    private Map<Integer, String> usuarios; 
    private Map<Integer, List<Carta>> manosUsuarios;
    private Map<String, List<Carta>> escaleras;

    private int turno = 0;
    private int primerJugador = -1;
    private String id;

    /**
     * Constructor por defecto
     */
    public Cinquillo() {
        id = this.generateID();
        baraja = BarajaEspaniola.devolverInstancia();
        mazo = baraja.devolverCartas();
        usuarios = new HashMap<>(MAX_USUARIOS);
        manosUsuarios = new HashMap<>(MAX_USUARIOS);
        escaleras = new HashMap<>(4);

        escaleras.put(BarajaEspaniola.OROS, new ArrayList<>());
        escaleras.put(BarajaEspaniola.COPAS, new ArrayList<>());
        escaleras.put(BarajaEspaniola.ESPADAS, new ArrayList<>());
        escaleras.put(BarajaEspaniola.BASTOS, new ArrayList<>());
    }

    /**
     * Cargar un juego de cinquillo dado un estado
     * @param estado - Juego almacenado en la Base de Datos
     */
    public Cinquillo(JSONObject estado){
        baraja = BarajaEspaniola.devolverInstancia();
        usuarios = new HashMap<>(MAX_USUARIOS);
        manosUsuarios = new HashMap<>(MAX_USUARIOS);
        escaleras = new HashMap<>(4);
        this.cargar(estado);
    }

    /**
     * Añade un jugador a la partida si esta no esta completa
     * @param usuario - Jugador a añadir en la partida
     */
    public void nuevoUsuario(String idUsuario){
        int numeroUsuarios = usuarios.size(); 

        if (numeroUsuarios < MAX_USUARIOS){
            usuarios.put(numeroUsuarios, idUsuario);
        }
    }

    /**
     * Reparto de cartas entre los usuarios y busqueda del 5 de Oros para iniciar la partida
     * 
     */
    public void iniciarPartida(){
        int jugador = 0; 
        int index = 0;
        List<Carta> mano = new ArrayList<>();
        Carta carta;
        
        //  Repartir las cartas y buscar el 5 de oros
        Collections.shuffle(mazo);
        do {
            do{
                carta = new Carta(mazo.get(index).getNumero(), mazo.get(index).getColor());
                index++;

                if (carta.getNumero() == 5 && baraja.colorReal(carta.getColor()).equals(BarajaEspaniola.OROS)) {
                    primerJugador = jugador;
                    turno = primerJugador;
                }
                mano.add(carta);
            }while(mano.size() < 10);
            
            manosUsuarios.put(jugador++, new ArrayList<>(mano));
            mano.clear();
        } while (jugador < MAX_USUARIOS);
        

        //  Modificar la mano del usuario con el 5 de oros y la escalera correspondiente
        List<Carta> manoPrimerJugador = manosUsuarios.get(primerJugador);
        for (Carta cartaJugada : manoPrimerJugador) {
            if (cartaJugada.getNumero() == 5 && baraja.colorReal(cartaJugada.getColor()).equals(BarajaEspaniola.OROS)) {
                manoPrimerJugador.remove(cartaJugada);
                List<Carta> escaleraOros = escaleras.get(BarajaEspaniola.OROS);
                escaleraOros.add(cartaJugada);
                break;
            }
        }
        siguenteTurno();
    }

    /**
     * Jugada normal del Cinquillo, 
     * el usuario tiene que jugar un 5 si lo tiene para empezar una nueva escalera, 
     * sino continuar las que ya estan en la mesa, 
     * sino saltar turno
     * @param usuario - Representa el jugador con el turno actual 
     * @param carta - La carta que el jugador quiere utilizar
     */
    public void jugada(Usuario usuario, Carta carta){
        int clave = -1;
        for (int i : usuarios.keySet()) {
            if(usuarios.get(i).equals(usuario.getID())){
                clave = i;
            }
        }
        if(carta != null){
            List<Carta> manoJugador = manosUsuarios.get(clave);
        
            //  En caso de que el jugador tenga un 5 lo juega de manera obligatoria
            if(carta.getNumero() != 5 && buscarCinco(manoJugador)){
                /**
                * Error el usuario tiene que jugar el cinco que tiene en la mano
                */
            }else{
                int color = 0;
                //  Notificar a control y hacer jugada en la interfaz
                manoJugador.remove(carta);
                escaleras.get(baraja.colorReal(carta.getColor())).add(carta);

                do{
                    //  Se organizan las escaleras en funcion de los numeros de las cartas
                    Collections.sort(escaleras.get(baraja.colorReal(color)), new Comparator<Carta>() {
                        @Override
                        public int compare(Carta carta1, Carta carta2) {
                            return Integer.compare(carta1.getNumero(), carta2.getNumero());
                        }
                    });
                }while(color++ < 4);
                siguenteTurno();
            }
        }

        //  Si no puede hacer nada termina su turno y pasa al siguiente
        siguenteTurno();
    }

    /**
     * Busca los 5 en la mano del usuario y devuelve verdadero en caso de encontrar uno
     * @param manoJugador - Cartas en la mano del usuario  
     * @return - True si la mano contiene un 5, Fale si no contiene un 5
     */
    private boolean buscarCinco(List<Carta> manoJugador){
        for (Carta carta : manoJugador) {
            if(carta.getNumero() == 5){
                return true;
            }
        }
        return false;
    }

    /**
     * Devuelve un string representando las cartas de una lista dada
     * @param cartas    - Lista de cartas
     * @return La representacion en string
     */
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
     *  <li> El primer jugador
     *  <li> Una lista de los usuario que contine en cada campo: 
     *       <ul>
     *       <li> El id del usuario
     *       <li> El turno del usuario en el juego
     *       <li> Las cartas (en forma de string) del usuario
     *       </ul>
     *  <li> Una lista de las escaleras que contine en cada campo: 
     *       <ul>
     *       <li> El palo de la escalera
     *       <li> Las cartas (en forma de string) colocadas en la escalera
     *       </ul>
     *  </ul>
     */
    @SuppressWarnings("unchecked")
    @Override
    public JSONObject guardar() {
        JSONObject estado = new JSONObject();
        JSONArray usuariosArray = new JSONArray();
        JSONArray escalerasArray = new JSONArray();
        
        for (Integer clave : this.usuarios.keySet()) {
            JSONObject usuarioJSON = new JSONObject();
            usuarioJSON.put("ID", this.usuarios.get(clave));
            usuarioJSON.put("turno_en_juego", clave);
            usuarioJSON.put("cartas", cartasToString(this.manosUsuarios.get(clave)));
            usuariosArray.add(usuarioJSON);
        }

        for (String clave : escaleras.keySet()) {
            JSONObject escaleraJSON = new JSONObject();
            escaleraJSON.put("palo", clave);
            escaleraJSON.put("cartas", cartasToString(this.escaleras.get(clave)));
            escalerasArray.add(escaleraJSON);
        }
        estado.put("ID", this.id);
        estado.put("turno", this.turno);
        estado.put("primer_jugador", primerJugador);
        estado.put("usuarios", usuariosArray);
        estado.put("escaleras", escalerasArray);

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
     *  <li> Una lista de los usuario que contine en cada campo: 
     *       <ul>
     *       <li> El id del usuario
     *       <li> El turno del usuario en el juego
     *       <li> Las cartas (en forma de string) del usuario
     *       </ul>
     *  <li> Una lista de las escaleras que contine en cada campo: 
     *       <ul>
     *       <li> El palo de la escalera
     *       <li> Las cartas (en forma de string) colocadas en la escalera
     *       </ul>
     *  </ul>
     */
    @Override
    public void cargar(JSONObject estado) {
        this.id = (String) estado.get("ID");
        this.turno = (Integer) estado.get("turno");
        this.primerJugador = (Integer) estado.get("primer_jugador");

        JSONArray usuarioArray = (JSONArray)estado.get("usuarios");
        for (Object object : usuarioArray) {
            JSONObject infoUsuario = (JSONObject) object;
           
            //TODO: con un id de un usaurio, acceder al objeto del usuario
            String id = (String) infoUsuario.get("ID");
            int orden = (Integer) infoUsuario.get("turno_en_juego");
            String cartasString = (String) infoUsuario.get("cartas");

            this.usuarios.put(orden, null);
            this.manosUsuarios.put(orden, baraja.parsearCartas(cartasString));
        }

        JSONArray escaleraArray = (JSONArray)estado.get("escaleras");
        for (Object object : escaleraArray) {
            JSONObject infoEscalera = (JSONObject) object;
            String palo = (String) infoEscalera.get("palo");
            String cartasString = (String) infoEscalera.get("cartas");
            if(cartasString != null){
                this.escaleras.put(palo, baraja.parsearCartas(cartasString));
            }
        }
    }

    @Override
    public void siguenteTurno() {
        turno++;
        if(turno == MAX_USUARIOS){
            turno = primerJugador;
        }
    }
}
