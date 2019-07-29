package hospital.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.MessagingException;

import org.apache.commons.codec.digest.DigestUtils;

import hospital.model.entities.AreaTrabajo;
import hospital.model.entities.Empleado;
import hospital.model.entities.Persona;
import hospital.model.manager.ManagerAuditoria;
import hospital.model.manager.ManagerEmpleado;
import hospital.view.util.EmailSender;
import hospital.view.util.JSFUtil;
import hospital.view.util.StringUtil;

@ManagedBean
@SessionScoped
public class ControllerEmpleado {

	private String cedulaEmp;
	private long idArea;
	private String cargoUs;
	private String activoEmp;
	private List<Persona> listaPersona;
	private List<Empleado> listaEmpleados;
	private List<AreaTrabajo> listaAreaTrabajo;
	private String usuario;

	@EJB
	private ManagerEmpleado managerEmpleado;

	@EJB
	private ManagerAuditoria managerAuditoria;

	@PostConstruct
	private void cargar() {
		listaEmpleados = managerEmpleado.findAllEmpleados();
		listaPersona = managerEmpleado.findAllPersonas();
		listaAreaTrabajo = managerEmpleado.findAllAreas();
		this.usuario = JSFUtil.getUserConnected();
	}

	public void actionRegistrarEmpleado() {
		try {
			Persona per = this.managerEmpleado.findPersonaById(this.cedulaEmp);
			if (per == null) {
				JSFUtil.crearMensajeError("No existe la persona con cédula " + this.cedulaEmp);
				return;
			}

			String clave = StringUtil.generarClave();
			String claveEncriptada = DigestUtils.sha256Hex(clave);

			String mensaje = "<p>Estimad@ " + per.getNombresEmp() + "</p><p>Esta es su contraseña <b>" + clave
					+ "</b> para <a href=\"http://186.71.221.50:9090/appClinicaWeb/faces/login.xhtml\">acceder</a> al sistema.</p>";
			EmailSender.enviarMail(per.getEmailEmp(), "Nuevo usuario", mensaje);

			managerEmpleado.insertEmpleado(cedulaEmp, idArea, cargoUs, claveEncriptada, activoEmp);
			listaEmpleados = managerEmpleado.findAllEmpleados();
			JSFUtil.crearMensajeInfo("Nuevo Empleado registrado.");
			this.managerAuditoria.registrarAccion(this.usuario, "Éxito", "Empleado " + this.cedulaEmp + " registrado");
		} catch (MessagingException | UnsupportedEncodingException exm) {
			JSFUtil.crearMensajeError("No se puedo enviar el correo");
		} catch (Exception e) {
			this.managerAuditoria.registrarAccion(this.usuario, "Error",
					"Error al registrar el empleado " + this.cedulaEmp + ": " + e.getMessage());
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerEliminarEmpleado(String cedEmp) {
		try {
			System.out.println("llego al controller");
			managerEmpleado.deleteEMPLEADO(cedEmp);
			listaEmpleados = managerEmpleado.findAllEmpleados();
			this.managerAuditoria.registrarAccion(this.usuario, "Éxito", "Empleado " + cedEmp + " eliminado");
			JSFUtil.crearMensajeInfo("Empleado " + cedEmp + " eliminado.");
		} catch (Exception e) {
			this.managerAuditoria.registrarAccion(this.usuario, "Error",
					"Error al eliminar el empleado " + cedEmp + ": " + e.getMessage());
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerCargarEmpleado(Empleado empleado) {
		cedulaEmp = empleado.getPersona().getCedulaEmp();
		cargoUs = empleado.getCargoUs();
		idArea = empleado.getAreaTrabajo().getIdArea();
		activoEmp = empleado.getActivoEmp();
	}

	public void actionListenerActualizarEmpleado() {
		try {
			managerEmpleado.UpdateEmpleado(cedulaEmp, idArea, cargoUs, activoEmp);
			listaEmpleados = managerEmpleado.findAllEmpleados();
			JSFUtil.crearMensajeInfo("Actualización correcta.");
			this.managerAuditoria.registrarAccion(this.usuario, "Éxito", "Empleado " + this.cedulaEmp + " actualizado");
		} catch (Exception e) {
			this.managerAuditoria.registrarAccion(this.usuario, "Error",
					"Error al actualizar el empleado " + this.cedulaEmp + ": " + e.getMessage());
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

	public long getIdArea() {
		return idArea;
	}

	public void setIdArea(long idArea) {
		this.idArea = idArea;
	}

	public String getCargoUs() {
		return cargoUs;
	}

	public void setCargoUs(String cargoUs) {
		this.cargoUs = cargoUs;
	}

	public String getActivoEmp() {
		return activoEmp;
	}

	public void setActivoEmp(String activoEmp) {
		this.activoEmp = activoEmp;
	}

	public List<Persona> getListaPersona() {
		return listaPersona;
	}

	public void setListaPersona(List<Persona> listaPersona) {
		this.listaPersona = listaPersona;
	}

	public List<Empleado> getListaEmpleados() {
		return listaEmpleados;
	}

	public void setListaEmpleados(List<Empleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}

	public List<AreaTrabajo> getListaAreaTrabajo() {
		return listaAreaTrabajo;
	}

	public void setListaAreaTrabajo(List<AreaTrabajo> listaAreaTrabajo) {
		this.listaAreaTrabajo = listaAreaTrabajo;
	}

}
