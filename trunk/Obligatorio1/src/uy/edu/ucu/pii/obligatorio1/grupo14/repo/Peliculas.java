package uy.edu.ucu.pii.obligatorio1.grupo14.repo;

import uy.edu.ucu.pii.obligatorio1.grupo14.datos.Pelicula;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.arbol.TArbol;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.arbol.TNodoAB;

public class Peliculas  extends TArbol{
	

	/**
	 * 
	 * @param nomPelicula
	 * @return true - si se insertro correctamente; false - si ya existe una pelicula con ese nombre
	 */
	public boolean agregarPelicula(String nomPelicula){
		boolean salida = false;
		
		salida = insertar(nomPelicula, new Pelicula(nomPelicula));
		
		return salida;
		
	}
	
	/**
	 * 
	 * @param nomPelicula
	 * @return
	 */
	public Pelicula buscarPelicula(String nomPelicula){
		Pelicula result = null;
		
		TNodoAB pelicula = buscar(nomPelicula);
		if(pelicula != null)
			result = (Pelicula)pelicula.getElemento();
		
		return result;
	}
	
	/**
	 * 
	 * @param nomPelicula
	 * @return
	 */
	public boolean borrarPelicula(String nomPelicula){
		boolean salida = false;
		salida = eliminar(nomPelicula);
		return salida;
	}

	public Comparable[] listarActoresYActrices(String nomPelicula) {
		Comparable[] salida = null;
		Pelicula pelicula = buscarPelicula(nomPelicula);
		if(pelicula != null)
			/*Para mostrar los actores se podria usar el metodo ordenar()
			 *que devuelve a los actores de forma ordenada de un array
			 *pero como ya los insertamos de forma ordenada no tiene sentido hacerlo
			 */
			salida = pelicula.getActores().mostrar();
		
		return salida;
	}
	
	
}
