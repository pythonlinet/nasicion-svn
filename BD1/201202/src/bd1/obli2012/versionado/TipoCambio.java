/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.versionado;

import java.io.Serializable;

/**
 *
 * @author gnasi
 */
public enum TipoCambio implements Serializable {
    //TABLAS

    TABLA_CREAR(new String[]{"NOMBRE_TABLA"}),
    TABLA_RENOMBRAR(new String[]{"NOMBRE_TABLA", "NUEVO_NOMBRE"}),
    TABLA_DROP(new String[]{"NOMBRE_TABLA"}),
    TABLA_SET_PK(new String[]{"NOMBRE_TABLA", "NOMBRE_CONSTRAINT"}),
    TABLA_DROP_PK(new String[]{"NOMBRE_TABLA", "NOMBRE_CONSTRIAINT", "COLUMNAS"}),
    TABLA_SET_FK(new String[]{"NOMBRE_TABLA", "NOMBRE_CONSTRAINT"}),
    TABLA_DROP_FK(new String[]{"NOMBRE_TABLA", "NOMBRE_CONSTRIAINT","COLUMNA", "REFERENCE"}),
    TABLA_SET_UNIQUE(new String[]{"NOMBRE_TABLA", "NOMBRE_CONSTRAINT"}),
    TABLA_DROP_UNIQUE(new String[]{"NOMBRE_TABLA", "NOMBRE_CONSTRAINT", "COLUMNAS"}),
    //COLUMNAS
    COLUMNA_CREAR(new String[]{"NOMBRE_TABLA", "NOMBRE_COLUMNA"}),
    COLUMNA_BORRAR(new String[]{"NOMBRE_TABLA", "NOMBRE_COLUMNA"}),
    COLUMNA_CAMBIAR_TIPO(new String[]{"NOMBRE_TABLA", "NOMBRE_COLUMNA", "TIPO_VIEJO", "TIPO_NUEVO"}),
    COLUMNA_CAMBIAR_SET_NOT_NULL(new String[]{"NOMBRE_TABLA", "NOMBRE_COLUMNA"}),
    COLUMNA_CAMBIAR_DROP_NOT_NULL(new String[]{"NOMBRE_TABLA", "NOMBRE_COLUMNA"}),
    COLUMNA_CAMBIAR_DEFAULT_VALUE(new String[]{"NOMBRE_TABLA", "NOMBRE_COLUMNA", "DEFAULT_VALUE_VIEJO", "DEFAULT_VALUE_NUEVO"});

    private String[] params;

    public String[] getParams() {
        return this.params;
    }

    private TipoCambio(String[] params) {
    }
}
