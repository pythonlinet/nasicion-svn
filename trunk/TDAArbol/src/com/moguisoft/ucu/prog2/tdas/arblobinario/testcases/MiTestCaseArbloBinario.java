package com.moguisoft.ucu.prog2.tdas.arblobinario.testcases;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.moguisoft.ucu.prog2.tdas.arblobinario.TArbol;

public class MiTestCaseArbloBinario {
	private TArbol arbol;

	@Before
	public void setup() {
		this.arbol = new TArbol();
	}

	@Test
	public void testAgregar() {
		assertTrue(arbol.insertar(8, null));
		assertTrue(arbol.insertar(30, null));
		assertTrue(arbol.insertar(3, null));
		assertTrue(arbol.insertar(7, null));
		assertTrue(arbol.insertar(9, null));
		assertTrue(arbol.insertar(91, null));
		assertTrue(arbol.insertar(19, null));
		assertTrue(arbol.insertar(12, null));

		assertEquals(8, arbol.getTamanio());

		assertFalse(arbol.insertar(8, null));
		assertFalse(arbol.insertar(3, null));
	}

	@Test
	public void testBuscar() {
		Random random = new Random();
		Comparable[] claves = new Comparable[20];
		for (int i = 0; i < 20; i++) {
			claves[i] = random.nextInt();
			arbol.insertar(claves[i], null);
		}
		for (Comparable c : claves)
			assertNotNull(arbol.buscar(c));

	}

	@Test
	public void testGetParent() {
		arbol.insertar("8", "8");
		arbol.insertar("2", "2");
		arbol.insertar("3", "3");
		arbol.insertar("4", "4");
		arbol.insertar("6", "6");
		arbol.insertar("1", "1");
		assertEquals("8", arbol.getParent("2").getClave());

	}

	@Test
	public void testPreOrden() {

		arbol.insertar("8", "8");
		arbol.insertar("2", "2");
		arbol.insertar("3", "3");
		arbol.insertar("4", "4");
		arbol.insertar("6", "6");
		arbol.insertar("1", "1");

		arbol.insertar("8", "8");
		arbol.insertar("4", "4");
		arbol.insertar("1", "1");
		arbol.insertar("2", "2");

		arbol.insertar("3", "3");
		arbol.insertar("6", "6");

		// preorden: 8,2,1,3,4,6
		Comparable[] preOrdenEsperado = new Comparable[] { "8", "2", "1", "3",
				"4", "6" };
		assertArrayEquals(preOrdenEsperado, arbol.preOrden());
	}

	@Test
	public void testPostOrden() {
		arbol.insertar("8", "8");
		arbol.insertar("2", "2");
		arbol.insertar("3", "3");
		arbol.insertar("4", "4");
		arbol.insertar("6", "6");
		arbol.insertar("1", "1");
		// postorden: 1,6,4,3,2,8
		Comparable[] postOrdenEsperado = new Comparable[] { "1", "6", "4", "3",
				"2", "8" };
		arbol.postOrden();
		assertArrayEquals(postOrdenEsperado, arbol.postOrden());
	}

	@Test
	public void testInOrden() {
		arbol.insertar("8", "8");
		arbol.insertar("2", "2");
		arbol.insertar("3", "3");
		arbol.insertar("4", "4");
		arbol.insertar("6", "6");
		arbol.insertar("1", "1");

		// inorden: 1,2,3,4,6,8
		Comparable[] inOrdenEsperado = new Comparable[] { "1", "2", "3", "4",
				"6", "8" };
		arbol.inOrden();
		assertArrayEquals(inOrdenEsperado, arbol.inOrden());
	}

	@Test
	public void testEliminar1() {

		arbol.insertar(0,null);
		arbol.insertar(2,null);
		arbol.insertar(1,null);
		arbol.insertar(6,null);
		arbol.insertar(4,null);
		arbol.insertar(3,null);
		arbol.insertar(46,null);
		arbol.insertar(45,null);
		arbol.insertar(31,null);
		arbol.insertar(19,null);
		arbol.insertar(17,null);
		arbol.insertar(10,null);
		arbol.insertar(18,null);
		arbol.insertar(20,null);
		arbol.insertar(25,null);
		arbol.insertar(34,null);
		arbol.insertar(48,null);

		System.out.println(  arbol.buscar(arbol.getRaiz().getMax().getClave()));
		Comparable[] lista1 = arbol.preOrden();
		Comparable[] lista2;
		
		Comparable claveAElminar = 10;
		
		assertTrue(arbol.eliminar(claveAElminar));
		lista2 = arbol.preOrden();
		
		
		
		System.out.print("ArbolPRE : ");
		for (Comparable clave : lista1) {
			System.out.print(clave + " ");
		}
		System.out.println();

		System.out.print("ArbolPOS : ");
		for (Comparable clave : lista2) {
			System.out.print(clave + " ");
		}
		System.out.println();

		assertFalse(arbol.eliminar(claveAElminar));
	}

}
