package org.iesalandalus.programacion.citasclinica.modelo;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Paciente {
	
	private static final String ER_DNI = "([0-9]{8})([A-Za-z])";
	private static final String ER_TELEFONO = "[96][0-9]{8}";
	private String nombre;
	private String dni;
	private String telefono;
	
	public Paciente (String nombre, String dni, String telefono) {
		setNombre(nombre);
		setDni(dni);
		setTelefono(telefono);
	}
	
	public Paciente (Paciente paciente) {
		if (paciente == null) {
			throw new NullPointerException("ERROR: No es posible copiar un paciente nulo."); 
		}
		setNombre(paciente.nombre);
		setDni(paciente.dni);
		setTelefono(paciente.telefono);
	}
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre de un paciente no puede ser nulo o vacío.");
		}  
		if (nombre.trim().equals("")) {
			throw new NullPointerException("ERROR: El nombre de un paciente no puede ser nulo o vacío.");
		}
		this.nombre = formateaNombre(nombre);
		//this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		if (telefono == null) {
			throw new NullPointerException("ERROR: El teléfono de un paciente no puede ser nulo o vacío.");
		}
		if (telefono.trim().equals("")) {
			throw new NullPointerException("ERROR: El teléfono de un paciente no puede ser nulo o vacío.");
		}
		if (!telefono.matches(ER_TELEFONO)) {
			throw new IllegalArgumentException("ERROR: El teléfono no tiene un formato válido.");
		}

		this.telefono = telefono;
	}
	
	public String getDni() {
		return dni;
	}

	private void setDni(String dni) {
		if (dni == null) {
			throw new NullPointerException("ERROR: El DNI de un paciente no puede ser nulo o vacío.");
		}
		if (dni.trim().equals("")) {
			throw new NullPointerException("ERROR: El DNI de un paciente no puede ser nulo o vacío.");
		}
		if (!dni.matches(ER_DNI)) {
			throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
		}
		if (comprobarLetraDni(dni) == false) {
			throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
		}
		this.dni = dni;
	}

	private String formateaNombre(String nombre) {
		String cadenaMinus = nombre;
		cadenaMinus = cadenaMinus.toLowerCase(); // convierto la cadena a minúscula
		//System.out.println(cadenaMinus);
		cadenaMinus = cadenaMinus.trim(); //quito espacios en blanco iniciales y finales
		cadenaMinus = cadenaMinus.replaceAll(" +"," "); //quita espacios de mas
		char[] cadCaracter = cadenaMinus.toCharArray(); // convierte la cadena en un array de caracteres
		cadCaracter[0] = Character.toUpperCase(cadCaracter[0]); // primera letra mayúscula

		// Recorre el array de char
		for (int i = 0; i < cadenaMinus.length() - 1; i++)
			// Si encuentra un espacio o punto, suma 1 a la posicion de "i" que encuentre y
			// convierte en mayus la primera letra
			if (cadCaracter[i] == ' ' || cadCaracter[i] == '.') {
				cadCaracter[i + 1] = Character.toUpperCase(cadCaracter[i + 1]);
			}
		String nombreFormateado = new String(cadCaracter);

		return nombreFormateado;
	}

	private static boolean comprobarLetraDni(String dni) {

		char[] letra = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H',
				'L', 'C', 'K', 'E' };
		Pattern pattern;
		Matcher matcher;

		pattern = Pattern.compile(ER_DNI);
		matcher = pattern.matcher(dni);
		boolean letraValida = false;
		int posicionLetra;

		while (matcher.find()) {
			String numeroDni = matcher.group(1); // grupo 1 de la expresion
			posicionLetra = Integer.parseInt(numeroDni) % 23; // calcula el resto que sera la letra
			String letraDni = matcher.group(2);
			//System.out.println("estoy aqui");
			char caracter = letraDni.charAt(0);
			if (caracter == letra[posicionLetra]) {
				letraValida = true;
			} else {
				letraValida = false;
			}
		}

		return letraValida;
	}
	
	
	private String getIniciales() {
		String [] nombres = nombre.split(" "); //Genera array de String  Maria del Mar --> ["María" "del" "Mar"] 
		String iniciales = ""; 
		for (String nombre : nombres) {
			if(!nombre.equals("")) {
			iniciales = iniciales + nombre.charAt(0); //MdM
			}
		}
		return iniciales.toUpperCase(); 	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Paciente)) {
			return false;
		}
		Paciente other = (Paciente) obj; // hago un casting del objeto obj
		if (dni == null) {
			if (other.dni != null) {
				return false;
			}
		} else if (!dni.equals(other.dni)) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return "nombre=" + nombre + " (" + getIniciales() + ")" + ", DNI=" + dni + ", teléfono=" + telefono;
	}
		
}
