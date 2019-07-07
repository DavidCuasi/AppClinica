package hospital.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import hospital.model.entities.Auditoria;
import hospital.model.manager.ManagerAuditoria;

@ManagedBean
@SessionScoped
public class ControllerLog {

	@EJB
	private ManagerAuditoria managerAuditoria;
	
	private List<Auditoria> lista;
	
	@PostConstruct
	private void cargar() {
		this.lista = this.managerAuditoria.findAll();
	}

	public List<Auditoria> getLista() {
		return lista;
	}

	public void setLista(List<Auditoria> lista) {
		this.lista = lista;
	}
}
