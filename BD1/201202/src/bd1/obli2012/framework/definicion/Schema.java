/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.framework.definicion;

import java.util.List;

/**
 *
 * @author guillermo
 */
public class Schema {
    private String nombre;
    private List<Tabla> tablas;

    public Schema(String nombre){
        this.nombre = nombre;
    }
            
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTablas(List<Tabla> tablas) {
        this.tablas = tablas;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Tabla> getTablas() {
        return tablas;
    }

    @Override
    public String toString() {
        return this.getNombre();
    }
}
