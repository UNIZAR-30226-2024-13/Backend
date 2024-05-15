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

    static public class IdCompuesto implements Serializable{
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
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
			result = prime * result + ((idPartida == null) ? 0 : idPartida.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			IdCompuesto other = (IdCompuesto) obj;
			return idPartida.equals(other.idPartida) && idUsuario.equals(idUsuario);
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
	@Column(name = "idUsuario")
    private String idUsuario;
    
    @Id
	@Column(name = "idPartida")
    private String idPartida;

    @Column(name = "cartasUsuario", columnDefinition = "character varying 255 default ''")
    private String cartas = "";

    @Column(name = "turno_en_partida", columnDefinition = "integer default -1")
    private Integer turnoEnPartida = -1;

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
