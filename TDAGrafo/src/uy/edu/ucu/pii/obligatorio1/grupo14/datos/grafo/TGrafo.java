package uy.edu.ucu.pii.obligatorio1.grupo14.datos.grafo;

import java.util.Vector;

import uy.edu.ucu.pii.obligatorio1.grupo14.datos.lista.TLista;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.lista.TNodo;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.grafo.TArista;

/**
 * Clase para la implementacion de Grafos.
 * 
 * @author Grupo14
 * 
 */
public class TGrafo {
	private static final Comparable INFINITO = null;

	private TLista vertices;

	private Comparable[][] mAdyacencia;
	private Comparable[][] mFloyd;
	private boolean regenMatriz;
	private int cantVertices = 0;

	public int getCantVertices() {
		return cantVertices;
	}

	private void setCantVertices(int cantVertices) {
		this.cantVertices = cantVertices;
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
	public boolean insertarVertice(Comparable etiqueta) {
		// El metodo insertar de TLista no permite insercion de datos
		// duplicados, por lo tanto no es necesario verificar que el elemento no
		// exista
		boolean salida = vertices.insertar(etiqueta, new TVertice(
				etiqueta, cantVertices));
		// Si se realiza la insercion aumentamos la cantidad de vertices en el
		// grafo
		setCantVertices(salida ? getCantVertices() + 1 : getCantVertices());
		// Si se realizo la insercion marcamos que la matriz tiene que ser
		// regenerada la proxima vez que se la quiera accesar
		if(salida){
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
				if(salida){
					this.regenMatriz = salida;
				}
			}
		}
		return salida;
	}

	/**
	 * Utiliza el algoritmo de Floyd, también se podría haber hecho usando Warshall, pero como ambos algor
	 * 
	 * @param etiquetaOrigen
	 * @param etiquetaDestino
	 * @return
	 */
	public boolean existeCamino(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
		if(regenMatriz)
			implementacionFloyd();
		int origen = getPosMatriz(etiquetaOrigen);
		int destino = getPosMatriz(etiquetaDestino);
		return  mFloyd[origen][destino] != INFINITO?true:false;
	}

	public Comparable[] mejorCamino(Comparable etiquetaOrigen,
			Comparable etiquetaDestino) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Metodo que retorna el vertice que sea el centro del grafo
	 * @return 
	 */
	public Object centroDelGrafo() {
		Comparable aux = 0;
		int pos = 0;
		Comparable[] excentricidad = excentricidad();
		for(int i = 0; i < excentricidad.length; i++){
			if(aux.compareTo(excentricidad[i]) < 0){
				aux = excentricidad[i];
				pos = i;
			}
		}
		
		return vertices.recuperar(pos).getClave();
	}
	/**
	 * Metodo para obtener la excentricidad del grafo
	 * @return array de Comparable
	 */
	public Comparable[] excentricidad(){
		if(regenMatriz)
			implementacionFloyd();

		Comparable[] salida = new Comparable[mFloyd.length];
		
		
		Comparable aux;
		for(int i = 0; i < mFloyd.length; i++){
			aux = 0;
			for(int j = 0; j < mFloyd.length; j++){
				if(mFloyd[j][i]!=INFINITO && mFloyd[j][i].compareTo(aux) > 0)
					aux = mFloyd[j][i];
			}
			salida[i] = aux;
		}
		return salida;
	}
	
	
	/**
	 * Metodo para saber si existe un vertice con una X etiqueta
	 * 
	 * @param etiqueta etiqueta del vertices
	 * @return true - si el vertice existe en el grafo; false - si el vertice no existe en el grafo
	 */
	public boolean existeVertice(String etiqueta) {
		return vertices.buscarNodo(etiqueta) == null ? false : true;
	}

	/**
	 * Metodo para saber si dos vectores ORIGEN y DESTINO son adyacentes
	 * 
	 * @param etiquetaOrigen etiqueta del nodo origen
	 * @param etiquetaDestino etiqueta del nodo destino
	 * @return true - si existe la adyacencia; false - si no existe una adyacencia entre ambos vertices
	 */
	public boolean existeAdyacencia(String etiquetaOrigen,	String etiquetaDestino) {
		boolean salida = false;
		TNodo origen = vertices.buscarNodo(etiquetaOrigen);
		// Si existe el origen
		if (origen != null) {
			salida = ((TVertice) origen.getElemento()).existeAdyacencia(etiquetaDestino);
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
			
			// Cuando sea el costo de un vertice x hacia el mismo, seteamos el costo en 0
			mAdyacencia[i][i] = 0;
			
			// Recorremos las adyacencias del vertice
			for (int j = 0; j < cantAdyacencias; j++) {

				// Guardo la referencia a la arista
				adyacencia = ((TArista) vertice.getAdyacentes().recuperar(j).getElemento());
				mAdyacencia[i][adyacencia.destino.getPosMatriz()] = adyacencia.costo;
			}
		}
		this.regenMatriz = false;
	}

	/**
	 * Metodo para saber la posicion que tiene un vertice en la matriz
	 * 
	 * @param etiqueta etiqueta del vertice del que se quiere saber la posicion
	 * @return unNumero - la posici�n del vertice dentro de la matriz de adyacencia; -1 - si el vertice no existe
	 */
	private int getPosMatriz(Comparable etiqueta) {
		int salida = -1;
		// Buscamos el vertice
		TVertice vertice = (TVertice) vertices.buscarNodo(etiqueta).getElemento();
		// Si el vertice existe guardamos la posici�n que tiene en la matriz
		salida = vertice != null ? vertice.getPosMatriz() : salida;

		return salida;
	}

	/**
	 * Implementacion del algoritmo de Floyd
	 * 
	 * @return Comparable[][] - con los costos minimos de ir desde un Origen hasta un Destino
	 */
	private Comparable[][] implementacionFloyd() {
		// Si la matriz fue marcada para regeneracion se la genera otra vez
		if (regenMatriz)
			cargarMatrizDeAdyacencia();

		Comparable[][] salida = this.mAdyacencia;
		Integer aIJ;
		Integer aIK;
		Integer aKJ;

		for (int k = 0; k < salida.length; k++) {
			for (int i = 0; i < salida.length; i++) {
				for (int j = 0; j < salida.length; j++) {
					// Si ninguno de los dos es infinito(NULL)
					if (salida[i][k] != INFINITO && salida[k][j] != INFINITO) {

						aIK = (Integer)salida[i][k];
						aKJ = (Integer)salida[k][j];
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
}
