package com.proyectosoftware.backend.database.entidades;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "partida")
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "turno", columnDefinition = "integer default 20", nullable = false)
    private int turno;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "guarda",
        joinColumns = @JoinColumn(name = "partida"),
        inverseJoinColumns = @JoinColumn(name = "usuario")
    )
    private Set<Usuario> usuarios = new HashSet<>();

    public Partida() {}

    public Partida(Long id, int turno) {
        this.id = id;
        this.turno = turno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
