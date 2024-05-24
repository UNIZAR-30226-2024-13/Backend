package com.proyectosoftware.backend.database.repository;

import org.springframework.stereotype.Repository;

import com.proyectosoftware.backend.database.entidades.Session;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, String> {

    public Optional<Session> findBySessionId(String sessionId);

}
