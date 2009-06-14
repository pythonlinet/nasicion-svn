package uy.edu.ucu.pii.obligatorio2.test;

import org.junit.Test;

import uy.edu.ucu.pii.obligatorio2.Obligatorio;

import junit.framework.TestCase;

public class MisTest extends TestCase{
	private Obligatorio obli;

	
	
	@Test
	public void testAgregarAviones(){
//		obli = new Obligatorio();
//		obli.agregarAvion("USS 9", 4.0);
//		obli.agregarAvion("USS 3", 1.0);
//		obli.agregarAvion("USS 2", 5.0);
//		obli.agregarAvion("USS 8", 3.0);
//		obli.agregarAvion("USS 1", 2.0);
//		obli.agregarAvion("USS 9", 4.0);
//		obli.agregarAvion("USS 3", 1.0);
//		obli.agregarAvion("USS 1", 2.0);
//		obli.agregarAvion("USS 2", 5.0);
//		
//		assertEquals(obli.getAviones().getTamanio(), new Integer(5));
//		for(Comparable c: obli.listadoAvionesPorRendimiento())
//			System.out.println(c);
		
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
