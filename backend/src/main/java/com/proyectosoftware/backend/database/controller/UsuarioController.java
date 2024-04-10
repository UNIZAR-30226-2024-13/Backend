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
    public ApiResponse saveUsuario(@RequestBody UsuarioEntidad usuarioNuevo) {
        Usuario usuario = new Usuario(
            usuarioNuevo.getNombre(),
            usuarioNuevo.getEmail(),
            usuarioNuevo.getFichas(),
            usuarioNuevo.getPais()
        );
        usuarioNuevo.setId(usuario.getID());
        return new ApiResponse(
            "Usuario aniadido con exito",
            true,
            usuarioService.saveUsuario(usuarioNuevo)
        );
    }
    
    @GetMapping("/getUsuario")
    public ApiResponse getUsuario(@RequestParam String tipo, @RequestParam String value) {
        Optional<UsuarioEntidad> usuario = null;
        if(tipo.equals("byId")){
            usuario = usuarioService.getUsuarioById(value);
        }else if(tipo.equals("byNombre")){
            usuario = usuarioService.getUsuarioByName(value);
        }else{
            return new ApiResponse("El tipo de busqueda solo puede se 'byId' o 'byNombre'", false);
        }

        if (!usuario.isPresent()) {
            return new ApiResponse("No existe el usuario'" + value + "'", false);
        }
        return new ApiResponse("Usuario'" + value + "'", true, usuario.get());
    } 

    @PostMapping("/addAmigo")
    public ApiResponse agregarAmigo(@RequestParam String idUsuario, @RequestParam String idAmigo) {
        try {
            UsuarioEntidad usuario = usuarioService.agregarAmigo(idUsuario, idAmigo);
            return new ApiResponse("Amigo '" + idAmigo + "' agregado con exito en la lista de amigos de '" + idUsuario + "'", true, usuario);
        } catch (Exception e) {
            return new ApiResponse(e.getMessage(), false);
        }
    }

    @DeleteMapping("/deleteAmigo")
    public ApiResponse deleteAmigo(@RequestParam String idUsuario, @RequestParam String idAmigo) {
        try {
            UsuarioEntidad usuario = usuarioService.borrarAmigo(idUsuario, idAmigo);
            return new ApiResponse("Amigo '" + idAmigo + "' borrado de la lista de amigos de '" + idUsuario + "'", true, usuario);
        } catch (Exception e) {
            return new ApiResponse(e.getMessage(), false);
        }
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
