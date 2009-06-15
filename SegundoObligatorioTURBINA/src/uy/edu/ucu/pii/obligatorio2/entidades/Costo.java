package uy.edu.ucu.pii.obligatorio2.entidades;

public class Costo {
	private Double tiempoEstimadoEnMinutos;
	private Double distanciaEnKm;
	public Double getTiempoEstimadoEnMinutos() {
		return tiempoEstimadoEnMinutos;
	}
	public void setTiempoEstimadoEnMinutos(Double tiempoEstimadoEnMinutos) {
		this.tiempoEstimadoEnMinutos = tiempoEstimadoEnMinutos;
	}
	public Double getDistanciaEnKm() {
		return distanciaEnKm;
	}
	public void setDistanciaEnKm(Double distanciaEnKm) {
		this.distanciaEnKm = distanciaEnKm;
	}
	public Costo(Double tiempoEstimadoEnMinutos, Double distanciaEnKm) {
		super();
		this.tiempoEstimadoEnMinutos = tiempoEstimadoEnMinutos;
		this.distanciaEnKm = distanciaEnKm;
	}
	
	
}
