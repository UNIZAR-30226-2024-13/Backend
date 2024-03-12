package com.proyectosoftware.backend.modelo;

import com.proyectosoftware.backend.modelo.interfaces.UniqueIDGenerator;

import java.util.HashSet;
import java.util.Set;

/**
 * Usuario
 */
public class Usuario implements UniqueIDGenerator{
    private String id;
    private String nombre;
    private String email;
    private double dinero;
    private String pais;
    private Set<String> IDsAmigos;

    /**
     * Constructor objeto Usuario
     * @param nombre    - nombre/nick del usuario
     * @param email     - email del usuario
     * @param dinero    - dinero del usuario
     * @param pais      - pais del usuario
     */
    public Usuario(String nombre, String email, double dinero, String pais) {
        this.nombre = nombre;
        this.email = email;
        this.dinero = dinero;
        this.pais = pais;
        this.id = generateID();

        this.IDsAmigos = new HashSet<>();
    }
    
    /**
     * {@inheritDoc}
     * 
     * @implSpec Se retorna un string con la forma {@code "usuario-nombre-xx"}
     * donde "xx" es el numero de "nombre" que aparece en la base de datos
     */
    @Override
    public String generateID() {
        StringBuilder mensaje = new StringBuilder(this.getClass().getSimpleName());
        mensaje.append("-");
        mensaje.append(this.nombre);
        mensaje.append("-"); 
        /* 
         *  TODO: funccion para añadir el numero
         *  mensaje.append( <numero de "this.nombre" de la base de datos> ); 
         */
        return mensaje.toString();
    }

    /**
     * Añade un id a la lista de amigos
     * @param IDUsuario - ID del usuario nuevo en la lista
     * 
     * @return {@code true} si el ID no existia en la lista
     */
    public boolean aniadirAmigos(String IDUsuario){
        return IDsAmigos.add(IDUsuario);
    }

    /**
     * Añade un id a la lista de amigos
     * @param IDUsuario - ID del usaurio a quitar de la lista
     * 
     * @return {@code true} si la lista contenia el ID
     */
    public boolean quitarAmigos(String IDUsuario){
        return IDsAmigos.remove(IDUsuario);
    }
}
