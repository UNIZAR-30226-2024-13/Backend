package com.proyectosoftware.backend.modelo.barajas;

import java.util.List;

import com.proyectosoftware.backend.modelo.Carta;
import com.proyectosoftware.backend.modelo.interfaces.Baraja;

public class BarajaUNO implements Baraja{

    public BarajaUNO(int numeroCartas, String cartasBaraja) {

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

    @Override
    public String colorReal(int color) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'colorReal'");
    }

    @Override
    public String numeroReal(int numero) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'numeroReal'");
    }

    @Override
    public List<Carta> devolverCartas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'devolverCartas'");
    }
    
}
