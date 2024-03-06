package com.proyectosoftware.backend.modelo;

public class Poker {
    protected int id;
    protected int bote;
    protected int ultima_apuesta;
    protected int[] cartas_mesa;
    protected int[] mazo;

    public Poker(int id, int bote, int ultima_apuesta, String mazo, String cartas_mesa) {
        super();
        this.id = id;
        this.bote = bote;
        this.ultima_apuesta = ultima_apuesta;
        
    }
}
