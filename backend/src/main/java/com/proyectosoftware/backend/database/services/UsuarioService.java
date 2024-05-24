package com.proyectosoftware.backend.database.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosoftware.backend.database.entidades.UsuarioEntidad;
import com.proyectosoftware.backend.database.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioService(){}

    public List<UsuarioEntidad> getAllUsuarios(){
        return usuarioRepository.findAll();
    }

    public UsuarioEntidad saveUsuario(UsuarioEntidad usuario){
        return usuarioRepository.save(usuario);
    }

    public Optional<UsuarioEntidad> getUsuarioByName(String nombre){
        return usuarioRepository.findByNombre(nombre);
    }

    public Optional<UsuarioEntidad> getUsuarioById(String id){
        return usuarioRepository.findById(id);
    }

    public void deleteUsuarioById(String idUsuario) throws Exception{
        UsuarioEntidad usuario = usuarioRepository.findById(idUsuario).orElseThrow(()-> new Exception("Usuario no existe"));
        for (UsuarioEntidad amigo : usuario.getAmigos()) {
            amigo.getAmigos().remove(usuario);
        }
        usuario.getAmigos().clear();
        usuarioRepository.delete(usuario);
    }

    public void deleteUsuarioByNombre(String nombre) throws Exception{
        UsuarioEntidad usuario = usuarioRepository.findByNombre(nombre).orElseThrow(()-> new Exception("Usuario no existe"));
        for (UsuarioEntidad amigo : usuario.getAmigos()) {
            amigo.getAmigos().remove(usuario);
        }
        usuario.getAmigos().clear();
        usuarioRepository.delete(usuario);
    }

    public UsuarioEntidad agregarAmigo(String nombreUsuario, String nombreAmigo) throws Exception {
        UsuarioEntidad usuario = usuarioRepository
                                .findByNombre(nombreUsuario)
                                .orElseThrow(() -> new Exception("El amigo con ID '" + nombreAmigo + "' no existe."));
        UsuarioEntidad amigo = usuarioRepository
                                .findByNombre(nombreAmigo)
                                .orElseThrow(() -> new Exception("El usuario con ID '" + nombreUsuario + "' no existe."));
        if(usuario.getAmigos().contains(amigo)){
            throw new Exception("El usuario con ID '" + nombreUsuario + "' ya es amigo del usuario con id '" + nombreAmigo + "'.");
        }
        usuario.getAmigos().add(amigo);
        amigo.getAmigos().add(usuario);
        return saveUsuario(usuario);
    }

    public UsuarioEntidad borrarAmigo(String nombreUsuario, String nombreAmigo) throws Exception {
        UsuarioEntidad usuario = usuarioRepository
                                .findByNombre(nombreUsuario)
                                .orElseThrow(() -> new Exception("El amigo con nombre '" + nombreAmigo + "' no existe."));
        UsuarioEntidad amigo = usuarioRepository
                                .findByNombre(nombreAmigo)
                                .orElseThrow(() -> new Exception("El usuario con nombre '" + nombreUsuario + "' no existe."));
        if(!usuario.getAmigos().contains(amigo) || !amigo.getAmigos().contains(usuario)){
            throw new Exception("El usuario con nombre'" + nombreUsuario + "' y el usuario con nombre '" + nombreAmigo + "' no son amigos.");
        }
        usuario.getAmigos().remove(amigo);
        amigo.getAmigos().remove(usuario);
        return saveUsuario(usuario);    
    }
}
