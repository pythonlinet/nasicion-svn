package uy.edu.ucu.pii.obligatorio1.repo;

import uy.edu.ucu.pii.obligatorio1.gnasi.datos.Actor;
import uy.edu.ucu.pii.obligatorio1.gnasi.datos.arbol.TArbol;
import uy.edu.ucu.pii.obligatorio1.gnasi.datos.arbol.TNodoAB;

public class Actores extends TArbol{

		
		
	public Actor buscarActor(String nomActor){
		Actor result = null;
		TNodoAB actor = buscar(nomActor);
		
		if(actor != null)
			result = (Actor)actor.getElemento();
		
		return  result;
	}
	
	public boolean agregarActor(String nomActor, String sexo){
		return insertar(nomActor, new Actor(nomActor,sexo));
	}
	
	
	
	@Override
	public String toString() {
		Comparable[] actores;
		String cadenaActores = null;
		
		for(Comparable a : inOrden())
			cadenaActores += a + "\n";
		
		return cadenaActores;
	}
}
