package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Usuario;

public class CtrlUsuario {
	private List<Usuario> usuarios;
	private static final String FILE_PATH = "resources/data/usuarios.dat";

	public CtrlUsuario() {
		this.usuarios = new ArrayList<>();
		loadFromFile();
	}

	// Obtener todos los usuarios
	public List<Usuario> getAllUsuarios() {
		return new ArrayList<>(usuarios);
	}

	// Agregar un nuevo usuario
	public void addUsuario(Usuario usuario) {
		usuarios.add(usuario);
		saveToFile();
	}

	// Buscar un usuario por su ID
	public Optional<Usuario> getUsuarioById(int userId) {
		return usuarios.stream().filter(usuario -> usuario.getUserId() == userId).findFirst();
	}

	// Actualizar un usuario
	public boolean updateUsuario(int userId, String nombre, String apellido, short edad, String correo,
			String contraseña) {
		boolean updated = false;
		Optional<Usuario> usuarioOpt = getUsuarioById(userId);
		if (usuarioOpt.isPresent()) {
			Usuario usuario = usuarioOpt.get();
			usuario.setNombre(nombre);
			usuario.setApellido(apellido);
			usuario.setEdad(edad);
			usuario.setCorreo(correo);
			usuario.setContrasena(contraseña);
			saveToFile();
			updated = true;
		}
		return updated;
	}

	// Eliminar un usuario por su ID
	public boolean deleteUsuario(int userId) {
		boolean removed = false;

		Optional<Usuario> usuarioOpt = getUsuarioById(userId);
		if (usuarioOpt.isPresent()) {
			usuarios.remove(usuarioOpt.get());
			saveToFile();
			removed = true;
		}
		return removed;
	}

	// Guardar la lista de usuarios en un archivo
	private void saveToFile() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
			oos.writeObject(usuarios);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Cargar la lista de usuarios desde un archivo
	@SuppressWarnings("unchecked")
	private void loadFromFile() {
		File file = new File(FILE_PATH);
		if (file.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
				usuarios = (List<Usuario>) ois.readObject();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
