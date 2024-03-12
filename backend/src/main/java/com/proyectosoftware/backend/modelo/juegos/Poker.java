package com.proyectosoftware.backend.modelo.juegos;

import com.proyectosoftware.backend.modelo.Carta;
import com.proyectosoftware.backend.modelo.Partida;
import com.proyectosoftware.backend.modelo.interfaces.JuegoConApuesta;

public class Poker implements JuegoConApuesta{
    private int bote;
    private int ultima_apuesta;
    private Carta[] cartas_mesa;
    private Carta[] mazo;

    public Poker(int id, int turno, int bote, int ultima_apuesta, String mazo, String cartas_mesa) {
        super(id, turno);
        this.bote = bote;
        this.ultima_apuesta = ultima_apuesta;
        Carta carta = new Carta();

        this.mazo = carta.parseStringCartas(mazo);
        this.cartas_mesa = carta.parseStringCartas(cartas_mesa);
    }
}
