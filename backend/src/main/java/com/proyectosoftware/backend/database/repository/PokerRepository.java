package com.proyectosoftware.backend.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectosoftware.backend.database.entidades.Poker;

public interface PokerRepository extends JpaRepository<Poker, Long> {

    Optional<Poker> findById(Long id);
}
