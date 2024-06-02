package model;

import java.util.regex.Pattern;

public class Usuario {
	// Definir constantes para las validaciones
	private static final int MAX_NOMBRE_LENGTH = 20;
	private static final int MAX_APELLIDO_LENGTH = 40;
	private static final int MAX_CORREO_LENGTH = 60;
	private static final int MIN_CONTRASENA_LENGTH = 8;
	private static final int MAX_CONTRASENA_LENGTH = 30;

	// Definir variables
	private int userId;
	private String nombre;
	private String apellido;
	private short edad;
	private String correo;
	private String contrasena;

	// Constructor por defecto
	public Usuario() {
		this.edad = 0; // Valor por defecto de edad
	}

	// Constructor con todos los campos
	public Usuario(int userId, String nombre, String apellido, short edad, String correo, String contrasena) {
		setUserId(userId);
		setNombre(nombre);
		setApellido(apellido);
		setEdad(edad);
		setCorreo(correo);
		setContrasena(contrasena);
	}

	// Getters y Setters con validaciones
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		if (userId <= 0) {
			throw new IllegalArgumentException("El userId debe ser un n\u00FAmero positivo.");
		}
		this.userId = userId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre != null && nombre.length() > MAX_NOMBRE_LENGTH) {
			throw new IllegalArgumentException("El nombre no puede exceder los " + MAX_NOMBRE_LENGTH + " caracteres.");
		}
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		if (apellido != null && apellido.length() > MAX_APELLIDO_LENGTH) {
			throw new IllegalArgumentException(
					"El apellido no puede exceder los " + MAX_APELLIDO_LENGTH + " caracteres.");
		}
		this.apellido = apellido;
	}

	public short getEdad() {
		return edad;
	}

	public void setEdad(short edad) {
		if (edad < 0) {
			throw new IllegalArgumentException("La edad no puede ser negativa.");
		}
		this.edad = edad;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		if (correo != null && correo.length() > MAX_CORREO_LENGTH) {
			throw new IllegalArgumentException("El correo no puede exceder los " + MAX_CORREO_LENGTH + " caracteres.");
		}
		if (!isValidEmail(correo)) {
			throw new IllegalArgumentException("El formato del correo es inv\u00E1lido.");
		}
		this.correo = correo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		if (contrasena != null && contrasena.length() < MIN_CONTRASENA_LENGTH) {
			throw new IllegalArgumentException(
					"La contrase\u00F1a debe tener al menos " + MIN_CONTRASENA_LENGTH + " caracteres.");
		}
		if (contrasena != null && contrasena.length() > MAX_CONTRASENA_LENGTH) {
			throw new IllegalArgumentException(
					"La contrase\u00F1a no puede exceder los " + MAX_CONTRASENA_LENGTH + " caracteres.");
		}
		this.contrasena = contrasena;
	}

	private boolean isValidEmail(String email) {

		// La parte local del correo electr\u00F3nico puede contener letras, números y
		// ciertos caracteres especiales como _, +, &, *, y -.
		// Puede tener uno o m\u00E1s subdominios separados por puntos, cada uno formado por
		// letras, n\u00FAmeros y guiones.
		// La extensi\u00F3n de dominio puede contener entre 2 y 7 letras.
		// El correo electr\u00F3nico debe comenzar con la parte local, seguida de un arroba,
		// y luego el dominio.
		
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pattern = Pattern.compile(emailRegex);
		return pattern.matcher(email).matches();
	}

	// Método toString
	@Override
	public String toString() {
		return "Usuario{" + "userId=" + userId + ", nombre='" + nombre + '\'' + ", apellido='" + apellido + '\''
				+ ", edad=" + edad + ", correo='" + correo + '\'' + ", contrasena='" + contrasena + '\'' + '}';
	}
}
