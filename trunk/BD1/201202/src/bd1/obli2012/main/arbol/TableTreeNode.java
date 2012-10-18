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
    private Table table;

    /**
     *
     * @param db
     * @param tableName
     */
    public TableTreeNode(Database db, Table table) {
        this.dbName = db.getDbName();
        this.table = table;
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
     * @return the table
     */
    public Table getTable() {
        return table;
    }

    /**
     * @param table the table to set
     */
    public void setTable(Table table) {
        this.table = table;
    }
}
