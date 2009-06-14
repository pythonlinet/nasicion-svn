package uy.edu.ucu.pii.grupo14.datos.lista;

@SuppressWarnings("unchecked")
public class TNodo <u> implements Comparable{
	protected Comparable clave;
	private TNodo<u> siguiente;
	protected u elemento;
	
	/*
	 * Inicio bloque getters y setters
	 */
	public TNodo<u> getSiguiente() {
		return siguiente;
	}
	public void setSiguiente(TNodo siguiente) {
		this.siguiente = siguiente;
	}
	public Comparable getClave() {
		return this.clave;
	}
	public void setClave(Comparable clave) {
		this.clave = clave;
	}
	public u getElemento() {
		return elemento;
	}
	public void setElemento(u elemento) {
		this.elemento = elemento;
	}
	
	
	/*
	 * Fin bloque getters y setters
	 */
	public TNodo(Comparable clave, u elemento) {
		super();
		this.clave = clave;
		this.elemento = elemento;
	}
		
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return this.clave.compareTo(((TNodo)o).getClave());
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.clave.toString();
	}

}
	