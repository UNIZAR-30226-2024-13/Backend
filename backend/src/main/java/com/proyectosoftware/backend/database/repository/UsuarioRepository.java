package com.proyectosoftware.backend.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectosoftware.backend.database.entidades.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Optional<Usuario> findById(String id);
}
