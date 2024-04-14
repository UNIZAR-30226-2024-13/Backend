package com.proyectosoftware.backend.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectosoftware.backend.database.entidades.BlackJack;
import com.proyectosoftware.backend.database.entidades.PartidaId;

public interface BlackJackRepository extends JpaRepository<BlackJack, PartidaId> {
    
    Optional<BlackJack> findById(PartidaId id);
}
