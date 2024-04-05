package com.proyectosoftware.backend.database.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyectosoftware.backend.database.entidades.Poker;
import com.proyectosoftware.backend.database.services.PokerService;

@RestController
public class PokerController {
    
    @Autowired
    private PokerService pokerService;

    @GetMapping("getPokers")
    public List<Poker> getAllPokers() {
        return pokerService.getAllPokers();
    }

    @PostMapping("addPoker")
    public Poker savePoker(@RequestBody Poker poker) {
        return pokerService.savePoker(poker);
    }
    
    @GetMapping("getPoker")
    public Optional<Poker> getPoker(@RequestParam Long idPoker) {
        return pokerService.getPoker(idPoker);
    } 
}
