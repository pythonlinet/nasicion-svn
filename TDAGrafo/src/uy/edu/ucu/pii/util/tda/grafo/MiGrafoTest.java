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
	public void testInsertarYExisteVertice(){
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
	public void testInsertarAdyacencia(){
		testInsertarYExisteVertice();
		assertTrue(g.insertarAdyacencia("Toronto", "Montevideo", 100));
		assertFalse(g.insertarAdyacencia("Toronto", "Montevideo", 100));
		assertTrue(g.insertarAdyacencia("Toronto", "New York", 20));
		assertFalse(g.insertarAdyacencia("New York", "Miami", 10));
		assertTrue(g.insertarVertice("Miami"));
		assertTrue(g.insertarAdyacencia("New York", "Miami", 10));
	}
}
