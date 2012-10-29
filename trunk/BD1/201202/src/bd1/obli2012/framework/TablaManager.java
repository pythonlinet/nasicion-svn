/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.framework;

import bd1.obli2012.framework.definicion.Tabla;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gnasi
 */
public class TablaManager {

    private static final String GET_PRIMARY_KEY_CONSTRAINT_NAME = "select constraint_name from information_schema.table_constraints where constraint_type = 'PRIMARY KEY' AND table_name='%s'";
    private static final Logger LOGGER = Logger.getLogger(TablaManager.class.getName());

    /**
     * Renombra la tabla especificada
     *
     * @param dbName
     * @param tbName
     * @param newName
     * @return
     */
    public ExecutionResult renameTable(String dbName, String tbName, String newName) {
        String sql = "ALTER TABLE %s RENAME TO %s";
        sql = String.format(sql, tbName, newName);
        return DatabaseManager.getInstance().executeQueryInDB(dbName, sql);
    }

    public ExecutionResult copyTableStructure(String dbName, String tbName) {
        String sql = "CREATE TABLE %s_new (LIKE %s);";
        sql = String.format(sql, tbName, tbName);
        return DatabaseManager.getInstance().executeQueryInDB(dbName, sql);
    }

    public ExecutionResult backupTable(String dbName, String tbName) {
        String sql = "CREATE TABLE %s_bkp AS SELECT * FROM %s;";
        sql = String.format(sql, tbName, tbName);
        return DatabaseManager.getInstance().executeQueryInDB(dbName, sql);
    }

    public ExecutionResult truncateTable(String dbName, String tbName) {
        String sql = "TRUNCATE TABLE %s;";
        sql = String.format(sql, tbName);
        return DatabaseManager.getInstance().executeQueryInDB(dbName, sql);
    }

    public ExecutionResult createTable(String dbName, String tbName) {
        String sql = "CREATE TABLE %s();";
        sql = String.format(sql, tbName);
        return DatabaseManager.getInstance().executeQueryInDB(dbName, sql);
    }

    public ExecutionResult dropTable(String dbName, String tbName) {
        String sql = "DROP TABLE %s;";
        sql = String.format(sql, tbName);
        return DatabaseManager.getInstance().executeQueryInDB(dbName, sql);
    }

    public String getPKConstraintName(String dbName, String tbName) {
        String sql = String.format(GET_PRIMARY_KEY_CONSTRAINT_NAME, tbName);

        ResultSet rs = DatabaseManager.getInstance().executeQueryWithResult(dbName, sql);
        String salida = "";
        try {
            while (rs.next()) {
                salida = rs.getString("constraint_name");
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return salida;
    }

    public ExecutionResult setPrimaryKey(Tabla tabla, List<String> pks) {
        String sqlDropConstraint = "ALTER TABLE %s DROP CONSTRAINT %s;";
        String sqlAddConstraint = "ALTER TABLE %s ADD CONSTRAINT %s PRIMARY KEY (%s);";
        String pkConstName = "pk_" + tabla.getNombre();
        
        //Borramos la pK actual
        String prevPkConstNameOld = getPKConstraintName(tabla.getDatabase(), tabla.getNombre());
        DatabaseManager.getInstance().executeQueryInDB(tabla.getDatabase(),
                String.format(sqlDropConstraint, tabla.getNombre(),
                prevPkConstNameOld));



        StringBuilder pkString = new StringBuilder();
        for (String pk : pks) {
            pkString.append(pk).append(",");
        }
        pkString.replace(pkString.length() - 1, pkString.length(),"");
        sqlAddConstraint = String.format(sqlAddConstraint,
                tabla.getNombre(), pkConstName, pkString.toString());
        
        return DatabaseManager.getInstance().executeQueryInDB(tabla.getDatabase(), sqlAddConstraint);
    }
    
    public ExecutionResult dropConstraint(Tabla tabla, String constraint){
        String sqlDropConstraint = "ALTER TABLE %s DROP CONSTRAINT %s;";
        sqlDropConstraint = String.format(sqlDropConstraint, tabla.getNombre(), constraint);
        return DatabaseManager.getInstance().executeQueryInDB(tabla.getDatabase(), sqlDropConstraint);
    }

    public ExecutionResult addFKConstraint(Tabla tabla,String consName, String columna, String tablaReferencia, String columnaReferencia) {
        String sql = "ALTER TABLE %s ADD CONSTRAINT %s FOREIGN KEY (%s) REFERENCES %s (%s);";
        
        sql = String.format(sql, tabla.getNombre(), consName, columna, tablaReferencia, columnaReferencia);
        
        return DatabaseManager.getInstance().executeQueryInDB(tabla.getDatabase(), sql);
    }
}
