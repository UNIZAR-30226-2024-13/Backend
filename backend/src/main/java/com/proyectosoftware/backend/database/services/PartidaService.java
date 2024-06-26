package com.proyectosoftware.backend.database.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosoftware.backend.database.entidades.Partida;
import com.proyectosoftware.backend.database.entidades.UsuarioEntidad;
import com.proyectosoftware.backend.database.repository.PartidaRepository;

@Service
public class PartidaService {
    
    @Autowired
    private PartidaRepository partidaRepository;

    public PartidaService() {
    }

    public List<Partida> getAllPartidas(){
        return partidaRepository.findAll();
    }

    public Partida savePartida(Partida partida){
        return partidaRepository.save(partida);
    }

    public Optional<Partida> getPartida(String idPartida){
        return partidaRepository.findById(idPartida);
    }

    public Partida addPartidaConUsuarios(String idPartida, UsuarioEntidad usuario1, UsuarioEntidad usuario2, UsuarioEntidad usuario3, UsuarioEntidad usuario4){
        Optional<Partida> optionalPartida = partidaRepository.findById(idPartida);
        
        if(optionalPartida.isPresent()){
            Partida partida = optionalPartida.get();

            Set<UsuarioEntidad> usuarios = new HashSet<>();
            usuarios.add(usuario1);
            usuarios.add(usuario2);
            usuarios.add(usuario3);
            usuarios.add(usuario4);
         
            //partida.setUsuarios(usuarios);
            return partidaRepository.save(partida);
        } else {
            return null;
        }
    }
}
