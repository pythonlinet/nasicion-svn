package uy.edu.ucu.pii.obligatorio1.test;

import org.junit.Before;
import org.junit.Test;

import uy.edu.ucu.pii.obligatorio1.Obligatorio;
import uy.edu.ucu.pii.obligatorio1.gnasi.datos.Actor;
import uy.edu.ucu.pii.obligatorio1.gnasi.datos.Categoria;
import uy.edu.ucu.pii.obligatorio1.gnasi.exec.SoloPeliculasException;
import uy.edu.ucu.pii.obligatorio1.gnasi.exec.ValorNoPermitidoException;
import uy.edu.ucu.pii.obligatorio1.repo.Actores;
import uy.edu.ucu.pii.obligatorio1.repo.Categorias;
import uy.edu.ucu.pii.obligatorio1.repo.Peliculas;
import uy.edu.ucu.pii.obligatorio1.util.LectorArchivos;

import junit.framework.TestCase;

public class TestPropio extends TestCase{
	protected Obligatorio obliga;
	protected String nombreArchivoActores = "data/DataActores.in";
	protected String nombreArchivoActoresPeliculas = "data/DataActoresPeliculas.in";
	protected String nombreArchivoCategorias = "data/DataCategorias.in";
	protected String nombreArchivoPeliculas = "data/DataPeliculas.in";
	
	
	
	Peliculas peliculas;
	Actores actores;
	Categorias categorias;
	
	
	//*************************************************************************/
	/**
	 * Método utilitario para la carga de información de los actores desde el archivo
	 */
	@SuppressWarnings("unused")
	private void cargarActores() {
		String[] dataActores = LectorArchivos.leerArchivo(nombreArchivoActores);
		
		for (int i = 0; i < dataActores.length; i++) {
			String cadena = dataActores[i];
			String[] datos = cadena.split(";");
			String nomActor = datos[0];
			String sexo = datos[1];
			int tamanioListaActoresAntes = obliga.listadoActoresActrices().length;
			assertTrue("Debe ser posible agregar actores inexistentes",obliga.agregarActor(nomActor, sexo));
			assertFalse("No debe ser posible agregar actores con nombres duplicados",obliga.agregarActor(nomActor, sexo));
			int tamanioListaActoresDespues = obliga.listadoActoresActrices().length;
			assertTrue("El listado de acrtrices y actores debe haber crecido en 1 al agregar 1 actor nuevo",tamanioListaActoresDespues-tamanioListaActoresAntes == 1);
			assertNotNull("Debo poder encontrar un actor que acabo de insertar",obliga.obtenerNombreActor(nomActor));
			assertEquals("El sexo del actor debe ser el que se cargó anteriormente",sexo, obliga.obtenerSexoActor(nomActor));
			assertEquals("Es necesario poder recuperar un actor que acabo de agregar",obliga.obtenerNombreActor(nomActor), nomActor);
		}
	}
	
	
	
	
	
	
	//*************************************************************************/
	
	
	
	
	@Before
	public void setUp(){
		peliculas = new Peliculas();
		actores = new Actores();
		categorias = new Categorias();
		
	}
	
	@Test
	public void testPeliculas(){
		peliculas = new Peliculas();
		assertTrue(peliculas.agregarPelicula("Bolt"));
		assertFalse(peliculas.agregarPelicula("Bolt"));
		
		assertEquals(peliculas.buscarPelicula("Bolt").getNombre(), "Bolt");
		assertTrue(peliculas.borrarPelicula("Bolt"));
		assertNull(peliculas.buscarPelicula("Bolt"));
		
	}
	
	@Test
	public void testActores(){
		String nomActor = "Zeta Johnes";
		String sexo = "F";
		assertTrue(actores.agregarActor(nomActor, sexo));
		assertFalse(actores.agregarActor(nomActor, sexo));
		
		nomActor = "Cuba Gooding Jr";
		sexo = "M";
		assertTrue(actores.agregarActor(nomActor, sexo));
	}
	
	@Test
	public void testCategorias() {
		String nomCategoria = "Mejor Pelicula";
		
		try {
			assertTrue(categorias.agregarCategoria(nomCategoria,"f"));
			
			
			
		} catch (ValorNoPermitidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNull(categorias.buscarCategoria(nomCategoria));
		
		try {
			assertTrue(categorias.agregarCategoria(nomCategoria,"P"));
		} catch (ValorNoPermitidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(categorias.buscarCategoria(nomCategoria));
		
	}
	
	@Test
	public void testInsertEnCategoria(){
		Categoria categoria = new Categoria("Actores","A");
		String nomCategoria = "Oscar Mejor Actor";

		try {
			Actor actor1 = new Actor("Will Smith","M");
			Actor actor2 = new Actor("Tommy Lee Jones","M");
			Actor actor3 = new Actor("George Harris","M");
			Actor actor4 = new Actor("Leonardo DiCaprio","M");
			Actor actor5 = new Actor("Adam Sandler","M");
			categoria.nominarActorACategoria(actor1);
			categoria.nominarActorACategoria(actor2);
			categoria.nominarActorACategoria(actor3);
			categoria.nominarActorACategoria(actor4);
			categoria.nominarActorACategoria(actor5);
			
			
		} catch (SoloPeliculasException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
