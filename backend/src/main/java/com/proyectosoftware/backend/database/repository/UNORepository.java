package com.proyectosoftware.backend.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectosoftware.backend.database.entidades.PartidaId;
import com.proyectosoftware.backend.database.entidades.UNO;

@Repository
public interface UNORepository extends JpaRepository<UNO, PartidaId> {

    Optional<UNO> findById(PartidaId id);
}
