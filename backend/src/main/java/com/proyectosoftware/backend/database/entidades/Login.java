package com.proyectosoftware.backend.database.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "login")
public class Login {

    @Id
    @Column(name = "id")
    private String userID;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Usuario usuario;

    @Column(name = "hashPasswd")
    private String passwd;

    public Login() {
    }

    public Login(String userID, String passwd) {
        this.userID = userID;
        this.passwd = passwd;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
