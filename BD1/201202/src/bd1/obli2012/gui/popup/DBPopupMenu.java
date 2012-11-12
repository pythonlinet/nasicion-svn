/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.gui.popup;

import bd1.obli2012.extapp.ExportadorAplicaciones;
import bd1.obli2012.framework.definicion.BaseDeDatos;
import bd1.obli2012.gui.DialogAddTabla;
import bd1.obli2012.gui.arbol.DBTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

/**
 *
 * @author favio.ortelli/guillermo.nasi
 */
public class DBPopupMenu extends JPopupMenu {

    private BaseDeDatos baseDeDatos;

    public DBPopupMenu(BaseDeDatos object, final DBTreeNode node) {
        baseDeDatos = object;
        agregarItemCrearTabla(node);
        agregarItemExportarApp(node);

    }

    private void agregarItemCrearTabla(final DBTreeNode node) {
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

    private void agregarItemExportarApp(final DBTreeNode node) {
        JMenuItem menuItem = new JMenuItem("Crear Aplciacion DB");
        menuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bd1/obli2012/icons/wizard.png")));
        add(menuItem);
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //DialogAddTabla addTabla = new DialogAddTabla(null, true, baseDeDatos.getDbName(), node);
                    //addTabla.setVisible(true);
                   
                    String path = JOptionPane.showInputDialog(null, "Indique donde se debera exportar la aplicacion", "Exportar app a...", JOptionPane.INFORMATION_MESSAGE);
                    if(path != null) {
                        ExportadorAplicaciones.exportarApp(path, baseDeDatos);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
