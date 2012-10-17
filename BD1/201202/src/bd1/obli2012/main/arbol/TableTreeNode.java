/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.main.arbol;

import bd1.obli2012.framework.Database;
import bd1.obli2012.framework.Table;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author guillermo
 */
public class TableTreeNode extends DefaultMutableTreeNode {

    private String dbName;
    private String tableName;

    /**
     *
     * @param db
     * @param tableName
     */
    public TableTreeNode(Database db, Table table) {
        this.dbName = db.getDbName();
        this.tableName = table.toString();
        this.setUserObject(table);
    }
    /**
     * @return the schema
     */
    public String getDatabase() {
        return dbName;
    }

    /**
     * @param databaseName the schema to set
     */
    public void setDatabase(String databaseName) {
        this.dbName = databaseName;
    }

    /**
     * @return the tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @param tableName the tableName to set
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
