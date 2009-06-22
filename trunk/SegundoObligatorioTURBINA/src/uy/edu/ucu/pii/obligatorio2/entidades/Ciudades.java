package uy.edu.ucu.pii.obligatorio2.entidades;

import java.util.Comparator;

import uy.edu.ucu.pii.grupo14.datos.lista.TLista;
import uy.edu.ucu.pii.grupo14.datos.lista.TNodo;
import uy.edu.ucu.pii.obligatorio2.grupo14.comparadores.compararAvionesPorRendimiento;
import uy.edu.ucu.pii.obligatorio2.grupo14.comparadores.compararCostoPorDistancia;
import uy.edu.ucu.pii.obligatorio2.grupo14.comparadores.compararCostoPorTiempo;

/**
 * Clase que reimplementa los metodos utilizados en el trabajo sobre grafos
 * Como mejora a dicha clase se modificaron los metodos, Floyd y Dijkstra entre otros para que utilizen comparadores para realizar los calculos
 * @author Grupo14
 * 
 * @see Ciudad
 * @see Tramo
 * @see Costo
 * 
 * @version 1.0
 */
public class Ciudades{

	

	private static final Double INFINITO = Double.MAX_VALUE;
	private static final Costo COSTO_INFINITO = new Costo(Double.MAX_VALUE,Double.MAX_VALUE);
	private static final Costo COSTO_CERO = new Costo(0.0,0.0);
	
	private TLista<Ciudad> ciudades;

	@SuppressWarnings("unchecked")
	private Tramo[][] mAdyacencia;
	@SuppressWarnings("unchecked")
	private Tramo[][] mFloyd;
	
	/**
	 * Indica a varios metodos si es necesario regenerar las matrices
	 */
	private boolean regenMatriz;
	/**
	 * Indica la cantidad de vertices del grafo, es utilizado tambi�n para
	 * setear a los vertices la posici�n que van a tener dentro de la matriz
	 */
	private int cantVertices = 0;

	
	
	
	public TLista<Ciudad> getCiudades() {
		return ciudades;
	}

	private void setCiudades(TLista<Ciudad> vertices) {
		this.ciudades = vertices;
	}

	public int getCantVertices() {
		return cantVertices;
	}

	private void setCantVertices(int cantVertices) {
		this.cantVertices = cantVertices;
	}

	public Tramo[][] getMFloydPorDistancias() {
		if(mFloyd == null)
			implementacionFloyd(new compararCostoPorDistancia());
		return mFloyd;
	}
	public Tramo[][] getMFloydPorTiempo() {
		if(mFloyd == null)
			implementacionFloyd(new compararCostoPorTiempo());
		return mFloyd;
	}

	public Tramo[][] getMAdyacencia(){
		if(this.mAdyacencia == null || regenMatriz)
			cargarMatrizDeAdyacencia();
		return this.mAdyacencia;
	}
	
	
	public Ciudades() {
		this.ciudades = new TLista<Ciudad>();
		this.cantVertices = 0;
		this.regenMatriz = true;
	}


	/**
	 * Metodo para insertar una nueva ciudad en el grafo de ciudades
	 * @param nombre
	 * @param pais
	 * @return true - si el vertice se inserto correctamente; 
	 * 	false - si el vertice ya existe
	 */
	@SuppressWarnings("unchecked")
	public boolean insertarCiudad(Comparable nombre, Comparable pais) {
		// El metodo insertar de TLista no permite insercion de datos
		// duplicados, por lo tanto no es necesario verificar que 
		// el elemento no exista
		boolean salida = ciudades.insertar(nombre, new Ciudad(nombre, pais));
		
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
	 * Metodo para agregar un tramo a una ciudad
	 * 
	 * @param nomCiudadOrigen
	 *            vertice de origen
	 * @param nomCiudadDestino
	 *            vertice de destino
	 * @param costo
	 *            costo desde el origen hasta el destino
	 * @return true - si se pudo agregar la adyacencia; false - si ya existia
	 *         una adyacencia o si uno de los
	 */
	@SuppressWarnings("unchecked")
	public boolean agregarTramo(Comparable nomCiudadOrigen, Comparable nomCiudadDestino, Costo costo) {
		boolean salida = false;

		Ciudad ciudadOrigen = ciudades.buscarNodo(nomCiudadOrigen).getElemento();
		// Si el nodo de origen existe
		if (ciudadOrigen != null) {
			Ciudad ciudadDestino = ciudades.buscarNodo(nomCiudadDestino).getElemento();
			// Si el nodo destino existe
			if (ciudadDestino != null) {
				// Demasiados casteos?? :P
				// No es necesario hacer un control de si ya existe la
				// adyacencia porque el insertar de la lista ya lo hace
		
				//salida = ((TVertice) nodoOrigen.getElemento()).ingresarAdyacencia((TVertice) nodoDestino.getElemento(), costo);
				salida = ciudadOrigen.agregarTramo(ciudadDestino,costo);
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
		implementacionFloyd(new compararCostoPorTiempo());
	
		int origen = getPosMatriz(etiquetaOrigen);
		int destino = getPosMatriz(etiquetaDestino);
		//System.err.println(etiquetaOrigen +" "+etiquetaDestino+" " +!mFloyd[origen][destino].equals(INFINITO));
		
		if(origen != -1 && destino != -1)
			salida = mFloyd[origen][destino].getCostoTramo().equals(COSTO_INFINITO)? false : true; 
		

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
	public Comparable[] mejorCamino(Comparable etiquetaOrigen,	Comparable etiquetaDestino, Comparator comparador) {
		
		//Lista de ciudades
		Comparable[] salida = null;
		//Buscamos que exista el ORIGEN
		TNodo existeO = getCiudades().buscarNodo(etiquetaOrigen);
		
		if(existeO != null && existeCamino(etiquetaOrigen, etiquetaDestino)){
			//Buscamos que exista el DESTINO
			TNodo existeD = getCiudades().buscarNodo(etiquetaDestino);
			if(existeD != null){
				//Obtenemos la lista de predecesores
				Comparable[] arrayPredecesores = implementacionDijkstra(etiquetaOrigen, true, comparador);
				
				//Obtenemos la posicion que tiene el destino dentro del array
				int posicionDestino = getCiudades().indexOf(existeD.getClave());
				
				//Verificamos que exista un camino hasta el destino, si lo hay procedemos a armarlo
				if(arrayPredecesores[posicionDestino] != INFINITO){
					String camino;
					//Inserto el final del camino
					camino = etiquetaDestino.toString();
					camino = arrayPredecesores[posicionDestino]+"," + camino;

					//posicionDestino = ((TVertice) vertices.buscarNodo(arrayPredecesores[posicionDestino]).getElemento()).getPosMatriz();
					posicionDestino = getCiudades().indexOf(arrayPredecesores[posicionDestino]);
					
					Ciudad aux;
					while(arrayPredecesores[posicionDestino].compareTo(etiquetaOrigen) != 0){
						aux = getCiudades().buscarNodo(arrayPredecesores[posicionDestino]).getElemento();
						camino = arrayPredecesores[posicionDestino]+"," + camino;
						
						//posicionDestino = ((TVertice) vertices.buscarNodo(arrayPredecesores[posicionDestino]).getElemento()).getPosMatriz();
						posicionDestino =  getCiudades().indexOf(arrayPredecesores[posicionDestino]);
						
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
	 * null - si el grafo no tiene centro, es decir, que es conexo
	 */
	@SuppressWarnings("unchecked")
	public Comparable centroDelGrafo(Comparator comparador) {
		Comparable salida = null;
		int pos = 0;
		Tramo[] excentricidad = excentricidad(comparador);
		Tramo aux = excentricidad[0];
		
		boolean conexo = false; 
		//Nos cercioramos que el grafo sea conexo
		for (int i = 0; i < excentricidad.length && !conexo; i++) {
			if(!excentricidad[i].getCostoTramo().equals(COSTO_INFINITO))
				conexo = true;
		}
		if(conexo){
			// Recorro el array de excentricidad del Grafo buscando la menor de las
			// excentricidades, que por definicion deberia se el centro del grafo
			for (int i = 1; i < excentricidad.length; i++) {
				
				//Si la excentricidad del nodo actual es menor que la guardada
				if(comparador.compare(aux , excentricidad[i]) > 0){
					//Guardamos la nueva excentricidad
					aux = excentricidad[i];
					// Guardamos la posicion del vertice
					pos = i;
				}
			}
			salida = getCiudades().recuperar(pos).getClave();
		}
		// Devolvemos la etiqueta del vertice
		return salida;
	}

	
//	/**
//	 * Metodo para obtener la excentricidad del grafo
//	 * 
//	 * @return array de Comparable
//	 */
//	@SuppressWarnings("unchecked")
//	public Integer[] excentricidad() {
//		if (regenMatriz)
//			implementacionFloyd();
//
//		Integer[] salida = new Integer[mFloyd.length];
//
//		boolean continuar = true;
//		Integer aux;
//		for (int i = 0; i < mFloyd.length; i++) {
//			aux = 0;
//			for (int j = 0; j < mFloyd.length && continuar; j++) {
//				// Si el costo es infinito, seteamos aux como INFINITO,
//				// porque no va a haber un costo mayor a ese
//				if (mFloyd[j][i] == INFINITO) {
//					aux = INFINITO;
//					// Cortamos la ejecucion ya que no va a haber un valor mayor
//					// que INFINITO
//					continuar = false;
//				} else if (mFloyd[j][i] > (aux))
//					aux = mFloyd[j][i];
//			}
//			// Volemos a setear continuar en true para que se ejecute bien el
//			continuar = true;
//			salida[i] = aux;
//		}
//		return salida;
//	}

	/**
	 * Metodo para obtener la excentricidad en base a la distancia entre ciudades
	 * 
	 * @return array de Double
	 */
	@SuppressWarnings("unchecked")
	public Tramo[] excentricidad(Comparator comparador) {

		implementacionFloyd(comparador);

		Tramo[] salida = new Tramo[mFloyd.length];

		boolean continuar = true;
		Tramo aux;
		for (int i = 0; i < mFloyd.length; i++) {
			aux = new Tramo(null, new Costo(COSTO_CERO.getTiempoEstimadoEnMinutos().doubleValue(),COSTO_CERO.getDistanciaEnKm().doubleValue()));
			for (int j = 0; j < mFloyd.length && continuar; j++) {
				// Si el costo es infinito, seteamos aux como INFINITO,
				// porque no va a haber un costo mayor a ese
				if (mFloyd[j][i].getCostoTramo().equals(COSTO_INFINITO)) {
					aux.setCostoTramo(COSTO_INFINITO);
					// Cortamos la ejecucion ya que no va a haber un valor mayor
					// que INFINITO
					continuar = false;
				} else if (comparador.compare(mFloyd[j][i], aux) > 0)
					aux = new Tramo(mFloyd[j][i]);
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
		return ciudades.buscarNodo(etiqueta) == null ? false : true;
	}

	/**
	 * Metodo para saber si dos vectores ORIGEN y DESTINO son adyacentes
	 * 
	 * @param nombreCiudadOrigen
	 *            etiqueta del nodo origen
	 * @param nombreCiudadDestino
	 *            etiqueta del nodo destino
	 * @return true - si existe la adyacencia; false - si no existe una
	 *         adyacencia entre ambos vertices
	 */
	public boolean existeTramo(String nombreCiudadOrigen,	String nombreCiudadDestino) {
		boolean salida = false;
		Ciudad ciudadOrigen = getCiudades().buscarNodo(nombreCiudadOrigen).getElemento();
		// Si existe el origen
		if (ciudadOrigen != null) {
			salida = ciudadOrigen.existeTramo(nombreCiudadDestino);
		}
		return salida;
	}

	/**
	 * Inicializa la matriz de adyacencia
	 */
	private void inicializarMatriz() {
		int tamanio = getCiudades().getTamanio();
		this.mAdyacencia = new Tramo[tamanio][tamanio];
		for(int i = 0; i < tamanio; i++)
			for(int j = 0; j < tamanio; j++){
				if(j != i){
					this.mAdyacencia[i][j] = new Tramo();
					this.mAdyacencia[i][j].setCostoTramo(COSTO_INFINITO);
				}else{
					this.mAdyacencia[i][j] = new Tramo();
					this.mAdyacencia[i][j].setCostoTramo(COSTO_CERO);
				}
			}

		
	}

	/**
	 * Carga la matriz de adyacencia Para el caso que sea desde en Vertice X
	 * hacia el mismo el costo sera 0 Cuando no haya una adyacencia se dejara el
	 * valor null y se lo manejara como si fuera INFINITO, es decir que no hay
	 * adyacencia entre ambos vertices
	 */
	private void cargarMatrizDeAdyacencia() {
		inicializarMatriz();
		int cantVertices = ciudades.getTamanio();
		
		// Usado para guardar las aristas
		int cantDestinos;
		Ciudad ciudad;
		Tramo tramo;
		TNodo<Ciudad> nodoCiudad = getCiudades().getPrimero();
		TNodo<Tramo> nodoTramo;
		int posX, posY;
		
		while(nodoCiudad != null){
			ciudad = nodoCiudad.getElemento();
			//Posicion del origen en la matriz
			posX = getCiudades().indexOf(ciudad.getNombre());
			nodoTramo = ciudad.getTramos().getPrimero();
			while(nodoTramo != null){
				tramo = nodoTramo.getElemento();
				posY = getCiudades().indexOf(tramo.getCiudadDestino().getNombre());
				
				this.mAdyacencia[posX][posY] = tramo;
				nodoTramo = nodoTramo.getSiguiente();
			}
			nodoCiudad = nodoCiudad.getSiguiente();
		}
		
		
//		for (int i = 0; i < cantVertices; i++) {
//			ciudad = getCiudades().recuperar(i).getElemento();
//			// Cantida de destinos desde la ciudad
//			cantDestinos = ciudad.getTramos().getTamanio();
//
//			// Cuando sea el costo de un vertice x hacia el mismo, seteamos el
//			// costo en 0
//			mAdyacencia[i][i] = new Tramo();
////			mAdyacencia[i][i].setCostoTramo(COSTO_CERO);
//
//			// Recorremos las adyacencias del vertice
//			for (int j = 0; j < cantDestinos; j++) {
//
//				// Guardo la referencia a la arista
//				tramo = ciudad.getTramos().recuperar(j).getElemento();
//				mAdyacencia[i][getCiudades().indexOf(tramo.getCiudadDestino().getNombre())] = tramo;
//			}
//		}
		this.regenMatriz = false;
	}

	/**
	 * Metodo para saber la posicion que tiene una ciudad en la matriz
	 * 
	 * @param etiqueta
	 *            etiqueta del vertice del que se quiere saber la posicion
	 * @return unNumero - la posici�n del vertice dentro de la matriz de
	 *         adyacencia; -1 - si el vertice no existe
	 */
	@SuppressWarnings("unchecked")
	private int getPosMatriz(Comparable etiqueta) {
		int salida = -1;
		
		// Buscamos la ciudad
		Ciudad ciudad = null;
		TNodo<Ciudad> nodo = getCiudades().buscarNodo(etiqueta);
		if(nodo != null)
			ciudad = nodo.getElemento(); 
		// Si el vertice existe guardamos la posici�n que tiene en la matriz
		salida = ciudad != null ? getCiudades().indexOf(ciudad.getNombre()) : salida;

		return salida;
	}

	/**
	 * Implementacion del algoritmo de Floyd
	 * @param comparador 
	 * 
	 * @return Comparable[][] - con los costos minimos de ir desde un Origen
	 *         hasta un Destino
	 */
	@SuppressWarnings("unchecked")
	private Tramo[][] implementacionFloyd(Comparator comparador) {
		cargarMatrizDeAdyacencia();

		Tramo[][] salida = new Tramo[this.mAdyacencia.length][this.mAdyacencia.length];
		
		//Copiamos la matriz de adyacencia en la matriz de salida
		for(int i = 0; i < salida.length; i++)
			for(int j = 0; j < salida.length; j++){
				salida[i][j] = new Tramo(mAdyacencia[i][j]);
			}
		
		Tramo aIJ, aIK, aKJ;
		Tramo suma = new Tramo();


		for (int k = 0; k < salida.length; k++) {
			for (int i = 0; i < salida.length; i++) {
				for (int j = 0; j < salida.length; j++) {
					aIJ = salida[i][j];
					
					aIK = salida[i][k];
					aKJ = salida[k][j];

					//Es necesario hacer esto porque con solo sumar 1 a infinto este toma un valor negativo acausa del  
//					suma = aIK == INFINITO || aKJ == INFINITO?INFINITO:aIK + aKJ;
//					if(comparador.compare(aIK, COSTO_INFINITO)  == 0 || comparador.compare(aKJ, COSTO_INFINITO)  == 0 )
					if(aIK.getCostoTramo().equals(COSTO_INFINITO) || aKJ.getCostoTramo().equals(COSTO_INFINITO)){
						suma.setCostoTramo(COSTO_INFINITO);
					}else{
						Costo nuevoCosto = new Costo(aIK.getCostoTramo().getTiempoEstimadoEnMinutos() + aKJ.getCostoTramo().getTiempoEstimadoEnMinutos(), aIK.getCostoTramo().getDistanciaEnKm() + aKJ.getCostoTramo().getDistanciaEnKm());
						
						suma.setCostoTramo(nuevoCosto);
					}
					
					//si el aIJ es mayor que la suma y la suma no es el costo 0
					if(comparador.compare(aIJ, suma) > 0)
						salida[i][j] = new Tramo(suma);
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
	public Comparable[] implementacionDijkstra(Comparable origen, boolean retornarCaminos, Comparator<Tramo> comparador) {
		Tramo[] d = null;
		Comparable[] p = null;
		TNodo<Ciudad> existeOrigen = ciudades.buscarNodo(origen);
		// Compruebo que exista el origen
		if (existeOrigen != null) {
			Ciudad cOrigen =  existeOrigen.getElemento();

			cargarMatrizDeAdyacencia();
			
			// Cargamos D con los valores iniciales
			d = mAdyacencia[getCiudades().indexOf(cOrigen.getNombre())];
			
			// Posicion del ORIGEN dentro de la matriz de adyacencia
			int x = getCiudades().indexOf(cOrigen.getNombre());

			
			//Inicializamos el array de predecesores
			p = new Comparable[getCiudades().getTamanio()];
			for(int i = 0; i < p.length; i++)
				p[i] = origen;
			p[x] = origen;
			
			// Vertice Actual
			Ciudad w = null;

			// Variables usadas a la hora de buscar el w minimo
			Tramo tramoACTUAL, tramoAUX;
			Ciudad v;

			// Variables usadas en la busqueda de caminos mas cortos
			Tramo sumaDeCostos = null;
			// Lista de vertices
			TLista<Ciudad> conjuntoV = new TLista<Ciudad>();

			// Cargo la lista de vertices v
			for (Comparable etiquetaV : ciudades.mostrar())
				conjuntoV.insertar(etiquetaV, ciudades.buscarNodo(etiquetaV).getElemento());

			// Quito el vertice origen
			conjuntoV.eliminar(origen);

			// Comienza el algoritmo de Dijkstra

			while (conjuntoV.getPrimero() != null) {

				// Ahora hay que elegir el vertice mas cerca de X(Origen)
				// Distancia minima actual es la del primer elemento del
				// conjunto V de vertices
				w = conjuntoV.recuperar(0).getElemento();
				tramoACTUAL = d[getCiudades().indexOf(w.getNombre())];

				for (int i = 1; i < conjuntoV.getTamanio(); i++) {
					// Guardo el vertice de V sobre el que estoy parado
					v = conjuntoV.recuperar(i).getElemento();
					tramoAUX = d[getCiudades().indexOf(v.getNombre())];

//					if(costoACTUAL > costoAUX){
					if(comparador.compare(tramoACTUAL, tramoAUX) > 0){
						// Guardo el vertice acutal
						w = v;
						tramoACTUAL = tramoAUX;
					}
				}

				// Elimino el vertice elegido del conjunto V
				conjuntoV.eliminar(w.getNombre());

				for (int i = 0; i < conjuntoV.getTamanio(); i++) {
					v = conjuntoV.recuperar(i).getElemento();

					//TOMA PA VOS Y TU TIA GREGORIA
//					sumaDeCostos = d[getCiudades().indexOf(w.getNombre())].equals(COSTO_INFINITO)|| mAdyacencia[getCiudades().indexOf(w.getNombre())][getCiudades().indexOf(v.getNombre())].equals(COSTO_INFINITO)?COSTO_INFINITO : d[getCiudades().indexOf(w.getNombre())] + mAdyacencia[getCiudades().indexOf(w.getNombre())][getCiudades().indexOf(v.getNombre())];
					
					if(d[getCiudades().indexOf(w.getNombre())].equals(COSTO_INFINITO)|| mAdyacencia[getCiudades().indexOf(w.getNombre())][getCiudades().indexOf(v.getNombre())].equals(COSTO_INFINITO)){
						sumaDeCostos.setCostoTramo(COSTO_INFINITO);
					}else{
						sumaDeCostos = new Tramo();
						sumaDeCostos.setCostoTramo(new Costo(
								d[getCiudades().indexOf(w.getNombre())].getCostoTramo().getTiempoEstimadoEnMinutos() + mAdyacencia[getCiudades().indexOf(w.getNombre())][getCiudades().indexOf(v.getNombre())].getCostoTramo().getTiempoEstimadoEnMinutos()
								,d[getCiudades().indexOf(w.getNombre())].getCostoTramo().getDistanciaEnKm() + mAdyacencia[getCiudades().indexOf(w.getNombre())][getCiudades().indexOf(v.getNombre())].getCostoTramo().getDistanciaEnKm()));
					}
					
					//Si la ruta d[v] + C[v,w] es menor que la ruta D[w]
//					if((Integer)d[getVertices().indexOf(v.getEtiqueta())] > sumaDeCostos){
//						//nos quedamos con la nueva distancia
//						d[getVertices().indexOf(v.getEtiqueta())] = sumaDeCostos;
//						//Se guarda el predecesor del vetice
//						p[getVertices().indexOf(v.getEtiqueta())] =  w.getEtiqueta();
//					}
					if(comparador.compare(d[getCiudades().indexOf(v.getNombre())], sumaDeCostos) > 0){
					//nos quedamos con la nueva distancia
					d[getCiudades().indexOf(v.getNombre())] = sumaDeCostos;
					//Se guarda el predecesor del vetice
					p[getCiudades().indexOf(v.getNombre())] =  w.getNombre();
				}

				}
			}
			if(retornarCaminos){
				return p;
			}
		}
		
		return p;
	}

	/**
	 * Metodo auxiliar de imprimirMatrizAdyacente y imprimirMatrizFloyd
	 * 
	 * @param matriz
	 *            - matriz que se quiere imprimir en consola
	 */
	private void imprimirMatrizGrafo(Object[][] matriz) {
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
		cargarMatrizDeAdyacencia();
		imprimirMatrizGrafo(mAdyacencia);
		System.out.println();
	}

	/**
	 * Metodo para mostrar por consola la matriz resultante de aplicar Floyd al
	 * grafo
	 */
	public void imprimirMatrizFloyd() {
		implementacionFloyd(new compararCostoPorDistancia());

		imprimirMatrizGrafo(mFloyd);
		System.out.println();
	}
	
	/**
	 * Elimina una adyacencia de un nodo hacia otro
	 * @param origen vertice de origen
	 * @param destino vertice de destino
	 * @return true - si se elimino la adyacencia
	 * false - si la adyacencia no existia
	 */
	public boolean quitarTramo(Comparable origen, Comparable destino){
		boolean salida = false;
		
		Ciudad ciudadOrigen = getCiudades().buscarNodo(origen).getElemento();
	
		if(ciudadOrigen != null){
			salida = ciudadOrigen.getTramos().eliminar(destino);
			//Marco la matriz para que sea regenerada la proxima vez que se la quiera accesar
		}
		this.regenMatriz = salida;
		
		return salida;
	}
	
	/**
	 * Elimina el vertice(ciudad) del grafo de ciudades y todas las adyacencias hacia este
	 * @param nomCiudad la etiqueta del vertice que se quiere eliminar
	 * @return true - si se produjo la eliminacion de forma correcta
	 * false - si el vertie no existe
	 */
	public boolean quitarCiudad(Comparable nomCiudad){
		boolean salida = false;
		TNodo<Ciudad> nodoCiudad = getCiudades().buscarNodo(nomCiudad);
		
		//Si la ciudad existe
		if(nodoCiudad != null){
			int indexVertice = getCiudades().indexOf(nodoCiudad.getClave());
			
			Tramo[][] mAdyacencia = getMAdyacencia();
			String adyacentes = new String();
			String[] arrayAdyacentes;
			
			//localizamos los vertices que son adyacentes a al que queremos eliminar
			for(int i = 0; i< mAdyacencia.length; i++){
				if(mAdyacencia[i][indexVertice].getCostoTramo().getTiempoEstimadoEnMinutos() != INFINITO)
					adyacentes += i + ";"; 
			}
			
			arrayAdyacentes =  adyacentes.split(";");
			Ciudad ciudadAdyacente;
			//Borro los tramos hacia esta ciudad
			for(int i = 0; i < arrayAdyacentes.length; i++){
				ciudadAdyacente = getCiudades().recuperar(Integer.parseInt(arrayAdyacentes[i])).getElemento();
				
			}
			//Eliminamos el vertice de la lista de adyacencias
			salida = getCiudades().eliminar(nomCiudad);
			//Marcamos la regeneracion de la matriz
			regenMatriz = true;
		}
		return salida;
	}
	
	
}
