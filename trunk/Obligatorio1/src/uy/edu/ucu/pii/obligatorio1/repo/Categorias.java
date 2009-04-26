package uy.edu.ucu.pii.obligatorio1.repo;

import uy.edu.ucu.pii.obligatorio1.grupo14.datos.Actor;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.Categoria;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.arbol.TArbol;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.arbol.TNodoAB;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.arbol.especiales.TNodoABActor;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.arbol.especiales.TNodoABCategoria;
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
		int votos = 0;
		
		salida = getRaiz().cantidadVotosDeActorOActriz(nomActor);
		
		
		return salida;
	}
	
}
