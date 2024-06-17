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

public class CtrlStatistics {

	public List<Objetivo> getCompletedGoals(int userId) throws Exception {
		List<Objetivo> completedGoals = new ArrayList<>();
		String sql = "SELECT * FROM OBJETIVO WHERE userId = ? AND progreso = 100";

		try {
			DataBase.open(); // Abrir conexión a la base de datos
			ResultSet rs = DataBase.executePreparedQuery(sql, userId);

			while (rs.next()) {

				String descripcion = rs.getString("descripcion");
				Timestamp fechaInicio = rs.getTimestamp("fechaInicio");
				int progreso = rs.getInt("progreso");
				int cantidadPuntos = rs.getInt("cantidadPuntos");
				String dificultadStr = rs.getString("dificultad");
				DificultadObjetivo dificultad = DificultadObjetivo.valueOf(dificultadStr); // Convertir el String a enum
																							// Dificultad

				Objetivo objetivo = new Objetivo(descripcion, userId, progreso, fechaInicio, cantidadPuntos,
						dificultad);
				completedGoals.add(objetivo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.close(); // Cerrar conexión a la base de datos en el bloque finally
		}

		return completedGoals;
	}

	public List<Objetivo> getTotalGoals(int userId) throws Exception {
		List<Objetivo> totalGoals = new ArrayList<>();
		String sql = "SELECT * FROM OBJETIVO WHERE userId = ?";

		try {
			DataBase.open(); // Abrir conexión a la base de datos
			ResultSet rs = DataBase.executePreparedQuery(sql, userId);

			while (rs.next()) {
				String descripcion = rs.getString("descripcion");
				Timestamp fechaInicio = rs.getTimestamp("fechaInicio");
				int progreso = rs.getInt("progreso");
				int cantidadPuntos = rs.getInt("cantidadPuntos");
				String dificultadStr = rs.getString("dificultad");
				DificultadObjetivo dificultad = DificultadObjetivo.valueOf(dificultadStr); // Convertir el String a enum
																							// Dificultad

				Objetivo objetivo = new Objetivo(descripcion, userId, progreso, fechaInicio, cantidadPuntos,
						dificultad);
				totalGoals.add(objetivo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.close(); // Cerrar conexión a la base de datos en el bloque finally
		}

		return totalGoals;
	}

	public Map<String, Integer> getGoalsByDifficulty(int userId) throws Exception {
		Map<String, Integer> goalsByDifficulty = new HashMap<>();
		String sql = "SELECT dificultad, COUNT(*) AS count FROM OBJETIVO WHERE userId = ? GROUP BY dificultad";

		try {
			DataBase.open(); // Abrir conexión a la base de datos
			ResultSet rs = DataBase.executePreparedQuery(sql, userId);

			while (rs.next()) {
				String dificultadStr = rs.getString("dificultad");
				DificultadObjetivo dificultad = DificultadObjetivo.valueOf(dificultadStr); // Convertir el String a enum
																							// Dificultad
				int count = rs.getInt("count");

				goalsByDifficulty.put(dificultad.toString(), count);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.close(); // Cerrar conexión a la base de datos en el bloque finally
		}

		return goalsByDifficulty;
	}

	public int getUserPoints(int userId) throws Exception {
		int totalPoints = 0;
		String sql = "SELECT SUM(cantidadPuntos) AS totalPoints FROM OBJETIVO WHERE userId = ? AND progreso = 100";

		try {
			DataBase.open(); // Abrir conexión a la base de datos
			ResultSet rs = DataBase.executePreparedQuery(sql, userId);

			if (rs.next()) {
				totalPoints = rs.getInt("totalPoints");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.close(); // Cerrar conexión a la base de datos en el bloque finally
		}

		return totalPoints;
	}
}
