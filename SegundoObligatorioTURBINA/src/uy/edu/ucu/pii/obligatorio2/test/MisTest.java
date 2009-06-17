package uy.edu.ucu.pii.obligatorio2.test;

import java.util.Comparator;

import org.junit.Test;

import uy.edu.ucu.pii.grupo14.datos.lista.comparadores.compararCostoPorDistancia;
import uy.edu.ucu.pii.grupo14.datos.lista.comparadores.compararCostoPorRendimiento;
import uy.edu.ucu.pii.grupo14.datos.lista.comparadores.compararCostoPorTiempo;
import uy.edu.ucu.pii.obligatorio2.Obligatorio;
import uy.edu.ucu.pii.obligatorio2.entidades.Avion;
import uy.edu.ucu.pii.obligatorio2.entidades.Ciudad;
import uy.edu.ucu.pii.obligatorio2.entidades.Costo;
import uy.edu.ucu.pii.obligatorio2.entidades.Tramo;

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
	public void testCompararCostos(){
		
		Costo costo1 = new Costo(20.0,300.0);
		Costo costo2 = new Costo(30.0,200.0);
		
		Tramo tramo1 = new Tramo(new Ciudad("Mvd","Uru"), costo1);
		Tramo tramo2 = new Tramo(new Ciudad("BsAs","Arg"), costo2);
		
		Comparator<Tramo> comparadorXTiempo = new compararCostoPorTiempo();
		int comparacion = comparadorXTiempo.compare(tramo1, tramo2);
		assertTrue(comparacion < 0);
		
		//Probamos el comparador de tramos por distancia
		Comparator<Tramo> comparadorXDistancia = new compararCostoPorDistancia();
		comparacion = comparadorXDistancia.compare(tramo1, tramo2);
		assertTrue(comparacion > 0);
		
		
		Avion avion1 = new Avion("USS Enterpries", 2.0);
		Avion avion2 = new Avion("USS Voyager", 3.0);
		
		tramo1.agregarAvion(avion1);
		tramo2.agregarAvion(avion2);
		
		//Probamos el comparador de tramos por rendmiento de avion
		Comparator<Tramo> comparadorTramosXRendimiento = new compararCostoPorRendimiento();
		comparacion = comparadorTramosXRendimiento.compare(tramo1,tramo2);
		assertTrue(comparacion == 0);
		//Aumentamos el valor del rendimiento del avion1
		avion1.setRendimiento(4.0);
		comparacion = comparadorTramosXRendimiento.compare(tramo1,tramo2);
		assertTrue(comparacion > 0);
		
		//Agregamos un nuevo avion al tramo1 con un mejor 
		Avion avion3 = new Avion("USS Capt Miranda", 1.0);
		tramo1.agregarAvion(avion3);
		comparacion = comparadorTramosXRendimiento.compare(tramo1,tramo2);
		assertTrue(comparacion < 0);
	}
}
