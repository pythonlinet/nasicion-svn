package bd1.obli2012.framework.estructura;

import java.util.List;

/**
 * Clase para representar la definición de una tabla de la base de datos
 * @author gnasi
 *
 */
public class TableDefinition {
	private String nombreTabla;
	private List<ColumnDefinition> defColumnas;
	
	
	
	public TableDefinition(){
		
	}

	public TableDefinition(String nombreTabla,
			List<ColumnDefinition> defColumnas) {
		super();
		this.nombreTabla = nombreTabla;
		this.defColumnas = defColumnas;
	}
	
	public String getNombreTabla() {
		return nombreTabla;
	}
	public void setNombreTabla(String nombreTabla) {
		this.nombreTabla = nombreTabla;
	}
	public List<ColumnDefinition> getDefColumnas() {
		return defColumnas;
	}
	public void setDefColumnas(List<ColumnDefinition> defColumnas) {
		this.defColumnas = defColumnas;
	}
	
	
}
