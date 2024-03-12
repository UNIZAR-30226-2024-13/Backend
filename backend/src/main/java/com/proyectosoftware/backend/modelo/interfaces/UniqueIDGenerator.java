package com.proyectosoftware.backend.modelo.interfaces;

/**
 * Interfaz para objetos que generan sus propias IDs uunicas
 */
public interface UniqueIDGenerator {
    
    /**
     * Funccion para generar un id unico
     * @return  - id generado
     */
    public String generateID();
}
