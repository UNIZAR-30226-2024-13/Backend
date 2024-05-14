package com.proyectosoftware.backend.database.entidades;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "mentiroso")
@JsonInclude(value = Include.NON_NULL)
public class MentirosoEntidad extends Partida{
    
    @Column(name = "cartas_mesa")
    private String cartasMesa;
    
    @Column(name = "numero")
    private int numero;
    
    @Column(name = "ultimasCartas")
    private int cartasUltimaJugada;

    public MentirosoEntidad() {}

    public MentirosoEntidad(String id, String cartasMesa, int numero, int ultimasCartas) {
        super(id);
        this.cartasMesa = cartasMesa;
        this.numero = numero;
        this.cartasUltimaJugada = ultimasCartas;
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

    public int getCartasUltimaJugada() {
        return cartasUltimaJugada;
    }

    public void setUltimasCartas(int cartasUltimaJugada) {
        this.cartasUltimaJugada = cartasUltimaJugada;
    }
}
