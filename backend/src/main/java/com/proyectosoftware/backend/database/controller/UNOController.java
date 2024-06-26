package com.proyectosoftware.backend.database.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.proyectosoftware.backend.database.entidades.UNOEntidad;
import com.proyectosoftware.backend.database.services.UNOService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class UNOController {
    
    @Autowired
    private UNOService unoService;

    @GetMapping("getUNOs")
    public List<UNOEntidad> getAllUNOs() {
        return unoService.getAllUNOs();
    }

    @PostMapping("addUNO")
    public UNOEntidad saveUNO(@RequestBody UNOEntidad uno) {
        return unoService.saveUNO(uno);
    }
    
    @GetMapping("getUNO")
    public Optional<UNOEntidad> getUNO(@RequestParam String idUNO) {
        return unoService.getUNO(idUNO);
    } 
}
