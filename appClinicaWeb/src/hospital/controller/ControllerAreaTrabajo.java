package hospital.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import hospital.model.entities.AreaTrabajo;
import hospital.model.manager.ManagerAreaTrabajo;
import hospital.view.util.JSFUtil;

@ManagedBean
@SessionScoped
public class ControllerAreaTrabajo {

	private long idArea;
	private String descripcionArea;
	private String estadoArea;
	private String gerenciaArea;
	private String nombreArea;
	private List<AreaTrabajo> lista;

	@EJB
	private ManagerAreaTrabajo managerAreaTrabajo;

	@PostConstruct
	private void cargar() {
		lista = managerAreaTrabajo.findAllAreas();
	}

	public void actionListenerRegistrarArea() {
		try {
			managerAreaTrabajo.insertAreaTrabajo(nombreArea, gerenciaArea, descripcionArea, estadoArea);
			lista = managerAreaTrabajo.findAllAreas();
			JSFUtil.crearMensajeInfo("Nueva Area de Trabajo registrada.");

		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionListenerEliminarArea(long idArea) {
		try {
			managerAreaTrabajo.deleteAreaTrabajo(idArea);
			lista = managerAreaTrabajo.findAllAreas();
			JSFUtil.crearMensajeInfo("Area de trabajo " + idArea + " eliminada.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerCargarArea(AreaTrabajo areaTrabajo) {

		idArea = areaTrabajo.getIdArea();
		descripcionArea = areaTrabajo.getDescripcionArea();
		estadoArea = areaTrabajo.getEstadoArea();
		gerenciaArea = areaTrabajo.getGerenciaArea();
		nombreArea = areaTrabajo.getNombreArea();
	}

	public void actionListenerActualizarArea() {
		try {
			managerAreaTrabajo.UpdateAreaTrabajo(idArea, nombreArea, gerenciaArea, descripcionArea, estadoArea);
			lista = managerAreaTrabajo.findAllAreas();
			JSFUtil.crearMensajeInfo("Actualización correcta.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public long getIdArea() {
		return idArea;
	}

	public void setIdArea(long idArea) {
		this.idArea = idArea;
	}

	public String getDescripcionArea() {
		return descripcionArea;
	}

	public void setDescripcionArea(String descripcionArea) {
		this.descripcionArea = descripcionArea;
	}

	public String getEstadoArea() {
		return estadoArea;
	}

	public void setEstadoArea(String estadoArea) {
		this.estadoArea = estadoArea;
	}

	public String getGerenciaArea() {
		return gerenciaArea;
	}

	public void setGerenciaArea(String gerenciaArea) {
		this.gerenciaArea = gerenciaArea;
	}

	public String getNombreArea() {
		return nombreArea;
	}

	public void setNombreArea(String nombreArea) {
		this.nombreArea = nombreArea;
	}

	public List<AreaTrabajo> getLista() {
		return lista;
	}

	public void setLista(List<AreaTrabajo> lista) {
		this.lista = lista;
	}

}
