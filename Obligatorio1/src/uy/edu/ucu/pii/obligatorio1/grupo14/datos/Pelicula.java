package uy.edu.ucu.pii.obligatorio1.grupo14.datos;

import uy.edu.ucu.pii.obligatorio1.grupo14.datos.lista.TLista;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.lista.TNodo;

public class Pelicula {
	private String nomPelicula;
	private TLista actores = new TLista();
	private int votos;
	
	public String getNombre() {
		return nomPelicula;
	}
	public void setNombre(String nombre) {
		this.nomPelicula = nombre;
	}
	public TLista getActores() {
		return actores;
	}
	
	
	
	
	public int getVotos() {
		return votos;
	}
	public void setVotos(int votos) {
		this.votos = votos;
	}
	public Pelicula(String nombre) {
		super();
		this.nomPelicula = nombre;
		this.votos = 0;
	}
	
	/**
	 * Agrega un actor a la pelicula
	 * @param actor
	 * @return true - si se inserto correctamente; false - si el actor ya esta asociado a la pelicula
	 */
	public boolean agregarActor(Actor actor){
		return actores.insertarOrdenado(actor.getNombre(), actor);
	}
	
	/**
	 * Busca un actor dentro de la pelicula
	 * @param nomActor
	 * @return unActor - si el actor esta asociado a dicha pelicula; null - si el actor no esta asociado a la pelicula
	 */
	public Actor buscarActor(String nomActor){
		Actor result = null;
		
		TNodo busqueda = actores.buscarNodo(nomActor);
		//Revisamos que no sea null porque si queremos hacer getElemento de un nodo nulo Java responde con NullPointerException
		if(busqueda != null)
			result = (Actor)busqueda.getElemento();

		return result;
	}
	
	
	
}
