/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.main.arbol;

import bd1.obli2012.framework.Schema;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author guillermo
 */
public class SchemaTreeNode extends DefaultMutableTreeNode{

    public SchemaTreeNode(Schema s) {
        this.userObject = s;
    }
    
}