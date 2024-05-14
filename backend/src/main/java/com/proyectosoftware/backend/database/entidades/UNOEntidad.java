package com.proyectosoftware.backend.database.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "uno")
public class UNOEntidad extends Partida{

    @Column(name = "mazo")
    private String mazo;

    @Column(name = "sentido")
    private int sentido;

    @Column(name = "ultima_carta")
    private String ultimaCarta;

    public UNOEntidad() {}

    public UNOEntidad(String id, String mazo, int sentido, String ultimaCarta) {
        super(id);
        this.mazo = mazo;
        this.sentido = sentido;
        this.ultimaCarta = ultimaCarta;
    }

    public String getMazo() {
        return mazo;
    }

    public void setMazo(String mazo) {
        this.mazo = mazo;
    }

    public int getSentido() {
        return sentido;
    }

    public void setSentido(int sentido) {
        this.sentido = sentido;
    }

    public String getUltimaCarta() {
        return ultimaCarta;
    }

    public void setUltimaCarta(String ultimaCarta) {
        this.ultimaCarta = ultimaCarta;
    }
}
