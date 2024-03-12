package com.proyectosoftware.backend.modelo.interfaces;

import java.util.UUID;

public interface Juego extends Estado, UniqueIDGenerator{
    /**
     * Crea un estado con el estado actual del juego
     * @return - estado
     */
    public Estado guardar();

    /**
     * Inicializa el juego con un estado dado
     * @param estado - el estado a cargar
     */
    public void cargar(Estado estado);

    /**
     * Pasa al siguente turno
     */
    public void siguenteTurno();

    @Override
    default String generateID() {
        StringBuilder mensaje = new StringBuilder("juego");
        mensaje.append("-");
        mensaje.append(getClass().getSimpleName());
        mensaje.append(UUID.randomUUID().toString().split("-")[0]);
        return mensaje.toString();
    }
}
