package bd1.obli2012.extapp;


import bd1.obli2012.Util;
import bd1.obli2012.framework.DatabaseManager;
import bd1.obli2012.framework.ExecutionResult;
import bd1.obli2012.framework.QueryBuilder;
import bd1.obli2012.framework.QueryCriteria;
import bd1.obli2012.framework.definicion.Columna;
import bd1.obli2012.framework.definicion.Tabla;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author favio.ortelli/guillermo.nasi
 */
public class AplicacionExterna extends javax.swing.JFrame {

    private List<Tabla> tablas;
    private final String dbName;
    private String tabla;
    private List<QueryCriteria> criteriasActivas;

    /**
     * Creates new form AplicacionExterna
     */
    public AplicacionExterna() {
        initComponents();
        //this.dbName = "autos";
        this.dbName = Util.getProperties().getProperty("db.name");
        tablas = DatabaseManager.getInstance().getTablesForDB(this.dbName);
        this.jList1.setListData(tablas.toArray());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ){@Override
            public boolean isCellEditable (int fila, int columna) {
                return false;
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jButton1.setText("Añadir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnBorrar.setText("Borrar");
        btnBorrar.setToolTipText("");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        jButton2.setText("Filtrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(btnModificar)
                            .addComponent(btnBorrar)
                            .addComponent(jButton2))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setCriteriasActivas(List<QueryCriteria> criteriasActivas) {
        this.criteriasActivas = criteriasActivas;
    }
    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        if (!evt.getValueIsAdjusting()) {
            Tabla t = (Tabla) this.jList1.getSelectedValue();
            this.criteriasActivas = null;
            actualizarTableModel(t);
        }
    }//GEN-LAST:event_jList1ValueChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new DialogAltaMod(this,
                true,
                (Tabla) this.jList1.getSelectedValue(), true, null).
                setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        new DialogAltaMod(this,
                true,
                (Tabla) this.jList1.getSelectedValue(), false, obtenerValoresPK()).
                setVisible(true);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        if (this.jList1.getSelectedIndex() > -1) {
            Integer salida;
            salida = JOptionPane.showConfirmDialog(null,
                    "¿Seguro que desea borrar el registro?",
                    "",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if (salida == 0) {
                Tabla t = (Tabla) this.jList1.getSelectedValue();
                List<String> pks = obtenerValoresPK();
                String query = QueryBuilder.borrarTuplaTabla(t, pks);
                ejecutarQuery(t, query);
                actualizarTableModel(t);
            }
        }
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new DialogoFiltro(this, true, (Tabla) jList1.getSelectedValue()).setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * Obtiene los valores de la PK de la fila seleccionada
     *
     * @return null si no hay fila seleccionada
     */
    private List<String> obtenerValoresPK() {
        Integer fila = jTable1.getSelectedRow();
        if (fila > -1) {
            Tabla tablaSeleccionada = (Tabla) jList1.getSelectedValue();

            List<String> pks = tablaSeleccionada.getPrimaryKeys();
            List<Integer> posicionPks = new ArrayList<Integer>();
            for (String pk : pks) {
                boolean encontrado = false;
                for (int j = 0; j < tablaSeleccionada.getAttributes().size() && !encontrado; j++) {
                    String nc = tablaSeleccionada.getAttributes().get(j).getNombre();
                    if (nc.equals(pk)) {
                        posicionPks.add(j);
                    }

                }
            }
            List<String> valoresPK = new LinkedList<String>();
            for (Integer pos : posicionPks) {
                valoresPK.add(jTable1.getValueAt(fila, pos).toString());
                //System.out.println(jTable1.getValueAt(fila, pos).toString());
            }
            return valoresPK;
        }
        return null;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AplicacionExterna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AplicacionExterna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AplicacionExterna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AplicacionExterna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AplicacionExterna().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    public void actualizarTableModel(Tabla tabla) {
        DefaultTableModel modelo = (DefaultTableModel) this.jTable1.getModel();
        modelo.setColumnIdentifiers(tabla.getNombresColumnas());

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String query = "";
        if(criteriasActivas == null || criteriasActivas.isEmpty()){
            query = QueryBuilder.obtenerInformacionTabla(tabla);
        } else {
            query = QueryBuilder.select(tabla, criteriasActivas);
        }
        
        ResultSet rs = DatabaseManager.getInstance().executeQueryWithResult(dbName, query);
        System.out.println(query);
        try {
            String[] rowData = null;
            while (rs.next()) {
                rowData = new String[rs.getMetaData().getColumnCount()];
                int i = 0;
                for (Columna c : tabla.getAttributes()) {
                    rowData[i] = rs.getString(c.getNombre());
                    i++;
                }
                modelo.addRow(rowData);
            }
            modelo.fireTableDataChanged();
        } catch (SQLException ex) {
            Logger.getLogger(AplicacionExterna.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Ejecuta la query provista, si es un exito cierra el modal si falla muestra mensaje de error
     * @param sql 
     */
    private void ejecutarQuery(Tabla tabla, String query){
        ExecutionResult er = DatabaseManager.getInstance().executeQueryInDB(tabla.getDatabase(), query);
        if (er.success) {
            actualizarTableModel(tabla);
        } else {
            JOptionPane.showMessageDialog(this, er.errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
