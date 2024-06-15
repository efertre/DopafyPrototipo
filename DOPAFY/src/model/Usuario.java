package model;

import java.io.Serializable;

public class Usuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Definir constantes para las validaciones
	private static final int MIN_FULLNAME_LENGTH = 20;
	private static final int MAX_FULLNAME_LENGTH = 60;
	public static final int MIN_USERNAME_LENGTH = 5;
	public static final int MAX_USERNAME_LENGTH = 25;

	private static final int MAX_EMAIL_LENGTH = 100;
	public static final int MIN_PASSWORD_LENGTH = 8;
	public static final int MAX_PASSWORD_LENGTH = 30;

	private int userId;
	private String nombre;
	private String email;
	private String password;
	private String username;
	private boolean esAdmin;

	// Constructor vacío
	public Usuario() {
	}

	// Constructor con todos los campos
	public Usuario(int userId, String nombre, String email, String password, String username, boolean esAdmin) {
		this.setUserId(userId);
		this.setNombre(nombre);
		this.setEmail(email);
		this.setPassword(password);
		this.setUsername(username);
		this.setEsAdmin(esAdmin);
	}

	// Getters y Setters con validaciones

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		if (userId <= 0) {
			throw new IllegalArgumentException("El userId debe ser mayor que 0");
		}
		this.userId = userId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null || nombre.length() > MAX_FULLNAME_LENGTH) {
			throw new IllegalArgumentException("El nombre no puede ser nulo y debe tener un mínimo de "
					+ MIN_FULLNAME_LENGTH + " y un máximo de " + MAX_FULLNAME_LENGTH + " caracteres");
		}
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email == null || email.length() > MAX_EMAIL_LENGTH || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			throw new IllegalArgumentException("El email no puede ser nulo, debe tener un máximo de " + MAX_EMAIL_LENGTH
					+ " caracteres y debe ser válido");
		}
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (password == null || password.length() > MAX_PASSWORD_LENGTH || password.length() < MIN_PASSWORD_LENGTH) {
			throw new IllegalArgumentException("La contraseña no puede ser nula y debe tener un mínimo de "
					+ MIN_PASSWORD_LENGTH + " y un máximo de " + MAX_PASSWORD_LENGTH + " caracteres");
		}
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if (username == null || username.length() > MAX_USERNAME_LENGTH || !username.matches("^[A-Za-z0-9_]+$")) {
			throw new IllegalArgumentException("El nombre de usuario no puede ser nulo, debe tener un mínimo de "
					+ MIN_USERNAME_LENGTH + ", un máximo de " + MAX_USERNAME_LENGTH
					+ " caracteres y no puede contener espacios ni caracteres especiales");
		}
		this.username = username;
	}

	public boolean getEsAdmin() {
		return esAdmin;
	}

	public void setEsAdmin(boolean esAdmin) {
		this.esAdmin = esAdmin;
	}

	// Método para mostrar la información del usuario
	@Override
	public String toString() {
		return "Usuario{" + "userId=" + userId + ", nombre='" + nombre + '\'' + ", email='" + email + '\''
				+ ", password='" + password + '\'' + ", username='" + username + '\'' + ", esAdmin=" + esAdmin + '}';
	}
}
