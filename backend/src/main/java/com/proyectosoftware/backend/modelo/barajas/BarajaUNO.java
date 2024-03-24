package com.proyectosoftware.backend.modelo.barajas;

import java.util.List;

import com.proyectosoftware.backend.modelo.Carta;
import com.proyectosoftware.backend.modelo.interfaces.Baraja;

public class BarajaUNO implements Baraja{
    private static final String AMARILLO = "amarillo";
    private static final String AZUL = "azul";
    private static final String ROJO = "rojo";
    private static final String VERDE = "verde";
    
    private int numeroCartas;
    private List<Carta> cartas;
    
    //private Set<String> coloresBaraja;
    //private Set<Integer> numerosBaraja;

    private Map<Integer, String> coloresBaraja;
    private Map<Integer, String> numerosBaraja;

    private static volatile BarajaUNO instancia = null; //Singleton
    
    public BarajaUNO() {
        this.coloresBaraja = new HashMap<>();
        coloresBaraja.put(0, AMARILLO);
        coloresBaraja.put(1, AZUL);
        coloresBaraja.put(2, ROJO);
        coloresBaraja.put(3, VERDE);
        
        this.numerosBaraja = new HashMap<>();
        numerosBaraja.put(1, "0");
        numerosBaraja.put(2, "1");
        numerosBaraja.put(3, "2");
        numerosBaraja.put(4, "3");
        numerosBaraja.put(5, "4");
        numerosBaraja.put(6, "5");
        numerosBaraja.put(7, "6");
        numerosBaraja.put(8, "7");
        numerosBaraja.put(9, "8");
        numerosBaraja.put(10, "9");        
        numerosBaraja.put(11, "Cambio de sentido");  
        numerosBaraja.put(12, "Suma Dos");  
        numerosBaraja.put(13, "Salta el turno");  
        numerosBaraja.put(14, "Cambio de color");  
        numerosBaraja.put(15, "Suma Cuatro");  
        
        this.cartas = crearBaraja();
    }
    
    /**
     * Crea una baraja en base 
     * @return - Devuelve una baraja del UNO
     */
    private List<Carta> crearBaraja(){
        List <Carta> baraja = new ArrayList<>();

        for (int color : coloresBaraja.keySet()) {
            for (int numero : numerosBaraja.keySet()) {
                if(numero < 14){
                    Carta carta = new Carta(numero, color);
                    baraja.add(carta);
                    baraja.add(carta);
                }
                else{
                    Carta carta = new Carta(numero, color);
                    baraja.add(carta);
                }
            }
        }
        return baraja;
    }
    
    /**
     * Devuelve la instancia de la baraja
     * @return - instancia
     * @see BarajaUNO
     */
    public static synchronized BarajaUNO devolverInstancia(){
        if(instancia == null){
            instancia = new BarajaUNO();
        }
        return instancia;
    }

    

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean esCartaDeLaBaraja(Carta carta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'esCartaDeLaBaraja'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean esCartaDeLaBaraja(int color, int numero) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'esCartaDeLaBaraja'");
    }

    @Override
    public String colorReal(int color) {
        // TODO Auto-generated method stub
        return coloresBaraja.get(color);
    }

    @Override
    public String numeroReal(int numero) {
        // TODO Auto-generated method stub
        return numerosBaraja.get(numero);
    }

    @Override
    public List<Carta> devolverCartas() {
        // TODO Auto-generated method stub
        if (cartas != null){
            return new ArrayList<>(cartas);
        }
        return null;
    }
    
        /**
     * 
     */
    @Override
    public String toString(){
        String res = "";

        for (Carta carta : cartas) {
            res += carta.toString() + ";";
        }
        return res;
    }
    
}
