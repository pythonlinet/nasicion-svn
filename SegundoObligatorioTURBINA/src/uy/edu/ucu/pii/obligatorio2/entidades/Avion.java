package uy.edu.ucu.pii.obligatorio2.entidades;

import uy.edu.ucu.pii.grupo14.datos.lista.TLista;

/**
 * Clase para representar los aviones dentro del programa
 * @author Grupo14
 * @version 1.0
 */
public class Avion {
	private Comparable nombre;
	private Double rendimiento;
	
	TLista<Tramo> tramosAsignados;

	
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

	public TLista<Tramo> getTramosAsignados() {
		return tramosAsignados;
	}

	public Avion(Comparable nombreAvion, Double rendimiento) {
		super();
		this.nombre = nombreAvion;
		this.rendimiento = rendimiento;
		this.tramosAsignados = new TLista<Tramo>();
	}
	
	
	@Override
	public String toString() {
		return this.nombre + " " + this.rendimiento;
	}
	
}
