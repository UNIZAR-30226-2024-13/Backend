package com.proyectosoftware.backend.database.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosoftware.backend.database.entidades.Login;
import com.proyectosoftware.backend.database.repository.LoginRepository;

@Service
public class LoginService {
    
    @Autowired
    private LoginRepository loginRepository;

    public Optional<Login> getLogin(String idLogin){
        return loginRepository.findById(idLogin);
    }

    public boolean isValid(String idUsuario, String hashPasswd){
        return loginRepository.findById(idUsuario).get().getHashPasswd().equals(hashPasswd);
    }
}
