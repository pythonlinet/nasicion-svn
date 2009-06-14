package uy.edu.ucu.pii.obligatorio2.grupo14.datos.lista;

/**
 * Implementacion de una array redimenzionable, escrito por Guillermo Nasi para
 * la materia Programaci�n 2 en la Universidad Catolica del Uruguay. Implementa
 * todas los comportamientos tipicos de una lista y se agregaron otros metodos
 * para darle mas funcionalidad a la lista. En proximas versiones se intentara
 * mejorar el algoritmo de ordenamiento entre otros para hacerlo mas eficiente.
 * 
 * @author Guillermo Nasi
 * @version 1.0
 */
public class TLista {
	private TNodo primero;
	private Integer tamanio;

	/*
	 * Inicio del bloque de getters y setters
	 */
	public TNodo getPrimero() {
		return primero;
	}

	@SuppressWarnings("unused")
	protected void setPrimero(TNodo primero) {
		this.primero = primero;
	}

	public TNodo getUltimo() {
		return recuperar(this.tamanio - 1);
	}

	public Integer getTamanio() {
		return this.tamanio;
	}

	protected void setTamanio(int tamanio){
		this.tamanio = tamanio; 
	}
	
	/*
	 * Fin del bloque de getters y setters
	 */
	/**
	 * Construye un TLista vacio
	 */
	public TLista() {
		this.tamanio = 0;
		setPrimero(null);
	}

	/**
	 * Inserta un nuevo elemento/nodo al final de la lista
	 * 
	 * @param clave clave del elemnto
	 * @param elemento objeto a insertar
	 * @return true - si se inserto un elemento; false - si no pudo realizarse la inserci�n.
	 */
	@SuppressWarnings("unchecked")
	public boolean insertar(Comparable clave, Object elemento) {
		TNodo nuevoNodo = new TNodo(clave, elemento);
		boolean salida = false;
		if (buscarNodo(clave) == null) {
			if (esVacia()) {
				this.primero = nuevoNodo;
				this.tamanio += 1;
				salida = true;
			} else {
				// Agrego el nuevo nodo
				getUltimo().setSiguiente(nuevoNodo);
				this.tamanio += 1;
				salida = true;
			}
		}
		return salida;
	}

	/**
	 * Inserta un nuevo elemento/nodo de forma ordenada en la lista, para esto
	 * recorre la lista hasta encontrar una posicion donde el siguiente sea
	 * mayor que al nodo a insertar y se menor al anterior.
	 *  
	 * @param clave clave del elemnto
	 * @param elemento objeto a insertar
	 * @return true - si se inserto un elemento; false - si no pudo realizarse la inserci�n.
	 */
	@SuppressWarnings("unchecked")
	public boolean insertarOrdenado(Comparable clave, Object elemento) {
		boolean salida = false;

		if (!esVacia()) {
			// Verificamos que no exista ya un elemento con esa clave
			TNodo aux = buscarNodo(clave);
			if (aux == null) {
				aux = new TNodo(clave, elemento);
				TNodo puntero = getPrimero();

				do {
					// Comprobamos si aux es menor que el puntero
					if (puntero.getClave().compareTo(clave) > 0) {
						if (puntero.equals(getPrimero())) {
							insertarPrimero(aux.getClave(), aux.getElemento());
							setTamanio(getTamanio()-1);
							salida = true;
						}
					} else {
						if (puntero.getSiguiente() == null) {
							puntero.setSiguiente(aux);
							salida = true;
						} else if (puntero.getSiguiente().getClave().compareTo(clave) > 0) {
							aux.setSiguiente(puntero.getSiguiente());
							puntero.setSiguiente(aux);
							salida = true;
						}
					}
					puntero = puntero.getSiguiente();
				} while (!salida);

			}
		} else {
			setPrimero(new TNodo(clave, elemento));
			salida = true;
		}
		// Si se realizo una insercion incrementamos el tamaño de la lista
		setTamanio(getTamanio() +(salida ? 1 : 0));
		return salida;
	}

	/**
	 * Inserta un nuevo elemento/nodo al principio de la lista
	 *  
	 * @param clave clave del nuevo nodo
	 * @param elemento objeto o elemento del nodo
	 * @return true - si se realizo la insercio; false - si no pudo insertarse el nuevo elemento;
	 */
	@SuppressWarnings("unchecked")
	public boolean insertarPrimero(Comparable clave, Object elemento) {
		boolean salida = false;
		if (!esVacia()) {
			if (buscarNodo(clave) == null) {
				TNodo nNodo = new TNodo(clave, elemento);
				nNodo.setSiguiente(getPrimero());
				setPrimero(nNodo);
				salida = true;
			}
		} else {
			//this.primero = new TNodo(clave, elemento);
			setPrimero(new TNodo(clave, elemento));
			salida = true;
		}
		//this.tamanio += salida?1:0;
		setTamanio(getTamanio() +(salida?1:0));
		return salida;
	}

	/**
	 * Metodo que devuelve el nodo ubicado en la posicion indicada.
	 * Las posiciones van desde el 0 hasta n, donde n es el tamanio de la lista - 1.
	 *   
	 * @param pos posicion del elemento que deseamos
	 * @return nodo el nodo en la posicion indicada; null - si no existe un nodo en esa posici�n, si la posici�n es invalida o si la lista esta vacia.
	 */
	public TNodo recuperar(Integer pos) {
		TNodo salida = null;
		if (!esVacia()) {
			/*
			 * Controlamos que la posicion sea mayor que cero que no sea mayor
			 * que el tama�o de la lista
			 */
			if (pos >= 0 && pos < getTamanio()) {
				if (!esVacia()) {
					/*
					 * Cargamos en la salida el primer nodo
					 */
					int i = 0;
					salida = getPrimero();
					/*
					 * Si la posicion es mayor que 0, empezamos a recorrer la
					 * lista, si la posicion es 0 significa que se trata del
					 * primer elemento.
					 */
					while (i < pos) {
						salida = salida.getSiguiente();
						i++;
					}
				}
			}
		}
		return salida;
	}

	/**
	 * Este metodo busca un nodo en la lista apartir de su clave, para hacerlo recorre la lista desde el comienzo hasta llegar al nodo con la clave que se le paso como parametro
	 *  
	 * @param clave  clave del nodo que se quiere obtener
	 * @return nodo - el nodo encotrado; null - si no existe el nodo con dicha clave o la lista esta vac�a
	 */
	@SuppressWarnings("unchecked")
	public TNodo buscarNodo(Comparable clave) {
		if (!esVacia()) {
			TNodo aux = getPrimero();
			do {
				if (aux.getClave().compareTo(clave) == 0)
					//TODO Esto no deberia hacerse, va contra buenas practicas de programacion
					return aux;
				aux = aux.getSiguiente();
			} while (aux != null);
		}
		return null;
	}

	/**
	 * Metodo booleano que retorna TRUE si la lista est� vacia y FALSE si las
	 * lista tiene al menos un elemento
	 * 
	 * @return true - si la lista esta vacia; false - si la lista contiene al menos un elemento.
	 */
	public boolean esVacia() {
		if (getPrimero() != null)
			return false;
		return true;
	}

	/**
	 * Metodo para saber cual nodo es el anterior a otro en base a su clave Se recorre la lista hasta llegar al nodo en el cual la clave del siguiente nodo a el sea la clave proporcionada
	 *   
	 * @param clave la clave del nodo que se quiere saber el anterior
	 * @return nodo - el nodo que antescede al que tiene la clave que se le pasa al metodo; null - si la clave corresponde al primer elemento de la lista
	 */
	@SuppressWarnings("unchecked")
	public TNodo anterior(Comparable clave) {
		TNodo salida = null;

		if (this.primero.getClave() != clave) {
			TNodo aux = getPrimero();
			do {
				if (aux.getSiguiente().getClave().compareTo(clave) == 0)
					salida = aux;
				else
					aux = aux.getSiguiente();
			} while (aux != null && salida == null);
		}

		return salida;
	}

	/**
	 * Metodo para eliminar un elemento/nodo de la lista.
	 *  Para eliminar el nodo primero se checkea que este se encuentre en la lista luego, lo unico que debe hacerse es tomar el anterior al nodo que se desea eliminar y setear su siguiente nodo como el nodo siguiente al cual se quiere eliminar.
	 *  
	 * @param clave - clave del nodo que se quiere eliminar
	 * @return true - si el nodo se elimino satisfactoriamente
	 */
	@SuppressWarnings("unchecked")
	public boolean eliminar(Comparable clave) {
		boolean salida = true;
		TNodo aEliminar = buscarNodo(clave);
		if (aEliminar == null) {
			salida = false;
		} else {
			TNodo anterior = anterior(clave);
			if(anterior != null)
				anterior.setSiguiente(aEliminar.getSiguiente());
			else
				//this.primero = aEliminar.getSiguiente();
				setPrimero(aEliminar.getSiguiente());
			
			//this.tamanio -= 1;
			setTamanio(getTamanio() -1);
			salida = true;
		}

		return salida;
	}

	/**
	 * Metodo para invertir el orden de la lista. para invertir la lista tomamos el primer elemento y su siguiente y los guardamos en dos variables que llamaremos aux y siguiente, seteamos el atributo primero de la lista como NULL para dejar la lista vacia, entonces, usando el metodo insertarPrimero insertamos el nodo que guardamos como el primero de la lista y seteamos la variable guardamos en auxiliar la referencia de siguiente y en siguiente el nodo siguiete a este. Este proceso se repite hasta que que auxiliar tenga valor nulo.
	 *  
	 *@return array de Comparable - si la lista fue invertida con exito; null - si la lista esta vac�a.
	 */
	@SuppressWarnings("unchecked")
	public Comparable[] invertir() {
		Comparable[] lista = null;
		if (!esVacia()) {
			// Creamos una array del tamaño de la lista
			lista = new Comparable[tamanio];
			// Guardamos en una varialbe auxiliar el primero
			TNodo aux = getPrimero();
			// Y en una el siguiente
			TNodo siguiente = aux.getSiguiente();
			// Borramos la referencia a primero para dejar la lista vacia
			//primero = null;
			setPrimero(null);
			//this.tamanio = 0;
			setTamanio(0);
			// ahora mientras que la auxiliar no sea nula recorremos la lista
			while (aux != null) {
				// Insertamos al principio de la lista los nodos
				insertarPrimero(aux.getClave(), aux.getElemento());
				aux = siguiente;
				/*
				 * controlamos que aux no sea nulo porque si intentamos obtener
				 * el siguiente a un elemento nulo Java retornara una
				 * NullPointerException
				 */
				if (aux != null)
					siguiente = aux.getSiguiente();
			}
			lista = mostrar();
		}
		return lista;
	}

	/**
	 * Metodo que retorna una array de elementos Comparable que contiene las claves de los elementos de la lista en el orden en que se encuentran actualmente.
	 *  
	 * @return array de Comparable - con las claves de los elementos en el orden en que se encuentra en la lista; null - si la lista esta vac�a.
	 */
	@SuppressWarnings("unchecked")
	public Comparable[] mostrar() {
		Comparable[] lista = null;
		if (!esVacia()) {
			lista = new Comparable[getTamanio()];
			for (int i = 0; i < getTamanio(); i++) {
				lista[i] = recuperar(i).getClave();
			}
		}
		return lista;
	}

	/**
	 * Metodo que retorna el Nodo con la clave mayor
	 *  
	 * @return nodo - nodo con la clave mas alta de la lista; null - si la lista esta vac�a
	 */
	@SuppressWarnings("unchecked")
	public TNodo getMax() {
		TNodo max = null;
		if (!esVacia()) {
			max = getPrimero();
			for (int i = 0; i < getTamanio(); i++) {
				max = max.getClave().compareTo(recuperar(i).getClave()) < 0 ? recuperar(i) : max;
			}
		}
		return max;
	}

	/**
	 * Metodo que retorna el Nodo con la clave menor
	 *  
	 * @return nodo - nodo con la clave mas baja de la lista; null - si la lista esta vac�a.
	 */
	@SuppressWarnings("unchecked")
	public TNodo getMin() {
		TNodo min = null;
		if (!esVacia()) {
			min = getPrimero();
			for (int i = 0; i < getTamanio(); i++) {
				min = min.getClave().compareTo(recuperar(i).getClave()) > 0 ? recuperar(i)
						: min;
			}
		}
		return min;
	}

	/**
	 * Intercambia los nodos en dos posiciones distintas
	 * 
	 * @param i  posicion del primer nodo 
	 * @param j  posicion del segundo nodo
	 */
	public void swapNodes(int i, int j) {
		if (!esVacia() && i < getTamanio() && j < getTamanio()) {
			TNodo nodoA = recuperar(i);
			TNodo nodoB = recuperar(j);
			TNodo aux = anterior(nodoB.getClave());

			if (anterior(nodoA.getClave()) != null)
				anterior(nodoA.getClave()).setSiguiente(nodoB);
			else
				//this.primero = nodoB;
				setPrimero(nodoB);

			aux.setSiguiente(nodoA);

			aux = nodoB.getSiguiente();
			nodoB.setSiguiente(nodoA.getSiguiente());
			nodoA.setSiguiente(aux);

		}

	}

	/**
	 * Metodo que ordena los elementos de la lista de menor a mayor
	 *  
	 * @return array de Comparable  con las claves de los elementos en el orden en que se encuentra en la lista ahora ordenada; null - si la lista esta vac�a.
	 */
	@SuppressWarnings("unchecked")
	public Comparable[] ordenar() {
		TNodo aux;

		for (int i = getTamanio() - 1; i >= 0; i--) {
			for (int k = getTamanio() - 1; k >= 0; k--) {
				if (recuperar(i).getClave().compareTo(recuperar(k).getClave()) > 0)
					swapNodes(i, k);
			}
		}
		return mostrar();
	}

	/*
	 * Metodos usados para el quicksort
	 */

	/*
	 * este metodo se encarga de encotrar un pivot dentro de la lista para
	 * usarlo en el metodo de ordenamiento quicksort para hacerlo se toma el
	 * primer elemento, el ultimo elemento y el elemento del medio y se comparan
	 * sus claves y se devuelve el que tenga un valor en medio
	 */
	@SuppressWarnings("unchecked")
	private int findPivot(int i, int j) {
		int pivot = -1;
		int medio = Math.round(((j - i) / 2));
		Comparable keyInicio = recuperar(i).getClave();
		Comparable keyMedio = recuperar(medio).getClave();
		Comparable keyFin = recuperar(j).getClave();

		if (keyInicio.compareTo(keyMedio) + keyInicio.compareTo(keyFin) == 0)
			pivot = i;
		else if (keyMedio.compareTo(keyInicio) + keyMedio.compareTo(keyFin) == 0)
			pivot = medio;
		else
			pivot = j;

		return pivot;
	}

	@SuppressWarnings("unchecked")
	private int partition(int l, int r, Comparable pivot) {
		do {
			swapNodes(l, r);
			while (recuperar(l).getClave().compareTo(pivot) < 0)
				l++;
			while (recuperar(r).getClave().compareTo(pivot) >= 0)
				r--;
		} while (l < r);
		return l;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	private Comparable[] quicksort(int i, int j) {
		Comparable pivot;
		int pivotIndex;
		int k;

		pivotIndex = findPivot(i, j);
		pivot = recuperar(pivotIndex).getClave();
		k = partition(i, j, pivot);
		quicksort(i, k - 1);
		quicksort(k, j);

		return mostrar();
	}
	/**
	 * Metodo para conocer el indice de un elemento en la lista en base a su clave
	 * @param clave
	 * @return
	 */
	public int indexOf(Comparable clave){
		int salida = -1;
		int indice = 0;
		boolean flag = true;
		TNodo temp = getPrimero();
		if(temp!=null){
			while(temp != null && flag){
				if(temp.getClave().compareTo(clave) == 0){
					flag = false;
					salida = indice;
				}else{
					++indice;
					temp = temp.getSiguiente();
				}
			}
		}
		
		
		
		return salida;
	}
}
