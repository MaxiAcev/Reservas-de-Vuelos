package com.proyecto.repository;

import com.proyecto.entity.Pasajero;

public class PasajeroRepository extends AbstractRepository<Pasajero, Integer>{

    public PasajeroRepository() {
        super(Pasajero.class);
    }
}
