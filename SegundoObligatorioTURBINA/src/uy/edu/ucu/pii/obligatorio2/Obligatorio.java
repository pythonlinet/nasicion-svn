package uy.edu.ucu.pii.obligatorio2;

import uy.edu.ucu.pii.grupo14.datos.lista.TLista;
import uy.edu.ucu.pii.grupo14.datos.lista.TNodo;
import uy.edu.ucu.pii.obligatorio2.entidades.Avion;
import uy.edu.ucu.pii.obligatorio2.entidades.Ciudad;
import uy.edu.ucu.pii.obligatorio2.entidades.Ciudades;
import uy.edu.ucu.pii.obligatorio2.entidades.Costo;
import uy.edu.ucu.pii.obligatorio2.entidades.Tramo;
import uy.edu.ucu.pii.obligatorio2.grupo14.comparadores.compararAvionesPorRendimiento;
import uy.edu.ucu.pii.obligatorio2.grupo14.comparadores.compararCostoPorDistancia;
import uy.edu.ucu.pii.obligatorio2.grupo14.comparadores.compararCostoPorRendimiento;
import uy.edu.ucu.pii.obligatorio2.grupo14.comparadores.compararCostoPorTiempo;

/**
 * Clase principal del 2do Obligatorio, la mayoria de los metodos son solo llamadas a los metodos de la clase {@link Ciudades}
 * @author Grupo14
 *	
 * @see Ciudades
 * @see Ciudad
 * @see Avion
 * @see TLista
 * 
 * @version 1.0
 */
public class Obligatorio {
	//Declaro la lista de aviones
	TLista<Avion> aviones;
	Ciudades grafo;
	public Ciudades getCiudades(){
		return this.grafo;
	}
	
	
	
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
	 * 
	 * @param nombreAvion nombre del avion
	 * @param etiquetaOrigen origen del tramo
	 * @param etiquetaDestino destino del tramo
	 * @return true - si se compelta la operacion con exito;
	 * false - en los casos que: el avion no exista o ya este asignado al tramo o los casos en que el origo y/o el destino no existan
	 */
	@SuppressWarnings("unchecked")
	public boolean asignarAvionATramo(Comparable nombreAvion, Comparable etiquetaOrigen, Comparable etiquetaDestino){
		boolean salida = false;
		TNodo<Avion> nodoAvion = getAviones().buscarNodo(nombreAvion);
		//Si existe el avion
		if(nodoAvion != null){
			Avion avion = nodoAvion.getElemento();
			TNodo<Ciudad> nodoCiudadOrigen = grafo.getCiudades().buscarNodo(etiquetaOrigen);
			//Si existe el origen
			if(nodoCiudadOrigen != null){
				Ciudad cOrigen = nodoCiudadOrigen.getElemento();
				TNodo<Tramo> nodoTramo = cOrigen.getTramos().buscarNodo(etiquetaDestino);
				//Si existe un tramo entre ambas ciudades
				if(nodoTramo != null){
					Tramo tramo = nodoTramo.getElemento();
					//Asigno el avion al tramo
					salida = tramo.getAvionesAsignados().insertarOrdenado(avion.getNombre(), avion,new compararAvionesPorRendimiento());
					if(salida){
						salida = avion.getTramosAsignados().insertar(cOrigen.getNombre().toString()+ tramo.getCiudadDestino().getNombre(), tramo);
					}
				}
					
			}
		}
		return salida;
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
	 * @return true - si se elmino satisfactoriamente el avion; false - si no se pudo elimnar el avion o el avion no existe
	 */
	@SuppressWarnings("unchecked")
	public boolean quitarAvion(Comparable nombreAvion){
		boolean salida = false;	
		TNodo<Avion> nodoAvion = getAviones().buscarNodo(nombreAvion);
			if(nodoAvion != null){
				for(Tramo tramo : nodoAvion.getElemento().getTramosAsignados()){
					tramo.getAvionesAsignados().eliminar(nombreAvion);
				}
				salida = getAviones().eliminar(nombreAvion);
			}
			
		return salida;
	}
	
	/**
	 * Utilizado para quitar una ciudad de nuestros itinerarios. Se eliminan todos los tramos asociados con la ciudad, y todos los itinerarios
	 * @param nomCiudad
	 * @return resultado de la operaci�n
	 */
	@SuppressWarnings("unchecked")
	public boolean quitarCiudad(Comparable nomCiudad){
//		para cada ciudad, que tenga un tramo a esta
//		obtener el tramo
//		con ese tramo
//			para cada avion asignado a este tramo	
//				borrar el tramo del avion
//			borrar el tramo
//
//		borrar la ciudad de la lista
		
		
		boolean salida = false;
		salida = getCiudades().getCiudades().eliminar(nomCiudad);
		//si existia la ciudad y la pude eliminar, entonces procedo a borrar las referencias (tramos)
		if(salida){
			TNodo<Ciudad> aux = getCiudades().getCiudades().getPrimero();
			Ciudad ciudadAux;
			TNodo<Tramo> tramoAux;
			TLista<Avion> avionesAsignados;
			TNodo<Avion> avionAux;
			Avion avion;
			while(aux != null){
				ciudadAux = aux.getElemento();
				//Buscamos si hay un tramo desde esta ciudad hacia la que queremos borrar
				tramoAux = ciudadAux.getTramos().buscarNodo(nomCiudad);
				//Borrarmos los tramos de los aviones
				if(tramoAux != null){
					//Obtengo los aviones asignados
					avionesAsignados = tramoAux.getElemento().getAvionesAsignados();
					avionAux = avionesAsignados.getPrimero();
					//Para cada avion asignado a este tramo, elimino el tramo de la lista del avion				
					while(avionAux != null){
						avionAux.getElemento().getTramosAsignados().eliminar(ciudadAux.getNombre().toString()+nomCiudad);
						avionAux = avionAux.getSiguiente();
					}
					
				}
				ciudadAux.getTramos().eliminar(nomCiudad);
				aux = aux.getSiguiente();
			}
		}
		
		return salida;
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
	 * Devuelve la lista de ciudades que componen el mejor itinerario para la empresa entre dos ciudades
	 * La lista se arma en base las distancias entre las ciudades y el consumo del avion mas barato que recorre ese tramo
	 * @param nomCiudadOrigen
	 * @param nomCiudadDestino
	 * @return array con los nombres de las ciudades por las que se pasa
	 */
	@SuppressWarnings("unchecked")
	public Comparable[] mejorItinerarioParaEmpresa(Comparable nomCiudadOrigen, Comparable nomCiudadDestino){
		return getCiudades().mejorCamino(nomCiudadOrigen, nomCiudadDestino, new compararCostoPorRendimiento());
	}
	
	/**
	 * Devuelve la lista de ciudades que componen el mejor itinerario para el cliente entre dos ciudades
	 * La lista se arma en base al tiempo entre las ciudades, de forma que el cliente haga el camino mas corto en tiempo
	 * @param nomCiudadOrigen
	 * @param nomCiudadDestino
	 * @return array con los nombres de las ciudades por las que se pasa
	 */
	@SuppressWarnings("unchecked")
	public Comparable[] mejorItinerarioParaCliente(Comparable nomCiudadOrigen, Comparable nomCiudadDestino){
		
		return getCiudades().mejorCamino(nomCiudadOrigen, nomCiudadDestino, new compararCostoPorTiempo());
	}
	
	/**
	 * Retorna el centro del grafo (El centro de operaciones) 
	 * @return nombre de la ciudad a ser utilizada como centro de operaciones;
	 * null - si no hay centro de operaciones
	 */
	@SuppressWarnings("unchecked")
	public Comparable obtenerCentroOperaciones(){
		return getCiudades().centroDelGrafo(new compararCostoPorDistancia());
	}
	
	/**
	 * Retorna una lista con los aviones que van hacia una ciudad
	 * @param nombreCiudad 
	 * @return Listado de nombres de aviones que pasan por dicha ciudad
	 */
	@SuppressWarnings("unchecked")
	public Comparable[] avionesParaCiudad(String nombreCiudad){
//		si existe la ciudad param{nombreCiudad}
//			para cada avion
//			obtener lista de tramos
//				para cada tramo
//					si tiene destino == param{nombreCiudad}
//						GUARDO EL NOMBRE DEL AVION
//		
		Comparable[] salida = null;
		TNodo<Ciudad> ciudad = getCiudades().getCiudades().buscarNodo(nombreCiudad);
		//Si existe la ciudad
		if(ciudad != null){
			String aviones = new String();
			TNodo<Avion> nodoAvion = getAviones().getPrimero();
			
			//Para cada avion
			while(nodoAvion != null){
				TNodo<Tramo> nodoTramo = nodoAvion.getElemento().getTramosAsignados().getPrimero();
				
				//Para cada tramo que tenga el avion
				while(nodoTramo != null){
					if(nodoTramo.getElemento().getCiudadDestino().getNombre().compareTo(nombreCiudad) == 0)
						aviones = aviones + nodoAvion.getElemento().getNombre().toString() + ";";
					nodoTramo = nodoTramo.getSiguiente();
				}
				
				nodoAvion = nodoAvion.getSiguiente();
			}
			salida = aviones.split(";");
		}
		return salida;
	}
	
	/**
	 * Devuelve el rendimiento de un avion
	 * @param nombreAvion
	 * @return numero - el rendmiento de un avion
	 */
	@SuppressWarnings("unchecked")
	public Comparable obtenerRendimientoAvion(String nombreAvion) {
		Comparable salida = null;
		
		TNodo<Avion> nodoAvion = aviones.buscarNodo(nombreAvion); 
		salida = nodoAvion!=null?nodoAvion.getElemento().getRendimiento():null;
		
		return salida;
	}

	public boolean existeTramo(String nombreCiudadOrigen, String nombreCiudadDestino) {
		
		return grafo.existeTramo(nombreCiudadOrigen, nombreCiudadDestino);
	}

	/**
	 * Metodo para conocer la distancia entre dos ciudades
	 * @param ciudadOrigen nombre de la ciudad de origen
	 * @param ciudadDestino nombre de la ciudad de destino
	 * @return null - si no existe el tramo que las una
	 * unNumero - la distancia entre las dos ciudades
	 */
	public Object obtenerDistanciaTramo(String ciudadOrigen, String ciudadDestino) {
		Double distancia = null;
		TNodo<Ciudad> nodoOrigen = getCiudades().getCiudades().buscarNodo(ciudadOrigen);
		//SI existe el origen
		if(nodoOrigen != null){
			TNodo<Tramo> nodoTramo = nodoOrigen.getElemento().getTramos().buscarNodo(ciudadDestino);
			//SI existe un tramo que las una
			if(nodoTramo != null){
				distancia = nodoTramo.getElemento().getCostoTramo().getDistanciaEnKm();
			}
		}
		return distancia;
	}

	/**
	 * Metodo para conocer el tiempo entre dos ciudades
	 * @param ciudadOrigen nombre de la ciudad de origen
	 * @param ciudadDestino nombre de la ciudad de destino
	 * @return null - si no existe el tramo que las una
	 * unNumero - tiempo de viaje entre las dos ciudades
	 */
	public Object obtenerTiempoEstimadoTramo(String ciudadOrigen, String ciudadDestino) {
		Double tiempo = null;
		TNodo<Ciudad> nodoOrigen = getCiudades().getCiudades().buscarNodo(ciudadOrigen);
		//SI existe el origen
		if(nodoOrigen != null){
			TNodo<Tramo> nodoTramo = nodoOrigen.getElemento().getTramos().buscarNodo(ciudadDestino);
			//SI existe un tramo que las una
			if(nodoTramo != null){
				tiempo = nodoTramo.getElemento().getCostoTramo().getTiempoEstimadoEnMinutos();
			}
		}
		return tiempo;
	}
	
	/**
	 * Dice si existe un camino entre 2 ciudades
	 * @param ciudadOrigen ciudad de origen
	 * @param ciudadDestino ciudad de destino
	 * @return true - si existe un camino; false - si no existe un camino, o una de las ciudades
	 */
	public boolean existeCamino(String ciudadOrigen, String ciudadDestino) {
		return getCiudades().existeCamino(ciudadOrigen, ciudadDestino);
	}
}
