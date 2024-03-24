package com.proyectosoftware.backend.modelo.barajas;

import java.util.ArrayList;
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
     * Constuctor Baraja francesa
     */
    private BarajaFrancesa() {
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
        
        this.cartas = crearBaraja();
    }

    /**
     * Crea una baraja en base
     * @return - Devuelve una baraja francesa
     */
    private List<Carta> crearBaraja() {
        List<Carta> baraja = new ArrayList<>();
        for (int palo : coloresBaraja.keySet()) {
            for (int numero : numerosBaraja.keySet()) {
                Carta carta = new Carta(numero, palo);
                baraja.add(carta);
            }
        }
        return baraja;
    }

    /**
     * Devuelve la instancia de la baraja
     * @return - instancia
     * @see BarajaFrancesa
     */
    public static synchronized BarajaFrancesa devolverInstancia(){
        if(instancia == null){
            instancia = new BarajaFrancesa();
        }
        return instancia;
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
        return coloresBaraja.get(color);
    }

    @Override
    public String numeroReal(int numero) {
        return numerosBaraja.get(numero);
    }

    @Override
    public List<Carta> devolverCartas() {
        if (cartas != null) {
            return new ArrayList<>(cartas);
        }
        return null;
    }
    
}
