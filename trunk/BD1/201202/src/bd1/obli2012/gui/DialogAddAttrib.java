/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.gui;

import bd1.obli2012.framework.ColumnManager;
import bd1.obli2012.framework.DatabaseManager;
import bd1.obli2012.framework.ExecutionResult;
import bd1.obli2012.framework.definicion.TipoDato;
import bd1.obli2012.gui.backend.Contexto;
import bd1.obli2012.versionado.Cambio;
import bd1.obli2012.versionado.TipoCambio;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author favio.ortelli/guillermo.nasi
 */
public class DialogAddAttrib extends javax.swing.JDialog {

    private String dbName;
    private String tbName;
    private PanelTabla parent;

    /**
     * Creates new form DialogAddAttrib
     */
    public DialogAddAttrib(java.awt.Frame parent, boolean modal, String dbName, String tbName, PanelTabla parentPanel) {
        super(parent, modal);
        initComponents();
        this.dbName = dbName;
        this.tbName = tbName;
        this.parent = parentPanel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbTipoDato = new javax.swing.JComboBox();
        chkNotNull = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        txtLargo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDefault = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar Tabla...");

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre*");

        jLabel2.setText("Tipo de Dato*");

        cmbTipoDato.setModel(new javax.swing.DefaultComboBoxModel(bd1.obli2012.framework.definicion.TipoDato.values()));
        cmbTipoDato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoDatoActionPerformed(evt);
            }
        });

        chkNotNull.setText("Not Null");
        chkNotNull.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkNotNullActionPerformed(evt);
            }
        });

        jLabel3.setText("Largo");

        txtLargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLargoActionPerformed(evt);
            }
        });

        jLabel4.setText("Valor por defecto");

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(chkNotNull)
                            .addComponent(jLabel4))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbTipoDato, javax.swing.GroupLayout.Alignment.TRAILING, 0, 268, Short.MAX_VALUE)
                            .addComponent(txtLargo)
                            .addComponent(txtNombre)
                            .addComponent(txtDefault)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAceptar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTipoDato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDefault, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkNotNull)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void cmbTipoDatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoDatoActionPerformed
        bd1.obli2012.framework.definicion.TipoDato tipo = (bd1.obli2012.framework.definicion.TipoDato) cmbTipoDato.getSelectedItem();
        txtLargo.setEnabled(tipo.hasLenght());
    }//GEN-LAST:event_cmbTipoDatoActionPerformed

    private void chkNotNullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkNotNullActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkNotNullActionPerformed

    private void txtLargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLargoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLargoActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed


        String nombre = txtNombre.getText().trim();
        String type = ((bd1.obli2012.framework.definicion.TipoDato) cmbTipoDato.getSelectedItem()).name();
        String largo = txtLargo.getText().trim();
        boolean notNull = chkNotNull.isSelected();
        String defaultValue = txtDefault.getText().trim();

        //boolean exitCode = DatabaseManager.getInstance().executeQueryInDB(dbName, query);
        ColumnManager colMan = new ColumnManager();
        ExecutionResult er = colMan.addColumn(dbName, tbName, nombre, type, largo, notNull, defaultValue);
        if (er.success) {
            //parent.actualizarDatos();

            Map<String, String> parametros = new HashMap<String, String>();
            parametros.put("NOMBRE_TABLA", tbName);
            parametros.put("NOMBRE_COLUMNA", nombre);

            Cambio cambio = new Cambio(TipoCambio.COLUMNA_CREAR, parametros);
            Contexto.getInstance().guardarCambio(cambio);
            MainFrame.getInstance().updateTree();
            MainFrame.getInstance().getPanelTabla().actualizarDatos();
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, er.errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JCheckBox chkNotNull;
    private javax.swing.JComboBox cmbTipoDato;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtDefault;
    private javax.swing.JTextField txtLargo;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}