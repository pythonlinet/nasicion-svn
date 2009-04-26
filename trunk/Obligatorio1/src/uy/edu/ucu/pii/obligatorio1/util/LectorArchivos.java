package uy.edu.ucu.pii.obligatorio1.util;

import java.io.BufferedReader;
import java.io.FileReader;

import uy.edu.ucu.pii.obligatorio1.grupo14.datos.lista.TLista;
import uy.edu.ucu.pii.obligatorio1.grupo14.datos.lista.TNodo;

public class LectorArchivos {
    
    /**
     * 
     * fichero:archivo donde se desea leer.
     * return: lista de strings contenidos en el archivo. En caso de estar vacio
     * retorna null.
     */
    public static String[] leerArchivo (String fichero) {
        TLista salida = new TLista();
        String linea;
        try {
            BufferedReader in = new BufferedReader(new FileReader(fichero));
            while ((linea = in.readLine()) != null)
                salida.insertar(linea,null);
            in.close();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return convertirAStringArray(salida);
    }
    
 
    private static String[] convertirAStringArray(TLista lista) {
    	int tamanioLista = lista.getTamanio();
		String salida[] = new String[tamanioLista];
		TNodo nodoActual = lista.recuperar(0); 
		for (int posicionActual = 0; posicionActual < tamanioLista; posicionActual++) {
			salida[posicionActual] = (String) nodoActual.getClave();
			nodoActual = nodoActual.getSiguiente();
		}    	
		return salida;
	}
}
