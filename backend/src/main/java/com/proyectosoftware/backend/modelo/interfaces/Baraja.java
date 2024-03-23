package com.proyectosoftware.backend.modelo.interfaces;

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
public interface Baraja {
   
    default List<Carta> parsearCartas(String cartasBaraja){
        List<Carta> cartas = new ArrayList<>();
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
        return cartas;
    }

    /**
     * 
     * @param carta - carta para verificar
     * @return {@code true} si la carta pertenece a la baraja
     */
    public boolean esCartaDeLaBaraja(Carta carta);

    /**
     * 
     * @param color     - color de la carta a verificar
     * @param numero    - numero de la carta a verificar
     * @return {@code true} si la carta pertenece a la baraja  
     */
    public boolean esCartaDeLaBaraja(int color, int numero);

    /**
     * 
     * @param color - Codigo del color se quiere interpretar
     * @return Devuelve el color real
     */
    public String colorReal(int color);

    /**
     * 
     * @param numero - Codigo del numero que se quiere interpretar
     * @return Devuelve el numero real
     */
    public String numeroReal(int numero);

    /**
     * 
     * @return - Devuelve una copia de las cartas de la baraja
     */
    public List<Carta> devolverCartas();
    
    /**
     * 
     */
    public void barajar();
}
