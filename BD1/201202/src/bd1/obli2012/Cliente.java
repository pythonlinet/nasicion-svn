package bd1.obli2012;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bd1.obli2012.framework.estructura.ColumnDefinition;
import bd1.obli2012.framework.estructura.DataType;
import bd1.obli2012.framework.estructura.Entidad;
import bd1.obli2012.framework.estructura.TableDefinition;

public class Cliente extends Entidad {

	private static final String tabla = "cliente";
	private String primerNombre;
	private String primerApellido;
	private String direccion;
	private String ciudad;
	private String pais;
	private Date fechaNacimiento;
	
	@Override
	public String getTabla() {
		return tabla;
	}

	@Override
	public Cliente createFromRecordSet(ResultSet rs) {
		// TODO Auto-gen0rated method stub
		return null;
	}

	@Override
	public List<Cliente> createListFromRecordSet(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableDefinition getTableDefinition() {
		/*CREATE TABLE customer 
		 * (First_Name CHARACTER(50), 
		 * Last_Name CHARACTER(50), 
		 * Address CHARACTER(50), 
		 * City CHARACTER(50), 
		 * Country CHARACTER(25), 
		 * Birth_Date date, 
		*/
		List<ColumnDefinition> defColumnas = new ArrayList<ColumnDefinition>();
		defColumnas.add(new ColumnDefinition("primer_nombre", DataType.CHARACTER, "50"));
		defColumnas.add(new ColumnDefinition("primer_apellido", DataType.CHARACTER, "50"));
		defColumnas.add(new ColumnDefinition("direccion", DataType.CHARACTER, "50"));
		defColumnas.add(new ColumnDefinition("ciudad", DataType.CHARACTER, "50"));
		defColumnas.add(new ColumnDefinition("pais", DataType.CHARACTER, "50"));
		defColumnas.add(new ColumnDefinition("fec_nac", DataType.DATE, null));
		
		TableDefinition td = new TableDefinition(tabla, defColumnas );
		return td;
	}
//Bloque de getters y setters
	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
}
