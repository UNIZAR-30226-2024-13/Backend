package com.proyectosoftware.backend.modelo.juegos;

import java.util.List;
import java.util.HashMap;

import com.proyectosoftware.backend.modelo.Carta;
import com.proyectosoftware.backend.modelo.Partida;
import com.proyectosoftware.backend.modelo.Usuario;
import com.proyectosoftware.backend.modelo.barajas.BarajaFrancesa;
import com.proyectosoftware.backend.modelo.interfaces.Baraja;
import com.proyectosoftware.backend.modelo.interfaces.Estado;
import com.proyectosoftware.backend.modelo.interfaces.JuegoConApuesta;

/*
 * Juego del blackjack
*/
public class BlackJack implements JuegoConApuesta, Estado {

    private enum Accion {
        PEDIR_CARTA,
        PLANTARSE
    }

    private static final int MAX_JUGADORES = 4;

    private List<Carta> cartas_banca;
    private List<Carta> mazo;
    private Baraja baraja;
    private int turno;

    private Map<String, Integer> fichas_usuario;       // Diccionario con los usuarios y sus fichas a usar en la partida
    private Map<String, List<Carta>> cartas_usuario;    // Diccionario con los usuarios y sus cartas a usar en la partida
    private Map<String, Boolean> plantado;             // Diccionario con los usuarios y si se han plantado o no (inicializar a false)
    private List<Carta> cartas_croupier;                // Lista de cartas del croupier (Carta de índice 0 está boca arriba y la otra boca abajo)
    private Map<String, Integer> apuesta_usuario;      // Diccionario con los usuarios y su apuesta en la partida
    private Map<String, Usuario> usuarios;
    private Map<String, Boolean> apuesta_plus;

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
        usuarios = new HashMap<>();
        apuesta_plus = new HashMap<>();
    }


    /**
     * Cargar un juego de blackjack dado un estado
     * @param estado
    */
    public BlackJack(Estado estado) {

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
        if (accion == PEDIR_CARTA) {
            if (!jugadorPlantado(usuario)) {
                pedirCarta(usuario);
                if (valorMano(cartas_usuario.get(usuario.getId())) >= 21) { // Si el usuario llega o se pasa de 21, se retira de la partida
                    plantarse(usuario);
                    siguienteTurno();
                }
            }
            else {
                // Lanzar error, jugador plantado y está pidiendo carta
            }
        }
        else { // accion == PLANTARSE
            if (!jugadorPlantado(usuario)) {
                plantarse(usuario);
                siguienteTurno();
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
        List<Usuario> usuarios_ganadores;
        turnoCroupier();
        usuarios_ganadores = comprobarGanadores();
    }


    public void nuevoUsuario(Usuario usuario) {
        int numeroUsuarios = usuarios.size(); 
        if (numeroUsuarios < MAX_JUGADORES) {
            usuarios.put(usuario.getId(), usuario);
            cartas_usuario.put(usuario.getId(), new ArrayList<Carta>());
            if (usuarios.size() == MAX_JUGADORES) {
                iniciarPartida();
            }
        }
        else {
            // Lanzar error, tope de jugadores alcanzado
        }
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
    public void siguienteTurno() {
        turno++;
    }


    /**
     * Apuesta inicial que un jugador realiza
     * @param usaurio   - Usuario que realiza la apuesta
     * @param apuesta   - Valor de la apuesta
     */
    @Override
    public void apostar(Usuario usaurio, int apuesta) {
        int fichas_disponibles = fichas_usuario.get(usuario.getId());
        if (apuesta > fichas_disponibles) {
            //Mandar error al control y esperar a nueva apuesta
        }
        else if (apuesta < ultima_apuesta) {
            //Mandar error al control y esperar a nueva apuesta
        }
        else {
            fichas_disponibles -= apuesta;
            fichas_usuario.put(usuario.getId(), fichas_disponibles);
            apuesta_usuario.put(usuario.getId(), apuesta);
            //Mandar al control las fichas disponibles
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
        plantados.put(usuario.getId(), true);   // Pone a true su booleano de plantado
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
        for (carta : cartas) {
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
        return plantados.get(usuario.getId());
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
     * Aumenta el numero de fichas de un usuario
     * @param usaurio  - Usuario que va a aumentar sus fichas
     * @param fichas   - Cantidad de fichas a sumar
    */
    public void sumarFichas(Usuario usuario, int fichas) {
        int fichas_nuevas = fichas_usuario.get(usuario.getId()) + fichas;
        fichas_usuario.put(usuario.getId(), fichas_nuevas);
    }
    

    /**
     * Se reparten las dos cartas iniciales a cada jugador
    */
    public void repartirCartas() {
        List<Carta> cartas;
        for (int i = 0; i < usuarios.size(); i++) {     // Itera sobre cada usuario
            cartas = new ArrayList<>();
            for (int j = 0; j < 2; j++) {               // A cada usuario se le reparten dos cartas
                cartas.add(mazo.get(0));                // Se saca una carta y se añade al array
                mazo.remove(0);
            }
            String usuario = usuarios.get(i).getId();
            cartas_usuario.put(usuario, cartas.clone());
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
    public List<Usuario> comprobarGanadores() {
        int cuenta_croupier = valorMano(cartas_croupier);
        List<Usuario> usuarios_ganadores = new ArrayList<>();

        for (String usuario : cartas_usuario.keySet()) {
            List<Carta> cartas = cartas_usuario.get(usuario);

            int cuenta = valorMano(cartas);
            int apuesta_realizada_usuario = apuesta_usuario.get(usuario.getId());
            int fichas_del_usuario = fichas_usuario.get(usuario.getId());

            int nuevas_fichas;
            if (cuenta > 21) {      // Si un jugador se pasa de 21, pierde independientemente de las cartas del croupier
                // usuario pierde
            }
            else if (cuenta_croupier > 21) {
                if (!apuesta_plus.get(usuario.getId()))
                    fichas_usuario.put(usuario.getId(), fichas_del_usuario + 2*(apuesta_realizada_usuario));
                else
                    fichas_usuario.put(usuario.getId(), fichas_del_usuario + 2*(apuesta_realizada_usuario) + (apuesta_realizada_usuario/2));
                usuarios_ganadores.add(usuarios.get(usuario));
                // usuario gana
            }
            else if (cuenta <= 21 && cuenta > cuenta_croupier) {
                if (!apuesta_plus.get(usuario.getId()))
                    fichas_usuario.put(usuario.getId(), fichas_del_usuario + 2*(apuesta_realizada_usuario));
                else
                    fichas_usuario.put(usuario.getId(), fichas_del_usuario + 2*(apuesta_realizada_usuario) + (apuesta_realizada_usuario/2));
                usuarios_ganadores.add(usuarios.get(usuario));
                // usuario gana
            }
            else if (cuenta <= 21 && cuenta < cuenta_croupier) {
                // usuario pierde
            }
        }
        return usuarios_ganadores;
    }
}
