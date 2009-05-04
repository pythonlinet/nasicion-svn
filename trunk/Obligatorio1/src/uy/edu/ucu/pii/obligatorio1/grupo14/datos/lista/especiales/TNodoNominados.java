package uy.edu.ucu.pii.obligatorio1.grupo14.datos.lista.especiales;

import uy.edu.ucu.pii.obligatorio1.grupo14.datos.lista.TNodo;
/**
 * Reeimplementacion de la clase {@link TNodo}
 * @author Grupo14
 * @see uy.edu.ucu.pii.obligatorio1.grupo14.datos.lista.TNodo
 */

public class TNodoNominados extends TNodo {

	private TNodoNominados siguiente;
	private int votos;

	public TNodoNominados getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(TNodoNominados siguiente) {
		this.siguiente = siguiente;
	}

	public int getVotos() {
		return votos;
	}

	public void setVotos(int votos) {
		this.votos = votos;
	}

	@SuppressWarnings("unchecked")
	public TNodoNominados(Comparable clave, Object elemento) {
		super(clave, elemento);
		this.votos = 0;
	}

}
