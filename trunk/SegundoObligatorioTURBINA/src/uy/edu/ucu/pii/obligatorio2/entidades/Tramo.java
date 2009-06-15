package uy.edu.ucu.pii.obligatorio2.entidades;

import uy.edu.ucu.pii.grupo14.datos.grafo.TArista;
import uy.edu.ucu.pii.grupo14.datos.lista.TLista;

public class Tramo{
	private Ciudad destino;
	
	private Costo costo;
	private TLista<Avion> aviones;
	
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
	
	
	public TLista<Avion> getAvionesAsignados() {
		return aviones;
	}

	public Tramo(Ciudad destino, Costo costo) {
		this.destino = destino;
		this.costo = costo;
		this.aviones = new TLista<Avion>();
	}
	
	
	
}
