/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.framework;

import bd1.obli2012.framework.definicion.Columna;
import bd1.obli2012.framework.definicion.Tabla;
import java.util.List;

/**
 *
 * @author favio.ortelli/guillermo.nasi
 */
public class QueryBuilder {

    /**
     * Genera la query para obtener toda la informacion de una tablar
     * @param tabla
     * @return 
     */
    public static String obtenerInformacionTabla(Tabla tabla) {
        StringBuilder sb = new StringBuilder("SELECT ");
        for(Columna c : tabla.getAttributes()) {
            sb.append(c.getNombre()).append(",");
        }
        sb.replace(sb.length()-1, sb.length(), "");
        sb.append(" FROM ").append(tabla.getNombre()).append(";");
        
        return sb.toString();
    }

    /**
     * Genera la query para instertar en una tabla
     * @param tabla
     * @param valores 
     */
    public static String insertarEnTabla(Tabla tabla, String[] valores) {
        StringBuilder sb = 
                new StringBuilder("INSERT INTO ").
                append(tabla.getNombre()).
                append(" VALUES (");
        for(String val :valores){
            sb.append("'").append(val).append("'").append(",");
        }
        sb.replace(sb.length()-1, sb.length(), "");
        sb.append(");");
        return sb.toString();
    }

    public static String obtenerTuplaTabla(Tabla tabla, List<String> valoresPK) {
        StringBuilder sb = new StringBuilder("SELECT ");
        for(Columna c : tabla.getAttributes()) {
            sb.append(c.getNombre()).append(",");
        }
        sb.replace(sb.length()-1, sb.length(), "");
        sb.append(" FROM ").
                append(tabla.getNombre()).
                append(" WHERE ");
        
        
        for (int i = 0; i < valoresPK.size(); i++) {
            sb.append(tabla.getPrimaryKeys().get(i)).
                    append("='").
                    append(valoresPK.get(i)).
                    append("' AND ");
        }
        sb.replace(sb.length()-4, sb.length(), "");
        sb.append(";");
        return sb.toString();
    }

    public static String modificarEnTabla(Tabla tabla, String[] valores, List<String> valoresPK) {
       StringBuilder sb = new StringBuilder("UPDATE ").
               append(tabla.getNombre()).append(" SET ");
        
        for (int i = 0; i < valores.length; i++) {
            sb.append(tabla.getAttributes().get(i).getNombre())
                    .append("='")
                    .append(valores[i])
                    .append("',");
            
        }
        sb.replace(sb.length()-1, sb.length(), "");
        sb.append(" WHERE ");
        for (int i = 0; i < valoresPK.size(); i++) {
            sb.append(tabla.getPrimaryKeys().get(i)).
                    append("='").
                    append(valoresPK.get(i)).
                    append("' AND ");
        }
        sb.replace(sb.length()-4, sb.length(), "");
        sb.append(";");
        return sb.toString();
    }

    public static String borrarTuplaTabla(Tabla tabla, List<String> valoresPK) {
        StringBuilder sb = new StringBuilder("DELETE ");
        sb.append("FROM ").
                append(tabla.getNombre()).
                append(" WHERE ");
        
        
        for (int i = 0; i < valoresPK.size(); i++) {
            sb.append(tabla.getPrimaryKeys().get(i)).
                    append("='").
                    append(valoresPK.get(i)).
                    append("' AND ");
        }
        sb.replace(sb.length()-4, sb.length(), "");
        sb.append(";");
        return sb.toString();
    }
    
    public static String select(Tabla tabla, List<QueryCriteria> criteria) {
    StringBuilder sb = new StringBuilder("SELECT ");
        for(Columna c : tabla.getAttributes()) {
            sb.append(c.getNombre()).append(",");
        }
        sb.replace(sb.length()-1, sb.length(), "");
        sb.append(" FROM ").
                append(tabla.getNombre()).
                append(" WHERE ");
        
        for (QueryCriteria qc : criteria) {
            sb.append(qc.toSQL()).
                    append(" AND ");
        }
        
        sb.replace(sb.length()-4, sb.length(), "");
        sb.append(";");
        return sb.toString();
    }
}
