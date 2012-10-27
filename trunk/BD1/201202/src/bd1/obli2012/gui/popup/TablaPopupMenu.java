/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.gui.popup;

import bd1.obli2012.framework.ExecutionResult;
import bd1.obli2012.framework.TablaManager;
import bd1.obli2012.framework.definicion.Tabla;
import bd1.obli2012.gui.DialogRenombrarTabla;
import bd1.obli2012.gui.arbol.DBTreeNode;
import bd1.obli2012.gui.arbol.TableTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

/**
 *
 * @author guillermo
 */
public class TablaPopupMenu extends JPopupMenu {

    public final String BORRAR_TABLA =
            "Se está por borrar la tabla \"%s\". \nToda la información en la tabla se perdera.";
    private Tabla tabla;

    public TablaPopupMenu(Tabla object, final TableTreeNode node) {
        this.tabla = object;

        JMenuItem menuItemBorrarTabla = new JMenuItem("Borrar tabla");
        JMenuItem menuItemRenombrarTabla = new JMenuItem("Renombrar tabla");

        menuItemBorrarTabla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bd1/obli2012/icons/table_delete.png")));
        menuItemRenombrarTabla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bd1/obli2012/icons/table_row_alt.png")));

        add(menuItemBorrarTabla);
        add(menuItemRenombrarTabla);

        menuItemBorrarTabla.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Integer salida;
                salida = JOptionPane.showConfirmDialog(null,
                        String.format(BORRAR_TABLA, tabla.getNombre()),
                        "Drop Tabla",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                if (salida == 0) {
                    TablaManager tm = new TablaManager();
                    ExecutionResult er = tm.dropTable(tabla.getDatabase(), tabla.getNombre());
                    if (er.success) {
                        DBTreeNode padre = (DBTreeNode) node.getParent();
                        padre.reconstruir(true);
                    } else {
                        JOptionPane.showMessageDialog(null, er.errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });




        menuItemRenombrarTabla.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DialogRenombrarTabla(null,
                        true,
                        tabla.getDatabase(),
                        tabla.getNombre(),
                        (DBTreeNode) node.getParent()).setVisible(true);
            }
        });
    }
}