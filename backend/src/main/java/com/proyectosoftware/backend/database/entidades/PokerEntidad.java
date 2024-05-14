package com.proyectosoftware.backend.database.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "poker")
public class PokerEntidad extends Partida{

    @Column(name = "bote", columnDefinition = "integer default 0")
    private int bote;

    @Column(name = "ultima_apuesta")
    private int ultimaApuesta;

    @Column(name = "cartas_mesa")
    private String cartasMesa;

    @Column(name = "mazo")
    private String mazo;

    public PokerEntidad() {}

    public PokerEntidad(String id, int bote, int ultimaApuesta, String cartasMesa, String mazo) {
        super(id);
        this.bote = bote;
        this.ultimaApuesta = ultimaApuesta;
        this.cartasMesa = cartasMesa;
        this.mazo = mazo;
    }

    public int getBote() {
        return bote;
    }

    public void setBote(int bote) {
        this.bote = bote;
    }

    public int getUltimaApuesta() {
        return ultimaApuesta;
    }

    public void setUltimaApuesta(int ultimaApuesta) {
        this.ultimaApuesta = ultimaApuesta;
    }

    public String getCartasMesa() {
        return cartasMesa;
    }

    public void setCartasMesa(String cartasMesa) {
        this.cartasMesa = cartasMesa;
    }

    public String getMazo() {
        return mazo;
    }

    public void setMazo(String mazo) {
        this.mazo = mazo;
    }
}
