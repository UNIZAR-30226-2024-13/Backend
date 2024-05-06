package com.proyectosoftware.backend.database;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class ApiResponse<T> {
    private String mensaje;
    private boolean status;
    private T datos;
    
    public ApiResponse() {
    }
    
    public ApiResponse(String mensaje, boolean status, T datos){
        this.mensaje = mensaje;
        this.status = status;
        this.datos = datos;
    }

    public ApiResponse(String mensaje, boolean status){
        this(mensaje,status,null);
    }

    public T getDatos() {
        return datos;
    }

    public void setDatos(T datos) {
        this.datos = datos;
    }
    
    public String getMensaje() {
        return mensaje;
    }
    
    public boolean getStatus() {
        return status;
    }
    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
