package com.proyectosoftware.backend.modelo.juegos;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.proyectosoftware.backend.modelo.Carta;
import com.proyectosoftware.backend.modelo.Usuario;
import com.proyectosoftware.backend.modelo.barajas.BarajaEspaniola;

public class CinquilloTest {
    private Usuario usuario1;
    private Usuario usuario2;
    private Usuario usuario3;
    private Usuario usuario4;

    private Cinquillo cinquillo;

    @BeforeEach
    public void setup(){
        this.usuario1 = new Usuario("1","1", 0,"1");
        this.usuario2 = new Usuario("2","2", 0,"2");
        this.usuario3 = new Usuario("3","3", 0,"3");
        this.usuario4 = new Usuario("4","4", 0,"4");
        this.cinquillo = new Cinquillo();
        
        cinquillo.nuevoUsuario(usuario1);
        cinquillo.nuevoUsuario(usuario2);
        cinquillo.nuevoUsuario(usuario3);
        cinquillo.nuevoUsuario(usuario4);
    }

    @Test
    public void estadoCorrecto(){
        cinquillo.iniciarPartida();
        JSONObject json = cinquillo.guardar();

        Assertions.assertThat(((String)json.get("ID"))).startsWith("juego-");
        System.out.println(((String)json.get("ID")));

        Assertions.assertThat(json.get("turno")).isIn(0,1,2,3);
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
            Assertions.assertThat(escalera.get("palo")).asString().matches("(oros)?(copas)?(espadas)?(bastos)?");
            if(escalera.get("cartas") != null) {
                Assertions.assertThat(escalera.get("cartas")).asString().matches("([0-9]+,[0-9]+(;[0-9]+,[0-9]+)*)?");
            }
        }
    }

    @Test
    public void cargaEstadoCorrecto(){
        JSONObject json = cinquillo.guardar();
        cinquillo = new Cinquillo(json);

        Assertions.assertThat(((String)json.get("ID"))).startsWith("juego-");
        System.out.println(((String)json.get("ID")));

        Assertions.assertThat(json.get("turno")).isIn(0,1,2,3);
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
            Assertions.assertThat(escalera.get("palo")).asString().matches("(oros)?(copas)?(espadas)?(bastos)?");
            if(escalera.get("cartas") != null) {
                Assertions.assertThat(escalera.get("cartas")).asString().matches("([0-9]+,[0-9]+(;[0-9]+,[0-9]+)*)?");
            }        
        }
    }
    
    @Test
    public void testIniciarPartida() {
        cinquillo.iniciarPartida();
        JSONObject json = cinquillo.guardar();
        cinquillo = new Cinquillo(json);

        JSONArray escaleras = (JSONArray)json.get("escaleras");
        for (Object object : escaleras) {
            JSONObject escalera = (JSONObject)object;
            if(escalera.get("palo").equals(BarajaEspaniola.OROS)){
                Assertions.assertThat(escalera.get("cartas")).asString().matches("0,5");
            }       
        }
    }

    private String cartasToString(List<Carta> cartas){
        return String.join(";", cartas.stream().map(Carta::toString).toList());
    }

    @SuppressWarnings("unchecked")
    @Test
    void testJugada() {
        JSONObject estado = new JSONObject();
        JSONArray usuariosArray = new JSONArray();
        JSONArray escalerasArray = new JSONArray();
        
        JSONObject usuarioJSON = new JSONObject();
        usuarioJSON.put("ID", usuario1.getID());
        usuarioJSON.put("turno_en_juego", 0);

        List<Carta> cartas = new ArrayList<>();
        cartas.add(new Carta(0,0));
        cartas.add(new Carta(1,0));
        cartas.add(new Carta(2,0));
        cartas.add(new Carta(3,0));
        cartas.add(new Carta(4,0));
        cartas.add(new Carta(6,0));
        cartas.add(new Carta(7,0));
        cartas.add(new Carta(8,0));
        cartas.add(new Carta(9,0));
        cartas.add(new Carta(10,0));

        usuarioJSON.put("cartas", cartasToString(cartas));
        usuariosArray.add(usuarioJSON);

        usuarioJSON.put("ID", usuario2.getID());
        usuarioJSON.put("turno_en_juego", 1);

        cartas = new ArrayList<>();
        cartas.add(new Carta(0,1));
        cartas.add(new Carta(1,1));
        cartas.add(new Carta(2,1));
        cartas.add(new Carta(3,1));
        cartas.add(new Carta(4,1));
        cartas.add(new Carta(5,1));
        cartas.add(new Carta(6,1));
        cartas.add(new Carta(7,1));
        cartas.add(new Carta(8,1));
        cartas.add(new Carta(9,1));
        cartas.add(new Carta(10,1));

        usuarioJSON.put("cartas", cartasToString(cartas));
        usuariosArray.add(usuarioJSON);

        usuarioJSON.put("ID", usuario3.getID());
        usuarioJSON.put("turno_en_juego", 2);

        cartas = new ArrayList<>();
        cartas.add(new Carta(0,2));
        cartas.add(new Carta(1,2));
        cartas.add(new Carta(2,2));
        cartas.add(new Carta(3,2));
        cartas.add(new Carta(4,2));
        cartas.add(new Carta(5,2));
        cartas.add(new Carta(6,2));
        cartas.add(new Carta(7,2));
        cartas.add(new Carta(8,2));
        cartas.add(new Carta(9,2));
        cartas.add(new Carta(10,2));

        usuarioJSON.put("cartas", cartasToString(cartas));
        usuariosArray.add(usuarioJSON);

        usuarioJSON.put("ID", usuario4.getID());
        usuarioJSON.put("turno_en_juego", 3);

        cartas = new ArrayList<>();
        cartas.add(new Carta(0,3));
        cartas.add(new Carta(1,3));
        cartas.add(new Carta(2,3));
        cartas.add(new Carta(3,3));
        cartas.add(new Carta(4,3));
        cartas.add(new Carta(5,3));
        cartas.add(new Carta(6,3));
        cartas.add(new Carta(7,3));
        cartas.add(new Carta(8,3));
        cartas.add(new Carta(9,3));
        cartas.add(new Carta(10,3));

        usuarioJSON.put("cartas", cartasToString(cartas));
        usuariosArray.add(usuarioJSON);

        JSONObject escaleraJSON = new JSONObject();
        escaleraJSON.put("palo", BarajaEspaniola.OROS);
        cartas = new ArrayList<>();
        cartas.add(new Carta(5, 0));
        escaleraJSON.put("cartas", cartasToString(cartas));
        escalerasArray.add(escaleraJSON);

        escaleraJSON = new JSONObject();
        escaleraJSON.put("palo", BarajaEspaniola.COPAS);
        cartas = new ArrayList<>();
        escaleraJSON.put("cartas", cartasToString(cartas));
        escalerasArray.add(escaleraJSON);

        escaleraJSON = new JSONObject();
        escaleraJSON.put("palo", BarajaEspaniola.ESPADAS);
        cartas = new ArrayList<>();
        escaleraJSON.put("cartas", cartasToString(cartas));
        escalerasArray.add(escaleraJSON);

        escaleraJSON = new JSONObject();
        escaleraJSON.put("palo", BarajaEspaniola.BASTOS);
        cartas = new ArrayList<>();
        escaleraJSON.put("cartas", cartasToString(cartas));
        escalerasArray.add(escaleraJSON);

        estado.put("ID", "juego-Cinquillo");
        estado.put("turno", 1);
        estado.put("primer_jugador", 0);
        estado.put("usuarios", usuariosArray);
        estado.put("escaleras", escalerasArray);

        JSONObject json = estado;
        cinquillo = new Cinquillo(json);
        cinquillo.jugada(usuario2, new Carta(3, 2));
        
        JSONArray escaleras = (JSONArray)json.get("escaleras");
        for (Object object : escaleras) {
            JSONObject escalera = (JSONObject)object;
            if(escalera.get("palo").equals(BarajaEspaniola.ESPADAS)){
                Assertions.assertThat(escalera.get("cartas")).asString().matches("2,3");
            }       
        }

        JSONArray usuarios = (JSONArray)json.get("usuarios");
        for (Object object : usuarios) {
            JSONObject usuario = (JSONObject)object;
            if(usuario.get("ID").equals(usuario2.getID())){
                Assertions.assertThat(usuario.get("cartas")).asString().doesNotContain("2,3");
            }       
        }
    }
}
