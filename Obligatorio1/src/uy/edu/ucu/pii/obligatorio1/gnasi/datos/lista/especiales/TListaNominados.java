package uy.edu.ucu.pii.obligatorio1.gnasi.datos.lista.especiales;

import uy.edu.ucu.pii.obligatorio1.gnasi.datos.lista.TLista;
import uy.edu.ucu.pii.obligatorio1.gnasi.datos.lista.TNodo;

public class TListaNominados extends TLista {

	private TNodoNominados primero;
	private int tamanio;
	
	public TNodoNominados getPrimero() {
		return primero;
	}

	public void setPrimero(TNodoNominados primero) {
		this.primero = primero;
	}

	public Integer getTamanio(){
		return this.tamanio;
	}
	
	public void setTamanio(int tamanio){
		this.tamanio = tamanio;
	}
	
	public TListaNominados() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Inserta un nuevo elemento/nodo al final de la lista
	 * 
	 * @param clave clave del elemnto
	 * @param elemento objeto a insertar
	 * @return true - si se inserto un elemento; false - si no pudo realizarse la inserci�n.
	 */
	@SuppressWarnings("unchecked")
	public boolean insertar(Comparable clave, Object elemento) {
		TNodoNominados nuevoNodo = new TNodoNominados(clave, elemento);
		boolean salida = false;
		if (buscarNodo(clave) == null) {
			if (esVacia()) {
				this.primero = nuevoNodo;
				this.tamanio += 1;
				salida = true;
			} else {
				// Agrego el nuevo nodo
				getUltimo().setSiguiente(nuevoNodo);
				this.tamanio += 1;
				salida = true;
			}
		}
		return salida;
	}
	
}
