package com.proyectosoftware.backend.database.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectosoftware.backend.database.ApiResponse;
import com.proyectosoftware.backend.database.entidades.Cinquillo;
import com.proyectosoftware.backend.database.services.CinquilloService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class CinquilloController {
    
    @Autowired
    private CinquilloService cinquilloService;

    @GetMapping("/api/cinquillo")
    public List<Cinquillo> getAllCinquillo() {
        return cinquilloService.getAllCinquillo();
    } 

    @PostMapping("/api/cinquillo")
    public Cinquillo saveCinquillo(@RequestBody Cinquillo cinquillo) {
            return cinquilloService.saveCinquillo(cinquillo);
    }

    @GetMapping("/api/cinquillo")
    public Optional<Cinquillo> getCinquillo(@RequestParam Long idCinquillo) {
        return cinquilloService.getCinquillo(idCinquillo);
    }
}
