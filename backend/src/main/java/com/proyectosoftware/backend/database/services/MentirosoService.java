package com.proyectosoftware.backend.database.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosoftware.backend.database.entidades.Mentiroso;
import com.proyectosoftware.backend.database.entidades.Partida;
import com.proyectosoftware.backend.database.entidades.PartidaId;
import com.proyectosoftware.backend.database.repository.MentirosoRepository;
import com.proyectosoftware.backend.database.repository.PartidaRepository;

@Service
public class MentirosoService {

    @Autowired
    private PartidaRepository partidaRepository;
    
    @Autowired
    private MentirosoRepository mentirosoRepository;

    public MentirosoService() {}

    public List<Mentiroso> getAllMentiroso(){
        return mentirosoRepository.findAll();
    }

    public Mentiroso saveMentiroso(Mentiroso mentiroso){
        return mentirosoRepository.save(mentiroso);
    }

    public Optional<Mentiroso> getMentiroso(String idMentiroso){
        PartidaId partida = new PartidaId();
        Optional<Partida> partidaOptional = partidaRepository.findById(idMentiroso);
        if(partidaOptional.isPresent()){
            partida.setPartida(partidaOptional.get());
        }
        return mentirosoRepository.findById(partida);
    }
}
