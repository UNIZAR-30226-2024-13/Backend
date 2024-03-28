package com.proyectosoftware.backend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.proyectosoftware.backend.modelo.Carta;
import com.proyectosoftware.backend.modelo.Partida;
import com.proyectosoftware.backend.modelo.Usuario;
import com.proyectosoftware.backend.modelo.barajas.BarajaFrancesa;
import com.proyectosoftware.backend.modelo.interfaces.Baraja;
import com.proyectosoftware.backend.modelo.interfaces.Estado;
import com.proyectosoftware.backend.modelo.interfaces.JuegoConApuesta;

import com.proyectosoftware.backend.modelo.juegos.Poker;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
		Poker poker = new Poker();
		List<Usuario> usuarios = new ArrayList<>();
		Usuario usuario;
		for (int i = 0; i < 4; i++) {
			usuario = new Usuario ("Nombre", "email", 12.0, "España");
			usuarios.add(usuario);
		}
		poker.repartirCartas(usuarios);
		//poker.agnadirCartaCentro();
		//poker.agnadirCartaCentro();
		//poker.ganadorPartida(usuarios);
	}

}
