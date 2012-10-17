/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.framework;

import java.util.List;

/**
 *
 * @author guillermo
 */
public class Schema {
    private String nombre;
    private List<Table> tablas;

    public Schema(String nombre){
        this.nombre = nombre;
    }
            
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTablas(List<Table> tablas) {
        this.tablas = tablas;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Table> getTablas() {
        return tablas;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
    
    
}
