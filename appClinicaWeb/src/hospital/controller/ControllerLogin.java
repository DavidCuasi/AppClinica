package hospital.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import hospital.model.entities.Empleado;
import hospital.model.manager.ManagerUsuario;
import hospital.model.util.ModelUtil;
import hospital.view.util.JSFUtil;

@ManagedBean
@SessionScoped
public class ControllerLogin {

	private String cedulaEmp;
	private String paswordUs;
	private String Rol;
	private boolean confirmadoLogin;

	@EJB
	private ManagerUsuario managerUsuarios;

	public String actionLogin() {
		try {
			confirmadoLogin = managerUsuarios.comprobarUsuario(cedulaEmp, paswordUs);
			JSFUtil.crearMensajeInfo("Login correcto");
			Empleado e = managerUsuarios.findEmpleadoById(cedulaEmp);
			Rol = e.getCargoUs();
			if (Rol.equals("ADMINISTRADOR")) {
				return "admin/index?faces-redirect=true";
			} else if (Rol.equals("MEDICO")) {
				return "medico/index?faces-redirect=true";
			} else {
				JSFUtil.crearMensajeInfo("USUARIO SIN PERMISOS.");
			}
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		return "";
	}

	public String actionSalir() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login?faces-redirect=true";
	}

	public void actionComprobarLogin() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			String path = ec.getRequestPathInfo();
			System.out.println("getRequestContextPath(): " + ec.getRequestContextPath());
			System.out.println("getRequestPathInfo(): " + path);
			System.out.println("Id usuario: " + cedulaEmp);
			if (path.equals("/login.xhtml"))
				return;
			if (ModelUtil.isEmpty(cedulaEmp))
				ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
			if (!confirmadoLogin) {
				ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
			} else {
				Empleado e = managerUsuarios.findEmpleadoById(cedulaEmp);
				Rol = e.getCargoUs();
				if (Rol.equals("ADMINISTRADOR")) {
					if (!path.contains("/admin/"))
						ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
					else
						return;
				}

				if (Rol.equals("MEDICO")) {
					if (!path.contains("/medico/"))
						ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
					else
						return;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String actionReturnAdmin() {
		return "/admin/index?faces-redirect=true";
	}

	public String actionReturnMedico() {
		return "/medico/index?faces-redirect=true";
	}

	public String getCedulaEmp() {
		return cedulaEmp;
	}

	public void setCedulaEmp(String cedulaEmp) {
		this.cedulaEmp = cedulaEmp;
	}

	public String getPaswordUs() {
		return paswordUs;
	}

	public void setPaswordUs(String paswordUs) {
		this.paswordUs = paswordUs;
	}

	public String getRol() {
		return Rol;
	}

	public void setRol(String rol) {
		Rol = rol;
	}

	public boolean isConfirmadoLogin() {
		return confirmadoLogin;
	}

}
