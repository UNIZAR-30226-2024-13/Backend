package com.proyectosoftware.backend.database.entidades;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "session")
public class Session {

    @MapsId
    @OneToOne
    @JoinColumn(name = "sesionUsuario", referencedColumnName = "sesionUsuario")
    private UsuarioEntidad usuario;

    @Id
    @Column(name = "sessionId")
    private String sessionId;

    @Column(name = "sessionToken", nullable = false, unique = true)
    private String sessionToken;

    @CreatedDate
    @Column(name = "creationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp creationDate;

    public Session(UsuarioEntidad usuario){
        this.usuario = usuario;
        this.sessionId = usuario.getId();
        this.sessionToken = generateSessionToken();
    }

    public Session(){
        super();
    }

    @PrePersist
    @PreUpdate
    public void onPrePersist() {
        this.creationDate = Timestamp.valueOf(LocalDateTime.now());
    }

    @PreRemove
    public void onRemove(){
        this.usuario.deleteSession();
    }

	public String getSessionId() {
		return sessionId;
	}

	public String getSessionToken() {
		return sessionToken;
	}    

    public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public String generateSessionToken(){
        return UUID.randomUUID().toString();
    }
}
