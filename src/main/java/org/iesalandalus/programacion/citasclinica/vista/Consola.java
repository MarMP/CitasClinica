package org.iesalandalus.programacion.citasclinica.vista;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.citasclinica.modelo.Cita;
import org.iesalandalus.programacion.citasclinica.modelo.Paciente;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	static final String STR_FORMATO_FECHA_HORA = "dd/MM/yyyy HH:mm";
	static final DateTimeFormatter FORMATO_FECHA_HORA = DateTimeFormatter.ofPattern(STR_FORMATO_FECHA_HORA);
	static final String STR_FORMATO_FECHA = "dd/MM/yyyy";
	static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern(STR_FORMATO_FECHA);

	private Consola() {

	}

	public static void mostrarMenu() {
		System.out.println("MENÚ DEL PROGRAMA: ");
		System.out.println("0.SALIR");
		System.out.println("1.INSERTAR CITA");
		System.out.println("2.BUSCAR CITA");
		System.out.println("3.BORRAR CITA");
		System.out.println("4.MOSTRAR CITAS DÍA");
		System.out.println("5.MOSTRAR CITAS");
	}

	public static Opciones elegirOpcion() {
		int opcionNumero = 0;
		Opciones[] opciones = Opciones.values();
		do {
			System.out.print("Por favor, introduzca la opción del menú que desee realizar: ");
			opcionNumero = Entrada.entero();
		} while (!(opcionNumero >= 0 && opcionNumero <= opciones.length - 1));

		return opciones[opcionNumero];
	}

	public static Paciente leerPaciente() throws NullPointerException, IllegalArgumentException {
		System.out.print("Introduzca el nombre del usuario/a: ");
		String nombre = Entrada.cadena();
		System.out.print("Introduzca el dni del usuario/a: ");
		String dni = Entrada.cadena();
		System.out.print("Introduzca el teléfono del usuario/a: ");
		String telefono = Entrada.cadena();

		return new Paciente(nombre, dni, telefono);
	}

	public static LocalDateTime leerFechaHora() {
		LocalDateTime fechaHora = null;
		boolean esCorrecta = false;

		do {
			try {
				System.out.println("Introduzca fecha y hora de la cita: " + STR_FORMATO_FECHA_HORA);
				fechaHora = LocalDateTime.parse(Entrada.cadena(), FORMATO_FECHA_HORA);
				esCorrecta = true;
				System.out.println("La fecha leída es correcta.");
			} catch (DateTimeParseException e) {
				esCorrecta = false;
				System.out.println("No correcta la fecha introducida.");
			}

		} while (!esCorrecta);

		return fechaHora;

	}

	public static Cita leerCita() throws NullPointerException, IllegalArgumentException {
		return new Cita(leerPaciente(), leerFechaHora());
	}

	public static LocalDate leerFecha() {

		LocalDate fecha = null;
		boolean esCorrecta = false;

		do {
			try {
				System.out.println("Introduzca fecha: " + STR_FORMATO_FECHA_HORA);
				fecha = LocalDate.parse(Entrada.cadena(), FORMATO_FECHA_HORA);
				esCorrecta = true;
				System.out.println("Es correcta la fecha introducida.");
			} catch (DateTimeParseException e) {
				esCorrecta = false;
				System.out.println("No correcta la fecha que ha introducido.");
			}

		} while (!esCorrecta);

		return fecha;
	}

}
