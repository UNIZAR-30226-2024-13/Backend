package com.proyectosoftware.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.proyectosoftware.backend.modelo.juegos.Cinquillo;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
		
		Cinquillo cinquillo = new Cinquillo();
		cinquillo.iniciarPartida();
		
		int n=0;
		do {
			n++;
			cinquillo.jugada();
		} while (n < 36);
	}
}
