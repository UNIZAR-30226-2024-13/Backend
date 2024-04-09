package com.proyectosoftware.backend.database.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public Optional<UsuarioEntidad> getUsuario(String idUsuario){
        return usuarioRepository.findById(idUsuario);
    }

    public void deleteUsuario(String idUsuario) throws Exception{
        UsuarioEntidad usuario = usuarioRepository.findById(idUsuario).orElseThrow(()-> new Exception("Usuario no existe"));
        usuarioRepository.delete(usuario);
    }

    public UsuarioEntidad agregarAmigo(String idUsuario, String idAmigo) {
        Optional<UsuarioEntidad> optionalUsuario = getUsuario(idUsuario);

        if (optionalUsuario.isPresent()) {
            UsuarioEntidad usuario = optionalUsuario.get();
            
            Optional<UsuarioEntidad> optionalAmigo = getUsuario(idAmigo);
            if (optionalAmigo.isPresent()) {
                UsuarioEntidad amigo = optionalAmigo.get();
                Set<UsuarioEntidad> amigos = usuario.getAmigos();
                
                amigos.add(amigo);
                usuario.setAmigos(amigos);
                return saveUsuario(usuario);
            } else {
                // Si el amigo no existe
                throw new IllegalArgumentException("El amigo con ID " + idAmigo + " no existe.");
            }
        } else {
            // Si el usuario no existe
            throw new IllegalArgumentException("El usuario con ID " + idUsuario + " no existe.");
        }
    }
}
