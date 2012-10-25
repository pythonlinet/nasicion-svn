/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.framework;

/**
 *
 * @author gnasi
 */
public class TablaManager {
    
       
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
        sql = String.format(sql, tbName,tbName);
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
}
