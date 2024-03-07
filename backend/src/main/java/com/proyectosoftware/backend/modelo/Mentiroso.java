package com.proyectosoftware.backend.modelo;

public class Mentiroso extends Partida{
    private Carta[] cartas_mesa;

    public Mentiroso(int id, int turno, String cartas_mesa) {
        super(id, turno);
        Carta carta = new Carta();

        this.cartas_mesa = carta.parseStringCartas(cartas_mesa);
    }
}
