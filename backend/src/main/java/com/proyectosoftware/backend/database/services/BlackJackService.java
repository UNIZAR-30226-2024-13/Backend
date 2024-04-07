package com.proyectosoftware.backend.database.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosoftware.backend.database.entidades.BlackJack;
import com.proyectosoftware.backend.database.entidades.Partida;
import com.proyectosoftware.backend.database.entidades.PartidaId;
import com.proyectosoftware.backend.database.repository.BlackJackRepository;
import com.proyectosoftware.backend.database.repository.PartidaRepository;

@Service
public class BlackJackService {

    @Autowired
    private PartidaRepository partidaRepository;
    
    @Autowired
    private BlackJackRepository blackJackRepository;

    public BlackJackService(){}

    public List<BlackJack> getAllBlackJacks(){
        return blackJackRepository.findAll();
    }

    public BlackJack saveBlackJack(BlackJack blackJack){
        return blackJackRepository.save(blackJack);
    }

    public Optional<BlackJack> getBlackJack(String idBlackJack){
        PartidaId partida = new PartidaId();
        Optional<Partida> partidaOptional = partidaRepository.findById(idBlackJack);
        if(partidaOptional.isPresent()){
            partida.setPartida(partidaOptional.get());
        }
        return blackJackRepository.findById(partida);
    }
}
