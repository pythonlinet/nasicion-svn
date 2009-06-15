package uy.edu.ucu.pii.obligatorio2;

import java.util.Comparator;

import uy.edu.ucu.pii.grupo14.datos.lista.TLista;
import uy.edu.ucu.pii.grupo14.datos.lista.comparadores.compararAvionesPorRendimiento;
import uy.edu.ucu.pii.obligatorio2.entidades.Avion;
import uy.edu.ucu.pii.obligatorio2.entidades.Ciudad;
import uy.edu.ucu.pii.obligatorio2.entidades.Ciudades;
import uy.edu.ucu.pii.obligatorio2.entidades.Costo;

/**
 * @author catedraPII
 *
 */
public class Obligatorio {
	//Declaro la lista de aviones
	TLista<Avion> aviones;
	Ciudades grafo;
	
	
	
	public TLista<Avion> getAviones() {
		return aviones;
	}


	public Obligatorio() {
		aviones = new TLista<Avion>();
		grafo = new Ciudades();
	}

	/*
	 * M�todos de carga/mantenimiento de datos
	 */
	/**
	 * Utilizado para cargar datos de un nuevo avi�n
	 * @param nombreAvion
	 * @param rendimiento
	 * @return resultado de la operaci�n
	 */
	@SuppressWarnings("unchecked")
	public boolean agregarAvion(Comparable nombreAvion, Double rendimiento){
		return aviones.insertar(nombreAvion,new Avion(nombreAvion,rendimiento));
	}
	
	/**	 
	 * Utilizado para cargar datos de una nueva ciudad
	 * @param nombre
	 * @param nombrePais
	 * @return resultado de la operaci�n
	 */
	@SuppressWarnings("unchecked")
	public boolean agregarCiudad(Comparable nombre, Comparable nombrePais){
		return grafo.insertarCiudad(nombre, nombrePais);
	}
	
	/**
	 * Utilizado para cargar datos de un nuevo tramo
	 * @param nomCiudadOrigen
	 * @param nomCiudadDestino
	 * @param distanciaEnKm
	 * @param tiempoEstimadoEnMinutos
	 * @return resultado de la operaci�n
	 */
	@SuppressWarnings("unchecked")
	public boolean agregarTramo(Comparable nomCiudadOrigen, Comparable nomCiudadDestino, Double distanciaEnKm, Double tiempoEstimadoEnMinutos){
		return grafo.agregarTramo(nomCiudadOrigen, nomCiudadDestino, new Costo(tiempoEstimadoEnMinutos,distanciaEnKm));
	}
	
	/**
	 * Utilizado para asignar un avi�n a una lista de tramos
	 * @param nombreAvion
	 * @param tramoAAsignar array de 2 posiciones, con un par ordenado de ciudades 
	 * @return resultado de la conjunci�n de todas las operaciones
	 */
	@SuppressWarnings("unchecked")
	public boolean asignarAvionATramo(Comparable nombreAvion, Comparable etiquetaOrigen, Comparable etiquetaDestino){
		//TODO - Implementar m�todo
		return false;
	}
	
	/**
	 * Utilizado para quitar un tramo de las rutas. En este caso, se quita la asociaci�n de todos los aviones con este tramo
	 * @param nomCiudadOrigen
	 * @param nomCiudadDestino
	 * @return resultado de la operaci�n
	 */
	@SuppressWarnings("unchecked")
	public boolean quitarTramo(Comparable nomCiudadOrigen, Comparable nomCiudadDestino){
		return grafo.quitarTramo(nomCiudadOrigen, nomCiudadDestino);		
	}
	
	/**
	 * Utilizado para quitar un avi�n de circulaci�n. Se quita la asignaci�n de dicho avi�n con todos los tramos (itinerarios de vuelo)
	 * @param nombreAvion
	 * @return resultado de la operaci�n
	 */
	@SuppressWarnings("unchecked")
	public boolean quitarAvion(Comparable nombreAvion){
		//TODO - Implementar m�todo
		return false;
	}
	
	/**
	 * Utilizado para quitar una ciudad de nuestros itinerarios. Se eliminan todos los tramos asociados con la ciudad, y todos los itinerarios
	 * @param nomCiudad
	 * @return resultado de la operaci�n
	 */
	@SuppressWarnings("unchecked")
	public boolean quitarCiudad(Comparable nomCiudad){
		//TODO - Implementar m�todo
		return false;
	}
	
	/*
	 * M�todos de consulta
	 */
	
	/**
	 * @return Lista con los nombres de los aviones, ordenados por su rendimiento (descendente seg�n km/l)
	 */
	@SuppressWarnings("unchecked")
	public Comparable[] listadoAvionesPorRendimiento(){
		Comparable[] salida = new Integer[0];

		Comparable[] aux  = aviones.ordenar(new compararAvionesPorRendimiento());
		salida = aux != null?aux:salida;
		
		return salida;
	}
	
	/**
	 * @param nomCiudadOrigen
	 * @param nomCiudadDestino
	 * @return array con los nombres de las ciudades por las que se pasa
	 */
	@SuppressWarnings("unchecked")
	public Comparable[] mejorItinerarioParaEmpresa(Comparable nomCiudadOrigen, Comparable nomCiudadDestino){
		//TODO - Implementar m�todo
		return null;
	}
	
	/**
	 * @param nomCiudadOrigen
	 * @param nomCiudadDestino
	 * @return array con los nombres de las ciudades por las que se pasa
	 */
	@SuppressWarnings("unchecked")
	public Comparable[] mejorItinerarioParaCliente(Comparable nomCiudadOrigen, Comparable nomCiudadDestino){
		//TODO - Implementar m�todo
		return null;
	}
	
	/**
	 * @return nombre de la ciudad a ser utilizada como centro de operaciones
	 */
	@SuppressWarnings("unchecked")
	public Comparable obtenerCentroOperaciones(){
		//TODO - Implementar m�todo
		return null;
	}
	
	/**
	 * @param nombreCiudad
	 * @return Listado de nombres de aviones que pasan por dicha ciudad
	 */
	@SuppressWarnings("unchecked")
	public Comparable[] avionesParaCiudad(String nombreCiudad){
		//TODO - Implementar m�todo
		return null;
	}

	@SuppressWarnings("unchecked")
	public Comparable obtenerRendimientoAvion(String nombreAvion) {
		return aviones.buscarNodo(nombreAvion).getElemento().getRendimiento();
	}

	public boolean existeTramo(String nombreCiudadOrigen, String nombreCiudadDestino) {
		//TODO - Implementar m�todo
		return false;
	}

	public Object obtenerDistanciaTramo(String ciudadOrigen, String ciudadDestino) {
		//TODO - Implementar m�todo
		return null;
	}

	public Object obtenerTiempoEstimadoTramo(String ciudadOrigen, String ciudadDestino) {
		//TODO - Implementar m�todo
		return null;
	}

	public boolean existeCamino(String ciudadOrigen, String ciudadDestino) {
		//TODO - Implementar m�todo
		return false;
	}
}
