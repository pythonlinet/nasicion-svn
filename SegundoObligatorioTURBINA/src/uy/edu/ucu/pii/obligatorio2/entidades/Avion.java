package uy.edu.ucu.pii.obligatorio2.entidades;

import uy.edu.ucu.pii.grupo14.datos.lista.TLista;

public class Avion {
	private Comparable nombre;
	private Double rendimiento;
	
	TLista itinerarios;

	
	public Comparable getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getRendimiento() {
		return rendimiento;
	}

	public void setRendimiento(Double rendimiento) {
		this.rendimiento = rendimiento;
	}

	public TLista getItinerarios() {
		return itinerarios;
	}

	public void setItinerarios(TLista itinerarios) {
		this.itinerarios = itinerarios;
	}

	public Avion(Comparable nombreAvion, Double rendimiento) {
		super();
		this.nombre = nombreAvion;
		this.rendimiento = rendimiento;
	}
	
	
	@Override
	public String toString() {
		return this.nombre + " " + this.rendimiento;
	}
	
}
