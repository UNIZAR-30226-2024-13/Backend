package com.proyectosoftware.backend.database.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyectosoftware.backend.database.entidades.BlackJack;
import com.proyectosoftware.backend.database.services.BlackJackService;

@RestController
public class BlackJackController {
    
    @Autowired
    private BlackJackService blackJackService;

    @GetMapping("getBlackJacks")
    public List<BlackJack> getAllBlackJacks() {
        return blackJackService.getAllBlackJacks();
    }

    @PostMapping("addBlackJack")
    public BlackJack saveBlackJack(@RequestBody BlackJack BlackJack) {
        return blackJackService.saveBlackJack(BlackJack);
    }
    
    @GetMapping("getBlackJack")
    public Optional<BlackJack> getBlackJack(@RequestParam Long idBlackJack) {
        return blackJackService.getBlackJack(idBlackJack);
    } 
}
