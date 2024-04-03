package com.proyectosoftware.backend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
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
		List<Carta> mano = new ArrayList<>();
		Usuario usuario, ganador;
		for (int i = 0; i < 4; i++) {
			usuario = new Usuario ("Nombre", "email", 12, "España");
			usuarios.add(usuario);
		}
		/*
		Carta carta1 = new Carta (10, 3);
		mano.add(carta1);
		Carta carta2 = new Carta (11, 2);
		mano.add(carta2);
		Carta carta3 = new Carta (12, 2);
		mano.add(carta3);
		Carta carta4 = new Carta (13,0);
		mano.add(carta4);
		Carta carta5 = new Carta (14, 2);
		mano.add(carta5);
		Carta carta6 = new Carta (9, 1);
		mano.add(carta6);
		Carta carta7 = new Carta (10, 3);
		mano.add(carta7);
		System.out.println(poker.verificarMano(mano).getMano());
		
		poker.repartirCartas(usuarios);
		
		poker.agnadirCartaCentro();
		poker.agnadirCartaCentro();
		ganador = poker.ganadorPartida(usuarios);
		System.out.println(ganador.getId());
		*/
		
	}

}
