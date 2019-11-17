package org.iesalandalus.programacion.citasclinica.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Cita {

	public static final String FORMATO_FECHA_HORA = "dd/MM/yyyy HH:mm";
	private LocalDateTime fechaHora;
	private Paciente paciente;

	public Cita(Paciente paciente, LocalDateTime fechaHora) {
		setPaciente(paciente);
		setFechaHora(fechaHora);

	}

	public Cita(Cita cita) {
		if (cita == null) {
			throw new NullPointerException("ERROR: No se puede copiar una cita nula.");
		}
		setFechaHora(cita.fechaHora);
		setPaciente(cita.paciente);
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		if (fechaHora == null) {
			throw new NullPointerException("ERROR: La fecha y hora de una cita no puede ser nula.");
		}
		this.fechaHora = fechaHora;
	}

	public Paciente getPaciente() {
		return new Paciente(paciente);
	}

	// Devuelve una copia del objeto paciente
	private void setPaciente(Paciente paciente) {
		if (paciente == null) {
			throw new NullPointerException("ERROR: El paciente de una cita no puede ser nulo.");
		}

		this.paciente = new Paciente(paciente);
	}

	@Override
	public int hashCode() {
		return Objects.hash(fechaHora, paciente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Cita)) {
			return false;
		}
		Cita other = (Cita) obj;
		if (fechaHora == null) {
			if (other.fechaHora != null) {
				return false;
			}
		} else if (!fechaHora.equals(other.fechaHora)) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return paciente + ", fechaHora=" + fechaHora.format(DateTimeFormatter.ofPattern(FORMATO_FECHA_HORA));
	}

}
