package com.proyectosoftware.backend.modelo;

public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private int fichas;
    private String pais;

    public Usuario(int id, String nombre, String email, int fichas, String pais) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.fichas = fichas;
        this.pais = pais;
    }
}
