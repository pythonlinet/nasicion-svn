package uy.edu.ucu.pii.obligatorio1.grupo14.datos;

import uy.edu.ucu.pii.obligatorio1.grupo14.datos.lista.TNodo;

public class Nominado extends TNodo {

	private int votos;
	
	@SuppressWarnings("unchecked")
	public Nominado(Comparable clave, Object elemento) {
		super(clave, elemento);
		votos = 0;
	}

	public int getVotos() {
		return votos;
	}

	public void setVotos(int votos) {
		this.votos = votos;
	}

	public int votar(){
		this.votos +=1;
		return votos;
	}
	
}
