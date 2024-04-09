package com.proyectosoftware.backend.database;

public class ApiResponse {
    private String mensaje;
    private boolean status;
    
    public ApiResponse() {
    }

    public ApiResponse(String mensaje, boolean status){
        this.mensaje = mensaje;
        this.status = status;
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
