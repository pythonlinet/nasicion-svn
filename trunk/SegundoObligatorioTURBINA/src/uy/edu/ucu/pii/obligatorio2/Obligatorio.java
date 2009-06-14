package uy.edu.ucu.pii.obligatorio2;

/**
 * @author catedraPII
 *
 */
public class Obligatorio {
	/*
	 * Métodos de carga/mantenimiento de datos
	 */
	/**
	 * Utilizado para cargar datos de un nuevo avión
	 * @param nombreAvion
	 * @param rendimiento
	 * @return resultado de la operación
	 */
	@SuppressWarnings("unchecked")
	public boolean agregarAvion(Comparable nombreAvion, Double rendimiento){
		//TODO - Implementar método
		return false;
	}
	
	/**	 
	 * Utilizado para cargar datos de una nueva ciudad
	 * @param nombre
	 * @param nombrePais
	 * @return resultado de la operación
	 */
	@SuppressWarnings("unchecked")
	public boolean agregarCiudad(Comparable nombre, Comparable nombrePais){
		//TODO - Implementar método
		return false;
	}
	
	/**
	 * Utilizado para cargar datos de un nuevo tramo
	 * @param nomCiudadOrigen
	 * @param nomCiudadDestino
	 * @param distanciaEnKm
	 * @param tiempoEstimadoEnMinutos
	 * @return resultado de la operación
	 */
	@SuppressWarnings("unchecked")
	public boolean agregarTramo(Comparable nomCiudadOrigen, Comparable nomCiudadDestino, Double distanciaEnKm, Double tiempoEstimadoEnMinutos){
		//TODO - Implementar método
		return false;
	}
	
	/**
	 * Utilizado para asignar un avión a una lista de tramos
	 * @param nombreAvion
	 * @param tramoAAsignar array de 2 posiciones, con un par ordenado de ciudades 
	 * @return resultado de la conjunción de todas las operaciones
	 */
	@SuppressWarnings("unchecked")
	public boolean asignarAvionATramo(Comparable nombreAvion, Comparable etiquetaOrigen, Comparable etiquetaDestino){
		//TODO - Implementar método
		return false;
	}
	
	/**
	 * Utilizado para quitar un tramo de las rutas. En este caso, se quita la asociación de todos los aviones con este tramo
	 * @param nomCiudadOrigen
	 * @param nomCiudadDestino
	 * @return resultado de la operación
	 */
	@SuppressWarnings("unchecked")
	public boolean quitarTramo(Comparable nomCiudadOrigen, Comparable nomCiudadDestino){
		//TODO - Implementar método
		return false;		
	}
	
	/**
	 * Utilizado para quitar un avión de circulación. Se quita la asignación de dicho avión con todos los tramos (itinerarios de vuelo)
	 * @param nombreAvion
	 * @return resultado de la operación
	 */
	@SuppressWarnings("unchecked")
	public boolean quitarAvion(Comparable nombreAvion){
		//TODO - Implementar método
		return false;
	}
	
	/**
	 * Utilizado para quitar una ciudad de nuestros itinerarios. Se eliminan todos los tramos asociados con la ciudad, y todos los itinerarios
	 * @param nomCiudad
	 * @return resultado de la operación
	 */
	@SuppressWarnings("unchecked")
	public boolean quitarCiudad(Comparable nomCiudad){
		//TODO - Implementar método
		return false;
	}
	
	/*
	 * Métodos de consulta
	 */
	
	/**
	 * @return Lista con los nombres de los aviones, ordenados por su rendimiento (descendente según km/l)
	 */
	@SuppressWarnings("unchecked")
	public Comparable[] listadoAvionesPorRendimiento(){
		//TODO - Implementar método
		return null;
	}
	
	/**
	 * @param nomCiudadOrigen
	 * @param nomCiudadDestino
	 * @return array con los nombres de las ciudades por las que se pasa
	 */
	@SuppressWarnings("unchecked")
	public Comparable[] mejorItinerarioParaEmpresa(Comparable nomCiudadOrigen, Comparable nomCiudadDestino){
		//TODO - Implementar método
		return null;
	}
	
	/**
	 * @param nomCiudadOrigen
	 * @param nomCiudadDestino
	 * @return array con los nombres de las ciudades por las que se pasa
	 */
	@SuppressWarnings("unchecked")
	public Comparable[] mejorItinerarioParaCliente(Comparable nomCiudadOrigen, Comparable nomCiudadDestino){
		//TODO - Implementar método
		return null;
	}
	
	/**
	 * @return nombre de la ciudad a ser utilizada como centro de operaciones
	 */
	@SuppressWarnings("unchecked")
	public Comparable obtenerCentroOperaciones(){
		//TODO - Implementar método
		return null;
	}
	
	/**
	 * @param nombreCiudad
	 * @return Listado de nombres de aviones que pasan por dicha ciudad
	 */
	@SuppressWarnings("unchecked")
	public Comparable[] avionesParaCiudad(String nombreCiudad){
		//TODO - Implementar método
		return null;
	}

	@SuppressWarnings("unchecked")
	public Comparable obtenerRendimientoAvion(String nombreAvion) {
		//TODO - Implementar método
		return null;
	}

	public boolean existeTramo(String nombreCiudadOrigen, String nombreCiudadDestino) {
		//TODO - Implementar método
		return false;
	}

	public Object obtenerDistanciaTramo(String ciudadOrigen, String ciudadDestino) {
		//TODO - Implementar método
		return null;
	}

	public Object obtenerTiempoEstimadoTramo(String ciudadOrigen, String ciudadDestino) {
		//TODO - Implementar método
		return null;
	}

	public boolean existeCamino(String ciudadOrigen, String ciudadDestino) {
		//TODO - Implementar método
		return false;
	}
}
