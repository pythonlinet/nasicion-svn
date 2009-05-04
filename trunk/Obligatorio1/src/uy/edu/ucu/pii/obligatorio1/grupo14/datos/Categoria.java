package uy.edu.ucu.pii.obligatorio1.grupo14.datos;

import uy.edu.ucu.pii.obligatorio1.grupo14.datos.lista.TLista;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.lista.especiales.TListaNominados;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.lista.especiales.TNodoNominados;
import uy.edu.ucu.pii.obligatorio1.grupo14.exec.SoloActoresException;
import uy.edu.ucu.pii.obligatorio1.grupo14.exec.SoloPeliculasException;

/**
 * Clase usada para representar una categoria y sus nominados en el TDA
 * @author Grupo14
 * @version 1.0
 * @see uy.edu.ucu.pii.obligatorio1.grupo14.datos.Actor
 * @see uy.edu.ucu.pii.obligatorio1.grupo14.datos.Pelicula
 */
public class Categoria {
	private String nomCategoria;
	private String tipo;
	private TListaNominados nominados;

	// **************************************************************************/
	public String getNomCategoria() {
		return nomCategoria;
	}

	public String getTipo() {
		return tipo;
	}

	public TLista getNominados() {
		return nominados;
	}

	// ******************************************************************************/

	public Categoria(String nomCategoria, String tipo) {
		this.nomCategoria = nomCategoria;
		this.tipo = tipo;
		this.nominados = new TListaNominados();

	}

	/**
	 * Nomina a un actor a la categoria
	 * @param actor
	 * @return true - si se pudo nominar el actor; false - si el actor ya esta nominado o la categoria no es de actores
	 * @throws SoloPeliculasException
	 */
	public boolean nominarActorACategoria(Actor actor)
			throws SoloPeliculasException {
		boolean salida = false;

		if (this.tipo.equals("P"))
			throw new SoloPeliculasException(this.getNomCategoria() + ", " + actor.getNombre());

		salida = getNominados().insertarOrdenado(actor.getNombre(), actor);

		return salida;
	}

	/**
	 * Suma 1 voto al actor en la categoria
	 * @param nomActor
	 * @return true - si se pudo incrementar el voto del actor; false - si el actor no esta en la categoria o la categoria no es de actores
	 */
	public boolean votarActorEnCategoria(String nomActor) {
		boolean salida = false;
		if (getTipo().equals("A")) {
			TNodoNominados actor = (TNodoNominados) nominados.buscarNodo(nomActor);
			if (actor != null) {
				//Aumento los votos totales del actor
				((Actor)actor.getElemento()).setVotos(((Actor)actor.getElemento()).getVotos() + 1);
				
				//Aumento los votos del actor dentro de la categoria
				actor.setVotos(actor.getVotos() + 1);
				salida = true;
			}

		}

		return salida;
	}

	/**
	 * Metodo que retorna el ganador dentro de la categoria
	 * @return unString - nombre del ganador de la categoria Actor/Pelicula
	 */
	public String ganadorEnCategoria() {
		String salida = null;
		if (!nominados.esVacia()) {
			TNodoNominados aux = nominados.getPrimero();
			TNodoNominados puntero = aux.getSiguiente();

			while (puntero != null) {
				if (aux.getVotos() < puntero.getVotos())
					aux = puntero;

				puntero = puntero.getSiguiente();
			}
			// Usamos el metodo toString para guardar la clave en la variable
			// salida
			salida = aux.getClave().toString();
		}

		return salida;
	}

	/**
	 * Metodo para nominar peliculas a una categoria
	 * @param pelicula pelicula a nominar
	 * @return true - si se nomino la pelicula; false - si la pelicula ya estaba nominada o si la categoria no es de peliculas
	 * @throws SoloActoresException
	 */
	public boolean nominarPeliculaACategoria(Pelicula pelicula)
			throws SoloActoresException {
		boolean salida = false;

		if (this.tipo.equals("A"))
			//Si la categoria era de Actores y no de Peliculas lanzo la excepción
			throw new SoloActoresException(this.getNomCategoria() + ", " + pelicula.getNombre());

		salida = getNominados().insertarOrdenado(pelicula.getNombre(), pelicula);

		return salida;
	}

	/**
	 * Metodo para incrementar en 1 la cantidad de votos de una pelicula en una categoria
	 * @param nomPelicula nombre de la pelicula
	 * @return true - si se pudo incrementar la cantidad de votos; false - si la pelicula no está en la categoria o si la categoria no es de peliculas
	 */
	public boolean votarPeliculaEnCategoria(String nomPelicula) {
		boolean salida = false;
		if (getTipo().equals("P")) {
			TNodoNominados pelicula = (TNodoNominados) nominados.buscarNodo(nomPelicula);
			if (pelicula != null) {
				//Aumento los votos totales de la pelicula
				((Pelicula)pelicula.getElemento()).setVotos(((Pelicula)pelicula.getElemento()).getVotos() + 1);
				
				
				pelicula.setVotos(pelicula.getVotos() + 1);
				salida = true;
			}

		}

		return salida;
	}
	/**
	 * Retorna la cantidad de votos que tiene la categoria
	 * @return unNumero - cantidad de votos dentro de la categoria
	 */
	public int cantidadVotosEnCategoria() {
		int salida = 0;
		TNodoNominados nodo = nominados.getPrimero();
		while(nodo != null){
			salida += nodo.getVotos();
			nodo = nodo.getSiguiente();
		}
			
		return salida;
	}
}
