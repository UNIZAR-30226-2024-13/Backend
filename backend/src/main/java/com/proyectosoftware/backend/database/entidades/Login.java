package com.proyectosoftware.backend.database.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "login")
public class Login {
    
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    @MapsId
    private UsuarioEntidad usuario;
    
    @Id
    @Column(name = "id")
    private String userID;

    @Column(name = "hashPasswd", nullable = false)
    private String hashPasswd;

    public Login() {
    }

    public Login(String userID, String hashPasswd) {
        this.userID = userID;
        this.hashPasswd = hashPasswd;
    }

    public Login(UsuarioEntidad usuarioEntidad) {
		this();
        this.usuario = usuarioEntidad;
	}

	public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getHashPasswd() {
        return hashPasswd;
    }

    public void setHashPasswd(String hashPasswd) {
        this.hashPasswd = hashPasswd;
    }
}
