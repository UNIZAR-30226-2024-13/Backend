package com.proyectosoftware.backend.database.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosoftware.backend.database.entidades.Partida;
import com.proyectosoftware.backend.database.entidades.PartidaId;
import com.proyectosoftware.backend.database.entidades.PokerEntidad;
import com.proyectosoftware.backend.database.repository.PartidaRepository;
import com.proyectosoftware.backend.database.repository.PokerRepository;

@Service
public class PokerService {

    @Autowired
    private PartidaRepository partidaRepository;
    
    @Autowired
    private PokerRepository pokerRepository;

    public PokerService(){}

    public List<PokerEntidad> getAllPokers(){
        return pokerRepository.findAll();
    }

    public PokerEntidad savePoker(PokerEntidad poker){
        return pokerRepository.save(poker);
    }

    public Optional<PokerEntidad> getPoker(String idPoker){
        PartidaId partida = new PartidaId();
        Optional<Partida> partidaOptional = partidaRepository.findById(idPoker);
        if(partidaOptional.isPresent()){
            partida.setPartida(partidaOptional.get());
        }
        
        return pokerRepository.findById(partida);
    }
}
