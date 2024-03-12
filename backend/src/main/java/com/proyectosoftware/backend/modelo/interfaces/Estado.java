package com.proyectosoftware.backend.modelo.interfaces;

import org.json.simple.JSONObject;

public interface Estado {
    /**
     * Genera un estado a partir de un string
     * @param estadoString - String a parsear
     * @return - Estado
     */
    public Estado recuperarEstado(String estadoString);

    /**
     * 
     * @param estado
     * @return
     */
    public String crearEstado(Estado estado);
}
