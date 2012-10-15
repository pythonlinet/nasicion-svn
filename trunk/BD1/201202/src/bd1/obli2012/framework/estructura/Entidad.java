package bd1.obli2012.framework.estructura;

import java.sql.ResultSet;
import java.util.List;

public abstract class Entidad{
	
	/**
	 * Retorna la tabla que representa la entidad en la base de datos
	 * @return
	 */
	public abstract String getTabla();
	
	/**
	 * Crea un objeto apartir del {@link ResultSet} obtenido de la consulta
	 * @param rs - el {@link ResultSet} de la consulta
	 * @return un objeto del tipo T
	 */
	public abstract Entidad createFromRecordSet(ResultSet rs);
	
	/**
	 * Crea una lista de objetos apartir del {@link ResultSet} obtenido de la consulta
	 * @param rs - el {@link ResultSet} de la consulta
	 * @return una lista de objetos del tipo T
	 */
	public abstract List<? extends Entidad> createListFromRecordSet(ResultSet rs);
	
	/**
	 * Genera la definición de la tabla para la entidad
	 * @return la {@link TableDefinition} de la entidad
	 */
	public abstract TableDefinition getTableDefinition(); 
}
