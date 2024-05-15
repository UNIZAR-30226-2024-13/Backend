package com.proyectosoftware.backend.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectosoftware.backend.database.entidades.UNOEntidad;

@Repository
public interface UNORepository extends JpaRepository<UNOEntidad, String> {

    Optional<UNOEntidad> findById(String id);
}
