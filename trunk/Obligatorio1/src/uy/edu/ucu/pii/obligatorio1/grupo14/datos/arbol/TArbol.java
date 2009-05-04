package uy.edu.ucu.pii.obligatorio1.grupo14.datos.arbol;

/**
 * Clase para la representación de Arboles Binarios de busqueda
 * Codigo escrito por Guillermo Nasi para la materia: Programación 2, Abril 2009, Universidad Catolica del Uruguay
 *@author Guillermo Nasi
 *@version 1.0
 * @see TNodoAB
 */
public class TArbol {
	private TNodoAB raiz;

	public TNodoAB getRaiz() {
		return raiz;
	}

	private void setRaiz(TNodoAB raiz) {
		this.raiz = raiz;
	}
	
	
	public TArbol() {
		this.raiz = null;
	}

	/**
	 * Busca un nodo dentro del arbol en base a la clave del mismo
	 * Para mas detalles sobre le metodo buscar ver la documentacion de {@link TNodoAB}
	 * @param clave - clave del nodo
	 * @return unNodo - si encontro un nodo con la clave especificada; null - si no existe un nodo con esa clave en el arbol
	 */
	@SuppressWarnings("unchecked")
	public TNodoAB buscar(Comparable clave) {
		TNodoAB salida = null;
		if (!vacio()) {
			salida = getRaiz().buscar(clave);
		}
		return salida;
	}

	/**
	 * Inserta un nuevo elemento en el arbol
	 * Para ver mas información sobre el metodo insertar en arbol binario ver la documentacion de la clase {@link TNodoAB}
	 * @param clave - clave que se usara para ordenar el nodo dentro del arbol
	 * @param elemento - elemento u objeto que se almacenara dentro del nodo
	 * @return true - si se inserto el nuevo nodo; false - si no se pudo insertar el nuevo nodo, posiblemente se de porque la clave del nodo está duplicada
	 */
	@SuppressWarnings("unchecked")
	public boolean insertar(Comparable clave, Object elemento) {
		boolean salida = false;
		if (vacio()) {
			setRaiz(new TNodoAB(clave, elemento));
			salida = true;
		} else
			salida = getRaiz().insertar(clave, elemento);

		return salida;
	}

	/**
	 * Elimina un nodo del arbol que tenga la clave ingresada
	 * @param clave - clave del nodo que se desea eliminar
	 * @return true - si se elimino el nodo; false - si el nodo no existe
	 */
	@SuppressWarnings("unchecked")
	public boolean eliminar(Comparable clave) {
		boolean salida = false;
		if (!vacio() && getRaiz().buscar(clave) != null) {
			setRaiz(getRaiz().eliminar(clave,this.raiz));
			if(this.buscar(clave) == null) salida = true;
		}
		return salida;
	}

	/**
	 * Metodo para encontrar el padre de un nodo
	 * @param clave - clave del nodo del cual se quiere hallar el padre
	 * @return unNodo - el padre del nodo se ingreso; false - si el nodo es la raiz o si el nodo no existe
	 */
	@SuppressWarnings("unchecked")
	public TNodoAB getParent(Comparable clave) {
		return getRaiz().getParent(clave);
	}

	/**
	 * Metodo para saber si un arbol se encuentra vacio
	 * @return true - si no hay elementos en el arbol; false - si el arbol  contiene al menos UN elemento
	 */
	public boolean vacio() {
		boolean salida = false;
		if (getRaiz() == null)
			salida = true;

		return salida;
	}

	/**
	 * Metodo para saber el tamaño del arbol, para mas detalles ver el metodo <b>getTamanio</b> en la clase {@link TNodoAB}
	 * @return unNumero - tamaño del arbol
	 */
	public int getTamanio() {
		int tamanio = 0;
		if (!vacio()) {
			tamanio = getRaiz().getTamanio();
		}
		return tamanio;
	}

/**
 * Vacia el arbol
 */
	public void vaciar() {
		this.raiz = null;
	}

	/**
	 * Metodo para saber la altura de un arbol, para mas detalles del metodo ver el metodo <b>getAltura</b> en la clase {@link TNodoAB}
	 * @return unNumero - altura del arbol
	 */
	public int getAltura() {
		int altura = 0;
		if (getRaiz() != null)
			altura = getRaiz().getAltura();
		return altura;
	}

	/**
	 * Devuelve un array con las claves de los elementos del arbol ordenados en preorden
	 * Para mas informacion del metodo referirse al metodo <b>preOrden</b> de {@link TNodoAB}
	 * @return array de Comparables - array cargado con el preorden del arbol
	 */
	@SuppressWarnings("unchecked")
	public Comparable[] preOrden() {
		Comparable[] salida = new Comparable[0];
		if (!vacio()) {
			salida = new Comparable[getTamanio()];
			salida = getRaiz().preOrden();
		}
		return salida;
	}

	/**
	 * Devuelve un array con las claves de los elementos del arbol ordenados en postorden
	 * Para mas informacion del metodo referirse al metodo <b>postOrden</b> de {@link TNodoAB}
	 * @return array de Comparables - array cargado con el postorden del arbol
	 */
	@SuppressWarnings("unchecked")
	public Comparable[] postOrden() {
		Comparable[] salida = new Comparable[0];
		if (!vacio()) {
			salida = new Comparable[getTamanio()];
			salida = getRaiz().postOrden();
		}
		return salida;
	}
	
	/**
	 * Devuelve un array con las claves de los elementos del arbol ordenados en inorden
	 * Para mas informacion del metodo referirse al metodo <b>inOrden</b> de {@link TNodoAB}
	 * @return array de Comparables - array cargado con el inorden del arbol
	 */
	@SuppressWarnings("unchecked")
	public Comparable[] inOrden() {
		Comparable[] salida = new Comparable[0];
		if (!vacio()) {
			salida = new Comparable[getTamanio()];
			salida = getRaiz().inOrden();
		}
		return salida;
	}

	/**
	 * Metodo para saber si dos arboles son similares
	 * Dos arboles son similares si sus estructuras son iguales
	 * Para ver mas informacion sobre el funcionamiento del algoritmo de similar ver la documentacion del metodo <b>similar</b> de la clase {@link TNodoAB}
	 * @param arbolSimilar - arbol con que se quiere realizar la comparación
	 * @return true - si los arboles son similares; false - si los arboles no son similares
	 */
	public boolean similar(TArbol arbolSimilar) {
		boolean salida = false;
		if(!this.vacio() && !arbolSimilar.vacio())
			salida = getRaiz().similar(arbolSimilar.raiz);
		
			return salida;
	}

	/**
	 * Metodo para saber si dos arboles son equivalentes
	 * Dos arboles son similares si sus estructuras son iguales y sus nodos son iguales (misma clave y mismo elemento)
	 * Para ver mas informacion sobre el funcionamiento del algoritmo de similar ver la documentacion del metodo <b> equivalente </b> de la clase {@link TNodoAB}
	 * @param arbolEquivalente - arbol con el que se quiere realizar la comparación
	 * @return true - si los arboles son equivlentes; false - si los arboles no son equivalentes
	 */
	public boolean equivalente(TArbol arbolEquivalente) {
		boolean salida = false;
		if(!this.vacio() && !arbolEquivalente.vacio())
			salida = getRaiz().equivalente(arbolEquivalente.raiz);
		
			return salida;
	}

}
