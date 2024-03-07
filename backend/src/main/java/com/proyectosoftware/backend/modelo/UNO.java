package com.proyectosoftware.backend.modelo;

public class UNO extends Partida{
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
