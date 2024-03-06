package com.proyectosoftware.backend.modelo;

public class Poker {
    private int id;
    private int bote;
    private int ultima_apuesta;
    private Carta[] cartas_mesa;
    private Carta[] mazo;

    public Poker(int id, int bote, int ultima_apuesta, String mazo, String cartas_mesa) {
        super();
        this.id = id;
        this.bote = bote;
        this.ultima_apuesta = ultima_apuesta;

        this.mazo = parseStringCartas(mazo);
        this.cartas_mesa = parseStringCartas(cartas_mesa);
    }

    /**
     * 
     * @param cadenaCartas, contiene la cadena de carecteres recibida desde la Base de Datos
     * @return retVal, contiene el vector de Carta que representa la baraja de juego
     */
    private Carta[] parseStringCartas(String cadenaCartas){
        Carta[] retVal = new Carta[52];
        String[] cartas_mazo = cadenaCartas.split(";");
        int i = 0;

        for (String carta : cartas_mazo){
            String[] parte_carta = carta.split(",");
            int parte = 0;

            for (String info_carta : parte_carta){
                if (parte == 0) {
                    int color = Integer.parseInt(info_carta);
                    retVal[i].setColor(color);
                    parte = 1;
                }
                else{
                    int numero = Integer.parseInt(info_carta);
                    retVal[i].setNumero(numero);
                }
            }        
            i++;    
        }
        return retVal;
    }
}
