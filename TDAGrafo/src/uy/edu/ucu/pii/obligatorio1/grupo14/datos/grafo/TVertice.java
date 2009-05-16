package uy.edu.ucu.pii.obligatorio1.grupo14.datos.grafo;

import uy.edu.ucu.pii.obligatorio1.grupo14.datos.lista.TLista;

public class TVertice {
	private Comparable etiqueta;
	private TLista adyacentes;

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

	public TVertice(Comparable etiqueta) {
		super();
		this.etiqueta = etiqueta;
		this.adyacentes = new TLista();
	}

	public boolean ingresarAdyacencia(TVertice destino, Comparable costo){
		//No es necesario verificar por elementos duplicados ya que el metodo insertarOrdenado de TLista lo hace por nosotros
		return adyacentes.insertarOrdenado(destino.getEtiqueta() , new TArista(costo,destino)); 
		
	}
}
