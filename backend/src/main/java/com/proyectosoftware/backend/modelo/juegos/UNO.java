package com.proyectosoftware.backend.modelo.juegos;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.proyectosoftware.backend.modelo.Carta;
import com.proyectosoftware.backend.modelo.Usuario;
import com.proyectosoftware.backend.modelo.barajas.BarajaUNO;
import com.proyectosoftware.backend.modelo.interfaces.Baraja;
import com.proyectosoftware.backend.modelo.interfaces.Estado;
import com.proyectosoftware.backend.modelo.interfaces.JuegoSinApuesta;

public class UNO implements JuegoSinApuesta{
    public static final int MAX_USUARIOS = 4;
    private Baraja baraja;
    private List <Carta> mazo;
    private List <Carta> ultimaCarta;
    private Map<Integer, Usuario> usuarios; 
    private Map<Integer, List<Carta>> manoUsuarios;
    private int sentido;
    private int turno = 0;
    
    public UNO(){
       baraja = BarajaEspaniola.devolverInstancia();
       mazo = baraja.devolverCartas();
       manoUsuarios = new HashMap<>(MAX_USUARIOS);
    }
    
        /**
     * Reparto de cartas entre los usuarios
     * 
     */
    public void iniciarPartida(){
        int jugador = 0; 
        int index = 0;
        List<Carta> mano = new ArrayList<>();
        Carta carta;
        
        //  Repartir las cartas
        Collections.shuffle(mazo);
        do {
            do{
                carta = new Carta(mazo.get(index).getNumero(), mazo.get(index).getColor());
                index++;
                mano.add(carta);
            }while(mano.size() < 7);
            
            manoUsuarios.put(jugador++, new ArrayList<>(mano));
            mano.clear();
        } while (jugador < MAX_USUARIOS);
        System.out.println(manoUsuarios);
        
        carta = new Carta(mazo.get(index).getNumero(), mazo.get(index).getColor());
        ultimaCarta.add(carta);
        HacerJugada();
    }
    
    
    
    /**
     * Cargar un juego de uno dado un estado
     * @param estado
     */
    public UNO(Estado estado){

    }
    
    
    @Override
    public Estado guardar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

    @Override
    public void cargar(Estado estado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cargar'");
    }

    @Override
    public Estado recuperarEstado(String estadoString) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recuperarEstado'");
    }

    @Override
    public String crearEstado(Estado estado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearEstado'");
    }


    @Override
    public void siguenteTurno() {
        turno++;
        if(turno == MAX_USUARIOS){
            turno = 0;
        }
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
        this.siguenteTurno();
        this.siguenteTurno();
    }
    
    public void SumaCuatro(){
    
    }
   
    public void HacerJugada(){
        Carta carta = new Carta();
        //carta = ;
        if (/*boton roba cartas*/){
                //roba carta
                //salta turno
        }
        else if (/*boton carta*/){
            if(carta.numero() == 10){
                CambioSentido();
                //cambia el sentido del juego
            }
            else if (carta.numero() == 11){
                SumaDos();
                //+2 para el siguiente
            }
            else if(carta.numero() == 12){
                SaltoTurno();
                //salta el turno del siguiente
            }
            else if(carta.numero() == 14){
                SumaCuatro();
                //+4 para el siguiente
            }
            else{
                this.siguenteTurno();
            }
            ultimaCarta.add(carta);
        }
    }
}
