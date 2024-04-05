package com.proyectosoftware.backend.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectosoftware.backend.database.entidades.UNO;

@Repository
public interface UNORepository extends JpaRepository<UNO, Long> {

    Optional<UNO> findById(Long id);
}
