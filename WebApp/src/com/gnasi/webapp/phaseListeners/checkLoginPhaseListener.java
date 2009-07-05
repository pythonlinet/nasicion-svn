package com.gnasi.webapp.phaseListeners;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class checkLoginPhaseListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent pe) {
		FacesContext fc = pe.getFacesContext();
		System.out.println("after - " + pe.getPhaseId().toString() + "---"	+ pe.getFacesContext().getViewRoot().getViewId());
		
		System.out.println(fc.getViewRoot().getViewId() + " - " + fc.getViewRoot().getViewId().contains("login"));
		
		 // revisamos si esta en la pagina de login
        boolean loginPage = fc.getViewRoot().getViewId().contains("login");  
		
		if (pe.getPhaseId() == PhaseId.RESTORE_VIEW) {
			//Si no esta en la pagina de login y no esta logueado
			if (!loginPage && !loggedIn()) {
				//redirigimos a la pagina de login
				NavigationHandler nh = fc.getApplication().getNavigationHandler();
				nh.handleNavigation(fc, null, "goLogin");
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent pe) {

		if (pe.getPhaseId() == PhaseId.RESTORE_VIEW) {
			System.out.println(this.getClass().getName() + " está procesando una nueva peticion");
		}
		System.out.println("before - " + pe.getPhaseId().toString());
	}

	private boolean loggedIn() {
		boolean salida = false;

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		// Comprobamos que el usuario este logueado
		salida = session.getAttribute("usuario") != null;
		System.out.println(this.getClass().getName() + " Session Id " + session.getId());
		
		return salida;
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
