package view.user.goals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dbm.DataBase;

public class CtrlPanGoals {

	// Método para obtener todos los objetivos de un usuario
	public List<Object[]> getGoals(int userId) throws Exception {
		List<Object[]> goals = new ArrayList<>();
		String sql = "SELECT goalId, descripcion, fechaInicio, progreso, cantidadPuntos, dificultad " + "FROM OBJETIVO "
				+ "WHERE userId = ?";
		try {
			DataBase.open(); // Abrir la conexión a la base de datos
			ResultSet rs = DataBase.executePreparedQuery(sql, userId);
			while (rs.next()) {
				goals.add(
						new Object[] { rs.getInt("goalId"), rs.getString("descripcion"), rs.getTimestamp("fechaInicio"),
								rs.getInt("progreso"), rs.getInt("cantidadPuntos"), rs.getString("dificultad") });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.close(); // Cerrar la conexión a la base de datos en cualquier caso
		}
		return goals;
	}

	// Método para añadir un nuevo objetivo
	public boolean addGoal(int userId, String descripcion, Timestamp fechaInicio, int progreso, int cantidadPuntos, String dificultad) throws Exception {
	    String sql = "INSERT INTO OBJETIVO ( descripcion, userId, fechaInicio, progreso, cantidadPuntos, dificultad) VALUES (?, ?, ?, ?, ?, ?)";
	    try {
	    	
	    	
	        // Iniciar la conexión y la transacción
	        DataBase.open();
	        DataBase.initTransacction();

	        // Insertar en la tabla OBJETIVO
	        DataBase.executePreparedDML(sql, descripcion, userId, fechaInicio, progreso, cantidadPuntos, dificultad);

	        // Confirmar la transacción si todo ha sido exitoso
	        DataBase.commitTransacction();
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            // En caso de error, revertir la transacción
	            DataBase.rollbackTransacction();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        return false;
	    } finally {
	        // Cerrar la conexión en el bloque finally
	        DataBase.close();
	    }
	}

	// Método para editar un objetivo existente
	public boolean editGoal(int goalId, String descripcion, Timestamp fechaInicio, int progreso, int puntosAsignados, String dificultad) throws Exception {
	    String sql = "UPDATE OBJETIVO SET descripcion = ?, fechaInicio = ?, progreso = ?, cantidadPuntos = ?, dificultad = ? WHERE goalId = ?";
	    try {
	        DataBase.open(); // Abrir la conexión a la base de datos
	        DataBase.initTransacction();
	        DataBase.executePreparedDML(sql, descripcion, fechaInicio, progreso, puntosAsignados, dificultad, goalId);
	        DataBase.commitTransacction();
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            DataBase.rollbackTransacction();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        return false;
	    } finally {
	        DataBase.close(); // Cerrar la conexión a la base de datos en cualquier caso
	    }
	}


	// Método para eliminar un objetivo
	public boolean deleteGoal(int goalId) throws Exception {
		String sql = "DELETE FROM OBJETIVO WHERE goalId = ?";
		try {
			DataBase.open(); // Abrir la conexión a la base de datos
			DataBase.executePreparedDML(sql, goalId);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			DataBase.close(); // Cerrar la conexión a la base de datos en cualquier caso
		}
	}

	// Método para buscar objetivos por descripción
	public List<Object[]> searchGoals(int userId, String descripcion) throws Exception {
		List<Object[]> goals = new ArrayList<>();
		String sql = "SELECT goalId, descripcion, fechaInicio, progreso, dificultad " + "FROM OBJETIVO "
				+ "WHERE userId = ? AND descripcion LIKE ?";
		try {
			DataBase.open(); // Abrir la conexión a la base de datos
			ResultSet rs = DataBase.executePreparedQuery(sql, userId, "%" + descripcion + "%");
			while (rs.next()) {
				goals.add(new Object[] { rs.getInt("goalId"), rs.getString("descripcion"),
						rs.getTimestamp("fechaInicio"), rs.getInt("progreso"), rs.getString("dificultad") });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.close(); // Cerrar la conexión a la base de datos en cualquier caso
		}
		return goals;
	}
}
