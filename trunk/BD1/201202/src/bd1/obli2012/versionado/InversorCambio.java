/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.versionado;

import java.util.Map;

/**
 *
 * @author favio.ortelli/guillermo.nasi
 */
public class InversorCambio {

    public static String obtenerInverso(TipoCambio tcambio, Map<String, String> params) {
        String salida = null;
        switch (tcambio) {
            case COLUMNA_CREAR:
                salida = "ALTER TABLE %s DROP COLUMN %s;";
                salida = String.format(salida, params.values().toArray());
                break;
            case COLUMNA_BORRAR:
                salida = "ALTER TABLE %s ADD %s %s %s;";
                salida = String.format(salida, params.values().toArray());
                break;
            case TABLA_RENOMBRAR:
                salida = "ALTER TABLE %s RENAME TO %s;";
                salida = String.format(salida, params.values().toArray());
                break;
            case TABLA_CREAR:
                salida = "DROP TABLE %s;";
                salida = String.format(salida, params.values().toArray());
                break;
            case COLUMNA_CAMBIAR_NOMBRE:
                salida = "ALTER TABLE %s RENAME COLUMN %s TO %s;";
                salida = String.format(salida, params.get("NOMBRE_TABLA"),params.get("NOMBRE_NUEVO"),params.get("NOMBRE_VIEJO"));
                break;
            case COLUMNA_MODIFICAR:
                salida = "ALTER TABLE %s ALTER COLUMN %s TYPE %s;";
                salida = String.format(salida, 
                        params.get("NOMBRE_TABLA"),
                        params.get("NOMBRE_COLUMNA"),params.get("TIPO"));
                
                Boolean notNull = Boolean.parseBoolean(params.get("NOT_NULL"));
                if(notNull){
                    salida += "ALTER TABLE %s ALTER COLUMN %s SET NOT NULL;";
                    
                } else {
                    salida += "ALTER TABLE %s ALTER COLUMN %s DROP NOT NULL;";
                }
                salida = String.format(salida, params.get("NOMBRE_TABLA"),params.get("NOMBRE_COLUMNA"));
                salida += "ALTER TABLE %s ALTER COLUMN %s SET DEFAULT '%s';";
                salida = String.format(salida, params.get("NOMBRE_TABLA"),
                        params.get("NOMBRE_COLUMNA"),params.get("DEFAULT_VALUE"));

                break;
                
        }

        return salida;
    }
}
