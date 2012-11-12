/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.versionado;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author favio.ortelli/guillermo.nasi
 */
public class Cambio implements Serializable {

    private String tabla;
    private TipoCambio tipoCambio;
    private Map<String, String> paramCambios;

    public Cambio(TipoCambio tipoCambio, Map<String, String> parametros) {
        this.tipoCambio = tipoCambio;
        this.paramCambios = parametros;
    }

    /**
     * @return the tabla
     */
    public String getTabla() {
        return tabla;
    }

    /**
     * @param tabla the tabla to set
     */
    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    /**
     * @return the tipoCambio
     */
    public TipoCambio getTipoCambio() {
        return tipoCambio;
    }

    /**
     * @param tipoCambio the tipoCambio to set
     */
    public void setTipoCambio(TipoCambio tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    /**
     * @return the paramCambios
     */
    public Map<String, String> getParamCambios() {
        return paramCambios;
    }

    /**
     * @param paramCambios the paramCambios to set
     */
    public void setParamCambios(Map<String, String> paramCambios) {
        this.paramCambios = paramCambios;
    }
}
