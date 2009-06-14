package uy.edu.ucu.pii.util.tda.grafo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uy.edu.ucu.pii.grupo14.datos.grafo.TGrafo;

public class MiGrafoTest {

	private TGrafo g = null;

	@Before
	public void before() {
		g = new TGrafo();
	}

	@Test
	public void testInsertarYExisteVertice() {
		assertTrue(g.insertarVertice("Toronto"));
		assertTrue(g.insertarVertice("New York"));
		assertTrue(g.insertarVertice("Montevideo"));
		assertTrue(g.insertarVertice("Timbuktu"));
		assertTrue(g.insertarVertice("Madrid"));
		assertTrue(g.insertarVertice("Praga"));

		assertFalse(g.insertarVertice("Toronto"));
		assertFalse(g.insertarVertice("Madrid"));
		assertFalse(g.insertarVertice("Timbuktu"));

		assertTrue(g.existeVertice("New York"));
		assertFalse(g.existeVertice("Chicago"));
		assertTrue(g.existeVertice("Timbuktu"));
	}

	@Test
	public void testInsertarAdyacencia() {
		testInsertarYExisteVertice();
		assertTrue(g.insertarAdyacencia("Toronto", "Montevideo", 100));
		assertFalse(g.insertarAdyacencia("Toronto", "Montevideo", 100));
		assertTrue(g.insertarAdyacencia("Toronto", "New York", 20));
		assertFalse(g.insertarAdyacencia("New York", "Miami", 10));
		assertTrue(g.insertarVertice("Miami"));
		assertTrue(g.insertarAdyacencia("New York", "Miami", 10));
	}

	@Test
	public void testExisteCamino() {

		assertTrue(g.insertarVertice("Toronto"));
		assertTrue(g.insertarVertice("New York"));
		assertTrue(g.insertarVertice("Montevideo"));
		assertTrue(g.insertarVertice("Timbuktu"));
		assertTrue(g.insertarVertice("Madrid"));
		assertTrue(g.insertarVertice("Praga"));

		g.insertarAdyacencia("Toronto", "New York", 10);
		g.insertarAdyacencia("New York", "Montevideo", 100);

		assertTrue(g.existeCamino("Toronto", "New York"));
		assertTrue(g.existeCamino("Toronto", "Montevideo"));
		assertFalse(g.existeCamino("Montevideo", "Timbuktu"));

		g.insertarAdyacencia("Montevideo", "Madrid", 500);
		g.insertarAdyacencia("Madrid", "Timbuktu", 100);

		assertTrue(g.existeCamino("Montevideo", "Timbuktu"));

	}

	@Test
	public void testCentro() {
		String[] vertices = new String[] { "A", "B", "C", "D", "E" };

		for (int i = 0; i < vertices.length; i++)
			assertTrue(g.insertarVertice(vertices[i]));
		for (int i = 0; i < vertices.length; i++)
			assertTrue(g.existeVertice(vertices[i]));

		// Pruebo utilizando un grafo con el que se trabajo en clase para estar
		// seguro que los metodos funcionan correctamente
		g.insertarAdyacencia("A", "C", 1);
		g.insertarAdyacencia("A", "D", 4);

		g.insertarAdyacencia("B", "A", 6);
		g.insertarAdyacencia("B", "E", 3);

		g.insertarAdyacencia("C", "B", 2);
		g.insertarAdyacencia("C", "E", 1);

		g.insertarAdyacencia("D", "C", 5);

		g.insertarAdyacencia("E", "A", 3);

		/*
		 * System.out.println("Matriz de Adyacencia");
		 * g.imprimirMatrizAdyacente();
		 * 
		 * System.out.println("Matriz resultante del Floyd");
		 * g.imprimirMatrizFloyd();
		 */
		assertEquals("E", g.centroDelGrafo());

	}

	@Test
	public void testDijkstra() {
		for (int i = 1; i < 6; i++)
			g.insertarVertice(i);

		g.insertarAdyacencia(1, 2, 10);
		g.insertarAdyacencia(1, 4, 30);
		g.insertarAdyacencia(1, 5, 100);

		g.insertarAdyacencia(2, 3, 50);

		g.insertarAdyacencia(3, 5, 10);

		g.insertarAdyacencia(4, 3, 20);
		g.insertarAdyacencia(4, 5, 60);


		Comparable[] c;
		for (int i = 1; i < g.getCantVertices(); i++) {
			c = g.implementacionDijkstra(i, false);
			for (int j = 0; j < c.length; j++) {
				assertEquals(c[j], g.getMFloyd()[i - 1][j]);
			}

		}
	}
	
	@Test
	public void testEliminarVertice(){
		for(int i = 1; i < 8; i++)
			g.insertarVertice(i);
		
		g.insertarAdyacencia(1, 2, 10);
		g.insertarAdyacencia(1, 6, 11);
		g.insertarAdyacencia(1, 4, 30);
		
		g.insertarAdyacencia(2, 7, 12);
		g.insertarAdyacencia(2, 6, 15);
		
		g.insertarAdyacencia(3, 2, 40);
		
		g.insertarAdyacencia(4, 3, 15);
		g.insertarAdyacencia(4, 5, 21);
		
		g.insertarAdyacencia(5, 6, 22);
		g.insertarAdyacencia(5, 1, 1);
		
		g.insertarAdyacencia(6, 7, 8);
		
		
		assertTrue(g.existeCamino(1, 6));
		
		for(Comparable c : g.mejorCamino(1, 6))
			System.out.print(c + " ");
		System.out.println();
		
		assertTrue(g.eliminarAdyacencia(1, 6));
		System.out.println("Eliminamos la adyacencia 1 - 6");
		
		
		for(Comparable c : g.mejorCamino(1, 6))
			System.out.print(c + " ");
		System.out.println();

		assertTrue(g.eliminarAdyacencia(1, 2));
		System.out.println("Eliminamos la adyacencia 1 - 2");
		
		for(Comparable c : g.mejorCamino(1, 6))
			System.out.print(c + " ");
		System.out.println();
		
		
	
		System.out.println();
		System.out.println("Camino de 1 a 7");
		assertTrue(g.existeCamino(1, 7));
		for(Comparable c : g.mejorCamino(1, 7))
			System.out.print(c + " ");
		System.out.println();
		
		assertTrue(g.eliminarVertice(6));
		System.out.println("Eliminamos el vertice 6");
		for(Comparable c : g.mejorCamino(1, 7))
			System.out.print(c + " ");
		System.out.println();
		
		System.out.println("Volvemos a ingresar la adyacencia 1 - 2");
		g.insertarAdyacencia(1, 2, 10);
		for(Comparable c : g.mejorCamino(1, 7))
			System.out.print(c + " ");
		System.out.println();
		
	}
	
}
