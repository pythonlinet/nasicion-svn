package uy.edu.ucu.pii.obligatorio2.entidades;

import uy.edu.ucu.pii.grupo14.datos.lista.TLista;

/**
 * Clase para representar una ciudad dentro de la clase simil grafo {@link Ciudades}
 * @author Grupo14
 *
 * @see  {@link Tramo}
 * @see  {@link Ciudades}
 * 
 * @version 1.0
 */
public class Ciudad{
	private Comparable nombre;
	private Comparable pais;

	private TLista<Tramo> tramos;
	
	public Comparable getNombre() {
		return nombre;
	}
	public void setNombre(Comparable nombre) {
		this.nombre = nombre;
	}
	public Comparable getPais() {
		return pais;
	}
	public void setPais(Comparable pais) {
		this.pais = pais;
	}
	
	
	public TLista<Tramo> getTramos() {
		return tramos;
	}
	public void setTramos(TLista<Tramo> tramos) {
		this.tramos = tramos;
	}
	public Ciudad(Comparable nombre, Comparable pais) {
		this.nombre = nombre;
		this.pais = pais;
		tramos = new TLista<Tramo>();
	}
	/**
	 * Agrega un tramo entre esta ciudad y la ciudad destino especificada
	 * @param destino ciudad de destino
	 * @param costo costo hacia esa ciudad
	 * @return true - si se realizo el alta
	 * false - si no se realizo el alta, en el caso de estar duplicada la entrada
	 */
	public boolean agregarTramo(Ciudad destino,	Costo costo) {
		// TODO Auto-generated method stub
		return tramos.insertar(destino.getNombre(), new Tramo(destino,costo));
	}
/**
 * Metodo para saber si esta ciudad tiene un tramo que la conecte con la ciudad de destino
 * @param nombreCiudadDestino nombre de la ciudad de la que se quiere conocer si existe un tramo hacia ella
 * @return true - si existe un tramo
 * false - si no existe un tramo entre ambas ciudades
 */
	public boolean existeTramo(String nombreCiudadDestino) {
		return getTramos().buscarNodo(nombreCiudadDestino)==null?false:true;
	}
	
	
	
	
}
