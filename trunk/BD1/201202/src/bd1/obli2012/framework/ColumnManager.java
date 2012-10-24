/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.framework;

/**
 *
 * @author gnasi
 */
public class ColumnManager {
    /**
     * Modifica la definición de una columna en la tabla de la base de datos
     * espeficicadas
     *
     * @param dbName
     * @param tbName
     * @param nombre
     * @param type
     * @param largo
     * @return
     */
    public boolean modifyColumnType(String dbName, String tbName, String nombre, String type, String largo) {
        String query = "ALTER TABLE %s ALTER COLUMN %s TYPE %s;";
        if (largo.trim().length() > 0) {
            type += "(" + largo.trim() + ")";
        }
        query = String.format(query, tbName, nombre, type);
        return DatabaseManager.getInstance().executeQueryInDB(dbName, query);
    }

    /**
     *
     * @param dbName
     * @param tbName
     * @param nombre
     * @param notNull
     * @return
     */
    public boolean modifyColumnNullability(String dbName, String tbName, String nombre, Boolean notNull) {
        String query = "ALTER TABLE %s ALTER COLUMN %s;";
        if (notNull) {
            query = String.format(query, tbName, nombre, "SET NOT NULL");
        } else {
            query = String.format(query, tbName, nombre, "DROP NOT NULL");
        }
        return DatabaseManager.getInstance().executeQueryInDB(dbName, query);
    }

    /**
     * Renombra la columna
     * @param dbName
     * @param tbName
     * @param nombre
     * @param newNombre
     * @return 
     */
    public boolean modifyColumnName(String dbName, String tbName, String nombre, String newNombre) {
        String query = "ALTER TABLE %s RENAME COLUMN %s TO %s;";
        query = String.format(query, tbName, nombre, newNombre);
        return DatabaseManager.getInstance().executeQueryInDB(dbName, query);
    }
    
    /**
     * modifica el default value de la columna
     * @param dbName
     * @param tbName
     * @param defaultValue
     * @return 
     */
    public boolean modifyDefaultValue(String dbName, String tbName, String colName, String defaultValue) {
        String query = "ALTER TABLE %s ALTER COLUMN %s SET default %s;";
        query = String.format(query, tbName, colName, defaultValue);
        return DatabaseManager.getInstance().executeQueryInDB(dbName, query);
    }
}
