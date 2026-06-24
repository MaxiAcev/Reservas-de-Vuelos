package com.proyecto.service;

import com.proyecto.entity.Pasajero;
import com.proyecto.entity.Reserva;
import com.proyecto.entity.Vuelo;
import com.proyecto.repository.PasajeroRepository;
import com.proyecto.repository.ReservaRepository;
import com.proyecto.repository.VueloRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReservaService {

    private final PasajeroRepository pasajeroRepo = new PasajeroRepository();
    private final VueloRepository vueloRepo = new VueloRepository();
    private final ReservaRepository reservaRepo = new ReservaRepository();

    public Reserva registrarReservaSegura(int idPasajero, int idVuelo, BigDecimal monto, String estado) throws Exception {


        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("❌ Regla de Negocio violada: El monto de la reserva debe ser mayor a $0.");
        }


        if (!estado.equals("CONFIRMADA") && !estado.equals("PENDIENTE")) {
            throw new IllegalArgumentException("❌ Regla de Negocio violada: El estado debe ser CONFIRMADA o PENDIENTE.");
        }


        Pasajero pasajero = pasajeroRepo.buscarPorId(idPasajero);
        if (pasajero == null) {
            throw new Exception("❌ Error de Sistema: No existe ningún pasajero con el ID: " + idPasajero);
        }

        Vuelo vuelo = vueloRepo.buscarPorId(idVuelo);
        if (vuelo == null) {
            throw new Exception("❌ Error de Sistema: No existe ningún vuelo con el ID: " + idVuelo);
        }

        if (vuelo.getFechaHoraSalida().isBefore(java.time.LocalDateTime.now())) {
            throw new IllegalArgumentException("❌ Regla de Negocio violada: No se pueden realizar reservas para vuelos que ya despegaron.");
        }

        Reserva nuevaReserva = new Reserva(LocalDate.now(), estado, monto, pasajero, vuelo);

        reservaRepo.guardar(nuevaReserva);
        return nuevaReserva;
    }
}