package com.proyectosoftware.backend.database.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosoftware.backend.database.entidades.Poker;
import com.proyectosoftware.backend.database.repository.PokerRepository;

@Service
public class PokerService {
    
    @Autowired
    private PokerRepository pokerRepository;

    public PokerService(){}

    public List<Poker> getAllPokers(){
        return pokerRepository.findAll();
    }

    public Poker savePoker(Poker poker){
        return pokerRepository.save(poker);
    }

    public Optional<Poker> getUNO(Long idPoker){
        return pokerRepository.findById(idPoker);
    }
}
