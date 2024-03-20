package com.proyectosoftware.backend.modelo.barajas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import com.proyectosoftware.backend.modelo.Carta;

/**
 * Baraja abstracta
 */
public abstract class BarajaAbstracta {
    protected int numeroCartas;
    protected List<Carta> cartas;

    //protected Set<String> coloresBaraja;
    //protected Set<Integer> numerosBaraja;

    protected Map<Integer, String> coloresBaraja;
    protected Map<Integer, String> numerosBaraja;

    
    /**
     * Constructor baraja
     * @param numeroCartas  - numero de cartas diferentes de la baraja
     * @param cartasBaraja  - String representando las diferentes cartas de la baraja
     *                          El string sera de tipo: "color,numero;color,numero;..."
     * 
     */
    public BarajaAbstracta(int numeroCartas, String cartasBaraja){
        this.numeroCartas = numeroCartas;
        parsearCartas(cartasBaraja);
    }
    
    /**
     * Funccion para crear las cartas del mazo a partir de un string
     * @param cartasBaraja - String representando las diferentes cartas de la baraja
     * 
     * @see Baraja.Baraja(int, String) (estuctura del string)
     * 
     */
    private void parsearCartas(String cartasBaraja){
        for (String carta : cartasBaraja.split(";")) {
            String[] datosCarta = carta.split(",");
            int color = Integer.parseInt(datosCarta[0]);
            int numero = Integer.parseInt(datosCarta[1]); 
            if(esCartaDeLaBaraja(numero, color)){
                cartas.add(new Carta(numero, color));
            }else {
                /**
                 * TODO: lanzar error
                 */
            }
        }
        this.cartas = Collections.unmodifiableList(cartas);
    }

    /**
     * 
     * @param carta - carta para verificar
     * @return {@code true} si la carta pertenece a la baraja
     */
    public abstract boolean esCartaDeLaBaraja(Carta carta);

    /**
     * 
     * @param color     - color de la carta a verificar
     * @param numero    - numero de la carta a verificar
     * @return {@code true} si la carta pertenece a la baraja  
     */
    public abstract boolean esCartaDeLaBaraja(int color, int numero);

    /**
     * 
     * @param color - Codigo del color se quiere interpretar
     * @return Devuelve el color real
     */
    public String colorReal(int color){
        return coloresBaraja.get(color);
    }

    /**
     * 
     * @param numero - Codigo del numero que se quiere interpretar
     * @return Devuelve el numero real
     */
    public String numeroReal(int numero){
        return numerosBaraja.get(numero);
    }

    /**
     * 
     * @return - Devuelve una copia de las cartas de la baraja
     */
    public List<Carta> devolverCartas(){
        if (cartas != null) {
            return new ArrayList<>(cartas);
        }else {
            /**
             * TODO: lanzar error "baraja no inicializada"
             */
            return null;
        }
    }
}
