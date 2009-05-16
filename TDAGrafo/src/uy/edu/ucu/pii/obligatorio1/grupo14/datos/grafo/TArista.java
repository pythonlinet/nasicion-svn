package uy.edu.ucu.pii.obligatorio1.grupo14.datos.grafo;

public class TArista {
	@SuppressWarnings("unchecked")
	Comparable costo;
	TVertice destino;
	
	
	@SuppressWarnings("unchecked")
	public TArista(Comparable costo, TVertice destino) {
		super();
		this.costo = costo;
		this.destino = destino;
	}
	
	
	
}
