package com.proyectosoftware.backend.database.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "blackjack")
public class BlackJack {
    
    @EmbeddedId
    private PartidaId id;

    @Column(name = "mazo")
    private String mazo;

    @Column(name = "cartas_banca")
    private String cartasBanca;

    public BlackJack() {}

    public BlackJack(PartidaId id, String mazo, String cartasBanca) {
        this.id = id;
        this.mazo = mazo;
        this.cartasBanca = cartasBanca;
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

    public String getCartasBanca() {
        return cartasBanca;
    }

    public void setCartasBanca(String cartasBanca) {
        this.cartasBanca = cartasBanca;
    }
}
