package uy.edu.ucu.pii.obligatorio1.gnasi.datos;

import uy.edu.ucu.pii.obligatorio1.gnasi.datos.lista.TLista;
import uy.edu.ucu.pii.obligatorio1.gnasi.exec.SoloPeliculasException;

public class Categoria {
	private String nomCategoria;
	private String tipo;
	private TLista nominados;

//******************************************************************************/
	public String getNomCategoria() {
		return nomCategoria;
	}

	public String getTipo() {
		return tipo;
	}

	public TLista getNominados() {
		return nominados;
	}
//******************************************************************************/
	
	public Categoria(String nomCategoria, String tipo) {
		super();
		this.nomCategoria = nomCategoria;
		this.tipo = tipo;
		this.nominados = new TLista();
	}

	public boolean nominarActorACategoria(Actor actor) throws SoloPeliculasException {
		boolean salida = false;
		
		if(this.tipo.equals("P"))
			throw new SoloPeliculasException(this.getNomCategoria());
		salida = getNominados().insertarOrdenado(actor.getNombre(), actor);
		
		return salida;
	}
	
	

}
