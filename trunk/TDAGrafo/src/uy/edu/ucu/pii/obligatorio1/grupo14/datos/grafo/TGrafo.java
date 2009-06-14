package uy.edu.ucu.pii.obligatorio1.grupo14.datos.grafo;

import uy.edu.ucu.pii.grupo14.datos.lista.TLista;
import uy.edu.ucu.pii.grupo14.datos.lista.TNodo;

/**
 * <b>Clase para la implementacion de Grafos. Diseniada y Desarrollada por el Grupo14 para la materia Porgarmacion II de la Universidad Catolica del Uruguay Anio 2009</b>
 * 
 * @author <i>Grupo14</i>
 * @version <i>1.5</i>
 * @see uy.edu.ucu.pii.obligatorio1.grupo14.datos.grafo.TVertice
 * @see uy.edu.ucu.pii.obligatorio1.grupo14.datos.grafo.TArista
 */
public class TGrafo {
	@SuppressWarnings("unchecked")
	private static final Integer INFINITO = Integer.MAX_VALUE;
	private static final Object NULO = null;

	private TLista vertices;

	@SuppressWarnings("unchecked")
	private Integer[][] mAdyacencia;
	@SuppressWarnings("unchecked")
	private Integer[][] mFloyd;
	
	/**
	 * Indica a varios metodos si es necesario regenerar las matrices
	 */
	private boolean regenMatriz;
	/**
	 * Indica la cantidad de vertices del grafo, es utilizado tambi�n para
	 * setear a los vertices la posici�n que van a tener dentro de la matriz
	 */
	private int cantVertices = 0;

	
	
	
	private TLista getVertices() {
		return vertices;
	}

	private void setVertices(TLista vertices) {
		this.vertices = vertices;
	}

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
		// duplicados, por lo tanto no es necesario verificar que 
		// el elemento no exista
		boolean salida = vertices.insertar(etiqueta, new TVertice(etiqueta));
		
		// Si se realiza la insercion aumentamos la cantidad de vertices en el grafo
		setCantVertices(salida ? getCantVertices() + 1 : getCantVertices());
		
		// Si se realizo la insercion marcamos que la matriz tiene que ser
		// regenerada la proxima vez que se la quiera accesar
		if (salida) {
			this.regenMatriz = true;
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
			Integer costo) {
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
				salida = ((TVertice) nodoOrigen.getElemento()).ingresarAdyacencia((TVertice) nodoDestino.getElemento(), costo);
				// Si se realizo la insercion de adyacencia marcamos que la matriz
				// tiene que ser regenerada la proxima vez que se la quiera accesar
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
	public boolean existeCamino(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
		boolean salida = false;
		if (regenMatriz)
			implementacionFloyd();
		
		int origen = getPosMatriz(etiquetaOrigen);
		int destino = getPosMatriz(etiquetaDestino);
		//System.err.println(etiquetaOrigen +" "+etiquetaDestino+" " +!mFloyd[origen][destino].equals(INFINITO));
		
		if(origen != -1 && destino != -1)
			salida = mFloyd[origen][destino].equals(INFINITO)? false : true; 
		

		return salida; 
	}

	
	/**
	 * Metodo para saber el camino mas corto dado un nodo de origen y un nodo de desitno
	 * @param etiquetaOrigen nombre o identificador del punto de origen
	 * @param etiquetaDestino nombre o identificador del punto de llegada
	 * @return 	null - si no existen alguno de los vertices o si no existe el camino; 
	 * 			Comparable[] - lista ordenada de los nodos que forman el camino
	 */
	@SuppressWarnings("unchecked")
	public Comparable[] mejorCamino(Comparable etiquetaOrigen,	Comparable etiquetaDestino) {
		
		Comparable[] salida = null;
		//Buscamos que exista el ORIGEN
		TNodo existeO = vertices.buscarNodo(etiquetaOrigen);
		if(existeO != null && existeCamino(etiquetaOrigen, etiquetaDestino)){
			//Buscamos que exista el DESTINO
			TNodo existeD = vertices.buscarNodo(etiquetaDestino);
			if(existeD != null){
				//Obtenemos la lista de predecesores
				Comparable[] arrayPredecesores = implementacionDijkstra(etiquetaOrigen, true);
				
				//Obtenemos la posicion que tiene el destino dentro del array
				int posicionDestino = getVertices().indexOf(existeD.getClave());
				
				//Verificamos que exista un camino hasta el destino, si lo hay procedemos a armarlo
				if(arrayPredecesores[posicionDestino] != INFINITO){
					String camino;
					//Inserto el final del camino
					camino = etiquetaDestino.toString();
					camino = arrayPredecesores[posicionDestino]+"," + camino;

					//posicionDestino = ((TVertice) vertices.buscarNodo(arrayPredecesores[posicionDestino]).getElemento()).getPosMatriz();
					posicionDestino = getVertices().indexOf(arrayPredecesores[posicionDestino]);
					
					TVertice aux;
					while(arrayPredecesores[posicionDestino].compareTo(etiquetaOrigen) != 0){
						aux = (TVertice) vertices.buscarNodo(arrayPredecesores[posicionDestino]).getElemento();
						camino = arrayPredecesores[posicionDestino]+"," + camino;
						
						//posicionDestino = ((TVertice) vertices.buscarNodo(arrayPredecesores[posicionDestino]).getElemento()).getPosMatriz();
						posicionDestino =  getVertices().indexOf(arrayPredecesores[posicionDestino]);
						
					}
					
					if(camino.split(",")[0].compareTo(etiquetaOrigen.toString()) != 0)
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
		Integer[] excentricidad = excentricidad();
		Integer aux = excentricidad[0];

		// Recorro el array de excentricidad del Grafo buscando la menor de las
		// excentricidades, que por definicion deberia se el centro del grafo
		for (int i = 1; i < excentricidad.length; i++) {
			
			//Si la excentricidad del nodo actual es menor que la guardada
			if(aux > excentricidad[i]){
				//Guardamos la nueva excentricidad
				aux = excentricidad[i];
				// Guardamos la posicion del vertice
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
	public Integer[] excentricidad() {
		if (regenMatriz)
			implementacionFloyd();

		Integer[] salida = new Integer[mFloyd.length];

		boolean continuar = true;
		Integer aux;
		for (int i = 0; i < mFloyd.length; i++) {
			aux = 0;
			for (int j = 0; j < mFloyd.length && continuar; j++) {
				// Si el costo es infinito, seteamos aux como INFINITO,
				// porque no va a haber un costo mayor a ese
				if (mFloyd[j][i] == INFINITO) {
					aux = INFINITO;
					// Cortamos la ejecucion ya que no va a haber un valor mayor
					// que INFINITO
					continuar = false;
				} else if (mFloyd[j][i] > (aux))
					aux = mFloyd[j][i];
			}
			// Volemos a setear continuar en true para que se ejecute bien el
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
	 * @return 	true - si el vertice existe en el grafo; 
	 * 			false - si el vertice no existe en el grafo
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
		this.mAdyacencia = new Integer[tamanio][tamanio];
		for(int i = 0; i < tamanio; i++)
			for(int j = 0; j < tamanio; j++)
				this.mAdyacencia[i][j] = INFINITO; 
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
				adyacencia = ((TArista) vertice.getAdyacentes().recuperar(j).getElemento());
				mAdyacencia[i][getVertices().indexOf(adyacencia.getDestino().getEtiqueta())] = adyacencia.getCosto();
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
		TVertice vertice = null;
		TNodo nodo = vertices.buscarNodo(etiqueta);
		if(nodo != null)
			vertice = (TVertice) nodo.getElemento(); 
		// Si el vertice existe guardamos la posici�n que tiene en la matriz
		salida = vertice != null ? getVertices().indexOf(vertice.getEtiqueta()) : salida;

		return salida;
	}

	/**
	 * Implementacion del algoritmo de Floyd
	 * 
	 * @return Comparable[][] - con los costos minimos de ir desde un Origen
	 *         hasta un Destino
	 */
	@SuppressWarnings("unchecked")
	private Integer[][] implementacionFloyd() {
		// Si la matriz fue marcada para regeneracion se la genera otra vez
		//if (regenMatriz)
			cargarMatrizDeAdyacencia();

		Integer[][] salida = new Integer[this.mAdyacencia.length][this.mAdyacencia.length];
		for(int i = 0; i < salida.length; i++)
			for(int j = 0; j < salida.length; j++)
				salida[i][j] = mAdyacencia[i][j];
		
		
		
		Integer aIJ, aIK, aKJ, suma;


		for (int k = 0; k < salida.length; k++) {
			for (int i = 0; i < salida.length; i++) {
				for (int j = 0; j < salida.length; j++) {
					aIJ = salida[i][j];
					
					aIK = salida[i][k];
					aKJ = salida[k][j];

					//Es necesario hacer esto porque con solo sumar 1 a infinto este toma un valor negativo acausa del  
					suma = aIK == INFINITO || aKJ == INFINITO?INFINITO:aIK + aKJ;
					
					if(aIJ > suma)
						salida[i][j] = suma;
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
//			if (regenMatriz)
				cargarMatrizDeAdyacencia();
			
			// Cargamos D con los valores iniciales
			d = mAdyacencia[getVertices().indexOf(vOrigen.getEtiqueta())];
			
			// Posicion del ORIGEN dentro de la matriz de adyacencia
			int x = getVertices().indexOf(vOrigen.getEtiqueta());

			
			//Inicializamos el array de predecesores
			Comparable[] p = new Comparable[vertices.getTamanio()];
			for(int i = 0; i < p.length; i++)
				p[i] = origen;
			p[x] = origen;
			
			// Vertice Actual
			TVertice w = null;

			// Variables usadas a la hora de buscar el w minimo
			Integer distACTUAL, distAUX;
			TVertice v;

			// Variables usadas en la busqueda de caminos mas cortos
			Integer sumaDeCostos = null;
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
				distACTUAL = (Integer)d[getVertices().indexOf(w.getEtiqueta())];

				for (int i = 1; i < conjuntoV.getTamanio(); i++) {
					// Guardo el vertice de V sobre el que estoy parado
					v = ((TVertice) conjuntoV.recuperar(i).getElemento());
					distAUX = (Integer)d[getVertices().indexOf(v.getEtiqueta())];

					if(distACTUAL > distAUX){
						// Guardo el vertice acutal
						w = v;
						distACTUAL = distAUX;
					}
				}

				// Elimino el vertice elegido del conjunto V
				conjuntoV.eliminar(w.getEtiqueta());

				for (int i = 0; i < conjuntoV.getTamanio(); i++) {
					v = (TVertice) conjuntoV.recuperar(i).getElemento();

					//TOMA PA VOS Y TU TIA GREGORIA
					sumaDeCostos = d[getVertices().indexOf(w.getEtiqueta())]==INFINITO || mAdyacencia[getVertices().indexOf(w.getEtiqueta())][getVertices().indexOf(v.getEtiqueta())] == INFINITO?INFINITO : (Integer)d[getVertices().indexOf(w.getEtiqueta())] + mAdyacencia[getVertices().indexOf(w.getEtiqueta())][getVertices().indexOf(v.getEtiqueta())];
					
					//Si la ruta d[v] + C[v,w] es menor que la ruta D[w]
					if((Integer)d[getVertices().indexOf(v.getEtiqueta())] > sumaDeCostos){
						//nos quedamos con la nueva distancia
						d[getVertices().indexOf(v.getEtiqueta())] = sumaDeCostos;
						//Se guarda el predecesor del vetice
						p[getVertices().indexOf(v.getEtiqueta())] =  w.getEtiqueta();
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
				System.out.print(matriz[i][j] + "\t\t");
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
	
	/**
	 * Elimina una adyacencia de un nodo hacia otro
	 * @param origen vertice de origen
	 * @param destino vertice de destino
	 * @return true - si se elimino la adyacencia
	 * false - si la adyacencia no existia
	 */
	public boolean eliminarAdyacencia(Comparable origen, Comparable destino){
		boolean salida = false;
		
		TVertice vOrigen = (TVertice)getVertices().buscarNodo(origen).getElemento();
	
		if(vOrigen != null){
			salida = vOrigen.getAdyacentes().eliminar(destino);
			//Marco la matriz para que sea regenerada la proxima vez que se la quiera accesar
		}
		this.regenMatriz = salida;
		
		return salida;
	}
	
	/**
	 * Elimina el vertice del grafo y todas las adyacencias hacia este
	 * @param etiqueta la etiqueta del vertice que se quiere eliminar
	 * @return true - si se produjo la eliminacion de forma correcta
	 * false - si el vertie no existe
	 */
	public boolean eliminarVertice(Comparable etiqueta){
		boolean salida = false;
		TNodo vertice = getVertices().buscarNodo(etiqueta);
		if(vertice != null){
			int indexVertice = getVertices().indexOf(vertice.getClave());
			Comparable[][] floyd = getMFloyd();
			String adyacentes = new String();
			String[] arrayAdyacentes;
			
			//localizamos los vertices que son adyacentes a al que queremos eliminar
			for(int i = 0; i< floyd.length; i++){
				if(floyd[i][indexVertice].compareTo(INFINITO) != 0)
					adyacentes += i + ";"; 
			}
			
			arrayAdyacentes =  adyacentes.split(";");
			
			for(int i = 0; i < arrayAdyacentes.length; i++){
				((TVertice)getVertices().recuperar(Integer.parseInt(arrayAdyacentes[i])).getElemento()).getAdyacentes().eliminar(etiqueta);
			}
			//Eliminamos el vertice de la lista de adyacencias
			salida = getVertices().eliminar(etiqueta);
			//Marcamos la regeneracion de la matriz
			regenMatriz = true;
		}
		return salida;
	}
}
