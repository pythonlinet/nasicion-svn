package uy.edu.ucu.pii.obligatorio1.gnasi.datos;

import uy.edu.ucu.pii.obligatorio1.gnasi.datos.lista.TLista;
import uy.edu.ucu.pii.obligatorio1.gnasi.datos.lista.especiales.TListaNominados;
import uy.edu.ucu.pii.obligatorio1.gnasi.datos.lista.especiales.TNodoNominados;
import uy.edu.ucu.pii.obligatorio1.gnasi.exec.SoloActoresException;
import uy.edu.ucu.pii.obligatorio1.gnasi.exec.SoloPeliculasException;

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
			throw new SoloActoresException(this.getNomCategoria() + ", " + pelicula.getNombre());

		salida = getNominados().insertarOrdenado(pelicula.getNombre(), pelicula);

		return salida;
	}
}
