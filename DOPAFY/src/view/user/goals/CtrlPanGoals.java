package view.user.goals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dbm.DataBase;

/**
 * Controlador para la gesti\u00F3n de objetivos de usuario.
 */
public class CtrlPanGoals {

    /**
     * M\u00E9todo para obtener todos los objetivos de un usuario.
     * @param userId ID del usuario cuyos objetivos se quieren obtener.
     * @return Lista de objetos que representan los objetivos del usuario.
     * @throws Exception Si ocurre alg\u00FAn error durante la obtenci\u00F3n de los datos.
     */
    public List<Object[]> getGoals(int userId) throws Exception {
        List<Object[]> goals = new ArrayList<>();
        String sql = "SELECT goalId, descripcion, fechaInicio, progreso, cantidadPuntos, dificultad " +
                     "FROM OBJETIVO " +
                     "WHERE userId = ?";
        try {
            DataBase.open(); // Abrir la conexi\u00F3n a la base de datos
            ResultSet rs = DataBase.executePreparedQuery(sql, userId);
            while (rs.next()) {
                goals.add(new Object[] { rs.getInt("goalId"), rs.getString("descripcion"), rs.getTimestamp("fechaInicio"),
                        rs.getInt("progreso"), rs.getInt("cantidadPuntos"), rs.getString("dificultad") });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataBase.close(); // Cerrar la conexi\u00F3n a la base de datos en cualquier caso
        }
        return goals;
    }

    /**
     * Método para a\u00F1adir un nuevo objetivo para un usuario.
     * @param userId ID del usuario al que se a\u00F1adir\u00E1 el objetivo.
     * @param descripcion Descripci\u00F3n del objetivo.
     * @param fechaInicio Fecha de inicio del objetivo.
     * @param progreso Progreso actual del objetivo (en porcentaje).
     * @param cantidadPuntos Cantidad de puntos asociados al objetivo.
     * @param dificultad Dificultad del objetivo.
     * @return true si se a\u00F1adi\u00F3 el objetivo correctamente, false si ocurri\u00F3 alg\u00FAn error.
     * @throws Exception Si ocurre alg\u00FAn error durante la ejecuci\u00F3n de la operaci\u00F3n.
     */
    public boolean addGoal(int userId, String descripcion, Timestamp fechaInicio, int progreso, int cantidadPuntos, String dificultad) throws Exception {
        String sql = "INSERT INTO OBJETIVO (descripcion, userId, fechaInicio, progreso, cantidadPuntos, dificultad) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            DataBase.open(); // Abrir la conexi\u00F3n a la base de datos
            DataBase.initTransacction(); // Iniciar la transacci\u00F3n

            // Insertar datos en la tabla OBJETIVO
            DataBase.executePreparedDML(sql, descripcion, userId, fechaInicio, progreso, cantidadPuntos, dificultad);

            // Confirmar la transacci\u00F3n si todo fue exitoso
            DataBase.commitTransacction();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                DataBase.rollbackTransacction(); // Revertir la transacci\u00F3n en caso de error
            } catch (SQLException ex) {
                
            }
            return false;
        } finally {
            DataBase.close(); // Cerrar la conexi\u00F3n a la base de datos en cualquier caso
        }
    }

    /**
     * Método para editar un objetivo existente.
     * @param goalId ID del objetivo a editar.
     * @param descripcion Nueva descripci\u00F3n del objetivo.
     * @param fechaInicio Nueva fecha de inicio del objetivo.
     * @param progreso Nuevo progreso del objetivo (en porcentaje).
     * @param puntosAsignados Nuevos puntos asignados al objetivo.
     * @param dificultad Nueva dificultad del objetivo.
     * @return true si se edit\u00F3 el objetivo correctamente, false si ocurri\u00F3 alg\u00FAn error.
     * @throws Exception Si ocurre alg\u00FAn error durante la ejecuci\u00F3n de la operaci\u00F3n.
     */
    public boolean editGoal(int goalId, String descripcion, Timestamp fechaInicio, int progreso, int puntosAsignados, String dificultad) throws Exception {
        String sql = "UPDATE OBJETIVO SET descripcion = ?, fechaInicio = ?, progreso = ?, cantidadPuntos = ?, dificultad = ? WHERE goalId = ?";
        try {
            DataBase.open(); // Abrir la conexi\u00F3n a la base de datos
            DataBase.initTransacction(); // Iniciar la transacci\u00F3n

            // Actualizar los datos del objetivo en la tabla OBJETIVO
            DataBase.executePreparedDML(sql, descripcion, fechaInicio, progreso, puntosAsignados, dificultad, goalId);

            // Confirmar la transacci\u00F3n si todo fue exitoso
            DataBase.commitTransacction();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                DataBase.rollbackTransacction(); // Revertir la transacci\u00F3n en caso de error
            } catch (SQLException ex) {
               
            }
            return false;
        } finally {
            DataBase.close(); // Cerrar la conexi\u00F3n a la base de datos en cualquier caso
        }
    }

    /**
     * Método para eliminar un objetivo existente.
     * @param goalId ID del objetivo a eliminar.
     * @return true si se elimin\u00F3 el objetivo correctamente, false si ocurri\u00F3 alg\u00FAn error.
     * @throws Exception Si ocurre alg\u00FAn error durante la ejecuci\u00F3n de la operaci\u00F3n.
     */
    public boolean deleteGoal(int goalId) throws Exception {
        String sql = "DELETE FROM OBJETIVO WHERE goalId = ?";
        try {
            DataBase.open(); // Abrir la conexi\u00F3n a la base de datos
            DataBase.executePreparedDML(sql, goalId);
            return true;
        } catch (SQLException e) {
           
            return false;
        } finally {
            DataBase.close(); // Cerrar la conexi\u00F3n a la base de datos en cualquier caso
        }
    }

    /**
     * M\u00E9todo para buscar objetivos por descripci\u00F3n para un usuario espec\u00EDfico.
     * @param userId ID del usuario cuyos objetivos se quieren buscar.
     * @param descripcion Descripci\u00F3n o parte de la descripci\u00F3n a buscar.
     * @return Lista de objetos que representan los objetivos encontrados que coinciden con la b\u00FAsqueda.
     * @throws Exception Si ocurre alg\u00FAn error durante la ejecuci\u00F3n de la operaci\u00F3n.
     */
    public List<Object[]> searchGoals(int userId, String descripcion) throws Exception {
        List<Object[]> goals = new ArrayList<>();
        String sql = "SELECT goalId, descripcion, fechaInicio, progreso, dificultad " +
                     "FROM OBJETIVO " +
                     "WHERE userId = ? AND descripcion LIKE ?";
        try {
            DataBase.open(); // Abrir la conexi\u00F3n a la base de datos
            ResultSet rs = DataBase.executePreparedQuery(sql, userId, "%" + descripcion + "%");
            while (rs.next()) {
                goals.add(new Object[] { rs.getInt("goalId"), rs.getString("descripcion"),
                        rs.getTimestamp("fechaInicio"), rs.getInt("progreso"), rs.getString("dificultad") });
            }
        } catch (SQLException e) {
           
        } finally {
            DataBase.close(); // Cerrar la conexi\u00F3n a la base de datos en cualquier caso
        }
        return goals;
    }
}
