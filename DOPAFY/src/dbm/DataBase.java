package dbm;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

	private static Connection conn = null;

	private DataBase() throws Exception {
		new Config();
	}

	public static Connection open() throws Exception {
		if (conn == null) {
			new DataBase();
		}
		conn = DriverManager.getConnection(Config.url, Config.userName, Config.userPass);
		return conn;
	}

	public static void close() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception ignore) {};
	}
	
	public static void initTransacction() throws SQLException {
		conn.setAutoCommit(false);
	}

	public static void commitTransacction() throws SQLException {
		conn.commit();
	}

	public static void rollbackTransacction() throws SQLException {
		conn.rollback();
	}

	public static ResultSet executeQuery(String sql) throws SQLException {
		return (conn == null) ? null : conn.createStatement().executeQuery(sql);
	}

	public static int executeDML(String sql) throws SQLException {
		return (conn == null) ? 0 : conn.createStatement().executeUpdate(sql);
	}

	public static boolean executeDDL(String sql) throws SQLException {
		return (conn == null) ? false : conn.createStatement().execute(sql);
	}

	public static DatabaseMetaData getMetaData() throws SQLException {
		return (conn == null) ? null : conn.getMetaData();
	}

	public static String[] getTableNames() throws SQLException {
		List<String> lstTableName = new ArrayList<>();
		try (ResultSet rs = conn.getMetaData().getTables(null, null, null, new String[] { "TABLE" })) {
			while (rs.next()) {
				lstTableName.add(rs.getString("TABLE_NAME"));
			}
		}
		return lstTableName.toArray(new String[0]);
	}

	public static String[] getColumnNames(String sql) throws SQLException {
	    if (!sql.trim().contains(" ")) sql = "SELECT * FROM " + sql;
	    try (ResultSet rs = executeQuery(sql)) {
	        ResultSetMetaData rsmd = rs.getMetaData();
	        int colCount = rsmd.getColumnCount();
	        String[] names = new String[colCount];
	        for (int i = 1; i <= colCount; i++) {
	            names[i - 1] = rsmd.getColumnName(i);
	        }
	        return names;
	    }
	}

	public static int rows(String sql) throws SQLException {
		int rowCount = 0;
		if (!sql.trim().contains(" ")) sql = "SELECT * FROM " + sql;
		try (ResultSet rs = executeQuery(sql)) {
			while (rs.next()) {
				rowCount++;
			}
		}
		return rowCount;
	}
	
	public static ResultSet executePreparedQuery(String sql, Object... parametros) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int index = 1;
	    for (Object parametro : parametros) {
	    	pstmt.setObject(index++, parametro);
	    }
		return pstmt.executeQuery();
	}
	
	public static int executePreparedDML(String sql, Object... parametros) throws SQLException {
        int filasAfectadas = 0;
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
	        int index = 1;
	        for (Object parametro : parametros) {
	            pstmt.setObject(index++, parametro);
	        }
			filasAfectadas = pstmt.executeUpdate();
		}
		return filasAfectadas;
	}

}