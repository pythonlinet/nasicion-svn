/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.framework;

import bd1.obli2012.framework.definicion.Columna;
import bd1.obli2012.framework.definicion.Tabla;

/**
 *
 * @author guillermo
 */
public class QueryBuilder {

    public static String obtenerInformacionTabla(Tabla tabla) {
        StringBuilder sb = new StringBuilder("SELECT ");
        for(Columna c : tabla.getAttributes()) {
            sb.append(c.getNombre()).append(",");
        }
        sb.replace(sb.length()-1, sb.length(), "");
        sb.append(" FROM ").append(tabla.getNombre()).append(";");
        
        return sb.toString();
    }
}
