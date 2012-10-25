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
public class VersionBDD  implements  Serializable{
    private String version;
    private Map<Integer,Cambio> cambios;
}
