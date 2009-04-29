package uy.edu.ucu.pii.obligatorio1.grupo14.repo;

import uy.edu.ucu.pii.obligatorio1.grupo14.datos.Actor;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.Categoria;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.Pelicula;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.arbol.TArbol;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.arbol.TNodoAB;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.arbol.especiales.TNodoABActor;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.arbol.especiales.TNodoABCategoria;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.lista.TLista;
import uy.edu.ucu.pii.obligatorio1.grupo14.exec.ValorNoPermitidoException;

public class Categorias extends TArbol {
	private final String VALOR_NO_PERMITIDO = "Valor no permitido";
	private TNodoABCategoria raiz = null;
	
	
	//********************************************************/
	public TNodoABCategoria getRaiz() {
		return raiz;
	}


	public void setRaiz(TNodoABCategoria raiz) {
		this.raiz = raiz;
	}
	//********************************************************/

	/**
	 * 
	 * @param nomCategoria
	 * @param tipo
	 * @return
	 */
	public boolean agregarCategoria(String nomCategoria, String tipo) throws ValorNoPermitidoException{
		boolean salida = false;
		/*Si el tipo no coincide con ninguna de las entradas permitidas lanzamos una excepcion
		 * los tipos permitidos son:
		 * 												P : Peliculas
		 * 												A : Actores
		 */
		if(!tipo.equals("A") && !tipo.equals("P"))
			throw new ValorNoPermitidoException(VALOR_NO_PERMITIDO);
		
		if (vacio()) {
			setRaiz(new TNodoABCategoria(nomCategoria, new Categoria(nomCategoria,tipo)));
			salida = true;
		} else
			salida = getRaiz().insertar(nomCategoria,  new Categoria(nomCategoria,tipo));

		return salida;
	}
	
	
	/**
	 * 
	 * @param nomCategoria
	 * @return
	 */
	public Categoria buscarCategoria(String nomCategoria){
		Categoria result = null;
		TNodoAB categoria = buscar(nomCategoria);
		
		if(categoria != null)
			result = (Categoria)categoria.getElemento();
		
		return result; 
	}

	/**
	 * 
	 * @param nomActor
	 * @return
	 */
	public int cantidadVotosDeActorOActriz(String nomActor) {
		int salida = -1;
		
		salida = getRaiz().cantidadVotosDeActorOActriz(nomActor);
	
		return salida;
	}


	public int cantidadVotosDePelicula(String nomPelicula) {
		int salida = -1;
		
		salida = getRaiz().cantidadVotosDePelicula(nomPelicula);
		
		return salida;
	}


	public Comparable[] listarToFivePeliculas(Comparable[] listaPeliculas) {
		Comparable[] salida = new Comparable[5];
		TLista peliculasOrdenadasPorVotos = new TLista();
		int votos;
		for(Comparable pelicula : listaPeliculas){
			votos = cantidadVotosDePelicula(pelicula.toString());
			peliculasOrdenadasPorVotos.insertar(votos, pelicula);
		}
		peliculasOrdenadasPorVotos.ordenar();
		peliculasOrdenadasPorVotos.invertir();
			
		for(int i = 0; i < 5; i++)
			salida[i] = peliculasOrdenadasPorVotos.recuperar(i).getElemento().toString();
		
		return salida;
	}
	
}
