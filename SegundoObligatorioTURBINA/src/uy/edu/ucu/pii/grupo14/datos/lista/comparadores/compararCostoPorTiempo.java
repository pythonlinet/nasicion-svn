package uy.edu.ucu.pii.grupo14.datos.lista.comparadores;

import java.util.Comparator;

import uy.edu.ucu.pii.obligatorio2.entidades.Tramo;

public class compararCostoPorTiempo implements Comparator<Tramo> {

	@Override
	public int compare(Tramo tramo1, Tramo tramo2) {
		return tramo1.getCostoTramo().getTiempoEstimadoEnMinutos().compareTo(tramo2.getCostoTramo().getTiempoEstimadoEnMinutos());
	}

}
