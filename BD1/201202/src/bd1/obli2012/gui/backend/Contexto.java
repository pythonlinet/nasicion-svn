/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.gui.backend;

import bd1.obli2012.versionado.BDDVersionado;
import bd1.obli2012.versionado.helper.VersionadoHelper;

/**
 *
 * @author gnasi
 */
public class Contexto {

    private static Contexto instance;
    private boolean tieneCambios;
    private boolean cambiosSalvados;
    private BDDVersionado baseDeDatosActiva;
    private String tbSeleccionada;

    private Contexto() {
    }

    public static Contexto getInstance() {
        if (instance == null) {
            instance = new Contexto();
        }
        return instance;
    }
    
    public void selectBaseDeDatos(String nombreBD) {
        if(baseDeDatosActiva == null || !nombreBD.equals(baseDeDatosActiva.getNombreBDD())){
            //Guardar cambios como nueva version
            BDDVersionado bddv = VersionadoHelper.obtenerVersiones(nombreBD);
            if(bddv == null) {
                baseDeDatosActiva = new BDDVersionado(nombreBD);
            }
        }
    }
    
    public boolean isBDDActiva(String nombreBDD) {
        return baseDeDatosActiva.getNombreBDD().equals(nombreBDD);
    }

    /**
     * @return the tbSeleccionada
     */
    public String getTbSeleccionada() {
        return tbSeleccionada;
    }

    /**
     * @param tbSeleccionada the tbSeleccionada to set
     */
    public void setTbSeleccionada(String tbSeleccionada) {
        this.tbSeleccionada = tbSeleccionada;
    }
    
    
    
}
