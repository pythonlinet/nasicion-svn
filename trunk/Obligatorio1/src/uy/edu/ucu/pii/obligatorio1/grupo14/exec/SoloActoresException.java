package uy.edu.ucu.pii.obligatorio1.grupo14.exec;

@SuppressWarnings("serial")
public class SoloActoresException extends Exception {
	
	private static final String SOLO_ACTORES_EXCEPTION = "Esta categoria solo permite el ingreso de actores : ";
	
	public SoloActoresException(String msg){
		System.err.println(SOLO_ACTORES_EXCEPTION + msg);
	}

}
