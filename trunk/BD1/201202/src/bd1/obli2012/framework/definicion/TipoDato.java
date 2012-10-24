/*
 * To change this template(), choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.framework.definicion;

/**
 *
 * @author guillermo
 */
public enum TipoDato {
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

    BIT(-7,true),
    TINYINT(-6,false),
    BIGINT(-5,false),
    LONGVARBINARY(-4,false),
    VARBINARY(-3,false),
    BINARY(-2,false),
    LONGVARCHAR(-1,true),
    NULL(0,false),
    CHAR(1,false),
    NUMERIC(2,true),
    DECIMAL(3,false),
    INTEGER(4,false),
    SMALLINT(5,false),
    FLOAT(6,false),
    REAL(7,false),
    DOUBLE(8,false),
    VARCHAR(12,true),
    DATE(91,false),
    TIME(92,false),
    TIMESTAMP(93,false);
    
    private int codigo;
    private boolean hasLenght;
    private TipoDato(int codigo, boolean hasLenght){
        this.codigo = codigo;
        this.hasLenght = hasLenght;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }
    
    public boolean hasLenght() {
        return this.hasLenght;
    }
    /**
     * Obtiene el TipoDato para un codigo dado
     * @param codigo - codigo del TipoDato requerido;
     * @return el TipoDato cuyo codigo corresponde o null si no existe  
     *      el type con el codigo dado
     */
    public static TipoDato getTypeForCode(int codigo){
        for(TipoDato t : TipoDato.values()){
            if(t.getCodigo() == codigo){
                return t;
            }
        }
        return null;
    }
}
