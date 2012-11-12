/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.gui.arbol;

import bd1.obli2012.framework.definicion.Columna;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author favio.ortelli/guillermo.nasi
 */
public class AttributeTreeNode extends DefaultMutableTreeNode{

    private String representation;

    public AttributeTreeNode(Columna a) {
        this.setUserObject(a);
        this.representation = a.getNombre() + ":" + a.getTipo();
        
    }

    @Override
    public String toString() {
        return this.representation;
    }
    
}
