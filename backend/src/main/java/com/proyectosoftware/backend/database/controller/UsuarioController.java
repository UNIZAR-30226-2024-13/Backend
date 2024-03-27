package com.proyectosoftware.backend.database.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyectosoftware.backend.database.entidades.Usuario;
import com.proyectosoftware.backend.database.services.UsuarioService;

@RestController
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("getUsuarios")
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @PostMapping("addUsuario")
    public Usuario saveUsuario(@RequestBody Usuario Usuario) {
        return usuarioService.saveUsuario(Usuario);
    }
    
    @GetMapping("getUsuario")
    public Optional<Usuario> getUsuario(@RequestParam String idUsuario) {
        return usuarioService.getUsuario(idUsuario);
    } 

    @PostMapping("addAmigo")
    public Usuario agregarAmigo(@RequestParam String idUsuario, @RequestParam String idAmigo) {
        return usuarioService.agregarAmigo(idUsuario, idAmigo);
    }
}
