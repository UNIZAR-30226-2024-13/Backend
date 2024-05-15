package com.proyectosoftware.backend.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectosoftware.backend.database.entidades.Guarda;

@Repository 
public interface GuardaRepository extends JpaRepository<Guarda, Long>{
    Optional<Guarda> findByUsuarioIdAndPartidaId(String idUsuario, String idPartida);
}

