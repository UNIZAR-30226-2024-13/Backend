package com.proyectosoftware.backend.modelo;

public class Amigos {
    private int usuario1;
    private int usuario2;

    public Amigos(int usuario1, int usuario2) {
        this.usuario1 = usuario1;
        this.usuario2 = usuario2;
    }

    /**
     * Devuelve el id del usuario1
     * @return id del usuario1
     */
    public int getUsuario1() {
        return usuario1;
    }

    /**
     * Devuelve el id del usuario2
     * @return id del usuario2
     */
    public int getUsuario2() {
        return usuario2;
    }

    /**
     * Establece el id del usuario1
     * @param usuario1
     */
    public void setUsuario1(int usuario1) {
        this.usuario1 = usuario1;
    }

    /**
     * Establece el id del usuario2
     * @param usuario2
     */
    public void setUsuario2(int usuario2) {
        this.usuario2 = usuario2;
    }
}
