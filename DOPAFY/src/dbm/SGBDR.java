package dbm;

/**
 * Enumeración que representa los tipos de Sistemas de Gestión de Bases de Datos
 * Relacionales (SGBDR) soportados por la aplicación.
 */
public enum SGBDR {

    /**
     * Tipo de SGBDR para SQLite.
     */
    SQLITE,

    /**
     * Tipo de SGBDR para MySQL.
     */
    MYSQL,

    /**
     * Tipo de SGBDR para Microsoft Access.
     */
    ACCESS,

    /**
     * Tipo de SGBDR para Oracle Database.
     */
    ORACLE,

    /**
     * Tipo de SGBDR para Microsoft SQL Server.
     */
    SQLSERVER;

    /**
     * Método estático para obtener un valor de la enumeración a partir de su nombre
     * en formato String.
     *
     * @param str el nombre del SGBDR como String.
     * @return el valor de la enumeración correspondiente al nombre proporcionado.
     * @throws IllegalArgumentException si el nombre proporcionado no corresponde
     *                                  a ningún valor de la enumeración.
     */
    public static SGBDR fromString(String str) {
        for (SGBDR sgbdr : SGBDR.values()) {
            if (sgbdr.name().equalsIgnoreCase(str)) {
                return sgbdr;
            }
        }
        throw new IllegalArgumentException("Tipo de SGBDR no válido: " + str);
    }
}
