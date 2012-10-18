/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.framework;

import java.util.List;
import java.util.Map;

/**
 *
 * @author 
 */
public class Table {
    private String nombre;
    private List<Attribute> attributes;
    private List<String> primaryKeys;
    private Map<String, String> foreignKeys;
    private String database;

    public Table(String nombre){
        this.nombre = nombre;
    }
    
    /**
     * Dice si una columna es primaryKey
     * @param nombreColumna
     * @return 
     */
    public boolean isPrimaryKey(String nombreColumna) {
        return this.primaryKeys.contains(nombreColumna);
    }
    
    /**
     * Dice si una columna es foreign key
     * @param nombreColumna
     * @return 
     */
    public boolean isForeignKey(String nombreColumna) {
        return this.foreignKeys.containsKey(nombreColumna);
    }
    
    /**
     * Dado un nombre de columna, si esta es FK obtiene su reference
     * @param nombreColumna
     * @return 
     */
    public String reference(String nombreColumna) {
        return this.foreignKeys.get(nombreColumna);
    }
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the attributes
     */
    public List<Attribute> getAttributes() {
        return attributes;
    }

    /**
     * @param attributes the attributes to set
     */
    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    /**
     * @return the primaryKeys
     */
    public List<String> getPrimaryKeys() {
        return primaryKeys;
    }

    /**
     * @param primaryKeys the primaryKeys to set
     */
    public void setPrimaryKeys(List<String> primaryKeys) {
        this.primaryKeys = primaryKeys;
    }

    @Override
    public String toString() {
        return this.nombre;
    }

    /**
     * @return the database
     */
    public String getDatabase() {
        return database;
    }

    /**
     * @param database the database to set
     */
    public void setDatabase(String database) {
        this.database = database;
    }
    
    
    

    /**
     * @return the foreignKeys
     */
    public Map<String, String> getForeignKeys() {
        return foreignKeys;
    }

    /**
     * @param foreignKeys the foreignKeys to set
     */
    public void setForeignKeys(Map<String, String> foreignKeys) {
        this.foreignKeys = foreignKeys;
    }
}
