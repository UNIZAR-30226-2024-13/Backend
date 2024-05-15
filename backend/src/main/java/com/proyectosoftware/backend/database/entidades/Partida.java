package com.proyectosoftware.backend.database.entidades;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Partida {

    @Id
    @Column(name = "id")
    private String id;
    
    @Column(name = "turno", nullable = false)
    private int turno;
    
    @Column(name = "usuarioGanador", nullable = true)
    private String usuarioGanador;

    @Column(name = "activa")
    private boolean activa;

    @Column(name = "privada")
    private boolean privada;

    /*
     * 
     @ManyToMany(cascade = CascadeType.ALL)
     @JoinTable(
         name = "guarda",
         joinColumns = @JoinColumn(name = "partida"),
         inverseJoinColumns = @JoinColumn(name = "usuario")
         )
         @JsonIgnoreProperties({"amigos", "partidas"})
         @JsonIncludeProperties({"id", "nombre"})
         private Set<UsuarioEntidad> usuarios = new HashSet<>();
         */

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partida")
    private Set<Guarda> guarda = new HashSet<>();

    public Partida() {}

    public Partida(String id) {
        this.id = id;
    }

    public Partida(String id, int turno) {
        this.id = id;
        this.turno = turno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }
/*
 * 
 public Set<UsuarioEntidad> getUsuarios() {
     return usuarios;
    }
    
    public void setUsuarios(Set<UsuarioEntidad> usuarios) {
        this.usuarios = usuarios;
    }
    */

	public String getUsuarioGanador() {
		return usuarioGanador;
	}

	public void setUsuarioGanador(String usuarioGanador) {
		this.usuarioGanador = usuarioGanador;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	public boolean isPrivada() {
		return privada;
	}

	public void setPrivada(boolean privada) {
		this.privada = privada;
	}

    @OneToMany
	public Set<Guarda> getGuarda() {
		return guarda;
	}

	public void setGuarda(Set<Guarda> guarda) {
		this.guarda = guarda;
	}
}
