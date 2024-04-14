package com.proyectosoftware.backend.database.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "cinquillo")
public class Cinquillo {

    @EmbeddedId
    private PartidaId id;
    
    @Column(name = "escaleras")
    private String escaleras;

    public Cinquillo() {}

    public Cinquillo(PartidaId id, String escaleras) {
        this.id = id;
        this.escaleras = escaleras;
    }

    public PartidaId getId() {
        return id;
    }

    public void setId(PartidaId id) {
        this.id = id;
    }

    public String getEscaleras() {
        return escaleras;
    }

    public void setEscaleras(String escaleras) {
        this.escaleras = escaleras;
    }
}