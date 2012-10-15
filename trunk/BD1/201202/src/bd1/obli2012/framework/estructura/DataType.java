package bd1.obli2012.framework.estructura;

/**
 * Usado para tener definidos los tipos de datos de la base
 * @author gnasi
 *
 */
public enum DataType {
	CHARACTER(true),
	DATE(false),
	INTEGER(false),
	BIGINT(false),
	FLOAT(true),
	TIMESTAMP(false),
	INTERVAL(false);
	
	/**
	 * Indica si a la hora de crear la tabla se le asignara un parametro
	 * (ej: el largo para el character)
	 */
	private boolean tieneParametro;
	public boolean getTieneParametro(){
		return this.tieneParametro;
	}
	private DataType(boolean tieneParametro){
		this.tieneParametro = tieneParametro;
	}
}
