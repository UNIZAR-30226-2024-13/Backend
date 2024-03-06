package com.proyectosoftware.backend.modelo;

public class Carta {
    private int numero;
    private int color;

    public Carta(){}

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
     * Establece el numero de la carta
     * @param numero
     */
    public void setNumero(int numero){
        this.numero = numero;
    }

    /**
     * Establece el color de la carta
     * @param color
     */
    public void setColor(int color){
        this.color = color;
    }
}
