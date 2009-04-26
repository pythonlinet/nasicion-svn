package uy.edu.ucu.pii.obligatorio1.grupo14.datos;

import uy.edu.ucu.pii.obligatorio1.grupo14.datos.lista.TLista;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.lista.especiales.TListaNominados;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.lista.especiales.TNodoNominados;
import uy.edu.ucu.pii.obligatorio1.grupo14.exec.SoloActoresException;
import uy.edu.ucu.pii.obligatorio1.grupo14.exec.SoloPeliculasException;

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
	 * 
	 * @param actor
	 * @return
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
	 * 
	 * @param nomActor
	 * @return
	 */
	public boolean votarActorEnCategoria(String nomActor) {
		boolean salida = false;
		if (getTipo().equals("A")) {
			TNodoNominados actor = (TNodoNominados) nominados
					.buscarNodo(nomActor);
			if (actor != null) {
				actor.setVotos(actor.getVotos() + 1);
				salida = true;
			}

		}

		return salida;
	}

	/**
	 * 
	 * @return
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
	 * 
	 * @param pelicula
	 * @return
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
	 * 
	 * @param nomPelicula
	 * @return
	 */
	public boolean votarPeliculaEnCategoria(String nomPelicula) {
		boolean salida = false;
		if (getTipo().equals("P")) {
			TNodoNominados pelicula = (TNodoNominados) nominados.buscarNodo(nomPelicula);
			if (pelicula != null) {
				pelicula.setVotos(pelicula.getVotos() + 1);
				salida = true;
			}

		}

		return salida;
	}
	/**
	 * 
	 * @return
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
