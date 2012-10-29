/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.framework.definicion;

/**
 *
 * @author guillermo
 */
public class ForeignKey {
    private String nombreColumna;
    private String fkConstraintName;
    private String referenciaTabla;
    private String referenciaColumna;

    public ForeignKey(String nombreColumna, String fkConstraintName, String referenciaTabla, String referenciaColumna) {
        this.nombreColumna = nombreColumna;
        this.fkConstraintName = fkConstraintName;
        this.referenciaTabla = referenciaTabla;
        this.referenciaColumna = referenciaColumna;
    }

    
    
    /**
     * @return the nombreColumna
     */
    public String getNombreColumna() {
        return nombreColumna;
    }

    /**
     * @param nombreColumna the nombreColumna to set
     */
    public void setNombreColumna(String nombreColumna) {
        this.nombreColumna = nombreColumna;
    }

    /**
     * @return the fkConstraintName
     */
    public String getFkConstraintName() {
        return fkConstraintName;
    }

    /**
     * @param fkConstraintName the fkConstraintName to set
     */
    public void setFkConstraintName(String fkConstraintName) {
        this.fkConstraintName = fkConstraintName;
    }

    /**
     * @return the referenciaTabla
     */
    public String getReferenciaTabla() {
        return referenciaTabla;
    }

    /**
     * @param referenciaTabla the referenciaTabla to set
     */
    public void setReferenciaTabla(String referenciaTabla) {
        this.referenciaTabla = referenciaTabla;
    }

    /**
     * @return the referenciaColumna
     */
    public String getReferenciaColumna() {
        return referenciaColumna;
    }

    /**
     * @param referenciaColumna the referenciaColumna to set
     */
    public void setReferenciaColumna(String referenciaColumna) {
        this.referenciaColumna = referenciaColumna;
    }
    
    
}
