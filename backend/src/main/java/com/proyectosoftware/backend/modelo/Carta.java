package com.proyectosoftware.backend.modelo;

/**
 * Abstraccion de una carta de juego de mesa
 */
public class Carta {
    private int numero;
    private int color;

    /**
     * Constructor de la carta
     * @param numero    - numero de la carta
     * @param color     - color de la carta
     */
    public Carta(int numero, int color){
        this.numero = numero;
        this.color = color;
    }

    /**
     * Devuelve el numero de la carta
     * @return numero de la carta
     */
    public int getNumero(){
        return numero;
    }
    
    /**
     * Devuelve el color de la carta
     * @return color de la carta
     */
    public int getColor(){
        return color;
    }

    /**
     * 
     */
    @Override
    public String toString(){
        return color + "," + numero;
    }
}
