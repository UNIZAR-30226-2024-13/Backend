package com.proyectosoftware.backend.modelo;

public class Poker {
    protected int id;
    protected int bote;
    protected int ultima_apuesta;
    protected Carta[] cartas_mesa;
    protected Carta[] mazo;

    public Poker(int id, int bote, int ultima_apuesta, String mazo, String cartas_mesa) {
        super();
        this.id = id;
        this.bote = bote;
        this.ultima_apuesta = ultima_apuesta;
        String[] cartas_mazo = mazo.split(";");
        int i = 0;
        for (String carta : cartas_mazo){
            String[] parte_carta = carta.split(",");
            int parte = 0;
            for (String info_carta : parte_carta){
                if (parte == 0) {
                   this.mazo[i].color =  Integer.parseInt(info_carta);
                   parte = 1;
                }
                else if (parte == 1){
                    this.mazo[i].numero =  Integer.parseInt(info_carta);;
                }
            }        
            i++;    
        }
        String[] cartas_m = cartas_mesa.split(";");
        int j = 0;
        for (String carta : cartas_m){
            String[] parte_carta = carta.split(",");
            int parte = 0;
            for (String info_carta : parte_carta){
                if (parte == 0) {
                   this.cartas_mesa[j].color =  Integer.parseInt(info_carta);
                   parte = 1;
                }
                else if (parte == 1){
                    this.cartas_mesa[j].numero =  Integer.parseInt(info_carta);;
                }
            }        
            j++;    
        }
    }
}
