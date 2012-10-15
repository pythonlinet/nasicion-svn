package bd1.obli2012.framework;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseManager {

	private ConnectionManager cm;
	private static DatabaseManager instance = null;
	private static final Logger LOGGER = Logger.getLogger(DatabaseManager.class
			.getName());

	/**
	 * Constructor por defecto
	 */
	private DatabaseManager() {

	}

	/**
	 * Retorna una instancia del DatabaseManager
	 * 
	 * @return
	 */
	public static DatabaseManager getInstance() {
		if (instance == null) {
			instance = new DatabaseManager();
		}
		return instance;
	}

	/**
	 * Ejecuta una consulta en la base de datos que no espere resultados
	 * (ej: creación de tablas)
	 * @param query - la query a ser ejecutada en la base de datos
	 * @return <code>true</code> si se realizó la consulta correctamente,
	 *         <code>false</code> si ocurrió un error al ejecutar la consulta
	 */
	public boolean executeQuery(String query) {
		boolean salida = true;
		cm = PostgresConnectionManager.getInstance();
		Connection con = cm.obtenerConexion();
		try {
			Statement st = con.createStatement();
			st.execute(query);
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al ejecutar la consulta : {0}", query);
			LOGGER.severe(e.getMessage());
			salida = false;
		}
		return salida;
	}

	/**
	 * Ejecuta una consulta contra la base de datos que espere resultados
	 * @param query - la query a ser ejecutada en la base de datos
	 * @return un {@link ResultSet} con el resultado de la consulta
	 */
	public ResultSet executeQueryWithResult(String query) {
		ResultSet rs = null;
		cm = PostgresConnectionManager.getInstance();
		Connection con = cm.obtenerConexion();
		try {
			Statement st = con.createStatement();
			rs = st.executeQuery(query);

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error al ejecutar la consulta : {0}", query);
			LOGGER.severe(e.getMessage());
		}
		return rs;
	}
    /**
     * Obtiene los esquemas presentes en la base de datos
     * @return 
     */
    public List<Schema> getDataBaseSchemas() {
        cm = PostgresConnectionManager.getInstance();
        Connection con = cm.obtenerConexion();
        List<Schema> salida = null;
        try {
            DatabaseMetaData metadata = con.getMetaData();
            ResultSet rs = metadata.getSchemas();
            salida = new ArrayList<Schema>();
            while(rs.next()){
                Schema s = new Schema(rs.getString(1));
                s.setTablas(getTablesForSchema(s.getNombre()));
                salida.add(s);
            }
            return salida;
        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
        }
        return salida;
    }
    
    public List<Table> getTablesForSchema(String nombreSchema){
        cm = PostgresConnectionManager.getInstance();
        Connection con = cm.obtenerConexion();
        List<Table> salida = null;
        try {
            DatabaseMetaData metadata = con.getMetaData();
            String[] tipoTabla = {"TABLE"};
            ResultSet rs = metadata.getTables(null, nombreSchema, "%", tipoTabla);
            salida = new ArrayList<Table>();
            while(rs.next()){
                /*
                 * Column 1 table_cat
                 * Column 2 table_schem
                 * Column 3 table_name
                 * Column 4 table_type
                 */
                Table t = new Table(rs.getString(3));
                
                t.setAttributes(getColumsForTable(rs.getString(3)));
                List<String> pk = getPrimaryKeysForTable(rs.getString(3));
                t.setPrimaryKeys(pk);
                salida.add(t);
            }
            
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error obteniendo tablas para el schema: {0}", nombreSchema);
            LOGGER.severe(ex.getMessage());
        }
        
        return salida;
    }
    
    public List<Attribute> getColumsForTable(String nombreTabla){
        cm = PostgresConnectionManager.getInstance();
        Connection con = cm.obtenerConexion();
        List<Attribute> salida = null;
        try {
            DatabaseMetaData metadata = con.getMetaData();
            ResultSet rs = metadata.getColumns(null, null, nombreTabla, "%");
            salida = new ArrayList<Attribute>();
            while(rs.next()){
                /*
                 * Column 1 TABLE_CAT
                 * Column 2 TABLE_SCHEM
                 * Column 3 TABLE_NAME
                 * Column 4 COLUMN_NAME
                 * Column 5 DATA_TYPE
                 * Column 6 TYPE_NAME
                 * Column 7 COLUMN_SIZE
                 * Column 8 BUFFER_LENGTH
                 * Column 9 DECIMAL_DIGITS
                 * Column 10 NUM_PREC_RADIX
                 * Column 11 NULLABLE
                 * Column 12 REMARKS
                 * Column 13 COLUMN_DEF
                 * Column 14 SQL_DATA_TYPE
                 * Column 15 SQL_DATETIME_SUB
                 * Column 16 CHAR_OCTET_LENGTH
                 * Column 17 ORDINAL_POSITION
                 * Column 18 IS_NULLABLE
                 * Column 19 SCOPE_CATLOG
                 * Column 20 SCOPE_SCHEMA
                 * Column 21 SCOPE_TABLE
                 * Column 22 SOURCE_DATA_TYPE
                 */
                String nombre = rs.getString(4);
                Integer codigoTipo  = Integer.parseInt(rs.getString(5));
                Boolean nullable = Integer.parseInt(rs.getString(11)) == 1 ? true : false;
                
                
                
                Attribute attr = new Attribute();
                attr.setNombre(nombre);
                attr.setTipo(Type.getTypeForCode(codigoTipo));
                attr.setNullable(nullable);
                salida.add(attr);
            }
            
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error obteniendo atributos para la tabla: {0}", nombreTabla);
            LOGGER.severe(ex.getMessage());
        }
        
        return salida;
    }

    private List<String> getPrimaryKeysForTable(String nombreTabla) {
        cm = PostgresConnectionManager.getInstance();
        Connection con = cm.obtenerConexion();
        List<String> result = null;
        try {
            DatabaseMetaData metadata = con.getMetaData();
            ResultSet pKeys = metadata.getPrimaryKeys(null, null, nombreTabla);
            result = new LinkedList<String>();
            while(pKeys.next()) {
                System.out.println(pKeys.getString("COLUMN_NAME"));
                result.add(pKeys.getString("COLUMN_NAME"));
            }
        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
        }
        return result;
    }
}
