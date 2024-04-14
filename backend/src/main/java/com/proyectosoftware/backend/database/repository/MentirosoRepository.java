package com.proyectosoftware.backend.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectosoftware.backend.database.entidades.Mentiroso;
import com.proyectosoftware.backend.database.entidades.PartidaId;

@Repository
public interface MentirosoRepository extends JpaRepository<Mentiroso, PartidaId> {

    Optional<Mentiroso> findById(PartidaId id);
}