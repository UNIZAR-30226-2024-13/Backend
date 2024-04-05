package com.proyectosoftware.backend.database.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "mentiroso")
public class Mentiroso {
    
    @EmbeddedId
    private PartidaId id;

    @Column(name = "cartas_mesa")
    private String cartasMesa;

    public Mentiroso() {}

    public Mentiroso(PartidaId id, String cartasMesa) {
        this.id = id;
        this.cartasMesa = cartasMesa;
    }

    public PartidaId getId() {
        return id;
    }

    public void setId(PartidaId id) {
        this.id = id;
    }

    public String getCartasMesa() {
        return cartasMesa;
    }

    public void setCartasMesa(String cartasMesa) {
        this.cartasMesa = cartasMesa;
    }
}
