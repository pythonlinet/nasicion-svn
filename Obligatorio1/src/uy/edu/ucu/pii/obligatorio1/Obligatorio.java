package uy.edu.ucu.pii.obligatorio1;

import uy.edu.ucu.pii.obligatorio1.gnasi.datos.Actor;
import uy.edu.ucu.pii.obligatorio1.gnasi.datos.Categoria;
import uy.edu.ucu.pii.obligatorio1.gnasi.datos.Pelicula;
import uy.edu.ucu.pii.obligatorio1.gnasi.exec.SoloPeliculasException;
import uy.edu.ucu.pii.obligatorio1.gnasi.exec.ValorNoPermitidoException;
import uy.edu.ucu.pii.obligatorio1.repo.Actores;
import uy.edu.ucu.pii.obligatorio1.repo.Categorias;
import uy.edu.ucu.pii.obligatorio1.repo.Peliculas;


/**
 * @author catedraPII
 *
 */
public class Obligatorio {
	public Actores actores;
	private Peliculas peliculas;
	private Categorias categorias;

	
	public Obligatorio(){
		actores = new Actores();
		peliculas = new Peliculas();
		categorias = new Categorias();
	}
	
	/**
	 * @param nomCategoria
	 * @param tipo
	 * @return true si la carga es válida
	 * Método utilizado para cargar datos de una categoría
	 */
	public boolean agregarCategoria(String nomCategoria, String tipo) {
		boolean salida = false;
		
		try {
			salida = categorias.agregarCategoria(nomCategoria, tipo);
		} catch (ValorNoPermitidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return salida;
	}

	/**
	 * @param nomCategoria
	 * @return nombre de la categoría, en caso de haberla encontrado
	 * Método utilizado para recuperar datos de una categoría
	 */
	public String obtenerNombreCategoria(String nomCategoria) {
		String salida = null;
		Categoria categoria = categorias.buscarCategoria(nomCategoria);
		if(categoria != null)
			salida = categoria.getNomCategoria();
		
		return salida;
	}
	
	/**
	 * @param nomCategoria
	 * @return tipo de la categoría, en caso de haberla encontrado
	 * Método utilizado para recuperar datos de una categoría 
	 */
	public String obtenerTipoCategoria(String nomCategoria) {
		String salida = null;
		Categoria categoria = categorias.buscarCategoria(nomCategoria);
		if(categoria != null)
			salida = categoria.getTipo();
		
		return salida;
	}

	
	/**
	 * @param nomActor
	 * @param sexo
	 * @return true si la carga es válida
	 * Método utilizado para cargar datos de una categoría
	 */
	public boolean agregarActor(String nomActor, String sexo) {
		return actores.agregarActor(nomActor, sexo);
	}
	
	/**
	 * @param nomActor
	 * @return nombre del actor, en caso de haberlo encontrado
	 * Método utilizado para recuperar datos de un actor
	 */
	public String obtenerNombreActor(String nomActor) {
		return actores.buscarActor(nomActor).getNombre();
	}
	
	/**
	 * @param nomActor
	 * @return sexo del actor, en caso de haberlo encontrado
	 * Método utilizado para recuperar datos de un actor
	 */
	public String obtenerSexoActor(String nomActor) {
		return actores.buscarActor(nomActor).getSexo();
	}

	
	/**
	 * @param nomPelicula
	 * @return true si la carga es válida
	 * Método utilizado para cargar datos de una película
	 */
	public boolean agregarPelicula(String nomPelicula) {
		return peliculas.agregarPelicula(nomPelicula);
	}
	
	/**
	 * @param nomPelicula
	 * @return nombre de la película, en caso de haberla encontrado
	 * Método utilizado para recuperar datos de una película
	 */
	public String obtenerNombrePelicula(String nomPelicula) {
		return peliculas.buscarPelicula(nomPelicula).getNombre();
	}
	
	/**
	 * @param nomActor
	 * @param nomPelicula
	 * @return true si la asociación es válida
	 * Método utilizado para asociar un actor a una película
	 */
	public boolean asignarActorAPelicula(String nomActor, String nomPelicula) {
		boolean result = false;
		Actor actor = actores.buscarActor(nomActor);
		if(actor != null){
			Pelicula pelicula = peliculas.buscarPelicula(nomPelicula);
			if(pelicula != null){
				result = pelicula.agregarActor(actor);
			}
		}
		
		return result;
	}
	
	/**
	 * @param nomActor
	 * @param nomPelicula
	 * @return nombre del actor, en caso de estar asociado a dicha película
	 * Método utilizado para validar la asignación de un actor a una película
	 */
	public String buscarActorEnPelicula(String nomActor, String nomPelicula) {
		String result = null;
		Pelicula pelicula = peliculas.buscarPelicula(nomPelicula);

		if(pelicula != null){
			Actor actor = pelicula.buscarActor(nomActor);
			if(actor != null)
				result = actor.getNombre();
		}
		
		return result;
	}
	
	/**
	 * @param nomActor
	 * @param nomCategoria
	 * @return true si la nominación es válida
	 * Método para cargar la nominación de un actor en una categoría dada
	 */
	public boolean nominarActorACategoria(String nomActor, String nomCategoria) {
		boolean salida = false;
		Categoria categoria = categorias.buscarCategoria(nomCategoria);
		if(categoria != null){
			Actor actor = actores.buscarActor(nomActor);
			if(actor != null){
				try {
					salida = categoria.nominarActorACategoria(actor);
				} catch (SoloPeliculasException e) {
					/*Se captura la excepcion que es lanzada en
					 * el caso de que se quiera insertar un actor en una categoria de peliculas
					 */
				}
			}
		}
		return salida;
	}
	
	/**
	 * @param nomPelicula
	 * @param nomCategoria
	 * @return true si la nominación es válida
	 * Método para cargar la nominación de una película en una categoría dada
	 */
	public boolean nominarPeliculaACategoria(String nomPelicula, String nomCategoria) {
		//TODO - Implementar método
		return false;
	}

	/**
	 * @param nomPelicula
	 * @param nomCategoria
	 * @return true si el voto es válido
	 * Método para cargar un voto a una película nominada en una categoría
	 */
	public boolean votarPeliculaEnCategoria(String nomPelicula, String nomCategoria) {
		//TODO - Implementar método
		return false;
	}
	
	/**
	 * @param nomActor
	 * @param nomCategoria
	 * @return true si el voto es válido
	 * Método para cargar un voto a un actor nominado en una categoría
	 */
	public boolean votarActorEnCategoria(String nomActor, String nomCategoria) {
		//TODO - Implementar método
		return false;
	}

	/**
	 * @return
	 * Corresponde al listado 2.2.2
	 */
	@SuppressWarnings("unchecked")
	public Comparable[] listadoActoresActrices () {
		//TODO - Implementar método
		return actores.inOrden();
	}
	
	/**
	 * @return
	 * Corresponde al listado 2.2.3
	 */
	@SuppressWarnings("unchecked")
	public Comparable[] listadoPeliculas() {
		return peliculas.inOrden();
	}
	
	/**
	 * @return
	 * Corresponde al listado 2.2.4
	 */
	@SuppressWarnings("unchecked")	
	public Comparable[] listadoActrices() {
		return actores.listadoActrices();
	}
	
	/**
	 * @param nomCategoria
	 * @return
	 * Corresponde a la consulta 2.2.5
	 */
	public String ganadorEnCategoria (String nomCategoria) {
		//TODO - Implementar método
		return null;
	}
	
	/**
	 * @param nomCategoria
	 * @return
	 * Corresponde a la consulta 2.2.6
	 */
	public int cantidadVotosEnCategoria (String nomCategoria) {
		//TODO - Implementar método
		return -1;
	}
	
	/**
	 * @param nomPelicula
	 * @return
	 * Corresponde a la consulta 2.2.7
	 */
	public int cantidadVotosDePelicula(String nomPelicula) {
		//TODO - Implementar método
		return -1;
	}
	
	/**
	 * @param nomActor
	 * @return
	 * Corresponde a la consulta 2.2.8
	 */
	public int cantidadVotosDeActorOActriz(String nomActor) {
		//TODO - Implementar método
		return -1;
	}
	
	/**
	 * @param nomPelicula
	 * @return
	 * Corresponde a la consulta 2.2.9
	 */
	@SuppressWarnings("unchecked")
	public Comparable[] listarActoresYActrices(String nomPelicula) {
		//TODO - Implementar método
		return null;
	}
	
	/**
	 * @return
	 * Corresponde a la consulta 2.2.10 
	 */
	@SuppressWarnings("unchecked")
	public Comparable[] listarPeliculasMasVotadas () {
		//TODO - Implementar método
		return null;
	}
}
