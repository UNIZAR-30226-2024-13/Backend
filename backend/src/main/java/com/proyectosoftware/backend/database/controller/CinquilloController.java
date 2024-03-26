package com.proyectosoftware.backend.database.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectosoftware.backend.database.repository.CinquilloRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/Cinquillo")
public class CinquilloController {
    
    private final CinquilloRepository cinquilloRepository;

    public CinquilloController(CinquilloRepository cinquilloRepository){
        this.cinquilloRepository = cinquilloRepository;
    }

    @SuppressWarnings("rawtypes")
    @GetMapping
    public ResponseEntity getAllCinquillo() {
        return ResponseEntity.ok(this.cinquilloRepository.findAll());
    }
    
}
