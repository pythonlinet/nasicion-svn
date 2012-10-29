/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.versionado;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author gnasi
 */
public class VersionBDD implements Serializable {

    private Integer version;
    private List<Cambio> cambios;

    /**
     * @return the version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * @return the cambios
     */
    public List<Cambio> getCambios() {
        return cambios;
    }

    /**
     * @param cambios the cambios to set
     */
    public void setCambios(List<Cambio> cambios) {
        this.cambios = cambios;
    }
}
