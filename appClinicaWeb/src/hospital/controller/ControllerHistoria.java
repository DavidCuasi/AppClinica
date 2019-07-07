package hospital.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import hospital.model.entities.Empleado;
import hospital.model.entities.Historia;
import hospital.model.manager.ManagerAuditoria;
import hospital.model.manager.ManagerHistoria;
import hospital.view.util.JSFUtil;

@ManagedBean
@SessionScoped
public class ControllerHistoria {
	private String cedulaEmp;
	private String alcoholH;
	private String alimentacionH;
	private String causasHH;
	private String causasMH;
	private String causasPH;
	private String causasSonH;
	private String dipsiaH;
	private String drogasH;
	private String fallecidoMH;
	private String fallecidoPH;
	private String frecuenciaAlcH;
	private String frecuenciaDroH;
	private String frecuenciaInsomH;
	private String frecuenciaParadipsia;
	private String frecuenciaTabH;
	private String insomnioH;
	private String madreH;
	private BigDecimal muerteHH;
	private BigDecimal muertosSonH;
	private BigDecimal numHH;
	private String otroH;
	private String padreH;
	private String paradipsia;
	private BigDecimal sonH;
	private String tabacoH;
	private BigDecimal vivoHH;
	private BigDecimal vivoSonH;
	private List<Historia> listaHistorias;
	private List<Empleado> listaEmpleados;
	private String usuario;

	@EJB
	private ManagerHistoria managerHistoria;
	
	@EJB
	private ManagerAuditoria managerAuditoria;

	@PostConstruct
	private void cargar() {
		listaEmpleados = managerHistoria.findAllEmpleados();
		listaHistorias = managerHistoria.findAllHistorias();
		this.usuario = JSFUtil.getUserConnected();
	}

	public void actionRegistrarHistoria() {
		try {
			managerHistoria.insertHistoria(cedulaEmp, alcoholH, alimentacionH, causasHH, causasMH, causasPH, causasSonH,
					dipsiaH, drogasH, fallecidoMH, fallecidoPH, frecuenciaAlcH, frecuenciaDroH, frecuenciaInsomH,
					frecuenciaParadipsia, frecuenciaTabH, insomnioH, madreH, muerteHH, muertosSonH, numHH, otroH,
					padreH, paradipsia, muertosSonH, tabacoH, vivoHH, vivoSonH);
			listaHistorias = managerHistoria.findAllHistorias();
			JSFUtil.crearMensajeInfo("Nueva Historia registrada.");
			this.managerAuditoria.registrarAccion(this.usuario,"Éxito", "Historia para "+this.cedulaEmp+" registrada");
		} catch (Exception e) {
			this.managerAuditoria.registrarAccion(this.usuario,"Error", "Error al registrar historia para "+this.cedulaEmp+": "+e.getMessage());
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerEliminarHistoria(String cedulaEmp) {
		try {
			System.out.println("llego al controller");
			managerHistoria.deleteHistoria(cedulaEmp);
			listaHistorias = managerHistoria.findAllHistorias();
			JSFUtil.crearMensajeInfo("Historia " + cedulaEmp + " Eliminada.");
			this.managerAuditoria.registrarAccion(this.usuario,"Éxito", "Historia para "+this.cedulaEmp+" eliminada");
		} catch (Exception e) {
			this.managerAuditoria.registrarAccion(this.usuario,"Error", "Error al eliminar historia para "+cedulaEmp+": "+e.getMessage());
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerCargarHistoria(Historia historia) {
		cedulaEmp = historia.getPersona().getCedulaEmp();
		alcoholH = historia.getAlcoholH();
		alimentacionH = historia.getAlimentacionH();
		causasHH = historia.getCausasHH();
		causasMH = historia.getCausasMH();
		causasPH = historia.getCausasPH();
		causasSonH = historia.getCausasSonH();
		dipsiaH = historia.getDipsiaH();
		drogasH = historia.getDrogasH();
		fallecidoMH = historia.getFallecidoMH();
		fallecidoPH = historia.getFallecidoPH();
		frecuenciaAlcH = historia.getFrecuenciaAlcH();
		frecuenciaDroH = historia.getFrecuenciaDroH();
		frecuenciaInsomH = historia.getFrecuenciaInsomH();
		frecuenciaParadipsia = historia.getFrecuenciaParadipsia();
		frecuenciaTabH = historia.getFrecuenciaTabH();
		insomnioH = historia.getInsomnioH();
		madreH = historia.getMadreH();
		muerteHH = historia.getMuerteHH();
		muertosSonH = historia.getMuertosSonH();
		numHH = historia.getNumHH();
		otroH = historia.getOtroH();
		padreH = historia.getPadreH();
		paradipsia = historia.getParadipsia();
		sonH = historia.getSonH();
		tabacoH = historia.getTabacoH();
		vivoHH = historia.getVivoHH();
		vivoSonH = historia.getVivoSonH();
	}

	public void actionListenerActualizarHistoria() {
		try {
			managerHistoria.UpdateHisyoria(cedulaEmp, alcoholH, alimentacionH, causasHH, causasMH, causasPH, causasSonH,
					dipsiaH, drogasH, fallecidoMH, fallecidoPH, frecuenciaAlcH, frecuenciaDroH, frecuenciaInsomH,
					frecuenciaParadipsia, frecuenciaTabH, insomnioH, madreH, muerteHH, muertosSonH, numHH, otroH,
					padreH, paradipsia, muertosSonH, tabacoH, vivoHH, vivoSonH);
			listaHistorias = managerHistoria.findAllHistorias();
			JSFUtil.crearMensajeInfo("Actualización correcta.");
			this.managerAuditoria.registrarAccion(this.usuario,"Éxito", "Historia para "+this.cedulaEmp+" actualizada");
		} catch (Exception e) {
			this.managerAuditoria.registrarAccion(this.usuario,"Error", "Error al actualizar historia para "+this.cedulaEmp+": "+e.getMessage());
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

	public String getAlcoholH() {
		return alcoholH;
	}

	public void setAlcoholH(String alcoholH) {
		this.alcoholH = alcoholH;
	}

	public String getAlimentacionH() {
		return alimentacionH;
	}

	public void setAlimentacionH(String alimentacionH) {
		this.alimentacionH = alimentacionH;
	}

	public String getCausasHH() {
		return causasHH;
	}

	public void setCausasHH(String causasHH) {
		this.causasHH = causasHH;
	}

	public String getCausasMH() {
		return causasMH;
	}

	public void setCausasMH(String causasMH) {
		this.causasMH = causasMH;
	}

	public String getCausasPH() {
		return causasPH;
	}

	public void setCausasPH(String causasPH) {
		this.causasPH = causasPH;
	}

	public String getCausasSonH() {
		return causasSonH;
	}

	public void setCausasSonH(String causasSonH) {
		this.causasSonH = causasSonH;
	}

	public String getDipsiaH() {
		return dipsiaH;
	}

	public void setDipsiaH(String dipsiaH) {
		this.dipsiaH = dipsiaH;
	}

	public String getDrogasH() {
		return drogasH;
	}

	public void setDrogasH(String drogasH) {
		this.drogasH = drogasH;
	}

	public String getFallecidoMH() {
		return fallecidoMH;
	}

	public void setFallecidoMH(String fallecidoMH) {
		this.fallecidoMH = fallecidoMH;
	}

	public String getFallecidoPH() {
		return fallecidoPH;
	}

	public void setFallecidoPH(String fallecidoPH) {
		this.fallecidoPH = fallecidoPH;
	}

	public String getFrecuenciaAlcH() {
		return frecuenciaAlcH;
	}

	public void setFrecuenciaAlcH(String frecuenciaAlcH) {
		this.frecuenciaAlcH = frecuenciaAlcH;
	}

	public String getFrecuenciaDroH() {
		return frecuenciaDroH;
	}

	public void setFrecuenciaDroH(String frecuenciaDroH) {
		this.frecuenciaDroH = frecuenciaDroH;
	}

	public String getFrecuenciaInsomH() {
		return frecuenciaInsomH;
	}

	public void setFrecuenciaInsomH(String frecuenciaInsomH) {
		this.frecuenciaInsomH = frecuenciaInsomH;
	}

	public String getFrecuenciaParadipsia() {
		return frecuenciaParadipsia;
	}

	public void setFrecuenciaParadipsia(String frecuenciaParadipsia) {
		this.frecuenciaParadipsia = frecuenciaParadipsia;
	}

	public String getFrecuenciaTabH() {
		return frecuenciaTabH;
	}

	public void setFrecuenciaTabH(String frecuenciaTabH) {
		this.frecuenciaTabH = frecuenciaTabH;
	}

	public String getInsomnioH() {
		return insomnioH;
	}

	public void setInsomnioH(String insomnioH) {
		this.insomnioH = insomnioH;
	}

	public String getMadreH() {
		return madreH;
	}

	public void setMadreH(String madreH) {
		this.madreH = madreH;
	}

	public BigDecimal getMuerteHH() {
		return muerteHH;
	}

	public void setMuerteHH(BigDecimal muerteHH) {
		this.muerteHH = muerteHH;
	}

	public BigDecimal getMuertosSonH() {
		return muertosSonH;
	}

	public void setMuertosSonH(BigDecimal muertosSonH) {
		this.muertosSonH = muertosSonH;
	}

	public BigDecimal getNumHH() {
		return numHH;
	}

	public void setNumHH(BigDecimal numHH) {
		this.numHH = numHH;
	}

	public String getOtroH() {
		return otroH;
	}

	public void setOtroH(String otroH) {
		this.otroH = otroH;
	}

	public String getPadreH() {
		return padreH;
	}

	public void setPadreH(String padreH) {
		this.padreH = padreH;
	}

	public String getParadipsia() {
		return paradipsia;
	}

	public void setParadipsia(String paradipsia) {
		this.paradipsia = paradipsia;
	}

	public BigDecimal getSonH() {
		return sonH;
	}

	public void setSonH(BigDecimal sonH) {
		this.sonH = sonH;
	}

	public String getTabacoH() {
		return tabacoH;
	}

	public void setTabacoH(String tabacoH) {
		this.tabacoH = tabacoH;
	}

	public BigDecimal getVivoHH() {
		return vivoHH;
	}

	public void setVivoHH(BigDecimal vivoHH) {
		this.vivoHH = vivoHH;
	}

	public BigDecimal getVivoSonH() {
		return vivoSonH;
	}

	public void setVivoSonH(BigDecimal vivoSonH) {
		this.vivoSonH = vivoSonH;
	}

	public List<Historia> getListaHistorias() {
		return listaHistorias;
	}

	public void setListaHistorias(List<Historia> listaHistorias) {
		this.listaHistorias = listaHistorias;
	}

	public List<Empleado> getListaEmpleados() {
		return listaEmpleados;
	}

	public void setListaEmpleados(List<Empleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}

}
