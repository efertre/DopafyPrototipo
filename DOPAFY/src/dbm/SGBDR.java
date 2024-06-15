package dbm;

public enum SGBDR {
	
	SQLITE, MYSQL, ACCESS, ORACLE, SQLSERVER;

	public static SGBDR fromString(String str) {
		for (SGBDR sgbdr : SGBDR.values()) {
			if (sgbdr.name().equalsIgnoreCase(str)) {
				return sgbdr;
			}
		}
		throw new IllegalArgumentException("Tipo de SGBDR no válido: " + str);
	}
}