package com.proyectosoftware.backend.modelo.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.proyectosoftware.backend.modelo.Carta;

/**
 * Baraja abstracta
 */
public interface Baraja {
   
    /**
     * Crea una lista de cartas desde un String
     * @param cartasBaraja  - Representacion en string de las cartas
     * @return La lista de cartas
     */
    public default List<Carta> parsearCartas(String cartasBaraja){
        List<Carta> cartas = new ArrayList<>();
        
        if (cartasBaraja.equals("")) {
            return cartas;
        }

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
     * Comprueba que una carta sea de la baraja
     * @param carta - carta para verificar
     * @return {@code true} si la carta pertenece a la baraja
     */
    public boolean esCartaDeLaBaraja(Carta carta);

    /**
     * Comprueba que un color dado y un numero dado son de la baraja
     * @param color     - color de la carta a verificar
     * @param numero    - numero de la carta a verificar
     * @return {@code true} si la carta pertenece a la baraja  
     */
    public boolean esCartaDeLaBaraja(int color, int numero);

    /**
     * Devuelve el color real de la represetnacion de un color
     * @param color - Codigo del color se quiere interpretar
     * @return El color real
     */
    public String colorReal(int color);

    /**
     * Devuevle el nuemro real de la represetnacion del numero
     * @param numero - Codigo del numero que se quiere interpretar
     * @return El numero real
     */
    public String numeroReal(int numero);

    /**
     * Devuevle una lista con todas las cartas que la baraja puede utilizar
     * @return - Copia de las cartas de la baraja
     */
    public List<Carta> devolverCartas();
}
