package com.proyectosoftware.backend.modelo;

import com.proyectosoftware.backend.modelo.interfaces.UniqueIDGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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
        mensaje.append(UUID.randomUUID().toString().split("-")[0]);
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

    /**
     * Realiza una apusta con el dienero del usuario
     * @param cantidad  - cantidad de la apuesta
     * @return {@code true} si el usuario tiene dinero suficiente para apostar
     */
    public boolean apostar(double cantidad){
        if(dinero >= cantidad){
            dinero -= cantidad;
            return true;
        }
        return false;
    }

    /**
     * Añade a la cartera del usuario la cantidad ganada
     * @param cantidad - cantidad a añadir
     */
    public void ganaApuesta(double cantidad){
        dinero += cantidad;
    }

    /**
     * Devuelve el id del usuario
     * @return id del usuario
     */
    public String getId() {
        return id;
    }
}
