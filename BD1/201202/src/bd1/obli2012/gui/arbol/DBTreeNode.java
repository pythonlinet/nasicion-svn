/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.gui.arbol;

import bd1.obli2012.framework.definicion.BaseDeDatos;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author gnasi
 */
public class DBTreeNode extends DefaultMutableTreeNode{
    private String dbName;
    public DBTreeNode(BaseDeDatos db) {
        setUserObject(db);
        this.dbName = db.getDbName();
               
    }

    @Override
    public String toString() {
        return this.dbName;
    }
    
}
