package com.proyectosoftware.backend.modelo.interfaces;

import java.util.UUID;

import org.json.simple.JSONObject;

import com.proyectosoftware.backend.modelo.Usuario;

public interface Juego extends Estado, UniqueIDGenerator{
    /**
     * Crea un estado con el estado actual del juego
     * @return - estado
     */
    public JSONObject guardar();

    /**
     * Inicializa el juego con un estado dado
     * @param estado - el estado a cargar
     */
    public void cargar(JSONObject estado);

    /**
     * Pasa al siguente turno
     */
    public void siguenteTurno();
    
    /**
     * Anaide un usuario al juego
     * @param usuario   - El usuario
     */
    public void nuevoUsuario(Usuario usuario);

    /**
     * {@inheritDoc}
     * @implSpec
     * El id generado sera del tipo:
     * <p><i><b>juego-nombreJuego-xxxxx</i></b>
     */
    @Override
    default String generateID() {
        StringBuilder mensaje = new StringBuilder("juego");
        mensaje.append("-");
        mensaje.append(getClass().getSimpleName());
        mensaje.append("-");
        mensaje.append(UUID.randomUUID().toString().split("-")[0]);
        return mensaje.toString();
    }
}