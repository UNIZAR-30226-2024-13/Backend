package com.proyectosoftware.backend.database.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosoftware.backend.database.entidades.MentirosoEntidad;
import com.proyectosoftware.backend.database.entidades.Partida;
import com.proyectosoftware.backend.database.entidades.PartidaId;
import com.proyectosoftware.backend.database.repository.MentirosoRepository;
import com.proyectosoftware.backend.database.repository.PartidaRepository;

@Service
public class MentirosoService {
    
    @Autowired
    private MentirosoRepository mentirosoRepository;

    public MentirosoService() {}

    public List<MentirosoEntidad> getAllMentiroso(){
        return mentirosoRepository.findAll();
    }

    public MentirosoEntidad saveMentiroso(MentirosoEntidad mentiroso){
        return mentirosoRepository.save(mentiroso);
    }

    public Optional<MentirosoEntidad> getMentiroso(String idMentiroso){
        return mentirosoRepository.findById(idMentiroso);
    }
}
