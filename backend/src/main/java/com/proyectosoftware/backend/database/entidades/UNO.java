package com.proyectosoftware.backend.database.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "uno")
public class UNO {
    
    @EmbeddedId
    private PartidaId id;

    @Column(name = "mazo")
    private String mazo;

    @Column(name = "sentido")
    private int sentido;

    @Column(name = "ultima_carta")
    private String ultimaCarta;

    public UNO() {}

    public UNO(PartidaId id, String mazo, int sentido, String ultimaCarta) {
        this.id = id;
        this.mazo = mazo;
        this.sentido = sentido;
        this.ultimaCarta = ultimaCarta;
    }

    public PartidaId getId() {
        return id;
    }

    public void setId(PartidaId id) {
        this.id = id;
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
