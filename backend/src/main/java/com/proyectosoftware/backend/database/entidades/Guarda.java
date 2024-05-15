package com.proyectosoftware.backend.database.entidades;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "guarda")
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties({"partida", "usuario"})
public class Guarda {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "gId")
	private Long id;
    
    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "id")
    @JsonIncludeProperties({"id", "nombre"})
    private UsuarioEntidad usuario;

    @ManyToOne
    @JoinColumn(name = "idPartida", referencedColumnName = "id")
    @JsonIncludeProperties({"id"})
    private Partida partida;

    @Column(name = "cartasUsuario")
    private String cartas = "";

    @Column(name = "turno_en_partida", columnDefinition = "integer default 0")
    private Integer turnoEnPartida = 0;

	public Guarda(){

	}

	public Guarda(UsuarioEntidad usuario, Partida partida, String cartas, Integer turnoEnPartida){
		this.usuario = usuario;
		this.partida = partida;
		this.cartas = cartas;
		this.turnoEnPartida = turnoEnPartida;
	}

	public UsuarioEntidad getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntidad usuario) {
		this.usuario = usuario;
	}

	public String getCartas() {
		return cartas;
	}

	public void setCartas(String cartas) {
		this.cartas = cartas;
	}

	public Integer getTurnoEnPartida() {
		return turnoEnPartida;
	}

	public void setTurnoEnPartida(Integer turnoEnPartida) {
		this.turnoEnPartida = turnoEnPartida;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public String getIdUsuario() {
		return usuario.getId();
	}

	public String getIdPartida() {
		return partida.getId();
	}
}
