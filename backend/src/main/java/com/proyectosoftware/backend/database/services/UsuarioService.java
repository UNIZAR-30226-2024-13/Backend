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

    public UsuarioEntidad saveUsuario(UsuarioEntidad Usuario){
        return usuarioRepository.save(Usuario);
    }

    public Optional<UsuarioEntidad> getUsuarioByName(String nombre){
        return usuarioRepository.findByNombre(nombre);
    }

    public Optional<UsuarioEntidad> getUsuarioById(String Id){
        return usuarioRepository.findById(Id);
    }

    public void deleteUsuario(String idUsuario) throws Exception{
        UsuarioEntidad usuario = usuarioRepository.findById(idUsuario).orElseThrow(()-> new Exception("Usuario no existe"));
        for (UsuarioEntidad amigo : usuario.getAmigos()) {
            amigo.getAmigos().remove(usuario);
        }
        usuario.getAmigos().clear();
        usuarioRepository.delete(usuario);
    }

    public UsuarioEntidad agregarAmigo(String idUsuario, String idAmigo) throws Exception {
        UsuarioEntidad usuario = usuarioRepository
                                .findById(idUsuario)
                                .orElseThrow(() -> new Exception("El amigo con ID '" + idAmigo + "' no existe."));
        UsuarioEntidad amigo = usuarioRepository
                                .findById(idAmigo)
                                .orElseThrow(() -> new Exception("El usuario con ID '" + idUsuario + "' no existe."));
        if(usuario.getAmigos().contains(amigo)){
            throw new Exception("El usuario con ID '" + idUsuario + "' ya es amigo del usuario con id '" + idAmigo + "'.");
        }
        usuario.getAmigos().add(amigo);
        amigo.getAmigos().add(usuario);
        return saveUsuario(usuario);
    }

    public UsuarioEntidad borrarAmigo(String idUsuario, String idAmigo) throws Exception {
        UsuarioEntidad usuario = usuarioRepository
                                .findById(idUsuario)
                                .orElseThrow(() -> new Exception("El amigo con ID '" + idAmigo + "' no existe."));
        UsuarioEntidad amigo = usuarioRepository
                                .findById(idAmigo)
                                .orElseThrow(() -> new Exception("El usuario con ID '" + idUsuario + "' no existe."));
        if(!usuario.getAmigos().contains(amigo) || !amigo.getAmigos().contains(usuario)){
            throw new Exception("El usuario con ID '" + idUsuario + "' y el usuario con id '" + idAmigo + "' no son amigos.");
        }
        usuario.getAmigos().remove(amigo);
        amigo.getAmigos().remove(usuario);
        return saveUsuario(usuario);    
    }
}
