package org.iesalandalus.programacion.citasclinica.modelo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.naming.OperationNotSupportedException;

public class Citas {

	private Cita[] coleccionCitas;
	private int capacidad;
	private int tamano;

	public Citas(int capacidad) {
		if (capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		tamano = 0;
		this.capacidad = capacidad;
		coleccionCitas = new Cita[capacidad];

	}

	public Cita[] getCitas() {
		return coleccionCitas;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}

	private boolean capacidadSuperada(int indice) {
		if (indice >= capacidad) {
			return true;
		} else {
			return false;
		}
	}

	private boolean tamanoSuperado(int indice) {
		if (indice >= tamano) {
			return true;
		} else {
			return false;
		}
	}

	private int buscarIndice(Cita cita) {
		boolean esEncontrado = false;
		int indice = 0;

		while (!tamanoSuperado(indice) && !esEncontrado) {
			if (coleccionCitas[indice].equals(cita)) {
				esEncontrado = true; // es una cita igual a otra
			} else {
				indice++;
			}

		}
		return indice;
		/*
		 * do { for (int i = 0; i < coleccionCitas.length; i++) { if
		 * (coleccionCitas[i].equals(cita)) { indice = i; esEncontrado = true; // es una
		 * cita igual a otra } else { indice = tamano + 1; esEncontrado = false; } } }
		 * while (!esEncontrado && tamanoSuperado(indice));
		 */

	}

	public void insertar(Cita cita) throws OperationNotSupportedException {
		int indice = buscarIndice(cita);

		if (cita == null) {
			throw new NullPointerException("ERROR: No se puede insertar una cita nula.");
		}

		if (capacidadSuperada(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más citas.");
		}

		if (tamanoSuperado(indice)) {
			coleccionCitas[indice] = new Cita(cita);
			tamano += 1;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe una cita para esa fecha y hora.");

		}

		/*
		 * for (int i = 0; i < coleccionCitas.length; i++) { if
		 * (!capacidadSuperada(capacidad)) { if (buscarIndice(cita) == tamano++) {
		 * coleccionCitas[capacidad] = cita; capacidad++; } else { throw new
		 * OperationNotSupportedException("ERROR: Ya existe una cita para esa fecha y hora."
		 * ); } } else { throw new
		 * OperationNotSupportedException("ERROR: No se aceptan más citas."); } }
		 */
	}

	public Cita buscar(Cita cita) {
		int indice = buscarIndice(cita);

		if (!tamanoSuperado(indice)) {
			return coleccionCitas[indice] = new Cita(cita);
		} else {
			return null;
		}

	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; i < coleccionCitas.length - 1; i++) {
			if (tamano > 0) {
				coleccionCitas[i] = coleccionCitas[i + 1];
			} else {
				coleccionCitas[i] = null;
			}
		}
	}

	public void borrar(Cita cita) throws OperationNotSupportedException {
		int indice = buscarIndice(cita);
		if (cita == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar una cita nula.");
		}
		if (buscar(cita) != null) {
			coleccionCitas[(buscarIndice(cita))] = null;
			desplazarUnaPosicionHaciaIzquierda(indice);
			tamano--;
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ninguna cita para esa fecha y hora.");
		}
	}

	public Cita[] getCitas(LocalDate fecha) {

		if (fecha == null) {
			throw new NullPointerException("ERROR: No se pueden devolver las citas para un día nulo.");
		}
		Cita[] citasDia = new Cita[tamano];
		int citas = 0;

		/*
		 * DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		 * String fechaFormateada = fecha.format(formatters);
		 */

		for (int i = 0; tamanoSuperado(i) == false; i++) {
			if (coleccionCitas[i].getFechaHora().toLocalDate().equals(fecha)) {
				citasDia[citas++] = new Cita(coleccionCitas[i]);
			}

		}
		return citasDia;
	}

}
