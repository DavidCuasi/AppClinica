package hospital.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.codec.digest.DigestUtils;

import hospital.model.entities.Empleado;
import hospital.model.entities.Persona;
import hospital.model.manager.ManagerAuditoria;
import hospital.model.manager.ManagerEmpleado;
import hospital.model.manager.ManagerUsuario;
import hospital.model.util.ModelUtil;
import hospital.view.util.EmailSender;
import hospital.view.util.JSFUtil;
import hospital.view.util.StringUtil;

@ManagedBean
@SessionScoped
public class ControllerLogin {

	private String cedulaEmp;
	private String paswordUs;
	private String Rol;
	private boolean confirmadoLogin;
	private String usuario;

	@EJB
	private ManagerEmpleado managerEmpleados;

	@EJB
	private ManagerUsuario managerUsuarios;

	@EJB
	private ManagerAuditoria managerAuditoria;

	public String actionLogin() {
		try {
			String claveEncriptada = DigestUtils.sha256Hex(this.paswordUs);
			confirmadoLogin = managerUsuarios.comprobarUsuario(this.cedulaEmp, claveEncriptada);

			JSFUtil.crearMensajeInfo("Login correcto");
			Empleado e = managerUsuarios.findEmpleadoById(cedulaEmp);
			Rol = e.getCargoUs();
			if (Rol.equals("ADMINISTRADOR")) {
				this.guardarUsuarioSesion();
				return "admin/index?faces-redirect=true";
			} else if (Rol.equals("MEDICO")) {
				this.guardarUsuarioSesion();
				return "medico/index?faces-redirect=true";
			} else if (Rol.equals("AUDITOR")) {
				this.guardarUsuarioSesion();
				return "auditor/index?faces-redirect=true";
			} else {
				this.managerAuditoria.registrarAccion(cedulaEmp, "Alerta", "Usuario sin permisos");
				JSFUtil.crearMensajeInfo("USUARIO SIN PERMISOS.");
			}
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
			this.managerAuditoria.registrarAccion(cedulaEmp, "Error", e.getMessage());
		}
		return "";
	}

	public void actionListenerRecoverPassword() {
		Empleado e = managerUsuarios.findEmpleadoById(cedulaEmp);
		if (e == null) {
			JSFUtil.crearMensajeError("No se encontró el usuario " + this.cedulaEmp);
		} else {
			Persona per = e.getPersona();

			String correo = per.getEmailEmp();
			String nuevaClave = StringUtil.generarClave();
			String claveEncriptada = DigestUtils.sha256Hex(nuevaClave);

			String mensaje = "<p>Estimad@ " + per.getNombresEmp() + "</p><p>Su nueva contraseña es la siguiente: <b>"
					+ nuevaClave + "</b> </p>";

			try {
				EmailSender.enviarMail(correo, "Reseteo de contraseña", mensaje);

				e.setPaswordUs(claveEncriptada);
				this.managerEmpleados.UpdateEmpleado(e);

				JSFUtil.crearMensajeInfo("Se ha enviado la nueva contraseña al correo " + correo);
				this.managerAuditoria.registrarAccion(cedulaEmp, "Éxito", "Reseteo de contraseña");
			} catch (Exception ex) {
				JSFUtil.crearMensajeError("Error al enviar el correo a " + correo);
				this.managerAuditoria.registrarAccion(cedulaEmp, "Error", ex.getMessage());
			}
		}
	}

	private void guardarUsuarioSesion() {
		this.managerAuditoria.registrarAccion(cedulaEmp, "Éxito", "Iniciar Sesión");
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", cedulaEmp);
	}

	public String actionSalir() {
		this.managerAuditoria.registrarAccion(cedulaEmp, "Éxito", "Cerrar Sesión");
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
				this.usuario = e.getPersona().getNombresEmp();
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

				if (Rol.equals("AUDITOR")) {
					if (!path.contains("/auditor/"))
						ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
					else
						return;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			this.managerAuditoria.registrarAccion(cedulaEmp, "Error", e.toString());
		}
	}

	public String actionReturnAdmin() {
		return "/admin/index?faces-redirect=true";
	}

	public String actionReturnMedico() {
		return "/medico/index?faces-redirect=true";
	}

	public String actionReturnAuditor() {
		return "/auditor/index?faces-redirect=true";
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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
