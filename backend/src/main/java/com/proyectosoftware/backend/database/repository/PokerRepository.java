package com.proyectosoftware.backend.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectosoftware.backend.database.entidades.PartidaId;
import com.proyectosoftware.backend.database.entidades.Poker;

public interface PokerRepository extends JpaRepository<Poker, PartidaId> {

    Optional<Poker> findById(PartidaId id);
}
