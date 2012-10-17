/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.main.arbol;

import bd1.obli2012.framework.Attribute;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author guillermo
 */
public class AttributeTreeNode extends DefaultMutableTreeNode{

    private String representation;

    public AttributeTreeNode(Attribute a) {
        this.setUserObject(a);
        this.representation = a.getNombre() + ":" + a.getTipo();
        
    }

    @Override
    public String toString() {
        return this.representation;
    }
    
}
