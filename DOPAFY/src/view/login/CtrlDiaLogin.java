package view.login;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import dbm.DataBase;
import model.Usuario;
import view.user.DataUser;

/**
 * Controlador para la autenticaci\u00F3n de usuarios durante el inicio de sesi\u00F3n.
 * Permite verificar las credenciales de usuario y proporcionar un objeto Usuario si la autenticaci\u00F3n es exitosa.
 */
public class CtrlDiaLogin {

    /**
     * Método para autenticar a un usuario basado en su nombre de usuario y contrase\u00F1a.
     *
     * @param username El nombre de usuario del usuario que intenta iniciar sesi\u00F3n.
     * @param password La contraseña proporcionada por el usuario para iniciar sesi\u00F3n.
     * @return Un Optional que contiene un objeto Usuario si la autenticaci\u00F3n es exitosa; de lo contrario, es vac\u00EDo.
     * @throws Exception Si ocurre un error durante la ejecuci\u00F3n de la consulta SQL.
     */
    public Optional<Usuario> authenticate(String username, String password) throws Exception {
        String sql = "SELECT * FROM USUARIO WHERE username = ?";
        
        try {
            DataBase.open(); // Abrir conexi\u00F3n a la base de datos
            ResultSet rs = DataBase.executePreparedQuery(sql, username); // Ejecutar consulta preparada
            if (rs.next()) { // Si se encontr\u00F3 un usuario con el nombre de usuario dado
                
                String storedPassword = rs.getString("password"); // Obtener la contrase\u00F1a almacenada en la base de datos
                DataUser.userId = rs.getInt("userId"); // Establecer el ID de usuario en DataUser para uso posterior
                DataUser.loggedInUsername = rs.getString("username"); // Establecer el nombre de usuario en DataUser
                
                // Validar si la contrase\u00F1a ingresada es igual a la almacenada en la base de datos
                // Se utiliza equals para comparar las cadenas de forma sensible a may\u00FAsculas y min\u00FAsculas
                if (password.equals(storedPassword)) {
                    // Crear un objeto Usuario con los datos obtenidos de la base de datos
                    Usuario usuario = new Usuario(rs.getInt("userId"), rs.getString("nombre"), rs.getString("email"),
                            storedPassword, rs.getString("username"), rs.getBoolean("esAdmin"));
                    return Optional.of(usuario); // Devolver un Optional que contiene el usuario autenticado
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprimir el error en caso de excepci\u00F3n SQL
        } finally {
            DataBase.close(); // Cerrar la conexi\u00F3n a la base de datos en cualquier caso
        }
        return Optional.empty(); // Devolver Optional vac\u00EDo si la autenticaci\u00F3n falla
    }
}
