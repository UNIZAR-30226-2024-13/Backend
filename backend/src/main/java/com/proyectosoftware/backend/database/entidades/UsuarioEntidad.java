package com.proyectosoftware.backend.database.entidades;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class UsuarioEntidad {
    
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "nombre", nullable = false, length = 30, unique = true)
    private String nombre;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "fichas", columnDefinition = "integer default 100")
    private int fichas;

    @Column(name = "pais")
    private String pais;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Login login;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
        name = "amigos",
        joinColumns = @JoinColumn(name = "usuario1", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "usuario2", referencedColumnName = "id")
    )
    @JsonIgnoreProperties({ "amigos","partidas" })
    @JsonIncludeProperties({ "id", "nombre" })
    private Set<UsuarioEntidad> amigos;

    @ManyToMany(mappedBy = "usuarios")
    private Set<Partida> partidas = new HashSet<>();

    public UsuarioEntidad() {}

    public UsuarioEntidad(String id, String nombre, String email, int fichas, String pais) {
        this.nombre = nombre;
        this.email = email;
        this.fichas = fichas;
        this.pais = pais;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFichas() {
        return fichas;
    }

    public void setFichas(int fichas) {
        this.fichas = fichas;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @ManyToMany()
    public Set<UsuarioEntidad> getAmigos() {
        return amigos;
    }

    public void setAmigos(Set<UsuarioEntidad> amigos) {
        this.amigos = amigos;
    }

    public Set<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(Set<Partida> partidas) {
        this.partidas = partidas;
    }
}
