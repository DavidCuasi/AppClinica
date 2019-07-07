package hospital.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import hospital.model.entities.Persona;
import hospital.model.manager.ManagerAuditoria;
import hospital.model.manager.ManagerUsuario;
import hospital.view.util.JSFUtil;

@ManagedBean
@SessionScoped
public class ControllerUsuario {

	private String cedulaEmp;
	private String ciudadnaciEmp;
	private String direccioEmp;
	private String emailEmp;
	private String estadoEmp;
	private Date nacifechaEmp;
	private String nacionalidadEmp;
	private String nombresEmp;
	private String telefonoEmp;
	private String tipoEmp;
	private List<Persona> lista;
	private String usuario;

	@EJB
	private ManagerUsuario managerUsuarios;
	
	@EJB
	private ManagerAuditoria managerAuditoria;

	@PostConstruct
	private void cargar() {
		lista = managerUsuarios.findAllPersonas();
		this.usuario = JSFUtil.getUserConnected();
	}

	public void actionRegistrarPersona() {
		try {
			managerUsuarios.insertPersona(cedulaEmp, ciudadnaciEmp, direccioEmp, emailEmp, estadoEmp, nacifechaEmp,
					nacionalidadEmp, nombresEmp, telefonoEmp, tipoEmp);
			lista = managerUsuarios.findAllPersonas();
			JSFUtil.crearMensajeInfo("Nuevo Usuario registrado.");
			this.managerAuditoria.registrarAccion(this.usuario,"Éxito", "Usuario "+this.cedulaEmp+" registrado");
		} catch (Exception e) {
			this.managerAuditoria.registrarAccion(this.usuario,"Error", "Error al registrar el usuario "+this.cedulaEmp+": "+e.getMessage());
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionListenerEliminarPersona(String cedulaEmp) {
		try {
			managerUsuarios.deletePersona(cedulaEmp);
			lista = managerUsuarios.findAllPersonas();
			JSFUtil.crearMensajeInfo("Usuario " + cedulaEmp + " eliminado.");
			this.managerAuditoria.registrarAccion(this.usuario,"Éxito", "Usuario "+this.cedulaEmp+" eliminado");
		} catch (Exception e) {
			this.managerAuditoria.registrarAccion(this.usuario,"Error", "Error al eliminar el usuario "+this.cedulaEmp+": "+e.getMessage());
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerCargarPersona(Persona persona) {

		cedulaEmp = persona.getCedulaEmp();
		ciudadnaciEmp = persona.getCiudadnaciEmp();
		direccioEmp = persona.getDireccioEmp();
		emailEmp = persona.getEmailEmp();
		estadoEmp = persona.getEstadoEmp();
		nacifechaEmp = persona.getNacifechaEmp();
		nacionalidadEmp = persona.getNacionalidadEmp();
		nombresEmp = persona.getNombresEmp();
		telefonoEmp = persona.getTelefonoEmp();
		tipoEmp = persona.getTipoEmp();

	}

	public void actionListenerActualizarPersona() {
		try {
			managerUsuarios.UpdatePersona(cedulaEmp, ciudadnaciEmp, direccioEmp, emailEmp, estadoEmp, nacifechaEmp,
					nacionalidadEmp, nombresEmp, telefonoEmp, tipoEmp);
			lista = managerUsuarios.findAllPersonas();
			JSFUtil.crearMensajeInfo("Actualización correcta.");
			this.managerAuditoria.registrarAccion(this.usuario,"Éxito", "Usuario "+this.cedulaEmp+" actualizado");
		} catch (Exception e) {
			this.managerAuditoria.registrarAccion(this.usuario,"Error", "Error al actualizar el usuario "+this.cedulaEmp+": "+e.getMessage());
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public String getCedulaEmp() {
		return cedulaEmp;
	}

	public void setCedulaEmp(String cedulaEmp) {
		this.cedulaEmp = cedulaEmp;
	}

	public String getCiudadnaciEmp() {
		return ciudadnaciEmp;
	}

	public void setCiudadnaciEmp(String ciudadnaciEmp) {
		this.ciudadnaciEmp = ciudadnaciEmp;
	}

	public String getDireccioEmp() {
		return direccioEmp;
	}

	public void setDireccioEmp(String direccioEmp) {
		this.direccioEmp = direccioEmp;
	}

	public String getEmailEmp() {
		return emailEmp;
	}

	public void setEmailEmp(String emailEmp) {
		this.emailEmp = emailEmp;
	}

	public String getEstadoEmp() {
		return estadoEmp;
	}

	public void setEstadoEmp(String estadoEmp) {
		this.estadoEmp = estadoEmp;
	}

	public Date getNacifechaEmp() {
		return nacifechaEmp;
	}

	public void setNacifechaEmp(Date nacifechaEmp) {
		this.nacifechaEmp = nacifechaEmp;
	}

	public String getNacionalidadEmp() {
		return nacionalidadEmp;
	}

	public void setNacionalidadEmp(String nacionalidadEmp) {
		this.nacionalidadEmp = nacionalidadEmp;
	}

	public String getNombresEmp() {
		return nombresEmp;
	}

	public void setNombresEmp(String nombresEmp) {
		this.nombresEmp = nombresEmp;
	}

	public String getTelefonoEmp() {
		return telefonoEmp;
	}

	public void setTelefonoEmp(String telefonoEmp) {
		this.telefonoEmp = telefonoEmp;
	}

	public String getTipoEmp() {
		return tipoEmp;
	}

	public void setTipoEmp(String tipoEmp) {
		this.tipoEmp = tipoEmp;
	}

	public List<Persona> getLista() {
		return lista;
	}

	public void setLista(List<Persona> lista) {
		this.lista = lista;
	}

}
