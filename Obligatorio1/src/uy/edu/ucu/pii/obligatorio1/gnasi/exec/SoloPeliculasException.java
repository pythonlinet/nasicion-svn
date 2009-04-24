/**
 * 
 */
package uy.edu.ucu.pii.obligatorio1.gnasi.exec;

/**
 * @author Kyubi
 *
 */
@SuppressWarnings("serial")
public class SoloPeliculasException extends Exception {
	private static final String SOLO_PELICULAS_EXCEPTION = "Esta categoria solo permite el ingreso de peliculas : ";
	
	public SoloPeliculasException(String msg){
		System.err.println(SOLO_PELICULAS_EXCEPTION + msg);
	}
}
