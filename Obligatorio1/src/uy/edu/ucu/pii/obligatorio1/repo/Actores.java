package uy.edu.ucu.pii.obligatorio1.repo;

import uy.edu.ucu.pii.obligatorio1.gnasi.datos.Actor;
import uy.edu.ucu.pii.obligatorio1.gnasi.datos.arbol.TArbol;
import uy.edu.ucu.pii.obligatorio1.gnasi.datos.arbol.TNodoAB;
import uy.edu.ucu.pii.obligatorio1.gnasi.datos.arbol.especiales.TNodoABActor;

public class Actores extends TArbol{
	private TNodoABActor raiz;

	
	
	public TNodoABActor getRaiz() {
		return raiz;
	}
	
	private void setRaiz(TNodoABActor raiz) {
		this.raiz = raiz;
	}

	/**
	 * 	
	 * @param nomActor
	 * @return
	 */
	public Actor buscarActor(String nomActor){
		Actor result = null;
		TNodoAB actor = buscar(nomActor);
		
		if(actor != null)
			result = (Actor)actor.getElemento();
		
		return  result;
	}
	/**
	 * 
	 * @param nomActor
	 * @param sexo
	 * @return
	 */
	public boolean agregarActor(String nomActor, String sexo){
		boolean salida = false;
		if (vacio()) {
			this.raiz = new TNodoABActor(nomActor, new Actor(nomActor,sexo));
			salida = true;
		} else
			salida = this.raiz.insertar(nomActor, new Actor(nomActor,sexo));

		return salida;
	}
	
	
	
	@Override
	public String toString() {
		Comparable[] actores;
		String cadenaActores = null;
		
		for(Comparable a : inOrden())
			cadenaActores += a + "\n";
		
		return cadenaActores;
	}
	
	/**
	 * Metodo para contar la cantdida de actrices
	 * @return cantidad de actrices
	 */
	public int contarActrices(){
		int result = 0;
			if(getRaiz() != null)
				result  = raiz.contarActrices();
		
		return result;
	}
	
	/**
	 * Reimplementacion del metodo inOrden para que filtre las actrices
	 * @return
	 */
	public Comparable[] listadoActrices() {
		Comparable[] listadoActrices = new Comparable[0];
		
		if(!vacio()){
			listadoActrices = this.raiz.listadoActrices();
		}
		
		return listadoActrices;
	}
}
