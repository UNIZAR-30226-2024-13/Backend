package com.proyectosoftware.backend.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectosoftware.backend.database.entidades.Partida;

public interface PartidaRepository extends JpaRepository<Partida, String>{

    Optional<Partida> findById(String id);
    
}
