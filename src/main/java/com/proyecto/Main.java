package com.proyecto;

import com.proyecto.entity.Pasajero;
import com.proyecto.entity.Reserva;
import com.proyecto.entity.Vuelo;
import com.proyecto.repository.PasajeroRepository;
import com.proyecto.repository.ReservaRepository;
import com.proyecto.repository.VueloRepository;
import com.proyecto.service.ReservaService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class Main {

    private static final PasajeroRepository pasajeroRepo = new PasajeroRepository();
    private static final VueloRepository vueloRepo = new VueloRepository();
    private static final ReservaRepository reservaRepo = new ReservaRepository();
    private static final ReservaService reservaService = new ReservaService();
    public static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n========================================");
            System.out.println("----- RESERVA DE VUELOS ----");
            System.out.println("========================================");
            System.out.println("1. Registrar un nuevo Pasajero");
            System.out.println("2. Registrar un nuevo Vuelo");
            System.out.println("3. Crear una nueva Reserva");
            System.out.println("4. Ver lista de Pasajeros");
            System.out.println("5. Ver lista de Vuelos");
            System.out.println("6. Ver todas las Reservas");
            System.out.println("7. Salir");
            System.out.println("========================================");
            System.out.println("Elija una opción: ");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    menuRegistrarPasajero();
                    break;
                case 2:
                    menuCrearVuelo();
                    break;
                case 3:
                    menuCrearReserva();
                    break;
                case 4:
                    menuListarPasajeros();
                    break;
                case 5:
                    menuListarVuelos();
                    break;
                case 6:
                    menuListarReservas();
                    break;
                case 7:
                    salir = true;
                    System.out.println("\n¡Gracias por usar el sistema! Saliendo...");
                    break;
                default:
                    System.out.println("❌ Opción inválida. Intente nuevamente.");
            }
        }
    }

    private static void menuRegistrarPasajero() {
        System.out.println("\n--- REGISTRAR NUEVO PASAJERO ---");
        System.out.println("Nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Apellido: ");
        String apellido = sc.nextLine();
        System.out.println("Fecha de Nacimiento (AAAA-MM-DD): ");
        String fechaStr =  sc.nextLine();
        System.out.println("Documento de Identidad: ");
        String documento = sc.nextLine();
        System.out.println("Nacionalidad: ");
        String nacionalidad = sc.nextLine();

        try {
            LocalDate fechaNac = LocalDate.parse(fechaStr);
            Pasajero nuevo = new Pasajero(nombre, apellido, fechaNac, documento, nacionalidad);
            pasajeroRepo.guardar(nuevo);
            System.out.println("✅ Pasajero guardado con éxito. ID: " + nuevo.getIdPasajero());
        }
        catch (Exception e) {
            System.out.println("❌ Error al guardar. Verifique el formato de fecha (AAAA-MM-DD).");
        }
    }

    private static void menuCrearVuelo() {
        System.out.println("\n--- CREAR NUEVO VUELO ---");
        System.out.print("Origen: ");
        String origen = sc.nextLine();
        System.out.print("Destino: ");
        String destino = sc.nextLine();
        System.out.print("Salida (AAAA-MM-DD HH:MM): ");
        String salidaStr = sc.nextLine();
        System.out.print("Llegada (AAAA-MM-DD HH:MM): ");
        String llegadaStr = sc.nextLine();

        try {
            DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");
            LocalDateTime salida = LocalDateTime.parse(salidaStr, formatter);
            LocalDateTime llegada = LocalDateTime.parse(llegadaStr, formatter);

            Vuelo nuevoVuelo = new Vuelo(origen, destino, salida, llegada);
            vueloRepo.guardar(nuevoVuelo);
            System.out.println("✅ Vuelo creado con éxito. ID: " + nuevoVuelo.getIdVuelo());
        }
        catch (Exception e) {
            System.out.println("❌ Error. Use el formato exacto: AAAA-MM-DD HH:MM (Ej: 2026-12-20 22:30)");
        }
    }

    private static void menuCrearReserva() {
        System.out.println("\n--- CREAR NUEVA RESERVA ---");
        System.out.print("Ingrese el ID del Pasajero: ");
        int idPasajero = sc.nextInt();
        System.out.print("Ingrese el ID del Vuelo: ");
        int idVuelo = sc.nextInt();
        System.out.print("Monto Total de la Reserva: ");
        BigDecimal monto = sc.nextBigDecimal();
        sc.nextLine(); // Limpiar buffer
        System.out.print("Estado (CONFIRMADA / PENDIENTE): ");
        String estado = sc.nextLine().toUpperCase();

        try {
            // Invocamos al servicio centralizado para que aplique los filtros
            Reserva nueva = reservaService.registrarReservaSegura(idPasajero, idVuelo, monto, estado);
            System.out.println("✅ " + nueva.getEstado() + "! ID Reserva: " + nueva.getIdReserva());

        } catch (IllegalArgumentException e) {
            // Captura específicamente si se violó una regla de negocio (ej: monto $0)
            System.out.println(e.getMessage());
        } catch (Exception e) {
            // Captura otros errores (ej: ID no encontrado)
            System.out.println(e.getMessage());
        }
    }

    private static void menuListarPasajeros() {
        System.out.println("\n--- LISTA DE PASAJEROS ---");
        List<Pasajero> pasajeros = pasajeroRepo.buscarTodos();
        if (pasajeros.isEmpty()) {
            System.out.println("No hay pasajeros registrados.");
        }
        else {
            for (Pasajero p : pasajeros) {
                System.out.println("ID: " + p.getIdPasajero() + " | " + p.getApellido() + ", " + p.getNombre() + " | DNI: " + p.getDocumentoIdent());
            }
        }
    }

    private static void menuListarVuelos() {
        System.out.println("\n--- LISTA DE VUELOS ---");
        List<Vuelo> vuelos = vueloRepo.buscarTodos();
        if (vuelos.isEmpty()) { // Corrección tipográfica intencional para que revises: debe ser vuelos.isEmpty()
            System.out.println("No hay vuelos registrados.");
        }
        else {
            for (Vuelo v : vuelos) {
                System.out.println("ID: " + v.getIdVuelo() + " | Desde: " + v.getOrigen() + " -> Hacia: " + v.getDestino());
            }
        }
    }

    private static void menuListarReservas() {
        System.out.println("\n--- LISTA DE RESERVAS ---");
        List<Reserva> reservas = reservaRepo.buscarTodos();
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas registradas.");
        }
        else {
            for (Reserva r : reservas) {
                // Gracias a JPA, podemos acceder a los datos del Pasajero y Vuelo directamente desde el objeto Reserva
                System.out.println("Reserva N°: " + r.getIdReserva() +
                        " | Pasajero: " + r.getPasajero().getApellido() +
                        " | Tramo: " + r.getVuelo().getOrigen() + " a " + r.getVuelo().getDestino() +
                        " | Estado: " + r.getEstado() +
                        " | Total: $" + r.getMontoTotal());
            }
        }
    }
}
