package uy.edu.ucu.pii.obligatorio1.gnasi.datos.lista.especiales;

import uy.edu.ucu.pii.obligatorio1.gnasi.datos.lista.TNodo;

public class TNodoNominados extends TNodo {

	TNodoNominados siguiente;

	public TNodoNominados getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(TNodoNominados siguiente) {
		this.siguiente = siguiente;
	}

	
	@SuppressWarnings("unchecked")
	public TNodoNominados(Comparable clave, Object elemento) {
		super(clave, elemento);
		// TODO Auto-generated constructor stub
	}

}
