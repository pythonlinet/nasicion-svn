/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.main.arbol;

import bd1.obli2012.framework.Schema;
import bd1.obli2012.framework.Table;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author guillermo
 */
public class TableTreeNode extends DefaultMutableTreeNode {

    private String schema;
    private String tableName;

    /**
     *
     * @param schema
     * @param tableName
     */
    public TableTreeNode(Schema schema, Table table) {
        this.schema = schema.getNombre();
        this.tableName = table.toString();
        this.setUserObject(table);
    }
    /**
     * @return the schema
     */
    public String getSchema() {
        return schema;
    }

    /**
     * @param schema the schema to set
     */
    public void setSchema(String schema) {
        this.schema = schema;
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
