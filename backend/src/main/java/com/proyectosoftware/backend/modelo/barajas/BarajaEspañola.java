package com.proyectosoftware.backend.modelo.barajas;

import com.proyectosoftware.backend.modelo.Carta;

public class BarajaEspañola extends BarajaAbstracta{


    public BarajaEspañola(int numeroCartas, String cartasBaraja) {
        super(numeroCartas, cartasBaraja);
        //TODO Auto-generated constructor stub
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean esCartaDeLaBaraja(Carta carta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'esCartaDeLaBaraja'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean esCartaDeLaBaraja(int color, int numero) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'esCartaDeLaBaraja'");
    }
    
}
