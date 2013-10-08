/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.gui;

import bd1.obli2012.framework.DatabaseManager;
import bd1.obli2012.framework.TablaManager;
import bd1.obli2012.framework.definicion.ForeignKey;
import bd1.obli2012.framework.definicion.Tabla;
import bd1.obli2012.gui.arbol.DBTreeNode;
import bd1.obli2012.gui.arbol.TableTreeNode;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author favio.ortelli/guillermo.nasi
 */
public class DialogFKTabla extends javax.swing.JDialog {

    private Tabla tabla;
    private final TableTreeNode node;
    private List<Component> cmps;

    /**
     * Creates new form DiagloFKTabla
     */
    public DialogFKTabla(java.awt.Frame parent, boolean modal, Tabla tabla, TableTreeNode node) {
        super(parent, modal);
        //initComponents();
        this.tabla = tabla;
        this.node = node;
        this.cmps = new ArrayList<Component>();

        construirVista();

        pack();

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
        setMinimumSize(new java.awt.Dimension(100, 100));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private void addRow(final ForeignKey fk) {
        JLabel columna = new JLabel("* Columna: " + fk.getNombreColumna());
        this.add(columna);
        JLabel nombrefk = new JLabel("Nombre Constraint: " + fk.getFkConstraintName());
        this.add(nombrefk);
        JLabel tablaReferencia = new JLabel("Tabla Referencia: " + fk.getReferenciaTabla());
        this.add(tablaReferencia);
        JLabel columnaReferencia = new JLabel("Columna Referencia: " + fk.getReferenciaColumna());
        this.add(columnaReferencia);
        JButton botonQuitar = new JButton(new Action() {
            public Object getValue(String string) {
                return null;
            }

            public void putValue(String string, Object o) {
            }

            public void setEnabled(boolean bln) {
            }

            public boolean isEnabled() {
                return true;
            }

            public void addPropertyChangeListener(PropertyChangeListener pl) {
            }

            public void removePropertyChangeListener(PropertyChangeListener pl) {
            }

            public void actionPerformed(ActionEvent ae) {
                TablaManager tm = new TablaManager();
                tm.dropConstraint(tabla, fk.getFkConstraintName());
                MainFrame.getInstance().getPanelTabla().actualizarDatos();
                DBTreeNode padre = (DBTreeNode) node.getParent();
                padre.reconstruir(true);
            }
        });
        this.add(botonQuitar);
    }

    void construirVista() {
        this.tabla = DatabaseManager.getInstance().getTable(tabla.getDatabase(), tabla.getNombre());
        for(Component f : cmps) {
            remove(f);
        }
                
        cmps = new ArrayList<Component>();
        int rows = tabla.getForeignKeys().size() + 1;
        setLayout(new GridLayout(rows, 1, 10, 10));
        for (ForeignKey fk : tabla.getForeignKeys().values()) {
            PanelFKTabla panelfk = new PanelFKTabla(this, tabla, fk);
            this.cmps.add(panelfk);
            this.add(panelfk);
            
        }
        JButton botonAgregar = new JButton("Agregar");
        botonAgregar.setIcon(new ImageIcon(getClass().getResource("/bd1/obli2012/icons/buttons/add22.png")));
        botonAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                agregarFK();

            }
        });
        this.add(botonAgregar);
        this.cmps.add(botonAgregar);
        //revalidate();
        
        pack();
    }

    private void agregarFK() {
        new DiagloAddFK(null, true, this, tabla).setVisible(true);
    }
}