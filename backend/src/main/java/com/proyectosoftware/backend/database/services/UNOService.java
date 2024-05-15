package com.proyectosoftware.backend.database.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosoftware.backend.database.entidades.Partida;
import com.proyectosoftware.backend.database.entidades.PartidaId;
import com.proyectosoftware.backend.database.entidades.UNOEntidad;
import com.proyectosoftware.backend.database.repository.PartidaRepository;
import com.proyectosoftware.backend.database.repository.UNORepository;

@Service
public class UNOService {

    @Autowired
    private PartidaRepository partidaRepository;
    
    @Autowired
    private UNORepository unoRepository;

    public UNOService() {}

    public List<UNOEntidad> getAllUNOs(){
        return unoRepository.findAll();
    }

    public UNOEntidad saveUNO(UNOEntidad uno){
        return unoRepository.save(uno);
    }

    public Optional<UNOEntidad> getUNO(String idUno){
   
        return unoRepository.findById(idUno);
    }
}
