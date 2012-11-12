/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.framework;

/**
 *
 * @author favio.ortelli/guillermo.nasi
 */
public class QueryCriteria {

    private String columna;
    private String operador;
    private String valor;

    public QueryCriteria(String columna, String operador, String valor) {
        this.columna = columna;
        this.operador = operador;
        this.valor = valor;
    }
    
    /**
     * @return the columna
     */
    public String getColumna() {
        return columna;
    }

    /**
     * @return the operador
     */
    public String getOperador() {
        return operador;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    public String toSQL() {
        return columna + " " + operador + " '" + valor + "'";
    }
    
    
}
