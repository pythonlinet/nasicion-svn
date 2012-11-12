/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.framework;

import bd1.obli2012.framework.definicion.Columna;
import bd1.obli2012.framework.definicion.TipoDato;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author favio.ortelli/guillermo.nasi
 */
public class ColumnManager {

    private static final Logger LOGGER = Logger.getLogger(ColumnManager.class
            .getName());

    /**
     * Añade una columna a la tabla especificada
     *
     * @param dbName
     * @param tbName
     * @param nombre
     * @param type
     * @param largo
     * @param notNull
     * @param defaultValue
     * @return
     */
    public ExecutionResult addColumn(String dbName, String tbName, String nombre, String type, String largo, boolean notNull, String defaultValue) {
        String query = "ALTER TABLE %s ADD %s %s %s";
        if (largo.trim().length() > 0) {
            type += "(" + largo.trim() + ")";
        }
        String notNullStr = notNull ? "NOT NULL" : "NULL";
        query = String.format(query, tbName, nombre, type, notNullStr);
        if (defaultValue.trim().length() > 0) {
            query += " default '" + defaultValue.trim() + "'";
        }
        query += ";";
        return DatabaseManager.getInstance().executeQueryInDB(dbName, query);
    }

    public ExecutionResult modificarColumna(String dbName, String tbName, String colName,String nombre, String type, String largo, boolean notNull, String defaultValue){
        Columna columnaOriginal = DatabaseManager.getInstance().getColumnFromTable(dbName, tbName, colName);
        
        List<String> querys = new LinkedList<String>();
        
        //FIXME arreglar el tema del largo Steeeeve!!
        if(!TipoDato.valueOf(type).equals(columnaOriginal.getTipo())) {
            querys.add(modifyTypeQueryBuilder(tbName, columnaOriginal.getNombre(), type, largo));
        }
        
        if(!defaultValue.trim().equals(columnaOriginal.getDefaultValue())){
            querys.add(modifyDefaultValueQueryBuilder(tbName, columnaOriginal.getNombre(), defaultValue.trim()));
        }
        
        if(notNull != columnaOriginal.notNull()) {
            querys.add(modifyNullabilityBuilder(tbName, columnaOriginal.getNombre(), notNull));
        }
        if(!nombre.equals(columnaOriginal.getNombre())){
            querys.add(modifyNameQueryBuilder(tbName, columnaOriginal.getNombre() ,nombre));
        }
        ExecutionResult er = null;
        if(querys.size() > 0) {
            try {
                DatabaseManager.getInstance().executeQuerysWithinTransaction(dbName, querys);
                er = new ExecutionResult(true, "");
            } catch (SQLException ex) {
                er = new ExecutionResult(false, ex.getLocalizedMessage());
            }
        } else {
            er = new ExecutionResult(true, "");
        }
        return er;
    }
    
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
    public String modifyTypeQueryBuilder(String tbName, String nombre, String type, String largo) {
        String query = "ALTER TABLE %s ALTER COLUMN %s TYPE %s;";
        if (largo.trim().length() > 0) {
            type += "(" + largo.trim() + ")";
        }
        query = String.format(query, tbName, nombre, type);
        return query;
        
    }

    /**
     *
     * @param tbName
     * @param nombre
     * @param notNull
     * @return
     */
    public String modifyNullabilityBuilder(String tbName, String nombre, Boolean notNull) {
        String query = "ALTER TABLE %s ALTER COLUMN %s %s;";
        if (notNull) {
            query = String.format(query, tbName, nombre, "SET NOT NULL");
        } else {
            query = String.format(query, tbName, nombre, "DROP NOT NULL");
        }
        return query;
    }

    /**
     * Renombra la columna
     *
     * @param tbName
     * @param nombre
     * @param newNombre
     * @return
     */
    public String modifyNameQueryBuilder(String tbName, String nombre, String newNombre) {
        String query = "ALTER TABLE %s RENAME COLUMN %s TO %s;";
        query = String.format(query, tbName, nombre, newNombre);
        return query;
    }

    /**
     * modifica el default value de la columna
     *
     * @param tbName
     * @param defaultValue
     * @return
     */
    public String modifyDefaultValueQueryBuilder(String tbName, String colName, String defaultValue) {
        String query = "ALTER TABLE %s ALTER COLUMN %s SET default '%s';";
        query = String.format(query, tbName, colName, defaultValue);
        return query;
    }

    /**
     * Borra la columna especificada
     *
     * @param dbName
     * @param tbName
     * @param colName
     * @return
     */
    public ExecutionResult dropColumn(String dbName, String tbName, String colName) {
        String query = "ALTER TABLE %s DROP COLUMN %s";
        query = String.format(query, tbName, colName);
        ExecutionResult salida = DatabaseManager.getInstance().executeQueryInDB(dbName, query);
       
        if(!salida.success) {
            LOGGER.severe("Error ejecutando metodo dropColumn");
        }
       
        return salida;
    }

    
}
