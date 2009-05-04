package uy.edu.ucu.pii.obligatorio1.grupo14.datos.arbol.especiales;

import uy.edu.ucu.pii.obligatorio1.grupo14.datos.Actor;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.Categoria;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.arbol.TNodoAB;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.lista.especiales.TNodoNominados;

/**
 * Reimplementacion de {@link TNodoAB} para agregar ciertos casos de busqueda que no eran solucionados por el TDA Arbol
 * @author Guillermo
 * @version 1.0
 * @see uy.edu.ucu.pii.obligatorio1.grupo14.datos.arbol.TArbol
 * @see uy.edu.ucu.pii.obligatorio1.grupo14.datos.arbol.TNodoAB
 */
public class TNodoABCategoria extends TNodoAB {
	private TNodoABCategoria hijoIzquierdo;
	private TNodoABCategoria hijoDerecho;

	
	
	
	public TNodoABCategoria getHijoIzquierdo() {
		return hijoIzquierdo;
	}



	public void setHijoIzquierdo(TNodoABCategoria hijoIzquierdo) {
		this.hijoIzquierdo = hijoIzquierdo;
	}



	public TNodoABCategoria getHijoDerecho() {
		return hijoDerecho;
	}



	public void setHijoDerecho(TNodoABCategoria hijoDerecho) {
		this.hijoDerecho = hijoDerecho;
	}



	public TNodoABCategoria(Comparable clave, Object elemento) {
		super(clave, elemento);
		// TODO Auto-generated constructor stub
	}

	
	
	/**
	 * Inserta un nuevo nodo tipo {@link TNodoABCategoria}
	 * 
	 * @param clave - clave del nodo
	 * @param elemento - objeto que se almacenara en el nodo
	 * @return true - si pudo insertar en nuevo nodo; false - si no se pudo realizar la insercion, por ejemplo si el nodo ya existe
	 */
	@SuppressWarnings("unchecked")
	public boolean insertar(Comparable clave, Object elemento) {
		boolean salida = false;
		/*
		 * Si la clave es mayor que la del nodo actual inserto en nuevo nodo en
		 * el hijo derecho
		 */
		if (clave.compareTo(this.getClave()) > 0) {
			// Si el hijo derecho es nulo lo inserto en el hijo derecho, sino, le digo al hijo derecho que lo inserte
			if (this.hijoDerecho == null) {
				this.hijoDerecho = new TNodoABCategoria(clave, elemento);
				salida = true;
			} else {
				salida = this.hijoDerecho.insertar(clave, elemento);
			}
		}
		/*
		 * Si la clave es menor que la del nodo actual inserto en nuevo nodo en el hijo derecho
		 */
		else if (clave.compareTo(this.getClave()) < 0) {
			// Si el hijo izquierdo es nulo lo inserto en el hijo izquierdo,
			// sino, le digo al hijo izquierdo que lo inserte
			if (this.hijoIzquierdo == null) {
				this.hijoIzquierdo = new TNodoABCategoria(clave, elemento);
				salida = true;
			} else{
				salida = this.hijoIzquierdo.insertar(clave, elemento);
			}
		}
		return salida;
	}
	
	
	
	
	/**
	 * Metodo que recorre un subArbol de categorias buscando la cantidad de votos que tiene el actor
	 * <i><b>Este metodo se encuentra fuera de uso ya que se agrego una variable a {@link Actor} que contabiliza la cantidad total de votos que tiene en el concurso</b></i>
	 * @param nomActor - actor del que se quiere saber los votos
	 * @return unNumero - la cantidad total de votos que tiene el actor 
	 */
	@Deprecated
	public int cantidadVotosDeActorOActriz(String nomActor) {
		int salida = 0;
		Categoria categoria =(Categoria)getElemento(); 
			
		//Si la categoria es de actores
		if(categoria.getTipo().equals("A")){
			TNodoNominados nominado = (TNodoNominados)categoria.getNominados().buscarNodo(nomActor);
			//Si el actor esta nominado a esa categoria
			if(nominado != null){
				//sumo 1 al total de los votos
				salida += nominado.getVotos();
			}
		}
		if(getHijoIzquierdo() != null){
			salida += ((TNodoABCategoria)getHijoIzquierdo()).cantidadVotosDeActorOActriz(nomActor);
		}
		if(getHijoDerecho() != null){
			salida += ((TNodoABCategoria)getHijoDerecho()).cantidadVotosDeActorOActriz(nomActor);
		}
		
		return salida;
	}



	public int cantidadVotosDePelicula(String nomPelicula) {
		int salida = 0;
		Categoria categoria =(Categoria)getElemento(); 
			
		//Si la categoria es de actores
		if(categoria.getTipo().equals("P")){
			TNodoNominados nominado = (TNodoNominados)categoria.getNominados().buscarNodo(nomPelicula);
			//Si el actor esta nominado a esa categoria
			if(nominado != null){
				//sumo 1 al total de los votos
				salida += nominado.getVotos();
			}
		}
		if(getHijoIzquierdo() != null){
			salida += ((TNodoABCategoria)getHijoIzquierdo()).cantidadVotosDePelicula(nomPelicula);
		}
		if(getHijoDerecho() != null){
			salida += ((TNodoABCategoria)getHijoDerecho()).cantidadVotosDePelicula(nomPelicula);
		}
		
		return salida;
	}
	
	

}
