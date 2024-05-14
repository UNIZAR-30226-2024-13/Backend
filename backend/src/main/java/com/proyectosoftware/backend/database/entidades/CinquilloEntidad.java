package com.proyectosoftware.backend.database.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "cinquillo")
public class CinquilloEntidad extends Partida{

    
    @Column(name = "escaleras")
    private String escaleras;

    public CinquilloEntidad() {}

    public CinquilloEntidad(String id, String escaleras) {
        super(id);
        this.escaleras = escaleras;
    }

    public String getEscaleras() {
        return escaleras;
    }

    public void setEscaleras(String escaleras) {
        this.escaleras = escaleras;
    }
}