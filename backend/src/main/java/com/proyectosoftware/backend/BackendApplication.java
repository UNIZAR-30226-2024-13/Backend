package com.proyectosoftware.backend;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(BackendApplication.class, args);
	}

}
