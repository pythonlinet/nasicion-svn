package uy.edu.ucu.pii.obligatorio1.grupo14.datos.lista;

@SuppressWarnings("unchecked")
public class TNodo implements Comparable{
	protected Comparable clave;
	private TNodo siguiente;
	protected Object elemento;
	
	/*
	 * Inicio bloque getters y setters
	 */
	public TNodo getSiguiente() {
		return siguiente;
	}
	public void setSiguiente(TNodo siguiente) {
		this.siguiente = siguiente;
	}
	public Object getElemento() {
		return elemento;
	}
	public void setElemento(Object objeto) {
		this.elemento = objeto;
	}
	public Comparable getClave() {
		return this.clave;
	}
	public void setClave(Comparable clave) {
		this.clave = clave;
	}
	
	/*
	 * Fin bloque getters y setters
	 */
	
	public TNodo(Comparable clave, Object elemento) {
		this.clave = clave;
		this.elemento = elemento;
	}
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return this.clave.compareTo(((TNodo)o).getClave());
	}
	
}
	