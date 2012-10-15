package bd1.obli2012.framework.estructura;

public enum ConstraintType {
	NOT_NULL(false, "not null"), 
	UNIQUE(false, "unique"), 
	CHECK(true, "check"), 
	PRIMARY_KEY(false, "primary key"), 
	REFERENCES(true, "references");

	/**
	 * Indica si a la hora de crear la tabla se le asignara un parametro (ej: el
	 * largo para el character)
	 */
	private boolean tieneParametro;
	private String toStringValue;

	public boolean getTieneParametro() {
		return this.tieneParametro;
	}

	@Override
	public String toString() {
		return toStringValue;
	}

	private ConstraintType(boolean tieneParametro, String toStringValue) {
		this.tieneParametro = tieneParametro;
	}
}
