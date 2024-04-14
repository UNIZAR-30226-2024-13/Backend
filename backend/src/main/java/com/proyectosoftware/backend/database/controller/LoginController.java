package com.proyectosoftware.backend.database.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyectosoftware.backend.database.entidades.Login;
import com.proyectosoftware.backend.database.services.LoginService;

@RestController
public class LoginController {
    
    @Autowired
    private LoginService loginService;

    @GetMapping("getLogins")
    public List<Login> getAllLogins() {
        return loginService.getAllLogins();
    }

    @PostMapping("addLogin")
    public Login saveLogin(@RequestBody Login login) {
        return loginService.saveLogin(login);
    }
    
    @GetMapping("getLogin")
    public Optional<Login> getLogin(@RequestParam String idLogin) {
        return loginService.getLogin(idLogin);
    }
}
