package com.proyectosoftware.backend.database.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosoftware.backend.database.entidades.Cinquillo;
import com.proyectosoftware.backend.database.entidades.Partida;
import com.proyectosoftware.backend.database.entidades.PartidaId;
import com.proyectosoftware.backend.database.repository.CinquilloRepository;
import com.proyectosoftware.backend.database.repository.PartidaRepository;

@Service
public class CinquilloService {

    @Autowired
    private PartidaRepository partidaRepository;

    @Autowired 
    private CinquilloRepository cinquilloRepository;

    public CinquilloService(){}

    public List<Cinquillo> getAllCinquillo(){
        return cinquilloRepository.findAll();
    }

    public Cinquillo saveCinquillo(Cinquillo cinquillo) {
        return cinquilloRepository.save(cinquillo);
    }

    public Optional<Cinquillo> getCinquillo(String idCinquillo) {
        PartidaId partida = new PartidaId();
        Optional<Partida> partidaOptional = partidaRepository.findById(idCinquillo);
        if(partidaOptional.isPresent()){
            partida.setPartida(partidaOptional.get());
        }
        return cinquilloRepository.findById(partida);
    }
    
}