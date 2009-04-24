package uy.edu.ucu.pii.obligatorio1.repo;

import uy.edu.ucu.pii.obligatorio1.gnasi.datos.Categoria;
import uy.edu.ucu.pii.obligatorio1.gnasi.datos.arbol.TArbol;
import uy.edu.ucu.pii.obligatorio1.gnasi.datos.arbol.TNodoAB;
import uy.edu.ucu.pii.obligatorio1.gnasi.exec.ValorNoPermitidoException;

public class Categorias extends TArbol {
	private final String VALOR_NO_PERMITIDO = "Valor no permitido";
	
	/**
	 * 
	 * @param nomCategoria
	 * @param tipo
	 * @return
	 */
	public boolean agregarCategoria(String nomCategoria, String tipo) throws ValorNoPermitidoException{
	
		/*Si el tipo no coincide con ninguna de las entradas permitidas lanzamos una excepcion
		 * los tipos permitidos son:
		 * 												P : Peliculas
		 * 												A : Actores
		 */
		
		if(!tipo.equals("A") && !tipo.equals("P"))
			throw new ValorNoPermitidoException(VALOR_NO_PERMITIDO);
		
		return insertar(nomCategoria, new Categoria(nomCategoria,tipo));
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
	
}
