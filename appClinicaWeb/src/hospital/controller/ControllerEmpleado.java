package hospital.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import hospital.model.entities.AreaTrabajo;
import hospital.model.entities.Empleado;
import hospital.model.entities.Persona;
import hospital.model.manager.ManagerEmpleado;
import hospital.model.manager.ManagerUsuario;
import hospital.view.util.JSFUtil;

/**
 * 
 * @author Santiago Lomas Almeida
 *
 */

@ManagedBean
@SessionScoped
public class ControllerEmpleado {

	private String cedulaEmp;
	private long idArea;
	private String cargoUs;
	private String paswordUs;
	private String activoEmp;
	private List<Persona> listaPersona;
	private List<Empleado> listaEmpleados;
	private List<AreaTrabajo> listaAreaTrabajo;

	@EJB
	private ManagerEmpleado managerEmpleado;

	@PostConstruct
	private void cargar() {
		listaEmpleados = managerEmpleado.findAllEmpleados();
		listaPersona = managerEmpleado.findAllPersonas();
		listaAreaTrabajo = managerEmpleado.findAllAreas();
	}

	public void actionRegistrarEmpleado() {
		try {
			managerEmpleado.insertEmpleado(cedulaEmp, idArea, cargoUs, paswordUs, activoEmp);
			listaEmpleados = managerEmpleado.findAllEmpleados();
			JSFUtil.crearMensajeInfo("Nuevo Empleado registrado.");

		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionListenerEliminarEmpleado(String cedEmp) {
		try {
			System.out.println("llego al controller");
			managerEmpleado.deleteEMPLEADO(cedEmp);
			listaEmpleados = managerEmpleado.findAllEmpleados();
			JSFUtil.crearMensajeInfo("Empleado " + cedEmp + " eliminado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerCargarEmpleado(Empleado empleado) {
		cedulaEmp = empleado.getPersona().getCedulaEmp();
		cargoUs = empleado.getCargoUs();
		idArea = empleado.getAreaTrabajo().getIdArea();
		paswordUs = empleado.getPaswordUs();
		activoEmp = empleado.getActivoEmp();
	}

	public void actionListenerActualizarEmpleado() {
		try {
			managerEmpleado.UpdateEmpleado(cedulaEmp, idArea, cargoUs, paswordUs, activoEmp);
			listaEmpleados = managerEmpleado.findAllEmpleados();
			JSFUtil.crearMensajeInfo("Actualización correcta.");
		} catch (Exception e) {
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

	public String getPaswordUs() {
		return paswordUs;
	}

	public void setPaswordUs(String paswordUs) {
		this.paswordUs = paswordUs;
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
