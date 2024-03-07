package com.proyectosoftware.backend.modelo;

public class Cinquillo extends Partida{
    private Carta[] escaleras;

    public Cinquillo(int id, int turno, String escaleras) {
        super(id, turno);
        Carta carta = new Carta();

        this.escaleras = carta.parseStringCartas(escaleras);
    }
}
