package com.ucu.prog2.tdas.listas.mistests;

import org.junit.Test;

import uy.edu.ucu.pii.grupo14.datos.lista.TLista;

import junit.framework.TestCase;


public class testListaClass extends TestCase{

	@Test
	public void testLista(){
		Alumno a = new Alumno();
		a.setCedula("9876");
		a.setNombre("Carlos");
		
		TLista miLista = new TLista();
		miLista.insertar(a.getCedula(), a);
	}
	
	@Test
	public void testIndexOf(){
		TLista miLista = new TLista();
		
		//0
		miLista.insertar(4, null);
		//1
		miLista.insertar(48, null);
		//2
		miLista.insertar(344, null);
		//3
		miLista.insertar(84, null);
		//4
		miLista.insertar(89, null);
	
		assertEquals(3, miLista.indexOf(84));
		assertEquals(0, miLista.indexOf(4));
		assertEquals(1, miLista.indexOf(48));
		assertEquals(3, miLista.indexOf(84));
		assertEquals(4, miLista.indexOf(89));
		
		
	}
	
}
