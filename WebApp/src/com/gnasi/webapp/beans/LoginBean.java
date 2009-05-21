package com.gnasi.webapp.beans;

import javax.faces.component.html.HtmlInputSecret;
import javax.faces.component.html.HtmlInputText;

import com.gnasi.webapp.datos.Resource;

public class LoginBean {
	private Resource userLogin;
	private HtmlInputText user;
	private HtmlInputSecret password;

	
	public Resource getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(Resource userLogin) {
		this.userLogin = userLogin;
	}

	public HtmlInputText getUser() {
		return user;
	}

	public void setUser(HtmlInputText user) {
		this.user = user;
	}

	public HtmlInputSecret getPassword() {
		return password;
	}

	public void setPassword(HtmlInputSecret password) {
		this.password = password;
	}

	public LoginBean() {
	}
	
	public String login(){
		String usuario = (String) this.user.getValue();
		String password = (String) this.password.getValue();
		this.user.setStyle("");
		this.password.setStyle("");
		if(usuario == null || usuario.equals("")){
			System.out.println("el USUARIO es NULL");
			this.user.setStyle("background-color: red;");
		}
		if(password == null || password.equals("")){
			System.out.println("el USUARIO es NULL");
			this.password.setStyle("background-color: red;");
		}
		System.out.println(usuario + " " + password);
		return null;
	}
}
