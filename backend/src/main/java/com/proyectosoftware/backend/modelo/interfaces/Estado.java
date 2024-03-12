package com.proyectosoftware.backend.modelo.interfaces;

import org.json.simple.JSONObject;

public interface Estado {
    public Estado recuperarEstado(String estadoString);
    public String crearEstado(Estado estado);
}
