package bd1.obli2012.framework.estructura;

import java.util.List;

/**
 * Clase para reperesentar la definicion de una columna de una tabla
 * @author gnasi
 *
 */
public class ColumnDefinition {
	private String nombreColumna;
	private DataType tipo;
	private String parametro;
	private String defaultValule;
	private List<Constraint> constraints;

	public ColumnDefinition(String nombreColumna, DataType tipo, String parametro) {
		this.nombreColumna = nombreColumna;
		this.tipo = tipo;
		this.parametro = parametro;
	}

	@Override
	public String toString() {
		StringBuilder strBld = new StringBuilder();
		strBld.append(this.nombreColumna).append(" ").append(this.tipo);
		if(this.tipo.getTieneParametro()) {
			strBld.append("(").append(this.parametro).append(")");
		}
		
		return strBld.toString();
	}
	
	/**
	 * Retorna la definición de la columna
	 * @return
	 */
	public String obtenerDefinicionColumna(){
		return this.toString();
	}
	public String getNombreColumna() {
		return nombreColumna;
	}

	public void setNombreColumna(String nombreColumna) {
		this.nombreColumna = nombreColumna;
	}

	public DataType getTipo() {
		return tipo;
	}

	public void setTipo(DataType tipo) {
		this.tipo = tipo;
	}

	

}
