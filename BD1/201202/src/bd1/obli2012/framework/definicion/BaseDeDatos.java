/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.framework.definicion;

import java.util.List;

/**
 *
 * @author favio.ortelli/guillermo.nasi
 */
public class BaseDeDatos {
    private String dbName;
    private List<Tabla> tables;

    /**
     * @return the dbName
     */
    public String getDbName() {
        return dbName;
    }

    /**
     * @param dbName the dbName to set
     */
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    /**
     * @return the tables
     */
    public List<Tabla> getTables() {
        return tables;
    }

    /**
     * @param tables the tables to set
     */
    public void setTables(List<Tabla> tables) {
        this.tables = tables;
    }

    @Override
    public String toString() {
        return this.dbName;
    }
    
    
}
