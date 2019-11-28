package org.iesalandalus.programacion.citasclinica;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.citasclinica.modelo.Cita;
import org.iesalandalus.programacion.citasclinica.modelo.Citas;
import org.iesalandalus.programacion.citasclinica.modelo.Paciente;
import org.iesalandalus.programacion.citasclinica.vista.Consola;
import org.iesalandalus.programacion.citasclinica.vista.Opciones;

public class MainApp {

	private static final int NUM_MAX_CITAS = 4;
	static Citas citasClinica = new Citas(NUM_MAX_CITAS);

	public static void main(String[] args) {

		System.out.println("Programa para gestionar las citas de la Clínica.");
		Opciones opciones;

		do {
			Consola.mostrarMenu();
			opciones = Consola.elegirOpcion();
			ejecutarOpcion(opciones);
		} while (opciones != Opciones.SALIR);

	}

	private static void insertarCita() {
		try {
			Cita cita = Consola.leerCita();

			citasClinica.insertar(cita);
			System.out.println("Se ha insertado la cita correctamente.");
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	private static void buscarCita() {
		Paciente paciente = new Paciente("Fran", "76661112L", "965772234");
		LocalDateTime fechaHora = Consola.leerFechaHora();
		Cita cita = new Cita(paciente, fechaHora);

		if ((cita = citasClinica.buscar(cita)) == null) {
			System.out.println("No hay citas. ");
		} else {
			System.out.println(cita);
		}

	}

	private static void borrarCita() {
		Cita cita = null;
		try {
			cita = Consola.leerCita();
			citasClinica.borrar(cita);
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	private static void mostrarCitas() {
		boolean existenCitas = false;
		try {
			for (int i = 0; i < citasClinica.getTamano(); i++) {
				System.out.println(citasClinica.getCitas()[i]);
				existenCitas = true;
			}
			if (existenCitas == false) {
				System.out.println("No hay citas insertadas. ");
			}

		} catch (IllegalArgumentException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	private static void mostrarCitasDia() {
		LocalDate fechaCita = Consola.leerFechaHora().toLocalDate();
		try {
			if (!(citasClinica.getCitas(fechaCita) == null)) {
				for (int i = 0; i < citasClinica.getTamano(); i++) {
					System.out.println(citasClinica.getCitas(fechaCita)[i]);
				}
			} else {
				System.out.println("No hay citas para ese día. ");
			}
		} catch (IllegalArgumentException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	private static void ejecutarOpcion(Opciones opcion) {
		switch (opcion) {
		case SALIR:
			System.out.println("Hasta luego!");
			break;
		case INSERTAR_CITA:
			insertarCita();
			break;
		case BUSCAR_CITA:
			buscarCita();
			break;
		case BORRAR_CITA:
			borrarCita();
			break;
		case MOSTRAR_CITAS_DIA:
			mostrarCitasDia();
			break;
		case MOSTRAR_CITAS:
			mostrarCitas();
			break;
		default:
			System.out.println("Opción incorrecta.");
		}
	}

}
