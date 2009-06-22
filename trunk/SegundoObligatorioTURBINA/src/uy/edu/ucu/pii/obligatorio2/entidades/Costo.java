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
	
	
	public Costo() {
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return tiempoEstimadoEnMinutos + "Mins " + distanciaEnKm + "Km";
	}
	
	@Override
	public boolean equals(Object arg0) {
		boolean salida = false;
		if(arg0 instanceof Costo){
			salida = ((Costo)arg0).getDistanciaEnKm().equals(this.distanciaEnKm) && ((Costo)arg0).getTiempoEstimadoEnMinutos().equals(this.tiempoEstimadoEnMinutos);
		}
		return salida;
	}
	
}
