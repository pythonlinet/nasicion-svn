package uy.edu.ucu.pii.grupo14.datos.lista.comparadores;

import java.util.Comparator;

import uy.edu.ucu.pii.obligatorio2.entidades.Avion;

public class compararAvionesPorRendimiento implements Comparator<Avion> {


	@Override
	public int compare(Avion o1, Avion o2) {
		int salida = 0;
		
		
		if(o1 != null)
			
			if(o2 != null)
				salida = o1.getRendimiento().compareTo(o2.getRendimiento());
			else
				salida = 1;
		else
			salida = -1;	
		
		return salida;
	}

}
