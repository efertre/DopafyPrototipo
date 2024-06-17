package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import dbm.DataBase;

public class CtrlUsuario {

	public void addUser(String nombre, String email, String username, String password, boolean esAdmin) {
		String sql = "INSERT INTO USUARIO (nombre, email, username, password, esAdmin) VALUES (?, ?, ?, ?, ?)";
		try {
			DataBase.open();
			DataBase.executePreparedDML(sql, nombre, email, username, password, esAdmin);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBase.close();
		}
	}

	public void updateUser(String id, String nombre, String email, String username, String password, boolean esAdmin) {
		String sql = "UPDATE USUARIO SET nombre = ?, email = ?, username = ?, password = ?, esAdmin = ? WHERE userId = ?";
		try {
			DataBase.open();
			DataBase.executePreparedDML(sql, nombre, email, username, password, esAdmin, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBase.close();
		}
	}

	public void deleteUser(String id) {

		String sql = "DELETE FROM USUARIO WHERE userId = ?";
		try {
			DataBase.open();
			DataBase.executePreparedDML(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBase.close();
		}
	}

	public void loadUsuarios(DefaultTableModel tableModel) {
		tableModel.setRowCount(0);
		String sql = "SELECT * FROM USUARIO";
		try {
			DataBase.open();
			ResultSet rs = DataBase.executeQuery(sql);
			while (rs.next()) {
				String id = rs.getString("userId");
				String nombre = rs.getString("nombre");
				String email = rs.getString("email");
				String username = rs.getString("username");
				String password = rs.getString("password");
				boolean esAdmin = rs.getBoolean("esAdmin");
				tableModel.addRow(new Object[] { id, nombre, email, username, password, esAdmin });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBase.close();
		}
	}

	public void searchUsers(DefaultTableModel tableModel, String id, String username) {
        tableModel.setRowCount(0);
        StringBuilder sql = new StringBuilder("SELECT * FROM USUARIO WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (!id.isEmpty()) {
            sql.append(" AND userId = ?");
            params.add(id);
        }
        if (!username.isEmpty()) {
            sql.append(" AND username LIKE ?");
            params.add("%" + username + "%");
        }

        try {
            DataBase.open();
            ResultSet rs = DataBase.executePreparedQuery(sql.toString(), params.toArray());
            while (rs.next()) {
                String userId = rs.getString("userId");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String userUsername = rs.getString("username");
                String password = rs.getString("password");
                boolean esAdmin = rs.getBoolean("esAdmin");
                tableModel.addRow(new Object[]{userId, nombre, email, userUsername, password, esAdmin});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataBase.close();
        }
    }
}
