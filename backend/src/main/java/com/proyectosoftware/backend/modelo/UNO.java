package com.proyectosoftware.backend.modelo;

public class UNO {
     private int id;
     private int sentido;
     private Carta[] mazo;
     private Carta[] ultimaCarta;
     
     public UNO(int id, int sentido, String mazo, String ultimaCarta){
         
         this.id =id;
         this.sentido=sentido;
         this.mazo=mazo;
         this.ultimaCarta=ultimaCarta;
     }
}
