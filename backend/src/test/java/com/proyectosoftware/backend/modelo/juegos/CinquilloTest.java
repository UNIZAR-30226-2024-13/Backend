package com.proyectosoftware.backend.modelo.juegos;

import org.assertj.core.api.Assertions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.proyectosoftware.backend.modelo.Usuario;
import com.proyectosoftware.backend.modelo.interfaces.Juego;

public class CinquilloTest {
    private Usuario usuario1;
    private Usuario usuario2;
    private Usuario usuario3;
    private Usuario usuario4;

    private Juego juego;
    private Cinquillo cinquillo;

    @BeforeEach
    public void setup(){
        this.usuario1 = new Usuario("1","1", 0,"1");
        this.usuario2 = new Usuario("2","2", 0,"2");
        this.usuario3 = new Usuario("3","3", 0,"3");
        this.usuario4 = new Usuario("4","4", 0,"4");
        this.juego = new Cinquillo();
    }

    @Test
    public void estadoCorrecto(){
        juego.nuevoUsuario(usuario1);
        juego.nuevoUsuario(usuario2);
        juego.nuevoUsuario(usuario3);
        juego.nuevoUsuario(usuario4);

        cinquillo = ((Cinquillo)juego);

        JSONObject json = cinquillo.guardar();

        Assertions.assertThat(((String)json.get("ID"))).startsWith("juego-");
        System.out.println(((String)json.get("ID")));

        Assertions.assertThat(json.get("turno")).isEqualTo(0);
        Assertions.assertThat(json.get("primer_jugador")).isIn(0,1,2,3);
        JSONArray usuarios = (JSONArray)json.get("usuarios");
        for (Object object : usuarios) {
            JSONObject usuario = (JSONObject)object;
            Assertions.assertThat(usuario.get("ID")).asString().startsWith("Usuario-");
            Assertions.assertThat(usuario.get("turno_en_juego")).isIn(0,1,2,3);
            Assertions.assertThat(usuario.get("cartas")).asString().matches("([0-9]+,[0-9]+(;[0-9]+,[0-9]+)*)?");
        }
        JSONArray escaleras = (JSONArray)json.get("escaleras");
        for (Object object : escaleras) {
            JSONObject escalera = (JSONObject)object;
            Assertions.assertThat(escalera.get("palo")).asString().matches("(OROS)?(COPAS)?(BASTOS)?(ESPADAS)?");
            Assertions.assertThat(escalera.get("cartas")).asString().matches("([0-9]+,[0-9]+(;[0-9]+,[0-9]+)*)?");
        }
    }

    @Test
    public void cargaEstadoCorrecto(){
        juego.nuevoUsuario(usuario1);
        juego.nuevoUsuario(usuario2);
        juego.nuevoUsuario(usuario3);
        juego.nuevoUsuario(usuario4);

        cinquillo = ((Cinquillo)juego);

        JSONObject json = cinquillo.guardar();

        cinquillo = new Cinquillo(json);

        Assertions.assertThat(((String)json.get("ID"))).startsWith("juego-");
        System.out.println(((String)json.get("ID")));

        Assertions.assertThat(json.get("turno")).isEqualTo(0);
        Assertions.assertThat(json.get("primer_jugador")).isIn(0,1,2,3);

        JSONArray usuarios = (JSONArray)json.get("usuarios");
        for (Object object : usuarios) {
            JSONObject usuario = (JSONObject)object;
            Assertions.assertThat(usuario.get("ID")).asString().startsWith("Usuario-");
            Assertions.assertThat(usuario.get("turno_en_juego")).isIn(0,1,2,3);
            Assertions.assertThat(usuario.get("cartas")).asString().matches("([0-9]+,[0-9]+(;[0-9]+,[0-9]+)*)?");
        }
        JSONArray escaleras = (JSONArray)json.get("escaleras");
        for (Object object : escaleras) {
            JSONObject escalera = (JSONObject)object;
            Assertions.assertThat(escalera.get("palo")).asString().matches("(OROS)?(COPAS)?(BASTOS)?(ESPADAS)?");
            Assertions.assertThat(escalera.get("cartas")).asString().matches("([0-9]+,[0-9]+(;[0-9]+,[0-9]+)*)?");
        }
    }
}
