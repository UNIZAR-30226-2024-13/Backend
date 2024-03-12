package com.proyectosoftware.backend.modelo.interfaces;

import com.proyectosoftware.backend.modelo.Usuario;

public interface JuegoConApuesta extends Juego{
    public void apostar(Usuario usaurio, double apuesta);
}
