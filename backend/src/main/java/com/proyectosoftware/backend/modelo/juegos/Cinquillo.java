package com.proyectosoftware.backend.modelo.juegos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
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
    public static final int MAX_USUARIOS = 4;

    private Baraja baraja;
    private List<Carta> mazo;
    private Map<Integer, Usuario> usuarios; 
    private Map<Integer, List<Carta>> manoUsuarios;
    private Map<String, List<Carta>> escaleras;

    private int turno = 0;

    /**
     * Constructor por defecto
     */
    public Cinquillo() {
        baraja = BarajaEspaniola.devolverInstancia();
        mazo = baraja.devolverCartas();
        usuarios = new HashMap<>(MAX_USUARIOS);
        manoUsuarios = new HashMap<>(MAX_USUARIOS);
        escaleras = new HashMap<>(4);

        escaleras.put(BarajaEspaniola.OROS, new ArrayList<>());
        escaleras.put(BarajaEspaniola.COPAS, new ArrayList<>());
        escaleras.put(BarajaEspaniola.ESPADAS, new ArrayList<>());
        escaleras.put(BarajaEspaniola.BASTOS, new ArrayList<>());
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
        Carta carta;
        
        //  Repartir las cartas y buscar el 5 de oros
        Collections.shuffle(mazo);
        do {
            do{
                carta = new Carta(mazo.get(index).getNumero(), mazo.get(index).getColor());
                index++;

                if (carta.getNumero() == 5 && BarajaEspaniola.OROS.equals(baraja.colorReal(carta.getColor()))) {
                    primerJugador = jugador;
                }
                mano.add(carta);
            }while(mano.size() < 10);
            
            manoUsuarios.put(jugador++, new ArrayList<>(mano));
            mano.clear();
        } while (jugador < MAX_USUARIOS);
        System.out.println(manoUsuarios);
        

        //  Modificar la mano del usuario con el 5 de oros y la escalera correspondiente
        List<Carta> manoPrimerJugador = manoUsuarios.get(primerJugador);
        Iterator<Carta> iter = manoPrimerJugador.iterator();
        while (iter.hasNext()) {
            carta = iter.next();

            if (carta.getNumero() == 5 && BarajaEspaniola.OROS.equals(baraja.colorReal(carta.getColor()))) {
                iter.remove();
                List<Carta> escaleraOros = escaleras.get(BarajaEspaniola.OROS);
                escaleraOros.add(carta);
                break;
            }
        }
        siguenteTurno();
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
        turno++;
        if(turno == MAX_USUARIOS){
            turno = 0;
        }
    }
}
