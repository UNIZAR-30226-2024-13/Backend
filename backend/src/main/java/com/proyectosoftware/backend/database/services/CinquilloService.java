package com.proyectosoftware.backend.database.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosoftware.backend.database.entidades.CinquilloEntidad;
import com.proyectosoftware.backend.database.entidades.Partida;
import com.proyectosoftware.backend.database.repository.CinquilloRepository;
import com.proyectosoftware.backend.database.repository.PartidaRepository;

@Service
public class CinquilloService {

    @Autowired
    private PartidaRepository partidaRepository;

    @Autowired 
    private CinquilloRepository cinquilloRepository;

    public CinquilloService(){}

    public List<CinquilloEntidad> getAllCinquillo(){
        return cinquilloRepository.findAll();
    }

    public CinquilloEntidad saveCinquillo(CinquilloEntidad cinquillo) {
        return cinquilloRepository.save(cinquillo);
    }

    public Optional<CinquilloEntidad> getCinquillo(String idCinquillo) {
        
        return cinquilloRepository.findById(idCinquillo);
    }
    
}
