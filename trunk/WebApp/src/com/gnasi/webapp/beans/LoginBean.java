package com.gnasi.webapp.beans;

import javax.faces.component.html.HtmlInputSecret;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
		String salida = null;
		
		String usuario = (String) this.user.getValue();
		String password = (String) this.password.getValue();
		this.user.setStyle("");
		this.password.setStyle("");
		
		if(usuario == null || usuario.equals("")){
			System.out.println("el USUARIO es NULL");
			this.user.setStyle("background-color: red;");
		}else
		if(password == null || password.equals("")){
			System.out.println("el USUARIO es NULL");
			this.password.setStyle("background-color: red;");
		}else
			salida = "goMain";
		this.userLogin = new Resource();
		userLogin.setUsuario(usuario);
		
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) ctx.getExternalContext().getSession(true);
		session.setAttribute("usuario", userLogin);
		System.out.println(this.getClass().getName() + " Session Id " + session.getId());
		
		
		return salida;
	}
	public String logOut(){
		String salida = "goLogin";
		
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) ctx.getExternalContext().getSession(true);
		session.invalidate();
		
		
		return salida;
	}
}
