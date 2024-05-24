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
import com.proyectosoftware.backend.modelo.barajas.BarajaUNO;
import com.proyectosoftware.backend.modelo.interfaces.Juego;

public class UNOTest {
    private Usuario usuario1;
    private Usuario usuario2;
    private Usuario usuario3;
    private Usuario usuario4;

    private UNO uno;

    @BeforeEach
    public void setup(){
        this.usuario1 = new Usuario("1","1", 0,"1");
        this.usuario2 = new Usuario("2","2", 0,"2");
        this.usuario3 = new Usuario("3","3", 0,"3");
        this.usuario4 = new Usuario("4","4", 0,"4");
        this.uno = new UNO();
        
        
        uno.nuevoUsuario(usuario1);
        uno.nuevoUsuario(usuario2);
        uno.nuevoUsuario(usuario3);
        uno.nuevoUsuario(usuario4);
    }

    @Test
    public void estadoCorrecto(){
        uno.iniciarPartida();
        JSONObject json = uno.guardar();

        Assertions.assertThat(((String)json.get("ID"))).startsWith("juego-");
        System.out.println(((String)json.get("ID")));

        Assertions.assertThat(json.get("turno")).isIn(0,1,2,3);
        Assertions.assertThat(json.get("sentido")).isIn(0,1);
        Assertions.assertThat(json.get("ultima_carta")).asString().matches("([0-9]+,[0-9]+(;[0-9]+,[0-9]+)*)?");
        Assertions.assertThat(json.get("primer_jugador")).isIn(0,1,2,3);
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
        uno.iniciarPartida();
        JSONObject json = uno.guardar();

        Assertions.assertThat(((String)json.get("ID"))).startsWith("juego-");
        System.out.println(((String)json.get("ID")));

        Assertions.assertThat(json.get("turno")).isIn(0,1,2,3);
        Assertions.assertThat(json.get("sentido")).isIn(0,1);
        Assertions.assertThat(json.get("ultima_carta")).asString().matches("([0-9]+,[0-9]+(;[0-9]+,[0-9]+)*)?");
        Assertions.assertThat(json.get("primer_jugador")).isIn(0,1,2,3);

        JSONArray usuarios = (JSONArray)json.get("usuarios");
        for (Object object : usuarios) {
            JSONObject usuario = (JSONObject)object;
            Assertions.assertThat(usuario.get("ID")).asString().startsWith("Usuario-");
            Assertions.assertThat(usuario.get("turno_en_juego")).isIn(0,1,2,3);
            Assertions.assertThat(usuario.get("cartas")).asString().matches("([0-9]+,[0-9]+(;[0-9]+,[0-9]+)*)?");
        }
    }
    
    @Test
    public void testIniciarPartida() {
        uno.iniciarPartida();
        JSONObject json = uno.guardar();
        uno = new UNO(json);
    }

    private String cartasToString(List<Carta> cartas){
        return String.join(";", cartas.stream().map(Carta::toString).toList());
    }

    @SuppressWarnings("unchecked")
    @Test
    void testJugada() {
        JSONObject estado = new JSONObject();
        JSONArray usuariosArray = new JSONArray();
        
        JSONObject usuarioJSON = new JSONObject();
        JSONObject usuarioJSON1 = new JSONObject();
        JSONObject usuarioJSON2 = new JSONObject();
        JSONObject usuarioJSON3 = new JSONObject();
        usuarioJSON.put("ID", usuario1.getID());
        usuarioJSON.put("turno_en_juego", 0);

        List<Carta> cartas = new ArrayList<>();
        cartas.add(new Carta(1,0));
        cartas.add(new Carta(2,0));
        cartas.add(new Carta(3,0));
        cartas.add(new Carta(4,0));
        cartas.add(new Carta(5,0));
        cartas.add(new Carta(6,0));
        cartas.add(new Carta(7,0));


        usuarioJSON.put("cartas", cartasToString(cartas));
        usuariosArray.add(usuarioJSON);

        usuarioJSON1.put("ID", usuario2.getID());
        usuarioJSON1.put("turno_en_juego", 1);

        cartas = new ArrayList<>();
        cartas.add(new Carta(1,1));
        cartas.add(new Carta(2,1));
        cartas.add(new Carta(3,1));
        cartas.add(new Carta(4,1));
        cartas.add(new Carta(5,1));
        cartas.add(new Carta(6,1));
        cartas.add(new Carta(7,1));

        usuarioJSON1.put("cartas", cartasToString(cartas));
        usuariosArray.add(usuarioJSON1);

        usuarioJSON2.put("ID", usuario3.getID());
        usuarioJSON2.put("turno_en_juego", 2);

        cartas = new ArrayList<>();
        cartas.add(new Carta(1,2));
        cartas.add(new Carta(2,2));
        cartas.add(new Carta(3,2));
        cartas.add(new Carta(4,2));
        cartas.add(new Carta(5,2));
        cartas.add(new Carta(6,2));
        cartas.add(new Carta(7,2));

        usuarioJSON2.put("cartas", cartasToString(cartas));
        usuariosArray.add(usuarioJSON2);

        usuarioJSON3.put("ID", usuario4.getID());
        usuarioJSON3.put("turno_en_juego", 3);

        cartas = new ArrayList<>();
        cartas.add(new Carta(1,3));
        cartas.add(new Carta(2,3));
        cartas.add(new Carta(3,3));
        cartas.add(new Carta(4,3));
        cartas.add(new Carta(5,3));
        cartas.add(new Carta(6,3));
        cartas.add(new Carta(7,3));

        usuarioJSON3.put("cartas", cartasToString(cartas));
        usuariosArray.add(usuarioJSON3);

        cartas = new ArrayList<>();
        cartas.add(new Carta(2,2));
        cartas.add(new Carta(4,2));
        cartas.add(new Carta(1,2));

        estado.put("ID", "juego-UNO");
        estado.put("turno", 2);
        estado.put("sentido",0);
        estado.put("primer_jugador", 0);
        estado.put("usuarios", usuariosArray);
        estado.put("ultima_carta",cartasToString(cartas));

        JSONObject json = estado;
        uno = new UNO(json);
        //uno.jugada(usuario3, new Carta(3, 2),0);
        json = uno.guardar();

        JSONArray usuarios = (JSONArray)json.get("usuarios");
        for (Object object : usuarios) {
            JSONObject usuario = (JSONObject)object;
            if(usuario.get("ID").equals(usuario3.getID())){
                Assertions.assertThat(usuario.get("cartas")).asString().doesNotContain("2,3");
            }       
        }
    }

}
