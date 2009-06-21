package uy.edu.ucu.pii.obligatorio2.test;


import junit.framework.TestCase;
import uy.edu.ucu.pii.obligatorio2.Obligatorio;
import uy.edu.ucu.pii.obligatorio2.util.ConversorNumeros;
import uy.edu.ucu.pii.obligatorio2.util.LectorArchivos;

public class TestObligatorio  extends TestCase{
	protected Obligatorio obliga;
	private ConversorNumeros conversorNum = new ConversorNumeros();
	protected String nombreArchivoAviones = "data/DataAviones.in";
	protected String nombreArchivoCiudades = "data/DataCiudades.in";
	protected String nombreArchivoTramos = "data/DataTramos.in";
	protected String nombreArchivoAvionesTramos = "data/DataAvionesTramos.in";
	
	/**
	 * Método utilitario para la carga de información de los aviones desde el archivo
	 */
	private void cargarAviones() {
		String[] dataAviones = LectorArchivos.leerArchivo(nombreArchivoAviones);
		if (dataAviones !=  null){
			for (int i = 0; i < dataAviones.length; i++) {
				String cadena = dataAviones[i];
				String[] datos = cadena.split(";");
				String rendimientoAvionStr = datos[0];
				String nombreAvion = datos[1];
				validarIngresoAvion(rendimientoAvionStr, nombreAvion);
			}			
		} else {
			fail("Error en la carga del archivo "+nombreArchivoAviones);
		}
	}

	private void validarIngresoAvion(String rendimientoAvionStr, String nombreAvion) {
		Double rendimientoAvion = new Double(rendimientoAvionStr);
		int tamanioListaAvionesAntes = obliga.listadoAvionesPorRendimiento().length;
		assertTrue("Debe ser posible agregar aviones inexistentes",obliga.agregarAvion(nombreAvion, rendimientoAvion));
		assertFalse("No debe ser posible agregar aviones con nombres duplicados",obliga.agregarAvion(nombreAvion, rendimientoAvion));
		int tamanioListaAvionesDespues = obliga.listadoAvionesPorRendimiento().length;
		assertTrue("El listado de aviones debe haber crecido en 1 al agregar 1 avión nuevo",tamanioListaAvionesDespues-tamanioListaAvionesAntes == 1);
		assertNotNull("Debo poder encontrar un avión que acabo de insertar",obliga.obtenerRendimientoAvion(nombreAvion));
		assertEquals("El rendimiento del avión debe ser el que se cargó anteriormente",rendimientoAvion, obliga.obtenerRendimientoAvion(nombreAvion));
	}
	
	/**
	 * Método utilitario para la carga de información las ciudades desde el archivo 
	 */
	private void cargarCiudades() {
		String[] dataCiudades = LectorArchivos.leerArchivo(nombreArchivoCiudades);
		if (dataCiudades != null){
			for (int i = 0; i < dataCiudades.length; i++) {
				String cadena = dataCiudades[i];
				String[] datos = cadena.split(";");
				String nombreCiudad = datos[0];
				String paisCiudad = datos[1];
				validarIngresoCiudad(nombreCiudad, paisCiudad);
			}
		} else {
			fail("Error en la carga del archivo "+nombreArchivoCiudades);
		}
	}
	
	private void validarIngresoCiudad(String nombreCiudad, String paisCiudad) {
		assertTrue("Debe ser posible agregar ciudades inexistentes",obliga.agregarCiudad(nombreCiudad, paisCiudad));
		assertFalse("No debe ser posible agregar ciudades con nombres duplicados",obliga.agregarCiudad(nombreCiudad, paisCiudad));
	}
	
	private void cargarTramos() {
		String[] dataTramos = LectorArchivos.leerArchivo(nombreArchivoTramos);
		if (dataTramos != null){
			for (int i = 0; i < dataTramos.length; i++) {
				String cadena = dataTramos[i];
				String[] datos = cadena.split(";");
				String nombreCiudadOrigen = datos[0];
				String nombreCiudadDestino = datos[1];
				String distanciaStr = datos[2];
				Double distancia = conversorNum.convertirADouble(distanciaStr);
				String tiempoEstimadoVueloStr = datos[3];
				Double tiempoEstimadoVuelo = conversorNum.convertirADouble(tiempoEstimadoVueloStr);
				validarIngresoTramo(nombreCiudadOrigen,nombreCiudadDestino,distancia,tiempoEstimadoVuelo);
			}			
		} else {
			fail("Error en la carga del archivo "+nombreArchivoTramos);
		}
	}

	private void validarIngresoTramo(String nombreCiudadOrigen, String nombreCiudadDestino, Double distancia, Double tiempoEstimadoVuelo) {
		assertFalse(obliga.existeTramo(nombreCiudadOrigen,nombreCiudadDestino));
		assertTrue("Debe ser posible ingresar un tramo inexistente",obliga.agregarTramo(nombreCiudadOrigen, nombreCiudadDestino, distancia, tiempoEstimadoVuelo));
		assertFalse("No debe ser posible ingresar un tramo existente",obliga.agregarTramo(nombreCiudadOrigen, nombreCiudadDestino, distancia, tiempoEstimadoVuelo));
		assertTrue(obliga.existeTramo(nombreCiudadOrigen,nombreCiudadDestino));
	}
	
	private void cargarAvionesTramos() {
		String[] dataAvionesTramos = LectorArchivos.leerArchivo(nombreArchivoAvionesTramos);
		
		if(dataAvionesTramos != null){
			for (int i = 0; i < dataAvionesTramos.length; i++) {
				String cadena = dataAvionesTramos[i];
				String[] datos = cadena.split(";");
				String nombreAvion = datos[0];
				String nombreCiudadOrigen = datos[1];
				String nombreCiudadDestino = datos[2];
				validarIngresoAvionTramo(nombreAvion,nombreCiudadOrigen,nombreCiudadDestino);
			}			
		} else {
			fail("Error en la carga del archivo "+nombreArchivoAvionesTramos);
		}
	}

	private void validarIngresoAvionTramo(String nombreAvion, String nombreCiudadOrigen, String nombreCiudadDestino) {
		String nombreAvionInexistente = "kjsndfkjdsn fañlakjngfñlksfdnñsldkfn";
		assertFalse("Es imposible asignar aviones inexistentes a tramos",obliga.asignarAvionATramo(nombreAvionInexistente, nombreCiudadOrigen, nombreCiudadDestino));
		String nombreCiudadFicta = "ciudadSinConexiones"; //Esta ciudad estoy segudo que no tiene conexiones que salen / llegan a ella
		assertTrue("Es necesario poder agregar una ciudad inexistente",obliga.agregarCiudad(nombreCiudadFicta, nombreCiudadFicta));
		
		assertFalse("Es imposible asignar aviones a conexiones inexistentes",obliga.asignarAvionATramo(nombreAvion, nombreCiudadOrigen, nombreCiudadFicta));
		assertFalse("Es imposible asignar aviones a conexiones inexistentes",obliga.asignarAvionATramo(nombreAvion, nombreCiudadFicta, nombreCiudadDestino));
		
		assertTrue("Es necesario poder asignar aviones válidos a conexiones válidas",obliga.asignarAvionATramo(nombreAvion, nombreCiudadOrigen, nombreCiudadDestino));
		assertFalse("Es imposible volver a asignar un mismo avión a una conexión",obliga.asignarAvionATramo(nombreAvion, nombreCiudadOrigen, nombreCiudadDestino));
		
		assertTrue("Es necesario poder quitar una ciudad existente",obliga.quitarCiudad(nombreCiudadFicta));
		
	}

	protected void inicializarObli() {
		obliga = new Obligatorio();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	public void setUp() {
		inicializarObli();
		cargarAviones();
		cargarCiudades();
		cargarTramos();
		cargarAvionesTramos();
	}
	
	/*
	 * Comienzo de los Tests
	 */
	public void testExisteTramo(){
		String ciudadOrigen = "Montevideo";
		String ciudadDestino = "PortoAlegre";
		
		//Estoy seguro que existe un tramo de ida, y no uno de vuelta
		assertTrue(obliga.existeTramo(ciudadOrigen, ciudadDestino));
		assertFalse(obliga.existeTramo(ciudadDestino, ciudadOrigen));
		
		//Conozco los costos asociados a dicho tramo
		assertEquals(new Double(1000),obliga.obtenerDistanciaTramo(ciudadOrigen,ciudadDestino));
		assertEquals(new Double(90),obliga.obtenerTiempoEstimadoTramo(ciudadOrigen,ciudadDestino));
		
		assertTrue(obliga.quitarCiudad(ciudadDestino));
		assertFalse("Acabo de eliminar uno de los vértices, el camino dejó de existir",obliga.existeTramo(ciudadOrigen, ciudadDestino));
	}
	
	public void testExisteCamimo(){
		String ciudadOrigen = "Montevideo";
		String ciudadDestino = "PortoAlegre";
		
		//Estoy seguro que existe un camino de ida, y de vuelta
		assertTrue(obliga.existeCamino(ciudadOrigen, ciudadDestino));
		assertTrue(obliga.existeCamino(ciudadDestino, ciudadOrigen));

		assertTrue(obliga.quitarCiudad(ciudadOrigen));
		assertFalse("Acabo de eliminar uno de los vértices, el camino dejó de existir",obliga.existeCamino(ciudadOrigen, ciudadDestino));

	}
	
	public void testQuitarCiudad(){
		String nombreCiudad = "Montevideo";
		assertTrue("Debe ser posible eliminar ciudades existentes", obliga.quitarCiudad(nombreCiudad));
		assertFalse("No es posible eliminar ciudades inexistentes",obliga.quitarCiudad(nombreCiudad));
		assertTrue("Debo poder agregar una ciudad que eliminé", obliga.agregarCiudad(nombreCiudad,"EstadosUnidos"));
	}
	
	public void testQuitarTramo(){
		String nombreCiudadOrigen = "Montevideo";
		String nombreCiudadDestino = "Durazno";
		assertTrue(obliga.existeTramo(nombreCiudadOrigen, nombreCiudadDestino));
		assertTrue("Debe ser posible eliminar un tramo existente",obliga.quitarTramo(nombreCiudadOrigen,nombreCiudadDestino));
		assertFalse("El tramo ha sido eliminado, por ende no existe más", obliga.existeTramo(nombreCiudadOrigen, nombreCiudadDestino));
	}

	
	public void testCentroOperacionesQuitandoTramo(){
		String nombreCiudadCentroOperaciones = "Montevideo";
		assertEquals(nombreCiudadCentroOperaciones,obliga.obtenerCentroOperaciones());
		
		//Si yo ahora obligo a que el único tramo que salía de La Paloma no esté más, el centro de operaciones va a pasar a ser La Paloma
		String nuevoCentro = "LaPaloma";
		assertTrue(obliga.quitarTramo(nuevoCentro, nombreCiudadCentroOperaciones));
		assertEquals("El centro de operaciones pasa a ser la única ciudad accesible desde el resto del grafo", nuevoCentro, obliga.obtenerCentroOperaciones());
	}
	
	public void testCentroOperacionesQuitandoCiudadCentro(){
		String nombreCiudadCentroOperaciones = "Montevideo";
		assertEquals(nombreCiudadCentroOperaciones,obliga.obtenerCentroOperaciones());
		
		//Si quito Montevideo, ahora no voy a tener más centro
		assertTrue(obliga.quitarCiudad(nombreCiudadCentroOperaciones));
		assertNull("Sin montevideo, el grafo no tiene centro, porque no es conexo",obliga.obtenerCentroOperaciones());
	}


	public void testAvionesParaCiudad(){
		assertNull("No puedo tener aviones asignados a una ciudad inexistente",obliga.avionesParaCiudad("kjsdnfkfjdnvfdkjnv"));
		//Por la paloma el único avión que pasa es el 767-5
		String nombreCiudad = "LaPaloma";
		String nombreAvion = "767-5";
		assertEquals("Solamente un avión pasa por la paloma",1, obliga.avionesParaCiudad(nombreCiudad).length);
		assertEquals(nombreAvion, obliga.avionesParaCiudad(nombreCiudad)[0]);
		
		//Ahora agrego un nuevo avión al tramo montevideo-lapaloma
		assertTrue(obliga.asignarAvionATramo("767-4", "Montevideo", nombreCiudad));
		assertEquals("Ahora debe haber 2 aviones que pasen por La Paloma",2, obliga.avionesParaCiudad(nombreCiudad).length);
	}
	
	@SuppressWarnings("unchecked")
	public void testMejorItinerarioParaCliente(){
		String nombreCiudadOrigen = "Montevideo";
		String nombreCiudadDestino = "RioDeJaneiro";
		Comparable[] mejorItinerarioEsperado = new Comparable[]{"Montevideo","PortoAlegre","SaoPaulo","RioDeJaneiro"}; //Estoy seguro que es este, porque es el único camino
		validarArraysIguales(mejorItinerarioEsperado,obliga.mejorItinerarioParaCliente(nombreCiudadOrigen, nombreCiudadDestino));
		
		//Ahora agrego un tramo que va a mejorar dicho camino, sin pasar por PortoAlegre
		assertTrue(obliga.agregarTramo("Montevideo", "SaoPaulo", new Double(3000), new Double(200))); //misma distancia, 10 minutos menos de espera
		Comparable[] nuevoMejorItinerarioEsperado = new Comparable[]{"Montevideo","SaoPaulo","RioDeJaneiro"};
		validarArraysIguales(nuevoMejorItinerarioEsperado,obliga.mejorItinerarioParaCliente(nombreCiudadOrigen, nombreCiudadDestino));
	}

	@SuppressWarnings("unchecked")
	public void testMejorItinerarioParaEmpresa(){
		String nombreCiudadOrigen = "Montevideo";
		String nombreCiudadDestino = "Bariloche";
		Comparable[] mejorItinerarioEsperado = new Comparable[]{"Montevideo","BuenosAires","Bariloche"}; //Estoy seguro que es este, porque es el único camino
		validarArraysIguales(mejorItinerarioEsperado,obliga.mejorItinerarioParaEmpresa(nombreCiudadOrigen, nombreCiudadDestino));
		
		//Ahora agrego un tramo directo entre Montevideo y Bariloche, misma distancia, mismo tiempo de espera que la suma de los 2, pero le asigno un avión más eficiente
		assertTrue(obliga.agregarTramo("Montevideo", "Bariloche", new Double(2100), new Double(210))); //misma distancia, mismo tiempo de espera
		assertTrue(obliga.asignarAvionATramo("747-8", nombreCiudadOrigen, nombreCiudadDestino));
		Comparable[] nuevoMejorItinerarioEsperado = new Comparable[]{"Montevideo","Bariloche"}; //misma distancia, mayor eficiencia global
		validarArraysIguales(nuevoMejorItinerarioEsperado,obliga.mejorItinerarioParaCliente(nombreCiudadOrigen, nombreCiudadDestino));
		validarArraysIguales(nuevoMejorItinerarioEsperado,obliga.mejorItinerarioParaEmpresa(nombreCiudadOrigen, nombreCiudadDestino));
	}

	
	@SuppressWarnings("unchecked")
	private void validarArraysIguales(Comparable[] vector1, Comparable[] vector2) {
		assertEquals(vector1.length, vector2.length);
		for (int i = 0; i < vector1.length; i++) {
			assertEquals(vector1[i],vector2[i]);
		}
	}
}
