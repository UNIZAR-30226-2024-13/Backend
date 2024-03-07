package com.proyectosoftware.backend.modelo;

public class Guarda {
    private int usuario1;
    private int usuario2;
    private int usuario3;
    private int usuario4;

    private int partida;
    private String cartasUsuario;
    private int apuestaUsuario;
    private int dineroUsuario;

    public Guarda(  int usuario1, int usuario2, int usuario3, int usuario4, int partida,
                    String cartasUsuario, int apuestaUsuario, int dineroUsuario)
    {
        this.usuario1 = usuario1;
        this.usuario2 = usuario2;
        this.usuario3 = usuario3;
        this.usuario4 = usuario4;
        this.partida = partida;
        this.cartasUsuario = cartasUsuario;
        this.apuestaUsuario = apuestaUsuario;
        this.dineroUsuario = dineroUsuario;
    }
}
