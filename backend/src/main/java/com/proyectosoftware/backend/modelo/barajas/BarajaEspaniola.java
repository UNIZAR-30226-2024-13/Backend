package com.proyectosoftware.backend.modelo.barajas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.proyectosoftware.backend.modelo.Carta;
import com.proyectosoftware.backend.modelo.interfaces.Baraja;

/**
 * Implemenatsion singleton de la baraja española
 */
public class BarajaEspaniola implements Baraja{
    public static final String OROS = "oros";
    public static final String COPAS = "copas";
    public static final String ESPADAS = "espadas";
    public static final String BASTOS = "bastos";

    private int numeroCartas;
    private List<Carta> cartas;

    //private Set<String> coloresBaraja;
    //private Set<Integer> numerosBaraja;

    private Map<Integer, String> coloresBaraja;
    private Map<Integer, String> numerosBaraja;

    private static volatile BarajaEspaniola instancia = null; //Singleton

    /**
     * Constuctor Baraja
     * @param numeroCartas  - cantidad de cartas de la baraja
     * @param cartasBaraja  - String representando las diferentes cartas de la baraja
     */
    private BarajaEspaniola() {
        this.coloresBaraja = new HashMap<>();
        coloresBaraja.put(0, OROS);
        coloresBaraja.put(1, COPAS);
        coloresBaraja.put(2, ESPADAS);
        coloresBaraja.put(3, BASTOS);
        
        this.numerosBaraja = new HashMap<>();
        numerosBaraja.put(1, "As");
        numerosBaraja.put(2, "2");
        numerosBaraja.put(3, "3");
        numerosBaraja.put(4, "4");
        numerosBaraja.put(5, "5");
        numerosBaraja.put(6, "6");
        numerosBaraja.put(7, "7");
        numerosBaraja.put(8, "Sota");
        numerosBaraja.put(9, "Caballo");        
        numerosBaraja.put(10, "Rey");  
        
        this.cartas = crearBaraja();
    }

    /**
     * Crea una baraja en base 
     * @return - Devuelve una baraja española
     */
    private List<Carta> crearBaraja(){
        List <Carta> baraja = new ArrayList<>();

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
     * @see BarajaEspaniola
     */
    public static synchronized BarajaEspaniola devolverInstancia(){
        if(instancia == null){
            instancia = new BarajaEspaniola();
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

    /**
     * {@inheritDoc}
     * @implSpec
     * Se hace uso de un diccionario interno para traducir el codigo del 
     * color al color interpretado por la baraja
     */
    @Override
    public String colorReal(int color) {
        return coloresBaraja.get(color);
    }

    /**
     * {@inheritDoc}
     * @implSpec
     * Se hace uso de un diccionario interno para traducir el codigo del 
     * numero al numero interpretado por la baraja
     */
    @Override
    public String numeroReal(int numero) {
        return numerosBaraja.get(numero);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Carta> devolverCartas() {
        if (cartas != null){
            return new ArrayList<>(cartas);
        }
        return null;
    }

    /**
     * 
     */
    @Override
    public String toString(){
        String res = "";

        for (Carta carta : cartas) {
            res += carta.toString() + ";";
        }
        return res;
    }
}
