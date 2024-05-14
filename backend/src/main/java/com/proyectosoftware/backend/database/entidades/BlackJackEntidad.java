package com.proyectosoftware.backend.database.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "blackjack")
public class BlackJackEntidad extends Partida{
    

    @Column(name = "mazo")
    private String mazo;

    @Column(name = "cartas_banca")
    private String cartasBanca;

    public BlackJackEntidad() {}

    public BlackJackEntidad(String id, String mazo, String cartasBanca) {
        super(id);
        this.mazo = mazo;
        this.cartasBanca = cartasBanca;
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
