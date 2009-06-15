package uy.edu.ucu.pii.obligatorio2.entidades;

import uy.edu.ucu.pii.grupo14.datos.lista.TLista;

public class Ciudad{
	private Comparable nombre;
	private Comparable pais;

	TLista<Tramo> tramos;
	
	public Comparable getNombre() {
		return nombre;
	}
	public void setNombre(Comparable nombre) {
		this.nombre = nombre;
	}
	public Comparable getPais() {
		return pais;
	}
	public void setPais(Comparable pais) {
		this.pais = pais;
	}
	
	
	public TLista<Tramo> getTramos() {
		return tramos;
	}
	public void setTramos(TLista<Tramo> tramos) {
		this.tramos = tramos;
	}
	public Ciudad(Comparable nombre, Comparable pais) {
		this.nombre = nombre;
		this.pais = pais;
		tramos = new TLista<Tramo>();
	}
	public boolean agregarTramo(Ciudad destino,	Costo costo) {
		// TODO Auto-generated method stub
		return tramos.insertar(destino.getNombre(), new Tramo(destino,costo));
	}
	
	
	
	
}
