package com.proyecto.repository;
import com.proyecto.entity.Vuelo;

public class VueloRepository extends AbstractRepository<Vuelo, Integer> {

    public VueloRepository() {
        super(Vuelo.class);
    }
}
