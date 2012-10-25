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
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author guillermo
 */
public class MainFrame extends javax.swing.JFrame implements TreeSelectionListener {

    private JTree arbolBD;
    private JPanel tablePanel;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        //Colocamos el nuevo tipo de layout que queremos que tenga nuestro JFrame
        this.setLayout(new FlowLayout());
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
        treePanel = new JScrollPane(arbolBD);
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        treePanel.setMinimumSize(new java.awt.Dimension(120, 90));

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
                .addComponent(treePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(713, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(treePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane treePanel;
    // End of variables declaration//GEN-END:variables

    public JTree cargarArbol() {

        List<BaseDeDatos> dbs = DatabaseManager.getInstance().getDataBases();
        DefaultMutableTreeNode dbRoot = new DefaultMutableTreeNode("Bases de datos");
        final JTree dbTree = new JTree(dbRoot);
        for (BaseDeDatos db : dbs) {
            DBTreeNode dbNode = new DBTreeNode(db,dbTree);
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
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    Point p = e.getPoint();
                    TreePath path = dbTree.getPathForLocation(p.x, p.y);
                }
            }//mousePressed

            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    Point p = e.getPoint();
                    Component mc = e.getComponent();
                    TreePath path = dbTree.getSelectionPath();
                    if (path != null) {
                        
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                        if(node instanceof DBTreeNode) {
                            Object nodeInfo = node.getUserObject();
                            BaseDeDatos mno = (BaseDeDatos) nodeInfo;

                            DBPopupMenu jPopupMenu1 = new DBPopupMenu(mno, (DBTreeNode)node);
                            jPopupMenu1.show(mc, e.getX(), e.getY());
                            if (jPopupMenu1.getParent().getX() == 0) {
                                jPopupMenu1.show(mc, e.getX(), e.getY() - jPopupMenu1.getHeight());
                            }//if
                        }
                    
                    }//if
                }//if
            }//mouseReleased
        };
        dbTree.addMouseListener(ml);

        return dbTree;
    }

    public void repaintTree() {
        this.treePanel.repaint();
    }

    public void valueChanged(TreeSelectionEvent tse) {
        Object selectedNode = arbolBD.getLastSelectedPathComponent();
        if (selectedNode instanceof TableTreeNode) {
            TableTreeNode tn = (TableTreeNode) selectedNode;

            if (tablePanel != null) {
                this.remove(tablePanel);
            }
            tablePanel = new PanelTabla(tn.getDatabase(), tn.getTable().getNombre(), this);
            this.add(tablePanel, BorderLayout.EAST);
            this.pack();
            Contexto.getInstance().setTbSeleccionada(tn.getTable().getNombre());
        } else if (selectedNode instanceof DBTreeNode) {
            DBTreeNode nodo = (DBTreeNode) selectedNode;

            //Contexto.getInstance().selectBaseDeDatos(nodo.getDbName());








        }


    }


    class DBPopupMenu extends JPopupMenu {
        private BaseDeDatos mno;

        public DBPopupMenu(BaseDeDatos object, final DBTreeNode node) {
            mno = object;

            JMenuItem menuItem = new JMenuItem("Crear tabla");
            menuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bd1/obli2012/icons/table_add.png")));
            add(menuItem);
            menuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    DialogAddTabla addTabla = new DialogAddTabla(null, true, mno.getDbName(), node);
                    addTabla.setVisible(true);
                }
            });

        }

    }
}
