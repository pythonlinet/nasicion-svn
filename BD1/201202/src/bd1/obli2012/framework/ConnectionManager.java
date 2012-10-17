package bd1.obli2012.framework;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionManager {

    
    /**
     * Retorna una conexión al SGBD
     *
     * @return una instancia de {@link Connection}
     */
    public Connection obtenerConexion();

    /**
     * Retorna una conexión a la base de datos
     *
     * @return una instancia de {@link Connection}
     */
    public Connection obtenerConexion(String dbName);
    
    /**
     * Cierra la conexión a la base de datos
     *
     * @throws SQLException
     */
    public void cerrarConexion();
    
    /**
     * Retorna la query necesaria para listar las bases de datos en un SGBD
     * Esto se hace porque ciertas implementaciones de JDBC no permiten listar las 
     * bases de datos, por ejemplo, el JDBC de PosgreSQL
     * @return 
     */
    public String listDBsQuery();
}