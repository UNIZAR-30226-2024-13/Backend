package com.proyectosoftware.backend.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectosoftware.backend.database.entidades.CinquilloEntidad;
import com.proyectosoftware.backend.database.entidades.PartidaId;


@Repository
public interface CinquilloRepository extends JpaRepository<CinquilloEntidad, String> {

    Optional<CinquilloEntidad> findById(String id);
}
