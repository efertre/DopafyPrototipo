package controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Usuario;
import dbm.DataBase;

public class CtrlUsuario {

	// Obtener todos los usuarios
	public List<Usuario> getAllUsuarios() throws Exception {
		List<Usuario> usuarios = new ArrayList<>();
		String sql = "SELECT * FROM USUARIO";
		try {
			DataBase.open();
			ResultSet rs = DataBase.executeQuery(sql);
			while (rs.next()) {
				Usuario usuario = new Usuario(rs.getInt("userId"), rs.getString("nombre"), rs.getString("email"),
						rs.getString("password"), rs.getString("username"), rs.getBoolean("esAdmin"));
				usuarios.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.close();
		}
		return usuarios;
	}

	// Agregar un nuevo usuario
	public void addUsuario(Usuario usuario) throws Exception {
		String sql = "INSERT INTO USUARIO (userId, nombre, email, password, username, esAdmin) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			DataBase.open();
			DataBase.executePreparedDML(sql, usuario.getUserId(), usuario.getNombre(), usuario.getEmail(),
					usuario.getPassword(), usuario.getUsername(), usuario.getEsAdmin());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.close();
		}
	}

	// Buscar un usuario por su ID
	public Optional<Usuario> getUsuarioById(int userId) throws Exception {
		String sql = "SELECT * FROM USUARIO WHERE userId = ?";
		try {
			DataBase.open();
			ResultSet rs = DataBase.executePreparedQuery(sql, userId);
			if (rs.next()) {
				Usuario usuario = new Usuario(rs.getInt("userId"), rs.getString("nombre"), rs.getString("email"),
						rs.getString("password"), rs.getString("username"), rs.getBoolean("esAdmin"));
				return Optional.of(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.close();
		}
		return Optional.empty();
	}

	// Actualizar un usuario
	public boolean updateUsuario(int userId, String nombre, String email, String password, String username,
			boolean esAdmin) throws Exception {
		String sql = "UPDATE USUARIO SET nombre = ?, email = ?, password = ?, username = ?, esAdmin = ? WHERE userId = ?";
		try {
			DataBase.open();
			int affectedRows = DataBase.executePreparedDML(sql, nombre, email, password, username, esAdmin, userId);
			return affectedRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.close();
		}
		return false;
	}

	// Eliminar un usuario por su ID
	public boolean deleteUsuario(int userId) throws Exception {
		String sql = "DELETE FROM USUARIO WHERE userId = ?";
		try {
			DataBase.open();
			int affectedRows = DataBase.executePreparedDML(sql, userId);
			return affectedRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.close();
		}
		return false;
	}
}
