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

    @Column(name = "numero")
    private int numero;

    @Column(name = "ultimasCartas")
    private int ultimasCartas;

    public Mentiroso() {}

    public Mentiroso(PartidaId id, String cartasMesa, int numero, int ultimasCartas) {
        this.id = id;
        this.cartasMesa = cartasMesa;
        this.numero = numero;
        this.ultimasCartas = ultimasCartas;
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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getUltimasCartas() {
        return ultimasCartas;
    }

    public void setUltimasCartas(int ultimasCartas) {
        this.ultimasCartas = ultimasCartas;
    }
}
