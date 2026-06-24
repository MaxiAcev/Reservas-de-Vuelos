package com.proyecto.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "vuelos")
public class Vuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vuelo")
    private Integer idVuelo;

    @Column(name = "origen", nullable = false, length = 100)
    private String origen;

    @Column(name = "destino", nullable = false, length = 100)
    private String destino;

    @Column(name = "fecha_hora_salida", nullable = false)
    private LocalDateTime fechaHoraSalida;

    @Column(name = "fecha_hora_llegada", nullable = false)
    private LocalDateTime fechaHoraLlegada;

    public Vuelo(){}

    public Vuelo(String origen, String destino,  LocalDateTime fechaHoraSalida, LocalDateTime fechaHoraLlegada){
        this.origen = origen;
        this.destino = destino;
        this.fechaHoraSalida = fechaHoraSalida;
        this.fechaHoraLlegada = fechaHoraLlegada;
    }

    public Integer getIdVuelo() {return idVuelo;}
    public void setIdVuelo(Integer idVuelo) {this.idVuelo = idVuelo;}

    public String getOrigen() {return origen;}
    public void setOrigen(String origen) {this.origen = origen;}

    public String getDestino() {return destino;}
    public void setDestino(String destino) {this.destino = destino;}

    public LocalDateTime getFechaHoraSalida() {return fechaHoraSalida;}
    public void setFechaHoraSalida(LocalDateTime fechaHoraSalida) {this.fechaHoraSalida = fechaHoraSalida;}

    public LocalDateTime getFechaHoraLlegada() {return fechaHoraLlegada;}
    public void setFechaHoraLlegada(LocalDateTime fechaHoraLlegada) {this.fechaHoraLlegada = fechaHoraLlegada;}
}
