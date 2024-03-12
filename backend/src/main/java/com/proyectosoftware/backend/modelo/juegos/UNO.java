package com.proyectosoftware.backend.modelo.juegos;

import com.proyectosoftware.backend.modelo.Carta;
import com.proyectosoftware.backend.modelo.Partida;
import com.proyectosoftware.backend.modelo.interfaces.JuegoSinApuesta;

public class UNO implements JuegoSinApuesta{
    private int sentido;
    private Carta[] mazo;
    private Carta[] ultimaCarta;
    
    public UNO(int id, int turno, int sentido, String mazo, String ultimaCarta){
       super(id, turno);
       this.sentido = sentido;
       Carta carta = new Carta();

       this.mazo = carta.parseStringCartas(mazo);
       this.ultimaCarta = carta.parseStringCartas(ultimaCarta);
    }
}
