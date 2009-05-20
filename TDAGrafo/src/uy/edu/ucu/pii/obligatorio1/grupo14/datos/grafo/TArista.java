package uy.edu.ucu.pii.obligatorio1.grupo14.datos.grafo;

public class TArista {
	@SuppressWarnings("unchecked")
	private Comparable costo;
	private TVertice destino;
	
	
	@SuppressWarnings("unchecked")
	public TArista(Comparable costo, TVertice destino) {
		super();
		this.costo = costo;
		this.destino = destino;
	}


	public Comparable getCosto() {
		return costo;
	}


	public void setCosto(Comparable costo) {
		this.costo = costo;
	}


	public TVertice getDestino() {
		return destino;
	}


	public void setDestino(TVertice destino) {
		this.destino = destino;
	}
	
	
	
}
