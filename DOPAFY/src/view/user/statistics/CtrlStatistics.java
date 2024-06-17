package view.user.statistics;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dbm.DataBase;
import model.DificultadObjetivo;
import model.Objetivo;

/**
 * Controlador para gestionar operaciones relacionadas con estadísticas de objetivos de usuario.
 */
public class CtrlStatistics {

    /**
     * Obtiene la lista de objetivos completados por un usuario.
     * 
     * @param userId ID del usuario
     * @return Lista de objetivos completados
     * @throws Exception Si ocurre algún error durante la consulta a la base de datos
     */
    public List<Objetivo> getCompletedGoals(int userId) throws Exception {
        List<Objetivo> completedGoals = new ArrayList<>();
        String sql = "SELECT * FROM OBJETIVO WHERE userId = ? AND progreso = 100";

        try {
            DataBase.open(); // Abrir conexi\u00F3n a la base de datos
            ResultSet rs = DataBase.executePreparedQuery(sql, userId);

            while (rs.next()) {
                String descripcion = rs.getString("descripcion");
                Timestamp fechaInicio = rs.getTimestamp("fechaInicio");
                int progreso = rs.getInt("progreso");
                int cantidadPuntos = rs.getInt("cantidadPuntos");
                String dificultadStr = rs.getString("dificultad");
                DificultadObjetivo dificultad = DificultadObjetivo.valueOf(dificultadStr); // Convertir el String a enum DificultadObjetivo

                Objetivo objetivo = new Objetivo(descripcion, userId, progreso, fechaInicio, cantidadPuntos,
                        dificultad);
                completedGoals.add(objetivo);
            }
        } catch (SQLException e) {
           
        } finally {
            DataBase.close(); // Cerrar conexi\u00F3n a la base de datos en el bloque finally
        }

        return completedGoals;
    }

    /**
     * Obtiene la lista de todos los objetivos de un usuario.
     * 
     * @param userId ID del usuario
     * @return Lista de todos los objetivos del usuario
     * @throws Exception Si ocurre alg\u00FAn error durante la consulta a la base de datos
     */
    public List<Objetivo> getTotalGoals(int userId) throws Exception {
        List<Objetivo> totalGoals = new ArrayList<>();
        String sql = "SELECT * FROM OBJETIVO WHERE userId = ?";

        try {
            DataBase.open(); // Abrir conexi\u00F3n a la base de datos
            ResultSet rs = DataBase.executePreparedQuery(sql, userId);

            while (rs.next()) {
                String descripcion = rs.getString("descripcion");
                Timestamp fechaInicio = rs.getTimestamp("fechaInicio");
                int progreso = rs.getInt("progreso");
                int cantidadPuntos = rs.getInt("cantidadPuntos");
                String dificultadStr = rs.getString("dificultad");
                DificultadObjetivo dificultad = DificultadObjetivo.valueOf(dificultadStr); // Convertir el String a enum Dificultad

                Objetivo objetivo = new Objetivo(descripcion, userId, progreso, fechaInicio, cantidadPuntos,
                        dificultad);
                totalGoals.add(objetivo);
            }
        } catch (SQLException e) {
            
        } finally {
            DataBase.close(); // Cerrar conexi\u00F3n a la base de datos en el bloque finally
        }

        return totalGoals;
    }

    /**
     * Obtiene un mapa con la cantidad de objetivos agrupados por dificultad para un usuario.
     * 
     * @param userId ID del usuario
     * @return Mapa con la cantidad de objetivos por cada dificultad
     * @throws Exception Si ocurre algún error durante la consulta a la base de datos
     */
    public Map<String, Integer> getGoalsByDifficulty(int userId) throws Exception {
        Map<String, Integer> goalsByDifficulty = new HashMap<>();
        String sql = "SELECT dificultad, COUNT(*) AS count FROM OBJETIVO WHERE userId = ? GROUP BY dificultad";

        try {
            DataBase.open(); // Abrir conexión a la base de datos
            ResultSet rs = DataBase.executePreparedQuery(sql, userId);

            while (rs.next()) {
                String dificultadStr = rs.getString("dificultad");
                DificultadObjetivo dificultad = DificultadObjetivo.valueOf(dificultadStr); // Convertir el String a enum Dificultad
                int count = rs.getInt("count");

                goalsByDifficulty.put(dificultad.toString(), count);
            }
        } catch (SQLException e) {
            
        } finally {
            DataBase.close(); // Cerrar conexi\u00F3n a la base de datos en el bloque finally
        }

        return goalsByDifficulty;
    }

    /**
     * Obtiene la cantidad total de puntos acumulados por un usuario a partir de los objetivos completados.
     * 
     * @param userId ID del usuario
     * @return Cantidad total de puntos del usuario
     * @throws Exception Si ocurre alg\u00FAn error durante la consulta a la base de datos
     */
    public int getUserPoints(int userId) throws Exception {
        int totalPoints = 0;
        String sql = "SELECT SUM(cantidadPuntos) AS totalPoints FROM OBJETIVO WHERE userId = ? AND progreso = 100";

        try {
            DataBase.open(); // Abrir conexi\u00F3n a la base de datos
            ResultSet rs = DataBase.executePreparedQuery(sql, userId);

            if (rs.next()) {
                totalPoints = rs.getInt("totalPoints");
            }
        } catch (SQLException e) {
            
        } finally {
            DataBase.close(); // Cerrar conexi\u00F3n a la base de datos en el bloque finally
        }

        return totalPoints;
    }
}
