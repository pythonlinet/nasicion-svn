package uy.edu.ucu.pii.grupo14.datos.lista.comparadores;

import java.util.Comparator;

import uy.edu.ucu.pii.obligatorio2.entidades.Costo;

public class compararCostoPorDistancia implements Comparator<Costo> {

	@Override
	public int compare(Costo costo1, Costo costo2) {
		return costo1.getDistanciaEnKm().compareTo(costo2.getDistanciaEnKm());
	}

}
