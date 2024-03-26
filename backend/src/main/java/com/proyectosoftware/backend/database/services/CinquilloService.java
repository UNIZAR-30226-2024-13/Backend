package com.proyectosoftware.backend.database.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosoftware.backend.database.entidades.Cinquillo;
import com.proyectosoftware.backend.database.repository.CinquilloRepository;

@Service
public class CinquilloService {

    @Autowired 
    private CinquilloRepository cinquilloRepository;

    public CinquilloService(){}

    public List<Cinquillo> getAllCinquillo(){
        return cinquilloRepository.findAll();
    }

    public Cinquillo saveCinquillo(Cinquillo cinquillo) {
        return cinquilloRepository.save(cinquillo);
    }

    public Optional<Cinquillo> getCinquillo(Long idCinquillo) {
        return cinquilloRepository.findById(idCinquillo);
    }
    
}
