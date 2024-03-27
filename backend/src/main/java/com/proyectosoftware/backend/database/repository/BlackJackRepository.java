package com.proyectosoftware.backend.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectosoftware.backend.database.entidades.BlackJack;

public interface BlackJackRepository extends JpaRepository<BlackJack, Long> {
    
    Optional<BlackJack> findById(Long id);
}
