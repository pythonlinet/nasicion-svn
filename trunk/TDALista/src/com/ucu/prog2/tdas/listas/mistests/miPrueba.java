package com.ucu.prog2.tdas.listas.mistests;

import static org.junit.Assert.assertFalse;
import uy.edu.ucu.pii.grupo14.datos.lista.TLista;


public class miPrueba {
	public static void main(String[] args) {
		TLista lista = new TLista();

		for (int i = 0; i < 10; i++) {
			lista.insertarOrdenado(1000+i, null);
		}
		
		Integer claveExistente = 1005;
		assertFalse(lista.insertarOrdenado(claveExistente, null));
		
		Integer claveChica = 500;
		Integer claveGrande = 1500;
		Integer claveMuyChica = 100;
		Integer claveMuyGrande = 5000;
		
		lista.insertar(claveGrande, null);
		lista.insertar(claveChica, null);
		lista.insertar(claveMuyChica, null);
		lista.insertar(claveMuyGrande, null);

		for(Comparable c : lista.mostrar())
			System.out.print(c + " ");
		System.out.println();
		
		
				
		for(Comparable c : lista.mostrar())
			System.out.print(c + " ");
		System.out.println();
	
		
		
	}
}
