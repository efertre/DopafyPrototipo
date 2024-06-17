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

/**
 * La clase DataBase proporciona métodos estáticos para la gestión de una
 * conexión de base de datos, incluyendo la ejecución de consultas y comandos
 * DML/DDL, así como la gestión de transacciones.
 */
public class DataBase {

    private static Connection conn = null;

    /**
     * Constructor privado que inicializa la configuración de la base de datos.
     *
     * @throws Exception si ocurre un error al cargar la configuración.
     */
    private DataBase() throws Exception {
        new Config();
    }

    /**
     * Abre una conexión a la base de datos utilizando los parámetros de configuración.
     *
     * @return La conexión abierta.
     * @throws Exception si ocurre un error al abrir la conexión.
     */
    public static Connection open() throws Exception {
        if (conn == null) {
            new DataBase();
        }
        conn = DriverManager.getConnection(Config.url, Config.userName, Config.userPass);
        return conn;
    }

    /**
     * Cierra la conexión a la base de datos.
     */
    public static void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception ignore) {}
    }

    /**
     * Inicia una transacción estableciendo el modo de autocommit en false.
     *
     * @throws SQLException si ocurre un error al iniciar la transacción.
     */
    public static void initTransacction() throws SQLException {
        conn.setAutoCommit(false);
    }

    /**
     * Confirma la transacción actual.
     *
     * @throws SQLException si ocurre un error al confirmar la transacción.
     */
    public static void commitTransacction() throws SQLException {
        conn.commit();
    }

    /**
     * Revierte la transacción actual.
     *
     * @throws SQLException si ocurre un error al revertir la transacción.
     */
    public static void rollbackTransacction() throws SQLException {
        conn.rollback();
    }

    /**
     * Ejecuta una consulta SQL y devuelve el ResultSet resultante.
     *
     * @param sql La consulta SQL a ejecutar.
     * @return El ResultSet resultante.
     * @throws SQLException si ocurre un error al ejecutar la consulta.
     */
    public static ResultSet executeQuery(String sql) throws SQLException {
        return (conn == null) ? null : conn.createStatement().executeQuery(sql);
    }

    /**
     * Ejecuta un comando DML (Data Manipulation Language) y devuelve el número de filas afectadas.
     *
     * @param sql El comando DML a ejecutar.
     * @return El número de filas afectadas.
     * @throws SQLException si ocurre un error al ejecutar el comando.
     */
    public static int executeDML(String sql) throws SQLException {
        return (conn == null) ? 0 : conn.createStatement().executeUpdate(sql);
    }

    /**
     * Ejecuta un comando DDL (Data Definition Language).
     *
     * @param sql El comando DDL a ejecutar.
     * @return true si el comando se ejecutó correctamente, false de lo contrario.
     * @throws SQLException si ocurre un error al ejecutar el comando.
     */
    public static boolean executeDDL(String sql) throws SQLException {
        return (conn == null) ? false : conn.createStatement().execute(sql);
    }

    /**
     * Obtiene los metadatos de la base de datos.
     *
     * @return Los metadatos de la base de datos.
     * @throws SQLException si ocurre un error al obtener los metadatos.
     */
    public static DatabaseMetaData getMetaData() throws SQLException {
        return (conn == null) ? null : conn.getMetaData();
    }

    /**
     * Obtiene los nombres de todas las tablas en la base de datos.
     *
     * @return Un array de nombres de tablas.
     * @throws SQLException si ocurre un error al obtener los nombres de las tablas.
     */
    public static String[] getTableNames() throws SQLException {
        List<String> lstTableName = new ArrayList<>();
        try (ResultSet rs = conn.getMetaData().getTables(null, null, null, new String[] { "TABLE" })) {
            while (rs.next()) {
                lstTableName.add(rs.getString("TABLE_NAME"));
            }
        }
        return lstTableName.toArray(new String[0]);
    }

    /**
     * Obtiene los nombres de las columnas de una tabla basada en una consulta SQL.
     *
     * @param sql La consulta SQL.
     * @return Un array de nombres de columnas.
     * @throws SQLException si ocurre un error al obtener los nombres de las columnas.
     */
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

    /**
     * Obtiene el número de filas de una tabla basada en una consulta SQL.
     *
     * @param sql La consulta SQL.
     * @return El número de filas.
     * @throws SQLException si ocurre un error al obtener el número de filas.
     */
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

    /**
     * Ejecuta una consulta SQL preparada con parámetros y devuelve el ResultSet resultante.
     *
     * @param sql        La consulta SQL.
     * @param parametros Los parámetros para la consulta preparada.
     * @return El ResultSet resultante.
     * @throws SQLException si ocurre un error al ejecutar la consulta.
     */
    public static ResultSet executePreparedQuery(String sql, Object... parametros) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        int index = 1;
        for (Object parametro : parametros) {
            pstmt.setObject(index++, parametro);
        }
        return pstmt.executeQuery();
    }

    /**
     * Ejecuta un comando DML preparado con parámetros y devuelve el número de filas afectadas.
     *
     * @param sql        El comando DML.
     * @param parametros Los parámetros para el comando preparado.
     * @return El número de filas afectadas.
     * @throws SQLException si ocurre un error al ejecutar el comando.
     */
    public static int executePreparedDML(String sql, Object... parametros) throws SQLException {
        int filasAfectadas = 0;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            int index = 1;
            for (Object parametro : parametros) {
                pstmt.setObject(index++, parametro);
            }
            filasAfectadas = pstmt.executeUpdate();
        }
        return filasAfectadas;
    }
}
