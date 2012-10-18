package bd1.obli2012.framework;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
    public List<Schema> getDataBaseSchemas(String dbName) {
        cm = PostgresConnectionManager.getInstance();
        Connection con = cm.obtenerConexion();
        List<Schema> salida = null;
        try {
            DatabaseMetaData metadata = con.getMetaData();
            ResultSet rs = metadata.getSchemas(dbName, "%");
            salida = new ArrayList<Schema>();
            while(rs.next()){
                Schema s = new Schema(rs.getString(1));
                s.setTablas(getTablesForDB(s.getNombre()));
                salida.add(s);
            }
            return salida;
        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
        }
        return salida;
    }
    
    public List<Table> getTablesForDB(String nombreDB){
        cm = PostgresConnectionManager.getInstance();
        Connection con = cm.obtenerConexion(nombreDB);
        List<Table> salida = null;
        try {
            DatabaseMetaData metadata = con.getMetaData();
            String[] tipoTabla = {"TABLE"};
            ResultSet rs = metadata.getTables(null, "public", "%", tipoTabla);
            salida = new ArrayList<Table>();
            while(rs.next()){
                /*
                 * Column 1 table_cat
                 * Column 2 table_schem
                 * Column 3 table_name
                 * Column 4 table_type
                 */
                Table t = new Table(rs.getString(3));
                t.setDatabase(nombreDB);
                t.setAttributes(getColumsForTable(t));
                List<String> pk = getPrimaryKeysForTable(t);
                Map<String,String> fk = getForeignKeysForTable(t);
                t.setPrimaryKeys(pk);
                t.setForeignKeys(fk);
                salida.add(t);
            }
            
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error obteniendo tablas para el schema: {0}", nombreDB);
            LOGGER.severe(ex.getMessage());
        }
        
        return salida;
    }
    
    public List<Attribute> getColumsForTable(Table tabla){
        cm = PostgresConnectionManager.getInstance();
        Connection con = cm.obtenerConexion(tabla.getDatabase());
        List<Attribute> salida = null;
        try {
            DatabaseMetaData metadata = con.getMetaData();
            ResultSet rs = metadata.getColumns(tabla.getDatabase(), "public", tabla.getNombre(), "%");
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
                String defaultValue = rs.getString(13);
                
                
                Attribute attr = new Attribute();
                attr.setNombre(nombre);
                attr.setTipo(Type.getTypeForCode(codigoTipo));
                attr.setNullable(nullable);
                attr.setDefaultValue(defaultValue);
                
                salida.add(attr);
            }
            
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error obteniendo atributos para la tabla: {0}", tabla);
            LOGGER.severe(ex.getMessage());
        }
        
        return salida;
    }

    private List<String> getPrimaryKeysForTable(Table tabla) {
        cm = PostgresConnectionManager.getInstance();
        Connection con = cm.obtenerConexion(tabla.getDatabase());
        List<String> result = null;
        try {
            DatabaseMetaData metadata = con.getMetaData();
            ResultSet pKeys = metadata.getPrimaryKeys(null, null, tabla.getNombre());
            result = new LinkedList<String>();
            while(pKeys.next()) {
                result.add(pKeys.getString("COLUMN_NAME"));
            }
        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
        }
        return result;
    }
    
    private Map<String, String> getForeignKeysForTable(Table tabla) {
        cm = PostgresConnectionManager.getInstance();
        Connection con = cm.obtenerConexion(tabla.getDatabase());
        Map<String, String> result = null;
        try {
            DatabaseMetaData metadata = con.getMetaData();
            ResultSet pKeys = metadata.getImportedKeys(null, null, tabla.getNombre());
            result = new HashMap<String, String>();
            
            while(pKeys.next()) {

                /*
                for (int i = 1; i <=  pKeys.getMetaData().getColumnCount(); i++) {
                    System.out.println("Column " + i + " " 
                            + pKeys.getMetaData().getColumnName(i) + " " +
                            pKeys.getString(i));
                    
                }*/
                result.put(pKeys.getString("FKCOLUMN_NAME"),pKeys.getString("pktable_name"));
            }
        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
        }
        return result;
    }

    public List<Database> getDataBases() {
        List<Database> databases = null;
        cm = PostgresConnectionManager.getInstance();
        Connection con = cm.obtenerConexion();
        try {
            ResultSet res = executeQueryWithResult(PostgresConnectionManager.getInstance().listDBsQuery());
            databases = new LinkedList<Database>();
            while(res.next()){
                Database db = new Database();
                db.setDbName(res.getString(1));
                
                db.setTables(getTablesForDB(db.getDbName()));
                
                databases.add(db);
            }
            
        } catch (SQLException ex) {
            LOGGER.severe("Error obteniendo las bases de datos del sistema");
            LOGGER.severe(ex.getMessage());
        }
        
        return databases;
    }

    public Table getTable(String nombreDB, String tbName) {
        cm = PostgresConnectionManager.getInstance();
        Connection con = cm.obtenerConexion(nombreDB);
        Table salida = null;
        try {
            DatabaseMetaData metadata = con.getMetaData();
            String[] tipoTabla = {"TABLE"};
            ResultSet rs = metadata.getTables(null, "public", tbName, tipoTabla);
            
            while(rs.next()){
                /*
                 * Column 1 table_cat
                 * Column 2 table_schem
                 * Column 3 table_name
                 * Column 4 table_type
                 */
                salida = new Table(rs.getString(3));
                salida.setDatabase(nombreDB);
                salida.setAttributes(getColumsForTable(salida));
                List<String> pk = getPrimaryKeysForTable(salida);
                Map<String,String> fk = getForeignKeysForTable(salida);
                salida.setPrimaryKeys(pk);
                salida.setForeignKeys(fk);
            }
            
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error obteniendo tablas para el schema: {0}", nombreDB);
            LOGGER.severe(ex.getMessage());
        }
        
        return salida;
    }
}
