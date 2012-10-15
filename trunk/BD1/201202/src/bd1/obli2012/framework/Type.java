/*
 * To change this template(), choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.framework;

/**
 *
 * @author guillermo
 */
public enum Type {
    /*    -7	BIT
     -6	TINYINT
     -5	BIGINT
     -4	LONGVARBINARY 
     -3	VARBINARY
     -2	BINARY
     -1	LONGVARCHAR
     0	NULL
     1	CHAR
     2	NUMERIC
     3	DECIMAL
     4	INTEGER
     5	SMALLINT
     6	FLOAT
     7	REAL
     8	DOUBLE
     12	VARCHAR
     91	DATE
     92	TIME
     93	TIMESTAMP
     1111 	OTHER*/

    BIT(-7),
    TINYINT(-6),
    BIGINT(-5),
    LONGVARBINARY(-4),
    VARBINARY(-3),
    BINARY(-2),
    LONGVARCHAR(-1),
    NULL(0),
    CHAR(1),
    NUMERIC(2),
    DECIMAL(3),
    INTEGER(4),
    SMALLINT(5),
    FLOAT(6),
    REAL(7),
    DOUBLE(8),
    VARCHAR(12),
    DATE(91),
    TIME(92),
    TIMESTAMP(93);
    
    private int codigo;
    private Type(int codigo){
        this.codigo = codigo;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }
    
    /**
     * Obtiene el Type para un codigo dado
     * @param codigo - codigo del Type requerido;
     * @return el Type cuyo codigo corresponde o null si no existe  
     *      el type con el codigo dado
     */
    public static Type getTypeForCode(int codigo){
        for(Type t : Type.values()){
            if(t.getCodigo() == codigo){
                return t;
            }
        }
        return null;
    }
}
