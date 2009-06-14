package uy.edu.ucu.pii.util.tda.grafo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uy.edu.ucu.pii.grupo14.datos.grafo.TGrafo;

public class TGrafoTest {
	private static final String[] VERTICES_INICIALES_1 = new String[]{"A","B","C"};
	private static final String[] VERTICES_INICIALES_2 = new String[]{"A","B","C","D","E","F"};
	private static final String[][] ARISTAS_INICIALES_2 = new String[][]{{"A","B"},{"B","C"},{"B","E"},{"C","D"},{"C","E"},{"D","E"},{"E","F"}};
	private static final Integer[] COSTOS_ARTISTAS_INICIALES_2 = new Integer[]{10,10,9,8,6,11,14};
	
	
	private TGrafo g = null;
	@Before
	public void before() {
		g = new TGrafo();
	}
	
	@Test
	public void testInsertaVertices() {
		validarInsercionVertices(VERTICES_INICIALES_1);
	}

	@Test
	public void testInsertaVerticesYAdyacencias() {
		validarInsercionVertices(VERTICES_INICIALES_2);
		validarInsercionAdyacencias(ARISTAS_INICIALES_2, COSTOS_ARTISTAS_INICIALES_2);
	}

	@Test
	public void testExisteCamino() {
		validarInsercionVertices(VERTICES_INICIALES_2);
		validarInsercionAdyacencias(ARISTAS_INICIALES_2, COSTOS_ARTISTAS_INICIALES_2);
		
		assertTrue(g.existeCamino("A","B"));
		assertFalse(g.existeCamino("F","A"));
		assertTrue(g.existeCamino("C","F"));
		assertTrue(g.existeCamino("B","F"));
	}
	
	@Test
	public void testMejorCamino() {
		validarInsercionVertices(VERTICES_INICIALES_2);
		validarInsercionAdyacencias(ARISTAS_INICIALES_2, COSTOS_ARTISTAS_INICIALES_2);
		
		String[] caminoEsperado = new String[] {"A","B","E","F"};
		validarListasIguales("El mejor camino entre A y F debe ser "+caminoEsperado,caminoEsperado,g.mejorCamino("A", "F"));
	}
	
	@Test
	public void testCentro() {
		validarInsercionVertices(VERTICES_INICIALES_2);
		validarInsercionAdyacencias(ARISTAS_INICIALES_2, COSTOS_ARTISTAS_INICIALES_2);
		
		assertEquals(g.centroDelGrafo(),"F");
	}

	

	@SuppressWarnings("unchecked")
	private void validarListasIguales(String mensajeError,String[] array1, Comparable[] array2) {
		assertNotNull(array1);
		assertNotNull(array2);
		assertEquals(array1.length, array2.length);
		for (int i = 0; i < array1.length; i++) {
			assertEquals(mensajeError, array1[i], array2[i]);
		}
		
	}

	private void validarInsercionVertices(String[] etiquetas){
		for (int i = 0; i < etiquetas.length; i++) {
			validarInsercionVertice(etiquetas[i]);
		}
	}
	
	private void validarInsercionVertice(String etiqueta) {
		assertFalse(g.existeVertice(etiqueta));
		assertTrue("Siempre debe ser posible insertar vertices en un grafo",g.insertarVertice(etiqueta));
		assertFalse("No es posible insertar vertices repetidos",g.insertarVertice(etiqueta));
		assertTrue("Es necesario que se encuentre un vertice que acabo de insertar!!",g.existeVertice(etiqueta));
	}
	
	/**
	 * @param adyacencias Arreglo con pares ordenados: etOrigen - etDestino (en forma de un array de dos posiciones)
	 * @param costos
	 */
	private void validarInsercionAdyacencias(String[][] adyacencias, Integer[] costos){
		for (int i = 0; i < adyacencias.length; i++) {
			String[] adyacenciaActual = adyacencias[i];
			validarInsercionAdyacencia(adyacenciaActual[0], adyacenciaActual[1], costos[i]);
		}
	}
	
	private void validarInsercionAdyacencia(String etiquetaOrigen, String etiquetaDestino, Integer costo){
		assertFalse(g.existeAdyacencia(etiquetaOrigen, etiquetaDestino));
		assertTrue("Siempre debe ser posible insertar adyacencias inexistentes a un grafo",g.insertarAdyacencia(etiquetaOrigen, etiquetaDestino, costo));
		assertFalse("No es posible insertar adyacencias duplicadas",g.insertarAdyacencia(etiquetaOrigen, etiquetaDestino, costo));
		assertTrue("Es necesario que se encuentre una adyacencia que acabo de insertar!!",g.existeAdyacencia(etiquetaOrigen, etiquetaDestino));
	}
}
