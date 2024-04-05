package com.proyectosoftware.backend.database.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosoftware.backend.database.entidades.Mentiroso;
import com.proyectosoftware.backend.database.repository.MentirosoRepository;

@Service
public class MentirosoService {
    
    @Autowired
    private MentirosoRepository mentirosoRepository;

    public MentirosoService() {}

    public List<Mentiroso> getAllMentiroso(){
        return mentirosoRepository.findAll();
    }

    public Mentiroso saveMentiroso(Mentiroso mentiroso){
        return mentirosoRepository.save(mentiroso);
    }

    public Optional<Mentiroso> getMentiroso(Long idMentiroso){
        return mentirosoRepository.findById(idMentiroso);
    }
}
