package uy.edu.ucu.pii.obligatorio1.grupo14.repo;

import uy.edu.ucu.pii.obligatorio1.grupo14.datos.Actor;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.arbol.TArbol;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.arbol.TNodoAB;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.arbol.especiales.TNodoABActor;

/**
 * Clase para representar el arbol de actores.
 * @author Grupo14
 * @see uy.edu.ucu.pii.obligatorio1.grupo14.datos.Actor
 */
public class Actores extends TArbol{
	private TNodoABActor raiz;

	
	
	public TNodoABActor getRaiz() {
		return raiz;
	}
	
	private void setRaiz(TNodoABActor raiz) {
		this.raiz = raiz;
	}

	/**
	 * 	Busca un actor dentro del arbol de actores
	 * @param nomActor
	 * @return actor - el actor buscado; null - si el actor no existe
	 */
	public Actor buscarActor(String nomActor){
		Actor result = null;
		TNodoAB actor = buscar(nomActor);
		
		if(actor != null)
			result = (Actor)actor.getElemento();
		
		return  result;
	}
	/**
	 * Agrega un actor al arbol
	 * @param nomActor nombre del actor
	 * @param sexo sexo del actor
	 * @return true - si se inserto corretamente; false - si el actor ya existia
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
	 * @return lista de actrices
	 */
	public Comparable[] listadoActrices() {
		Comparable[] listadoActrices = new Comparable[0];
		
		if(!vacio()){
			listadoActrices = this.raiz.listadoActrices();
		}
		
		return listadoActrices;
	}
}
