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
    private int turno;
    private Map<Integer, Usuario> usuarios;
    private Map<Integer, List<Carta>> cartasUsuarios;
    private List<Carta> cartas;
    
    private int numeroActual;
    private int cartasUltimaJugada;
    private List<Carta> cartasMesa;

    /**
     * Prepara un nuevo juego
     */
    public Mentiroso(){
        this.baraja = BarajaEspaniola.devolverInstancia();
        this.usuarios = new HashMap<>(4);
        this.cartasUsuarios = new HashMap<>(4);
        this.cartas = baraja.devolverCartas();
    }

    /**
     * 
     * @param estado
     */
    public Mentiroso(Estado estado) {
        
    }

    /**
     * Jugada inicial sin cartas en la mesa
     * @param usuario   - El usuario que realiza la jugada. Debe represetnar el usuario del turno actual
     * @param cartas    - Cartas que se ponen en la mesa
     * @param numero    - Numero que se pone en la mesa
     */
    public void jugada(Usuario usuario, List<Carta> cartas, int numero){
        int clave = -1;
        for (int i : usuarios.keySet()) {
            if(usuarios.get(i).getID().equals(usuario.getID())){
                clave = i;
            }
        }
        List<Carta> cartasUsuario = cartasUsuarios.get(clave);

        for (Carta carta : cartas) {
            cartasUsuario.remove(carta);
            cartasMesa.add(carta);
        }

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
            int clave = -1;
            for (int i : usuarios.keySet()) {
                if(usuarios.get(i).getID().equals(usuario.getID())){
                    clave = i;
                }
            }
            List<Carta> cartasUsuario = cartasUsuarios.get(clave);

            if(accion == Accion.MENTIR){
                for (Carta carta : cartas) {
                    cartasUsuario.remove(carta);
                    cartasMesa.add(carta);
                }
                cartasUltimaJugada = cartas.size();
            }else if(accion == Accion.LEVANTAR){
                boolean miente = false;
                for(Carta carta: cartasMesa.subList(cartasMesa.size() - cartasUltimaJugada - 1, cartasMesa.size() - 1)){
                    if(carta.getNumero() != numeroActual){
                        miente = true;
                        break;
                    }
                }

                int usuarioPerdedor=-1;
                if (miente) {
                    usuarioPerdedor = turno - 1;
                    if(usuarioPerdedor > 0 ){
                        usuarioPerdedor = MAX_JUGADORES - 1;
                    }
                }else{
                    usuarioPerdedor = turno;
                    siguenteTurno();
                }

                cartasUsuarios.get(usuarioPerdedor).addAll(cartasMesa);
                cartasMesa.clear();
                numeroActual = -1;
                cartasUltimaJugada = 0;
            }
        }
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

        // Turno
        turno = 0;
        numeroActual = -1;
        cartasUltimaJugada = 0;
        cartasMesa = new ArrayList<>();
    }

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
