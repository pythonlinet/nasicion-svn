package uy.edu.ucu.pii.obligatorio1.grupo14.datos.grafo;

import java.util.Vector;

import uy.edu.ucu.pii.obligatorio1.grupo14.datos.lista.TLista;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.lista.TNodo;

/**
 * <b>Clase para la implementacion de Grafos. Diseniada y Desarrollada por el Grupo14 para la materia Porgarmacion II de la Universidad Catolica del Uruguay Anio 2009</b>
 * 
 * @author <i>Grupo14</i>
 * @version <i>1.0</i>
 * @see uy.edu.ucu.pii.obligatorio1.grupo14.datos.grafo.TVertice
 * @see uy.edu.ucu.pii.obligatorio1.grupo14.datos.grafo.TArista
 */
public class TGrafo {
	@SuppressWarnings("unchecked")
	private static final Comparable INFINITO = null;

	private TLista vertices;

	@SuppressWarnings("unchecked")
	private Comparable[][] mAdyacencia;
	@SuppressWarnings("unchecked")
	private Comparable[][] mFloyd;
	
	/**
	 * Indica a varios metodos si es necesario regenerar las matrices
	 */
	private boolean regenMatriz;
	/**
	 * Indica la cantidad de vertices del grafo, es utilizado tambi�n para
	 * setear a los vertices la posici�n que van a tener dentro de la matriz
	 */
	private int cantVertices = 0;

	public int getCantVertices() {
		return cantVertices;
	}

	private void setCantVertices(int cantVertices) {
		this.cantVertices = cantVertices;
	}

	public Comparable[][] getMFloyd() {
		if(mFloyd == null)
			implementacionFloyd();
		return mFloyd;
	}

	public TGrafo() {
		this.vertices = new TLista();
		this.cantVertices = 0;
		this.regenMatriz = true;

	}

	/**
	 * Metodo para insertar un nuevo vertice en el grafo
	 * 
	 * @param etiqueta
	 * @return true - si el vertice se inserto correctamente; false - si el
	 *         vertice ya existe
	 */
	@SuppressWarnings("unchecked")
	public boolean insertarVertice(Comparable etiqueta) {
		// El metodo insertar de TLista no permite insercion de datos
		// duplicados, por lo tanto no es necesario verificar que el elemento no
		// exista
		boolean salida = vertices.insertar(etiqueta, new TVertice(etiqueta,
				cantVertices));
		// Si se realiza la insercion aumentamos la cantidad de vertices en el
		// grafo
		setCantVertices(salida ? getCantVertices() + 1 : getCantVertices());
		// Si se realizo la insercion marcamos que la matriz tiene que ser
		// regenerada la proxima vez que se la quiera accesar
		if (salida) {
			this.regenMatriz = salida;
		}

		return salida;

	}

	/**
	 * Metodo para agregar una adyacencia
	 * 
	 * @param origen
	 *            vertice de origen
	 * @param destino
	 *            vertice de destino
	 * @param costo
	 *            costo desde el origen hasta el destino
	 * @return true - si se pudo agregar la adyacencia; false - si ya existia
	 *         una adyacencia o si uno de los
	 */
	@SuppressWarnings("unchecked")
	public boolean insertarAdyacencia(Comparable origen, Comparable destino,
			Comparable costo) {
		boolean salida = false;

		TNodo nodoOrigen = vertices.buscarNodo(origen);
		// Si el nodo de origen existe
		if (nodoOrigen != null) {
			TNodo nodoDestino = vertices.buscarNodo(destino);
			// Si el nodo destino existe
			if (nodoDestino != null) {
				// Demasiados casteos?? :P
				// No es necesario hacer un control de si ya existe la
				// adyacencia porque el insertar de la lista ya lo hace
				salida = ((TVertice) nodoOrigen.getElemento())
						.ingresarAdyacencia((TVertice) nodoDestino
								.getElemento(), costo);
				// Si se realizo la insercion de adyacencia marcamos que la
				// matriz tiene que ser regenerada la proxima vez que se la
				// quiera accesar
				if (salida) {
					this.regenMatriz = salida;
				}
			}
		}
		return salida;
	}

	/**
	 * Utiliza el algoritmo de Floyd, tambien se podria haber hecho usando
	 * el algoritmo de Warshall, pero como ambos algoritmos son similares
	 * se considero que no era necesario desarrollar tambien el algoritmo antes mencionado 
	 * 
	 * @param etiquetaOrigen identificador del vertice de origen
	 * @param etiquetaDestino identificador del vertice de destino
	 * @return true - si existe un camino desde el origen hasta el destino;
	 *         false - si no existe un camino hasta el destino
	 */
	@SuppressWarnings("unchecked")
	public boolean existeCamino(Comparable etiquetaOrigen,
			Comparable etiquetaDestino) {
		if (regenMatriz)
			implementacionFloyd();
		int origen = getPosMatriz(etiquetaOrigen);
		int destino = getPosMatriz(etiquetaDestino);
		return mFloyd[origen][destino] != INFINITO ? true : false;
	}

	
	/**
	 * Metodo para saber el camino mas corto dado un nodo de origen y un nodo de desitno
	 * @param etiquetaOrigen nombre o identificador del punto de origen
	 * @param etiquetaDestino nombre o identificador del punto de llegada
	 * @return null - si no existen alguno de los vertices o si no existe el camino; Comparable[] - lista ordenada de los nodos que forman el camino
	 */
	@SuppressWarnings("unchecked")
	public Comparable[] mejorCamino(Comparable etiquetaOrigen,	Comparable etiquetaDestino) {
		Comparable[] salida = null;
		//Buscamos que exista el ORIGEN
		TNodo existeO = vertices.buscarNodo(etiquetaOrigen);
		if(existeO != null){
			//Buscamos que exista el DESTINO
			TNodo existeD = vertices.buscarNodo(etiquetaDestino);
			if(existeD != null){
				//Obtenemos la lista de predecesores
				Comparable[] arrayPredecesores = implementacionDijkstra(etiquetaOrigen, true);
				
				//Obtenemos la posicion que tiene el destino dentro del array
				int posicionDestino = ((TVertice)existeD.getElemento()).getPosMatriz();
				
				//Verificamos que exista un camino hasta el destino, si lo hay procedemos a armarlo
				if(arrayPredecesores[posicionDestino] != INFINITO){
					String camino;
					//Inserto el final del camino
					camino = (String) etiquetaDestino;
					camino = arrayPredecesores[posicionDestino]+"," + camino;
					posicionDestino = ((TVertice) vertices.buscarNodo(arrayPredecesores[posicionDestino]).getElemento()).getPosMatriz();
					TVertice aux;
					while(arrayPredecesores[posicionDestino].compareTo(etiquetaOrigen) != 0){
						aux = (TVertice) vertices.buscarNodo(arrayPredecesores[posicionDestino]).getElemento();
						camino = arrayPredecesores[posicionDestino]+"," + camino;
						posicionDestino = ((TVertice) vertices.buscarNodo(arrayPredecesores[posicionDestino]).getElemento()).getPosMatriz();
					}
					
					//Agrego por ultimo el origen
					camino = etiquetaOrigen+"," + camino;
	
					salida = camino.split(",");
				}
				
			}
		}
		
		return salida;
	}

	/**
	 * Metodo que retorna el vertice que sea el centro del grafo
	 * 
	 * @return Etiqueta del centro del grafo
	 */
	@SuppressWarnings("unchecked")
	public Object centroDelGrafo() {

		int pos = 0;
		Comparable[] excentricidad = excentricidad();
		Comparable aux = excentricidad[0];

		// Recorro el array de excentricidad del Grafo buscando la menor de las
		// excentricidades, que por definicion deberia se el centro del grafo
		for (int i = 1; i < excentricidad.length; i++) {
			/*
			 * Si aux es INFINITO y excentricidad[i] es != de INFINITO,
			 * asignamos a aux el valor de excentricidad[i], este control se
			 * hace porque en el caso de que aux sea INIFINITO (null), al querer
			 * comparar el programa dara NullPointerException
			 */
			if (aux == INFINITO && excentricidad[i] != INFINITO) {
				aux = excentricidad[i];
				// Guardamos la posicion del vertice
				pos = i;
			}
			/*
			 * Aqui hacemos el mismo control que en el IF anterior por la misma
			 * razon, si excentricidad[i] es nulo (INFINITO), se cae por
			 * NullPointerException
			 */
			else if (excentricidad[i] != INFINITO
					&& aux.compareTo(excentricidad[i]) > 0) {
				aux = excentricidad[i];
				// guardo la posicion del elemento en el array
				pos = i;
			}
		}
		// Devolvemos la etiqueta del vertice
		return vertices.recuperar(pos).getClave();
	}

	
	/**
	 * Metodo para obtener la excentricidad del grafo
	 * 
	 * @return array de Comparable
	 */
	@SuppressWarnings("unchecked")
	public Comparable[] excentricidad() {
		if (regenMatriz)
			implementacionFloyd();

		Comparable[] salida = new Comparable[mFloyd.length];

		boolean continuar = true;
		Comparable aux;
		for (int i = 0; i < mFloyd.length; i++) {
			aux = 0;

			for (int j = 0; j < mFloyd.length && continuar; j++) {

				// Si el costo es infinito (null), seteamos aux como INFINITO,
				// porque no va a haber un costo mayor a ese
				if (mFloyd[j][i] == INFINITO) {
					aux = INFINITO;
					// Cortamos la ejecucion ya que no va a haber un valor mayor
					// que INFINITO
					continuar = false;
				} else if (mFloyd[j][i].compareTo(aux) > 0)
					aux = mFloyd[j][i];

			}
			// Volemos a setear continuar en true para que se ejecute bien el
			// for
			continuar = true;
			salida[i] = aux;
		}
		return salida;
	}

	/**
	 * Metodo para saber si existe un vertice con una X etiqueta
	 * 
	 * @param etiqueta
	 *            etiqueta del vertices
	 * @return true - si el vertice existe en el grafo; false - si el vertice no
	 *         existe en el grafo
	 */
	public boolean existeVertice(String etiqueta) {
		return vertices.buscarNodo(etiqueta) == null ? false : true;
	}

	/**
	 * Metodo para saber si dos vectores ORIGEN y DESTINO son adyacentes
	 * 
	 * @param etiquetaOrigen
	 *            etiqueta del nodo origen
	 * @param etiquetaDestino
	 *            etiqueta del nodo destino
	 * @return true - si existe la adyacencia; false - si no existe una
	 *         adyacencia entre ambos vertices
	 */
	public boolean existeAdyacencia(String etiquetaOrigen,
			String etiquetaDestino) {
		boolean salida = false;
		TNodo origen = vertices.buscarNodo(etiquetaOrigen);
		// Si existe el origen
		if (origen != null) {
			salida = ((TVertice) origen.getElemento())
					.existeAdyacencia(etiquetaDestino);
		}
		return salida;
	}

	/**
	 * Inicializa la matriz de adyacencia
	 */
	private void inicializarMatriz() {
		int tamanio = vertices.getTamanio();
		this.mAdyacencia = new Comparable[tamanio][tamanio];
	}

	/**
	 * Carga la matriz de adyacencia Para el caso que sea desde en Vertice X
	 * hacia el mismo el costo sera 0 Cuando no haya una adyacencia se dejara el
	 * valor null y se lo manejara como si fuera INFINITO, es decir que no hay
	 * adyacencia entre ambos vertices
	 */
	private void cargarMatrizDeAdyacencia() {
		inicializarMatriz();
		int cantVertices = vertices.getTamanio();
		TVertice vertice;
		// Usado para guardar las aristas
		TArista adyacencia;
		int cantAdyacencias;
		for (int i = 0; i < cantVertices; i++) {
			vertice = (TVertice) vertices.recuperar(i).getElemento();
			// Cantida de adyacencias del vertice
			cantAdyacencias = vertice.getAdyacentes().getTamanio();

			// Cuando sea el costo de un vertice x hacia el mismo, seteamos el
			// costo en 0
			mAdyacencia[i][i] = 0;

			// Recorremos las adyacencias del vertice
			for (int j = 0; j < cantAdyacencias; j++) {

				// Guardo la referencia a la arista
				adyacencia = ((TArista) vertice.getAdyacentes().recuperar(j)
						.getElemento());
				mAdyacencia[i][adyacencia.getDestino().getPosMatriz()] = adyacencia.getCosto();
			}
		}
		this.regenMatriz = false;
	}

	/**
	 * Metodo para saber la posicion que tiene un vertice en la matriz
	 * 
	 * @param etiqueta
	 *            etiqueta del vertice del que se quiere saber la posicion
	 * @return unNumero - la posici�n del vertice dentro de la matriz de
	 *         adyacencia; -1 - si el vertice no existe
	 */
	@SuppressWarnings("unchecked")
	private int getPosMatriz(Comparable etiqueta) {
		int salida = -1;
		// Buscamos el vertice
		TVertice vertice = (TVertice) vertices.buscarNodo(etiqueta)
				.getElemento();
		// Si el vertice existe guardamos la posici�n que tiene en la matriz
		salida = vertice != null ? vertice.getPosMatriz() : salida;

		return salida;
	}

	/**
	 * Implementacion del algoritmo de Floyd
	 * 
	 * @return Comparable[][] - con los costos minimos de ir desde un Origen
	 *         hasta un Destino
	 */
	@SuppressWarnings("unchecked")
	private Comparable[][] implementacionFloyd() {
		// Si la matriz fue marcada para regeneracion se la genera otra vez
		if (regenMatriz)
			cargarMatrizDeAdyacencia();

		Comparable[][] salida = new Comparable[this.mAdyacencia.length][this.mAdyacencia.length];
		for(int i = 0; i < salida.length; i++)
			for(int j = 0; j < salida.length; j++)
				salida[i][j] = mAdyacencia[i][j];
		
		
		
		Integer aIJ;
		Integer aIK;
		Integer aKJ;

		for (int k = 0; k < salida.length; k++) {
			for (int i = 0; i < salida.length; i++) {
				for (int j = 0; j < salida.length; j++) {
					// Si ninguno de los dos es infinito(NULL)
					if (salida[i][k] != INFINITO && salida[k][j] != INFINITO) {

						aIK = (Integer) salida[i][k];
						aKJ = (Integer) salida[k][j];
						// Si el costo inicial no es INFINITO
						if (salida[i][j] != INFINITO) {
							// Para poder realizar la comparacion o la suma de
							// los costos se tiene que pasar las variables de
							// Comparable a otro Objeto que soporte estas
							// operaciones
							aIJ = (Integer) salida[i][j];
							if (aIJ > aIK + aKJ) {
								salida[i][j] = aIK + aKJ;
							}
						} else {
							// Si resulta que el costo inicial era INFINITO tomo
							// el costo de ir de I hasta K y luego desde K hasta
							// el destino J
							salida[i][j] = aIK + aKJ;
						}
					}
				}
			}
		}

		this.mFloyd = salida;
		return salida;
	}

	/**
	 * Implementacion del algoritmo de Dijkstra que devuelve los costos desde un
	 * nodo origen hasta el resto de los vertices del Grafo o si se pasa el valor TRUE en "retornarCaminos" este devolvera 
	 * la lista de precedencia de los nodos
	 * @param origen
	 *            etiqueta del vertice de origen
	 * @return array de Comparable - los costos desde el vertice origen hasta
	 *         los demas vertices; null - si el nodo origen no existe
	 */
	public Comparable[] implementacionDijkstra(Comparable origen, boolean retornarCaminos) {
		Comparable[] d = null;
		TNodo existeOrigen = vertices.buscarNodo(origen);
		// Compruebo que exista el origen
		if (existeOrigen != null) {
			TVertice vOrigen = (TVertice) existeOrigen.getElemento();

			/*
			 * Si la matriz de adyacencia no existe o esta marcada para
			 * regeneracion la generamos
			 */
			if (regenMatriz)
				cargarMatrizDeAdyacencia();
			// Cargamos D con los valores iniciales
			d = mAdyacencia[vOrigen.getPosMatriz()];
			
			// Posicion del ORIGEN dentro de la matriz de adyacencia
			int x = vOrigen.getPosMatriz();

			
			//Inicializamos el array de predecesores
			Comparable[] p = new Comparable[vertices.getTamanio()];
			for(int i = 0; i < p.length; i++)
				p[i] = origen;
			p[x] = origen;
			
			// Vertice Actual
			TVertice w = null;

			// Variables usadas a la hora de buscar el w minimo
			Comparable distACTUAL, distAUX;
			TVertice v;

			// Variables usadas en la busqueda de caminos mas cortos
			Integer sumaDeCostos = null;
			boolean costosValidos;

			// Lista de vertices
			TLista conjuntoV = new TLista();

			// Cargo la lista de vertices v
			for (Comparable etiquetaV : vertices.mostrar())
				conjuntoV.insertar(etiquetaV, vertices.buscarNodo(etiquetaV).getElemento());

			// Quito el vertice origen
			conjuntoV.eliminar(origen);

			// Comienza el algoritmo de Dijkstra

			while (conjuntoV.getPrimero() != null) {

				// Ahora hay que elegir el vertice mas cerca de X(Origen)
				// Distancia minima actual es la del primer elemento del
				// conjunto V de vertices
				w = ((TVertice) conjuntoV.recuperar(0).getElemento());
				distACTUAL = d[w.getPosMatriz()];

				for (int i = 1; i < conjuntoV.getTamanio(); i++) {
					// Guardo el vertice de V sobre el que estoy parado
					v = ((TVertice) conjuntoV.recuperar(i).getElemento());
					distAUX = d[v.getPosMatriz()];

					// Si distACTUAL es INFINITO, seteo distACTUAL como distAUX
					// ya que no puede ser peor
					if (distACTUAL == INFINITO) {
						distACTUAL = distAUX;
					} else if (distAUX != INFINITO	&& distACTUAL.compareTo(distAUX) > 0) {
						distACTUAL = distAUX;
						// Guardo el vertice acutal
						w = v;
					}
				}

				// Elimino el vertice elegido del conjunto V
				conjuntoV.eliminar(w.getEtiqueta());

				for (int i = 0; i < conjuntoV.getTamanio(); i++) {
					v = (TVertice) conjuntoV.recuperar(i).getElemento();

					// Permite saber si los costos de D[v] y C[w,v] son
					// validos(no son infinitos), para asi poder sumarlos
					costosValidos = (d[w.getPosMatriz()] != INFINITO) && 
									(mAdyacencia[w.getPosMatriz()][v.getPosMatriz()] != INFINITO);

					if (costosValidos)
						// Se hace el casteo porque sino no puede realizarse la
						// suma de los costos ya que Comparable no soporta esa
						// operacion
						sumaDeCostos = (Integer) d[w.getPosMatriz()] + (Integer) mAdyacencia[w.getPosMatriz()][v.getPosMatriz()];

					//Si la la distancia al vertice es inifito
					if (d[v.getPosMatriz()] == INFINITO) {
						//Y los costos son validos
						if (costosValidos) {

							d[v.getPosMatriz()] = sumaDeCostos;
							//Parche
							//Se guarda el predecesor del vetice
							p[v.getPosMatriz()] =  w.getEtiqueta();
						}
						// Si el costo actual no es INFINITO y los costos de
						// D[w] y C[w,v] no son INFINITOS ninguno de los dos
					} else if (costosValidos) {
						boolean esMenor = d[v.getPosMatriz()].compareTo(sumaDeCostos) > 0;
						// Si la suma de costos es menor que el costo ya
						// ingresado, nos quedamos con la suma de costos
						
						d[v.getPosMatriz()] =  esMenor? sumaDeCostos:d[v.getPosMatriz()];
						
						//Si era una arista valida y menor guardamos el predecesor de ese vertice
						if(esMenor){
							p[v.getPosMatriz()] =  w.getEtiqueta();
							
						}
						
					}

				}
			}
			if(retornarCaminos){
				d = p;
			}
		}
		
		return d;
	}

	/**
	 * Metodo auxiliar de imprimirMatrizAdyacente y imprimirMatrizFloyd
	 * 
	 * @param matriz
	 *            - matriz que se quiere imprimir en consola
	 */
	private void imprimirMatrizGrafo(Comparable[][] matriz) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				System.out.print(matriz[i][j] + "\t");
			}
			System.out.println();
		}
	}

	/**
	 * Metodo usado para mostrar en consola la matriz de adyacencia
	 */
	public void imprimirMatrizAdyacente() {
		if (regenMatriz)
			cargarMatrizDeAdyacencia();
		imprimirMatrizGrafo(mAdyacencia);
	}

	/**
	 * Metodo para mostrar por consola la matriz resultante de aplicar Floyd al
	 * grafo
	 */
	public void imprimirMatrizFloyd() {
		implementacionFloyd();

		imprimirMatrizGrafo(mFloyd);
	}
}
