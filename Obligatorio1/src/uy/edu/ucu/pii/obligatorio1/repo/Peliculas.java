package uy.edu.ucu.pii.obligatorio1.repo;

import uy.edu.ucu.pii.obligatorio1.gnasi.datos.Pelicula;
import uy.edu.ucu.pii.obligatorio1.gnasi.datos.arbol.TArbol;
import uy.edu.ucu.pii.obligatorio1.gnasi.datos.arbol.TNodoAB;

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
	
	
}
