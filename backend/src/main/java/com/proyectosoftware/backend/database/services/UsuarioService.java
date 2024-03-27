package com.proyectosoftware.backend.database.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosoftware.backend.database.entidades.Usuario;
import com.proyectosoftware.backend.database.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioService(){}

    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario saveUsuario(Usuario Usuario){
        return usuarioRepository.save(Usuario);
    }

    public Optional<Usuario> getUsuario(String idUsuario){
        return usuarioRepository.findById(idUsuario);
    }

    public Usuario agregarAmigo(String idUsuario, String idAmigo) {
        Optional<Usuario> optionalUsuario = getUsuario(idUsuario);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            
            Optional<Usuario> optionalAmigo = getUsuario(idAmigo);
            if (optionalAmigo.isPresent()) {
                Usuario amigo = optionalAmigo.get();
                Set<Usuario> amigos = usuario.getAmigos();
                
                amigos.add(amigo);
                usuario.setAmigos(amigos);
                return saveUsuario(usuario);
            } else {
                // Si el amigo no existe, manejar el error de alguna manera
                // Aquí simplemente lanzamos una excepción, pero puedes manejarlo de otra forma
                throw new IllegalArgumentException("El amigo con ID " + idAmigo + " no existe.");
            }
        } else {
            // Si el usuario no existe, manejar el error de alguna manera
            // Aquí simplemente lanzamos una excepción, pero puedes manejarlo de otra forma
            throw new IllegalArgumentException("El usuario con ID " + idUsuario + " no existe.");
        }
    }
}
