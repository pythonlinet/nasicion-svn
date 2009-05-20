package uy.edu.ucu.pii.obligatorio1.grupo14.datos.grafo;

/**
 * <b>Clase para representar una arista entre dos vertices de un grafo</b>
 * @author <i>Grupo14</i>
 * @version <i>1.0</i>
 * 
 * @see uy.edu.ucu.pii.obligatorio1.grupo14.datos.grafo.TGrafo
 */
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
