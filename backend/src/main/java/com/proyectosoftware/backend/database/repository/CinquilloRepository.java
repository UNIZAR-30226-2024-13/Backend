package com.proyectosoftware.backend.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectosoftware.backend.database.entidades.Cinquillo;


@Repository
public interface CinquilloRepository extends JpaRepository<Cinquillo, Long> {

    Optional<Cinquillo> findById(Long id);
}