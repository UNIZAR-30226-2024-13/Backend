package com.proyectosoftware.backend.modelo.juegos;

import org.junit.jupiter.api.Test;
import com.proyectosoftware.backend.modelo.Usuario;
import org.assertj.core.api.Assertions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PokerTest {

    private Usuario usuario1;
    private Usuario usuario2;
    private Usuario usuario3;
    private Usuario usuario4;

    private Poker poker;

    private JSONObject json;

    @BeforeEach
    public void setup(){
        this.usuario1 = new Usuario("1","1", 20,"1");
        this.usuario2 = new Usuario("2","2", 15,"2");
        this.usuario3 = new Usuario("3","3", 12,"3");
        this.usuario4 = new Usuario("4","4", 77,"4");
        this.poker = new Poker();
        
        poker.nuevoUsuario(usuario1);
        poker.nuevoUsuario(usuario2);
        poker.nuevoUsuario(usuario3);
        poker.nuevoUsuario(usuario4);

        json = poker.guardar();
    }


    @Test
    void testCargar() {
        poker = new Poker(json);
        Assertions.assertThat(((String)json.get("ID"))).startsWith("juego-");
        Assertions.assertThat(json.get("Turno")).isIn(0,1,2,3);
        Assertions.assertThat(json.get("Fichas_mesa")).isInstanceOf(Integer.class);
        Assertions.assertThat(json.get("Cartas_mazo")).asString().matches("([0-9]+,[0-9]+(;[0-9]+,[0-9]+)*)?");
        Assertions.assertThat(json.get("Cartas_mesa")).asString().matches("([0-9]+,[0-9]+(;[0-9]+,[0-9]+)*)?");

        JSONArray usuarios = (JSONArray)json.get("Usuarios");
        for (Object object : usuarios) {
            JSONObject usuario = (JSONObject) object;
            Assertions.assertThat(usuario.get("ID")).asString().startsWith("Usuario-");
            Assertions.assertThat(usuario.get("Fichas")).isInstanceOf(Integer.class);
            Assertions.assertThat(usuario.get("Cartas")).asString().matches("([0-9]+,[0-9]+(;[0-9]+,[0-9]+)*)?");
        }
    }


    @Test
    void testGuardar() {
        Assertions.assertThat(((String)json.get("ID"))).startsWith("juego-");
        Assertions.assertThat(json.get("Turno")).isIn(0,1,2,3);
        Assertions.assertThat(json.get("Fichas_mesa")).isInstanceOf(Integer.class);
        Assertions.assertThat(json.get("Cartas_mazo")).asString().matches("([0-9]+,[0-9]+(;[0-9]+,[0-9]+)*)?");
        Assertions.assertThat(json.get("Cartas_mesa")).asString().matches("([0-9]+,[0-9]+(;[0-9]+,[0-9]+)*)?");

        JSONArray usuarios = (JSONArray)json.get("Usuarios");
        for (Object object : usuarios) {
            JSONObject usuario = (JSONObject) object;
            Assertions.assertThat(usuario.get("ID")).asString().startsWith("Usuario-");
            Assertions.assertThat(usuario.get("Fichas")).isInstanceOf(Integer.class);
            Assertions.assertThat(usuario.get("Cartas")).asString().matches("([0-9]+,[0-9]+(;[0-9]+,[0-9]+)*)?");
        }
    }
}
