package com.proyectosoftware.backend.modelo;

public class Cinquillo extends Partida{
    private int id;
    private Carta[] escaleras;

    public Cinquillo(int id, String escaleras) {
        super();
        this.id = id;
        Carta carta = new Carta();

        this.escaleras = carta.parseStringCartas(escaleras);
    }
}