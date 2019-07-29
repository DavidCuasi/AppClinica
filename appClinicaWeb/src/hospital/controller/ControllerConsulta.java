package hospital.controller;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import hospital.model.entities.CabeceraConsulta;
import hospital.model.entities.Persona;
import hospital.model.manager.ManagerAuditoria;
import hospital.model.manager.ManagerConsulta;
import hospital.model.manager.ManagerUsuario;
import hospital.view.util.JSFUtil;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@ManagedBean
@SessionScoped
public class ControllerConsulta {
	private long idCon;
	private Date fechaCon;
	private String indicacionesCon;
	private String motivoCon;
	private String observacionesCon;
	private BigDecimal pesoCon;
	private BigDecimal presionCon;
	private String sintomasCon;
	private BigDecimal temperaturaCon;
	private String cedulaEmp;
	private List<Persona> listaPersona;
	private List<CabeceraConsulta> listaCabecera;
	private String usuario;

	@EJB
	private ManagerUsuario managerUsuario;

	@EJB
	private ManagerConsulta managerConsulta;
	
	@EJB
	private ManagerAuditoria managerAuditoria;

	@PostConstruct
	private void cargar() {
		listaPersona = managerUsuario.findAllPersonas();
		listaCabecera = managerConsulta.findAllConsultas();
		fechaCon = new Date();
		this.usuario = JSFUtil.getUserConnected();
	}

	public void actionRegistrarConsulta() {
		try {
			managerConsulta.crearConsulta(cedulaEmp, fechaCon, indicacionesCon, motivoCon, observacionesCon, pesoCon,
					presionCon, sintomasCon, temperaturaCon);
			listaCabecera = managerConsulta.findAllConsultas();
			JSFUtil.crearMensajeInfo("Nueva Consulta registrada.");
			this.managerAuditoria.registrarAccion(this.usuario,"Éxito", "Consulta para "+this.cedulaEmp+" registrada");
		} catch (Exception e) {
			this.managerAuditoria.registrarAccion(this.usuario,"Error", "Error al registrar consulta para "+this.cedulaEmp+": "+e.getMessage());
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerEliminarConsulta(long idCon) {
		try {
			managerConsulta.eliminarConsulta(idCon);
			listaCabecera = managerConsulta.findAllConsultas();
			JSFUtil.crearMensajeInfo("Consulta " + idCon + " eliminada.");
			this.managerAuditoria.registrarAccion(this.usuario,"Éxito", "Consulta "+idCon+" eliminada");
		} catch (Exception e) {
			this.managerAuditoria.registrarAccion(this.usuario,"Error", "Error al eliminar consulta "+idCon+": "+e.getMessage());
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerCargarConsulta(CabeceraConsulta cabeceraConsulta) {
		cedulaEmp = cabeceraConsulta.getPersona().getCedulaEmp();
		idCon = cabeceraConsulta.getIdCon();
		fechaCon = cabeceraConsulta.getFechaCon();
		indicacionesCon = cabeceraConsulta.getIndicacionesCon();
		motivoCon = cabeceraConsulta.getMotivoCon();
		observacionesCon = cabeceraConsulta.getObservacionesCon();
		pesoCon = cabeceraConsulta.getPesoCon();
		presionCon = cabeceraConsulta.getPresionCon();
		sintomasCon = cabeceraConsulta.getSintomasCon();
		temperaturaCon = cabeceraConsulta.getTemperaturaCon();
	}

	public void actionListenerActualizarConsulta() {
		try {
			managerConsulta.editarBlog(idCon, fechaCon, indicacionesCon, motivoCon, observacionesCon, pesoCon,
					presionCon, sintomasCon, temperaturaCon);
			listaCabecera = managerConsulta.findAllConsultas();
			JSFUtil.crearMensajeInfo("Actualización correcta.");
			this.managerAuditoria.registrarAccion(this.usuario,"Éxito", "Consulta "+this.idCon+" actualizada");
		} catch (Exception e) {
			this.managerAuditoria.registrarAccion(this.usuario,"Error", "Error al actualizar consulta "+this.idCon+": "+e.getMessage());
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public String actionReporte(long idCon) {
		Map<String, Object> parametros = new HashMap<String, Object>();

		parametros.put("Parameter1", idCon);

		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
		String ruta = servletContext.getRealPath("medico/ReporteConsultaMedica.jasper");
		System.out.println(ruta);
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		response.addHeader("Content-disposition", "attachment;filename=ReporteConsultaMedica.pdf");
		response.setContentType("application/pdf");
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = null;
			connection = DriverManager.getConnection("jdbc:postgresql://186.71.221.50:5432/Clinica", "postgres", "dba.1234");
			JasperPrint impresion = JasperFillManager.fillReport(ruta, parametros, connection);
			JasperExportManager.exportReportToPdfStream(impresion, response.getOutputStream());
			context.getApplication().getStateManager().saveView(context);
			System.out.println("reporte generado.");
			context.responseComplete();
			
			this.managerAuditoria.registrarAccion(this.usuario,"Éxito", "Reporte generado");
		} catch (Exception e) {
			this.managerAuditoria.registrarAccion(this.usuario,"Error", "Error al generar reporte: "+e.getMessage());
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		return "";
	}

	public long getIdCon() {
		return idCon;
	}

	public void setIdCon(long idCon) {
		this.idCon = idCon;
	}

	public Date getFechaCon() {
		return fechaCon;
	}

	public void setFechaCon(Date fechaCon) {
		this.fechaCon = fechaCon;
	}

	public String getIndicacionesCon() {
		return indicacionesCon;
	}

	public void setIndicacionesCon(String indicacionesCon) {
		this.indicacionesCon = indicacionesCon;
	}

	public String getMotivoCon() {
		return motivoCon;
	}

	public void setMotivoCon(String motivoCon) {
		this.motivoCon = motivoCon;
	}

	public String getObservacionesCon() {
		return observacionesCon;
	}

	public void setObservacionesCon(String observacionesCon) {
		this.observacionesCon = observacionesCon;
	}

	public BigDecimal getPesoCon() {
		return pesoCon;
	}

	public void setPesoCon(BigDecimal pesoCon) {
		this.pesoCon = pesoCon;
	}

	public BigDecimal getPresionCon() {
		return presionCon;
	}

	public void setPresionCon(BigDecimal presionCon) {
		this.presionCon = presionCon;
	}

	public String getSintomasCon() {
		return sintomasCon;
	}

	public void setSintomasCon(String sintomasCon) {
		this.sintomasCon = sintomasCon;
	}

	public BigDecimal getTemperaturaCon() {
		return temperaturaCon;
	}

	public void setTemperaturaCon(BigDecimal temperaturaCon) {
		this.temperaturaCon = temperaturaCon;
	}

	public String getCedulaEmp() {
		return cedulaEmp;
	}

	public void setCedulaEmp(String cedulaEmp) {
		this.cedulaEmp = cedulaEmp;
	}

	public List<Persona> getListaPersona() {
		return listaPersona;
	}

	public void setListaPersona(List<Persona> listaPersona) {
		this.listaPersona = listaPersona;
	}

	public List<CabeceraConsulta> getListaCabecera() {
		return listaCabecera;
	}

	public void setListaCabecera(List<CabeceraConsulta> listaCabecera) {
		this.listaCabecera = listaCabecera;
	}

}
