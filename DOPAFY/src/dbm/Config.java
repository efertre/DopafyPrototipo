package dbm;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class Config {

	public static String url = null;
	public static String userName = null;
	public static String userPass = null;
	
	private final String FILE_CONFIG = "db/dbconfiguration.properties";

	public Config() throws Exception {
		Properties prop = new Properties();
		prop.load(new InputStreamReader(new FileInputStream(FILE_CONFIG)));

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