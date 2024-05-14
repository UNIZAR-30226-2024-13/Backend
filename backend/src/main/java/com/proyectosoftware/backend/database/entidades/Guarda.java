package com.proyectosoftware.backend.database.entidades;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "guarda")
@IdClass(Guarda.IdCompuesto.class)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties({"partida","usuario"})
public class Guarda {

    static protected class IdCompuesto implements Serializable{
        private String idUsuario;
        private String idPartida;
		public IdCompuesto(String idUsuario, String idPartida) {
			this.idUsuario = idUsuario;
			this.idPartida = idPartida;
		}
        public IdCompuesto(){

        }
		public String getIdUsuario() {
			return idUsuario;
		}
		public void setIdUsuario(String idUsuario) {
			this.idUsuario = idUsuario;
		}
		public String getIdPartida() {
			return idPartida;
		}
		public void setIdPartida(String idPartida) {
			this.idPartida = idPartida;
		}
    }

    @MapsId
    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "id")
    @JsonIncludeProperties({"id", "nombre"})
    private UsuarioEntidad usuario;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "idPartida", referencedColumnName = "id")
    @JsonIncludeProperties({"id"})
    private Partida partida;

    @Id
    private String idUsuario;
    
    @Id
    private String idPartida;

    @Column(name = "cartasUsuario")
    private String cartas;

    @Column(name = "turnoEnPartida")
    private Integer turnoEnPartida;

	public UsuarioEntidad getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntidad usuario) {
		this.usuario = usuario;
        this.setIdUsuario(usuario.getId());
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
        this.setIdPartida(partida.getId());
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIdPartida() {
		return idPartida;
	}

	public void setIdPartida(String idPartida) {
		this.idPartida = idPartida;
	}
}
