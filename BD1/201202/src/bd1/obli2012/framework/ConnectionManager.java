package bd1.obli2012.framework;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionManager {

	/**
	 * Retorna una conexión a la base de datos
	 * 
	 * @return una instancia de {@link Connection}
	 */
	public Connection obtenerConexion();

	/**
	 * Cierra la conexión a la base de datos
	 * @throws SQLException 
	 */
	public void cerrarConexion();

}