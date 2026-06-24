package com.proyecto.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Integer idReserva;

    @Column(name = "fecha_reserva", nullable = false)
    private LocalDate fechaReserva;

    @Column(name = "estado", nullable = false, length = 30)
    private String estado;

    @Column(name = "monto_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoTotal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pasajero")
    private Pasajero pasajero;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_vuelo")
    private Vuelo vuelo;

    public Reserva(LocalDateTime fechaReserva, String confirmada, BigDecimal montoTotal, Pasajero pasajeroUno, Vuelo vueloUno) {}

    public Reserva() {}
    public Reserva(LocalDate fechaReserva, String estado, BigDecimal montoTotal, Pasajero pasajero, Vuelo vuelo) {
        this.fechaReserva = fechaReserva;
        this.estado = estado;
        this.montoTotal = montoTotal;
        this.pasajero = pasajero;
        this.vuelo = vuelo;
    }

    // --- Getters y Setters ---
    public Integer getIdReserva() { return idReserva; }
    public void setIdReserva(Integer idReserva) { this.idReserva = idReserva; }

    public LocalDate getFechaReserva() { return fechaReserva; }
    public void setFechaReserva(LocalDate fechaReserva) { this.fechaReserva = fechaReserva; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public BigDecimal getMontoTotal() { return montoTotal; }
    public void setMontoTotal(BigDecimal montoTotal) { this.montoTotal = montoTotal; }

    public Pasajero getPasajero() { return pasajero; }
    public void setPasajero(Pasajero pasajero) { this.pasajero = pasajero; }

    public Vuelo getVuelo() { return vuelo; }
    public void setVuelo(Vuelo vuelo) { this.vuelo = vuelo; }
}

