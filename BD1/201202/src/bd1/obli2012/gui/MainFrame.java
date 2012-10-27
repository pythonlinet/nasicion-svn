/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.gui;

import bd1.obli2012.gui.arbol.TableTreeNode;
import bd1.obli2012.framework.definicion.BaseDeDatos;
import bd1.obli2012.framework.DatabaseManager;
import bd1.obli2012.framework.definicion.Tabla;
import bd1.obli2012.gui.arbol.DBTreeCellRenderer;
import bd1.obli2012.gui.arbol.DBTreeNode;
import bd1.obli2012.gui.backend.Contexto;
import bd1.obli2012.gui.popup.DBPopupMenu;
import bd1.obli2012.gui.popup.TablaPopupMenu;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author guillermo
 */
public class MainFrame extends javax.swing.JFrame implements TreeSelectionListener {

    
    private JTree arbolBD;
    private JPanel panelTabla;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        //Colocamos el nuevo tipo de layout que queremos que tenga nuestro JFrame
        //this.setLayout(new FlowLayout());
        this.setLayout(new BorderLayout());

        this.pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        /* inicializamos el arbol */
        arbolBD = cargarArbol();
        treePane = new JScrollPane(arbolBD);
        flowPanel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1300, 600));

        treePane.setViewportBorder(javax.swing.BorderFactory.createEtchedBorder());
        treePane.setMaximumSize(new java.awt.Dimension(250, 600));
        treePane.setMinimumSize(new java.awt.Dimension(250, 0));
        treePane.setPreferredSize(new java.awt.Dimension(260, 0));

        flowPanel.setPreferredSize(new java.awt.Dimension(800, 440));
        flowPanel.setLayout(new java.awt.BorderLayout());

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(treePane, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(flowPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(treePane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(flowPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            /*
             for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
             if ("Nimbus".equals(info.getName())) {
             javax.swing.UIManager.setLookAndFeel(info.getClassName());
             break;
             }
             }*/
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel flowPanel;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane treePane;
    // End of variables declaration//GEN-END:variables

    public JTree cargarArbol() {

        List<BaseDeDatos> dbs = DatabaseManager.getInstance().getDataBases();
        DefaultMutableTreeNode dbRoot = new DefaultMutableTreeNode("Bases de datos");
        final JTree dbTree = new JTree(dbRoot);
        for (BaseDeDatos db : dbs) {
            DBTreeNode dbNode = new DBTreeNode(db, dbTree);
            //Este if es por un extraño bug que no piendo perder tiempo en investigar
            if (db.getTables() != null) {
                for (Tabla t : db.getTables()) {
                    TableTreeNode tableNode = new TableTreeNode(db, t);
                    //Descomentar para mostrar las columnas en el arbol
                    /*
                     for (Attribute a : t.getAttributes()) {
                     AttributeTreeNode atn = new AttributeTreeNode(a);
                     tableNode.add(atn);
                     }*/
                    dbNode.add(tableNode);
                }
            }
            dbRoot.add(dbNode);
        }


        dbTree.addTreeSelectionListener(this);
        dbTree.setCellRenderer(
                new DBTreeCellRenderer());


        //Este es el codigo para el click derecho del nodo
        MouseListener ml = new MouseAdapter() {
            /*
             public void mousePressed(MouseEvent e) {
             if (SwingUtilities.isRightMouseButton(e)) {
             Point p = e.getPoint();
             TreePath path = dbTree.getPathForLocation(p.x, p.y);
             }
             }//mousePressed
             */
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    Point p = e.getPoint();
                    Component mc = e.getComponent();
                    TreePath path = dbTree.getSelectionPath();
                    if (path != null) {

                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                        Object nodeInfo = node.getUserObject();
                        if (node instanceof DBTreeNode) {
                            BaseDeDatos bd = (BaseDeDatos) nodeInfo;

                            DBPopupMenu popUpMenuDB = new DBPopupMenu(bd, (DBTreeNode) node);
                            popUpMenuDB.show(mc, e.getX(), e.getY());
                            if (popUpMenuDB.getParent().getX() == 0) {
                                popUpMenuDB.show(mc, e.getX(), e.getY() - popUpMenuDB.getHeight());
                            }//if
                        } else if (node instanceof TableTreeNode) {
                            Tabla tabla = (Tabla) nodeInfo;

                            TablaPopupMenu popUpMenuTabla = new TablaPopupMenu(tabla, (TableTreeNode) node);
                            popUpMenuTabla.show(mc, e.getX(), e.getY());
                            if (popUpMenuTabla.getParent().getX() == 0) {
                                popUpMenuTabla.show(mc, e.getX(), e.getY() - popUpMenuTabla.getHeight());
                            }//if
                        }

                    }//if
                }//if
            }//mouseReleased
        };
        dbTree.addMouseListener(ml);

        return dbTree;
    }

    public void valueChanged(TreeSelectionEvent tse) {
        Object selectedNode = arbolBD.getLastSelectedPathComponent();
        if (selectedNode instanceof TableTreeNode) {
            TableTreeNode tn = (TableTreeNode) selectedNode;

            if (panelTabla != null) {
                this.remove(panelTabla);
            }
            panelTabla = new PanelTabla(tn.getDatabase(), tn.getTable().getNombre(), this);

            //this.add(panelTabla, BorderLayout.WEST);
            //this.pack();
            this.flowPanel.removeAll();;
            this.flowPanel.add(panelTabla);
            this.flowPanel.revalidate();;

            Contexto.getInstance().setTbSeleccionada(tn.getTable().getNombre());
        } else if (selectedNode instanceof DBTreeNode) {
            DBTreeNode nodo = (DBTreeNode) selectedNode;

            //Contexto.getInstance().selectBaseDeDatos(nodo.getDbName());
        }


    }

    

   

    public JScrollPane getTreePane() {
        return this.treePane;
    }
}
