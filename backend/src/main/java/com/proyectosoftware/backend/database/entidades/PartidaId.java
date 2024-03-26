package com.proyectosoftware.backend.database.entidades;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Embeddable
public class PartidaId implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Partida partida;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((partida == null) ? 0 : partida.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
            PartidaId other = (PartidaId) obj;
        if (partida == null) {
            if (other.partida != null)
                return false;
        } else if (!partida.equals(other.partida))
            return false;
        return true;
    }
}