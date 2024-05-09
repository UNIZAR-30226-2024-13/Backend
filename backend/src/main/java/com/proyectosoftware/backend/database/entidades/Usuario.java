package com.proyectosoftware.backend.database.entidades;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
    
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

    @ManyToMany
    @JoinTable(
        name = "amigos",
        joinColumns = @JoinColumn(name = "usuario1"),
        inverseJoinColumns = @JoinColumn(name = "usuario2")
    )
    private Set<Usuario> amigos;

    @ManyToMany(mappedBy = "usuarios")
    private Set<Partida> partidas = new HashSet<>();

    public Usuario() {}

    public Usuario(String id, String nombre, String email, int fichas, String pais) {
        this.id = id;
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

    public Set<Usuario> getAmigos() {
        return amigos;
    }

    public void setAmigos(Set<Usuario> amigos) {
        this.amigos = amigos;
    }

    public Set<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(Set<Partida> partidas) {
        this.partidas = partidas;
    }
}
