package com.proyectosoftware.backend.database.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosoftware.backend.database.entidades.Partida;
import com.proyectosoftware.backend.database.repository.PartidaRepository;

@Service
public class PartidaService {
    
    @Autowired
    private PartidaRepository partidaRepository;

    public PartidaService() {
    }

    public List<Partida> getAllPartidas(){
        return partidaRepository.findAll();
    }

    public Partida savePartida(Partida partida){
        return partidaRepository.save(partida);
    }

    public Optional<Partida> getPartida(Long idPartida){
        return partidaRepository.findById(idPartida);
    }
}
