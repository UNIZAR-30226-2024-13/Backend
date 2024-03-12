package com.proyectosoftware.backend.modelo.juegos;

import com.proyectosoftware.backend.modelo.Carta;
import com.proyectosoftware.backend.modelo.Partida;
import com.proyectosoftware.backend.modelo.interfaces.JuegoConApuesta;

public class BlackJack implements JuegoConApuesta{
    private Carta[] cartas_banca;
    private Carta[] mazo;

    public BlackJack(int id, int turno, String mazo, String cartas_banca) {
        super(id, turno);
        Carta carta = new Carta();

        this.mazo = carta.parseStringCartas(mazo);
        this.cartas_banca = carta.parseStringCartas(cartas_banca);
    }
}
