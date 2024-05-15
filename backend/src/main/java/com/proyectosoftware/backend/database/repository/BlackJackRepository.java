package com.proyectosoftware.backend.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectosoftware.backend.database.entidades.BlackJackEntidad;
import com.proyectosoftware.backend.database.entidades.PartidaId;

public interface BlackJackRepository extends JpaRepository<BlackJackEntidad, String> {
    
    Optional<BlackJackEntidad> findById(String id);
}
