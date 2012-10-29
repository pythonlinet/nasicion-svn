/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.gui;

import bd1.obli2012.Util;
import bd1.obli2012.framework.ExecutionResult;
import bd1.obli2012.framework.TablaManager;
import bd1.obli2012.framework.definicion.Columna;
import bd1.obli2012.framework.definicion.Tabla;
import bd1.obli2012.gui.arbol.DBTreeNode;
import bd1.obli2012.gui.arbol.TableTreeNode;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

/**
 *
 * @author guillermo
 */
public class DialogPKTabla extends javax.swing.JDialog {

    private Tabla tabla;
    private TableTreeNode node;
    

    /**
     * Creates new form DialogPKTabla
     */
    public DialogPKTabla(MainFrame parent, boolean modal, final Tabla tabla, TableTreeNode node) {
        super(parent, modal);
        //initComponents();
        
        this.setTitle("Indique cuales campos deberian ser las PKs para la tabla");
        this.tabla = tabla;
        this.node = node;
        setLayout(new GridLayout(tabla.getAttributes().size() + 1, 1,10,10));
        JButton btnAceptar = new JButton("Aceptar");

        add(btnAceptar);
        for (Columna c : tabla.getAttributes()) {
            JCheckBox chk = new JCheckBox(c.getNombre(), null, tabla.isPrimaryKey(c.getNombre()));
            add(chk);
        }
        pack();




        btnAceptar.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent ae) {
                aceptarButtonActionPeformed(ae);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 389, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aceptarButtonActionPeformed(ActionEvent evt) {
        TablaManager tm = new TablaManager();

        List<JCheckBox> selected = Util.getAllCheckbox(this);
        List<String> pks = new ArrayList<String>();
        for (JCheckBox o : selected) {
            if(o.isSelected()){
                pks.add(o.getText());
            }
        }
        ExecutionResult er = tm.setPrimaryKey(tabla, pks);
        if (er.success) {
            MainFrame.getInstance().getPanelTabla().actualizarDatos();
            DBTreeNode padre = (DBTreeNode) node.getParent();
            padre.reconstruir(true);
            this.dispose();

        } else {
            JOptionPane.showMessageDialog(null, er.errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

