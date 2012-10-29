/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.gui;

import bd1.obli2012.framework.ExecutionResult;
import bd1.obli2012.framework.TablaManager;
import bd1.obli2012.framework.definicion.Tabla;
import bd1.obli2012.gui.arbol.DBTreeNode;
import javax.swing.JOptionPane;

/**
 *
 * @author guillermo
 */
public class DialogRenombrarTabla extends javax.swing.JDialog {

    private String dbName;
    private DBTreeNode node;
    private String nombreOriginal;

    /**
     * Creates new form DialogAddTabla
     */
    public DialogRenombrarTabla(java.awt.Frame parent, boolean modal, String dbName, String nombreTabla, DBTreeNode parentNode) {
        super(parent, modal);
        initComponents();
        this.dbName = dbName;
        this.node = parentNode;
        this.nombreOriginal = nombreTabla;
        this.nombreTabla.setText(nombreTabla);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        nombreTabla = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Nombre");

        nombreTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreTablaActionPerformed(evt);
            }
        });

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(nombreTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nombreTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nombreTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreTablaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreTablaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (this.nombreTabla.getText().trim().length() > 0) {
            String nombreTb = this.nombreTabla.getText().trim();
            TablaManager tm = new TablaManager();
            ExecutionResult er = tm.renameTable(dbName, nombreOriginal, nombreTb);


            if (er.success) {
                //parent.actualizarDatos();
                node.reconstruir(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, er.errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField nombreTabla;
    // End of variables declaration//GEN-END:variables
}