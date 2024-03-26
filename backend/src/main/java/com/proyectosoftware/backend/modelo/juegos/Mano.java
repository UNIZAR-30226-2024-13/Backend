package com.proyectosoftware.backend.modelo.juegos;

public class Mano {
    private String mano;
    private int prioridad;
    private int valor;

    public Mano(){
        mano = "";
        prioridad = -1;
        valor = 0;
    }

    public String getMano() {
        return mano;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public int getValor() {
        return valor;
    }

    public void setMano(String mano) {
        this.mano = mano;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public void setValor(int valor) {
        this.valor = valor;   
    }
}
