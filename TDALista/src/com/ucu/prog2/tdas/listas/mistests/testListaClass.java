package com.ucu.prog2.tdas.listas.mistests;

import org.junit.Test;

import uy.edu.ucu.pii.obligatorio1.grupo14.datos.lista.TLista;

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
	
}
