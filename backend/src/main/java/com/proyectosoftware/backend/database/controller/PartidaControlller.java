package com.proyectosoftware.backend.database.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.proyectosoftware.backend.database.entidades.Partida;
import com.proyectosoftware.backend.database.entidades.Usuario;
import com.proyectosoftware.backend.database.services.PartidaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class PartidaControlller {
    
    @Autowired
    private PartidaService partidaService;

    @GetMapping("getPartidas")
    public List<Partida> getAllPartidas() {
        return partidaService.getAllPartidas();
    }

    @PostMapping("addPartida")
    public Partida savePartida(@RequestBody Partida partida) {
        return partidaService.savePartida(partida);
    }
    
    @GetMapping("getPartida")
    public Optional<Partida> getPartida(@RequestParam Long idPartida) {
        return partidaService.getPartida(idPartida);
    }
    
    @PostMapping("addPartidaConUsuarios")
    public Partida addPartida(@RequestBody Long idPartida, Usuario usuario1, Usuario usuario2, Usuario usuario3, Usuario usuario4) {
        return partidaService.addPartidaConUsuarios(idPartida, usuario1, usuario2, usuario3, usuario4);
    }
    
}
