package uy.edu.ucu.pii.obligatorio1.grupo14.datos.arbol.especiales;

import uy.edu.ucu.pii.obligatorio1.grupo14.datos.Actor;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.arbol.TNodoAB;

/**
 * Reimplementacion de {@link TNodoAB} para agregar ciertos casos de busqueda que no eran solucionados por el TDA Arbol
 * @author Guillermo
 * @version 1.0
 * @see uy.edu.ucu.pii.obligatorio1.grupo14.datos.arbol.TArbol
 * @see uy.edu.ucu.pii.obligatorio1.grupo14.datos.arbol.TNodoAB
 */

public class TNodoABActor extends TNodoAB {
	private TNodoABActor hijoIzquierdo;
	private TNodoABActor hijoDerecho;

	
	
	public TNodoABActor getHijoIzquierdo() {
		return hijoIzquierdo;
	}

	private void setHijoIzquierdo(TNodoABActor hijoIzquierdo) {
		this.hijoIzquierdo = hijoIzquierdo;
	}

	public TNodoABActor getHijoDerecho() {
		return hijoDerecho;
	}

	private void setHijoDerecho(TNodoABActor hijoDerecho) {
		this.hijoDerecho = hijoDerecho;
	}

	public TNodoABActor(Comparable clave, Object elemento) {
		super(clave, elemento);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Inserta un nuevo nodo del tipo {@link TNodoABActor}
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
				this.hijoDerecho = new TNodoABActor(clave, elemento);
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
				this.hijoIzquierdo = new TNodoABActor(clave, elemento);
				salida = true;
			} else{
				salida = this.hijoIzquierdo.insertar(clave, elemento);
			}
		}
		return salida;
	}
	
	
	
	
	/**
	 * Metodo para contar la cantidad de actrices
	 * 
	 * @return unNumero - cantidad de actrices
	 */
	public int contarActrices() {
		/*
		 * Para contar las actrices se utiliza el mismo metodo que para contar
		 * la altura del arbol agregando la condicion de que para que se sume el
		 * valor el sexo debe ser F AlturaSubArbol = 1 + alturaHD + alturaHI
		 */

		// Seteo tamanio a 1
		int actrices = ((Actor) this.getElemento()).getSexo().equals("F") ? 1
				: 0;
		// Le sumo la cant de mi hijo Derecho
		if (getHijoDerecho() != null)
			actrices += getHijoDerecho().contarActrices();
		// Le sumo la cant de mi hijo Izquierdo
		if (getHijoIzquierdo() != null)
			actrices += getHijoIzquierdo().contarActrices();

		return actrices;
	}

	/**
	 * Metodo para contar la cantidad de actores
	 * 
	 * @return unNumero - cantidad de actores
	 */
	public int contarActores() {
		/*
		 * Para contar los actores se utiliza el mismo metodo que para contar la
		 * altura del arbol agregando la condicion de que para que se sume el
		 * valor el sexo debe ser M AlturaSubArbol = 1 + alturaHD + alturaHI
		 */

		// Seteo tamanio a 1
		int actores = ((Actor) this.getElemento()).getSexo().equals("M") ? 1: 0;
		// Le sumo la cant de mi hijo Derecho
		if (getHijoDerecho() != null)
			actores += getHijoDerecho().contarActrices();
		// Le sumo la cant de mi hijo Izquierdo
		if (getHijoIzquierdo() != null)
			actores += getHijoIzquierdo().contarActrices();

		return actores;
	}

	/**
	 * Metodo para listar todas las actrices del subArbol
	 * @return	lista de actrices del subarbol de este nodo
	 */
	public Comparable[] listadoActrices() {
		Comparable[] actrices = new Comparable[contarActrices()];

		if (!esHoja()) {
			int i = 0;
			
			if (getHijoIzquierdo() != null) {
				Comparable[] lisActHI = getHijoIzquierdo().listadoActrices();
				if(lisActHI != null)
				for(int j = 0; j < lisActHI.length; j++){
					if(((Actor)buscar(lisActHI[j]).getElemento()).getSexo().equals("F")){
						actrices[i] = lisActHI[j];
						++i;
					}
				}
			}
			if(((Actor) this.getElemento()).getSexo().equals("F")){
				actrices[i] = ((Actor) this.getElemento()).getNombre();
				++i;
			}
			
			if (getHijoDerecho() != null) {
				Comparable[] lisActHD = getHijoDerecho().listadoActrices();
				if(lisActHD != null)
				for(int j = 0; j < lisActHD.length; j++){
					if(((Actor)buscar(lisActHD[j]).getElemento()).getSexo().equals("F")){
						actrices[i] = lisActHD[j];
						++i;
					}
				}
			}

		} else {
			if(((Actor) this.getElemento()).getSexo().equals("F"))
				actrices[0] = ((Actor) this.getElemento()).getNombre();
		}

		return actrices;
	}

	
	
}
