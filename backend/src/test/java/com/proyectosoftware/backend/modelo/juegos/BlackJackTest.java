package com.proyectosoftware.backend.modelo.juegos;

import com.proyectosoftware.backend.modelo.Usuario;
import org.assertj.core.api.Assertions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BlackJackTest {

    private Usuario usuario1;
    private Usuario usuario2;
    private Usuario usuario3;
    private Usuario usuario4;
    private BlackJack blackjack;

    @BeforeEach
    public void setup(){
        this.usuario1 = new Usuario("1","1", 20,"1");
        this.usuario2 = new Usuario("2","2", 15,"2");
        this.usuario3 = new Usuario("3","3", 12,"3");
        this.usuario4 = new Usuario("4","4", 77,"4");
        this.blackjack = new BlackJack();
        
        blackjack.nuevoUsuario(usuario1);
        blackjack.nuevoUsuario(usuario2);
        blackjack.nuevoUsuario(usuario3);
        blackjack.nuevoUsuario(usuario4);
        blackjack.iniciarPartida();
    }

    @Test
    void testCargar() {

    }

    @Test
    void testGuardar() {

    }
}