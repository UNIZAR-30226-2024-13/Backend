package com.proyectosoftware.backend.database.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Cinquillo {

    @Id
    private Long id;

    public Cinquillo() {}

    public Cinquillo(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
