package com.proyectosoftware.backend.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectosoftware.backend.database.entidades.UsuarioEntidad;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntidad, String> {

    Optional<UsuarioEntidad> findById(String id);

    Optional<UsuarioEntidad> findByNombre(String nombre);
}
