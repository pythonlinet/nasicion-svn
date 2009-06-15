package uy.edu.ucu.pii.obligatorio2.entidades;

import sun.rmi.transport.Target;
import uy.edu.ucu.pii.grupo14.datos.grafo.TArista;

public class Tramo extends TArista{
	private Ciudad destino;
	
	private Costo costo;

	
	public Ciudad getCiudadDestino() {
		return destino;
	}

	public void setCiudadDestino(Ciudad destino) {
		this.destino = destino;
	}


	public Costo getCostoTramo() {
		return costo;
	}

	
	public void setCostoTramo(Costo costo) {
		this.costo = costo;
	}

	public Tramo(Ciudad destino, Costo costo) {
		this.destino = destino;
		this.costo = costo;
	}
	
	
	
}
