package uy.edu.ucu.pii.obligatorio1.gnasi.datos;

public class Actor {
	private String nombre;
	private String sexo;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Actor(String nombre, String sexo) {

		this.nombre = nombre;
		this.sexo = sexo;
	}

}
