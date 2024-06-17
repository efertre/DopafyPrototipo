package view.register;

import java.sql.ResultSet;
import java.sql.SQLException;
import dbm.DataBase;

/**
 * Controlador para el registro de usuarios en la base de datos.
 */
public class CtrlDiaRegister {

    /**
     * Registra un nuevo usuario en la base de datos.
     *
     * @param username Nombre de usuario.
     * @param password Contrase\u00F1a del usuario.
     * @param email Direcci\u00F3n de correo electr\u00F3nico del usuario.
     * @throws Exception Si ocurre algún error durante el registro, como nombre de usuario ya existente.
     */
    public void register(String username, String password, String email) throws Exception {
        DataBase.open();  // Apertura de la conexi\u00F3n a la base de datos

        try {
            if (userExists(username)) {
                throw new Exception("Nombre de usuario ya existente.");
            }

            String sql = "INSERT INTO USUARIO (nombre, password, email, username) VALUES (?, ?, ?, ?)";
            DataBase.executePreparedDML(sql, username, password, email, username);
        } finally {
            DataBase.close();  // Cierre de la conexi\u00F3n a la base de datos
        }
    }

    /**
     * Verifica si un nombre de usuario ya existe en la base de datos.
     *
     * @param username Nombre de usuario a verificar.
     * @return true si el nombre de usuario ya existe en la base de datos, false de lo contrario.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
    private boolean userExists(String username) throws SQLException {
        String sql = "SELECT COUNT(*) FROM USUARIO WHERE username = ?";
        ResultSet rs = DataBase.executePreparedQuery(sql, username);

        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
        return false;
    }
}

