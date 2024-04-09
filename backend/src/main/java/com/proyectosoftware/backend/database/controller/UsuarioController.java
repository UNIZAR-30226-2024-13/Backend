package com.proyectosoftware.backend.database.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyectosoftware.backend.database.ApiResponse;
import com.proyectosoftware.backend.database.entidades.UsuarioEntidad;
import com.proyectosoftware.backend.database.services.UsuarioService;
import com.proyectosoftware.backend.modelo.Usuario;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/getUsuarios")
    public List<UsuarioEntidad> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @PostMapping("/newUsuario")
    public UsuarioEntidad saveUsuario(@RequestBody UsuarioEntidad usuarioNuevo) {
        Usuario usuario = new Usuario(
            usuarioNuevo.getNombre(),
            usuarioNuevo.getEmail(),
            usuarioNuevo.getFichas(),
            usuarioNuevo.getPais()
        );
        usuarioNuevo.setId(usuario.getID());
        return usuarioService.saveUsuario(usuarioNuevo);
    }
    
    @GetMapping("/getUsuario")
    public Optional<UsuarioEntidad> getUsuario(@RequestParam String idUsuario) {
        return usuarioService.getUsuario(idUsuario);
    } 

    @PostMapping("/addAmigo")
    public UsuarioEntidad agregarAmigo(@RequestParam String idUsuario, @RequestParam String idAmigo) {
        return usuarioService.agregarAmigo(idUsuario, idAmigo);
    }

    @DeleteMapping("/deleteUsuario")
    public ApiResponse deleteUsuario(@RequestParam String idUsuario){
        try {
            usuarioService.deleteUsuario(idUsuario);
            return new ApiResponse("Usuario '" + idUsuario + "' borrado correctamente", true);
        } catch (Exception e) {
            return new ApiResponse("Usuario '" + idUsuario + "' no existe", false);
        }
    }
}
