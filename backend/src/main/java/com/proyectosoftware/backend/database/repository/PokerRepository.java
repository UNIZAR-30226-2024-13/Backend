package com.proyectosoftware.backend.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectosoftware.backend.database.entidades.PokerEntidad;

public interface PokerRepository extends JpaRepository<PokerEntidad, String> {

    Optional<PokerEntidad> findById(String id);
}
