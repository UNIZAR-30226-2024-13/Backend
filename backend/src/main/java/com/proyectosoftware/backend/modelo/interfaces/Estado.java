package com.proyectosoftware.backend.modelo.interfaces;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public interface Estado {
    /**
     * Genera un estado a partir de un string
     * @param estadoString - String a parsear
     * @return - Estado
     */
    public default JSONObject crearEstado(String estadoString){
        JSONParser parser = new JSONParser();
        JSONObject json;
        try {
            json = (JSONObject) parser.parse(estadoString);
        } catch (ParseException e) {
            json = new JSONObject(); // Estado vacio
        }
        return json;
    }

    /**
     * 
     * @param estado
     * @return
     public String crearEstado(JSONObject estado);
     */
}