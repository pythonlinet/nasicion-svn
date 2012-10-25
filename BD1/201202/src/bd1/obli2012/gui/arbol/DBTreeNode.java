/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.gui.arbol;

import bd1.obli2012.framework.DatabaseManager;
import bd1.obli2012.framework.definicion.BaseDeDatos;
import bd1.obli2012.framework.definicion.Tabla;
import java.util.List;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author gnasi
 */
public class DBTreeNode extends DefaultMutableTreeNode {

    private BaseDeDatos db;
    private JTree tree;

    public DBTreeNode(BaseDeDatos db, JTree tree) {
        setUserObject(db);
        this.db = db;
        this.tree = tree;

    }

    public void reconstruir(boolean llamadaDeTabla) {
        this.removeAllChildren();
        List<Tabla> tablas = DatabaseManager.getInstance().getTablesForDB(db.getDbName());
        if (tablas != null) {
            for (Tabla t : tablas) {
                TableTreeNode tableNode = new TableTreeNode(db, t);

                add(tableNode);
            }
        }

        TreePath seleccionado = tree.getAnchorSelectionPath();
        DefaultTreeModel model = (DefaultTreeModel) this.tree.getModel();
        model.reload();
        if (llamadaDeTabla) {
            seleccionado = seleccionado.getParentPath();
        }
        tree.expandPath(seleccionado);
    }

    public String getDbName() {
        return this.db.getDbName();
    }

    @Override
    public String toString() {
        return this.db.getDbName();
    }
}
