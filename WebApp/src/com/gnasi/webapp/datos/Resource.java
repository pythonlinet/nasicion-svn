package com.gnasi.webapp.datos;

import javax.persistence.DiscriminatorValue;

import org.hibernate.annotations.Entity;



@Entity
@DiscriminatorValue("resource")
public class Resource extends Person{
	private Long employeeNumber;
	private String usuario;
	private String password;
	
	public Long getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(Long employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
		
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Resource() {
	}
	
	
}
