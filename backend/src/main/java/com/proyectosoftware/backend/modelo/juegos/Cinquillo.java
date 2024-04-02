package com.proyectosoftware.backend.modelo.juegos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    private Map<Integer, List<Carta>> manosUsuarios;
    private Map<String, List<Carta>> escaleras;

    private int turno = 0;
    private int primerJugador = -1;

    /**
     * Constructor por defecto
     */
    public Cinquillo() {
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
        Iterator<Carta> iter = manoPrimerJugador.iterator();
        while (iter.hasNext()) {
            carta = iter.next();

            if (carta.getNumero() == 5 && baraja.colorReal(carta.getColor()).equals(BarajaEspaniola.OROS)) {
                iter.remove();
                List<Carta> escaleraOros = escaleras.get(BarajaEspaniola.OROS);
                escaleraOros.add(carta);
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
     * 
     */
    public void jugada(Usuario usuario, Carta carta){
        int clave = -1;
        for (int i : usuarios.keySet()) {
            if(usuarios.get(i).getID().equals(usuario.getID())){
                clave = i;
            }
        }
        List<Carta> manoJugador = manosUsuarios.get(clave);
        Iterator<Carta> iterator = manoJugador.iterator();
        List<Carta> posiblesJugadas = new ArrayList<>();
    
        //  En caso de que el jugador tenga un 5 lo juega de manera obligatoria
        posiblesJugadas = jugarCinco(posiblesJugadas, iterator);
        if(posiblesJugadas.contains(carta)){
            //  Notificar a control y hacer jugada en la interfaz
            siguenteTurno();
        }

        //  Si no tiene un 5 y puede continuar una escalera decide cual continuar
        posiblesJugadas = jugarCarta(posiblesJugadas, manoJugador.iterator());
        if(posiblesJugadas.contains(carta)){
            //  Notificar a control y hacer jugada en la interfaz
            siguenteTurno();
        }

        //  Si no puede hacer nada termina su turno y pasa al siguiente
        siguenteTurno();
    }

    /**
     * Busca los 5 en la mano del usuario y devuelve una lista que contiene los 5 de la mano del usuario
     * @param posiblesJugadas - Lista con las posibles cartas a jugar
     * @param iterator - Cartas en la mano del usuario  
     * @return - Lista con las posibles cartas a jugar
     */
    private List<Carta> jugarCinco(List<Carta> posiblesJugadas, Iterator<Carta> iterator){
        while(iterator.hasNext()) {
            Carta carta = iterator.next();
            if(carta.getNumero() == 5){
                switch (baraja.colorReal(carta.getColor())) {
                    case BarajaEspaniola.COPAS:
                        posiblesJugadas.add(carta);

                        iterator.remove();
                        escaleras.get(BarajaEspaniola.COPAS).add(carta);
                        break;
                
                    case BarajaEspaniola.ESPADAS:
                        posiblesJugadas.add(carta);

                        iterator.remove();
                        escaleras.get(BarajaEspaniola.ESPADAS).add(carta);
                        break;

                    case BarajaEspaniola.BASTOS:
                        posiblesJugadas.add(carta);

                        iterator.remove();
                        escaleras.get(BarajaEspaniola.BASTOS).add(carta);
                        break;
                }
            }
        }

        return posiblesJugadas;
    }

    /**
     * Busca las cartas que pueden ser jugadas en base a las escaleras ya colocadas en la mesa
     * @param posiblesJugadas - Lista con las posibles cartas a jugar
     * @param iterator - Cartas en la mano del usuario 
     * @return - Lista con las posibles cartas a jugar
     */
    private List<Carta> jugarCarta(List<Carta> posiblesJugadas, Iterator<Carta> iterator){
        int color = 0;

        do{
            int ultimoIndice = escaleras.get(baraja.colorReal(color)).size();
            if(ultimoIndice != 0){
                Carta primeraCarta = escaleras.get(baraja.colorReal(color)).get(0);
                Carta ultimaCarta = escaleras.get(baraja.colorReal(color)).get(ultimoIndice - 1);

                while(iterator.hasNext()) {
                    Carta carta = iterator.next();

                    if(carta.getColor() == primeraCarta.getColor() && carta.getNumero() == primeraCarta.getNumero() - 1){
                        posiblesJugadas.add(carta);
                        
                        iterator.remove();
                        escaleras.get(baraja.colorReal(color)).add(carta);
                    }else if(carta.getColor() == ultimaCarta.getColor() && carta.getNumero() == ultimaCarta.getNumero() + 1){
                        posiblesJugadas.add(carta);

                        iterator.remove();
                        escaleras.get(baraja.colorReal(color)).add(carta);
                    }
                }
            }

            //  Se organizan las escaleras en funcion de los numeros para que solo sea necesario comprobar el primer y último elemento
            Collections.sort(escaleras.get(baraja.colorReal(color)), new Comparator<Carta>() {
                @Override
                public int compare(Carta carta1, Carta carta2) {
                    return Integer.compare(carta1.getNumero(), carta2.getNumero());
                }
            });
            color++;
        }while(color < 4);
        return posiblesJugadas;
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
            turno = primerJugador;
        }
    }
}
