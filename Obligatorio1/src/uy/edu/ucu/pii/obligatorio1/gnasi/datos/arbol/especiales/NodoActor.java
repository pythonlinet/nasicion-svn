package uy.edu.ucu.pii.obligatorio1.gnasi.datos.arbol.especiales;

import uy.edu.ucu.pii.obligatorio1.gnasi.datos.Actor;
import uy.edu.ucu.pii.obligatorio1.gnasi.datos.arbol.TNodoAB;

public class NodoActor extends TNodoAB {
	private NodoActor hijoIzquierdo;
	private NodoActor hijoDerecho;

	
	
	public NodoActor getHijoIzquierdo() {
		return hijoIzquierdo;
	}

	private void setHijoIzquierdo(NodoActor hijoIzquierdo) {
		this.hijoIzquierdo = hijoIzquierdo;
	}

	public NodoActor getHijoDerecho() {
		return hijoDerecho;
	}

	private void setHijoDerecho(NodoActor hijoDerecho) {
		this.hijoDerecho = hijoDerecho;
	}

	public NodoActor(Comparable clave, Object elemento) {
		super(clave, elemento);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
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
				this.hijoDerecho = new NodoActor(clave, elemento);
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
				this.hijoIzquierdo = new NodoActor(clave, elemento);
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
	 * @return cantidad de actrices
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
	 * @return cantidad de actrices
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
