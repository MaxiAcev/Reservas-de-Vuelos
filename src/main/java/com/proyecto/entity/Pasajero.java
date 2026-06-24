package com.proyecto.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pasajeros")
public class Pasajero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pasajero")
    private Integer idPasajero;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Column(name = "fecha_nac", nullable = false)
    private LocalDate fechaNac;

    @Column(name = "documento_ident", nullable = false, length = 20)
    private String documentoIdent;

    @Column(name = "nacionalidad", nullable = false, length = 150)
    private String nacionalidad;


    public Pasajero() {}
    public Pasajero(String nombre, String apellido, LocalDate fechaNac, String documentoIdent, String nacionalidad) {
       this.nombre = nombre;
       this.apellido = apellido;
       this.fechaNac = fechaNac;
       this.documentoIdent = documentoIdent;
       this.nacionalidad = nacionalidad;
   }

   public Integer getIdPasajero(){return idPasajero;}
   public void setIdPasajero(Integer idPasajero){this.idPasajero=idPasajero;}

   public String getNombre(){return nombre;}
   public void setNombre(String nombre){this.nombre=nombre;}

   public String getApellido(){return apellido;}
   public void setApellido(String apellido){this.apellido=apellido;}

   public LocalDate getFechaNac(){return fechaNac;}
   public void setFechaNac(LocalDate fechaNac){this.fechaNac=fechaNac;}

   public String getDocumentoIdent(){return documentoIdent;}
   public void setDocumentoIdent(String documentoIdent){this.documentoIdent=documentoIdent;}

   public String getNacionalidad(){return nacionalidad;}
   public void setNacionalidad(String nacionalidad){this.nacionalidad=nacionalidad;}


   @Override
   public String toString(){
        return "Pasajero{ " + "id = " + idPasajero +
                ", nombre =' " + nombre + '\'' +
                ", DNI =' " + documentoIdent + '\'' +
                '}';
   }
}
