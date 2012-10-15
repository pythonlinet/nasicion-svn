package bd1.obli2012.framework.estructura;

/**
 * 
 * @author gnasi
 *
 */
public class Constraint {
	private ConstraintType constrainType;
	private String parametros;
	
	public ConstraintType getConstrainType() {
		return constrainType;
	}
	public void setConstrainType(ConstraintType constrainType) {
		this.constrainType = constrainType;
	}
	public String getParametros() {
		return parametros;
	}
	public void setParametros(String parametros) {
		this.parametros = parametros;
	}
	
	/**
	 * Retorna la definición de la constraint
	 * @return {@link String} conteniendo la definción de la constraint
	 */
	public String getDefinicionDeConstraint(){
		return this.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder strBld = new StringBuilder();
		strBld.append(this.constrainType.toString());
		if(this.constrainType.getTieneParametro()){
			strBld.append(this.parametros);
		}
		return strBld.toString();
	}
}
