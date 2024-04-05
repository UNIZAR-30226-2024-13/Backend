package com.proyectosoftware.backend.database.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosoftware.backend.database.entidades.BlackJack;
import com.proyectosoftware.backend.database.repository.BlackJackRepository;

@Service
public class BlackJackService {
    
    @Autowired
    private BlackJackRepository blackJackRepository;

    public BlackJackService(){}

    public List<BlackJack> getAllBlackJacks(){
        return blackJackRepository.findAll();
    }

    public BlackJack saveBlackJack(BlackJack blackJack){
        return blackJackRepository.save(blackJack);
    }

    public Optional<BlackJack> getBlackJack(Long idBlackJack){
        return blackJackRepository.findById(idBlackJack);
    }
}
