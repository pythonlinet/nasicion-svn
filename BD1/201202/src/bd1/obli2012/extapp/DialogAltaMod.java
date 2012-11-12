package bd1.obli2012.extapp;


import bd1.obli2012.framework.DatabaseManager;
import bd1.obli2012.framework.ExecutionResult;
import bd1.obli2012.framework.QueryBuilder;
import bd1.obli2012.framework.definicion.Columna;
import bd1.obli2012.framework.definicion.Tabla;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author favio.ortelli/guillermo.nasi
 */
public class DialogAltaMod extends javax.swing.JDialog {

    private Tabla tabla;

    /**
     * Creates new form NewJDialog
     */
    public DialogAltaMod(java.awt.Frame parent, boolean modal, Tabla tabla, boolean nuevoRegistro, final List<String> valoresPK) {
        super(parent, modal);
        this.tabla = tabla;
        setLayout(new GridLayout(tabla.getAttributes().size() + 1, 2));
        for (Columna c : tabla.getAttributes()) {
            JLabel l = new JLabel(c.getNombre());
            JTextField txt = new JTextField();
            add(l);
            add(txt);

        }
        JButton aceptar = new JButton("Acpetar");
        if (nuevoRegistro) {
            aceptar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    insertarAction(evt);
                }
            });
        } else {
            cargarDatosTupla(valoresPK);
            aceptar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    updateAction(evt, valoresPK);
                }
            });
        }

        add(aceptar);
        pack();
    }

    /**
     * Obtiene los valors del formulario
     * @return array string con los valores del "form" (Dios, extranio web)
     */
    private String[] obtenerValores() {

        List<String> vals = new ArrayList<String>();
        List<JTextField> comps = getAllTxtComponents(this);
        for (Component c : comps) {
            vals.add(((JTextField) c).getText());
        }
        String[] valores = new String[vals.size()];
        for (int i = 0; i < vals.size(); i++) {
            valores[i] = vals.get(i);

        }
        return valores;
    }

    /**
     * Obtiene todos los componentes JTextField del formulario
     * @param c contenedor de los txtfield
     * @return 
     */
    private List<JTextField> getAllTxtComponents(final Container c) {
        Component[] comps = c.getComponents();
        List<JTextField> compList = new ArrayList<JTextField>();
        for (Component comp : comps) {
            if (comp instanceof JTextField) {
                compList.add((JTextField) comp);
            }
            if (comp instanceof Container) {
                compList.addAll(getAllTxtComponents((Container) comp));
            }
        }
        return compList;
    }

    /**
     * Accion para el boton Aceptar cuando hay que insertar
     * @param evt 
     */
    private void insertarAction(ActionEvent evt) {
        String[] valores = obtenerValores();
        String query = QueryBuilder.insertarEnTabla(tabla, valores);
        ejecutarQuery(query);
    }

    private void updateAction(ActionEvent evt, List<String> valoresPK) {
        String[] valores = obtenerValores();
        String query = QueryBuilder.modificarEnTabla(tabla, valores, valoresPK);
        ejecutarQuery(query);
    }

    /**
     * Ejecuta la query provista, si es un exito cierra el modal si falla muestra mensaje de error
     * @param sql 
     */
    private void ejecutarQuery(String query){
        ExecutionResult er = DatabaseManager.getInstance().executeQueryInDB(tabla.getDatabase(), query);
        if (er.success) {
            ((AplicacionExterna) getParent()).actualizarTableModel(tabla);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, er.errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Carga los datos de una tupla en la pantalla en base a las PK y la tabla
     * @param valoresPK valores de pk
     */
    private void cargarDatosTupla(List<String> valoresPK) {
        String query = QueryBuilder.obtenerTuplaTabla(tabla, valoresPK);
        //System.out.println(query);
        ResultSet rs =
                DatabaseManager.getInstance().
                executeQueryWithResult(tabla.getDatabase(), query);
        try {
            while (rs.next()) {
                List<JTextField> fields = getAllTxtComponents(this);
                for (int i = 0; i < fields.size(); i++) {
                    JTextField field = fields.get(i);
                    field.setText(rs.getString(i + 1));

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DialogAltaMod.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        setModal(true);
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 562, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 282, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
