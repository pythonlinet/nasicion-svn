package uy.edu.ucu.pii.grupo14.datos.lista.comparadores;

import java.util.Comparator;


import uy.edu.ucu.pii.grupo14.datos.lista.TNodo;
import uy.edu.ucu.pii.obligatorio2.entidades.Avion;
import uy.edu.ucu.pii.obligatorio2.entidades.Tramo;

/**
 * Clase comparadora para comparar el costo de dos tramos en base al rendimiento del mejor de sus aviones 
 * y a la distancia que deben recorrer
 * @author Grupo14
 *@version 1.0
 */
public class compararCostoPorRendimiento implements Comparator<Tramo> {

	@Override
	public int compare(Tramo u1, Tramo u2) {
		int salida = 0;
		//Ya que la lista de aviones esta ordenada por rendimiento, el primer avion de la lista de aviones del tramo sera el mas eficiente
		TNodo<Avion> nodoAvion = u1.getAvionesAsignados().getPrimero(); 
		if(nodoAvion != null){
			Avion avionT1 = nodoAvion.getElemento();
			nodoAvion = u2.getAvionesAsignados().getPrimero();
			if(nodoAvion != null){
				Avion avionT2 = nodoAvion.getElemento();
				
				//Calculo el costo de combustible para el tramo 1
				Double costo1 = avionT1.getRendimiento() * u1.getCostoTramo().getDistanciaEnKm();
				//Calculo el costo de combustible para el tramo 2
				Double costo2 = avionT2.getRendimiento() * u2.getCostoTramo().getDistanciaEnKm();
			
				//Los comparo
				salida = costo1.compareTo(costo2);
			}
		}
		
		
		return salida;
	}

}
