package com.proyectosoftware.backend.database.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosoftware.backend.database.entidades.Login;
import com.proyectosoftware.backend.database.repository.LoginRepository;

@Service
public class LoginService {
    
    @Autowired
    private LoginRepository loginRepository;

    public LoginService(){}

    public List<Login> getAllLogins(){
        return loginRepository.findAll();
    }

    public Login saveLogin(Login login){
        return loginRepository.save(login);
    }

    public Optional<Login> getLogin(String idLogin){
        return loginRepository.findById(idLogin);
    }
}
