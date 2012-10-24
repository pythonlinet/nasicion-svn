/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.gui.arbol;

import bd1.obli2012.framework.definicion.BaseDeDatos;
import bd1.obli2012.framework.definicion.Tabla;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author guillermo
 */
public class TableTreeNode extends DefaultMutableTreeNode {

    private String dbName;
    private Tabla table;

    /**
     *
     * @param db
     * @param tableName
     */
    public TableTreeNode(BaseDeDatos db, Tabla table) {
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
    public Tabla getTable() {
        return table;
    }

    /**
     * @param table the table to set
     */
    public void setTable(Tabla table) {
        this.table = table;
    }
}
