package com.proyectosoftware.backend.modelo;

public class UNO extends Partida{
     private int id;
     private int sentido;
     private Carta[] mazo;
     private Carta ultimaCarta;
     
     public UNO(int id, int sentido, String mazo, String ultimaCarta){
         
<<<<<<< HEAD
         this.id =id;
         this.sentido=sentido;
         Carta carta = new Carta();

         this.mazo= carta.parseStringCartas(mazo);
         this.ultimaCarta= carta.parseStringCartas(ultimaCarta);
=======
         this.id = id;
         this.sentido = sentido;
         this.mazo = parseStringMazo(mazo);
         this.ultimaCarta = parseStringUltCarta(ultimaCarta);
>>>>>>> 9791560c7a257a9d64feede4c32271d5772cf9f0
     }

     /**
     * 
     * @param cadenaCartas, contiene la cadena de carecteres recibida desde la Base de Datos
     * @return retVal, contiene el vector de Carta que representa la baraja de juego
     */
    private Carta[] parseStringMazo(String cadenaCartas){
        Carta[] retVal = new Carta[108];
        String[] cartas_mazo = cadenaCartas.split(";");
        int i = 0;

        for (String carta : cartas_mazo){
            String[] color_carta = carta.split(",");
            int color = Integer.parseInt(color_carta[0]);
            int numero = Integer.parseInt(color_carta[1]);
            retVal[i].setColor(color);
            retVal[i].setNumero(numero);
            i++;
        }
        return retVal;
    }


    /**
     * 
     * @param ultCarta, contiene la cadena de carecteres recibida desde la Base de Datos
     * @return retVal, contiene el valor de la carta que representa la última carta que se ha jugado
     */
    private Carta[] parseStringUltCarta(String ultCarta){
        Carta retVal = new Carta;
        String[] color_carta = ultCarta.split(",");
        int color = Integer.parseInt(color_carta[0]);
        int numero = Integer.parseInt(color_carta[1]);
        retVal.setColor(color);
        retVal.setNumero(numero);

        return retVal;
    }
}
