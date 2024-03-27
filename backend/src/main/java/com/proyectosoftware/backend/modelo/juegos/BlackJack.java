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
    private List<Carta> cartas_banca;
    private List<Carta> mazo;
    private Baraja baraja;
    private int apuesta_mesa;
    private Map<String, Integer> fichas_usuario;        // Diccionario con los usuarios y sus fichas a usar en la partida
    private Map<String, List<Carta>> cartas_usuario;    // Diccionario con los usuarios y sus cartas a usar en la partida
    private Map<String, Boolean> plantado;              // Diccionario con los usuarios y si se han plantado o no (inicializar a false)
    private List<Carta> cartas_croupier;                // Lista de cartas del croupier (Carta de índice 0 está boca arriba y la otra boca abajo)

    /*
     * Constructor por defecto
    */
    public BlackJack() {
        baraja = BarajaFrancesa.devolverInstancia();
        mazo = baraja.devolverCartas();
        apuesta_mesa = 0;
        fichas_usuario = new HashMap<>();
        cartas_usuario = new HashMap<>();
        plantado = new HashMap<>();
        cartas_croupier = new ArrayList<>();
    }


    /*
     * Cargar un juego de blackjack dado un estado
     * @param estado
    */
    public BlackJack(Estado estado) {

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
     * Apuesta que un usuario realiza
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
            fichas_usuario.put(usuario.getId(),fichas_disponibles);
            apuesta_mesa += apuesta;
            //Mandar al control las fichas disponibles
        }
    }


    /*
     * Genera un estado a partir de un string
     * @param estadoString - String a parsear
     * @return - Estado
    */
    public Estado recuperarEstado(String estadoString) {

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
        plantados.put(usuario.getId(), true);
    }
    

    /**
     * Devuelve el valor de una carta dentro del Blackjack
     * @param carta - Carta a evaluar
     * @return valor - Valor de la carta
    */
    public int valorCarta(Carta carta) {
        int numero = carta.getNumero();
        return (numero == 1) ? 11 : carta;      // Si la carta es un as (valor 1), devuelve 11, sino su valor normal
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
     * El croupier juega su turno después de que
     * los jugadores hayan jugado sus turnos.
     * La funcion acaba con la situación final de las cartas del croupier
    */
    public void turnoCroupier() {
        // Se revela la segunda carta del croupier (vista)
        int suma_croupier = valorMano(cartas_croupier);
        while (suma_croupier <= 16) {
            Carta carta_sacada = mazo.get(0);
            mazo.remove(0);                                         // Se saca una carta y se quita del mazo
            cartas_croupier.add(carta_sacada);
            suma_croupier = valorMano(cartas_croupier);
        }
    }


    /**
     * Aumenta el numero de fichas de un usuario
     * @param usaurio   - Usuario que va a aumentar sus fichas
     * @param fichas   - Cantidad de fichas
    */
    public void sumarFichas(Usuario usuario, int fichas) {
        int fichas_nuevas = fichas_usuario.get(usuario.getId()) + fichas;
        fichas_usuario.put(usuario.getId(), fichas_nuevas)
    }


    /**
     * Resta el numero de fichas de un usuario
     * @param usaurio   - Usuario que va a perder fichas
     * @param apuesta   - Cantidad de fichas
    */
    public void restarFichas(Usuario usuario, int fichas) {
        sumarFichas(usuario, -fichas);
    }
    

    /*
     * 
     * @param estado
     * @return
    */
    public String crearEstado(Estado estado) {
        
    }


    /**
     * Se reparten las dos cartas iniciales a cada jugador
     * @param usuarios
    */
    public void repartirCartas(List<Usuario> usuarios) {
        Collections.shuffle(mazo);
        List<Carta> cartas;
        for (int i = 0; i < usuarios.size(); i++) {     // Itera sobre cada usuario
            cartas = new ArrayList<>();
            for (int j = 0; j < 2; j++) {               // A cada usuario se le reparten dos cartas
                cartas.add(mazo.get(0));                // Se saca una carta y se añade al array
                mazo.remove(0);
            }
            cartas_usuario.put(usuarios.get(i).getId(), cartas.clone());
        }
        for (int i = 0; i < 2; i++) {                   // Reparto de cartas al croupier
            cartas_croupier.add(mazo.get(0));
            mazo.remove(0);
        }
    }
}
