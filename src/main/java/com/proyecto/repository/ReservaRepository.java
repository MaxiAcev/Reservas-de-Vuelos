package com.proyecto.repository;

import com.proyecto.entity.Reserva;

public class ReservaRepository extends AbstractRepository<Reserva, Integer> {

    public ReservaRepository() {
        super(Reserva.class);
    }
}
