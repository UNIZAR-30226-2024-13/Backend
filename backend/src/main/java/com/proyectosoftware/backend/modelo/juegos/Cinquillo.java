package com.proyectosoftware.backend.modelo.juegos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.proyectosoftware.backend.modelo.Carta;
import com.proyectosoftware.backend.modelo.Usuario;
import com.proyectosoftware.backend.modelo.barajas.BarajaEspaniola;
import com.proyectosoftware.backend.modelo.interfaces.Baraja;
import com.proyectosoftware.backend.modelo.interfaces.Estado;
import com.proyectosoftware.backend.modelo.interfaces.JuegoSinApuesta;

/**
 * Juego del chinquillo
 */
public class Cinquillo implements JuegoSinApuesta{
    private Baraja baraja;
    private List<Carta> mazo;
    private Map<Integer, Usuario> usuarios; 
    private Map<Integer, List<Carta>> manoUsuarios;
    private Map<Integer, List<Carta>> escaleras;

    /**
     * Constructor por defecto
     */
    public Cinquillo() {
        baraja = BarajaEspaniola.devolverInstancia();
        mazo = baraja.devolverCartas();
        usuarios = new HashMap<>(4);
        manoUsuarios = new HashMap<>(4);
        escaleras = new HashMap<>(4);
    }

    /**
     * Reparto de cartas entre los usuarios y busqueda del 5 de Oros para iniciar la partida
     * 
     */
    public void iniciarPartida(){
        int jugador = 0; 
        int index = 0;
        int primerJugador = 0;
        List<Carta> mano = new ArrayList<>();
        
        Collections.shuffle(mazo);
        do {
            do{
                Carta carta = new Carta(mazo.get(index).getNumero(), mazo.get(index).getColor());
                index++;

                if (carta.getNumero() == 5 && carta.getColor() == BarajaEspaniola.OROS) {
                    primerJugador = jugador;
                }
                mano.add(carta);
            }while(mano.size() < 10);
            
            manoUsuarios.put(jugador++, new ArrayList<>(mano));
            mano.clear();
        } while (jugador < 4);
        
        /**
         * TODO
         * Elimina el 5 de Oros de la mano del primer jugador
         * lo añade a la escalera correspondiente
         * inicializa le resto de escaleras 
         */
        manoUsuarios.remove(0,5);

        escaleras.put(BarajaEspaniola.OROS, );
        escaleras.put(BarajaEspaniola.COPAS, );
        escaleras.put(BarajaEspaniola.ESPADAS, );
        escaleras.put(BarajaEspaniola.BASTOS, );
    }

    /**
     * Cargar un juego de cinquillo dado un estado
     * @param estado
     */
    public Cinquillo(Estado estado){

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
}
