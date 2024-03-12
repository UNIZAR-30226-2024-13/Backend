package com.proyectosoftware.backend.modelo.interfaces;

public interface Juego extends Estado, UniqueIDGenerator{
    public Estado guardar();
    public void cargar(Estado estado);
}
