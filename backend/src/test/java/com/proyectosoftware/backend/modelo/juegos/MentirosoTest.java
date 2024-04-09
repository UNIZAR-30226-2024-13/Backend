package com.proyectosoftware.backend.modelo.juegos;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.assertj.core.api.Assertions;

import com.proyectosoftware.backend.modelo.Usuario;
import com.proyectosoftware.backend.modelo.interfaces.Juego;

public class MentirosoTest {
    private Usuario usuario1;
    private Usuario usuario2;
    private Usuario usuario3;
    private Usuario usuario4;

    private Juego juego;
    private Mentiroso mentiroso;

    @BeforeEach
    public void setup(){
        this.usuario1 = new Usuario("1","1", 0,"1");
        this.usuario2 = new Usuario("2","2", 0,"2");
        this.usuario3 = new Usuario("3","3", 0,"3");
        this.usuario4 = new Usuario("4","4", 0,"4");
        this.juego = new Mentiroso();
    }

    @Test
    public void estadoCorrecto(){
        juego.nuevoUsuario(usuario1);
        juego.nuevoUsuario(usuario2);
        juego.nuevoUsuario(usuario3);
        juego.nuevoUsuario(usuario4);

        mentiroso = ((Mentiroso)juego);

        JSONObject json = mentiroso.guardar();

        Assertions.assertThat(((String)json.get("ID"))).startsWith("juego-");
        System.out.println(((String)json.get("ID")));

        Assertions.assertThat(json.get("turno")).isEqualTo(0);
        Assertions.assertThat(json.get("cartas_mesa")).isEqualTo("");
        Assertions.assertThat(json.get("ultimas_cartas")).isEqualTo(0);
        Assertions.assertThat(json.get("numero_actual")).isEqualTo(-1);
        JSONArray usuarios = (JSONArray)json.get("usuarios");
        for (Object object : usuarios) {
            JSONObject usuario = (JSONObject)object;
            Assertions.assertThat(usuario.get("ID")).asString().startsWith("Usuario-");
            Assertions.assertThat(usuario.get("turno_en_juego")).isIn(0,1,2,3);
            Assertions.assertThat(usuario.get("cartas")).asString().matches("([0-9]+,[0-9]+(;[0-9]+,[0-9]+)*)?");
        }
    }

    @Test
    public void cargaEstadoCorrecto(){
        juego.nuevoUsuario(usuario1);
        juego.nuevoUsuario(usuario2);
        juego.nuevoUsuario(usuario3);
        juego.nuevoUsuario(usuario4);

        mentiroso = ((Mentiroso)juego);

        JSONObject json = mentiroso.guardar();

        mentiroso = new Mentiroso(json);

        Assertions.assertThat(((String)json.get("ID"))).startsWith("juego-");
        System.out.println(((String)json.get("ID")));

        Assertions.assertThat(json.get("turno")).isEqualTo(0);
        Assertions.assertThat(json.get("cartas_mesa")).isEqualTo("");
        Assertions.assertThat(json.get("ultimas_cartas")).isEqualTo(0);
        Assertions.assertThat(json.get("numero_actual")).isEqualTo(-1);

        JSONArray usuarios = (JSONArray)json.get("usuarios");
        for (Object object : usuarios) {
            JSONObject usuario = (JSONObject)object;
            Assertions.assertThat(usuario.get("ID")).asString().startsWith("Usuario-");
            Assertions.assertThat(usuario.get("turno_en_juego")).isIn(0,1,2,3);
            Assertions.assertThat(usuario.get("cartas")).asString().matches("([0-9]+,[0-9]+(;[0-9]+,[0-9]+)*)?");
        }
    }
}