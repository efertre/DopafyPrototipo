package view.register;

import java.sql.ResultSet;
import java.sql.SQLException;
import dbm.DataBase;

public class CtrDiaRegister {

    public void register(String username, String password, String email) throws Exception {
        DataBase.open();  // Asegúrate de abrir la conexión a la base de datos

        try {
            if (userExists(username)) {
                throw new Exception("Nombre de usuario ya existente.");
            }

            String sql = "INSERT INTO USUARIO (nombre, password, email, username) VALUES (?, ?, ?, ?)";
            DataBase.executePreparedDML(sql, username, password, email, username);
        } finally {
            DataBase.close();  // Asegúrate de cerrar la conexión a la base de datos
        }
    }

    private boolean userExists(String username) throws SQLException {
        String sql = "SELECT COUNT(*) FROM USUARIO WHERE username = ?";
        ResultSet rs = DataBase.executePreparedQuery(sql, username);

        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
        return false;
    }
}
