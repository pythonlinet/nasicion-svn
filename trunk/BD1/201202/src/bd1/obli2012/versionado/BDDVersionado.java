/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.versionado;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author gnasi
 */
public class BDDVersionado implements Serializable {

    private String nombreBDD;
    private Integer versionActual;
    private Map<Integer, VersionBDD> versiones;

    public BDDVersionado() {
    }

    public BDDVersionado(String nombreBDD) {
        this.nombreBDD = nombreBDD;
        this.versionActual = 1;
        this.versiones = new HashMap<Integer, VersionBDD>();
    }

    /**
     * @return the nombreBDD
     */
    public String getNombreBDD() {
        return nombreBDD;
    }

    /**
     * @return the versionActual
     */
    public Integer getVersionActual() {
        return versionActual;
    }

    /**
     * @param versionActual the versionActual to set
     */
    public void setVersionActual(Integer versionActual) {
        this.versionActual = versionActual;
    }

    /**
     * @return the versiones
     */
    public Map<Integer, VersionBDD> getVersiones() {
        return versiones;
    }
}
