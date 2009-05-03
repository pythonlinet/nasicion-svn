package uy.edu.ucu.pii.obligatorio1.grupo14.datos;

public class Actor {
	private String nombre;
	private String sexo;
	private int votos;

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

	public int getVotos() {
		return votos;
	}

	public void setVotos(int votos) {
		this.votos = votos;
	}

	public Actor(String nombre, String sexo) {

		this.nombre = nombre;
		this.sexo = sexo;
		this.votos = 0;
	}

}
