package com.proyectosoftware.backend.database.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosoftware.backend.database.entidades.UNO;
import com.proyectosoftware.backend.database.repository.UNORepository;

@Service
public class UNOService {
    
    @Autowired
    private UNORepository unoRepository;

    public UNOService() {}

    public List<UNO> getAllUNOs(){
        return unoRepository.findAll();
    }

    public UNO saveUNO(UNO uno){
        return unoRepository.save(uno);
    }

    public Optional<UNO> getUNO(Long idUno){
        return unoRepository.findById(idUno);
    }
}
