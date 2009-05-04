package uy.edu.ucu.pii.obligatorio1.grupo14.datos.lista.especiales;

import uy.edu.ucu.pii.obligatorio1.grupo14.datos.lista.TLista;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.lista.TNodo;

/**
 * Clase que extienede de la clase {@link TLista} y reimplementa algunos metodos
 * 
 * @author Grupo14
 * @see uy.edu.ucu.pii.obligatorio1.grupo14.datos.lista.TLista
 */

public class TListaNominados extends TLista {

	private TNodoNominados primero;

	// ****************************************************************************************/
	public TNodoNominados getPrimero() {
		return primero;
	}

	protected void setPrimero(TNodoNominados primero) {
		this.primero = primero;
	}

	public TListaNominados() {
		// TODO Auto-generated constructor stub
	}

	// *************************************************************************************/
	/**
	 * Inserta un nuevo elemento/nodo al final de la lista
	 * 
	 * @param clave
	 *            clave del elemnto
	 * @param elemento
	 *            objeto a insertar
	 * @return true - si se inserto un elemento; false - si no pudo realizarse
	 *         la inserciÛn.
	 */
	@SuppressWarnings("unchecked")
	public boolean insertar(Comparable clave, Object elemento) {
		TNodoNominados nuevoNodo = new TNodoNominados(clave, elemento);
		boolean salida = false;
		if (buscarNodo(clave) == null) {
			if (esVacia()) {
				this.primero = nuevoNodo;
				// this.tamanio += 1;
				setTamanio(getTamanio() + 1);
				salida = true;
			} else {
				// Agrego el nuevo nodo
				getUltimo().setSiguiente(nuevoNodo);
				// this.tamanio += 1;
				setTamanio(getTamanio() + 1);
				salida = true;
			}
		}
		return salida;
	}

	/**
	 * Inserta un nuevo elemento/nodo de forma ordenada en la lista, para esto
	 * recorre la lista hasta encontrar una posicion donde el siguiente sea
	 * mayor que al nodo a insertar y se menor al anterior.
	 * 
	 * @param clave
	 *            clave del elemnto
	 * @param elemento
	 *            objeto a insertar
	 * @return true - si se inserto un elemento; false - si no pudo realizarse
	 *         la inserciÔøΩn.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean insertarOrdenado(Comparable clave, Object elemento) {
		boolean salida = false;
		if (!esVacia()) {
			// Verificamos que no exista ya un elemento con esa clave
			TNodoNominados aux = (TNodoNominados) buscarNodo(clave);
			if (aux == null) {
				aux = new TNodoNominados(clave, elemento);
				TNodoNominados puntero = getPrimero();

				do {
					// Comprobamos si aux es menor que el puntero
					if (puntero.getClave().compareTo(clave) > 0) {
						if (puntero.equals(getPrimero())) {
							insertarPrimero(aux.getClave(), aux.getElemento());
							// Al insertar primero suma 1 al tamaÒo, pero como
							// ya controlamos el tamaÒo al final del
							// procedimiento aqui le restamos 1
							// TODO Corregir!!! Muy Poco Prolijo
							setTamanio(getTamanio() - 1);
							salida = true;
						}
					} else {
						if (puntero.getSiguiente() == null) {
							puntero.setSiguiente(aux);
							salida = true;
						} else if (puntero.getSiguiente().getClave().compareTo(
								clave) > 0) {
							aux.setSiguiente(puntero.getSiguiente());
							puntero.setSiguiente(aux);
							salida = true;
						}
					}
					puntero = puntero.getSiguiente();
				} while (!salida);

			}
		} else {
			setPrimero(new TNodoNominados(clave, elemento));
			salida = true;
		}
		// Si se realizo una insercion incrementamos el tama√±o de la lista
		setTamanio(getTamanio() + (salida ? 1 : 0));
		return salida;
	}

	/**
	 * Inserta un nuevo elemento/nodo al principio de la lista
	 * 
	 * @param clave
	 *            clave del nuevo nodo
	 * @param elemento
	 *            objeto o elemento del nodo
	 * @return true - si se realizo la insercio; false - si no pudo insertarse
	 *         el nuevo elemento;
	 */
	@SuppressWarnings("unchecked")
	public boolean insertarPrimero(Comparable clave, Object elemento) {
		boolean salida = false;
		if (!esVacia()) {
			if (buscarNodo(clave) == null) {
				TNodoNominados nNodo = new TNodoNominados(clave, elemento);
				nNodo.setSiguiente(getPrimero());
				setPrimero(nNodo);
				salida = true;
			}
		} else {
			// this.primero = new TNodo(clave, elemento);
			setPrimero(new TNodoNominados(clave, elemento));
			salida = true;
		}
		// this.tamanio += salida?1:0;
		setTamanio(getTamanio() + (salida ? 1 : 0));
		return salida;
	}

}
