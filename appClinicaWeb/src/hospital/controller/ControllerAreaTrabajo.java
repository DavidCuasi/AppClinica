package hospital.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import hospital.model.entities.AreaTrabajo;
import hospital.model.manager.ManagerAreaTrabajo;
import hospital.model.manager.ManagerAuditoria;
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
	private String usuario;

	@EJB
	private ManagerAreaTrabajo managerAreaTrabajo;

	@EJB
	private ManagerAuditoria managerAuditoria;

	@PostConstruct
	private void cargar() {
		lista = managerAreaTrabajo.findAllAreas();
		this.usuario = JSFUtil.getUserConnected();
	}

	public void actionListenerRegistrarArea() {
		try {
			managerAreaTrabajo.insertAreaTrabajo(nombreArea, gerenciaArea, descripcionArea, estadoArea);
			lista = managerAreaTrabajo.findAllAreas();
			JSFUtil.crearMensajeInfo("Nueva Area de Trabajo registrada.");
			this.managerAuditoria.registrarAccion(usuario, "Éxito", "Área de trabajo " + nombreArea + " registrada");
		} catch (Exception e) {
			this.managerAuditoria.registrarAccion(usuario, "Error", "Error al registrar área: " + e.getMessage());
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionListenerEliminarArea(long idArea) {
		try {
			managerAreaTrabajo.deleteAreaTrabajo(idArea);
			lista = managerAreaTrabajo.findAllAreas();
			JSFUtil.crearMensajeInfo("Área de trabajo " + idArea + " eliminada");
			this.managerAuditoria.registrarAccion(usuario, "Éxito", "Área de trabajo " + idArea + " eliminada");
		} catch (Exception e) {
			this.managerAuditoria.registrarAccion(usuario, "Error", "Error al eliminar área de trabajo " + idArea + ": " + e.getMessage());
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
			this.managerAuditoria.registrarAccion(usuario, "Éxito", "Área de trabajo " + idArea + " actualizada");
		} catch (Exception e) {
			this.managerAuditoria.registrarAccion(usuario, "Error",
					"Error al actualizar área de trabajo " + idArea + ": " + e.getMessage());
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
