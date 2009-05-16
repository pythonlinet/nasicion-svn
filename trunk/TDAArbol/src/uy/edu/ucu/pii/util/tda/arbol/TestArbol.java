package uy.edu.ucu.pii.util.tda.arbol;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.moguisoft.ucu.prog2.tdas.arblobinario.TArbol;


public class TestArbol {
	private TArbol arbol = null;

	@Before
	public void setup() {
		arbol = new TArbol();
	}

	@Test
	public void testVacio() {
		assertTrue(arbol.vacio());
		arbol.insertar(new Integer(1), null);
		assertFalse(arbol.vacio());
		for (int i = 1; i < 10; i++) {
			assertEquals(arbol.getTamanio(), i);
			assertTrue(arbol.insertar(new Integer(10+i), null));
		}
		arbol.vaciar();
		assertTrue(arbol.vacio());
		assertEquals(arbol.getTamanio(), 0);
	}

	@Test
	public void testAltura() {
		assertEquals(arbol.getAltura(),0);
		arbol.insertar("1", null);
		assertEquals(arbol.getAltura(),0);
		for (int i = 1; i < 10; i++) {
			//La altura crece en 1 a cada elemento que agrego, porque están ordenados
			assertTrue(arbol.insertar(Integer.toString(10+i), null));
			assertEquals(arbol.getAltura(), i);
		}
	}

	@Test
	public void testSimilarYEquivalente() {
		TArbol arbolSimilar = new TArbol();
		TArbol arbolEquivalente = new TArbol();
		Random generadorAleatorios = new Random();
		for (int i = 0; i < 100; i++) { //Trato de hacer 100 inserciones entre 0 y 50 para no tener siepmre éxito
			int numeroActual = generadorAleatorios.nextInt(50);
			if (arbol.insertar(numeroActual, null)){
				//Si el arbol 1 me deja insertarlo, entonces el equivalente también, y el similar me tiene que dejar insertar numero -1
				assertTrue(arbolSimilar.insertar(numeroActual-1, null));
				assertTrue(arbolEquivalente.insertar(numeroActual, null));
			} else {
				assertFalse(arbolSimilar.insertar(numeroActual-1, null));
				assertFalse(arbolEquivalente.insertar(numeroActual, null));
			}
			assertTrue(arbol.similar(arbolSimilar));
			assertTrue(arbol.equivalente(arbolEquivalente));
		}		
	}

	@Test
	public void testEliminar() {
		Random generadorAleatorios = new Random();
		for (int i = 0; i < 100; i++) { //Trato de hacer 100 inserciones entre 0 y 50 para no tener siepmre éxito
			int numeroActual = generadorAleatorios.nextInt(50);
			if (arbol.buscar(numeroActual)== null){
				assertTrue(arbol.insertar(numeroActual, numeroActual));
			} else {
				assertFalse(arbol.insertar(numeroActual, numeroActual));
			}
			
			boolean eliminar = generadorAleatorios.nextBoolean();
			if (eliminar){
				assertTrue(arbol.eliminar(numeroActual));
				assertFalse(arbol.eliminar(numeroActual));
			} 
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testOrden() {
		Comparable[] ordenEsperado = new Comparable[0];
		assertArrayEquals(ordenEsperado, arbol.inOrden());
		assertArrayEquals(ordenEsperado, arbol.preOrden());
		assertArrayEquals(ordenEsperado, arbol.postOrden());
		
		arbol.insertar("8", "8");
		arbol.insertar("2", "2");
		arbol.insertar("3", "3");
		
		//inorden: 2,3,8
		Comparable[] inOrdenEsperado = new Comparable[] {"2","3","8"};
		assertArrayEquals(inOrdenEsperado,arbol.inOrden());
		
		//preorden: 8,2,3
		Comparable[] preOrdenEsperado = new Comparable[] {"8","2","3"};
		assertArrayEquals(preOrdenEsperado, arbol.preOrden());
		
		//postorden: 3,2,8
		Comparable[] postOrdenEsperado = new Comparable[] {"3","2","8"};
		assertArrayEquals(postOrdenEsperado, arbol.postOrden());
		
		arbol.insertar("4", "4");
		arbol.insertar("6", "6");
		arbol.insertar("1", "1");
		
		//inorden: 1,2,3,4,6,8
		inOrdenEsperado = new Comparable[] {"1","2","3","4","6","8"};
		assertArrayEquals(inOrdenEsperado,arbol.inOrden());
		
		//preorden: 8,2,1,3,4,6
		preOrdenEsperado = new Comparable[] {"8","2","1","3","4","6"};
		assertArrayEquals(preOrdenEsperado, arbol.preOrden());
		
		//postorden: 1,6,4,3,2,8
		postOrdenEsperado = new Comparable[] {"1","6","4","3","2","8"};
		assertArrayEquals(postOrdenEsperado, arbol.postOrden());
	}

}
