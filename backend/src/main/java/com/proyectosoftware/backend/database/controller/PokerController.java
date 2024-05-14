package com.proyectosoftware.backend.database.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyectosoftware.backend.database.entidades.PokerEntidad;
import com.proyectosoftware.backend.database.services.PokerService;

@RestController
public class PokerController {
    
    @Autowired
    private PokerService pokerService;

    @GetMapping("getPokers")
    public List<PokerEntidad> getAllPokers() {
        return pokerService.getAllPokers();
    }

    @PostMapping("addPoker")
    public PokerEntidad savePoker(@RequestBody PokerEntidad poker) {
        return pokerService.savePoker(poker);
    }
    
    @GetMapping("getPoker")
    public Optional<PokerEntidad> getPoker(@RequestParam String idPoker) {
        return pokerService.getPoker(idPoker);
    } 
}
