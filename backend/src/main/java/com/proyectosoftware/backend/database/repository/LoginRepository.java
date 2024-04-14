package com.proyectosoftware.backend.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectosoftware.backend.database.entidades.Login;

public interface LoginRepository extends JpaRepository<Login, String> {
    
    Optional<Login> findById(String userID);
}