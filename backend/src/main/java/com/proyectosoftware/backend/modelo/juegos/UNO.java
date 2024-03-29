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
    private int sentido = 0;
    private int turno = 0;
    private int masdos = 0;
    private int mascuatro = 0;
    
    public UNO(){
       baraja = BarajaUNO.devolverInstancia();
       mazo = baraja.devolverCartas();
       usuarios = new HashMap<>(MAX_USUARIOS);
       manoUsuarios = new HashMap<>(MAX_USUARIOS);
    }
    
     /**
     * Reparto de cartas entre los usuarios
     * 
     */
    public void iniciarPartida(){
        int jugador = 0; 
        List<Carta> mano = new ArrayList<>();
        Carta carta;
        
        //  Repartir las cartas
        Collections.shuffle(mazo);
        do {
            do{
                carta = new Carta(mazo.get(0).getNumero(), mazo.get(0).getColor());
                mazo.remove(0);
                mano.add(carta);
            }while(mano.size() < 7);
            
            manoUsuarios.put(jugador++, new ArrayList<>(mano));
            mano.clear();
        } while (jugador < MAX_USUARIOS);
        System.out.println(manoUsuarios);
        
        carta = new Carta(mazo.get(0).getNumero(), mazo.get(0).getColor());
        mazo.remove(0);
        ultimaCarta.add(carta);
        jugada();
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
        if(sentido==0){
           turno++;
            if(turno == MAX_USUARIOS){
                turno = 0;
            }
        }
        else if(sentido==1){
            turno--;
            if(turno == -1){
                turno = 3;
            }
        }

    }
     /*
     * Jugada del UNO, esta separado según los casos necesarios:
     * tener que robar una carta, pasar el turno o realizar una
     * jugada. 
     */
    public void jugada(){
        List<Carta> manoJugador = manoUsuarios.get(turno);
        Iterator<Carta> iterator = manoJugador.iterator();
        List<Carta> posiblesJugadas = new ArrayList<>();
        //  Caso de robar dos cartas
        if(masdos == 1){
            RobaCarta(manoJugador,2);
        }
        //  Caso de robar cuatro cartas
        else if(mascuatro == 1){
            RobaCarta(manoJugador,4);
        }
        else{
            //Posibles juagadas a elegir
            posiblesJugadas = jugarCarta(posiblesJugadas, manoJugador.iterator());
            if(posiblesJugadas.isEmpty()){
                RobaCarta(manoJugador,1);
                siguenteTurno();
            }
        }
    }
    
     /**
     * Devuelve todas las posibles jugadas que tiene el usuario en posiblesJugadas 
     */
    private List<Carta> jugarCarta(List<Carta> posiblesJugadas, Iterator<Carta> iterator){
        while(iterator.hasNext()) {
            Carta carta = iterator.next();
            if(CartaValida(carta)){
                posiblesJugadas.add(carta);
            }
        }  
        return posiblesJugadas;
    }
    
    // Comprueba si una carta es valida en función de la última carta jugada
    public boolean CartaValida(Carta carta){
        boolean valido=false;
        Carta ultCarta = ultimaCarta.get(ultimaCarta.size() - 1);
        // Obtenemos la última carta
        int color = ultCarta.getColor();
        int numero = ultCarta.getNumero();
        if(carta.getNumero() == 13 || carta.getNumero() == 14){
            valido = true;
        }
        else if (carta.getNumero() < 13){
            if (carta.getColor() == color || carta.getNumero() == numero){
                valido = true;
            }
        }
        return valido;
    }
    
    // Cambia el sentido de la partida
    public void CambioSentido(){
        if (sentido == 0){
            sentido = 1;
        }
        else {
            sentido = 0;
        }
    }
    
     // Cambia el estado de masdos
    public void SumaDos(){
        masdos=1;
    }
    
    // Añade n cartas a la mano del usuario y pasa el turno
    public void RobaCarta(List<Carta> mano,int n){
        Carta carta;
        for(int i=0;i<n;i++){
            carta = new Carta(mazo.get(0).getNumero(), mazo.get(0).getColor());
            mazo.remove(0);
            mano.add(carta);
        }
        if(n>1){
            masdos=0;
            mascuatro=0;
        }
        this.siguenteTurno();
    }
    
    // Pasa el turno
    public void SaltoTurno(){
        this.siguenteTurno();
    }
    
    // Cambia el estado de mascuatro
    public void SumaCuatro(){
        mascuatro=1;
    }
    
   
     /*
     * En el caso de que eljugador no haya sufrido ninguna sanción 
     * y tenga alguna posible jugada a realizar. Puede elegir pasar
     * su turno en caso que lo desee.
     */
    public void HacerJugada(Carta carta,int color){
        List<Carta> manoJugador = manoUsuarios.get(turno);
        Carta c;
        if (carta == null){
            //roba carta
            RobaCarta(manoJugador,1);
        }
        else {
            int n = carta.getNumero();
            switch (n) {
                case 10:
                    CambioSentido();
                    //cambia el sentido del juego
                    break;
                case 11:
                    SumaDos();
                    //+2 para el siguiente
                    break;
                case 12:
                    SaltoTurno();
                    //salta el turno del siguiente
                    break;
                case 13:
                    c = new Carta(n,color);
                    carta = c;
                    //cambia el color al elegido por el usuario
                    break;
                case 14:
                    SumaCuatro();
                    //+4 para el siguiente
                    break;
                default:
                    //carta normal
                    break;
            }
            ultimaCarta.add(carta);
            this.siguenteTurno();
        }
    }
}
