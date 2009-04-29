package uy.edu.ucu.pii.obligatorio1.test;
import java.util.Random;

import uy.edu.ucu.pii.obligatorio1.Obligatorio;
import uy.edu.ucu.pii.obligatorio1.util.LectorArchivos;
import junit.framework.TestCase;


public class TestObligatorio extends TestCase {
	protected Obligatorio obliga;
	protected String nombreArchivoActores = "data/DataActores.in";
	protected String nombreArchivoActoresPeliculas = "data/DataActoresPeliculas.in";
	protected String nombreArchivoCategorias = "data/DataCategorias.in";
	protected String nombreArchivoPeliculas = "data/DataPeliculas.in";
	
	/**
	 * M�todo utilitario para la carga de informaci�n de los actores desde el archivo
	 */
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
			assertEquals("El sexo del actor debe ser el que se carg� anteriormente",sexo, obliga.obtenerSexoActor(nomActor));
			assertEquals("Es necesario poder recuperar un actor que acabo de agregar",obliga.obtenerNombreActor(nomActor), nomActor);
		}
	}
	
	/**
	 * M�todo utilitario para la carga de informaci�n de las pel�culas desde el archivo
	 */
	private void cargarPeliculas() {
		String[] dataPeliculas = LectorArchivos.leerArchivo(nombreArchivoPeliculas);
		
		for (int i = 0; i < dataPeliculas.length; i++) {
			String cadena = dataPeliculas[i];
			String[] datos = cadena.split(";");
			String nomPelicula = datos[0];
			int tamanioListaPeliculasAntes = obliga.listadoPeliculas().length;
			assertTrue("Debe ser posible agregar pel�culas inexistentes",obliga.agregarPelicula(nomPelicula));
			assertFalse("No debe ser posible agregar pel�culas con nombres duplicados",obliga.agregarPelicula(nomPelicula));
			int tamanioListaPeliculasDespues = obliga.listadoPeliculas().length;
			assertTrue("El listado de pel�culas debe haber crecido en 1 al agregar 1 actor nuevo",tamanioListaPeliculasDespues-tamanioListaPeliculasAntes == 1);			
			assertNotNull(obliga.obtenerNombrePelicula(nomPelicula));
			assertEquals(obliga.obtenerNombrePelicula(nomPelicula), nomPelicula);
		}
	}

	/**
	 * M�todo utilitario para la carga de informaci�n de asociaciones entre actores y pel�culas desde el archivo
	 */
	private void cargarActoresPeliculas() {
		String[] dataActoresPeliculas = LectorArchivos.leerArchivo(nombreArchivoActoresPeliculas);
		
		for (int i = 0; i < dataActoresPeliculas.length; i++) {
			String cadena = dataActoresPeliculas[i];
			String[] datos = cadena.split(";");
			String nomPelicula = datos[0];
			String nomActor = datos[1];
			assertTrue("Debe ser posible agregar un actor existente a una pel�cula existente",obliga.asignarActorAPelicula(nomActor, nomPelicula));
			assertFalse("No puedo permitir agregar una combinaci�n actor/pel�cula inv�lida",obliga.asignarActorAPelicula(nomActor+"kjndsfkjn", nomPelicula));
			assertFalse("No puedo permitir agregar una combinaci�n actor/pel�cula inv�lida",obliga.asignarActorAPelicula(nomActor, nomPelicula+"ksjdnfdjn"));
			
			String nomActorBus = obliga.buscarActorEnPelicula(nomActor, nomPelicula);
			assertNotNull(nomActorBus);
			assertEquals(nomActorBus, nomActor);
		}
	}
	
	/**
	 * M�todo utilitario para la carga de informaci�n de las categor�as desde el archivo
	 */
	private void cargarCategorias() {
		String[] dataCategorias = LectorArchivos.leerArchivo(nombreArchivoCategorias);
		
		for (int i = 0; i < dataCategorias.length; i++) {
			String cadena = dataCategorias[i];
			String[] datos = cadena.split(";");
			String nomCategoria = datos[0];
			String tipo = datos[1];
			assertTrue("Debe ser posible agregar categor�as inexistentes",obliga.agregarCategoria(nomCategoria, tipo));
			assertFalse("No debe ser posible agregar categor�as con nombres duplicados",obliga.agregarCategoria(nomCategoria, tipo));
			assertNotNull(obliga.obtenerNombreCategoria(nomCategoria));
			assertEquals(tipo, obliga.obtenerTipoCategoria(nomCategoria));
			assertEquals(obliga.obtenerNombreCategoria(nomCategoria), nomCategoria);
		}
	}
	
	/**
	 * @param datosEsperados
	 * @param datosObtenidos
	 * @param mensajeError
	 * M�todo utilitario para validar que el contenido de dos arrays es igual
	 */
	@SuppressWarnings("unchecked")
	private void validarArraysIguales(String[] datosEsperados, Comparable[] datosObtenidos, String mensajeError) {
		/*
		 * Nota: No se utiliza assertEquals() con los dos arrays "enteros" porque dependo de c�mo fueron construidos los arreglos para que sean iguales, 
		 * y a m� lo que me interesa es el contenido que tienen
		 * O sea, si yo tengo:
		 * 	Comparable[] a1 = new Comparable[]{"1","2","3"}
		 *  String[] a2 = new String[]{"1","2","3"}
		 *  
		 *  assertEquals(a1, a2) va a fallar, porque no son del mismo tipo (fueron creados como objetos de distinto tipo)
		 */
		assertEquals(mensajeError, datosEsperados.length, datosObtenidos.length);
		for (int i = 0; i < datosEsperados.length; i++) {
			assertEquals(mensajeError,datosEsperados[i], datosObtenidos[i]);
		}
	}

	
	protected void inicializarObli() {
		obliga = new Obligatorio();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	public void setUp() {
		inicializarObli();
		cargarActores();
		cargarPeliculas();
		cargarCategorias();
		cargarActoresPeliculas();
	}

	protected String nombreArchivoListadoActoresEsperado = "data/ListadoActoresEsperado.txt";
	/**
	 * Valida que el listado de actores coincida con el indicado por el archivo correspondiente
	 */
	@SuppressWarnings("unchecked")
	public void testListadoActores () {
		String[] listadoActoresEsperado = LectorArchivos.leerArchivo(nombreArchivoListadoActoresEsperado);
		Comparable[] listadoActoresActricesObtenido = obliga.listadoActoresActrices();
		assertNotNull("El listado de actores y actrices no puede ser nulo",listadoActoresActricesObtenido);
		validarArraysIguales(listadoActoresEsperado, listadoActoresActricesObtenido, "El listado de actores debe coincidir con el esperado");
	}
	
	protected String nombreArchivoListadoPeliculasEsperado = "data/ListadoPeliculasEsperado.txt";
	/**
	 * Valida que el listado de actores coincida con el indicado por el archivo correspondiente
	 */
	@SuppressWarnings("unchecked")
	public void testListadoPeliculas () {
		String[] listadoPeliculasEsperado = LectorArchivos.leerArchivo(nombreArchivoListadoPeliculasEsperado); 
		Comparable[] listadoPeliculasObtenido = obliga.listadoPeliculas();
		assertNotNull(listadoPeliculasObtenido);
		validarArraysIguales(listadoPeliculasEsperado, listadoPeliculasEsperado, "El listado de pel�culas debe coincidir con el esperado");
	}
	
	protected String nombreArchivoListadoActricesEsperado = "data/ListadoActricesEsperado.txt";
	/**
	 * Valida que el listado de actores coincida con el indicado por el archivo correspondiente
	 */
	@SuppressWarnings("unchecked")
	public void testListadoDeActrices() {
		String[] listadoActricesEsperado = LectorArchivos.leerArchivo(nombreArchivoListadoActricesEsperado);		
		Comparable[] listadoActricesObtenido = obliga.listadoActrices();		
		assertNotNull(listadoActricesObtenido);
		validarArraysIguales(listadoActricesEsperado, listadoActricesObtenido, "El listado de actrices debe coincidir con el esperado");
	}

	/**
	 * Vota algunos actores para una categor�a dada, y valida que el resultado de la votaci�n sea el esperado
	 */
	public void testOscarMejorActor() {
		String datosEsperados = "Tommy Lee Jones";
		String nomCategoria = "Oscar al Mejor Actor";
		
		assertTrue(obliga.nominarActorACategoria("Will Smith", nomCategoria));
		assertTrue(obliga.nominarActorACategoria("Tommy Lee Jones", nomCategoria));
		assertTrue(obliga.nominarActorACategoria("George Harris", nomCategoria));
		assertTrue(obliga.nominarActorACategoria("Leonardo DiCaprio", nomCategoria));
		assertTrue(obliga.nominarActorACategoria("Adam Sandler", nomCategoria));

		assertTrue(obliga.votarActorEnCategoria("Adam Sandler", nomCategoria));
		assertTrue(obliga.votarActorEnCategoria("Tommy Lee Jones", nomCategoria));
		assertTrue(obliga.votarActorEnCategoria("George Harris", nomCategoria));
		assertTrue(obliga.votarActorEnCategoria("Adam Sandler", nomCategoria));
		assertTrue(obliga.votarActorEnCategoria("Tommy Lee Jones", nomCategoria));
		assertTrue(obliga.votarActorEnCategoria("Tommy Lee Jones", nomCategoria));
		
		assertFalse("No es posible votar a actores no nominados a categor�as",obliga.votarActorEnCategoria("Cuba Gooding Jr", nomCategoria));
		
		String ganador = obliga.ganadorEnCategoria(nomCategoria);
		assertNotNull(ganador);
		assertEquals(datosEsperados, ganador);
	}
	
	
	/**
	 * Vota algunas pel�culas para una categor�a dada, y valida que el resultado de la votaci�n sea el esperado
	 */
	public void testOscarMejorPelicula() {
		String datosEsperados = "Mejor Imposible";
		String nomCategoria = "Oscar a la Mejor Pelicula";
		
		assertTrue(obliga.nominarPeliculaACategoria("Titanic", nomCategoria));
		assertTrue(obliga.nominarPeliculaACategoria("El Resplandor", nomCategoria));
		assertTrue(obliga.nominarPeliculaACategoria("Mr. Deeds", nomCategoria));
		assertTrue(obliga.nominarPeliculaACategoria("Matrix Reloaded", nomCategoria));
		assertTrue(obliga.nominarPeliculaACategoria("Mejor Imposible", nomCategoria));

		assertTrue(obliga.votarPeliculaEnCategoria("Mejor Imposible", nomCategoria));
		assertTrue(obliga.votarPeliculaEnCategoria("Matrix Reloaded", nomCategoria));
		assertTrue(obliga.votarPeliculaEnCategoria("Mejor Imposible", nomCategoria));
		assertTrue(obliga.votarPeliculaEnCategoria("El Resplandor", nomCategoria));
		
		String ganador = obliga.ganadorEnCategoria(nomCategoria);
		assertNotNull(ganador);
		assertEquals(datosEsperados, ganador);
	}
	
	
	/**
	 * Vota algunas pel�culas para una categor�a dada, y valida que la cantidad de votos para esta categor�a sea el esperado
	 */
	public void testCantidadVotosEnCategoria() {
		int datosEsperados = 4;
		String nomCategoria = "Oscar a la Mejor Pelicula";
		
		assertTrue(obliga.nominarPeliculaACategoria("Titanic", nomCategoria));
		assertTrue(obliga.nominarPeliculaACategoria("El Resplandor", nomCategoria));
		assertTrue(obliga.nominarPeliculaACategoria("Mr. Deeds", nomCategoria));
		assertTrue(obliga.nominarPeliculaACategoria("Matrix Reloaded", nomCategoria));
		assertTrue(obliga.nominarPeliculaACategoria("Mejor Imposible", nomCategoria));

		assertTrue(obliga.votarPeliculaEnCategoria("Mejor Imposible", nomCategoria));
		assertTrue(obliga.votarPeliculaEnCategoria("Matrix Reloaded", nomCategoria));
		assertTrue(obliga.votarPeliculaEnCategoria("Mejor Imposible", nomCategoria));
		assertTrue(obliga.votarPeliculaEnCategoria("El Resplandor", nomCategoria));
		
		int votos = obliga.cantidadVotosEnCategoria(nomCategoria);
		assertEquals(datosEsperados, votos);
	}
	
	/**
	 * Vota algunas pel�culas para una categor�a dada, y valida que la cantidad de votos para una de las pel�culas sea el esperado
	 */
	public void testCantidadVotosPelicula() {
		int datosEsperados = 3;
		String nomCategoria = "Oscar a la Mejor Pelicula";
		String nomCategoriaInvalida = "Oscar al Mejor Actor";
		String nomCategoria2 = "Oscar a la Mejor Banda Sonora";
		String nomPelicula = "Mejor Imposible";
		
		assertTrue("Debe ser posible nominar una pel�cula a una categor�a v�lida",obliga.nominarPeliculaACategoria("Titanic", nomCategoria));
		assertFalse("No debe ser posible nominar una actor a una categor�a para pel�culas",obliga.nominarActorACategoria("Helen Hunt", nomCategoria));
		assertTrue("Debe ser posible nominar una pel�cula a una categor�a v�lida",obliga.nominarPeliculaACategoria("El Resplandor", nomCategoria));
		assertTrue("Debe ser posible nominar una pel�cula a una categor�a v�lida",obliga.nominarPeliculaACategoria("Mr. Deeds", nomCategoria));
		assertTrue("Debe ser posible nominar una pel�cula a una categor�a v�lida",obliga.nominarPeliculaACategoria("Matrix Reloaded", nomCategoria));
		assertTrue("Debe ser posible nominar una pel�cula a una categor�a v�lida",obliga.nominarPeliculaACategoria(nomPelicula, nomCategoria));
		assertFalse("No es posible nominar una pel�cula a una categor�a de actor",obliga.nominarPeliculaACategoria(nomPelicula, nomCategoriaInvalida));
		assertTrue("Debe ser posible nominar una pel�cula a una categor�a v�lida",obliga.nominarPeliculaACategoria(nomPelicula, nomCategoria2));

		assertTrue(obliga.votarPeliculaEnCategoria(nomPelicula, nomCategoria));
		assertTrue(obliga.votarPeliculaEnCategoria("Matrix Reloaded", nomCategoria));
		assertTrue(obliga.votarPeliculaEnCategoria(nomPelicula, nomCategoria));
		//Agrego votos en la otra categor�a
		assertTrue(obliga.votarPeliculaEnCategoria(nomPelicula, nomCategoria2));
		assertTrue(obliga.votarPeliculaEnCategoria("El Resplandor", nomCategoria));
		
		int votos = obliga.cantidadVotosDePelicula(nomPelicula);
		assertEquals(datosEsperados, votos);
	}

	/**
	 * Vota algunos actores para una categor�a dada, y valida que la cantidad de votos para uno de los actores sea el esperado
	 */
	public void testCantidadVotosActor() {
		int datosEsperados = 3;
		String nomCategoria = "Oscar al Mejor Actor";
		String nomActor = "Tommy Lee Jones";
		
		assertTrue(obliga.nominarActorACategoria("Will Smith", nomCategoria));
		assertTrue(obliga.nominarActorACategoria("Tommy Lee Jones", nomCategoria));
		assertTrue(obliga.nominarActorACategoria("George Harris", nomCategoria));
		assertTrue(obliga.nominarActorACategoria("Leonardo DiCaprio", nomCategoria));
		assertTrue(obliga.nominarActorACategoria("Adam Sandler", nomCategoria));

		assertTrue(obliga.votarActorEnCategoria("Adam Sandler", nomCategoria));
		assertTrue(obliga.votarActorEnCategoria("Tommy Lee Jones", nomCategoria));
		assertTrue(obliga.votarActorEnCategoria("George Harris", nomCategoria));
		assertTrue(obliga.votarActorEnCategoria("Adam Sandler", nomCategoria));
		assertTrue(obliga.votarActorEnCategoria("Tommy Lee Jones", nomCategoria));
		assertTrue(obliga.votarActorEnCategoria("Tommy Lee Jones", nomCategoria));
		
		assertFalse(obliga.votarActorEnCategoria("Cuba Gooding Jr", nomCategoria));
		
		int ganador = obliga.cantidadVotosDeActorOActriz(nomActor);
		assertEquals(datosEsperados, ganador);
	}
	
	/**
	 * Valida que el listado de actores de una pel�cula sea el esperado
	 */
	@SuppressWarnings("unchecked")
	public void testListadoActoresDePelicula () {
		String nomPelicula = "Mejor Imposible";
		String[] datosEsperados = new String[]{"Cuba Gooding Jr","Greg Kinnear","Helen Hunt","Jack Nicholson"};
		
		Comparable[] listadoActoresPelicula = obliga.listarActoresYActrices(nomPelicula);
		validarArraysIguales(datosEsperados, listadoActoresPelicula, "La lista de actores para la pel�cula no es la esperada");
	}
	
	/**
	 * Introduce un n�mero de votos conocido en orden aleatorio, y verifica que el listado de los 5 m�s votados sea el esperado
	 */
	@SuppressWarnings("unchecked")
	public void test5PeliculasMasVotadas() {
		String[] categorias = {"Oscar a la Mejor Banda Sonora","Oscar a la Mejor Pelicula","Oscar al Mejor Documental"};
		String[] peliculas = {"Matrix Reloaded","Mejor Imposible","Zohan","Mr. Deeds","300"};
		for (int i = 0; i < categorias.length; i++) {
			for (int j = 0; j < peliculas.length; j++) {
				assertTrue(obliga.nominarPeliculaACategoria(peliculas[j],categorias[i]));	
			}
				
		}
		
		int[] votosAAsignar = new int[] {1000,884,778,625,13};
		int votosTotales = 3300;
		
		Random rnd = new Random();
		//Voy a agregar votos a categor�as aleatorias en forma decreciente para cada una de las pel�culas
		for (int i = 0; i < votosTotales; i++) {			
			int posicionPeliculaActual = rnd.nextInt(peliculas.length);
			int votosPeliculaActual = votosAAsignar[posicionPeliculaActual];
			while(votosPeliculaActual == 0){
				//Si me qued� sin votos para asignarle a la pel�cula actual, paso a la siguiente directamente
				posicionPeliculaActual++;
				posicionPeliculaActual = posicionPeliculaActual % peliculas.length; //Me aseguro que el "siguiente" al �ltimo sea el primero
				votosPeliculaActual = votosAAsignar[posicionPeliculaActual];
			}
			votosAAsignar[posicionPeliculaActual]--;
			
			//Ahora que eleg� en forma aleatoria la pel�cula a la que voy a votar, me fijo cu�l es
			String nombrePeliculaActual = peliculas[posicionPeliculaActual];
			
			int posicionCategoriaActual = rnd.nextInt(categorias.length); //elijo una categor�a al azar, dado que no me interesa una en particular
			String nombreCategoriaActual = categorias[posicionCategoriaActual];
			int votosPeliculaAntes = obliga.cantidadVotosDePelicula(nombrePeliculaActual);
			int votosCategoriaAntes = obliga.cantidadVotosEnCategoria(nombreCategoriaActual);
			assertTrue("Los votos para nominaciones existentes son v�lidos",obliga.votarPeliculaEnCategoria(nombrePeliculaActual, nombreCategoriaActual));
			int votosPeliculaDespues = obliga.cantidadVotosDePelicula(nombrePeliculaActual);
			int votosCategoriaDespues = obliga.cantidadVotosEnCategoria(nombreCategoriaActual);
			assertEquals("Acabo de agregar un voto a la pel�cula "+nombrePeliculaActual+", se deber�a haber incrementado su cantidad de votos en 1",1,votosPeliculaDespues-votosPeliculaAntes);
			assertEquals("Acabo de agregar un voto a la categor�a "+nombreCategoriaActual+", se deber�a haber incrementado su cantidad de votos en 1",1,votosCategoriaDespues-votosCategoriaAntes);
		}
		
		Comparable[] peliculasMasVotadas = obliga.listarPeliculasMasVotadas();
		assertNotNull("El listado de pel�culas m�s votados no puede ser nulo",peliculasMasVotadas);
		assertEquals("El listado de pel�culas m�s votados debe tener tama�o menor o igual a 5, en este caso debe ser 5",5,peliculasMasVotadas.length);
		for (int i = 0; i < peliculas.length; i++) {
			assertEquals(peliculas[i], peliculasMasVotadas[i]);
		}
		
	}
	
}
