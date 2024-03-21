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
    
    // Comprueba si una carta es valida en función de la última carta jugada
    public boolean CartaValida(Carta carta){
        boolean valido=false;
        if(carta.numero == 13 || carta.numero == 14){
            valido = true;
        }
        else if (carta.numero < 13){
            if (carta.color == ultimaCarta.getColor() || carta.numero == ultimaCarta.getNumero()){
                valido = true;
            }
        }
        return valido;
    }
    
    public void CambioSentido(){
        if (sentido == 0){
            sentido = 1;
        }
        else {
            sentido = 0;
        }
    }
    
    public void SumaDos(){
    
    }
    
    public void SaltoTurno(){
        this.siguienteTurno();
        this.siguienteTurno();
    }
    
    public void SumaCuatro(){
    
    }
    
    
    public Carta ElegirCarta(Carta[] cartasUsuario, int n){
        if(CartaValida(cartasUsuario[n])){
            return cartasUsuario[n];
        }
        else{
            return null;
        }
    }
    
    public void HacerJugada(){
        Carta carta = new Carta();

        if (/*boton roba cartas*/){
                
                //salta turno
        }
        else if (/*boton carta*/){
            while(carta == null){
                carta = ElegirCarta();
            }
            if(carta.numero() == 10){
                //cambio sentido
            }
            else if (carta.numero() == 11){
                //+2
            }
            else if(carta.numero() == 12){
                //salto turno
            }
            else if(carta.numero() == 14){
                //+4
            }
            ultimaCarta[ultimaCarta.length] = carta;
        }
    }
}
