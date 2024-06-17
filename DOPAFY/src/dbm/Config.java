package dbm;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * La clase Config carga la configuración de la base de datos desde un archivo
 * de propiedades y establece los parámetros necesarios para la conexión.
 */
public class Config {

    public static String url = null;
    public static String userName = null;
    public static String userPass = null;

    private final String FILE_CONFIG = "db/dbconfiguration.properties";

    /**
     * Constructor de la clase Config. Carga la configuración de la base de datos
     * desde el archivo de propiedades y establece la URL de conexión.
     *
     * @throws Exception si ocurre un error al cargar la configuración.
     */
    public Config() throws Exception {
        Properties prop = new Properties();
        prop.load(new InputStreamReader(new FileInputStream(FILE_CONFIG), "UTF-8"));

        String TYPE = prop.getProperty("TYPE");
        String HOST = prop.getProperty("HOST");
        String PORT = prop.getProperty("PORT");
        String NAME = prop.getProperty("NAME");
        userName = prop.getProperty("USER");
        userPass = prop.getProperty("PASS");

        switch (SGBDR.fromString(TYPE)) {
            case SQLITE    -> url = "jdbc:sqlite:" + NAME;
            case MYSQL     -> url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + NAME;
            case ACCESS    -> url = "jdbc:ucanaccess://" + NAME + ";memory=true";
            case ORACLE    -> url = "jdbc:oracle:thin:@" + HOST + ":" + PORT + ":" + NAME;
            case SQLSERVER -> url = "jdbc:hyperion:sqlserver://" + HOST + ":" + PORT + ";DatabaseName=" + NAME;
        }
    }
}
