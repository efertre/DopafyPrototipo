package model;

import java.io.Serializable;

/**
 * La clase Usuario representa a un usuario en el sistema.
 * Implementa Serializable para permitir la serialización de los objetos Usuario.
 */
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    // Definir constantes para las validaciones
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

    /**
     * Constructor vacío.
     */
    public Usuario() {
    }

    /**
     * Constructor con todos los campos.
     *
     * @param userId    Identificador del usuario.
     * @param nombre    Nombre completo del usuario.
     * @param email     Correo electrónico del usuario.
     * @param password  Contraseña del usuario.
     * @param username  Nombre de usuario.
     * @param esAdmin   Indica si el usuario tiene permisos de administrador.
     */
    public Usuario(int userId, String nombre, String email, String password, String username, boolean esAdmin) {
        this.setUserId(userId);
        this.setNombre(nombre);
        this.setEmail(email);
        this.setPassword(password);
        this.setUsername(username);
        this.setEsAdmin(esAdmin);
    }

    // Getters y Setters con validaciones

    /**
     * Obtiene el identificador del usuario.
     *
     * @return Identificador del usuario.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Establece el identificador del usuario.
     *
     * @param userId Identificador del usuario.
     * @throws IllegalArgumentException si el identificador es menor o igual a 0.
     */
    public void setUserId(int userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("El userId debe ser mayor que 0");
        }
        this.userId = userId;
    }

    /**
     * Obtiene el nombre completo del usuario.
     *
     * @return Nombre completo del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre completo del usuario.
     *
     * @param nombre Nombre completo del usuario.
     * @throws IllegalArgumentException si el nombre es nulo o no tiene la longitud adecuada.
     */
    public void setNombre(String nombre) {
		if (nombre == null || nombre.length() > MAX_FULLNAME_LENGTH) {
			throw new IllegalArgumentException("El nombre no puede ser nulo y debe tenerun máximo de " + MAX_FULLNAME_LENGTH + " caracteres");
		}
		this.nombre = nombre;
	}

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return Correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param email Correo electrónico del usuario.
     * @throws IllegalArgumentException si el correo es nulo, excede la longitud máxima o no es válido.
     */
    public void setEmail(String email) {
        if (email == null || email.length() > MAX_EMAIL_LENGTH || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("El email no puede ser nulo, debe tener un m\u00E1ximo de " 
                    + MAX_EMAIL_LENGTH + " caracteres y debe ser v\u00E1lido");
        }
        this.email = email;
    }

    /**
     * Obtiene la contrase\u00F1a del usuario.
     *
     * @return Contrase\u00F1a del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contrase\u00F1a del usuario.
     *
     * @param password Contrase\u00F1a del usuario.
     * @throws IllegalArgumentException si la contrase\u00F1a es nula o no tiene la longitud adecuada.
     */
    public void setPassword(String password) {
        if (password == null || password.length() > MAX_PASSWORD_LENGTH || password.length() < MIN_PASSWORD_LENGTH) {
            throw new IllegalArgumentException("La contrase\u00F1a no puede ser nula y debe tener un m\u00EDnimo de "
                    + MIN_PASSWORD_LENGTH + " y un m\u00E1ximo de " + MAX_PASSWORD_LENGTH + " caracteres");
        }
        this.password = password;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return Nombre de usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param username Nombre de usuario.
     * @throws IllegalArgumentException si el nombre de usuario es nulo, no tiene la longitud adecuada o contiene caracteres no válidos.
     */
    public void setUsername(String username) {
        if (username == null || username.length() > MAX_USERNAME_LENGTH || username.length() < MIN_USERNAME_LENGTH || !username.matches("^[A-Za-z0-9_]+$")) {
            throw new IllegalArgumentException("El nombre de usuario no puede ser nulo, debe tener un m\u00EDnimo de "
                    + MIN_USERNAME_LENGTH + ", un m\u00E1ximo de " + MAX_USERNAME_LENGTH
                    + " caracteres y no puede contener espacios ni caracteres especiales");
        }
        this.username = username;
    }

    /**
     * Obtiene el estado de administrador del usuario.
     *
     * @return {@code true} si el usuario es administrador, {@code false} en caso contrario.
     */
    public boolean getEsAdmin() {
        return esAdmin;
    }

    /**
     * Establece el estado de administrador del usuario.
     *
     * @param esAdmin Estado de administrador del usuario.
     */
    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    /**
     * Retorna una representación en cadena del usuario.
     *
     * @return Una cadena que representa al usuario.
     */
    @Override
    public String toString() {
        return "Usuario{" + "userId=" + userId + ", nombre='" + nombre + '\'' + ", email='" + email + '\''
                + ", password='" + password + '\'' + ", username='" + username + '\'' + ", esAdmin=" + esAdmin + '}';
    }
    
    /**
     * Convierte el usuario a una línea CSV.
     *
     * @return Una cadena que representa al usuario en formato CSV.
     */
    public String toCSV() {
        return userId + "," + nombre + "," + email + "," + username + "," + password + "," + esAdmin;
    }
}
