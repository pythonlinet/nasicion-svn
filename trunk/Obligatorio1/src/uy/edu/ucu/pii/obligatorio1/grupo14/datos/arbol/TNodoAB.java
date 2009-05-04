package uy.edu.ucu.pii.obligatorio1.grupo14.datos.arbol;

import uy.edu.ucu.pii.obligatorio1.grupo14.datos.lista.TLista;


/**
 * Clase para representar un nodo dentro de un Arbol Binario de busqueda
 * Solución diseañada y codificad por Guillermo Nasi para la materia: Programación 2, Abril 2009, Universidad Catolica del Uruguay
 *@author Guillermo Nasi
 *@version 1.0
 *@see TArbol
 */
public class TNodoAB {
	@SuppressWarnings("unchecked")
	private Comparable clave;
	private Object elemento;
	private TNodoAB hijoIzquierdo;
	private TNodoAB hijoDerecho;

	// *********************INICIO de Getters**********************//
	@SuppressWarnings("unchecked")
	public Comparable getClave() {
		return clave;
	}

	public Object getElemento() {
		return elemento;
	}

	public TNodoAB getHijoIzquierdo() {
		return hijoIzquierdo;
	}

	public TNodoAB getHijoDerecho() {
		return hijoDerecho;
	}

	
	private void setHijoIzquierdo(TNodoAB hijoIzquierdo) {
		this.hijoIzquierdo = hijoIzquierdo;
	}

	private void setHijoDerecho(TNodoAB hijoDerecho) {
		this.hijoDerecho = hijoDerecho;
	}

	// *********************FIN de Getters**********************//
	@Override
	public String toString() {
		return this.clave.toString();
	}

	@SuppressWarnings("unchecked")
	public TNodoAB(Comparable clave, Object elemento) {
		this.clave = clave;
		this.elemento = elemento;
	}

	/**
	 * Metodo para buscar un nodo dentro del Sub Arbol, la raiz del arbol seria el nodo en el cual se ejecuta la consulta 
	 * @param clave - clave del nodo a buscar
	 * @return unNodo - el nodo que se queria buscar; null - si el nodo no existe en el arbol
	 */
	@SuppressWarnings("unchecked")
	public TNodoAB buscar(Comparable clave) {
		TNodoAB salida = null;

		if (getClave().compareTo(clave) == 0)
			salida = this;
		if (getClave().compareTo(clave) > 0 && getHijoIzquierdo() != null)
			salida = getHijoIzquierdo().buscar(clave);
		if (getClave().compareTo(clave) < 0 && getHijoDerecho() != null)
			salida = getHijoDerecho().buscar(clave);

		return salida;
	}

	/**
	 * Inserta un nuevo nodo hijo
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
		if (clave.compareTo(this.clave) > 0) {
			// Si el hijo derecho es nulo lo inserto en el hijo derecho, sino, le digo al hijo derecho que lo inserte
			if (getHijoDerecho() == null) {
				setHijoDerecho( new TNodoAB(clave, elemento));
				salida = true;
			} else {
				salida = getHijoDerecho().insertar(clave, elemento);
			}
		}
		/*
		 * Si la clave es menor que la del nodo actual inserto en nuevo nodo en el hijo derecho
		 */
		else if (clave.compareTo(getClave()) < 0) {
			// Si el hijo izquierdo es nulo lo inserto en el hijo izquierdo,
			// sino, le digo al hijo izquierdo que lo inserte
			if (getHijoIzquierdo() == null) {
				setHijoIzquierdo(new TNodoAB(clave, elemento));
				salida = true;
			} else{
				salida = getHijoIzquierdo().insertar(clave, elemento);
			}
		}
		return salida;
	}

	/**
	 * Metodo usado para eliminar un nodo apartir de una clave y la raiz del subarbol donde se puede o no encontrar el nodo
	 * @param clave - clave del nodo a eliminar
	 * @param raizSubAB - nodo raiz del subArbol donde está el nodo.
	 * @return unNodo, el nodo va a ser la raiz del subArbol, cuando se llame desde el TArbol, el metodo va a devolver la nueva raiz del arbol. 
	 */
	@SuppressWarnings("unchecked")
	public TNodoAB eliminar(Comparable clave, TNodoAB raizSubAB) {

		if (raizSubAB != null) {
			//Si la clave es menor a la mia, le digo a mi hijo izquierdo que elminie el nodo de su subArbol
			if (clave.compareTo(raizSubAB.getClave()) < 0) {

				raizSubAB.hijoIzquierdo = raizSubAB.eliminar(clave, raizSubAB.hijoIzquierdo);
				
			//Sino, si la clave es mayor a la mia, le digo a mi hijo derecho que elimine el nodo de su subArbol
			} else if (clave.compareTo(raizSubAB.getClave()) > 0) {

				raizSubAB.hijoDerecho = raizSubAB.eliminar(clave,raizSubAB.hijoDerecho);

			} else {// Si no es menor ni mayor es que yo soy el nodo a eliminar
				if (raizSubAB.hijoIzquierdo != null) {
					//Busco mi antecesor lexicografico
					TNodoAB anterior = raizSubAB.hijoIzquierdo.getMax();
	
					//Intercambio lugar con mi antecesor  
					raizSubAB.clave = anterior.getClave();
					raizSubAB.elemento = anterior.getElemento();
					
					
					 //Le digo a mi hijo Izquierdo que elimine de sus arboles el nodo con el cual acabo de intercambiar lugares
					 raizSubAB.hijoIzquierdo = eliminar(raizSubAB.getClave(), raizSubAB.hijoIzquierdo);
				
				} else if (raizSubAB.hijoDerecho != null) {//Si no tengo hijo Izquierdo intento la misma operacion con mi hijo Derecho
					//En lugar de buscar mi antecesor lexicografico busco mi sucesor
					TNodoAB siguiente = raizSubAB.hijoDerecho.getMin();
					
					//Intercambio lugares con él
					raizSubAB.clave = siguiente.getClave();
					raizSubAB.elemento = siguiente.getElemento();
					
					//Le digo a mi hijo que elimine de su subarbol el nodo con el que intercambie el lugar
					raizSubAB.hijoDerecho = eliminar(raizSubAB.getClave(), raizSubAB.hijoDerecho);
				} else {
					//En el caso de que no tenga hijos devuelvo null
					raizSubAB = null;
				}
			}
		}
		return raizSubAB;
	}

	
	/**
	 * Devuelve el padre de un nodo X, el metodo esta pensado para que se use en el nodo Raiz del arbol
	 * @param clave - clave del nodo del cual queremos averiguar el padre
	 * @return unNodo 
	 */
	@SuppressWarnings("unchecked")
	public TNodoAB getParent(Comparable clave) {
		TNodoAB salida = null;

		if (clave.compareTo(this.clave) < 0) {
			if (this.hijoIzquierdo != null)
				if (clave.compareTo(this.hijoIzquierdo.getClave()) == 0) {
					salida = this;
				} else
					this.hijoIzquierdo.getParent(clave);
		} else if (clave.compareTo(this.clave) > 0) {
			if (this.hijoDerecho != null)
				if (clave.compareTo(this.hijoDerecho.getClave()) == 0) {
					salida = this;
				} else
					this.hijoDerecho.getParent(clave);
		}

		return salida;
	}

	
	/**
	 * Metodo para averiguar el tamanio (cant de nodos) de un subArbol
	 * Para el calculo del tamaño se emplea la formula: AlturaSubArbol = 1 + alturaHD + alturaHI
	 * @return unNumero - cant de nodos del subArbol
	 */
	public int getTamanio() {
		/*
		 * AlturaSubArbol = 1 + alturaHD + alturaHI
		 */
		
		//Seteo tamanio a 1
		int tamanio = 1;
		//Le sumo el tamanio de mi hijo Derecho
		if (getHijoDerecho() != null)
			tamanio += getHijoDerecho().getTamanio();
		//Le sumo el tamanio de mi hijo Izquierdo
		if (getHijoIzquierdo() != null)
			tamanio += getHijoIzquierdo().getTamanio();
		return tamanio;
	}

	/**
	 *Metodo para hallar la altura de un nodo
	 * Para calcular la altura se utiliza la foruma: 
	 * 		si nodo no es hoja
	 * 			alturaSubArbol = Max(altura(hijoDerecho), altura(hijoIzquierdo)) +1
	 * 		sino
	 * 			altura = 0 
	 * @return unNumero - altura de un nodo, en el caso de la raiz, retorna la altura del arbol
	 */
	public int getAltura() {
		int altura = 0;
		// Nos fijamos si el hijo Derecho es nulo, si lo es, seteamos su altura
		// en 0 sino sera la altura devuelta por el hijoDerecho
		int altHD = this.hijoDerecho == null ? 0 : this.hijoDerecho.getAltura();
		// Nos fijamos si el hijo Izquierdo es nulo, si lo es, seteamos su
		// altura en 0 sino sera la altura devuelta por el hijoIzquierdo
		int altHI = this.hijoIzquierdo == null ? 0 : this.hijoIzquierdo.getAltura();

		// Si el nodo tiene al menos hijo entonces procedemos a calcular su altura, sino
		// la altura continuara con valor 0
		if (this.hijoDerecho != null || this.hijoIzquierdo != null)
			altura += Math.max(altHD, altHI) + 1;

		return altura;
	}

	/**
	 * Metodo para saber si un nodo es hoja
	 * @return true - si no tiene hijos (es hoja); false - si tiene al menos un hijo
	 */
	public boolean esHoja() {
		boolean salida = false;
		if (getHijoDerecho() == null && getHijoIzquierdo() == null)
			salida = true;
		return salida;
	}

	/**
	 * Esté metodo intercambia dos nodos
	 * 
	 * @param nodo
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private void switchNodes(TNodoAB nodo) {
		Comparable clave = this.clave;
		Object elemento = this.elemento;

		this.clave = nodo.getClave();
		this.elemento = nodo.getElemento();

		nodo.clave = clave;
		nodo.elemento = elemento;
	}

	/**
	 * Metodo para averiguar el preorden de un arbol
	 * Para calcular el preorden de siguen los pasos:
	 * 		nodoRaiz del subArbol + preOrden(hijoIzquierdo) + preOrden(hijoDerecho)
	 * @return array con los elementos del arbol en pre orden
	 */
	@SuppressWarnings("unchecked")
	public Comparable[] preOrden() {
		Comparable[] salida = new Comparable[getTamanio()];
		salida[0] = this.clave;

		if (!esHoja()) {
			int i = 1;

			if (this.hijoIzquierdo != null) {
				Comparable[] hDPre = this.hijoIzquierdo.preOrden();
				for (int j = 0; j < hDPre.length; j++, i++) {
					salida[i] = hDPre[j];
				}
			}

			if (this.hijoDerecho != null) {
				Comparable[] hDPre = this.hijoDerecho.preOrden();
				for (int j = 0; j < hDPre.length; j++, i++) {
					salida[i] = hDPre[j];
				}

			}

		}
		return salida;
	}

	/**
	 * Metodo para averiguar el postorden de un arbol
	 * Para calcular el post de siguen los pasos:
	 * 		postOrden(hijoIzquierdo) + postOrden(hijoDerecho) + nodoRaiz del subArbol
	 * @return array con los elementos del arbol en post orden
	 */
	@SuppressWarnings("unchecked")
	public Comparable[] postOrden() {
		Comparable[] salida = new Comparable[getTamanio()];
		if (!esHoja()) {
			int i = 0;

			if (this.hijoIzquierdo != null) {
				Comparable[] hIPost = this.hijoIzquierdo.postOrden();
				for (int j = 0; j < hIPost.length; j++, i++) {
					salida[i] = hIPost[j];
				}
			}

			if (this.hijoDerecho != null) {
				Comparable[] hDPost = this.hijoDerecho.postOrden();
				for (int j = 0; j < hDPost.length; j++, i++) {
					salida[i] = hDPost[j];
				}

			}
			salida[i] = this.clave;
		} else {
			salida[0] = this.clave;
		}
		return salida;
	}

	/**
	 * Metodo para averiguar el inorden de un arbol
	 * Para calcular el post de siguen los pasos:
	 * 		inOrden(hijoIzquierdo)  + nodoRaiz del subArbol + inOrden(hijoDerecho)
	 * @return array con los elementos del arbol en inorden
	 */
	@SuppressWarnings("unchecked")
	public Comparable[] inOrden() {
		Comparable[] salida = new Comparable[getTamanio()];

		// Verifico si soy hoja
		if (!esHoja()) {
			int i = 0;
			// Recorro mi hijoIzquierdo en Inorden
			if (getHijoIzquierdo() != null) {
				Comparable[] hIIno = getHijoIzquierdo().inOrden();
				for (int j = 0; j < hIIno.length; j++, i++) {
					salida[i] = hIIno[j];
				}
			}
			// Yo
			salida[i] = getClave();
			i++;

			// Recorro mi hijoDerecho en Inorden
			if (getHijoDerecho() != null) {
				Comparable[] hDIno = getHijoDerecho().inOrden();
				for (int j = 0; j < hDIno.length; j++, i++) {
					salida[i] = hDIno[j];
				}
			}
		} else
			// Si soy hoja, retorno mi clave
			salida[0] = getClave();

		return salida;
	}

	/**
	 * Metodo que retorna el menor nodo de un subArbol
	 * @return unNodo - el nodo menor del subArbol
	 */
	public TNodoAB getMin() {
		TNodoAB salida = this;
		if (this.hijoIzquierdo != null)
			salida = this.hijoIzquierdo.getMin();
		return salida;
	}

	/**
	 * Metodo que retorna el mayor nodo de un subArbol
	 * @return unNodo - el nodo mayor del subArbol
	 */
	public TNodoAB getMax() {
		TNodoAB salida = this;
		if (this.hijoDerecho != null)
			salida = this.hijoDerecho.getMax();
		return salida;
	}

	/**
	 * Metodo para saber si dos arboles son similares
	 * Primero se compara si ambos tienen la misma estructura y luego si sus hijos tambien tienen la misma estructura
	 * @param raiz - raiz del arbol que se quiere comparar
	 * @return true - si los nodos son similares; false - si los nodos no son similares
	 */
	public boolean similar(TNodoAB raiz) {
		boolean salida = true;

		if ((this.hijoIzquierdo == null) == (raiz.hijoIzquierdo == null)) {
			if (this.hijoIzquierdo != null)
				if (!this.hijoIzquierdo.similar(raiz.getHijoIzquierdo())) {
					salida = false;
				}
		} else {
			salida = false;
		}

		if ((this.hijoDerecho == null) == (raiz.hijoDerecho == null)) {
			if (this.hijoDerecho != null)
				if (!this.hijoDerecho.similar(raiz.getHijoDerecho())) {
					salida = false;
				}
		} else {
			salida = false;
		}

		return salida;
	}

	/**
	 * Metodo para comparar si dos nodos son equivalentes
	 * Se compara si ambos tienen la misma estructura, luego si sus hijos son equivalentes y por ultimo si ambos nodos tienen los mismos datos
	 * @param raiz - nodo raiz del subArbol con el que realizar la comparacion
	 * @return true - si los arboles son equivalentes; false - si los arboles no son equivalentes
	 */
	@SuppressWarnings("unchecked")
	public boolean equivalente(TNodoAB raiz) {
		boolean salida = true;

		if ((this.hijoIzquierdo == null) == (raiz.hijoIzquierdo == null)) {
			if (this.hijoIzquierdo != null)
				if (!this.hijoIzquierdo.similar(raiz.getHijoIzquierdo())) {
					salida = false;
				}
		} else {
			salida = false;
		}

		if ((this.hijoDerecho == null) == (raiz.hijoDerecho == null)) {
			if (this.hijoDerecho != null)
				if (!this.hijoDerecho.similar(raiz.getHijoDerecho())) {
					salida = false;
				}
		} else {
			salida = false;
		}
		if (salida)
			if (this.clave.compareTo(raiz.clave) != 0)
				salida = false;

		return salida;
	}

}