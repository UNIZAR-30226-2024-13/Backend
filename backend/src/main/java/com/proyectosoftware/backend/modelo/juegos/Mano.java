package com.proyectosoftware.backend.modelo.juegos;

/**
 * Clase con la información necesaria para gestionar la mano de un usuario
 */
public class Mano {
    private String nombre;
    private int prioridad;
    private int valor;

    /**
     * Constructor clase Mano
     */
    public Mano(){
        nombre = "";
        prioridad = -1;
        valor = 0;
    }

    /**
     * Devuelve el nombre de la mano
     * @return nombre de la mano
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve la prioridad de la mano
     * @return prioridad de la mano
     */
    public int getPrioridad() {
        return prioridad;
    }

    /**
     * Devuelve el valor de la mano
     * @return valor de la mano
     */
    public int getValor() {
        return valor;
    }

    /**
     * Establece el nombre de la mano
     * @param nombre - nombre de la mano
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece la prioridad de la mano
     * @param nombre - prioridad de la mano
     */
    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    /**
     * Establece el valor de la mano
     * @param nombre - valor de la mano
     */
    public void setValor(int valor) {
        this.valor = valor;   
    }
}
