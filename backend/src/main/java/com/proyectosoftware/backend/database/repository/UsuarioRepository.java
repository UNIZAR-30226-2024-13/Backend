package com.proyectosoftware.backend.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectosoftware.backend.database.entidades.UsuarioEntidad;


public interface UsuarioRepository extends JpaRepository<UsuarioEntidad, String> {

    Optional<UsuarioEntidad> findById(String id);
}
