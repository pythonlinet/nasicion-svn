package uy.edu.ucu.pii.obligatorio2.entidades;

import uy.edu.ucu.pii.grupo14.datos.lista.TLista;

/**
 * Clase utilizada para representar un tramo entre 2 ciudades junto con su lista de aviones
 * @author Grupo14
 * @see {@link Ciudades}
 * @see {@link Costo}
 * @see {@link Avion}
 * @see {@link TLista}
 * 
 * @version 1.0
 *	
 */
public class Tramo{
	private Ciudad destino;
	
	private Costo costo;
	private TLista<Avion> aviones;
	
	public Ciudad getCiudadDestino() {
		return destino;
	}

	public void setCiudadDestino(Ciudad destino) {
		this.destino = destino;
	}


	public Costo getCostoTramo() {
		return costo;
	}

	
	public void setCostoTramo(Costo costo) {
		this.costo = costo;
	}
	
	
	public TLista<Avion> getAvionesAsignados() {
		return aviones;
	}

	public Tramo(Ciudad destino, Costo costo) {
		this.destino = destino;
		this.costo = costo;
		this.aviones = new TLista<Avion>();
	}
	
	public Tramo() {
		super();
		this.costo = new Costo();
		this.aviones = new TLista<Avion>();
	}
	
	/**
	 * Constructor que genera un nuevo tramo apartir de otro, se utiliza principalmente para generar las nuevas matrices y que no haya problemas de referncia.
	 * @param tramo tramo que se quiere duplicar
	 */
	public Tramo(Tramo tramo) {
		this.costo = new Costo(); 
		this.costo.setDistanciaEnKm(tramo.getCostoTramo().getDistanciaEnKm().doubleValue());
		this.costo.setTiempoEstimadoEnMinutos(tramo.getCostoTramo().getTiempoEstimadoEnMinutos().doubleValue());
	}

	
	public boolean agregarAvion(Avion avion){
		return aviones.insertarOrdenado(avion.getNombre(),avion);
	}
	
	@Override
	public String toString() {
		return this.getCostoTramo().getTiempoEstimadoEnMinutos()+"Mins"  + " " + this.getCostoTramo().getDistanciaEnKm()+"Km";
	}
	
}
