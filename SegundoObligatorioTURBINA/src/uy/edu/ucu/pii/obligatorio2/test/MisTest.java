package uy.edu.ucu.pii.obligatorio2.test;

import org.junit.Test;

import uy.edu.ucu.pii.obligatorio2.Obligatorio;

import junit.framework.TestCase;

public class MisTest extends TestCase{
	private Obligatorio obli;

	
	@Test
	public void testGenerales(){
		obli = new Obligatorio();
		obli.agregarAvion("USS 9", 4.0);
		obli.agregarAvion("USS 3", 1.0);
		obli.agregarAvion("USS 2", 5.0);
		obli.agregarAvion("USS 8", 3.0);
		obli.agregarAvion("USS 1", 2.0);
		
		obli.getCiudades().imprimirMatrizAdyacente();
	}

	@Test
	public void testMenejoCiudades(){
		obli = new Obligatorio();
		assertTrue(obli.agregarCiudad("Montevideo","Uruguay"));
		assertTrue(obli.agregarCiudad("LaPaloma","Uruguay"));
		assertTrue(obli.agregarCiudad("Roma","Italia"));
		assertTrue(obli.agregarCiudad("Madrid","España"));
		assertTrue(obli.agregarCiudad("Shangai","China"));
		
		assertTrue(obli.quitarCiudad("Madrid"));
		assertFalse(obli.quitarCiudad("Madrid"));
		assertTrue(obli.quitarCiudad("Montevideo"));
		assertTrue(obli.quitarCiudad("Shangai"));
		
	}
	
	@Test
	public void testAgregarAviones(){

		obli = new Obligatorio();
		obli.agregarAvion("747-1", 4.3);
		obli.agregarAvion("747-2", 4.4);
		obli.agregarAvion("747-3", 4.4);
		
		obli.getAviones().ordenar();
//		for(Comparable c: obli.listadoAvionesPorRendimiento())
//			System.out.println(c);
		
	}
	@Test
	public void testOrdenarAviones(){
		
	}
}
