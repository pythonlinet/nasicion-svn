package uy.edu.ucu.pii.obligatorio1;

import uy.edu.ucu.pii.obligatorio1.grupo14.datos.Actor;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.Categoria;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.Pelicula;
import uy.edu.ucu.pii.obligatorio1.grupo14.exec.SoloActoresException;
import uy.edu.ucu.pii.obligatorio1.grupo14.exec.SoloPeliculasException;
import uy.edu.ucu.pii.obligatorio1.grupo14.exec.ValorNoPermitidoException;
import uy.edu.ucu.pii.obligatorio1.grupo14.repo.Actores;
import uy.edu.ucu.pii.obligatorio1.grupo14.repo.Categorias;
import uy.edu.ucu.pii.obligatorio1.grupo14.repo.Peliculas;


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
	 * @return true si la carga es v�lida
	 * M�todo utilizado para cargar datos de una categor�a
	 */
	public boolean agregarCategoria(String nomCategoria, String tipo) {
		boolean salida = false;
		
		try {
			salida = categorias.agregarCategoria(nomCategoria, tipo);
		} catch (ValorNoPermitidoException e) {
			e.printStackTrace();
		}
		
		return salida;
	}

	/**
	 * @param nomCategoria
	 * @return nombre de la categor�a, en caso de haberla encontrado
	 * M�todo utilizado para recuperar datos de una categor�a
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
	 * @return tipo de la categor�a, en caso de haberla encontrado
	 * M�todo utilizado para recuperar datos de una categor�a 
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
	 * @return true si la carga es v�lida
	 * M�todo utilizado para cargar datos de una categor�a
	 */
	public boolean agregarActor(String nomActor, String sexo) {
		return actores.agregarActor(nomActor, sexo);
	}
	
	/**
	 * @param nomActor
	 * @return nombre del actor, en caso de haberlo encontrado
	 * M�todo utilizado para recuperar datos de un actor
	 */
	public String obtenerNombreActor(String nomActor) {
		return actores.buscarActor(nomActor).getNombre();
	}
	
	/**
	 * @param nomActor
	 * @return sexo del actor, en caso de haberlo encontrado
	 * M�todo utilizado para recuperar datos de un actor
	 */
	public String obtenerSexoActor(String nomActor) {
		return actores.buscarActor(nomActor).getSexo();
	}

	
	/**
	 * @param nomPelicula
	 * @return true si la carga es v�lida
	 * M�todo utilizado para cargar datos de una pel�cula
	 */
	public boolean agregarPelicula(String nomPelicula) {
		return peliculas.agregarPelicula(nomPelicula);
	}
	
	/**
	 * @param nomPelicula
	 * @return nombre de la pel�cula, en caso de haberla encontrado
	 * M�todo utilizado para recuperar datos de una pel�cula
	 */
	public String obtenerNombrePelicula(String nomPelicula) {
		return peliculas.buscarPelicula(nomPelicula).getNombre();
	}
	
	/**
	 * @param nomActor
	 * @param nomPelicula
	 * @return true si la asociaci�n es v�lida
	 * M�todo utilizado para asociar un actor a una pel�cula
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
	 * @return nombre del actor, en caso de estar asociado a dicha pel�cula
	 * M�todo utilizado para validar la asignaci�n de un actor a una pel�cula
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
	 * @return true si la nominaci�n es v�lida
	 * M�todo para cargar la nominaci�n de un actor en una categor�a dada
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
	 * @return true si la nominaci�n es v�lida
	 * M�todo para cargar la nominaci�n de una pel�cula en una categor�a dada
	 */
	public boolean nominarPeliculaACategoria(String nomPelicula, String nomCategoria) {
		boolean salida = false;
		Categoria categoria = categorias.buscarCategoria(nomCategoria);
		if(categoria != null){
			Pelicula pelicula = peliculas.buscarPelicula(nomPelicula);
			if(pelicula != null){
				try {
					salida = categoria.nominarPeliculaACategoria(pelicula);
				} catch (SoloActoresException e) {
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
	 * @return true si el voto es v�lido
	 * M�todo para cargar un voto a una pel�cula nominada en una categor�a
	 */
	public boolean votarPeliculaEnCategoria(String nomPelicula, String nomCategoria) {
		boolean salida = false;
		Categoria categoria = categorias.buscarCategoria(nomCategoria);
		if(categoria != null){
			salida = categoria.votarPeliculaEnCategoria(nomPelicula);
		}
		
		
		return salida;
	}
	
	/**
	 * @param nomActor
	 * @param nomCategoria
	 * @return true si el voto es v�lido
	 * M�todo para cargar un voto a un actor nominado en una categor�a
	 */
	public boolean votarActorEnCategoria(String nomActor, String nomCategoria) {
		boolean salida = false;
		Categoria categoria = categorias.buscarCategoria(nomCategoria);
		if(categoria != null){
			salida = categoria.votarActorEnCategoria(nomActor);
		}
		
		
		return salida;
	}

	/**
	 * @return
	 * Corresponde al listado 2.2.2
	 */
	@SuppressWarnings("unchecked")
	public Comparable[] listadoActoresActrices () {
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
		String salida = null;
		Categoria categoria = categorias.buscarCategoria(nomCategoria);
		if(categoria != null){
			salida = categoria.ganadorEnCategoria();
		}
		return salida;
	}
	
	/**
	 * @param nomCategoria
	 * @return
	 * Corresponde a la consulta 2.2.6
	 */
	public int cantidadVotosEnCategoria (String nomCategoria) {
		int salida = -1;
		Categoria categoria = categorias.buscarCategoria(nomCategoria);
		if(categoria != null){
			salida = categoria.cantidadVotosEnCategoria();
		}
		return salida;
	}
	
	/**
	 * @param nomPelicula
	 * @return
	 * Corresponde a la consulta 2.2.7
	 */
	public int cantidadVotosDePelicula(String nomPelicula) {
		int salida = -1;
		
		Pelicula pelicula = peliculas.buscarPelicula(nomPelicula);
		if(pelicula != null)
			salida = pelicula.getVotos();
		
		return salida;
	}
	
	/**
	 * @param nomActor
	 * @return
	 * Corresponde a la consulta 2.2.8
	 */
	public int cantidadVotosDeActorOActriz(String nomActor) {
		int salida = -1;
		Actor actor = actores.buscarActor(nomActor);
		if(actor != null)
			salida = actor.getVotos();
		return salida;
	}
	
	/**
	 * @param nomPelicula
	 * @return
	 * Corresponde a la consulta 2.2.9
	 */
	@SuppressWarnings("unchecked")
	public Comparable[] listarActoresYActrices(String nomPelicula) {
		Comparable[] salida = null;
		salida = peliculas.listarActoresYActrices(nomPelicula);
		return salida;
	}
	
	/**
	 * @return
	 * Corresponde a la consulta 2.2.10 
	 */
	@SuppressWarnings("unchecked")
	public Comparable[] listarPeliculasMasVotadas () {
		Comparable[] salida = null;
		if(!categorias.vacio()){
				Comparable[] listaPeliculas = peliculas.inOrden();
				salida = categorias.listarToFivePeliculas(listaPeliculas);
		}
		return salida;
	}
}
