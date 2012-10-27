/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.gui.popup;

import bd1.obli2012.framework.definicion.BaseDeDatos;
import bd1.obli2012.gui.DialogAddTabla;
import bd1.obli2012.gui.arbol.DBTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author guillermo
 */
public class DBPopupMenu extends JPopupMenu {

        private BaseDeDatos baseDeDatos;

        public DBPopupMenu(BaseDeDatos object, final DBTreeNode node) {
            baseDeDatos = object;

            JMenuItem menuItem = new JMenuItem("Crear tabla");
            menuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bd1/obli2012/icons/table_add.png")));
            add(menuItem);
            menuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    DialogAddTabla addTabla = new DialogAddTabla(null, true, baseDeDatos.getDbName(), node);
                    addTabla.setVisible(true);
                }
            });

        }
    }
