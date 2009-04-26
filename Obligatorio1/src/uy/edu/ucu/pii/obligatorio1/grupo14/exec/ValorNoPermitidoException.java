package uy.edu.ucu.pii.obligatorio1.grupo14.exec;

@SuppressWarnings("serial")
public class ValorNoPermitidoException extends Exception {
	private static final String VALOR_NO_PERMITIDO = "Valor no permitido: ";
	
	public ValorNoPermitidoException(String msg){
		System.err.println(VALOR_NO_PERMITIDO + msg);
	}
}
