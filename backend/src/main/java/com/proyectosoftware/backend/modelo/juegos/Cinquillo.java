package com.proyectosoftware.backend.modelo.juegos;

import com.proyectosoftware.backend.modelo.Carta;
import com.proyectosoftware.backend.modelo.Partida;
import com.proyectosoftware.backend.modelo.interfaces.Estado;
import com.proyectosoftware.backend.modelo.interfaces.JuegoSinApuesta;

public class Cinquillo implements JuegoSinApuesta{
    private Baraja baraja;

    public Cinquillo(int id, int turno, String escaleras) {
        super(id, turno);
        Carta carta = new Carta();

        this.escaleras = carta.parseStringCartas(escaleras);
    }

    @Override
    public Estado guardar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

    @Override
    public void cargar(Estado estado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cargar'");
    }

    @Override
    public Estado recuperarEstado(String estadoString) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recuperarEstado'");
    }

    @Override
    public String crearEstado(Estado estado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearEstado'");
    }

    @Override
    public String generateID() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateID'");
    }
}
