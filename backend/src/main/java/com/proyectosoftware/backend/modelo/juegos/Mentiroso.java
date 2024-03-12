package com.proyectosoftware.backend.modelo.juegos;

import com.proyectosoftware.backend.modelo.Carta;
import com.proyectosoftware.backend.modelo.Partida;
import com.proyectosoftware.backend.modelo.interfaces.JuegoSinApuesta;

public class Mentiroso implements JuegoSinApuesta{
    private Carta[] cartas_mesa;

    public Mentiroso(int id, int turno, String cartas_mesa) {
        super(id, turno);
        Carta carta = new Carta();

        this.cartas_mesa = carta.parseStringCartas(cartas_mesa);
    }
}
