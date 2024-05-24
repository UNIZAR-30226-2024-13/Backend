package com.proyectosoftware.backend.database.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectosoftware.backend.database.entidades.CinquilloEntidad;
import com.proyectosoftware.backend.database.services.CinquilloService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class CinquilloController {
    
   @Autowired
   private CinquilloService cinquilloService;

   @GetMapping("getCinquillos")
   public List<CinquilloEntidad> getAllCinquillo() {
       return cinquilloService.getAllCinquillo();
   } 

   @PostMapping("addCinquillo")
   public CinquilloEntidad saveCinquillo(@RequestBody CinquilloEntidad cinquillo) {
        return cinquilloService.saveCinquillo(cinquillo);
   }

   @GetMapping("getCinquillo")
   public Optional<CinquilloEntidad> getCinquillo(@RequestParam String idCinquillo) {
       return cinquilloService.getCinquillo(idCinquillo);
   }
   
}
