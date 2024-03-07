package com.proyectosoftware.backend.modelo;

public class UNO extends Partida{
     private int id;
     private int sentido;
     private Carta[] mazo;
     private Carta[] ultimaCarta;
     
     public UNO(int id, int sentido, String mazo, String ultimaCarta){
         
         this.id =id;
         this.sentido=sentido;
         Carta carta = new Carta();

         this.mazo= carta.parseStringCartas(mazo);
         this.ultimaCarta= carta.parseStringCartas(ultimaCarta);
     }
}
