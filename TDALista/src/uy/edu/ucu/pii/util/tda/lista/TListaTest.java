package uy.edu.ucu.pii.util.tda.lista;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import uy.edu.ucu.pii.grupo14.datos.lista.TLista;
import uy.edu.ucu.pii.grupo14.datos.lista.TNodo;


/**
 * @author nacho
 * 
 */
public class TListaTest {
	private TLista lista = null;

	@Before
	public void setup() {
		lista = new TLista();
	}

	@Test
	public void testAgregar() {
		assertEquals(0, lista.getTamanio());

		assertTrue(agregarALista("10", lista));
		assertEquals(1, lista.getTamanio());

		// vuelvo a agregarlo, y veo qu&eacute; pasa
		agregarALista("10", lista);
		assertNotNull(lista.buscarNodo("10"));
	}

	@Test
	public void testBuscarNodo() {
		String claveABuscar = "unaClave";
		assertNull(lista.buscarNodo(claveABuscar));

		// Agrego el elemento a la lista
		lista.insertar(claveABuscar, null);

		TNodo nodoEncontrado = lista.buscarNodo(claveABuscar);
		// Verifico que encontr&eacute; algo
		assertNotNull(nodoEncontrado);
		// Verifico que el nodo encontrado tiene la misma clave que buscaba
		assertEquals(claveABuscar, nodoEncontrado.getClave());
	}

	@Test
	public void testEsVacia() {
		assertTrue(lista.esVacia());

		// Agrego un elemento, y verifico que la lista dej&oacute; de ser
		// vac&iacute;a
		assertTrue(agregarALista("1", lista));
		assertFalse(lista.esVacia());
	}

	@Test
	public void testEliminar() {
		// Agrego 5 elementos a la lista para que tenga datos
		agregarElementosALista(5, lista, 1000);

		Integer claveTestEliminar = new Integer(123);
		// verifico que el nodo no este en la lista
		assertNull(lista.buscarNodo(claveTestEliminar));

		// Lo agrego a la lista, y valido que ahora se lo encuentro
		assertTrue(agregarALista(claveTestEliminar, lista));
		assertNotNull(lista.buscarNodo(claveTestEliminar));

		// Agrego 5 elementos mas a la lista
		agregarElementosALista(5, lista, 2000);

		// Ahora verifico que la eliminacion es exitosa, y que dejo de
		// encontrarlo, aparte de validar que decrement&oacute; el tama&ntilde;o
		// de la lista
		int tamanioAntes = lista.getTamanio();
		assertTrue(lista.eliminar(claveTestEliminar));
		assertNull(lista.buscarNodo(claveTestEliminar));
		assertEquals(tamanioAntes - 1, lista.getTamanio());
	}

	@Test
	public void testRecuperar() {
		assertNull(lista.recuperar(0)); // Verifico que no devuelva nada con la
										// lista vacia

		assertNull(lista.recuperar(-10)); // Verifico que funciona correctamente
											// con una posicion invalida

		String clave = "28041979";
		agregarALista(clave, lista);

		TNodo nodoRecuperado = lista.recuperar(0);
		assertNotNull(nodoRecuperado);
		assertEquals(clave, nodoRecuperado.getClave());

		// Verifico que se comporte correctamente con posiciones inexistentes
		assertNull(lista.recuperar(10));

	}

	@Test
	public void testBuscarVarios() {
		Random rnd = new Random();
		int claveInicial = rnd.nextInt(10000);
		;
		int cantidadAAgregar = rnd.nextInt(100);
		agregarElementosALista(cantidadAAgregar, lista, claveInicial);

		// Valido que encuentro a todos ellos
		for (int i = claveInicial; i < (claveInicial + cantidadAAgregar); i++) {
			Integer clave = new Integer(i);
			TNodo nodoEncontrado = lista.buscarNodo(clave);
			assertNotNull(nodoEncontrado);
			assertEquals(clave, nodoEncontrado.getClave());
		}
	}

	@Test
	public void testGetTamanio() {
		Random rnd = new Random();
		int cantidadAAgregar = rnd.nextInt(50);
		agregarElementosALista(cantidadAAgregar, lista, 1234);
		assertEquals(cantidadAAgregar, lista.getTamanio());
	}

	@Test
	public void testGetPrimero() {
		String nuevaClave = "vjkfnfdksjn";
		lista.insertar(nuevaClave, null);
		assertFalse(lista.esVacia());
		assertEquals(nuevaClave, lista.getPrimero().getClave());
	}

	@Test
	public void testInsertarOrdenado() {
		for (int i = 0; i < 10; i++) {
			lista.insertarOrdenado(1000 + i, null);
		}

		Integer claveExistente = 1005;
		assertFalse(lista.insertarOrdenado(claveExistente, null));

		Integer claveChica = 500;
		Integer claveGrande = 1500;
		Integer claveMuyChica = 100;
		Integer claveMuyGrande = 5000;

		lista.insertarOrdenado(claveGrande, null);
		lista.insertarOrdenado(claveChica, null);
		lista.insertarOrdenado(claveMuyChica, null);
		lista.insertarOrdenado(claveMuyGrande, null);

		validarPosicionElemento(500, 1, lista);
		validarPosicionElemento(1500, 12, lista);
		validarPosicionElemento(5000, 13, lista);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testInvertir() {
		for (int i = 0; i < 10; i++) {
			lista.insertar(1000 + i, null);
		}
		
		Comparable[] numerosLista = lista.invertir();
		
		
		validarArregloOrdenado(numerosLista, false);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testOrdenar() {
		Integer[] numerosAOrdenar = new Integer[] { 1, 5, 2, 7, 4, 9, 1002, -1,
				8, 100 };
		for (int i = 0; i < numerosAOrdenar.length; i++) {
			lista.insertar(numerosAOrdenar[i], null);
		}
		lista.ordenar();

		Comparable[] elementosLista = lista.mostrar();
		validarArregloOrdenado(elementosLista, true);
	}

	@SuppressWarnings("unchecked")
	private void validarArregloOrdenado(Comparable[] numerosLista,
			boolean ascendente) {
		int posicionActual = 1;
		while (posicionActual < numerosLista.length) {
			Integer numeroAnterior = (Integer) numerosLista[posicionActual - 1];
			Integer numeroActual = (Integer) numerosLista[posicionActual];
			if (ascendente) {
				assertTrue(numeroAnterior < numeroActual);
			} else {
				assertTrue(numeroAnterior > numeroActual);
			}

			posicionActual++;
		}
	}

	@SuppressWarnings("unchecked")
	private void validarPosicionElemento(Comparable clave, int posicion,
			TLista unaLista) {
		TNodo unNodo = unaLista.recuperar(posicion);
		assertNotNull(unNodo);
		assertEquals(clave, unNodo.getClave());
	}

	/**
	 * @param string
	 * @param unaLista
	 *            M&eacute;todo utilitario para reutilizar el c&oacute;digo
	 *            correspondiente a la creaci&oacute;n de un nodo e
	 *            invocaci&oacute;n a agregarNodo
	 */
	@SuppressWarnings("unchecked")
	private boolean agregarALista(Comparable claveAAgregar, TLista unaLista) {
		return unaLista.insertar(claveAAgregar, null);
	}

	/**
	 * @param cantidadElementos
	 * @param unaLista
	 *            Agrega n elementos con claves claveInicial, claveInicial + 1,
	 *            ...
	 */
	private void agregarElementosALista(int cantidadElementos, TLista unaLista,
			int claveInicial) {
		for (int i = 0; i < cantidadElementos; i++) {
			Integer clave = new Integer(claveInicial + i);
			agregarALista(clave, lista);
		}
	}
}