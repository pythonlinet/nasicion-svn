package uy.edu.ucu.pii.grupo14.datos.lista.comparadores;

import java.util.Comparator;

import uy.edu.ucu.pii.obligatorio2.entidades.Avion;

public class compararAvionesPorRendimiento implements Comparator<Avion> {


	@Override
	public int compare(Avion o1, Avion o2) {
		return o1.getRendimiento().compareTo(o2.getRendimiento());
	}

}
