package bd1.obli2012.framework;

import bd1.obli2012.Util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;


/**
 * 
 * @author gnasi
 *
 */
public class PostgresConnectionManager implements ConnectionManager{

	private static final Logger LOGGER = Logger
			.getLogger(PostgresConnectionManager.class.getName());
	private static final String baseURL = "jdbc:postgresql://%s:%s/%s";
	private static ConnectionManager instance;
	private Connection conn;
    private String dbHost;

	private PostgresConnectionManager() {
	}

	/**
	 * Obtiene una instancia del ConnectionManager
	 * 
	 * @return
	 */
	public static ConnectionManager getInstance() {
		if (instance == null) {
			instance = new PostgresConnectionManager();
		}
		return instance;
	}

	/* (non-Javadoc)
	 * @see uy.edu.ucu.bd1.gnasi.ConnectionManager#obtenerConexion()
	 */
	@Override
	public Connection obtenerConexion() {

		if (this.conn == null) {
			Properties props = Util.getProperties();
			// Cadena de conexión
			// jdbc:postgresql://host:port/database
			String url = String.format(baseURL, props.get("db.host"),
					props.get("db.port"), props.get("db.name"));
			String user = props.getProperty("db.user");
			String passsword = props.getProperty("db.userPassword");
			try {
				LOGGER.info("Estableciendo conexión");
				this.conn = DriverManager.getConnection(url, user, passsword);
				LOGGER.info("Conexión establecida");
                                this.dbHost = (String) props.get("db.host");
			} catch (SQLException e) {
				LOGGER.severe("Ocurrió un error al establecer la conexión");
				LOGGER.severe(e.getMessage());
			}
		}
		return this.conn;
	}

	/* (non-Javadoc)
	 * @see uy.edu.ucu.bd1.gnasi.ConnectionManager#cerrarConexion()
	 */
	@Override
	public void cerrarConexion() {
		if (this.conn != null) {
			try {
				this.conn.close();
			} catch (SQLException e) {
				LOGGER.severe("ERROR al cerrar la conexión");
				LOGGER.severe(e.getMessage());
			}
		}
	}
        
        public String getDbHost() {
            return this.dbHost;
        }
}
