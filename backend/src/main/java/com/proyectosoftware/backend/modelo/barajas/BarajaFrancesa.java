package com.proyectosoftware.backend.modelo.barajas;

<<<<<<< HEAD
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.proyectosoftware.backend.modelo.Carta;
import com.proyectosoftware.backend.modelo.interfaces.Baraja;

/**
 * Implementacion baraja francesa
 */
public class BarajaFrancesa implements Baraja{
    private static final String PICAS = "picas";
    private static final String DIAMANTES = "diamantes";
    private static final String TREBOLES = "treboles";
    private static final String CORAZONES = "corazones";

    private int numeroCartas;
    private List<Carta> cartas;


    private Map<Integer, String> coloresBaraja;
    private Map<Integer, String> numerosBaraja;

    private static volatile BarajaFrancesa instancia = null; //Singleton

    /**
     * Constuctor Baraja
     * @param numeroCartas  - cantidad de cartas de la baraja
     * @param cartasBaraja  - String representando las diferentes cartas de la baraja
     */
    private BarajaFrancesa(int numeroCartas, String cartasBaraja) {
        this.numeroCartas = numeroCartas;
        this.cartas = Collections.unmodifiableList(parsearCartas(cartasBaraja));
        
        this.coloresBaraja = new HashMap<>();
        coloresBaraja.put(0, PICAS);
        coloresBaraja.put(1, DIAMANTES);
        coloresBaraja.put(2, TREBOLES);
        coloresBaraja.put(3, CORAZONES);
        
        this.numerosBaraja = new HashMap<>();
        numerosBaraja.put(1, "As");
        numerosBaraja.put(2, "1");
        numerosBaraja.put(3, "2");
        numerosBaraja.put(4, "3");
        numerosBaraja.put(5, "4");
        numerosBaraja.put(6, "5");
        numerosBaraja.put(7, "6");
        numerosBaraja.put(8, "7");
        numerosBaraja.put(9, "8");
        numerosBaraja.put(10, "9");        
        numerosBaraja.put(11, "10");
        numerosBaraja.put(12, "Sota");
        numerosBaraja.put(13, "Caballo");        
        numerosBaraja.put(14, "Rey");      
    }

    /**
     * Devuelve la instancia de la baraja
     * @return - instancia
     * @see BarajaFrancesa
     */
    public static synchronized BarajaFrancesa devolverInstancia(int numeroCartas, String cartasBaraja){
        if(instancia == null){
            instancia = new BarajaFrancesa(numeroCartas, cartasBaraja);
        }
        return instancia;
    }

    /**
     * Devuelve la instancia de la baraja
     * @return - instancia
     * @see BarajaFrancesa
     */
    public static synchronized BarajaFrancesa devolverInstancia(){
        if(instancia == null){
            /**
             * TODO: lanzar error
             */
        }
        return instancia;
=======
import java.util.List;

import com.proyectosoftware.backend.modelo.Carta;
import com.proyectosoftware.backend.modelo.interfaces.Baraja;

public class BarajaFrancesa implements Baraja{

    public BarajaFrancesa(int numeroCartas, String cartasBaraja) {

>>>>>>> origin/Cinquillo
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean esCartaDeLaBaraja(Carta carta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'esCartaDeLaBaraja'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean esCartaDeLaBaraja(int color, int numero) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'esCartaDeLaBaraja'");
    }

    @Override
    public String colorReal(int color) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'colorReal'");
    }

    @Override
    public String numeroReal(int numero) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'numeroReal'");
    }

    @Override
    public List<Carta> devolverCartas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'devolverCartas'");
    }
    
}
