package com.proyectosoftware.backend.modelo;

import java.util.Comparator;

public class ComparadorCarta implements Comparator<Carta> {

    @Override
    public int compare(Carta carta1, Carta carta2) {
        return Integer.compare(carta1.getNumero(), carta2.getNumero());
    }
}
