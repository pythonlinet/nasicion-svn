package uy.edu.ucu.pii.obligatorio1.grupo14.datos.grafo;

import uy.edu.ucu.pii.obligatorio1.grupo14.datos.lista.TLista;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.lista.TNodo;
/**
 * Clase para la implementacion de Grafos.
 * 
 * @author Grupo14
 *
 */
public class TGrafo {
	TLista vertices;

	/**
	 * Metodo para insertar un nuevo vertice en el grafo 
	 * @param etiqueta
	 * @return
	 */
	public boolean insertarVertice(Comparable etiqueta) {
		// El metodo insertar de TLista no permite insercion de datos
		// duplicados, por lo tanto no es necesario verificar que el elemento no
		// exista
		return vertices.insertarOrdenado(etiqueta, new TVertice(etiqueta));

	}
	/**
	 *  Metodo para agregar una adyacencia 
	 * @param origen vertice de origen
	 * @param destino vertice de destino
	 * @param costo costo desde el origen hasta el destino
	 * @return true - si se pudo agregar la adyacencia; false - si ya existia una adyacencia o si uno de los 
	 */
	public boolean insertarAdyacencia(Comparable origen, Comparable destino, Comparable costo){
		boolean salida = false;
		
		TNodo nodoOrigen = vertices.buscarNodo(origen);
		//Si el nodo de origen existe
		if(nodoOrigen != null){
			TNodo nodoDestino = vertices.buscarNodo(destino);
			//Si el nodo destino existe
			if(nodoDestino != null){
				//Demasiados casteos??
				salida = ((TVertice)nodoOrigen.getElemento()).ingresarAdyacencia((TVertice)nodoDestino.getElemento(), costo);
			}
		}
		return salida;
	}
}
