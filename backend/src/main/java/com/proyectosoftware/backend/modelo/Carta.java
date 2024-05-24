package com.proyectosoftware.backend.modelo;

/**
 * Abstraccion de una carta de juego de mesa
 */
public class Carta {
    private int numero;
    private int color;

    /**
     * Constructor de la carta
     * @param numero    - numero de la carta
     * @param color     - color de la carta
     * @param ruta      - ruta de la imagen de la carta
     */
    public Carta(int numero, int color){
        this.numero = numero;
        this.color = color;
    }

    /**
     * Devuelve el numero de la carta
     * @return numero de la carta
     */
    public int getNumero(){
        return numero;
    }
    
    /**
     * Devuelve el color de la carta
     * @return color de la carta
     */
    public int getColor(){
        return color;
    }

    /**
     * Sobreescribe toString
     */
    @Override
    public String toString() {
        return Integer.toString(color) + "," + Integer.toString(numero);
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carta other = (Carta) obj;
		if (numero != other.numero)
			return false;
		if (color != other.color)
			return false;
		return true;
	}
    
    /**
     * 
     * @param cadenaCartas, contiene la cadena de carecteres recibida desde la Base de Datos
     * @return retVal, contiene el vector de Carta que representa la baraja de juego
     */
    /*
    public Carta[] parseStringCartas(String cadenaCartas){
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
    }*/
}
