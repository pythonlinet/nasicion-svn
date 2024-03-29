package uy.edu.ucu.pii.grupo14.datos.grafo;

import uy.edu.ucu.pii.grupo14.datos.lista.TLista;

/**
 * <b>Clase dise�ada para representar los vertices de un grafo</b>
 * @author <i>Grupo14</i>
 * @version <i>1.0</i>
 * 
 * @see uy.edu.ucu.pii.grupo14.datos.grafo.TGrafo
 * @see uy.edu.ucu.pii.grupo14.datos.grafo.TArista
 */
public class TVertice {
	private Comparable etiqueta;
	private TLista adyacentes;
	private boolean visitado;

	public Comparable getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(Comparable etiqueta) {
		this.etiqueta = etiqueta;
	}

	public TLista getAdyacentes() {
		return adyacentes;
	}

	public void setAdyacentes(TLista adyacentes) {
		this.adyacentes = adyacentes;
	}
	
	public boolean isVisitado() {
		return visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

	public TVertice(Comparable etiqueta) {
		super();
		this.etiqueta = etiqueta;
		this.adyacentes = new TLista();
		this.visitado = false;
	}

	/**
	 * Metodo para ingresar una adyacencia en un vertice
	 * @param destino vertice destino de la adyacencia
	 * @param costo costo de la adyacencia
	 * @return true - si se pudo crear la adycencia; false - si la adyacencia ya existe
	 */
	public boolean ingresarAdyacencia(TVertice destino, Integer costo){
		//No es necesario verificar por elementos duplicados ya que el metodo insertar de TLista lo hace por nosotros
		return adyacentes.insertar(destino.getEtiqueta() , new TArista(costo,destino)); 
		
	}
	/**
	 * Metodo para saber si el Vertice tiene como adyacente otro Vertice
	 * @param destino destino del que se quiere saber si existe adyacencia
	 * @return true - si es adyacente del vertice; false - si no es adyacente
	 */
	public boolean existeAdyacencia(Comparable destino){
		return adyacentes.buscarNodo(destino) == null?false:true;
	}
	
	/**
	 * Metodo para saber la cantidad de vertices adyacentes a este vertice
	 * @return Integer - cantidad de vertices adyacentes
	 */
	public Integer cantidadDeAdyacencias(){
		return adyacentes.getTamanio();
	}
	
}
