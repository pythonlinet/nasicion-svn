package uy.edu.ucu.pii.util.tda.grafo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uy.edu.ucu.pii.obligatorio1.grupo14.datos.grafo.TGrafo;

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
		g.insertarAdyacencia("New York","Montevideo", 100);
		
		assertTrue(g.existeCamino("Toronto", "New York"));
		assertTrue(g.existeCamino("Toronto", "Montevideo"));
		assertFalse(g.existeCamino("Montevideo", "Timbuktu"));
		
		g.insertarAdyacencia("Montevideo", "Madrid", 500);
		g.insertarAdyacencia("Madrid", "Timbuktu", 100);
		
		assertTrue(g.existeCamino("Montevideo", "Timbuktu"));

	}
	
	@Test
	public void testCentro(){


		g.insertarVertice("Toronto");
		g.insertarVertice("New York");
		g.insertarVertice("Montevideo");
		g.insertarVertice("Timbuktu");
		g.insertarVertice("Madrid");
		g.insertarVertice("Praga");
		
		g.insertarAdyacencia("Toronto", "New York", 10);
		g.insertarAdyacencia("New York","Montevideo", 100);
		
		g.existeCamino("Toronto", "New York");
		g.existeCamino("Toronto", "Montevideo");
		g.existeCamino("Montevideo", "Timbuktu");
		
		g.insertarAdyacencia("Montevideo", "Madrid", 500);
		g.insertarAdyacencia("Madrid", "Timbuktu", 100);
		
		Comparable[] list = g.excentricidad();

	}
}
