package view.login;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import dbm.DataBase;
import model.Usuario;

public class CtrDiaLogin {

	// Autenticar usuario
	public Optional<Usuario> authenticate(String username, String password) throws Exception {
		String sql = "SELECT * FROM USUARIO WHERE username = ?";
		try {
			DataBase.open();
			ResultSet rs = DataBase.executePreparedQuery(sql, username);
			if (rs.next()) {

				String storedPassword = rs.getString("password");
				// Validación para comprobar si los datos son exactamente a los guardados en la
				// base de datos (sensibilidad a la capitalización)
				if (password.equals(storedPassword)) {
					Usuario usuario = new Usuario(rs.getInt("userId"), rs.getString("nombre"), rs.getString("email"),
							storedPassword, rs.getString("username"), rs.getBoolean("esAdmin"));
					return Optional.of(usuario);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.close();
		}
		return Optional.empty();
	}
}
