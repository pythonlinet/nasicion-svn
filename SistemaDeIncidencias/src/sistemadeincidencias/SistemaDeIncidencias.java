/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadeincidencias;

import sistemadeincidencias.gui.MainFrame;
import java.awt.HeadlessException;
import javax.swing.SwingUtilities;

/**
 *
 * @author gnasi
 */
public class SistemaDeIncidencias{

    public SistemaDeIncidencias() throws HeadlessException {
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame ex = new MainFrame();
                ex.setVisible(true);
            }
        });
    }
}
