/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.main.arbol;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author guillermo
 */
public class DBTreeCellRenderer extends DefaultTreeCellRenderer {

    private static final ImageIcon dbsIcon = new ImageIcon(DBTreeCellRenderer.class.getResource("/bd1/obli2012/icons/databases.png"));
    private static final ImageIcon dbIcon = new ImageIcon(DBTreeCellRenderer.class.getResource("/bd1/obli2012/icons/database.png"));
    private static final ImageIcon tableIcon = new ImageIcon(DBTreeCellRenderer.class.getResource("/bd1/obli2012/icons/table.png"));
    private static final ImageIcon attributeIcon = new ImageIcon(DBTreeCellRenderer.class.getResource("/bd1/obli2012/icons/table_column_alt.png"));

    @Override
    public Component getTreeCellRendererComponent(
            JTree tree,
            Object value,
            boolean sel,
            boolean expanded,
            boolean leaf,
            int row,
            boolean hasFocus) {

        super.getTreeCellRendererComponent(
                tree, value, sel,
                expanded, leaf, row,
                hasFocus);

        if (value instanceof TableTreeNode) {
            setIcon(tableIcon);
        } else if (value instanceof AttributeTreeNode) {
            setIcon(attributeIcon);
        } else if (value instanceof SchemaTreeNode) {
            setIcon(dbIcon);
        } else {
            setIcon(dbsIcon);
        }

        return this;
    }
}
