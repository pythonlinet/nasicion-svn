/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.versionado;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author gnasi
 */
public class BaseDeDatos implements Serializable {

    private String name;
    private String versionActual;
    private Map<String, VersionBD> versiones;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the versionActual
     */
    public String getVersionActual() {
        return versionActual;
    }

    /**
     * @param versionActual the versionActual to set
     */
    public void setVersionActual(String versionActual) {
        this.versionActual = versionActual;
    }

    /**
     * @return the versiones
     */
    public Map<String, VersionBD> getVersiones() {
        return versiones;
    }

    /**
     * @param versiones the versiones to set
     */
    public void setVersiones(Map<String, VersionBD> versiones) {
        this.versiones = versiones;
    }
}
