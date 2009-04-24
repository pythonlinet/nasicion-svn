package uy.edu.ucu.pii.obligatorio1.gnasi.datos;

import uy.edu.ucu.pii.obligatorio1.gnasi.datos.lista.TLista;

public class Pelicula {
	private String nomPelicula;
	private TLista actores;
	
	public String getNombre() {
		return nomPelicula;
	}
	public void setNombre(String nombre) {
		this.nomPelicula = nombre;
	}
	public TLista getActores() {
		return actores;
	}
	
	public Pelicula(String nombre) {
		super();
		this.nomPelicula = nombre;
	}
	
	/**
	 * 
	 * @param actor
	 * @return
	 */
	public boolean agregarActor(Actor actor){
		return actores.insertarOrdenado(actor.getNombre(), actor);
	}
	
	
	
	
}
